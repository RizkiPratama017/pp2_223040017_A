import java.awt.event.*;
import javax.swing.*;


public class KeyListenerExample {
    public static void main(String[] args) {
        JFrame frame = new  JFrame("keyListener Example");

        JLabel label = new  JLabel("Tekan tombol pada keyboad.");
        label.setBounds(50,50,300,30);

        //mebuat text field untuk fokus keyboard
        JTextField textField = new  JTextField();
        textField.setBounds(50,100,200,30);

        //menambahkan keyListener ke text field
        textField.addKeyListener(new  KeyListener() {
            //dijalankan ketika tombol ditekan
            public void keyPressed(KeyEvent e){
                label.setText("Key Pressed: "+KeyEvent.getKeyText(e.getKeyCode()));
            }

            //dijalankan ketika tombol dilepaskan
            public void keyReleased(KeyEvent e){
                label.setText("Key Released: "+KeyEvent.getKeyText(e.getKeyCode()));
            }

            //dijalankan ketika tombol ditekan dan dilepaskan (sama dengan mengetik karakter)
            public void keyTyped(KeyEvent e){
                label.setText("Key Typed" + e.getKeyChar());
            }
        });

        //menambahkan komponen ke frame
        frame.add(label);
        frame.add(textField);

        //setting frame
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
