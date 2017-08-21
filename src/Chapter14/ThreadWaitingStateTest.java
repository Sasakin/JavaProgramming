package Chapter14;

/**
 * Created by sasakin on 21.08.2017.
 */
// ��������� BLOCKED, WAITING: ThreadWaitingStateTest
public class ThreadWaitingStateTest extends Thread {
    public void run() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            System.err.println("������ ������1");
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new ThreadWaitingStateTest();
            thread.start();
            synchronized (thread) {
                Thread.sleep(10);
                //BLOCKED
                System.out.println("1: "+thread.getState());
            }
            Thread.sleep(10);
            // WAITING - ����� wait() ������ synchronized
            // ��������� ����� � ��������� ����������
            System.out.println("2: "+ thread.getState());
            thread.interrupt();
        } catch (InterruptedException e) {
            System.err.println("������ ������2");
        }
    }
}
