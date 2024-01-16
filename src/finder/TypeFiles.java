package finder;

public enum TypeFiles {
    JAVA("java"),
    KOTLIN("kt"),
    JSON("json");

    String extension;

    TypeFiles(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
