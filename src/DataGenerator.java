import java.io.*;
import java.util.*;

public class DataGenerator {

    private static final int NUM_SETS = 70;

    private static final int MIN_SIZE = 100;
    private static final int MAX_SIZE = 10_000;

    private static final int VALUE_BOUND = 1_000_000;

    private static final String OUTPUT_DIR = "data/";

    public static void main(String[] args) throws IOException {
        new File(OUTPUT_DIR).mkdirs();

        Random random = new Random(42);

        for (int i = 0; i < NUM_SETS; i++) {
            int size = MIN_SIZE + random.nextInt(MAX_SIZE - MIN_SIZE + 1);

            String filename = String.format("%sdata_%04d.txt", OUTPUT_DIR, i + 1);

            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println(size);
                for (int j = 0; j < size; j++) {
                    writer.println(random.nextInt(VALUE_BOUND));
                }
            }
            System.out.printf("Сгенерирован файл %s (%d элементов)%n", filename, size);
        }
        System.out.println("Генерация завершена.");
    }
}

