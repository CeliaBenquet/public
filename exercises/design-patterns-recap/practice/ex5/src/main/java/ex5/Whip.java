package ex5;

public class Whip extends BeverageDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whipped Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
