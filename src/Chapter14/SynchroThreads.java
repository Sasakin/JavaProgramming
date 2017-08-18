package Chapter14;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sasakin on 17.08.2017.
 */

class Synchro {
    private FileWriter fileWriter;

    Synchro(String file) throws IOException {
        fileWriter = new FileWriter(file, true);
    }
    void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void writing(String str, int i) {
        try {
            System.out.println(str + i);
            fileWriter.append(str + i);
            Thread.sleep((long)(Math.random() * 50));
            System.out.println("->" + i + " ");
            fileWriter.append("->" + i + " ");
        } catch (IOException e) {
            System.err.println("ошибка файла");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("ошибка потока");
            e.printStackTrace();
        }

    }
}

class MyThread extends Thread {
    private Synchro s;

    MyThread(String str, Synchro s) {
        super(str);
        this.s = s;
    }
    public void run() {
        for (int i=0; i < 5; i++) {
            s.writing(getName(), i);
        }
    }
}

public class SynchroThreads {
    public static void main(String[] args) {
        try {
            Synchro s = new Synchro("C:\\Users\\sasakin\\Documents\\услуги.txt");

            MyThread t1 = new MyThread("First", s);
            MyThread t2 = new MyThread("Second", s);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            s.close();
        } catch (IOException e) {
            System.err.println("ошибка файла");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("ошибка потока");
            e.printStackTrace();
        }
    }
}
