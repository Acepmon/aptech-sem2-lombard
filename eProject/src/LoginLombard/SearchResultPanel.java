package LoginLombard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SearchResultPanel extends JPanel {

    private String imgStr;
    private String nameStr;
    private String idStr;
    private JLabel img = new JLabel();
    private JLabel name = new JLabel();
    private JLabel id = new JLabel();

    public SearchResultPanel(String imgStr, String nameStr, String idStr) {
        this.imgStr = imgStr;
        this.nameStr = nameStr;
        this.idStr = idStr;
        Components();
    }

    private void Components() {
        setPreferredSize(new Dimension(800, 80));
        setBorder(BorderFactory.createLineBorder(Color.white, 1));
        setLayout(null);
        setBackground(new Color(41, 75, 110));
        img.setIcon(new ImageIcon(imgStr));
        name.setText(nameStr);
        id.setText(idStr);
        img.setBounds(10, 10, 80, 60);
        name.setBounds(110, 10, 100, 25);
        id.setBounds(110, 35, 100, 25);
        name.setForeground(Color.white);
        id.setForeground(Color.white);
        add(img);
        add(id);
        add(name);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    JOptionPane.showMessageDialog(name, "I am too AWESOME!!!");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(211, 215, 226));
                name.setForeground(Color.black);
                id.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(41, 75, 110));
                name.setForeground(Color.white);
                id.setForeground(Color.white);
            }
        });
    }
}
