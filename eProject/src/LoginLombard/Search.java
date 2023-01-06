package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Search extends JFrame {

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
    //East panel components
    JTextField[] fields = new JTextField[4];
    JLabel image = new JLabel(new ImageIcon("images.jpeg")),
            field1L = new JLabel("Firstname"),
            field2L = new JLabel("Lastname"),
            field3L = new JLabel("username"),
            field4L = new JLabel("Password");
    public final JButton logout = new JButton("Log out");
    JLabel gap1 = new JLabel(),
            gap2 = new JLabel();
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
    //Center panel components
    private String[] for_name = {"Customer", "Transaction"};
    private String[] for_name2 = {"Customer", "Employee", "Transaction"};
    private String[] by_name = {"ID", "Firstname", "Lastname", "Cell Phone", "Register number"},
            by_name2 = {"ID", "Type", "Make", "Serial"},
            by_name3 = {"ID", "Firstname", "Lastname", "Register number"};
    private JLabel titleL = new JLabel("Search");
    public DefaultComboBoxModel sForM1 = new DefaultComboBoxModel(for_name),
            sForM2 = new DefaultComboBoxModel(for_name2),
            sByM1 = new DefaultComboBoxModel(by_name),
            sByM2 = new DefaultComboBoxModel(by_name2),
            sByM3 = new DefaultComboBoxModel(by_name3);
    public JComboBox sFor = new JComboBox(sForM1),
            sBy = new JComboBox(sByM1);
    private JTextField sName = new JTextField();
    public JButton addCus = new JButton("Add Customer"), addEmp = new JButton("Add Employee");
    private JLabel sForL = new JLabel("Search for"),
            sByL = new JLabel("Search by"),
            resultL = new JLabel("Results");
    private int row = 8;
    public JPanel searchPanel = new JPanel(new GridLayout(row, 1, 10, 10));
    private JPanel sp = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 10));
    public JScrollPane scScroll = new JScrollPane(sp);
    private SearchResultPanel[] srp;
    JMenuItem mi = new JMenuItem("Log out");
    JPopupMenu popup = new JPopupMenu("Main GUI");

    /*
     * ************************************************************************
     */
    public Search() {
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

    public JPanel CenterPanel() {
        centerP.setLayout(null);

        centerP.add(titleL);
        titleL.setFont(new Font("Arial", Font.BOLD, 30));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 10, width - 300, 30);
        titleL.setVerticalAlignment(JLabel.TOP);

        addCus.setBackground(background.darker());
        addCus.setForeground(Color.WHITE);
        addCus.setFont(new Font("Arial", Font.BOLD, 15));
        addEmp.setBackground(background.darker());
        addEmp.setForeground(Color.WHITE);
        addEmp.setFont(new Font("Arial", Font.BOLD, 15));

        sForL.setBounds(10, 50, 180, 27);
        sByL.setBounds(200, 50, 180, 27);
        sFor.setBounds(10, 80, 180, 27);
        sBy.setBounds(200, 80, 180, 27);
        sName.setBounds(390, 80, 230, 27);
        resultL.setBounds(10, 120, 180, 27);
        addCus.setBounds(10, 570, 150, 50);
        addEmp.setBounds(170, 570, 150, 50);
        scScroll.setBounds(10, 120, 880, 440);
        scScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchPanel.setBackground(background);
        sp.add(searchPanel);
        sp.setBackground(background);

        resultL.setVisible(false);
        scScroll.setVisible(false);

        sName.setHorizontalAlignment(JTextField.CENTER);
        sName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                sName.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        sFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sFor.getSelectedIndex() == 1) {
                    sBy.setModel(sByM1);
                } else if (sForM2.equals(sFor.getModel()) && (sFor.getSelectedIndex() == 2)) {
                    sBy.setModel(sByM3);
                } else {
                    sBy.setModel(sByM2);
                }
            }
        });
        sName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                String text = sName.getText().toLowerCase();
                if (!text.equals("")) {
                    try {
                        searchPanel.removeAll();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop", "root", "");
                        PreparedStatement stm = con.prepareCall("Select * from customer");
                        ResultSet rs = stm.executeQuery();
                        PreparedStatement stm2 = con.prepareCall("Select * from customer_contact");
                        ResultSet rs2 = stm2.executeQuery();
                        PreparedStatement stm3 = con.prepareCall("Select * from ticket");
                        ResultSet rs3 = stm3.executeQuery();
                        PreparedStatement stm4 = con.prepareCall("Select * from ticket_item");
                        ResultSet rs4 = stm4.executeQuery();
                        PreparedStatement stm5 = con.prepareCall("Select * from employee");
                        ResultSet rs5 = stm5.executeQuery();
                        String searching = "",
                                searching2 = "";
                        srp = new SearchResultPanel[row];
                        int i = 0;
                        while (rs.next()) {
                            System.out.println("" + sFor.getSelectedIndex() + " " + sBy.getSelectedIndex());
                            switch (sFor.getSelectedIndex()) {
                                case 0:
                                    switch (sBy.getSelectedIndex()) {
                                        case 0:
                                            searching = rs.getString("Customer");
                                            break;
                                        case 1:
                                            searching = rs.getString("Customer_firstname");
                                            break;
                                        case 2:
                                            searching = rs.getString("Customer_lastname");
                                            break;
                                        case 3:
                                            searching = rs2.getString("Cell_Phone");
                                            break;
                                        case 4:
                                            searching = rs.getString("Customer_registernumber");
                                            break;
                                    }
                                    break;
                                case 1:
                                    if (sForM2.equals(sFor.getModel())) {
                                        switch (sBy.getSelectedIndex()) {
                                            case 0:
                                                searching = rs5.getString("Emloyee_id");
                                                break;
                                            case 1:
                                                searching = rs5.getString("Employee_firstname");
                                                break;
                                            case 2:
                                                searching = rs5.getString("Employee_lastname");
                                                break;
                                            case 3:
                                                searching = rs5.getString("Employee_registernumber");
                                                break;
                                        }
                                    } else {
                                        switch (sBy.getSelectedIndex()) {
                                            case 0:
                                                searching = rs4.getString("Ticket_id");
                                                break;
                                            case 1:
                                                searching = rs4.getString("Type");
                                                break;
                                            case 2:
                                                searching = rs4.getString("Make");
                                                break;
                                            case 3:
                                                searching = rs4.getString("Serial_number");
                                                break;
                                        }
                                    }
                                    break;
                                case 2:
                                    switch (sBy.getSelectedIndex()) {
                                        case 0:
                                            searching = rs4.getString("Ticket_id");
                                            break;
                                        case 1:
                                            searching = rs4.getString("Type");
                                            break;
                                        case 2:
                                            searching = rs4.getString("Make");
                                            break;
                                        case 3:
                                            searching = rs4.getString("Model");
                                            break;
                                    }
                            }
                            if (searching.toLowerCase().contains(text)) {
                                srp[i] = new SearchResultPanel("index.jpeg", rs5.getString("Employee_firstname"), rs5.getString("Employee_lastname"));
                                searchPanel.add(srp[i]);
                                i++;
                            }
                        }
                        scScroll.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (key == KeyEvent.VK_BACK_SPACE) {
                    if (sName.getCaretPosition() == 1) {
                        scScroll.setVisible(false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        sForL.setForeground(Color.white);
        sByL.setForeground(Color.white);
        resultL.setForeground(Color.white);

        sForL.setFont(new Font("Times New Roman", Font.BOLD, 15));
        sByL.setFont(new Font("Times New Roman", Font.BOLD, 15));
        resultL.setFont(new Font("Times New Roman", Font.BOLD, 15));

        popup.add(mi);
        centerP.add(popup);

        centerP.setBounds(0, 0, width - (width / 8), height);
        centerP.setBackground(new Color(41, 75, 110));
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

        centerP.add(sForL);
        centerP.add(sByL);
        centerP.add(sFor);
        centerP.add(sBy);
        centerP.add(sName);
        centerP.add(resultL);
        centerP.add(addCus);
        centerP.add(addEmp);
        centerP.add(scScroll);

        centerP.setBounds(0, 0, width - (width / 8), height);
        centerP.setBackground(new Color(41, 75, 110));
        return centerP;
    }

    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(130, 25);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(20, 75, 120));
        eastP.setPreferredSize(new Dimension(width / 8, height));
        eastP.setOpaque(false);
        eastP.setBorder(BorderFactory.createLineBorder(Color.black));

        gap1.setPreferredSize(new Dimension(110, 25));
        gap2.setPreferredSize(new Dimension(110, 215));

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
        logout.setBackground(background.darker());
        logout.setForeground(Color.WHITE);

        field1L.setFont(new Font("Arial", Font.BOLD, 13));
        field2L.setFont(new Font("Arial", Font.BOLD, 13));
        field3L.setFont(new Font("Arial", Font.BOLD, 13));
        field4L.setFont(new Font("Arial", Font.BOLD, 13));
        logout.setPreferredSize(new Dimension(130, 50));

        image.setPreferredSize(new Dimension(130, 130));
        field1L.setPreferredSize(fieldDim);
        field2L.setPreferredSize(fieldDim);
        field3L.setPreferredSize(fieldDim);
        field4L.setPreferredSize(fieldDim);
        logout.setFont(new Font("Arial", Font.BOLD, 15));

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
        //eastP.add(field4L);
        //eastP.add(fields[3]);
        eastP.add(gap2);
        eastP.add(logout);

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
        new Search().setVisible(true);
    }
}
