import java.lang.reflect.Array;

public class Lesson1 {
    public static void main(String[] args) {

        Participant[] participants = new Participant[3];
            participants[0] = new Human("Human1", 100, 20);
            participants[1] = new Cat("Cat1", 50, 10);
            participants[2] = new Robot("Robot1", 200, 50);

        TrainingField[] trainingField = new TrainingField[6];
            trainingField[0] = new Track("Track1", 25);
            trainingField[1] = new Barrier("Barrier1", 15);
            trainingField[2] = new Track("Track2", 75);
            trainingField[3] = new Barrier("Barrier2", 20);
            trainingField[4] = new Track("Track3", 150);
            trainingField[5] = new Barrier("Barrier3", 40);

        for (int i = 0; i < trainingField.length; i++) {
            System.out.println("Stage " + (i + 1) + ":");
            for (int j = 0; j < participants.length; j++) {
                if (participants[j] instanceof Human) {
                    if (((Human) participants[j]).isOutOfGame()) {
                        continue;
                    }
                }
                if (participants[j] instanceof Cat) {
                    if (((Cat) participants[j]).isOutOfGame()) {
                        continue;
                    }
                }
                if (participants[j] instanceof Robot) {
                    if (((Robot) participants[j]).isOutOfGame()) {
                        continue;
                    }
                }
                if (trainingField[i] instanceof Track) {
                    participants[j].run((Track) trainingField[i]);
                } else if (trainingField[i] instanceof Barrier) {
                    participants[j].jump((Barrier) trainingField[i]);
                }
            }
        }
    }
}
