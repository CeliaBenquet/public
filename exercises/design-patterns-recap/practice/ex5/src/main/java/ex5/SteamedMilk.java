package ex5;

public class SteamedMilk extends BeverageDecorator {

    Beverage beverage;

    public SteamedMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Steamed Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }

}
