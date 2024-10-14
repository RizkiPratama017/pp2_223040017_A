import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
public class MouseListenerExample {
    public static void main(String[] args) {
        //membuat frame
        JFrame frame = new  JFrame("MouseListener Example");
        
        //membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Arahkan dan klik mouse pada area ini.");
        label.setBounds(50,50,300,30);

        //menambahkan MouseListener ke label
        label.addMouseListener(new MouseListener() {
            //dijalankan ketika mouse diklik (klik kiri, kanan, atau tengah)
            public void mouseClicked(MouseEvent e){
                label.setText("Mouse Clicked at:("+ e.getX() + "," + e.getY() + ")");
            }

            //dijalankan ketika mouse ditekan (tanpa dilepaskan)
            public void mousePressed(MouseEvent e){
                label.setText("Mouse Pressed at:("+ e.getX() + "," + e.getY() + ")");
            }

            //dijalankan ketika mouse dilepaskan setelah ditekan
            public void mouseReleased(MouseEvent e){
                label.setText("Mouse Released at:("+ e.getX() + "," + e.getY() + ")");
            }

            //dijalankan ketika mouse masuk kearea komponen
            public void mouseEntered(MouseEvent e){
                label.setText("Mouse Entered the area.");
            }

            //dijalankan ketika mouse keluar dari area komponen
            public void mouseExited(MouseEvent e){
                label.setText("Mouse Exited the area.");
            }
        });

        //menambahkan label ke frame
        frame.add(label);

        //setting frame
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
