package Chapter14;

/**
 * Created by sasakin on 21.08.2017.
 */
// ��������� NEW, RUNNABLE, TIMED_WAITING, TERMINATED
public class ThreadTimedWaitingStateTast extends Thread {
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.err.println("������ ������");
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new ThreadTimedWaitingStateTast();
            System.out.println("1: " + thread.getState());
            thread.start();
            //RUNNABLE - ����� �������
            System.out.println("2: " + thread.getState());
            Thread.sleep(10);
            // TIMED_WAITING
            // ����� ���� ��������� ����� ��������� ������ ������� ������
            System.out.println("3: " + thread.getState());
            thread.join();
            //TERMINATED - ����� �������� ����������
            System.out.println("4: "+ thread.getState());
        } catch (InterruptedException e) {
            System.err.println("������ ������");
        }
    }
}
