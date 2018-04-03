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
        editInterface();
        editComponents();
    }
    
    public void editComponents(){
        lback.setBounds(0, 0, 600, 600);
        ltv.setBounds(100, 100, 400, 400);
        lcanal.setBounds(260, 230, 200, 200);
        lcanal.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 200));
        lcanal.setForeground(new java.awt.Color(255, 255, 255));
    }
    
    public void editInterface() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
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
