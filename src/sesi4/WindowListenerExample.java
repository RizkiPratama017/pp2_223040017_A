
import java.awt.event.*;
import javax.swing.*;  

public class WindowListenerExample {  
    public static void main(String[] args) {  
        JFrame frame = new JFrame("Window Listener Example");  
        
        JLabel label = new JLabel("lakukan operasi pada jendela");
        label.setBounds(50,50,300, 30);

        frame.addWindowListener(new WindowListener() { 

            public void windowOpened(WindowEvent e) {    //ketika dibuka
                label.setText("Window Opened");  
            }  
            public void windowClosing(WindowEvent e) {   //ketika proses penutupan
                label.setText("Window Closing");  //bisa ditambahkan system.exit(0); jika ingin menghentikan program
        
            }  
            public void windowClosed(WindowEvent e) {  //benar benar ditutup
                System.out.println("Window Closed");  
            }  
            public void windowIconified(WindowEvent e) {  //minimaze
                label.setText("Window Minimized");  
            }  
            public void windowDeiconified(WindowEvent e) {  //full setelah minimaze
                label.setText("Window Restored");  
            }  
            public void windowActivated(WindowEvent e) {  //menjadi aktif (berfokus)
                label.setText("Window Activated");  
            }  
            public void windowDeactivated(WindowEvent e) {  //kehilangan fokus
                label.setText("Window Deactivated");  
            }  
        });  
        
        frame.add(label);
        frame.setSize(400, 400);  
        frame.setLayout(null);  
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
}