package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Transaction extends JFrame {

    /**
     * ************************************************************************
     */
    //Container components
    private Color background = new Color(41, 75, 110);
    private JFrame frame = this;
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
    public JPanel centerP = new JPanel();
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
    private JTextField[] fields = new JTextField[7];
    private JLabel field1L = new JLabel("ID"),
            field2L = new JLabel("Firstname"),
            field3L = new JLabel("Lastname"),
            field4L = new JLabel("All"),
            field5L = new JLabel("Pawn"),
            field6L = new JLabel("Forfeit"),
            field7L = new JLabel("Buy");
    private JLabel gap2 = new JLabel();

    /**
     * ************************************************************************
     */
    //Center panel components
    private JLabel titleL = new JLabel("Transaction");
    private Border lb = BorderFactory.createEmptyBorder();

    private JRadioButton rbAll = new JRadioButton("All"),
            rbPawns = new JRadioButton("Pawns"),
            rbSales = new JRadioButton("Sales"),
            rbBuys = new JRadioButton("Buys"),
            rbRep = new JRadioButton("Repairs");
    private ButtonGroup rbbg = new ButtonGroup();
    private JPanel pAll = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)),
            pOpen = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)),
            pDetails = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    private JLabel pOpenL = new JLabel("Open"),
            pAllL = new JLabel("All");
    private DefaultTableModel allTableModel = new DefaultTableModel();
    private JTable allTable = new JTable(allTableModel);
    private JScrollPane ats = new JScrollPane(allTable);

    private JLabel pdTicketL = new JLabel("Ticket"),
            pdStatusL = new JLabel("Status"),
            pdTotalL = new JLabel("Total Items"),
            pdLoanL = new JLabel("Loan Amount"),
            pdCharL = new JLabel("Current Charges"),
            pdTermL = new JLabel("Current Term"),
            pdRecL = new JLabel("Received"),
            pdPullL = new JLabel("Pulled"),
            pdForL = new JLabel("Forfeited"),
            pdRedL = new JLabel("Redeemed");
    JButton repbut = new JButton("Save Information");

    private JTextField pdTicketF = new JTextField(),
            pdStatusF = new JTextField(),
            pdTotalF = new JTextField(),
            pdLoanF = new JTextField(),
            pdCharF = new JTextField(),
            pdTermF = new JTextField(),
            pdRecF = new JTextField(),
            pdPullF = new JTextField(),
            pdForF = new JTextField(),
            pdRedF = new JTextField();

    private DefaultTableModel pdTab1Model = new DefaultTableModel();
    private JTable pdTab1 = new JTable(pdTab1Model);
    private JScrollPane pdscr1 = new JScrollPane(pdTab1);

    private DefaultTableModel pdTab2Model = new DefaultTableModel();
    private JTable pdTab2 = new JTable(pdTab2Model);
    private JScrollPane pdscr2 = new JScrollPane(pdTab2);

    public JButton voidB = new JButton("Void"),
            printB = new JButton("Print"),
            tranB = new JButton("New Transaction");
    JMenuItem mi = new JMenuItem("Back");
    JPopupMenu popup = new JPopupMenu("Main GUI");

    /**
     * ************************************************************************
     */
    public Container getPane() {
        return this.getContentPane();
    }

    public Transaction() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        getContentPane().add(MainPanel());
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Lombard");
        setLocationRelativeTo(null);
    }

    private JPanel MainPanel() {
        p.add(EastPanel(), BorderLayout.EAST);
        p.add(WestPanel(), BorderLayout.WEST);
        p.add(CenterPanel(), BorderLayout.CENTER);

        return p;
    }

    private JPanel CenterPanel() {
        Dimension rbDim = new Dimension(170, 25);
        Dimension pdDim = new Dimension(125, 20);
        
        

        JPanel rbPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rbPanel2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        rbPanel2.setBackground(background);
        rbPanel2.setBounds(10, 60, 880, 35);
        TitledBorder tbDetails = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbDetails.setTitleColor(Color.white);
        tbDetails.setTitle("Details");
        tbDetails.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tbAll = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbAll.setTitleColor(Color.white);
        tbAll.setTitle("All");
        tbAll.setTitleColor(Color.white);
        tbAll.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tbOpen = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbOpen.setTitleColor(Color.white);
        tbOpen.setTitle("      Open");
        tbOpen.setTitleFont(new Font("Arial", Font.BOLD, 13));
        centerP.setLayout(null);
        centerP.setBackground(new Color(41, 75, 110));
        titleL.setFont(new Font("Arial", Font.BOLD, 35));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 10, width - (width / 8), 50);
        titleL.setVerticalAlignment(JLabel.TOP);
        
        rbbg.add(rbAll);
        rbbg.add(rbPawns);
        rbbg.add(rbSales);
        rbbg.add(rbBuys);
        rbbg.add(rbRep);

        rbAll.setPreferredSize(rbDim);
        rbPawns.setPreferredSize(rbDim);
        rbSales.setPreferredSize(rbDim);
        rbBuys.setPreferredSize(rbDim);
        rbRep.setPreferredSize(rbDim);

        rbAll.setBackground(background);
        rbPawns.setBackground(background);
        rbSales.setBackground(background);
        rbBuys.setBackground(background);
        rbRep.setBackground(background);

        rbAll.setForeground(Color.white);
        rbPawns.setForeground(Color.white);
        rbSales.setForeground(Color.white);
        rbBuys.setForeground(Color.white);
        rbRep.setForeground(Color.white);
        pdTicketF.setEnabled(false);
        pdCharF.setEnabled(false);
        pdForF.setEnabled(false);
        pdLoanF.setEnabled(false);
        pdPullF.setEnabled(false);
        pdRecF.setEnabled(false);
        pdRedF.setEnabled(false);
        pdStatusF.setEnabled(false);

        rbAll.setFont(new Font("Arial", Font.BOLD, 17));
        rbPawns.setFont(new Font("Arial", Font.BOLD, 17));
        rbSales.setFont(new Font("Arial", Font.BOLD, 17));
        rbBuys.setFont(new Font("Arial", Font.BOLD, 17));
        rbRep.setFont(new Font("Arial", Font.BOLD, 17));

        rbPanel2.add(rbAll);
        rbPanel2.add(rbPawns);
        rbPanel2.add(rbSales);
        rbPanel2.add(rbBuys);
        rbPanel2.add(rbRep);

        pAll.setBackground(background);
        pAll.setBounds(10, 110, 425, 460);
        pAll.setBorder(tbAll);
        pOpen.setBackground(background);
        pOpen.setBounds(10, 110, 425, 460);
        pOpen.setBorder(tbOpen);
        pDetails.setBackground(background);
        pDetails.setBorder(tbDetails);
        pDetails.setBounds(455, 110, 425, 460);
        pOpen.setVisible(false);
        pOpenL.setBounds(41, 97, 40, 40);
        pOpenL.setFont(new Font("Arial", Font.BOLD, 13));
        pOpenL.setForeground(Color.gray);
        pAllL.setBounds(17, 105, 40, 25);
        pAllL.setForeground(Color.gray);
        pAllL.setFont(new Font("Arial", Font.BOLD, 13));
        pOpenL.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                pAll.setVisible(false);
                pOpen.setVisible(true);
                pOpenL.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pOpenL.setForeground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pOpenL.setForeground(Color.gray);
            }
        });
        pAllL.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                pOpenL.setVisible(true);
                pOpen.setVisible(false);
                pAll.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pAllL.setForeground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pAllL.setForeground(Color.gray);
            }
        });

        allTableModel.addColumn("ID");
        allTableModel.addColumn("Type");
        allTableModel.addColumn("Date");
        allTableModel.addColumn("Description");

        ats.setPreferredSize(new Dimension(400, 400));

        pdTicketL.setPreferredSize(pdDim);
        pdStatusL.setPreferredSize(pdDim);
        pdTotalL.setPreferredSize(pdDim);
        pdLoanL.setPreferredSize(pdDim);
        pdCharL.setPreferredSize(pdDim);
        pdTermL.setPreferredSize(pdDim);

        pdTicketF.setPreferredSize(pdDim);
        pdStatusF.setPreferredSize(pdDim);
        pdTotalF.setPreferredSize(pdDim);
        pdLoanF.setPreferredSize(pdDim);
        pdCharF.setPreferredSize(pdDim);
        pdTermF.setPreferredSize(pdDim);

        pdscr1.setPreferredSize(new Dimension(398, 100));
        pdscr2.setPreferredSize(new Dimension(398, 100));

        pdRecL.setPreferredSize(new Dimension(92, 20));
        pdPullL.setPreferredSize(new Dimension(92, 20));
        pdForL.setPreferredSize(new Dimension(92, 20));
        pdRedL.setPreferredSize(new Dimension(92, 20));

        pdRecF.setPreferredSize(new Dimension(92, 20));
        pdPullF.setPreferredSize(new Dimension(92, 20));
        pdForF.setPreferredSize(new Dimension(92, 20));
        pdRedF.setPreferredSize(new Dimension(92, 20));
        pdRecL.setPreferredSize(new Dimension(92, 20));
        repbut.setPreferredSize(new Dimension(230, 30));
        pdTermF.setEnabled(false);
        pdTotalF.setEnabled(false);

        pdTicketL.setForeground(Color.white);
        pdStatusL.setForeground(Color.white);
        pdTotalL.setForeground(Color.white);
        pdLoanL.setForeground(Color.white);
        pdCharL.setForeground(Color.white);
        pdTermL.setForeground(Color.white);
        pdRecL.setForeground(Color.white);
        pdPullL.setForeground(Color.white);
        pdForL.setForeground(Color.white);
        pdRedL.setForeground(Color.white);
        repbut.setVisible(false);

        pdTicketF.setBorder(lb);
        pdStatusF.setBorder(lb);
        pdTotalF.setBorder(lb);
        pdLoanF.setBorder(lb);
        pdCharF.setBorder(lb);
        pdTermF.setBorder(lb);
        pdRecF.setBorder(lb);
        pdPullF.setBorder(lb);
        pdForF.setBorder(lb);
        pdRedF.setBorder(lb);

        pDetails.add(pdTicketL);
        pDetails.add(pdStatusL);
        pDetails.add(pdTotalL);
        pDetails.add(pdTicketF);
        pDetails.add(pdStatusF);
        pDetails.add(pdTotalF);
        pDetails.add(pdscr1);
        pDetails.add(pdLoanL);
        pDetails.add(pdCharL);
        pDetails.add(pdTermL);
        pDetails.add(pdLoanF);
        pDetails.add(pdCharF);
        pDetails.add(pdTermF);
        pDetails.add(pdscr2);
        pDetails.add(pdRecL);
        pDetails.add(pdPullL);
        pDetails.add(pdForL);
        pDetails.add(pdRedL);
        pDetails.add(pdRecF);
        pDetails.add(pdPullF);
        pDetails.add(pdForF);
        pDetails.add(pdRedF);
        pDetails.add(repbut);

        pdTicketF.setHorizontalAlignment(JTextField.CENTER);
        pdStatusF.setHorizontalAlignment(JTextField.CENTER);
        pdTotalF.setHorizontalAlignment(JTextField.CENTER);
        pdLoanF.setHorizontalAlignment(JTextField.CENTER);
        pdCharF.setHorizontalAlignment(JTextField.CENTER);
        pdTermF.setHorizontalAlignment(JTextField.CENTER);
        pdRecF.setHorizontalAlignment(JTextField.CENTER);
        pdPullF.setHorizontalAlignment(JTextField.CENTER);
        pdForF.setHorizontalAlignment(JTextField.CENTER);
        pdRedF.setHorizontalAlignment(JTextField.CENTER);

        pAll.add(ats);

        voidB.setBounds(380, 580, 150, 40);
        printB.setBounds(540, 580, 150, 40);
        tranB.setBounds(700, 580, 180, 40);

        voidB.setBackground(background.darker());
        voidB.setForeground(Color.WHITE);
        voidB.setFont(new Font("Arial", Font.BOLD, 15));

        printB.setBackground(background.darker());
        printB.setForeground(Color.WHITE);
        printB.setFont(new Font("Arial", Font.BOLD, 15));

        tranB.setBackground(background.darker());
        tranB.setForeground(Color.WHITE);
        tranB.setFont(new Font("Arial", Font.BOLD, 15));

        rbAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                makeItBlah();
            }
        });
        rbBuys.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                makeItBlah();
            }
        });
        rbPawns.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                makeItBlah();
            }
        });
        rbSales.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                makeItBlah();
            }
        });
        rbRep.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                repbut.setVisible(true);
                pdTicketF.setEnabled(true);
                pdTermF.setEnabled(true);
                pdTotalF.setEnabled(true);
                pdCharF.setEnabled(true);
                pdForF.setEnabled(true);
                pdLoanF.setEnabled(true);
                pdPullF.setEnabled(true);
                pdRecF.setEnabled(true);
                pdRedF.setEnabled(true);
                pdStatusF.setEnabled(true);
            }
        });

        popup.add(mi);
        centerP.add(popup);
        centerP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        centerP.add(titleL);
        centerP.add(rbPanel2);
        centerP.add(pOpenL);
        centerP.add(pAll);
        centerP.add(pAllL);
        centerP.add(pOpen);
        centerP.add(pDetails);
        centerP.add(voidB);
        centerP.add(printB);
        centerP.add(tranB);

        return centerP;
    }

    private void makeItBlah() {
        repbut.setVisible(false);
        pdTicketF.setText("");
        pdTermF.setText("");
        pdTotalF.setText("");
        pdCharF.setText("");
        pdForF.setText("");
        pdLoanF.setText("");
        pdPullF.setText("");
        pdRecF.setText("");
        pdRedF.setText("");
        pdStatusF.setText("");
        pdTicketF.setEnabled(false);
        pdTermF.setEnabled(false);
        pdTotalF.setEnabled(false);
        pdCharF.setEnabled(false);
        pdForF.setEnabled(false);
        pdLoanF.setEnabled(false);
        pdPullF.setEnabled(false);
        pdRecF.setEnabled(false);
        pdRedF.setEnabled(false);
        pdStatusF.setEnabled(false);
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
        field5L.setForeground(Color.white);
        field6L.setForeground(Color.white);
        field7L.setForeground(Color.white);

        field1L.setFont(new Font("Arial", Font.BOLD, 13));
        field2L.setFont(new Font("Arial", Font.BOLD, 13));
        field3L.setFont(new Font("Arial", Font.BOLD, 13));
        field4L.setFont(new Font("Arial", Font.BOLD, 13));
        field5L.setFont(new Font("Arial", Font.BOLD, 13));
        field6L.setFont(new Font("Arial", Font.BOLD, 13));
        field7L.setFont(new Font("Arial", Font.BOLD, 13));

        field1L.setPreferredSize(fieldDim);
        field2L.setPreferredSize(fieldDim);
        field3L.setPreferredSize(fieldDim);
        field4L.setPreferredSize(fieldDim);
        field5L.setPreferredSize(fieldDim);
        field6L.setPreferredSize(fieldDim);
        field7L.setPreferredSize(fieldDim);

        field1L.setHorizontalAlignment(JLabel.LEFT);
        field2L.setHorizontalAlignment(JLabel.LEFT);
        field3L.setHorizontalAlignment(JLabel.LEFT);
        field4L.setHorizontalAlignment(JLabel.LEFT);
        field5L.setHorizontalAlignment(JLabel.LEFT);
        field6L.setHorizontalAlignment(JLabel.LEFT);
        field7L.setHorizontalAlignment(JLabel.LEFT);

        eastP.add(gap2);
        eastP.add(field1L);
        eastP.add(fields[0]);
        eastP.add(field2L);
        eastP.add(fields[1]);
        eastP.add(field3L);
        eastP.add(fields[2]);
        eastP.add(field4L);
        eastP.add(fields[3]);
        eastP.add(field5L);
        eastP.add(fields[4]);
        eastP.add(field6L);
        eastP.add(fields[5]);
        eastP.add(field7L);
        eastP.add(fields[6]);
        
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
