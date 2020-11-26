package ex3;

public class ProxyFile implements File {

    private static final String ERROR_MESSAGE = "Something sneaky is going on here! Somebody is trying to access a sensitive file";

    private static RealFile file = new RealFile();

    private boolean isSensitive(String fileName) {
        return fileName.contains("sensitive");
    }

    @Override
    public void write(String fileName, String data) {
        if (file == null) {
            file = new RealFile();
        }

        if(isSensitive(fileName)) {
            System.err.println(ERROR_MESSAGE);
            return;
        }

        file.write(fileName, data);
    }

    @Override
    public String read(String fileName) {
        if (file == null) {
            file = new RealFile();
        }

        if(isSensitive(fileName)) {
            System.err.println(ERROR_MESSAGE);
            return "no data";
        }

        return file.read(fileName);
    }

}