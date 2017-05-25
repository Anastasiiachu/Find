package Find_program;

public class Flags {
    private boolean recursive;
    private String dir;
    private String fileName;
    public Flags (String[] args) throws IllegalStateException {
        boolean isNextDir = false;
        for (String arg : args) {
            switch (arg) {
                case "-r":
                    if (dir != null || fileName != null || isNextDir)
                        throw new IllegalStateException("Invalid start arguments");
                    recursive = true;
                    break;
                case "-d":
                    if (dir != null || fileName != null || isNextDir)
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
    public boolean getRecursive() {
        return recursive;
    }
    public String getDir() {
        return dir;
    }
    public String getFileName() {
        return fileName;
    }
}
