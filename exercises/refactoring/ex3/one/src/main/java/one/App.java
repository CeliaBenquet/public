/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package one;

public class App {

    public static void main(String[] args) {
        // try out your program here

        Benchpress exercise = new Benchpress(3);
        System.out.println(exercise.getWeight());
        exercise.doExercise();
    }
}
