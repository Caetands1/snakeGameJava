package ignoreTestCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI implements ActionListener {

    int counts = 0;
    JFrame frame;
    JLabel label;
    JPanel panel;
    JButton button;

    public TestGUI() {

        frame = new JFrame();


        button = new JButton("Click me");

        button.addActionListener(this);
        label = new JLabel("Number of clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(label);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);

    }

    public static  void main(String[] args) {
         new TestGUI();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        counts++;
        label.setText("Number of clicks: " + counts);

    }
}
