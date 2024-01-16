
import finder.Finder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        System.out.println("-----------------Поиск начался!--------------------------");
        String path = "C:\\client-onlline-repository";
//        String foundWorld = "saveFile";
        String foundWorld = "sing";

        Finder finder = new Finder(path, foundWorld);
        List<String> result = finder.start();

        result.forEach(searchWorld -> System.out.println("Слово " + foundWorld + " найдено в " + searchWorld));


//        try {
//            Files.walk(Paths.get(path))
//                    .map(Path::toFile)
//                    .filter(file -> file.isFile() && (file.getName().contains(".java") || file.getName().contains(".kt")))
//                    .toList()
//                    .forEach(file -> {
//                        try {
//                            Files.readAllLines(Paths.get(file.toURI()))
//                                    .stream().filter(stringLine -> stringLine.contains(foundWorld))
//                                    .findFirst()
//                                    .ifPresent(line -> System.out.println("Слово + " + foundWorld + " найдено в " + file.toURI()));
//
////                            find.ifPresent(line -> System.out.println("Слово call найдено в " + file.toURI()));
//
//
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//
//
//        } catch (Exception exception) {
//            System.out.println(exception.fillInStackTrace());
//        }

        System.out.println("-----------------Поиск окончен!--------------------------");
    }
}