package intellij;

public class GoodClass implements commonBehaviors {
    // This name is really bad
    private String name;

    public GoodClass(String s) {
        name = s;
    }

    // This method will most likely be common to multiple classes
    @Override
    public void addition() {
        int a = 1, b = 1;
        int c = a + b;
        System.out.println(a + "+" + b + "=" + c);
    }

    // This method is very long and hard to understand
    public String modifyName() {
        int n = computeRepetition();
        return computeModifiedName(n);
    }

    private String computeModifiedName(int n) {
        // Do some stuff to the string, based on n
        String s = name.repeat(n);
        s = s.concat("!!");
        s = s.toUpperCase();
        s = s.replaceAll("[AEIOUY]", "<3");
        s = s.concat(name);
        s = s.repeat(n);
        s = s.substring(s.length()/2);

        return s;
    }

    private int computeRepetition() {
        // Compute some value of n
        int n = name.length();
        n *= 51;
        n >>= 3;
        n %= 5;
        n = Math.max(n, 1);
        return n;
    }


    public void multiplesOfA(int a) {
        System.out.printf("=== Multiples of %d ===\n", a);
        for(int i = 1; i <= 12; i++) {
            System.out.printf("%d * %d = ", i, a);
            System.out.print(i * a);
            System.out.print("\n");
        }
    }
}
