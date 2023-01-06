package LoginLombard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class newTransaction extends JFrame {

    /**
     * *************************************************************************
     */
    //Container components
    private JFrame frame = this;
    private Color background = new Color(41, 75, 110);
    private JPanel p = new JPanel(new BorderLayout(10, 10));
    public JPanel eastP = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, 900, getBackground().darker().darker());

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1204 / 8, 770);

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
            g2d.fillRect(0, 0, 1204 / 8, 770);

            super.paintComponent(g);
        }
    };
    JPanel centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private int width = 1204, height = 660;
    /**
     * ************************************************************************
     */
    //West panel coeponents
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
    private JTextField[] fields = new JTextField[4];
    private JLabel field1L = new JLabel("Customer _id"),
            field2L = new JLabel("Firstname"),
            field3L = new JLabel("Lastname"),
            field4L = new JLabel("Entered by");
    private JLabel gap2 = new JLabel();
    /**
     * ************************************************************************
     */
    //Center panel components
    private JLabel titleL = new JLabel("New Transaction");
    private Border lb = BorderFactory.createEmptyBorder();
    private JTabbedPane transac = new JTabbedPane();
    private JPanel ticketsP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JRadioButton Tpawn = new JRadioButton("Pawn"),
            Tsale = new JRadioButton("Sale"),
            Tbuy = new JRadioButton("Buy");
    private ButtonGroup rbbg = new ButtonGroup();
    private JPanel addP = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8)),
            addS = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8)),
            ticP = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)),
            addB = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
    JRadioButton addMerch = new JRadioButton("Merchandise"),
            addJew = new JRadioButton("Jewelery");
    private ButtonGroup addBg = new ButtonGroup();
    private JPanel merch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
    private JPanel jew = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
    private JPanel g = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
    private JPanel saleItem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
    private final JLabel merchCategoryL = new JLabel("Item Category"),
            merchTypeL = new JLabel("Item Type"),
            merchMakeL = new JLabel("Make"),
            merchModelL = new JLabel("Model"),
            merchSerialL = new JLabel("Serial Number"),
            merchDetailsL = new JLabel("Details"),
            merchRateL = new JLabel("Loan Rate"),
            merchSdateL = new JLabel("Start End"),
            merchEdateL = new JLabel("End Date"),
            jewRateL = new JLabel("Loan Rate"),
            jewSdateL = new JLabel("Start End"),
            jewEdateL = new JLabel("End Date"),
            merchAmountL = new JLabel("Pawn Amount");
    DefaultComboBoxModel moodel = new DefaultComboBoxModel(),
            moodle = new DefaultComboBoxModel();
    final JComboBox merchCategoryF = new JComboBox(moodel);
    JTextField merchSerialF = new JTextField(),
            merchRateF = new JTextField(),
            merchDetailsF = new JTextField(),
            merchTypeF = new JTextField(),
            merchSdateF = new JTextField(),
            merchEdateF = new JTextField(),
            jewRateF = new JTextField(),
            jewSdateF = new JTextField(),
            jewEdateF = new JTextField(),
            merchAmountF = new JTextField(),
            merchMakeF = new JTextField(),
            merchModelF = new JTextField();
    private JLabel jewTypeL = new JLabel("Type"),
            jewStyleL = new JLabel("Style"),
            jewMetalL = new JLabel("Metal"),
            jewKaratL = new JLabel("Karat"),
            jewSizeL = new JLabel("Size"),
            jewDetailL = new JLabel("Details");
    String[] stylem = {"Clear", "Green", "Blue", "Red"},
            metalm = {"Silver", "Gold", "Bronze"};
    JComboBox jewTypeF = new JComboBox(moodle),
            jewStyleF = new JComboBox(stylem),
            jewMetalF = new JComboBox(metalm);
    JTextField jewKaratF = new JTextField();
    JTextField jewSizeF = new JTextField(),
            jewDetailF = new JTextField();
    private JLabel gAmountL = new JLabel("Amount:");
    private JLabel ticLabel1 = new JLabel("Tickets");
    private DefaultTableModel ticModel1 = new DefaultTableModel();
    private JTable ticTable1 = new JTable(ticModel1);
    private JScrollPane ticScroll1 = new JScrollPane(ticTable1);
    private JButton ticPlus1 = new JButton("+"),
            ticMinus1 = new JButton("-");
    private JLabel ticLabel2 = new JLabel("Ticket Items");
    private DefaultTableModel ticModel2 = new DefaultTableModel();
    private JTable ticTable2 = new JTable(ticModel2);
    private JScrollPane ticScroll2 = new JScrollPane(ticTable2);
    private JButton ticMinus2 = new JButton("+");
    JTextField gSerialF = new JTextField(),
            gDetailsF = new JTextField();
    private JLabel saleSearchL = new JLabel("Item Search"),
            saleCateL = new JLabel("Item Category"),
            saleTypeL = new JLabel("Item Type"),
            saleAvaiL = new JLabel("Available For Sale"),
            saleAmountL = new JLabel("Sale Amount");
    JTextField saleSearchF = new JTextField(),
            saleCateF = new JTextField(),
            saleTypeF = new JTextField(),
            saleAmountF = new JTextField();
    private DefaultTableModel saleModel = new DefaultTableModel();
    private JTable saleTable = new JTable(saleModel);
    private JScrollPane saleAvail = new JScrollPane(saleTable);
    JButton addT = new JButton("Add Ticket");
    public JButton addT2 = new JButton("Add Ticket");
    private Insets ins = new Insets(5, 5, 5, 5);
    /**
     * ********<!--Blah-->*********
     */
    int x = 0;
    boolean bool = false;
    public Timer fonet = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bool) {
                p.setBounds(x, 0, width - (width / 8), height);
                x += 50;
                p.setBackground(background);
                if (x == (50)) {
                    bool = false;
                    fonet.stop();
                }
            } else {
                centerP.setBounds(x, 0, width - (width / 8), height);
                x -= 50;
                if (x == (-1050)) {
                    bool = true;
                }
            }
        }
    });
    JMenuItem mi = new JMenuItem("Back");
    JPopupMenu popup = new JPopupMenu("Main GUI");

    /**
     * ************************************************************************
     */
    public newTransaction() {
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
        p.setBackground(background);
        return p;
    }

    private void SwitchAdd(int a) {
        switch (a) {
            case 0:
                jew.setVisible(false);
                merch.setVisible(true);
                break;
            case 1:
                jew.setVisible(true);
                merch.setVisible(false);
                break;
        }
    }

    private void SwitchLB(int a) {
        switch (a) {
            case 0:
                addP.setVisible(true);
                addS.setVisible(false);
                break;
            case 1:
                addP.setVisible(false);
                addS.setVisible(true);
                break;
            case 2:
                addP.setVisible(false);
                addS.setVisible(false);
                break;
        }
    }

    private JPanel Merchandise() {
        try {
            Dimension dim = new Dimension(180, 25);

            merchCategoryL.setForeground(Color.white);
            merchTypeL.setForeground(Color.white);
            merchMakeL.setForeground(Color.white);
            merchModelL.setForeground(Color.white);
            merchSerialL.setForeground(Color.white);
            merchRateL.setForeground(Color.white);
            merchDetailsL.setForeground(Color.white);
            merchAmountL.setForeground(Color.white);
            merchSdateL.setForeground(Color.white);
            merchEdateL.setForeground(Color.white);
            Date d = new Date();
            merchSdateF.setText("" + (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + d.getDate());

            merchCategoryL.setPreferredSize(dim);
            merchTypeL.setPreferredSize(dim);
            merchMakeL.setPreferredSize(dim);
            merchModelL.setPreferredSize(dim);
            merchSerialL.setPreferredSize(dim);
            merchRateL.setPreferredSize(dim);
            merchSdateL.setPreferredSize(dim);
            merchEdateL.setPreferredSize(dim);
            merchDetailsL.setPreferredSize(new Dimension(350, 25));
            merchAmountL.setPreferredSize(new Dimension(350, 25));

            merchCategoryF.setPreferredSize(dim);
            merchTypeF.setPreferredSize(dim);
            merchMakeF.setPreferredSize(dim);
            merchModelF.setPreferredSize(dim);
            merchSerialF.setPreferredSize(dim);
            merchRateF.setPreferredSize(dim);
            merchSdateF.setPreferredSize(dim);
            merchEdateF.setPreferredSize(dim);
            merchDetailsF.setPreferredSize(new Dimension(370, 80));
            merchAmountF.setPreferredSize(new Dimension(150, 25));

            merchCategoryF.setBackground(Color.white);
            merchTypeF.setBackground(Color.white);
            merchMakeF.setBackground(Color.white);
            merchModelF.setBackground(Color.white);
            merchSerialF.setBackground(Color.white);
            merchRateF.setBackground(Color.white);
            merchSdateF.setBackground(Color.white);
            merchEdateF.setBackground(Color.white);
            merchDetailsF.setBackground(Color.white);
            merchSdateF.setEnabled(false);

            merchCategoryL.setHorizontalAlignment(JLabel.LEFT);
            merchTypeL.setHorizontalAlignment(JLabel.LEFT);
            merchMakeL.setHorizontalAlignment(JLabel.LEFT);
            merchModelL.setHorizontalAlignment(JLabel.LEFT);
            merchSerialL.setHorizontalAlignment(JLabel.LEFT);
            merchRateF.setHorizontalAlignment(JLabel.LEFT);
            merchSdateF.setHorizontalAlignment(JLabel.LEFT);
            merchEdateF.setHorizontalAlignment(JLabel.LEFT);
            merchDetailsL.setHorizontalAlignment(JLabel.LEFT);

            merchCategoryF.setBorder(lb);
            merchTypeF.setBorder(lb);
            merchMakeF.setBorder(lb);
            merchModelF.setBorder(lb);
            merchSerialF.setBorder(lb);
            merchRateF.setBorder(lb);
            merchSdateF.setBorder(lb);
            merchEdateF.setBorder(lb);
            merchDetailsF.setBorder(lb);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop", "root", "");
            PreparedStatement stm = con.prepareCall("Select * from Category_All");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                moodel.addElement(rs.getString("Category_name"));
            }
            PreparedStatement stm2 = con.prepareCall("Select * from Jewelry_Category");
            ResultSet rs2 = stm2.executeQuery();
            while (rs2.next()) {
                //moodle.addElement(rs2.getString("Jewelry_Name"));
            }

            merch.add(merchCategoryL);
            merch.add(merchTypeL);
            merch.add(merchCategoryF);
            merch.add(merchTypeF);
            merch.add(merchMakeL);
            merch.add(merchModelL);
            merch.add(merchMakeF);
            merch.add(merchModelF);
            merch.add(merchSerialL);
            merch.add(merchRateL);
            merch.add(merchSerialF);
            merch.add(merchRateF);
            merch.add(merchDetailsL);
            merch.add(merchDetailsF);
            merch.add(merchSdateL);
            merch.add(merchEdateL);
            merch.add(merchSdateF);
            merch.add(merchEdateF);

            merchDetailsF.setHorizontalAlignment(JTextField.LEFT);

            merch.setPreferredSize(new Dimension(390, 340));
            merch.setBackground(background);
        } catch (SQLException ex) {
            Logger.getLogger(newTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return merch;
    }

    private JPanel Jewel() {
        Dimension dim = new Dimension(180, 25);

        jewTypeL.setPreferredSize(dim);
        jewStyleL.setPreferredSize(dim);
        jewMetalL.setPreferredSize(dim);
        jewKaratL.setPreferredSize(dim);
        jewRateL.setPreferredSize(dim);
        jewEdateL.setPreferredSize(dim);
        jewSdateL.setPreferredSize(dim);
        jewSizeL.setPreferredSize(dim);
        jewDetailL.setPreferredSize(new Dimension(350, 25));

        jewTypeF.setPreferredSize(dim);
        jewStyleF.setPreferredSize(dim);
        jewMetalF.setPreferredSize(dim);
        jewKaratF.setPreferredSize(dim);
        jewSizeF.setPreferredSize(dim);
        jewRateF.setPreferredSize(dim);
        jewEdateF.setPreferredSize(dim);
        jewSdateF.setPreferredSize(dim);
        jewDetailF.setPreferredSize(new Dimension(370, 80));

        jewTypeL.setHorizontalAlignment(JLabel.LEFT);
        jewStyleL.setHorizontalAlignment(JLabel.LEFT);
        jewMetalL.setHorizontalAlignment(JLabel.LEFT);
        jewKaratL.setHorizontalAlignment(JLabel.LEFT);
        jewSizeL.setHorizontalAlignment(JLabel.LEFT);
        jewDetailL.setHorizontalAlignment(JLabel.LEFT);
        jewRateL.setHorizontalAlignment(JLabel.LEFT);
        jewEdateL.setHorizontalAlignment(JLabel.LEFT);
        jewSdateL.setHorizontalAlignment(JLabel.LEFT);

        jewTypeL.setForeground(Color.white);
        jewStyleL.setForeground(Color.white);
        jewMetalL.setForeground(Color.white);
        jewKaratL.setForeground(Color.white);
        jewSizeL.setForeground(Color.white);
        jewDetailL.setForeground(Color.white);
        jewRateL.setForeground(Color.white);
        jewEdateL.setForeground(Color.white);
        jewSdateL.setForeground(Color.white);

        jewTypeF.setBorder(lb);
        jewStyleF.setBorder(lb);
        jewMetalF.setBorder(lb);
        jewKaratF.setBorder(lb);
        jewSizeF.setBorder(lb);
        jewDetailF.setBorder(lb);
        jewRateF.setBorder(lb);
        jewEdateF.setBorder(lb);
        jewSdateF.setBorder(lb);

        jewTypeF.setBackground(Color.white);
        jewStyleF.setBackground(Color.white);
        jewMetalF.setBackground(Color.white);
        jewKaratF.setBackground(Color.white);
        jewSizeF.setBackground(Color.white);
        jewDetailF.setBackground(Color.white);
        jewRateF.setBackground(Color.white);
        jewEdateF.setBackground(Color.white);
        jewSdateF.setBackground(Color.white);
        Date d = new Date();
        jewSdateF.setText("" + (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + d.getDate());
        jewSdateF.setEnabled(false);

        jew.add(jewTypeL);
        jew.add(jewStyleL);
        jew.add(jewTypeF);
        jew.add(jewStyleF);
        jew.add(jewMetalL);
        jew.add(jewKaratL);
        jew.add(jewMetalF);
        jew.add(jewKaratF);
        jew.add(jewSizeL);
        jew.add(jewRateL);
        jew.add(jewSizeF);
        jew.add(jewRateF);
        jew.add(jewDetailL);
        jew.add(jewDetailF);
        jew.add(jewSdateL);
        jew.add(jewEdateL);
        jew.add(jewSdateF);
        jew.add(jewEdateF);

        jew.setPreferredSize(new Dimension(390, 340));
        jew.setBackground(background);
        return jew;
    }

    private JPanel SaleItem() {
        Dimension dim = new Dimension(180, 25);
        JLabel gap5 = new JLabel();

        saleSearchF.setBackground(Color.white);
        saleCateF.setBackground(Color.white);
        saleTypeF.setBackground(Color.white);
        saleAvail.setBackground(Color.white);
        saleAmountF.setBackground(Color.white);

        saleSearchF.setBorder(lb);
        saleCateF.setBorder(lb);
        saleTypeF.setBorder(lb);
        saleAvail.setBorder(lb);
        saleAmountF.setBorder(lb);

        saleSearchL.setForeground(Color.white);
        saleCateL.setForeground(Color.white);
        saleTypeL.setForeground(Color.white);
        saleAvaiL.setForeground(Color.white);
        saleAmountL.setForeground(Color.white);

        saleSearchL.setPreferredSize(new Dimension(350, 25));
        saleSearchF.setPreferredSize(dim);
        saleCateL.setPreferredSize(dim);
        saleTypeL.setPreferredSize(dim);
        saleCateF.setPreferredSize(dim);
        saleTypeF.setPreferredSize(dim);
        saleAvaiL.setPreferredSize(new Dimension(350, 25));
        saleAvail.setPreferredSize(new Dimension(370, 200));
        saleAmountL.setPreferredSize(new Dimension(350, 25));
        saleAmountF.setPreferredSize(new Dimension(180, 25));
        gap5.setPreferredSize(new Dimension(180, 25));

        saleItem.add(saleSearchL);
        saleItem.add(saleSearchF);
        saleItem.add(gap5);
        saleItem.add(saleCateL);
        saleItem.add(saleTypeL);
        saleItem.add(saleCateF);
        saleItem.add(saleTypeF);
        saleItem.add(saleAvaiL);
        saleItem.add(saleAvail);
        saleItem.add(saleAmountL);
        saleItem.add(saleAmountF);

        saleTable.addColumn(new TableColumn());

        saleItem.setPreferredSize(new Dimension(390, 410));
        saleItem.setBackground(background);
        return saleItem;
    }

    private JPanel TicketsPanel() {
        JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        JPanel rbPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        rbPanel.setBackground(background);
        rbPanel2.setBackground(background);
        rbPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        rbPanel2.setBorder(BorderFactory.createLineBorder(Color.white));
        rbPanel2.setPreferredSize(new Dimension(390, 35));
        Dimension rbDim = new Dimension(201, 25);
        Dimension rbDim2 = new Dimension(119, 25);
        TitledBorder tb1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb1.setTitleColor(Color.white);
        tb1.setTitle("Add Pawn Item");
        tb1.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tb2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb2.setTitleColor(Color.white);
        tb2.setTitle("Tickets");
        tb2.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tb3 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb3.setTitleColor(Color.white);
        tb3.setTitle("Add Sale Item");
        tb3.setTitleFont(new Font("Arial", Font.BOLD, 13));
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
        //23232323
        JLabel ticgap1 = new JLabel();

        ticLabel1.setPreferredSize(new Dimension(380, 20));
        ticScroll1.setPreferredSize(new Dimension(380, 130));
        ticMinus1.setPreferredSize(new Dimension(70, 50));
        ticPlus1.setPreferredSize(new Dimension(70, 50));
        ticgap1.setPreferredSize(new Dimension(200, 20));
        ticLabel2.setPreferredSize(new Dimension(380, 20));
        ticScroll2.setPreferredSize(new Dimension(380, 140));
        ticMinus2.setPreferredSize(new Dimension(70, 50));

        ticLabel1.setFont(new Font("Arial", Font.BOLD, 15));
        ticLabel2.setFont(new Font("Arial", Font.BOLD, 15));

        ticMinus1.setFont(new Font("Berlin Sans FB", Font.BOLD, 30));
        ticPlus1.setFont(new Font("Berlin Sans FB", Font.BOLD, 30));
        ticMinus2.setFont(new Font("Berlin Sans FB", Font.BOLD, 30));

        ticMinus1.setBackground(background.darker());
        ticMinus1.setForeground(Color.WHITE);
        ticMinus2.setBackground(background.darker());
        ticMinus2.setForeground(Color.WHITE);
        ticPlus1.setBackground(background.darker());
        ticPlus1.setForeground(Color.WHITE);

        ticLabel1.setForeground(Color.white);
        ticLabel2.setForeground(Color.white);

        ticP.add(ticLabel1);
        ticP.add(ticScroll1);
        ticP.add(ticgap1);
        ticP.add(ticPlus1);
        ticP.add(ticMinus1);
        ticP.add(ticLabel2);
        ticP.add(ticScroll2);
        ticP.add(ticMinus2);

        ticketsP.add(rbPanel);
        ticketsP.add(addP);
        ticketsP.add(addS);
        ticketsP.add(ticP);

        //23434343

        rbbg.add(Tpawn);
        rbbg.add(Tsale);
        rbbg.add(Tbuy);

        rbPanel.add(Tpawn);
        rbPanel.add(Tsale);
        rbPanel.add(Tbuy);
        rbPanel.setPreferredSize(new Dimension(820, 40));

        addP.setBorder(tb1);
        addP.setBackground(background);
        addP.setPreferredSize(new Dimension(415, 490));
        addS.setBorder(tb3);
        addS.setBackground(background);
        addS.setPreferredSize(new Dimension(415, 490));
        ticP.setBorder(tb2);
        ticP.setBackground(background);
        ticP.setPreferredSize(new Dimension(415, 490));

        addMerch.setBackground(background);
        addMerch.setForeground(Color.white);
        addMerch.setPreferredSize(rbDim2);
        addJew.setBackground(background);
        addJew.setForeground(Color.white);
        addJew.setPreferredSize(rbDim2);

        rbPanel2.add(addMerch);
        rbPanel2.add(addJew);

        addBg.add(addMerch);
        addBg.add(addJew);

        addMerch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchAdd(0);
            }
        });
        addJew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchAdd(1);
            }
        });
        addMerch.setFont(new Font("Arial", Font.BOLD, 15));
        addJew.setFont(new Font("Arial", Font.BOLD, 15));

        addMerch.setSelected(true);
        Jewel().setVisible(false);

        addT.setPreferredSize(new Dimension(150, 30));
        addT.setFont(new Font("Arial", Font.BOLD, 15));
        addT2.setPreferredSize(new Dimension(150, 30));
        addT2.setFont(new Font("Arial", Font.BOLD, 15));

        addP.add(rbPanel2);
        addP.add(Merchandise());
        addP.add(Jewel());
        merchAmountL.setHorizontalAlignment(JLabel.LEFT);
        merchAmountF.setHorizontalAlignment(JTextField.CENTER);
        merchAmountF.setBorder(lb);
        gAmountL.setHorizontalAlignment(JLabel.LEFT);
        gAmountL.setPreferredSize(new Dimension(48, 10));
        gAmountL.setForeground(Color.white);
        g.add(gDetailsF);
        addP.add(gAmountL);
        addP.add(merchAmountF);
        addP.add(addT);

        addS.add(SaleItem());
        addS.add(addT2);

        ticketsP.add(rbPanel);
        ticketsP.add(addP);
        ticketsP.add(addS);
        ticketsP.add(ticP);

        Tpawn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchLB(0);
            }
        });
        Tsale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchLB(1);
            }
        });
        Tbuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwitchLB(2);
            }
        });

        Tpawn.setSelected(true);
        addS.setVisible(false);
        addP.setVisible(true);
        ticP.setVisible(true);

        ticketsP.setBackground(new Color(41, 75, 110));

        return ticketsP;
    }

    ;
    private JPanel CenterPanel() {
        centerP.setLayout(null);

        centerP.add(titleL);
        titleL.setFont(new Font("Arial", Font.BOLD, 30));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 10, width - (width / 8), 35);
        titleL.setVerticalAlignment(JLabel.TOP);

        ticketsP.setBounds(10, 50, 860, height - 90);

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
        centerP.setBounds(0, 0, width - (width / 8), height);
        centerP.setBackground(new Color(41, 75, 110));
        centerP.add(TicketsPanel());
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
        eastP.add(field3L);
        eastP.add(fields[2]);
        eastP.add(field4L);
        eastP.add(fields[3]);

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
        new newTransaction().setVisible(true);
    }
}