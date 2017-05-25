package Find_program;

import java.io.*;
import java.util.*;

public class Find {
    public static void main (String[] args) {
        try {
            Flags flags = new Flags(args);
            if (flags.getFileName() == null) throw new IllegalStateException("Invalid start arguments");
            File directory = (flags.getDir() != null) ? new File(flags.getDir()) : new File(".");
            if (directory.isDirectory() && directory.exists()) {
                find(flags, directory).forEach(System.out::println);
            } else throw new IllegalStateException("Such directory doesn't exist");
        } catch (IllegalStateException e) {
            System.out.println("Invalid flags or args, please check. " + e.getMessage());
        } catch (IOException e) {
            //ошибка,выдаваемая file.getCanonicalPath()
            e.printStackTrace();
        }
    }
    public static List<String> find(Flags flags, File directory) throws IOException {
        List<String> list = new ArrayList<>();
        if (flags.getRecursive()) {
            ArrayDeque<File> fileNames = new ArrayDeque<>();
            fileNames.add(directory);
            while (!fileNames.isEmpty()) {
                File file = fileNames.poll();
                if (file.getName().equals(flags.getFileName()))
                    list.add(file.getCanonicalPath());
                if (file.isDirectory() && file.listFiles() != null)
                    fileNames.addAll(Arrays.asList(file.listFiles()));
            }
        } else {
            if (directory.listFiles() != null) {
                File reqFile = Arrays.stream(directory.listFiles())
                        .filter(F -> F.getName().equals(flags.getFileName())).findAny().orElse(null);
                if (reqFile != null)
                    list.add(reqFile.getCanonicalPath());
            }
        }
        return list;
    }
}
