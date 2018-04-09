package Interface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class Interface extends JFrame{
    
    ImageIcon background = new ImageIcon(getClass().getResource("back.jpg"));
    ImageIcon tv = new ImageIcon(getClass().getResource("tv.png"));
    
    JLabel lback = new JLabel(background);
    JLabel ltv = new JLabel(tv);
    JLabel lcanal = new JLabel("0");
    
    public Interface() {
        initComponents();
        editarInterface();
        editarComponents();
        revalidate();
        repaint(); 
    }
    
    public void editarComponents(){
        lback.setBounds(0, 0, 600, 600);
        ltv.setBounds(100, 100, 400, 400);
        lcanal.setBounds(200, 230, 250, 200);
        lcanal.setFont(new Font("DejaVu Sans Condensed", 1, 200));
        lcanal.setForeground(new java.awt.Color(255, 255, 255));
    }
    
    public void addHospede(int pos, int id, int canal, JLabel lstatus, int ta, int td){
        JLabel linfo = new JLabel("Hospede "+id);
        JLabel lcanal = new JLabel(" Canal :"+canal);
        JLabel labelstat = new JLabel("Status: ");
        JLabel lta = new JLabel("Tempo Assistindo: "+ta);
        JLabel ltd = new JLabel("Tempo Descansando: "+td);

    
        linfo.setBounds(610, pos, 100, 20);
        lcanal.setBounds(710, pos, 100, 20);
        add(linfo);
        add(lcanal);
        labelstat.setBounds(610, pos+20, 100, 20);
        lstatus.setBounds(710, pos+20, 100, 20);
        add(labelstat);
        add(lstatus);
        lta.setBounds(610, pos+40, 200, 20);
        ltd.setBounds(610, pos+60, 200, 20);        
        add(lta);
        add(ltd);
        
    }
    
    public void editarInterface() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        add(lcanal);
        add(ltv);
        add(lback);
    }
    
    public void setCanal(int canal){
        lcanal.setText(String.valueOf(canal));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        new Interface();
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
