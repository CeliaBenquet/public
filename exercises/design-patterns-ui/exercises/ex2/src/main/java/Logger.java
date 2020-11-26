package ex2;

public class Logger {

    public void print(){
        System.out.println("Logged");
    }

    private static final Logger instance = new Logger();

    private Logger() {};

    public static Logger getInstance() { return instance; }

}