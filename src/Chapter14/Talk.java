package Chapter14;

/**
 * Created by sasakin on 31.07.2017.
 */
public class Talk extends  Thread {
    @Override
    public void run() {
        for(int i = 0; i<8 ; i++) {
            System.out.println("Talking");
            try{
                //остановка на 400 миллисекунд
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }


    public static void main(String args[]) {
        Thread talk = new Talk();
        talk.start();

        Thread sleep = new Sleep();
        sleep.start();
    }
}

class Sleep extends  Thread {
    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            System.out.println("Sleeping");
            try {
                //остановка на 400 миллисекунд
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}