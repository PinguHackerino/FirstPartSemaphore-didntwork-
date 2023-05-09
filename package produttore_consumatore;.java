import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProduttoreConsumatore {
    public static void main(String[] args) {
		
        Semaphore semaforo = new Semaphore(0);
        List<Integer> canale = new ArrayList<Integer>();
		
        Consumatore consumatore = new Consumatore(semaforo, canale);
        consumatore.start();
		
        Produttore produttore = new Produttore(semaforo, canale);
        produttore.start();
		
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
        produttore.interrupt();
        consumatore.interrupt();
    }
}