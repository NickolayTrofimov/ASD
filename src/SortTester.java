import java.io.*;
import java.util.*;

public class SortTester {

    private final String dataDir;
    private final String outputCsv;

    private static class TestResult {
        int size;
        double heapArrayTime;
        long   heapArrayIters;
        double heapListTime;
        long   heapListIters;
        double treeArrayTime;
        long   treeArrayIters;
        double treeListTime;
        long   treeListIters;
    }

    public SortTester(String dataDir, String outputCsv) {
        this.dataDir = dataDir;
        this.outputCsv = outputCsv;
    }

    public void runAll() throws IOException {
        File[] files = DataLoader.getDataFiles(dataDir);
        if (files == null || files.length == 0) {
            System.err.println("Файлы данных не найдены в " + dataDir);
            return;
        }

        List<TestResult> results = new ArrayList<>();

        System.out.println("Файлов для обработки: " + files.length);
        System.out.println("Начинаем тестирование...\n");

        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getPath();
            System.out.printf("[%d/%d] %s%n", i + 1, files.length, files[i].getName());

            TestResult r = testFile(filename);
            results.add(r);
        }

        writeCsv(results);
        System.out.println("\nРезультаты сохранены в " + outputCsv);
    }

    private TestResult testFile(String filename) throws IOException {
        TestResult r = new TestResult();

        Integer[] array = DataLoader.loadToArray(filename);
        ArrayList<Integer> list = DataLoader.loadToList(filename);
        r.size = array.length;

        Stopwatch sw = new Stopwatch();

        Integer[] arrayCopy1 = array.clone();
        HeapSort<Integer> heapSort = new HeapSort<>();
        sw.start();
        heapSort.sort(arrayCopy1);
        sw.stop();
        r.heapArrayTime = sw.getMicroseconds();
        r.heapArrayIters = heapSort.iterations;

        ArrayList<Integer> listCopy1 = new ArrayList<>(list);
        heapSort = new HeapSort<>();
        sw.start();
        heapSort.sort(listCopy1);
        sw.stop();
        r.heapListTime = sw.getMicroseconds();
        r.heapListIters = heapSort.iterations;

        Integer[] arrayCopy2 = array.clone();
        TreeSort<Integer> treeSort = new TreeSort<>();
        sw.start();
        treeSort.sort(arrayCopy2);
        sw.stop();
        r.treeArrayTime = sw.getMicroseconds();
        r.treeArrayIters = treeSort.iterations;

        ArrayList<Integer> listCopy2 = new ArrayList<>(list);
        treeSort = new TreeSort<>();
        sw.start();
        treeSort.sort(listCopy2);
        sw.stop();
        r.treeListTime = sw.getMicroseconds();
        r.treeListIters = treeSort.iterations;

        System.out.printf("  Size=%d | HeapArr: %6.0f мкс (%d) | HeapList: %6.0f мкс (%d) | " +
                        "TreeArr: %6.0f мкс (%d) | TreeList: %6.0f мкс (%d)%n",
                r.size, r.heapArrayTime, r.heapArrayIters,
                r.heapListTime, r.heapListIters,
                r.treeArrayTime, r.treeArrayIters,
                r.treeListTime, r.treeListIters);

        return r;
    }

    private void writeCsv(List<TestResult> results) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputCsv))) {
            pw.println("Size;HeapArrayTime;HeapArrayIters;HeapListTime;HeapListIters;" +
                    "TreeArrayTime;TreeArrayIters;TreeListTime;TreeListIters");

            for (TestResult r : results) {
                pw.printf("%d;%d;%d;%d;%d;%d;%d;%d;%d%n",
                        r.size,
                        Math.round(r.heapArrayTime),
                        r.heapArrayIters,
                        Math.round(r.heapListTime),
                        r.heapListIters,
                        Math.round(r.treeArrayTime),
                        r.treeArrayIters,
                        Math.round(r.treeListTime),
                        r.treeListIters);
            }
        }
    }
}