package LoginLombard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AddTicket extends JFrame{

    /***************************************************************************/
    //Container components
    private JPanel p = new JPanel(new BorderLayout(0, 0));
    private JPanel eastP = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, 900, getBackground().darker().darker());
            
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1204/8, 900);
            
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
            g2d.fillRect(0, 0, 1204/8, 900);
            
            super.paintComponent(g);
        }
    };
    private JPanel centerP = new JPanel();
    private int width = 1204, height = 770;
    
    /***************************************************************************/
    //West panel components

    private JButton inventoryB = new JButton("Inventory"),
            adminB = new JButton("Admin"),
            policeB = new JButton("Police & Gun"),
            configB = new JButton("Configuration"),
            supportB = new JButton("Support Info");
    
    private JLabel gapL = new JLabel();
    
    /***************************************************************************/
    //East panel components
    private JLabel[] fields = new JLabel[2];
    private JLabel field1L = new JLabel("Enhanced by"), 
            field2L = new JLabel("Date Entered");
    
    private JLabel gap2 = new JLabel(),
            gap3 = new JLabel();
    
    /***************************************************************************/
    //Center panel components
    
    private JLabel titleL = new JLabel("Test Customer's Transaction");
    
    private Dimension cmPanelDim = new Dimension(210, 25);
    private Border lb =  BorderFactory.createEmptyBorder();
    
    private JPanel leftP = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 20));
        private JButton cntB = new JButton("Create New Tickets for Checkout");
        private JTabbedPane tp = new JTabbedPane();
        private JPanel tpAll = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)),
                tpOpen = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)),
                tpRede = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)),
                tpForf = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private JPanel rightP = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 5));
        private JButton voidB = new JButton("Void"),
                printB = new JButton("Print");
        private JLabel numL = new JLabel("Ticket Number"),
                statL = new JLabel("Status"),
                totalL = new JLabel("Total Items"),
                loanL = new JLabel("Loan Amount"),
                charL = new JLabel("Current Charges"),
                termL = new JLabel("Current Term");
        private JTextField numF = new JTextField(),
                statF = new JTextField(),
                totalF = new JTextField(),
                loanF = new JTextField(),
                charF = new JTextField(),
                termF = new JTextField();
        
        private DefaultTableModel dtm = new DefaultTableModel();
        private JTable tableT = new JTable(dtm);
        private JScrollPane scrollP = new JScrollPane(tableT);
        
        private JLabel pawnD = new JLabel("Pawn Date: "),
                forfeitD = new JLabel("Forfeit Date: "),
                pulled = new JLabel("Pulled: "),
                redeem = new JLabel("Redeemed: ");
        
    /***************************************************************************/
    public AddTicket() {
        getContentPane().add(MainPanel());
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Lombard");
    }
    private JPanel MainPanel() {
        p.add(EastPanel(), BorderLayout.EAST);
        p.add(WestPanel(), BorderLayout.WEST);
        p.add(CenterPanel(), BorderLayout.CENTER);
        
        return p;
    }
    private JPanel CenterPanel() {
        Dimension labelDim = new Dimension(115, 25);
        Dimension resultDim = new Dimension(360, 30);
        centerP.setLayout(null);
        centerP.setBackground(new Color(41, 75, 110));
        
        titleL.setFont(new Font("Arial", Font.BOLD, 35));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 10, width-(width/8), 50);
        titleL.setVerticalAlignment(JLabel.TOP);
        
        cntB.setPreferredSize(new Dimension(260, 30));
        
        tp.setPreferredSize(new Dimension(480, 460));
        
        tp.addTab("All", tpAll);
        tp.addTab("Open", tpOpen);
        tp.addTab("Redeemed", tpRede);
        tp.addTab("Forfeit", tpForf);
        
        tpAll.setBackground(new Color(41, 75, 110).brighter());
        tpOpen.setBackground(new Color(41, 75, 110).brighter());
        tpRede.setBackground(new Color(41, 75, 110).brighter());
        tpForf.setBackground(new Color(41, 75, 110).brighter());
        
        leftP.add(cntB);
        leftP.add(tp);
        
        leftP.setBounds(10, 60, 490, 700);
        rightP.setBounds(510, 60, 380, 530);
        
        leftP.setBackground(new Color(41, 75, 110));
        
         TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Ticket Information");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
        rightP.setBorder(tb);
        rightP.setBackground(new Color(41, 75, 110));
        
        voidB.setPreferredSize(new Dimension(174, 30));
        printB.setPreferredSize(new Dimension(174, 30));
        
        numL.setForeground(Color.white);
        statL.setForeground(Color.white);
        totalL.setForeground(Color.white);
        loanL.setForeground(Color.white);
        charL.setForeground(Color.white);
        termL.setForeground(Color.white);
        
        numL.setPreferredSize(labelDim);
        statL.setPreferredSize(labelDim);
        totalL.setPreferredSize(labelDim);
        numF.setPreferredSize(labelDim);
        statF.setPreferredSize(labelDim);
        totalF.setPreferredSize(labelDim);
        loanL.setPreferredSize(labelDim);
        charL.setPreferredSize(labelDim);
        termL.setPreferredSize(labelDim);
        loanF.setPreferredSize(labelDim);
        charF.setPreferredSize(labelDim);
        termF.setPreferredSize(labelDim);
        
        numF.setBorder(BorderFactory.createEmptyBorder());
        statF.setBorder(BorderFactory.createEmptyBorder());
        totalF.setBorder(BorderFactory.createEmptyBorder());
        loanF.setBorder(BorderFactory.createEmptyBorder());
        charF.setBorder(BorderFactory.createEmptyBorder());
        termF.setBorder(BorderFactory.createEmptyBorder());
        
        scrollP.setPreferredSize(new Dimension(350, 200));
        scrollP.setBorder(BorderFactory.createLineBorder(Color.black));
        tableT.setBackground(Color.red);
        tableT.addColumn(new TableColumn());
        tableT.addColumn(new TableColumn());
        tableT.addColumn(new TableColumn());
        tableT.addColumn(new TableColumn());
        
        pawnD.setPreferredSize(resultDim);
        forfeitD.setPreferredSize(resultDim);
        pulled.setPreferredSize(resultDim);
        redeem.setPreferredSize(resultDim);
        
        pawnD.setForeground(Color.white);
        forfeitD.setForeground(Color.white);
        pulled.setForeground(Color.white);
        redeem.setForeground(Color.white);
        
        rightP.add(voidB);
        rightP.add(printB);
        
        rightP.add(numL);
        rightP.add(statL);
        rightP.add(totalL);
        
        rightP.add(numF);
        rightP.add(statF);
        rightP.add(totalF);
        
        rightP.add(loanL);
        rightP.add(charL);
        rightP.add(termL);
        
        rightP.add(loanF);
        rightP.add(charF);
        rightP.add(termF);
        
        rightP.add(scrollP);
        
        rightP.add(pawnD);
        rightP.add(forfeitD);
        rightP.add(pulled);
        rightP.add(redeem);
        
        centerP.add(titleL);
        centerP.add(leftP);
        centerP.add(rightP);
                
        return centerP;
    }
    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(108, 29);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width/8, height));
        eastP.setOpaque(false);
        eastP.setBorder(BorderFactory.createLineBorder(Color.black));
        
        gap2.setPreferredSize(new Dimension(110, 100));
        gap3.setPreferredSize(new Dimension(110, 25));
        
        for (int i=0; i<fields.length; i++) {
            fields[i] = new JLabel();
            fields[i].setPreferredSize(fieldDim);
            fields[i].setIcon(new ImageIcon("textfield.jpg"));
        }
        
        field1L.setForeground(Color.white);
        field2L.setForeground(Color.white);
        
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
        westP.setPreferredSize(new Dimension(width/8, height));
        westP.setOpaque(false);
        westP.setBorder(BorderFactory.createLineBorder(Color.black));

        inventoryB.setPreferredSize(buttonDim);
        adminB.setPreferredSize(buttonDim);
        policeB.setPreferredSize(buttonDim);
        configB.setPreferredSize(buttonDim);
        gapL.setPreferredSize(new Dimension(120, 370));
        supportB.setPreferredSize(new Dimension(130, 70));
        /*
        supportB.setBorder(BorderFactory.createEmptyBorder());
        supportB.setBackground(new Color(81, 109, 137).brighter());
        supportB.setForeground(Color.white);
        */
        //supportB.setBackground(new Color(41, 75, 250));
        supportB.setBorder(new LineBorder(new Color(20, 75, 120).darker() , 15, true));
        supportB.setFont(new Font("Arial", Font.BOLD, 15));
        supportB.setContentAreaFilled(true);
        supportB.setBorderPainted(true);
        
        westP.add(inventoryB);
        westP.add(adminB);
        westP.add(policeB);
        westP.add(configB);
        westP.add(gapL);
        westP.add(supportB);
        
        return westP;
    }
    
    public static void main(String[] args) {
        new AddTicket().setVisible(true);
    }
}
