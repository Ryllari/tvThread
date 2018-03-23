package tvthread;

public class TvThread {

    public static void main(String[] args) {
        // TODO code application logic here
        int h = 5;
        int n = 44;
        int ta1 = 10;
        int ta2 = 30;
        int td1 = 15;
        int td2 = 40;
        
        Hospede h1 = new Hospede(1, n, ta1, ta2, td1, td2);
        Hospede h2 = new Hospede(2, n, ta1, ta2, td1, td2);
        Hospede h3 = new Hospede(3, n, ta1, ta2, td1, td2); 
        h1.start();
        h2.start();
        h3.start();

    }
    
}
