package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Inventory extends JFrame {

    //Container components
    private Color background = new Color(41, 75, 110);
    private JFrame frame = this;
    private JPanel cent = new JPanel();
    private JPanel p = new JPanel(new BorderLayout(0, 0));
    public JPanel eastP = new JPanel() {
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
    //West panel components
    private JButton inventoryB = new JButton("Inventory"),
            adminB = new JButton("Admin"),
            searchB = new JButton("Search"),
            configB = new JButton("Settings"),
            supportB = new JButton("Support Info");
    private JLabel gapL = new JLabel();
    //East panel components
    private JTextField[] fields = new JTextField[4];
    private JLabel field1L = new JLabel("Total pawn"),
            field2L = new JLabel("Total buy"),
            field3L = new JLabel("Total sale"),
            field4L = new JLabel("All"); 
    private JLabel gap2 = new JLabel();
    //Center panel components
    private JLabel titleL = new JLabel("Inventory");
    private Border lb = BorderFactory.createEmptyBorder();
    private JRadioButton Tall = new JRadioButton("All"),
            Tpawn = new JRadioButton("Pawn"),
            Tbuy = new JRadioButton("Buy"),
            Tsale = new JRadioButton("Sale");
    private ButtonGroup rbbg = new ButtonGroup();
    private String[][] allArray = {
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"}
    };
    private String[][] pawnArray = {
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"},
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"}};
    private String[][] buyArray = {
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"},
        {"asdasd", "asdasd", "asdasd", "asdasd"}
    };
    private String[][] saleArray = {
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"},
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"},
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"},
        {"asdsdsdsdsdasd", "sdsdsdsasdasd", "sdsds", "assdsdsdsdsdsdasd"}};
    private DefaultTableModel allModel = new DefaultTableModel();
    private DefaultTableModel pawnModel = new DefaultTableModel();
    private DefaultTableModel buyModel = new DefaultTableModel();
    private DefaultTableModel saleModel = new DefaultTableModel();
    private JTable invTable = new JTable();
    private JScrollPane invScroll = new JScrollPane(invTable);

    public Inventory() {
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

    private void SwitchInv(int a) {
        switch (a) {
            case 0:

                invTable.setModel(allModel);
                break;
            case 1:

                invTable.setModel(pawnModel);
                break;
            case 2:

                invTable.setModel(buyModel);
                break;
            case 3:

                invTable.setModel(saleModel);
                break;
        }
    }

    private JScrollPane Inventories() {
        allModel.addColumn("HAaha");
        allModel.addColumn("aha");
        allModel.addColumn("HAhha");
        allModel.addColumn("HAhaha");
        pawnModel.addColumn("HAaha");
        pawnModel.addColumn("HAhha");
        pawnModel.addColumn("HAhaha");
        pawnModel.addColumn("HAhaha");
        buyModel.addColumn("HAaha");
        buyModel.addColumn("HAhaha");
        buyModel.addColumn("HAha");
        buyModel.addColumn("HAhaha");
        saleModel.addColumn("HAhaha");
        saleModel.addColumn("HAhha");
        saleModel.addColumn("HAhaha");
        saleModel.addColumn("Hhaha");

        invTable.setDragEnabled(false);
        invScroll.setEnabled(false);
        invScroll.setBorder(BorderFactory.createLineBorder(Color.black));
        invScroll.setBounds(20, 120, 845, 500);
        return invScroll;
    }

    private JPanel CenterPanel() {
        Dimension rbDim = new Dimension(201, 25);
        JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 7, 6));
        rbPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        rbPanel.setBackground(background);
        rbPanel.setBounds(20, 70, 845, 38);
        Tpawn.setBackground(background);
        Tpawn.setForeground(Color.white);
        Tpawn.setPreferredSize(rbDim);
        Tpawn.setFont(new Font("Arial", Font.BOLD, 17));
        Tsale.setBackground(background);
        Tsale.setForeground(Color.white);
        Tsale.setPreferredSize(rbDim);
        Tsale.setFont(new Font("Arial", Font.BOLD, 17));
        Tbuy.setBackground(background);
        Tbuy.setForeground(Color.white);
        Tbuy.setPreferredSize(rbDim);
        Tbuy.setFont(new Font("Arial", Font.BOLD, 17));
        Tall.setBackground(background);
        Tall.setForeground(Color.white);
        Tall.setPreferredSize(rbDim);
        Tall.setFont(new Font("Arial", Font.BOLD, 17));
        Tall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allModel.setRowCount(0);
                for (int i = 0; i < allArray.length; i++) {
                    allModel.addRow(allArray[i]);
                }
                SwitchInv(0);
            }
        });
        Tpawn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pawnModel.setRowCount(0);
                for (int i = 0; i < pawnArray.length; i++) {
                    pawnModel.addRow(pawnArray[i]);
                }

                SwitchInv(1);
            }
        });
        Tbuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyModel.setRowCount(0);
                for (int i = 0; i < buyArray.length; i++) {
                    buyModel.addRow(buyArray[i]);
                }

                SwitchInv(2);
            }
        });
        Tsale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saleModel.setRowCount(0);
                for (int i = 0; i < saleArray.length; i++) {
                    saleModel.addRow(saleArray[i]);
                }
                SwitchInv(3);
            }
        });


        rbbg.add(Tpawn);
        rbbg.add(Tsale);
        rbbg.add(Tbuy);
        rbbg.add(Tall);

        rbPanel.add(Tall);
        rbPanel.add(Tpawn);
        rbPanel.add(Tbuy);
        rbPanel.add(Tsale);

        cent.setLayout(null);
        centerP.setLayout(null);
        centerP.setBackground(new Color(41, 75, 110));

        centerP.add(titleL);
        titleL.setFont(new Font("Arial", Font.BOLD, 30));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 15, 870, 40);
        titleL.setVerticalAlignment(JLabel.TOP);
        titleL.setHorizontalAlignment(JLabel.LEFT);

        centerP.setBounds(0, 0, width, height);
        centerP.setBackground(new Color(41, 75, 110));

        centerP.add(rbPanel);
        centerP.add(Inventories());
        
        Tall.setSelected(true);
        allModel.setRowCount(0);
        for (int i = 0; i < allArray.length; i++) {
            allModel.addRow(allArray[i]);
        }
        invTable.setModel(allModel);

        return centerP;
    }

    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(130, 25);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width / 8, height));
        eastP.setOpaque(false);
        eastP.setBorder(BorderFactory.createLineBorder(Color.black));

        gap2.setPreferredSize(new Dimension(110, 100));

        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField();
            fields[i].setPreferredSize(fieldDim);
            fields[i].setEditable(false);
        }

        field1L.setForeground(Color.white);
        field2L.setForeground(Color.white);
        field3L.setForeground(Color.white);
        field4L.setForeground(Color.white);

        field1L.setFont(new Font("Arial", Font.BOLD, 13));
        field2L.setFont(new Font("Arial", Font.BOLD, 13));
        field3L.setFont(new Font("Arial", Font.BOLD, 13));
        field4L.setFont(new Font("Arial", Font.BOLD, 13));

        field1L.setPreferredSize(fieldDim);
        field2L.setPreferredSize(fieldDim);
        field3L.setPreferredSize(fieldDim);
        field4L.setPreferredSize(fieldDim);

        field1L.setHorizontalAlignment(JLabel.LEFT);
        field2L.setHorizontalAlignment(JLabel.LEFT);
        field3L.setHorizontalAlignment(JLabel.LEFT);
        field4L.setHorizontalAlignment(JLabel.LEFT);

        eastP.add(gap2);
        eastP.add(field1L);
        eastP.add(fields[0]);
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
        inventoryB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Inventory().setVisible(true);
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
}
