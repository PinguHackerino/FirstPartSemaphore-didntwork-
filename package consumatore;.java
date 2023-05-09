import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produttore_Consumatore {
    public static void main(String[] args) {

        Semaphore semaforo = new Semaphore(0);
        List<Integer> canale = new ArrayList<Integer>();

        Consumatore consumato = new Consumatore(semaforo, canale);
        consumato.start();

        Produttore produttore = new Produttore(semaforo, canale);
        produttore.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        produttore.interrupt();
        consumato.interrupt();
    }
}

class Consumatore extends Thread {

    private Semaphore semaforo;
    private List<Integer> canale;

    public Consumatore(Semaphore semaforo, List<Integer> canale) {
        this.semaforo = semaforo;
        this.canale = canale;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                semaforo.acquire();
            } catch (InterruptedException e) {
                return;
            }
            synchronized (canale) {
                Integer dato = canale.remove(0);
                System.out.println("Ricevuto: " + dato);
            }
        }
    }
}

class Produttore extends Thread {
    private Semaphore semaforo;
    private List<Integer> canale;

    public Produttore(Semaphore semaforo, List<Integer> canale) {
        this.semaforo = semaforo;
        this.canale = canale;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!isInterrupted()) {
            synchronized (canale) {
                Integer dato = random.nextInt(99);
                System.out.println("Ho prodotto il dato: " + dato);
                canale.add(dato);
            }
            semaforo.release();
        }
    }
}
