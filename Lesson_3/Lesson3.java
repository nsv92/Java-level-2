import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;


public class Lesson3 {

    public static void main(String[] args) {

        System.out.println("Задание №1");
        ArrayList<String> arrList = new ArrayList<>();
        arrList.add("apple");
        arrList.add("orange");
        arrList.add("banana");
        arrList.add("pear");
        arrList.add("kiwi");
        arrList.add("apple");
        arrList.add("watermelon");
        arrList.add("apple");
        arrList.add("kiwi");
        arrList.add("orange");
        arrList.add("banana");
        arrList.add("cherry");
        arrList.add("apple");
        arrList.add("kiwi");
        System.out.println("Исходный массив:");
        System.out.println(arrList);

        LinkedHashSet<String> hashSet = new LinkedHashSet<>(arrList);
        System.out.println("Массив без повторений:");
        System.out.println(hashSet);
        System.out.println("Подсчет повторений:");
        for (String s : hashSet) {
            System.out.println(s + ": " + Collections.frequency(arrList, s));
        }

        System.out.println();
        System.out.println("Задание №2");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(111, "name1");
        phoneBook.add(222, "name2");
        phoneBook.add(333, "name3");
        phoneBook.add(444, "name4");
        phoneBook.add(555, "name2");
        phoneBook.add(666, "name5");
        phoneBook.get("name1");
        phoneBook.get("name2");
        phoneBook.get("name3");
        phoneBook.get("name100");
    }

}