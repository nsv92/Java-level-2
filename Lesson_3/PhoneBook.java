import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    HashMap<Integer, String> hashMap = new HashMap<>();

    public void add(int number, String name) {
        hashMap.put(number, name);
    }

    public void get(String name) {
        if (hashMap.containsValue(name)) {
            for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
                if (entry.getValue().equals(name)) {
                    System.out.println("Номер для " + name + ": " + entry.getKey());
                }
            }
        } else System.out.println(name + " не найдено");
    }
}