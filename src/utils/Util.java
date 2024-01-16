package utils;

import finder.TypeFiles;

public class Util {

    private static final String DOT = ".";
    private static final String TEMPLATE = "%s%s";

    public static String getExtensionTemplate(TypeFiles typeFiles) {
        return String.format(TEMPLATE, DOT, typeFiles);
    }
}
