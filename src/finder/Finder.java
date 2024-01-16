package finder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.util.concurrent.CopyOnWriteArrayList;

public class Finder {

    private final String path;
    private final String searchWord;
    private final CopyOnWriteArrayList<String> resultList;

    public Finder(String path, String searchWord) {
        this.path = path;
        this.searchWord = searchWord;
        this.resultList = new CopyOnWriteArrayList<>();
    }

    public List<String> start() {
        List<File> listRepository = getAllRepository();
        List<Thread> threads = new ArrayList<>();

        for (File repository : listRepository) {
            threads.add(new Thread(() -> foundProcessor(repository.getPath())));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

        return this.resultList;
    }

    private List<File> getAllRepository() {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();

        return Arrays.asList(arrFiles);
    }

    private void foundProcessor(String repositoryPath) {

        ArrayList<String> searchWorldsList = new ArrayList<>(16);

        try {
            Files.walk(Paths.get(repositoryPath))
                    .map(Path::toFile)
                    .filter(file -> file.isFile() && (file.getName().contains(".java") || file.getName().contains(".kt")))
//                    .filter(file -> file.isFile() && (file.getName().contains(".json")))
//                    .filter(file -> file.isFile() && (file.getName().contains(".kts")))
//                    .filter(file -> file.isFile() && (file.getName().contains(".yml")))
                    .toList()
                    .forEach(file -> {
                        try {
                            Files.readAllLines(Paths.get(file.toURI()))
                                    .stream().filter(stringLine -> stringLine.contains(searchWord))
                                    .findFirst()
                                    .ifPresent(line -> searchWorldsList.add(file.toURI().getPath()));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception exception) {
            System.out.println(exception.fillInStackTrace());
        }

        this.resultList.addAll(searchWorldsList);
    }
}
