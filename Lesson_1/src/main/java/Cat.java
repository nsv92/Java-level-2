public class Cat implements Participant {
    private String name;
    private int runLimit;
    private int jumpLimit;
    private boolean isOutOfGame;

    public Cat(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public void run(Track track) {
        if (runLimit >= track.getLength()) {
            System.out.println(name + " run " + track.getName() + " successfully!");
        } else {
            isOutOfGame = true;
            System.out.println(name + " failed to run " + track.getName());
        }
    }

    @Override
    public void jump(Barrier barrier) {
        if (jumpLimit >= barrier.getHeight()) {
            System.out.println(name + " jump " + barrier.getName() + " successfully!");
        } else {
            isOutOfGame = true;
            System.out.println(name + " failed to jump " + barrier.getName());
        }
    }

    public boolean isOutOfGame() {
        return isOutOfGame;
    }
}
