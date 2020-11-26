package ex5;

public abstract class BeverageDecorator extends Beverage {
    private Beverage beverage;
    public String getDescription() {
        return description;
    }
}
