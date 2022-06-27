import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class Barber implements Runnable{

    Bshop shop;
    int barberId;

    public Barber(Bshop shop, int barberId) {

        this.shop = shop;
        this.barberId = barberId;
    }

    public void run() {

        while(true) {

            shop.cutHair(barberId);
        }
    }
}
