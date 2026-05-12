import java.io.*;
import java.util.*;

public class DataLoader {

    public static Integer[] loadToArray(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int size = Integer.parseInt(reader.readLine().trim());
            Integer[] array = new Integer[size];
            for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(reader.readLine().trim());
            }
            return array;
        }
    }

    public static ArrayList<Integer> loadToList(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int size = Integer.parseInt(reader.readLine().trim());
            ArrayList<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(Integer.parseInt(reader.readLine().trim()));
            }
            return list;
        }
    }

    public static File[] getDataFiles(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.startsWith("data_") && name.endsWith(".txt"));
        if (files != null) {
            Arrays.sort(files); // сортируем по имени для порядка
        }
        return files;
    }
}
