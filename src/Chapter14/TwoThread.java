package Chapter14;

/**
 * Created by sasakin on 21.08.2017.
 * Блокировка объекта потоком*/
public class TwoThread {
    public static void main(String[] args) {
        final StringBuffer s = new StringBuffer();
        new Thread() {
            public void run() {
                int i = 0;
                synchronized (s) {
                    while (i++ < 3) {
                        s.append("A");
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            System.err.println(e);
                        }
                        System.out.println(s);
                    }
                }//конец synchronized
            }
        }.start();
        new Thread() {
            public void run() {
                int j = 0;
                synchronized (s) {
                    while (j++<3) {
                        s.append("B");
                        System.out.println(s);
                    }
                } // конец synchronized
            }
        }.start();
    }

}
