package tvthread;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TvThread {
    
    public static void main(String[] args) {
        // TODO code application logic here
        int h = Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo ao Hotel IDK!\nQual é o número de hópedes?"));
        int n = Integer.parseInt(JOptionPane.showInputDialog("Qual é a quantidade de canais?"));
        JOptionPane.showMessageDialog(null ,"Vamos organizar as coisas agora!");
        int ta1 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o tempo mínimo assistindo?"));
        int ta2 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o tempo máximo assistindo?"));
        int td1 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o tempo mínimo descansando?"));
        int td2 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o tempo máximo descansando?"));
        
        for (int i = 0; i< h; i++){
            Hospede hos = new Hospede(i, n, ta1, ta2, td1, td2);
            hos.start();
        }


    }
    
}
