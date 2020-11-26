package two;

import java.util.Scanner;

public class Greeter {

    private static String getFirstName() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your first name:");
        return s.next();
    }

    private static String getLastName() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your last name:");
        return s.next();
    }

    private static String getAge() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your age:");
        return s.next();
    }

    // prints: Nice to meet you Willy
    public static void askForFirstName() {
        String firstName = getFirstName();
        System.out.println("Nice to meet you " + firstName);
    }

    // prints: Nice to meet you Wonka
    public static void askForLastName() {
       String lastName = getLastName();
        System.out.println("Nice to meet you " + lastName);
    }

    // prints: Nice to meet you Willy Wonka
    public static void askForFullName() {
        String firstName = getFirstName();
        String lastName = getLastName();
        System.out.println("Nice to meet you " + firstName + " " + lastName);
    }

    // prints: Nice to meet you Willy Wonka (23 years old)
    public static void askForFullNameAndAge() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String age = getAge();
        System.out.println("Nice to meet you " + firstName + " " + lastName + " (" + age + " years old)");
    }
}
