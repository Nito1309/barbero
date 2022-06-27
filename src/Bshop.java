import javax.swing.*;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Bshop {

    private final AtomicInteger totalHairCuts = new AtomicInteger(0);
    private final AtomicInteger customersLost = new AtomicInteger(0);
    int nchair, noOfBarbers, availableBarbers;
    List<Customer> listCustomer;
    JTextArea txtConsole;
    Random r = new Random();

    public Bshop(int noOfBarbers, int noOfChairs, JTextArea txtConsole){

        this.nchair = noOfChairs;														//number of chairs in the waiting room
        listCustomer = new LinkedList<Customer>();						//list to store the arriving customers
        this.noOfBarbers = noOfBarbers;									//initializing the the total number of barbers
        availableBarbers = noOfBarbers;
        this.txtConsole = txtConsole;
    }

    public AtomicInteger getTotalHairCuts() {

        totalHairCuts.get();
        return totalHairCuts;
    }

    public AtomicInteger getCustomerLost() {

        customersLost.get();
        return customersLost;
    }

    public void cutHair(int barberId)
    {
        Customer customer;
        synchronized (listCustomer) {									//listCustomer is a shared resource so it has been synchronized to avoid any
            //unexpected errors in the list when multiple threads access it
            while(listCustomer.size()==0) {

                txtConsole.append("\nBarber "+barberId+" is waiting "
                        + "for the customer and sleeps in his chair\n");

                try {

                    listCustomer.wait();								//barber sleeps if there are no customers in the shop
                }
                catch(InterruptedException iex) {

                    iex.printStackTrace();
                }
            }

            customer = (Customer)((LinkedList<?>)listCustomer).poll();	//takes the first customer from the head of the list for haircut

            txtConsole.append("Customer "+customer.getCustomerId()+
                    " finds the barber asleep and wakes up "
                    + "the barber "+barberId+"\n");
        }

        int millisDelay=0;

        try {

            availableBarbers--; 										//decreases the count of the available barbers as one of them starts
            //cutting hair of the customer and the customer sleeps
            txtConsole.append("Barber "+barberId+" cutting hair of "+
                    customer.getCustomerId()+ " so customer sleeps");

            double val = r.nextGaussian() * 2000 + 4000;				//time taken to cut the customer's hair has a mean of 4000 milliseconds and
            millisDelay = Math.abs((int) Math.round(val));				//and standard deviation of 2000 milliseconds
            Thread.sleep(millisDelay);

            txtConsole.append("\nCompleted Cutting hair of "+
                    customer.getCustomerId()+" by barber " +
                    barberId +" in "+millisDelay+ " milliseconds.\n");

            totalHairCuts.incrementAndGet();
            //exits through the door
            if(listCustomer.size()>0) {
                txtConsole.append("Barber "+barberId+					//barber finds a sleeping customer in the waiting room, wakes him up and
                        " wakes up a customer in the "					//and then goes to his chair and sleeps until a customer arrives
                        + "waiting room\n");
            }

            availableBarbers++;											//barber is available for haircut for the next customer
        }
        catch(InterruptedException iex) {

            iex.printStackTrace();
        }

    }

    public void add(Customer customer) {

        txtConsole.append("\nCustomer "+customer.getCustomerId()+
                " enters through the entrance door in the the shop at "
                +customer.getInTime()+"\n");

        synchronized (listCustomer) {

            if(listCustomer.size() == nchair) {							//No chairs are available for the customer so the customer leaves the shop

                txtConsole.append("\nNo chair available "
                        + "for customer "+customer.getCustomerId()+
                        " so customer leaves the shop\n");

                customersLost.incrementAndGet();

                return;
            }
            else if (availableBarbers > 0) {							//If barber is available then the customer wakes up the barber and sits in
                //the chair
                ((LinkedList<Customer>)listCustomer).offer(customer);
                listCustomer.notify();
            }
            else {														//If barbers are busy and there are chairs in the waiting room then the customer
                //sits on the chair in the waiting room
                ((LinkedList<Customer>)listCustomer).offer(customer);

                txtConsole.append("All barber(s) are busy so "+
                        customer.getCustomerId()+
                        " takes a chair in the waiting room\n");

                if(listCustomer.size()==1)
                    listCustomer.notify();
            }
        }
    }
}