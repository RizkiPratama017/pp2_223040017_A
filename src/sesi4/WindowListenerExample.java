
import java.awt.event.*;
import javax.swing.*;  

public class WindowListenerExample {  
    public static void main(String[] args) {  
        JFrame f = new JFrame("Window Listener Example");  
        
        JLabel label = new JLabel("lakukan operasi pada jendela");
        label.setBounds(50,50,300, 30);

        f.addWindowListener(new WindowListener() { 

            public void windowOpened(WindowEvent e) {  
                label.setText("Window Opened");  
            }  
            public void windowClosing(WindowEvent e) {  
                label.setText("Window Closing");  
        
            }  
            public void windowClosed(WindowEvent e) {  
                System.out.println("Window Closed");  
            }  
            public void windowIconified(WindowEvent e) {  
                label.setText("Window Minimized");  
            }  
            public void windowDeiconified(WindowEvent e) {  
                label.setText("Window Restored");  
            }  
            public void windowActivated(WindowEvent e) {  
                label.setText("Window Activated");  
            }  
            public void windowDeactivated(WindowEvent e) {  
                label.setText("Window Deactivated");  
            }  
        });  
        
        f.add(label);
        f.setSize(400, 400);  
        f.setLayout(null);  
        f.setVisible(true);  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
}