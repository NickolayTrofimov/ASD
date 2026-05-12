import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Генерация данных");
        DataGenerator.main(args);

        System.out.println("\nТесты");
        SortTester tester = new SortTester("data/", "results.csv");
        tester.runAll();

        System.out.println("\nГотово! Результаты в файле results.csv");
    }
}