import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class App extends JFrame{
    private JTextArea txtConsole;
    private JPanel mainPanel;
    private JTextField txtBarbers;
    private JTextField txtChairs;
    private JButton btnRun;
    private JLabel Barbers;
    private JLabel Chairs;

    public App (String title){
        super(title);
        DefaultCaret caret = (DefaultCaret) txtConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    SleepingBarber sleepingBarber = new SleepingBarber(txtConsole, txtBarbers, txtChairs);
                    Thread hilo = new Thread(sleepingBarber);
                    hilo.start();

            }
        });
    }


    public static void main (String a[]) throws InterruptedException {
        JFrame frame = new App("Barbero dormilon");
        frame.setVisible(true);

    }
}
