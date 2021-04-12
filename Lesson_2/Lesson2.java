public class Lesson2 {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] stringsCorrect = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] stringsLengthProblem = {
                {"1", "2", "3"},
                {"5", "6", "7"},
                {"9", "10", "11"},
                {"13", "14", "15"}
        };

        String[][] stringsDataProblem = {
                {"1", "2", "3", "4"},
                {"5", "6", "A", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        arrToConsole(stringsCorrect);
        try {
            someMethod(stringsCorrect);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        System.out.println();
        arrToConsole(stringsLengthProblem);
        try {
            someMethod(stringsLengthProblem);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        System.out.println();
        arrToConsole(stringsDataProblem);
        try {
            someMethod(stringsDataProblem);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static void someMethod(String[][] strings) throws MyArraySizeException, MyArrayDataException {
        if (strings.length != 4 || strings[0].length != 4) {
            throw new MyArraySizeException("Некорректная длинна массива!");
        }
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    int a = Integer.parseInt(strings[i][j]);
                    sum += a;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке: [" + i + "][" + j + "]");
                }
            }
        }
        System.out.println("Сумма элементов массива: " + sum);
    }

    /*Метод выводит массив в консоль для наглядности*/
    public static void arrToConsole(String[][] strings) {
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                System.out.print(strings[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
