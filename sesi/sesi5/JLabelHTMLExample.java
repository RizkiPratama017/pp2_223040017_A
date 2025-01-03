import javax.swing.*;

public class JLabelHTMLExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JLabel HTML Example");

        JLabel label = new JLabel("<html><b>Bold Text</b>, <i>Italic Text</i>, and <u>Underlined Text</u></html>");

        frame.add(label);

        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
