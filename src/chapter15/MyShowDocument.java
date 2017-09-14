package chapter15;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sasakin on 07.09.2017.
 */
public class MyShowDocument extends JApplet {
    private URL bsu = null;

    public String getMyURL() {
        return "http://www.bsu.by";
    }
    public void print(Graphics g) {
        int timer = 0;
        g.drawString("Загрузка страницы", 10, 10);
        try {
            for (; timer < 30; timer++) {
                g.drawString(".", 10 + timer*5, 25);
                Thread.sleep(100);
            }
            bsu = new URL(getMyURL());
            getAppletContext().showDocument(bsu, "_blank");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            //некорректно задан протокол, доменное имя или путь к файлу
            e.printStackTrace();
        }
    }
}


