package tvthread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class Hospede extends Thread {

    private int id; // identificador
    private int canal; // canal preferido
    private int ta; // tempo assistindo tv
    private int td; // tempo de descanso

    static int assistindo;
    static int esperando;
    static Semaphore mutexpos = new Semaphore(1);
    static Semaphore mutex = new Semaphore(1);
    static Semaphore mutexespere = new Semaphore(1);
    static Semaphore tv = new Semaphore(1);
    static Semaphore espere = new Semaphore(0);
    static int tvcanal = 0;
    static int pos = 0;
    
    private JLabel lstatus = new JLabel("Iniciando");
    
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
            } catch (InterruptedException ex) {}
            
                tvcanal = this.getcanal();
                TvThread.janela.setCanal(tvcanal);
        }
                
        lstatus.setText("Assistindo");
        System.out.println("Hospede "+this.getid()+" assistindo TV");
        mutex.release();        
    }
    
    public void descansa(){ // Hospede termina de assistir TV
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {}
        lstatus.setText("Descansando");
        System.out.println("Hospede "+this.getid()+" foi descansar");
        assistindo -= 1;
        if (assistindo == 0){
            try {
                mutexespere.acquire();
            } catch (InterruptedException ex) {}
            tvcanal = 0;
            TvThread.janela.setCanal(tvcanal);
            tv.release();
            espere.release(esperando);
            esperando = 0;
            mutexespere.release();
        }
        mutex.release();
    }
    
    @Override
    public void run (){
        System.out.println("Hospede "+this.getid()+" diz: CHEGUEI!!!! CANAL= "+this.getcanal()+" TA="+this.getta()+" TD="+this.gettd());
        try {
            mutexpos.acquire();
        } catch (InterruptedException ex) {}
        TvThread.janela.addHospede(pos, this.getid(), this.getcanal(), lstatus, this.getta(), this.gettd());
        pos += 100;
        mutexpos.release();
        while(true){
            if ((assistindo == 0 && tvcanal == 0) || tvcanal == this.getcanal()){
            this.assiste();            
            this.doing(this.getta());        
            this.descansa();
            this.doing(this.gettd());
            System.out.println("Hospede "+this.getid()+" diz: ACORDEI");
            }
            else{
                try {
                    mutexespere.acquire();
                } catch (InterruptedException ex) {}
                esperando += 1;
                lstatus.setText("Esperando");
                mutexespere.release();    
            }
        }
    }

}
