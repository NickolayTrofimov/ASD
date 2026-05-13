import java.io.*;
import java.util.*;

public class DataGenerator {

    private static final int NUM_SETS = 70;

    private static final int MIN_SIZE = 100;

    private static final int MAX_SIZE = 100_000;

    private static final int VALUE_BOUND = 1_000_000;

    private static final String OUTPUT_DIR = "data_rb/";

    public static void main(String[] args) throws IOException {
        new File(OUTPUT_DIR).mkdirs();

        Random random = new Random(42);

        System.out.println("Генерация данных для красно-чёрного дерева\n");

        for (int i = 0; i < NUM_SETS; i++) {
            int size = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE + 1);

            String filename = String.format("%sdata_%04d.txt", OUTPUT_DIR, i + 1);

            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println(size);
                for (int j = 0; j < size; j++) {
                    writer.println(random.nextInt(VALUE_BOUND));
                }
            }

            System.out.printf("[%2d/70] Сгенерирован %s (%d элементов)%n",
                    i + 1, filename, size);
        }

        System.out.println("\nГенерация завершена. Файлы сохранены в папку " + OUTPUT_DIR);
    }
}