import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Генерация данных\n");
        DataGenerator.main(args);

        System.out.println("\nТестирование красно-чёрного дерева\n");

        File oldResults = new File("results_rb.csv");
        if (oldResults.exists()) {
            oldResults.delete();
            System.out.println("Старый файл results_rb.csv удалён.\n");
        }

        RBTester tester = new RBTester("data_rb/", "results_rb.csv");
        tester.runAll();

        System.out.println("\nГотово");
        System.out.println("Результаты сохранены в файл: results_rb.csv");
    }
}