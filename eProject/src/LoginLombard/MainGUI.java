package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame {

    /**
     * ************************************************************************
     */
    //Container components
    private JFrame frame = this;
    private Color background = new Color(41, 75, 110);
    private JPanel p = new JPanel(new BorderLayout(0, 0));
    JPanel eastP = new JPanel() {
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
    public JPanel centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
    JTextField[] fields = new JTextField[4];
    private final JLabel image = new JLabel(new ImageIcon("images.jpeg")),
            field1L = new JLabel("Firstname"),
            field2L = new JLabel("Lastname"),
            field3L = new JLabel("username"),
            field4L = new JLabel("Password");
    JLabel gap1 = new JLabel();
    /**
     * ************************************************************************
     */
    //Center panel components
    private JLabel titleL = new JLabel("Add Customer");
    private JPanel[] cmPanels = new JPanel[7];
    public JButton cancelB = new JButton("Reset"),
            saveB = new JButton("Save"),
            tranB = new JButton("Transaction");
    private Dimension cmPanelDim = new Dimension(210, 25);
    private Border lb = BorderFactory.createEmptyBorder();
    //NameFieldPanel nfp
    public JTextField nfPfirst = new JTextField("First Name");
    public JTextField nfPlast = new JTextField("Last Name");
    public JTextField nfPmiddle = new JTextField("Register No");
    //ContactFieldPanel cfP
    private String[] Career_name = {"Teacher", "Business man", "Derector", "Solder", "Student"};
    public JTextField cfPnum1 = new JTextField("Home Phone");
    public JTextField cfPnum2 = new JTextField("Employer");
    public JTextField cfPnum3 = new JTextField("Employee Phone");
    public JTextField cfPnum4 = new JTextField("Cell Phone");
    DefaultComboBoxModel dcm1 = new DefaultComboBoxModel(Career_name);
    JComboBox cfPcom = new JComboBox(dcm1);
    public JTextField cfPnum5 = new JTextField("E-mail");
    public JTextField cfPnum6 = new JTextField("Date of Birth");
    //AddressFieldPanel afP
    private String[] State_name = {"Mongolia", "United States", "United_Kingdom", "China", "France", "Germany"};
    public JTextField afPaddress1 = new JTextField("Address1");
    public JTextField afPaddress2 = new JTextField("Address2");
    public JTextField afPaddress3 = new JTextField("City");
    DefaultComboBoxModel dcm2 = new DefaultComboBoxModel(State_name);
    JComboBox afPaddress4 = new JComboBox(dcm2);
    public JTextField afPaddress5 = new JTextField("+976");
    //DescFieldPanel dfP
    private String[] Nation_name = {"Халх", "Дөрвөд", "Буриад", "Урианхай"},
            Gender_type = {"Эрэгтэй", "Эмэгтэй"},
            Eye_Color = {"Бор", "Хар", "Цэнхэр"},
            Hair_Color = {"Хар", "Бор", "Шар", "Улаан", "Ягаан", "Сор"};
    DefaultComboBoxModel dcm3 = new DefaultComboBoxModel(Nation_name),
            dcm4 = new DefaultComboBoxModel(Gender_type),
            dcm5 = new DefaultComboBoxModel(Eye_Color),
            dcm6 = new DefaultComboBoxModel(Hair_Color);
    public JComboBox defPdesc1 = new JComboBox(dcm3); //nation
    public JComboBox defPdesc2 = new JComboBox(dcm4); //Gender
    public JComboBox defPdesc3 = new JComboBox(dcm5); //Eye
    public JComboBox defPdesc6 = new JComboBox(dcm6); //Hair
    public JTextField defPdesc5 = new JTextField("Weight");//
    public JTextField defPdesc7 = new JTextField("Height"); //Hair
    public JTextField defPdesc8 = new JTextField("Tattoos");
    public JTextField defPdesc9 = new JTextField("Notes");
    //PhotoFieldPanel pfP
    private JPanel pfPphoto = new JPanel();
    private JButton pfPtake = new JButton("Take Photo");
    private JButton pfPscan = new JButton("Scan Photo");
    JMenuItem mi = new JMenuItem("Back");
    JPopupMenu popup = new JPopupMenu("Main GUI");

    /**
     * ************************************************************************
     */
    public MainGUI() {

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

        NameFieldPanel();
        //IdenFieldPanel();
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
        nfPmiddle.setPreferredSize(cmPanelDim);

        nfPfirst.setBorder(lb);
        nfPlast.setBorder(lb);

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb.setTitleColor(Color.white);
        tb.setTitle("Name");
        tb.setTitleFont(new Font("Arial", Font.BOLD, 13));

        nfPfirst.setFont(new Font("Arial", Font.BOLD, 15));
        nfPfirst.setForeground(Color.gray);
        nfPfirst.setHorizontalAlignment(JTextField.CENTER);
        nfPfirst.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nfPfirst.getText().equals("First Name")) {
                    nfPfirst.setText("");
                    nfPfirst.setForeground(Color.black);
                } else {
                    nfPfirst.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nfPfirst.getText().equals("")) {
                    nfPfirst.setText("First Name");
                    nfPfirst.setForeground(Color.lightGray);
                }
            }
        });
        nfPlast.setFont(new Font("Arial", Font.BOLD, 15));
        nfPlast.setForeground(Color.gray);
        nfPlast.setHorizontalAlignment(JTextField.CENTER);
        nfPlast.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nfPlast.getText().equals("Last Name")) {
                    nfPlast.setText("");
                    nfPlast.setForeground(Color.black);
                } else {
                    nfPlast.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nfPlast.getText().equals("")) {
                    nfPlast.setText("Last Name");
                    nfPlast.setForeground(Color.lightGray);
                }
            }
        });
        nfPmiddle.setFont(new Font("Arial", Font.BOLD, 15));
        nfPmiddle.setForeground(Color.gray);
        nfPmiddle.setHorizontalAlignment(JTextField.CENTER);
        nfPmiddle.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nfPmiddle.getText().equals("Register No")) {
                    nfPmiddle.setText("");
                    nfPmiddle.setForeground(Color.black);
                } else {
                    nfPmiddle.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nfPmiddle.getText().equals("")) {
                    nfPmiddle.setText("Register No");
                    nfPmiddle.setForeground(Color.lightGray);
                }
            }
        });

        cmPanels[0].setBorder(tb);

        cmPanels[0].add(nfPfirst);
        cmPanels[0].add(nfPlast);
        cmPanels[0].add(nfPmiddle);
    }

    private void ContactFieldPanel() {
        cfPnum1.setPreferredSize(cmPanelDim);
        cfPnum2.setPreferredSize(cmPanelDim);
        cfPnum3.setPreferredSize(cmPanelDim);
        cfPnum4.setPreferredSize(cmPanelDim);
        cfPcom.setPreferredSize(cmPanelDim);
        cfPnum5.setPreferredSize(cmPanelDim);
        cfPnum6.setPreferredSize(cmPanelDim);

        cfPnum1.setBorder(lb);
        cfPnum2.setBorder(lb);
        cfPnum3.setBorder(lb);
        cfPnum4.setBorder(lb);
        cfPcom.setBorder(lb);
        cfPnum5.setBorder(lb);
        cfPnum6.setBorder(lb);

        cfPnum1.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum1.setForeground(Color.gray);
        cfPnum1.setHorizontalAlignment(JTextField.CENTER);
        cfPnum1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum1.getText().equals("Home Phone")) {
                    cfPnum1.setText("");
                    cfPnum1.setForeground(Color.black);
                } else {
                    cfPnum1.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum1.getText().equals("")) {
                    cfPnum1.setText("Home Phone");
                    cfPnum1.setForeground(Color.lightGray);
                }
            }
        });

        cfPnum2.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum2.setForeground(Color.gray);
        cfPnum2.setHorizontalAlignment(JTextField.CENTER);
        cfPnum2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum2.getText().equals("Employer")) {
                    cfPnum2.setText("");
                    cfPnum2.setForeground(Color.black);
                } else {
                    cfPnum2.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum2.getText().equals("")) {
                    cfPnum2.setText("Employer");
                    cfPnum2.setForeground(Color.lightGray);
                }
            }
        });

        cfPnum3.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum3.setForeground(Color.gray);
        cfPnum3.setHorizontalAlignment(JTextField.CENTER);
        cfPnum3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum3.getText().equals("Employee Phone")) {
                    cfPnum3.setText("");
                    cfPnum3.setForeground(Color.black);
                } else {
                    cfPnum3.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum3.getText().equals("")) {
                    cfPnum3.setText("Employee Phone");
                    cfPnum3.setForeground(Color.lightGray);
                }
            }
        });

        cfPnum4.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum4.setForeground(Color.gray);
        cfPnum4.setHorizontalAlignment(JTextField.CENTER);
        cfPnum4.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum4.getText().equals("Cell Phone")) {
                    cfPnum4.setText("");
                    cfPnum4.setForeground(Color.black);
                } else {
                    cfPnum4.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum4.getText().equals("")) {
                    cfPnum4.setText("Cell Phone");
                    cfPnum4.setForeground(Color.lightGray);
                }
            }
        });

        cfPnum5.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum5.setForeground(Color.gray);
        cfPnum5.setHorizontalAlignment(JTextField.CENTER);
        cfPnum5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum5.getText().equals("E-mail")) {
                    cfPnum5.setText("");
                    cfPnum5.setForeground(Color.black);
                } else {
                    cfPnum5.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum5.getText().equals("")) {
                    cfPnum5.setText("E-mail");
                    cfPnum5.setForeground(Color.lightGray);
                }
            }
        });

        cfPnum6.setFont(new Font("Arial", Font.BOLD, 15));
        cfPnum6.setForeground(Color.gray);
        cfPnum6.setHorizontalAlignment(JTextField.CENTER);
        cfPnum6.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cfPnum6.getText().equals("Date of Birth")) {
                    cfPnum6.setText("");
                    cfPnum6.setForeground(Color.black);
                } else {
                    cfPnum6.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cfPnum6.getText().equals("")) {
                    cfPnum6.setText("Date of Birth");
                    cfPnum6.setForeground(Color.lightGray);
                }
            }
        });

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
        cmPanels[3].add(cfPnum6);
    }

    private void AddressFieldPanel() {
        Dimension newDim = new Dimension(440, 25);
        afPaddress1.setPreferredSize(newDim);
        afPaddress2.setPreferredSize(newDim);
        afPaddress3.setPreferredSize(newDim);
        afPaddress4.setPreferredSize(newDim);
        afPaddress5.setPreferredSize(new Dimension(70, 25));

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

        afPaddress1.setFont(new Font("Arial", Font.BOLD, 15));
        afPaddress1.setForeground(Color.gray);
        afPaddress1.setHorizontalAlignment(JTextField.CENTER);
        afPaddress1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (afPaddress1.getText().equals("Address1")) {
                    afPaddress1.setText("");
                    afPaddress1.setForeground(Color.black);
                } else {
                    afPaddress1.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (afPaddress1.getText().equals("")) {
                    afPaddress1.setText("Address1");
                    afPaddress1.setForeground(Color.lightGray);
                }
            }
        });

        afPaddress2.setFont(new Font("Arial", Font.BOLD, 15));
        afPaddress2.setForeground(Color.gray);
        afPaddress2.setHorizontalAlignment(JTextField.CENTER);
        afPaddress2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (afPaddress2.getText().equals("Address2")) {
                    afPaddress2.setText("");
                    afPaddress2.setForeground(Color.black);
                } else {
                    afPaddress2.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (afPaddress2.getText().equals("")) {
                    afPaddress2.setText("Address2");
                    afPaddress2.setForeground(Color.lightGray);
                }
            }
        });

        afPaddress3.setFont(new Font("Arial", Font.BOLD, 15));
        afPaddress3.setForeground(Color.gray);
        afPaddress3.setHorizontalAlignment(JTextField.CENTER);
        afPaddress3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (afPaddress3.getText().equals("City")) {
                    afPaddress3.setText("");
                    afPaddress3.setForeground(Color.black);
                } else {
                    afPaddress3.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (afPaddress3.getText().equals("")) {
                    afPaddress3.setText("City");
                    afPaddress3.setForeground(Color.lightGray);
                }
            }
        });

        afPaddress5.setEnabled(false);
        afPaddress5.setFont(new Font("Arial", Font.BOLD, 15));
        afPaddress5.setForeground(Color.gray);
        afPaddress5.setHorizontalAlignment(JTextField.CENTER);

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
        defPdesc5.setPreferredSize(cmPanelDim);
        defPdesc6.setPreferredSize(cmPanelDim);
        defPdesc7.setPreferredSize(cmPanelDim);
        defPdesc8.setPreferredSize(new Dimension(210, 75));
        defPdesc9.setPreferredSize(new Dimension(210, 75));

        defPdesc1.setBorder(lb);
        defPdesc2.setBorder(lb);
        defPdesc3.setBorder(lb);
        defPdesc5.setBorder(lb);
        defPdesc6.setBorder(lb);
        defPdesc7.setBorder(lb);
        defPdesc8.setBorder(lb);
        defPdesc9.setBorder(lb);

        defPdesc1.setBackground(Color.white);
        defPdesc2.setBackground(Color.white);
        defPdesc3.setBackground(Color.white);
        defPdesc6.setBackground(Color.white);
        defPdesc7.setBackground(Color.white);

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb.setTitleColor(Color.white);
        tb.setTitle("Description");
        tb.setTitleFont(new Font("Arial", Font.BOLD, 13));

        defPdesc5.setFont(new Font("Arial", Font.BOLD, 15));
        defPdesc5.setForeground(Color.gray);
        defPdesc5.setHorizontalAlignment(JTextField.CENTER);
        defPdesc5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (defPdesc5.getText().equals("Weight")) {
                    defPdesc5.setText("");
                    defPdesc5.setForeground(Color.black);
                } else {
                    defPdesc5.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (defPdesc5.getText().equals("")) {
                    defPdesc5.setText("Weight");
                    defPdesc5.setForeground(Color.lightGray);
                }
            }
        });

        defPdesc7.setFont(new Font("Arial", Font.BOLD, 15));
        defPdesc7.setForeground(Color.gray);
        defPdesc7.setHorizontalAlignment(JTextField.CENTER);
        defPdesc7.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (defPdesc7.getText().equals("Height")) {
                    defPdesc7.setText("");
                    defPdesc7.setForeground(Color.black);
                } else {
                    defPdesc7.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (defPdesc7.getText().equals("")) {
                    defPdesc7.setText("Height");
                    defPdesc7.setForeground(Color.lightGray);
                }
            }
        });
        defPdesc8.setFont(new Font("Arial", Font.BOLD, 15));
        defPdesc8.setForeground(Color.gray);
        defPdesc8.setHorizontalAlignment(JTextField.CENTER);
        defPdesc8.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (defPdesc8.getText().equals("Tattoos")) {
                    defPdesc8.setText("");
                    defPdesc8.setForeground(Color.black);
                } else {
                    defPdesc8.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (defPdesc8.getText().equals("")) {
                    defPdesc8.setText("Tattoos");
                    defPdesc8.setForeground(Color.lightGray);
                }
            }
        });

        defPdesc9.setFont(new Font("Arial", Font.BOLD, 15));
        defPdesc9.setForeground(Color.gray);
        defPdesc9.setHorizontalAlignment(JTextField.CENTER);
        defPdesc9.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (defPdesc9.getText().equals("Notes")) {
                    defPdesc9.setText("");
                    defPdesc9.setForeground(Color.black);
                } else {
                    defPdesc9.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (defPdesc9.getText().equals("")) {
                    defPdesc9.setText("Notes");
                    defPdesc9.setForeground(Color.lightGray);
                }
            }
        });

        cmPanels[5].setBorder(tb);

        cmPanels[5].add(defPdesc1);
        cmPanels[5].add(defPdesc2);
        cmPanels[5].add(defPdesc3);
        cmPanels[5].add(defPdesc7);
        cmPanels[5].add(defPdesc5);
        cmPanels[5].add(defPdesc6);
        cmPanels[5].add(defPdesc8);
        cmPanels[5].add(defPdesc9);
    }

    private void PhotoFieldPanel() {
        cmPanels[6].setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        pfPphoto.setPreferredSize(new Dimension(360, 260));
        pfPtake.setPreferredSize(new Dimension(175, 30));
        pfPscan.setPreferredSize(new Dimension(175, 30));

        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tb.setTitleColor(Color.white);
        tb.setTitle("Photo");
        tb.setTitleFont(new Font("Arial", Font.BOLD, 13));

        pfPtake.setBackground(background.darker());
        pfPtake.setForeground(Color.WHITE);

        pfPscan.setBackground(background.darker());
        pfPscan.setForeground(Color.WHITE);

        pfPtake.setBackground(background.darker());
        pfPtake.setForeground(Color.WHITE);
        pfPtake.setFont(new Font("Arial", Font.BOLD, 15));

        pfPscan.setBackground(background.darker());
        pfPscan.setForeground(Color.WHITE);
        pfPscan.setFont(new Font("Arial", Font.BOLD, 15));

        cmPanels[6].setBorder(tb);

        cmPanels[6].add(pfPphoto);
        cmPanels[6].add(pfPtake);
        cmPanels[6].add(pfPscan);
    }

    private JPanel CenterPanel() {
        try {
            centerP.setLayout(null);
            centerP.setBackground(new Color(41, 75, 110));

            centerP.add(titleL);
            titleL.setFont(new Font("Arial", Font.BOLD, 30));
            titleL.setForeground(Color.white);
            titleL.setBounds(15, 10, width - (width / 8), 30);
            titleL.setVerticalAlignment(JLabel.TOP);

            for (int i = 0; i < cmPanels.length; i++) {
                cmPanels[i] = new JPanel();
                cmPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
                cmPanels[i].setBackground(new Color(41, 75, 110));
                TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
                tb.setTitleColor(Color.white);
                tb.setTitle("Address");
                tb.setTitleFont(new Font("Arial", Font.BOLD, 13));
                cmPanels[i].setBorder(tb);
                centerP.add(cmPanels[i]);
            }
            //Adding Elementds to ComboBoxModel
            afPaddress4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop", "root", "");
                        PreparedStatement stm = con.prepareCall("Select * from state");
                        ResultSet rs = stm.executeQuery();
                        for (int i = 0; i <= afPaddress4.getSelectedIndex(); i++) {
                            if (rs.next()) {
                                String abc = rs.getString("Zip");
                                afPaddress5.setText(abc);
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            cancelB.setBackground(background.darker());
            cancelB.setForeground(Color.WHITE);

            saveB.setBackground(background.darker());
            saveB.setForeground(Color.WHITE);

            tranB.setBackground(background.darker());
            tranB.setForeground(Color.WHITE);

            centerP.add(cancelB);
            centerP.add(saveB);
            centerP.add(tranB);

            cmPanels[0].setBounds(10, 50, 230, 120);
            //cmPanels[1].setBounds(250, 50, 230, 150);
            cmPanels[3].setBounds(10, 180, 230, 240);
            cmPanels[4].setBounds(10, 440, 460, 180);
            cmPanels[5].setBounds(250, 50, 230, 375);
            cmPanels[6].setBounds(490, 50, 400, 350);

            cancelB.setBounds(491, 415, 187, 90);
            saveB.setBounds(700, 415, 187, 90);
            tranB.setBounds(491, 525, 399, 90);
            cancelB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    reset();
                }
            });

            cancelB.setBackground(background.darker());
            cancelB.setForeground(Color.WHITE);
            cancelB.setFont(new Font("Arial", Font.BOLD, 15));

            saveB.setBackground(background.darker());
            saveB.setForeground(Color.WHITE);
            saveB.setFont(new Font("Arial", Font.BOLD, 15));

            tranB.setBackground(background.darker());
            tranB.setForeground(Color.WHITE);
            tranB.setFont(new Font("Arial", Font.BOLD, 15));

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

        } catch (Exception ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centerP;
    }

    public void reset() {
        nfPfirst.setText("First Name");
        nfPlast.setText("Last Name");
        nfPmiddle.setText("Register No");
        cfPnum1.setText("Home Phone");
        cfPnum2.setText("Employer");
        cfPnum3.setText("Employee Phone");
        cfPnum4.setText("Cell Phone");
        cfPnum5.setText("E-mail");
        cfPnum6.setText("Date of Birth");
        afPaddress1.setText("Address1");
        afPaddress2.setText("Address2");
        afPaddress3.setText("City");
        afPaddress5.setText("Zip");
        afPaddress5.setEnabled(false);
        defPdesc5.setText("Weight");
        defPdesc7.setText("Height");
        defPdesc8.setText("Tattoos");
        defPdesc9.setText("Notes");
        cfPcom.setSelectedIndex(0);
        afPaddress4.setSelectedIndex(0);
        defPdesc1.setSelectedIndex(0);
        defPdesc2.setSelectedIndex(0);
        defPdesc3.setSelectedIndex(0);
        defPdesc6.setSelectedIndex(0);
        nfPfirst.setForeground(Color.gray);
        nfPlast.setForeground(Color.gray);
        nfPmiddle.setForeground(Color.gray);
        cfPnum1.setForeground(Color.gray);
        cfPnum2.setForeground(Color.gray);
        cfPnum3.setForeground(Color.gray);
        cfPnum4.setForeground(Color.gray);
        cfPnum5.setForeground(Color.gray);
        cfPnum6.setForeground(Color.gray);
        defPdesc7.setForeground(Color.gray);
        afPaddress1.setForeground(Color.gray);
        afPaddress2.setForeground(Color.gray);
        afPaddress3.setForeground(Color.gray);
        afPaddress5.setForeground(Color.gray);
        defPdesc5.setForeground(Color.gray);
        defPdesc8.setForeground(Color.gray);
        defPdesc9.setForeground(Color.gray);
    }

    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(130, 25);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width / 8, height));
        eastP.setOpaque(false);
        eastP.setBorder(BorderFactory.createLineBorder(Color.black));

        gap1.setPreferredSize(new Dimension(110, 25));

        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField();
            fields[i].setPreferredSize(fieldDim);
            fields[i].setEditable(false);
        }

        image.setForeground(Color.white);
        field1L.setForeground(Color.white);
        field2L.setForeground(Color.white);
        field3L.setForeground(Color.white);
        field4L.setForeground(Color.white);

        field1L.setFont(new Font("Arial", Font.BOLD, 13));
        field2L.setFont(new Font("Arial", Font.BOLD, 13));
        field3L.setFont(new Font("Arial", Font.BOLD, 13));
        field4L.setFont(new Font("Arial", Font.BOLD, 13));

        image.setPreferredSize(new Dimension(130, 130));
        field1L.setPreferredSize(fieldDim);
        field2L.setPreferredSize(fieldDim);
        field3L.setPreferredSize(fieldDim);
        field4L.setPreferredSize(fieldDim);

        field1L.setHorizontalAlignment(JLabel.LEFT);
        field2L.setHorizontalAlignment(JLabel.LEFT);
        field3L.setHorizontalAlignment(JLabel.LEFT);
        field4L.setHorizontalAlignment(JLabel.LEFT);

//        fields[0].setText(new tempSaveUser().getUserName());
        eastP.add(gap1);
        eastP.add(image);
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
        new MainGUI().setVisible(true);
    }
}