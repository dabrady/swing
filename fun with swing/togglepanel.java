import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class togglepanel {

    public static void main(String[] args) {

        final JFrame frame = new JFrame();
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        JButton button1 = new JButton("previous frame!");
        JButton button2 = new JButton("next frame");

        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
		frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.add(panel1);
        frame.add(panel2);

        panel1.add(button2);
        panel1.setVisible(true);

        panel2.add(button1);
        panel2.setVisible(false);

        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                panel1.setVisible(true);
                panel2.setVisible(false);

            }
        });


        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                panel1.setVisible(false);
                panel2.setVisible(true);
            }
        });
    }
}