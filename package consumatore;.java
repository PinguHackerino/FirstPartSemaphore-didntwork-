package produttore_consumatore;

import java.util.List;
import java.util.concurrent.Semaphore;

public class consumatore extends Thread{

private Semaphore semaforo;
private List<Integer> canale;
Public Consumatore(Semaphore semaforo, List <Integer> canale){
    this.semaforo = semaforo;
    this.canale = canale;
}

@Override
public void run(){
    while(!isInterrupted()) {
        try{
            semaforo.acquire();
        }
        catch(InterruptedException e){
            return;
        }
        synchronized(canale){
            System.out.println("Ricevuto: " + canale);
        }
    }
}

}