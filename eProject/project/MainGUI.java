
package LombardClasses;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame{

    /***************************************************************************/
    //Container components
    private JPanel p = new JPanel(new BorderLayout(0, 0));
    private JPanel eastP = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, 900, getBackground().darker().darker());
            
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
            
            GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, 900, getBackground().darker().darker());
            
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1204/8, 900);
            
            super.paintComponent(g);
        }
    };
    private JPanel centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private int width = 1204, height = 900;
    
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
    private JTextField[] fields = new JTextField[2];
    private JLabel field1L = new JLabel("Enhanced by"), 
            field2L = new JLabel("Date Entered");
    
    private JLabel gap2 = new JLabel(),
            gap3 = new JLabel();
    
    /***************************************************************************/
    //Center panel components
    
    private JLabel titleL = new JLabel("Add Customer");
    
    private JPanel[] cmPanels = new JPanel[7];
    
    private JButton cancelB = new JButton("Cancel"),
            saveB = new JButton("Save"),
            printB = new JButton("Print"),
            tranB = new JButton("Transaction");
    
    private Dimension cmPanelDim = new Dimension(210, 30);
    private LineBorder lb = (LineBorder) BorderFactory.createLineBorder(Color.white);
        
    //NameFieldPanel nfp
    private JTextField nfPfirst = new JTextField("First Name");
    private JTextField nfPlast = new JTextField("Last Name");
    private JButton nfPadd = new JButton("Add");
    
    //IdenFieldPanel ifP
    private JComboBox ifPlicense = new JComboBox();
    private JTextField ifPidk = new JTextField();
    private JComboBox ifPloc = new JComboBox();
    private JTextField ifPdate = new JTextField();
    private JButton ifPcal = new JButton();
    
    //DobFieldPanel dfP
    private JTextField dfPnum = new JTextField();
    private JTextField dfPdate = new JTextField();
    private JButton dfPcal = new JButton();
    
    //ContactFieldPanel cfP
    private JTextField cfPnum1 = new JTextField();
    private JTextField cfPnum2 = new JTextField();
    private JTextField cfPnum3 = new JTextField();
    private JTextField cfPnum4 = new JTextField();
    private JComboBox cfPcom = new JComboBox();
    private JTextField cfPnum5 = new JTextField();
    
    //AddressFieldPanel afP
    private JTextField afPaddress1 = new JTextField();
    private JTextField afPaddress2 = new JTextField();
    private JTextField afPaddress3 = new JTextField();
    private JComboBox afPaddress4 = new JComboBox();
    private JTextField afPaddress5 = new JTextField();
    
    //DescFieldPanel dfP
    private JComboBox defPdesc1 = new JComboBox();
    private JComboBox defPdesc2 = new JComboBox();
    private JComboBox defPdesc3 = new JComboBox();
    private JComboBox defPdesc4 = new JComboBox();
    private JTextField defPdesc5 = new JTextField();
    private JComboBox defPdesc6 = new JComboBox();
    private JComboBox defPdesc7 = new JComboBox();
    private JTextField defPdesc8 = new JTextField();
    private JTextField defPdesc9 = new JTextField();
    
    //PhotoFieldPanel pfP
    private JPanel pfPphoto = new JPanel();
    private JButton pfPtake = new JButton("Take Photo");
    private JButton pfPscan = new JButton("Scan Photo");
    
    /***************************************************************************/

    public MainGUI() {
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
        
        EastPanel().setOpaque(false);
        WestPanel().setOpaque(false);
        
        NameFieldPanel();
        IdenFieldPanel();
        DobFieldPanel();
        ContactFieldPanel();
        AddressFieldPanel();
        DescFieldPanel();
        PhotoFieldPanel();
        
        return p;
    }
    
    //Name, Identification, Dob, Contact, Address, Description, Photo
    /*
     * cmPanels[0] == NameFieldPanel
     * cmPanels[1] == IdenFieldPanel
     * cmPanels[2] == DobFieldPanel
     * cmPanels[3] == ContactFieldPanel
     * cmPanels[4] == AddressFieldPanel
     * cmPanels[5] == DescFieldPanel
     * cmPanels[6] == PhotoFieldPanel
     */
    
    private void NameFieldPanel() {
        nfPfirst.setPreferredSize(cmPanelDim);
        nfPlast.setPreferredSize(cmPanelDim);
        nfPadd.setPreferredSize(new Dimension(70, 34));
        
        nfPfirst.setBorder(lb);
        nfPlast.setBorder(lb);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Name");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[0].setBorder(tb);
        
        cmPanels[0].add(nfPfirst);
        cmPanels[0].add(nfPlast);
        cmPanels[0].add(nfPadd);
    }
    private void IdenFieldPanel() {
        ifPlicense.setPreferredSize(cmPanelDim);
        ifPidk.setPreferredSize(cmPanelDim);
        ifPloc.setPreferredSize(cmPanelDim);
        ifPdate.setPreferredSize(new Dimension(155, 34));
        ifPcal.setPreferredSize(new Dimension(50, 34));
        
        ifPlicense.setBorder(lb);
        ifPidk.setBorder(lb);
        ifPloc.setBorder(lb);
        ifPdate.setBorder(lb);
        
        ifPlicense.setBackground(Color.white);
        ifPloc.setBackground(Color.white);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Identification");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[1].setBorder(tb);
        
        cmPanels[1].add(ifPlicense);
        cmPanels[1].add(ifPidk);
        cmPanels[1].add(ifPloc);
        cmPanels[1].add(ifPdate);
        cmPanels[1].add(ifPcal);
    }
    private void DobFieldPanel() {
        dfPnum.setPreferredSize(cmPanelDim);
        dfPdate.setPreferredSize(new Dimension(155, 34));
        dfPcal.setPreferredSize(new Dimension(50, 34));
        
        dfPnum.setBorder(lb);
        dfPdate.setBorder(lb);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("DOB & SSN");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[2].setBorder(tb);
        
        cmPanels[2].add(dfPnum);
        cmPanels[2].add(dfPdate);
        cmPanels[2].add(dfPcal);
    }
    private void ContactFieldPanel() {
        cfPnum1.setPreferredSize(cmPanelDim);
        cfPnum2.setPreferredSize(cmPanelDim);
        cfPnum3.setPreferredSize(cmPanelDim);
        cfPnum4.setPreferredSize(cmPanelDim);
        cfPcom.setPreferredSize(cmPanelDim);
        cfPnum5.setPreferredSize(cmPanelDim);
        
        cfPnum1.setBorder(lb);
        cfPnum2.setBorder(lb);
        cfPnum3.setBorder(lb);
        cfPnum4.setBorder(lb);
        cfPcom.setBorder(lb);
        cfPnum5.setBorder(lb);
        
        cfPcom.setBackground(Color.white);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Contact");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[3].setBorder(tb);
        
        cmPanels[3].add(cfPnum1);
        cmPanels[3].add(cfPnum2);
        cmPanels[3].add(cfPnum3);
        cmPanels[3].add(cfPnum4);
        cmPanels[3].add(cfPcom);
        cmPanels[3].add(cfPnum5);
    }
    private void AddressFieldPanel() { 
        afPaddress1.setPreferredSize(cmPanelDim);
        afPaddress2.setPreferredSize(cmPanelDim);
        afPaddress3.setPreferredSize(cmPanelDim);
        afPaddress4.setPreferredSize(cmPanelDim);
        afPaddress5.setPreferredSize(new Dimension(70, 34));
        
        afPaddress1.setBorder(lb);
        afPaddress2.setBorder(lb);
        afPaddress3.setBorder(lb);
        afPaddress4.setBorder(lb);
        afPaddress5.setBorder(lb);
        
        afPaddress4.setBackground(Color.white);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Address");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[4].setBorder(tb);
        
        cmPanels[4].add(afPaddress1);
        cmPanels[4].add(afPaddress2);
        cmPanels[4].add(afPaddress3);
        cmPanels[4].add(afPaddress4);
        cmPanels[4].add(afPaddress5);
    }
    private void DescFieldPanel() {
        defPdesc1.setPreferredSize(cmPanelDim);
        defPdesc2.setPreferredSize(cmPanelDim);
        defPdesc3.setPreferredSize(cmPanelDim);
        defPdesc4.setPreferredSize(cmPanelDim);
        defPdesc5.setPreferredSize(new Dimension(70, 34));
        defPdesc6.setPreferredSize(cmPanelDim);
        defPdesc7.setPreferredSize(cmPanelDim);
        defPdesc8.setPreferredSize(new Dimension(210, 125));
        defPdesc9.setPreferredSize(new Dimension(210, 125));
        
        defPdesc1.setBorder(lb);
        defPdesc2.setBorder(lb);
        defPdesc3.setBorder(lb);
        defPdesc4.setBorder(lb);
        defPdesc5.setBorder(lb);
        defPdesc6.setBorder(lb);
        defPdesc7.setBorder(lb);
        defPdesc8.setBorder(lb);
        defPdesc9.setBorder(lb);
        
        defPdesc1.setBackground(Color.white);
        defPdesc2.setBackground(Color.white);
        defPdesc3.setBackground(Color.white);
        defPdesc4.setBackground(Color.white);
        defPdesc6.setBackground(Color.white);
        defPdesc7.setBackground(Color.white);
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Description");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[5].setBorder(tb);
        
        cmPanels[5].add(defPdesc1);
        cmPanels[5].add(defPdesc2);
        cmPanels[5].add(defPdesc3);
        cmPanels[5].add(defPdesc4);
        cmPanels[5].add(defPdesc5);
        cmPanels[5].add(defPdesc6);
        cmPanels[5].add(defPdesc7);
        cmPanels[5].add(defPdesc8);
        cmPanels[5].add(defPdesc9);
    }
    private void PhotoFieldPanel() {
        cmPanels[6].setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
        
        pfPphoto.setPreferredSize(new Dimension(360, 300));
        pfPtake.setPreferredSize(new Dimension(120,  60));
        pfPscan.setPreferredSize(new Dimension(120, 60));
        
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Photo");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            
        cmPanels[6].setBorder(tb);
        
        cmPanels[6].add(pfPphoto);
        cmPanels[6].add(pfPtake);
        cmPanels[6].add(pfPscan);
    }
    
    private JPanel CenterPanel() {
        
        centerP.setLayout(null);
        centerP.setBackground(new Color(41, 75, 110));
        
        centerP.add(titleL);
        titleL.setFont(new Font("Arial", Font.BOLD, 35));
        titleL.setForeground(Color.white);
        //titleL.setB   order(BorderFactory.createLineBorder(Color.yellow));
        titleL.setBounds(15, 10, width-(width/8), 100);
        titleL.setVerticalAlignment(JLabel.TOP);
        
        for (int i=0; i<cmPanels.length; i++) {
            cmPanels[i] = new JPanel();
            cmPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
            cmPanels[i].setBackground(new Color(41, 75, 110));
            TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
            tb.setTitleColor(Color.white);
            tb.setTitle("Something");
            tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
            cmPanels[i].setBorder(tb);
            centerP.add(cmPanels[i]);
        }
        
        centerP.add(cancelB);
        centerP.add(saveB);
        centerP.add(tranB);
        centerP.add(printB);
        
        cmPanels[0].setBounds(10, 110, 230, 150);
        cmPanels[1].setBounds(250, 110, 230, 200);
        cmPanels[2].setBounds(10, 270, 230, 110);
        cmPanels[3].setBounds(10, 390, 230, 250);
        cmPanels[4].setBounds(10, 650, 230, 210);
        cmPanels[5].setBounds(250, 320, 230, 540);
        cmPanels[6].setBounds(490, 110, 400, 430);
        
        cancelB.setBounds(490, 555, 127, 145);
        printB.setBounds(627, 555, 127, 145);
        saveB.setBounds(762, 555, 127, 145);
        tranB.setBounds(490, 713, 399, 145);
        
        return centerP;
    }
    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(110, 28);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width/8, height));
        
        gap2.setPreferredSize(new Dimension(110, 100));
        gap3.setPreferredSize(new Dimension(110, 25));
        
        for (int i=0; i<fields.length; i++) {
            fields[i] = new JTextField();
            fields[i].setPreferredSize(fieldDim);
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

        inventoryB.setPreferredSize(buttonDim);
        adminB.setPreferredSize(buttonDim);
        policeB.setPreferredSize(buttonDim);
        configB.setPreferredSize(buttonDim);
        gapL.setPreferredSize(new Dimension(120, 500));
        supportB.setPreferredSize(new Dimension(130, 70));
        
        //supportB.setBackground(new Color(41, 75, 250));
        supportB.setFont(new Font("Arial", Font.BOLD, 15));
        
        westP.add(inventoryB);
        westP.add(adminB);
        westP.add(policeB);
        westP.add(configB);
        westP.add(gapL);
        westP.add(supportB);
        
        return westP;
    }
    
    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}
