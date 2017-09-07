package Chapter14;

import java.util.concurrent.Semaphore;

/**
 * Created by sasakin on 21.08.2017.
 */
// Применение семафора
public class Sort {
    public static final int ITEMS_COUNT = 15;
    public static double items[];
    // семафор контролирующий разрешение на доступ к элементам массива
    public static Semaphore sortSemaphore = new Semaphore(0, true);

    public static void main(String[] args) {
        items = new double[ITEMS_COUNT];
        for(int i = 0; i<items.length; ++i)
            items[i] = Math.random();
        new Thread(new ArraySort(items)).start();
        for(int i = 0; i<items.length ; ++i) {
            /**
             * при проверке доступности элемента сортируемого
             * массива происходит блокировка главного потока
             * до освобождения семафора
             */
            sortSemaphore.acquireUninterruptibly();
            System.out.println(items[i]);
        }
    }
}
class ArraySort implements Runnable {
    private double items[];
    public ArraySort(double items[]) {
        this.items = items;
    }
    public void run() {
        for(int i = 0; i < items.length -1 ; ++i) {
            for (int j = i + 1; j < items.length - 1; ++j) {
                if (items[i] < items[j]) {
                    double tmp = items[i];
                    items[i] = items[j];
                    items[j] = tmp;
                }
            }
            // освобождение семафора
            Sort.sortSemaphore.release();
            try {
                Thread.sleep(71);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        Sort.sortSemaphore.release();
    }

}
