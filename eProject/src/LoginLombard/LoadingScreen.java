package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoadingScreen extends JWindow {

    private int width = 1204, height = 660;
    private JPanel panel = new JPanel();
    private JLabel back = new JLabel(new ImageIcon("indexer.jpg"));
    private JProgressBar progress = new JProgressBar(0, 100);
    private Timer timer;
    private int tm = 0;
    private JWindow window = this;
    private Color background = new Color(41, 75, 110);
    private JLabel title = new JLabel("PAWN SHOP");

    public LoadingScreen() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            getContentPane().add(ContentPane());
            setSize(width, height);
            setLocationRelativeTo(null);
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }

    private Timer Time() {
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tm != progress.getMaximum()) {
                    progress.setValue(tm);
                } else {
                    window.dispose();
                    Time().stop();
                    new Login().setVisible(true);
                }
                tm++;
            }
        });
        return timer;
    }

    private JPanel ContentPane() {
        back.setBounds(0, 0, width, height);
        title.setBounds(50, 50, width-200, height-500);
        title.setFont(new Font("Arial", Font.BOLD, 55));
        title.setHorizontalAlignment(JLabel.RIGHT);
        progress.setBounds(0, height - 80, width-1, 40);
        progress.setStringPainted(true);
        progress.setString("Loading...");
        progress.setFont(new Font("Arial", Font.PLAIN, 25));
        progress.setBackground(new Color(200, 200, 200, 225));
        progress.setForeground(background);
        progress.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.add(title);
        panel.add(progress);
        panel.add(back);
        back.setBorder(BorderFactory.createLineBorder(background, 2));
        Time().start();
        return panel;
    }

    public static void main(String[] args) {
        new LoadingScreen().setVisible(true);
        new NewSocket();
    }
}
