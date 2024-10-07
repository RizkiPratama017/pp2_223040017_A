
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBorderLayout extends JFrame{
    public  HelloBorderLayout(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelpertanyaan = new  JLabel("Apakah ibukota Indonesia");
        JLabel labelhasil = new JLabel("Jawab Pertanyaan di atas");

        JButton buttonA = new  JButton("Jakarta");
        JButton buttonB = new  JButton("Bandung");
        JButton buttonC = new  JButton("Surabaya");

        buttonA.addActionListener(new  ActionListener (){
            public void actionPerformed(ActionEvent e){
                labelhasil.setText("jawaban anda benar");
            }
        });


        buttonB.addActionListener(new  ActionListener (){
            public void actionPerformed(ActionEvent e){
                labelhasil.setText("jawaban anda Salah");
            }
        });
        buttonC.addActionListener(new  ActionListener (){
            public void actionPerformed(ActionEvent e){
                labelhasil.setText("jawaban anda Salah");
            }
        });

        this.add(labelpertanyaan, BorderLayout.NORTH);
        this.add(labelhasil, BorderLayout.SOUTH);
        this.add(buttonA, BorderLayout.WEST);
        this.add(buttonB, BorderLayout.CENTER);
        this.add(buttonC, BorderLayout.EAST);

        this.setSize(400, 200);

    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void  run(){
            HelloBorderLayout h = new HelloBorderLayout();
            h.setVisible(true);
        }
        
    });

}
}