import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produttore extends Thread {
    private Semaphore semaforo;
    private List<Integer> canale;
 
    public Produttore(Semaphore semaforo, List<Integer> canale) {
        this.semaforo = semaforo;
        this.canale   = canale;
    }
 
    @Override
    public void run() {
        Random random = new Random();
        while ( !isInterrupted() ) {	
            synchronized (canale) {
                Integer dato = random.nextInt( 99 );
                System.out.println( "Ho prodotto il dato: " + dato );
                canale.add( dato );			
            }
            semaforo.release();
        }
    }
}