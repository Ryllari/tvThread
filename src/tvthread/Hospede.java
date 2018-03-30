package tvthread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hospede extends Thread {

    private int id; // identificador
    private int canal; // canal preferido
    private int ta; // tempo assistindo tv
    private int td; // tempo de descanso

    static int assistindo;
    static int esperando;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore tv = new Semaphore(1);
    static Semaphore espere = new Semaphore(0);
    static int tvcanal = 0;
   
    Random escolha = new Random();
    
    // CONSTRUTOR
    public Hospede(int id, int canal, int ta1, int ta2, int td1, int td2){
        this.id = id;
        this.canal = escolha.nextInt(canal) + 1;
        this.ta = escolha.nextInt(ta2-ta1) + ta1;
        this.td = escolha.nextInt(td2-td1) + td1;    
    }
    
    // FUNCOES GET
    public int getid(){
        return this.id;
    }
    
    public int getcanal(){
        return this.canal;
    }
    
    public int getta(){
        return this.ta;
    }
    
    public int gettd(){
        return this.td;
    }
                
    // FAZENDO ALGO QUE CONSOME TEMPO
    public void doing(int time_to){
        long t = System.currentTimeMillis() + (time_to * 1000);
            while(System.currentTimeMillis() < t){}
    }
    
    public void assiste(){
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {}
        assistindo += 1;        
        if (assistindo == 1){
            try {
                tv.acquire();  
                tvcanal = this.getcanal();
            } catch (InterruptedException ex) {}
        }
        System.out.println("Hospede "+this.getid()+" assistindo TV");
        mutex.release();        
    }
    
    public void descansa(){ // Hospede termina de assistir TV
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {}
        System.out.println("Hospede "+this.getid()+" foi descansar");
        assistindo -= 1;
        if (assistindo == 0){
            while(esperando>0){
                esperando -= 1;
                espere.release();
            }
            tvcanal = 0;
            tv.release();
        }
        mutex.release();
    }
    
    @Override
    public void run (){
        System.out.println("Hospede "+this.getid()+" diz: CHEGUEI!!!! CANAL= "+this.getcanal()+" TA="+this.getta()+" TD="+this.gettd());
        while(true){
            if (assistindo == 0 || tvcanal == this.getcanal()){
            this.assiste();            
            this.doing(this.getta());        
            this.descansa();
            this.doing(this.gettd());
            System.out.println("Hospede "+this.getid()+" diz: ACORDEI");
            }
            else{
                esperando += 1;
                try {
                    espere.acquire();
                } catch (InterruptedException ex) {}
            }
        }
    }

}
