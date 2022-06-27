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

                    SleepingBarber sleepingBarber = new SleepingBarber(txtConsole);
                    Thread hilo = new Thread(sleepingBarber);
                    hilo.start();
//                int noOfBarbers=2, customerId=1, noOfCustomers=100, noOfChairs;	//initializing the number of barber and customers
//
//                //Scanner sc = new Scanner(System.in);
//
//                //System.out.println("Enter the number of barbers(M):");
//                noOfBarbers= Integer.parseInt(txtBarbers.getText());
//
//                //System.out.println("Enter the number of waiting room"
//                //        + " chairs(N):");
//                noOfChairs= Integer.parseInt(txtChairs.getText());
//
////    	System.out.println("Enter the number of customers:");
////    	noOfCustomers=sc.nextInt();
//
//                ExecutorService exec = Executors.newFixedThreadPool(6);		//initializing with 12 threads as multiple of cores in the CPU, here 6
//                Bshop shop = new Bshop(noOfBarbers, noOfChairs, txtConsole);				//initializing the barber shop with the number of barbers
//                Random r = new Random();  										//a random number to calculate delays for customer arrivals and haircut
//
//                txtConsole.append("\nBarber shop opened with "
//                        +noOfBarbers+" barber(s)\n");
//
//                long startTime  = System.currentTimeMillis();					//start time of program
//
//                for(int i=1; i<=noOfBarbers;i++) {								//generating the specified number of threads for barber
//
//                    Barber barber = new Barber(shop, i);
//                    Thread thbarber = new Thread(barber);
//                    exec.execute(thbarber);
//                }
//
//                for(int i=0;i<noOfCustomers;i++) {								//customer generator; generating customer threads
//
//                    Customer customer = new Customer(shop);
//                    customer.setInTime(new Date());
//                    Thread thcustomer = new Thread(customer);
//                    customer.setcustomerId(customerId++);
//                    exec.execute(thcustomer);
//
//                    try {
//
//                        double val = r.nextGaussian() * 2000 + 2000;			//'r':object of Random class, nextGaussian() generates a number with mean 2000 and
//                        int millisDelay = Math.abs((int) Math.round(val));		//standard deviation as 2000, thus customers arrive at mean of 2000 milliseconds
//                        Thread.sleep(millisDelay);								//and standard deviation of 2000 milliseconds
//                    }
//                    catch(InterruptedException iex) {
//
//                        iex.printStackTrace();
//                    }
//
//                }
//
//                exec.shutdown();												//shuts down the executor service and frees all the resources
//                try {
//                    exec.awaitTermination(6, SECONDS);								//waits for 12 seconds until all the threads finish their execution
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//                long elapsedTime = System.currentTimeMillis() - startTime;		//to calculate the end time of program
//
//                txtConsole.append("\nBarber shop closed");
//                txtConsole.append("\nTotal time elapsed in seconds"
//                        + " for serving "+noOfCustomers+" customers by "
//                        +noOfBarbers+" barbers with "+noOfChairs+
//                        " chairs in the waiting room is: "
//                        + TimeUnit.MILLISECONDS
//                        .toSeconds(elapsedTime));
//                txtConsole.append("\nTotal customers: "+noOfCustomers+
//                        "\nTotal customers served: "+shop.getTotalHairCuts()
//                        +"\nTotal customers lost: "+shop.getCustomerLost());
//
//                //sc.close();
            }
        });
    }


    public static void main (String a[]) throws InterruptedException {
        JFrame frame = new App("Barbero dormilon");
        frame.setVisible(true);

    }
}
