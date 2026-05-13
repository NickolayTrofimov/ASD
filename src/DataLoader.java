import java.io.*;
import java.util.*;

public class DataLoader {

    public static List<Integer> loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int size = Integer.parseInt(reader.readLine().trim());

            List<Integer> data = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                data.add(Integer.parseInt(reader.readLine().trim()));
            }

            return data;
        }
    }

    public static File[] getDataFiles(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) ->
                name.startsWith("data_") && name.endsWith(".txt"));

        if (files != null) {
            Arrays.sort(files);
        }

        return files;
    }
}