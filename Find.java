package Find_program;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Find {
    private static boolean recursive = false;
    private static String dir = null;
    private static String fileName = null;
    private static void parse (String[] args) throws IllegalStateException {
        boolean isNextDir = false;
        for (String arg: args) {
            switch (arg) {
                case "-r":
                    if (dir != null || fileName != null || isNextDir)
                        throw new IllegalStateException("Invalid start arguments");
                    recursive = true;
                    break;
                case "-d":
                    if (dir != null || fileName!= null || isNextDir)
                        throw new IllegalStateException("Invalid start arguments");
                    isNextDir = true;
                    break;
                default:
                    if (isNextDir) {
                        dir = arg;
                        isNextDir = false;
                    } else {
                        if (fileName != null)
                            throw new IllegalStateException("Invalid start arguments");
                        fileName = arg;
                    }
                    break;
            }
        }
    }
    public static void main (String[] args) {
        try {
            parse(args);
            if (fileName == null) throw new IllegalStateException("Invalid start arguments");
            File directory = (dir != null) ? new File(dir) : new File(".");
            if (directory.isDirectory() && directory.exists()) {
                if (recursive) {
                    ArrayDeque<File> fileNames = new ArrayDeque<>();
                    List<String> reqFiles = new LinkedList<>();
                    fileNames.add(directory);
                    while (!fileNames.isEmpty()) {
                        File file = fileNames.poll();
                        if (file.getName().equals(fileName))
                            reqFiles.add(file.getCanonicalPath());
                        if (file.isDirectory() && file.listFiles() != null)
                            fileNames.addAll(Arrays.asList(file.listFiles()));
                    }
                    reqFiles.forEach(S -> System.out.println(S));
                } else {
                    if (directory.listFiles() != null) {
                        File reqFile = Arrays.stream(directory.listFiles())
                                .filter(F -> F.getName().equals(fileName)).findAny().orElse(null);
                        if (reqFile != null)
                            System.out.println(reqFile.getCanonicalPath());
                    }
                }
            } else throw new IllegalStateException("Such directory doesn't exist");
        } catch (IllegalStateException e) {
            System.out.println("Invalid flags or args, please check. " + e.getMessage());
        } catch (IOException e) {
            //ошибка,выдаваемая file.getCanonicalPath()
            e.printStackTrace();
        }
    }
}
