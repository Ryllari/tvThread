package tvthread;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class Hospede extends Thread {

    private int id; // identificador
    private int canal; // canal preferido
    private int ta; // tempo assistindo tv
    private int td; // tempo de descanso

   
    Random escolha = new Random();
    
    // CONSTRUTOR
    public Hospede(int id, int canal, int ta1, int ta2, int td1, int td2){
        this.id = id;
        this.canal = escolha.nextInt(canal);
        this.ta = (escolha.nextInt(ta2) + ta1) * 1000;
        this.td = (escolha.nextInt(td2) + td1) * 1000;    
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
        long t = System.currentTimeMillis() + time_to;
            while(System.currentTimeMillis() < t){}
    }
    
    @Override
    public void run (){
        System.out.println("Hospede "+this.getid()+" diz: CHEGUEI!!!! CANAL="+this.getcanal()+"TA="+this.getta()+" TD="+this.gettd());
        while(true){
            System.out.println("Hospede "+this.getid()+" diz: VOU ASSISTIR");
            this.doing(this.getta());
            System.out.println("Hospede "+this.getid()+" diz: VOU DORMIR");
            this.doing(this.gettd());
            System.out.println("Hospede "+this.getid()+" diz: ACORDEI");
        }
    }

}
