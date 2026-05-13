import java.io.*;
import java.util.*;

public class RBTester {

    private final String dataDir;
    private final String outputCsv;

    private static final int SEARCH_COUNT = 1000;
    private static final int DELETE_COUNT = 1000;

    public RBTester(String dataDir, String outputCsv) {
        this.dataDir = dataDir;
        this.outputCsv = outputCsv;
    }

    public void runAll() throws IOException {
        File[] files = DataLoader.getDataFiles(dataDir);

        if (files == null || files.length == 0) {
            System.err.println("Файлы данных не найдены в папке: " + dataDir);
            return;
        }

        System.out.println("Найдено файлов: " + files.length);
        System.out.println("Начинаем тестирование...\n");

        System.out.printf("%-6s | %-10s | %-12s | %-12s | %-12s | %-12s | %-12s%n",
                "№", "Size", "Insert(мс)", "Search(мс)", "Delete(мс)", "BFS(мс)", "DFS(мс)");
        System.out.println("-".repeat(85));

        List<TestResult> results = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getPath();
            int fileNum = i + 1;

            TestResult result = testFile(filename);
            results.add(result);

            System.out.printf("%4d  | %10d | %12.3f | %12.3f | %12.3f | %12.3f | %12.3f%n",
                    fileNum, result.size,
                    result.insertTimeMs,
                    result.searchTimeMs,
                    result.deleteTimeMs,
                    result.bfsTimeMs,
                    result.dfsTimeMs);
        }

        writeCsv(results);
        System.out.println("\nРезультаты сохранены в файл: " + outputCsv);
    }

    private TestResult testFile(String filename) throws IOException {

        List<Integer> data = DataLoader.loadFromFile(filename);

        TestResult result = new TestResult();
        result.size = data.size();

        Stopwatch sw = new Stopwatch();
        Random rand = new Random(123);

        RBTree tree = new RBTree();
        sw.start();
        for (int value : data) {
            tree.insert(value);
        }
        sw.stop();
        result.insertTimeMs = sw.getMilliseconds();

        sw.start();
        for (int i = 0; i < SEARCH_COUNT; i++) {
            int randomIndex = rand.nextInt(data.size());
            tree.search(data.get(randomIndex));
        }
        sw.stop();
        result.searchTimeMs = sw.getMilliseconds();

        List<Integer> toDelete = new ArrayList<>();
        for (int i = 0; i < DELETE_COUNT; i++) {
            int randomIndex = rand.nextInt(data.size());
            toDelete.add(data.get(randomIndex));
        }

        sw.start();
        for (int value : toDelete) {
            tree.delete(value);
        }
        sw.stop();
        result.deleteTimeMs = sw.getMilliseconds();

        sw.start();
        List<Integer> bfsResult = tree.bfs();
        sw.stop();
        result.bfsTimeMs = sw.getMilliseconds();

        sw.start();
        List<Integer> dfsResult = tree.dfs();
        sw.stop();
        result.dfsTimeMs = sw.getMilliseconds();

        if (data.size() <= 20) {
            System.out.println("\nСтруктура дерева (размер " + data.size() + "):");
            tree.printTree();
            System.out.println("BFS: " + bfsResult);
            System.out.println("DFS (in-order): " + dfsResult);
            System.out.println();
        }

        return result;
    }

    private void writeCsv(List<TestResult> results) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputCsv))) {

            pw.println("Size;InsertTimeUs;SearchTimeUs;DeleteTimeUs;BfsTimeUs;DfsTimeUs");

            for (TestResult r : results) {
                pw.printf("%d;%d;%d;%d;%d;%d%n",
                        r.size,
                        Math.round(r.insertTimeMs * 1000),
                        Math.round(r.searchTimeMs * 1000),
                        Math.round(r.deleteTimeMs * 1000),
                        Math.round(r.bfsTimeMs * 1000),
                        Math.round(r.dfsTimeMs * 1000));
            }
        }
    }

    private static class TestResult {
        int size;
        double insertTimeMs;
        double searchTimeMs;
        double deleteTimeMs;
        double bfsTimeMs;
        double dfsTimeMs;
    }
}