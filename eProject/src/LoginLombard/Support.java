package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class Support extends JFrame {

    /**
     * ************************************************************************
     */
    //Container components
    private Color background = new Color(41, 75, 110);
    private JFrame frame = this;
    private Icon img = new ImageIcon("indexer.jpg");
    private JLabel jlab = new JLabel(img);
    private JPanel cent = new JPanel();
    private JPanel p = new JPanel(new BorderLayout(0, 0));
    private JPanel eastP = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, 900, getBackground().darker().darker());

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1204 / 8, height);

            super.paintComponent(g);
        }
    };
    private JPanel westP = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, 900, getBackground().darker().darker());

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1204 / 8, height);

            super.paintComponent(g);
        }
    };
    JPanel centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private int width = 1204, height = 660;
    /**
     * ************************************************************************
     */
    //West panel components
    private JButton inventoryB = new JButton("Inventory"),
            adminB = new JButton("Admin"),
            searchB = new JButton("Search"),
            configB = new JButton("Settings"),
            supportB = new JButton("Support Info");
    private JLabel gapL = new JLabel();
    /**
     * ************************************************************************
     */
    //East panel components
    private JTextField[] fields = new JTextField[2];
    private JLabel field1L = new JLabel("Entered by"),
            field2L = new JLabel("Last Entered");
    private JLabel gap2 = new JLabel(),
            gap3 = new JLabel();
    /**
     * ************************************************************************
     */
    //Center panel components
    private JLabel titleL = new JLabel("Support Info");
    private Border lb = BorderFactory.createEmptyBorder();

    /**
     * ************************************************************************
     */
    public Support() {
        getContentPane().add(p);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }

        p.add(EastPanel(), BorderLayout.EAST);
        p.add(WestPanel(), BorderLayout.WEST);
        p.add(CenterPanel(), BorderLayout.CENTER);

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Lombard");
        setLocationRelativeTo(null);
    }

    private JPanel CenterPanel() {
            cent.setLayout(null);
            centerP.setLayout(null);
            centerP.setBackground(new Color(41, 75, 110));

            centerP.add(titleL);
            titleL.setFont(new Font("Arial", Font.BOLD, 30));
            titleL.setForeground(Color.white);
            titleL.setBounds(15, 15, 870, 40);
            //titleL.setBorder(BorderFactory.createLineBorder(Color.yellow));
            titleL.setVerticalAlignment(JLabel.TOP);
            titleL.setHorizontalAlignment(JLabel.LEFT);

            centerP.setBounds(0, 0, width, height);
            centerP.setBackground(new Color(41, 75, 110));
            cent.add(centerP);

            jlab.setBounds(0, 0, width - (width / 8), height);
            cent.add(jlab);
        return cent;
    }
    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(130, 29);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width / 8, height));
        eastP.setOpaque(false);
        eastP.setBorder(BorderFactory.createLineBorder(Color.black));

        gap2.setPreferredSize(new Dimension(110, 100));
        gap3.setPreferredSize(new Dimension(110, 25));

        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField();
            fields[i].setPreferredSize(fieldDim);
            fields[i].setEditable(false);
        }

        field1L.setForeground(Color.white);
        field2L.setForeground(Color.white);

        field1L.setFont(new Font("Arial", Font.BOLD, 13));
        field2L.setFont(new Font("Arial", Font.BOLD, 13));

        field1L.setPreferredSize(fieldDim);
        field2L.setPreferredSize(fieldDim);

        field1L.setHorizontalAlignment(JLabel.LEFT);
        field2L.setHorizontalAlignment(JLabel.LEFT);

        eastP.add(gap2);
        eastP.add(field1L);
        eastP.add(fields[0]);
        eastP.add(gap3);
        eastP.add(field2L);
        eastP.add(fields[1]);

        return eastP;
    }
    private JPanel WestPanel() {
        Dimension buttonDim = new Dimension(110, 35);
        westP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        westP.setBackground(new Color(20, 75, 120));
        westP.setPreferredSize(new Dimension(width / 8, height));
        westP.setOpaque(false);
        westP.setBorder(BorderFactory.createLineBorder(Color.black));

        inventoryB.setPreferredSize(buttonDim);
        adminB.setPreferredSize(buttonDim);
        searchB.setPreferredSize(buttonDim);
        configB.setPreferredSize(buttonDim);
        gapL.setPreferredSize(new Dimension(120, height / 3 + 40));
        supportB.setPreferredSize(new Dimension(130, 70));
        supportB.setFont(new Font("Arial", Font.BOLD, 15));

        inventoryB.setBackground(background.darker());
        inventoryB.setForeground(Color.WHITE);
        inventoryB.setFont(new Font("Arial", Font.BOLD, 15));
        adminB.setBackground(background.darker());
        adminB.setForeground(Color.WHITE);
        adminB.setFont(new Font("Arial", Font.BOLD, 15));
        searchB.setBackground(background.darker());
        searchB.setForeground(Color.WHITE);
        searchB.setFont(new Font("Arial", Font.BOLD, 15));
        configB.setBackground(background.darker());
        configB.setForeground(Color.WHITE);
        configB.setFont(new Font("Arial", Font.BOLD, 15));
        supportB.setBackground(background.darker());
        supportB.setForeground(Color.WHITE);
        supportB.setFont(new Font("Arial", Font.BOLD, 15));

        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search().setVisible(true);
            }
        });
        configB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Settings().setVisible(true);
            }
        });
        supportB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Support().setVisible(true);
            }
        });
        adminB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Admin().setVisible(true);
            }
        });

        westP.add(inventoryB);
        westP.add(adminB);
        westP.add(searchB);
        westP.add(configB);
        westP.add(gapL);
        westP.add(supportB);

        return westP;
    }
    public static void main(String[] args) {
        new Support().setVisible(true);
    }
}
