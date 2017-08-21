package Chapter14;

/**
 * Created by sasakin on 21.08.2017.
 */

class Blocked {
    private int i = 1000;

    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public synchronized void doWait() {
        try {
            System.out.println("�� ");
            this.wait(); /* ��������� ������ � ������������ �� ���������� */
            System.out.println("��������� "); // ����� �������� ����������
            Thread.sleep(50);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        for(int j = 0; j < 5; j++) i/=5;
        System.out.println("����� ");
    }
}

public class Runner {
    public static void main(String[] args) {
        Blocked lock = new Blocked();
        new Thread() {
            public void run() {
                lock.doWait();
            }
        }.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {//1
            lock.setI(lock.getI() + 2);
            System.out.println("���������� ");
            lock.notify(); // ������� ����������
        }
        synchronized (lock) { //2
            lock.setI(lock.getI() + 3);
            // ���������� ����� doWait()
            System.out.println("������������. ");
            try {
                lock.wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=" + lock.getI());
    }
}
