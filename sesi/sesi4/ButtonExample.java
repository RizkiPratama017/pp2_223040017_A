import java.awt.event.*;
import  javax.swing.*;


public class ButtonExample {
    public static void main(String[] args) {
        JFrame frame =new  JFrame("Button Example"); //title
        JButton button = new  JButton("Click Me"); //text

        // menambahkan actionlistener ke jbutton
        button.addActionListener(new  ActionListener(){
            public  void  actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");
            }
        });

        button.setBounds(50,50,150,30);  //x,y,width,height
        frame.add(button);
        frame.setSize(300,200); //width,height
        frame.setLayout(null); //manager
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}