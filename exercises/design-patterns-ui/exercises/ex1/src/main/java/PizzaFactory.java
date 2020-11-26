package ex1;

public class PizzaFactory {
    public static Pizza get(String name) {
        if (name == "Pepperoni Pizza") {
            return new PepperoniPizza();
        }

        if (name == "Veggie Pizza") {
            return new VeggiePizza();
        }

        if (name == "Clam Pizza") {
            return new ClamPizza();
        }

        if (name == "Cheese Pizza") {
            return new CheesePizza();
        }

        throw new IllegalArgumentException("We don't make the following pizza. Check the name.");
    }
}
