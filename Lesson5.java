import java.util.Arrays;

public class Lesson5 {
    public static void main(String[] args) throws InterruptedException {
        noThreadMethod();
        threadMethod();
    }

    public static void noThreadMethod() {
        final int size = 10000000;
        final int halfSize = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        float c = (float) (b - a) / 1000;
        System.out.println("noThreadMethod speed: " + c + " sec");
    }

    public static void threadMethod() throws InterruptedException {
        final int size = 10000000;
        final int halfSize = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();

        float[] arrCopy1 = new float[halfSize];
        float[] arrCopy2 = new float[halfSize];
        System.arraycopy(arr, 0, arrCopy1, 0, halfSize);
        System.arraycopy(arr, halfSize, arrCopy2, 0, halfSize);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arrCopy1.length; i++) {
                arrCopy1[i] = (float) (arrCopy1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arrCopy2.length; i++) {
                arrCopy2[i] = (float) (arrCopy2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();
        thread2.start();

        System.arraycopy(arrCopy1, 0, arr, 0, halfSize);
        System.arraycopy(arrCopy2, 0, arr, halfSize, halfSize);

        long b = System.currentTimeMillis();
        float c = (float) (b - a) / 1000;
        System.out.println("threadMethod speed: " + c + " sec");
    }
}
