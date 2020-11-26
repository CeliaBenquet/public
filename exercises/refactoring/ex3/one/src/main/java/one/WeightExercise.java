package one;

public class WeightExercise extends Exercise {
    private int weight;

    public WeightExercise(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public void doExercise() {
        System.out.println("Wow, I totally just did a " + name + " with " + getWeight() + "kg");
    }

}
