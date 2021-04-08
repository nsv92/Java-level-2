public class Barrier extends TrainingField {
    private String name;
    private int height;

    public Barrier(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }
}
