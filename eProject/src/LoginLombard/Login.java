package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    /**
     * ************************************************************************
     */
    //Container components
    JFrame tf = this;
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

            GradientPaint gp = new GradientPaint(0, 0, getBackground(), 0, 900, getBackground().darker().darker());

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
    private JPanel centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
    private JLabel titleL = new JLabel("Sign in");
    private Border lb = BorderFactory.createEmptyBorder();
    private JTextField userF = new JTextField();
    private JTextField passF1 = new JTextField();
    private JPasswordField passF = new JPasswordField();
    private JButton signB = new JButton("Sign in");
    private JLabel forgotL = new JLabel("Forgot password?");
    //Timer
    int x = -900, x2 = width / 8, selected = 0, speed = 35, eSelected = 1;
    Timer timer = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            searchPE.setBounds(150, 0, width / 8, height);
            loginP.setBounds(x, 0, width - 300, height);
            nullPE.setBackground(new Color(41, 75, 110));
            nullPE.setBounds(0, 0, width / 8, height);
            x += 30;
            if (x > -50) {
                timer.stop();
            }
        }
    });
    JPanel loginP = new JPanel(null);//Log in Panel
    boolean mbool = false;
    Search search = new Search();
    MainGUI mainGUI = new MainGUI();
    MainGUI2 mainGUI2 = new MainGUI2();
    Admin admin = new Admin();
    Inventory inventory = new Inventory();
    Settings settings = new Settings();
    Support support = new Support();
    Transaction transaction = new Transaction();
    newTransaction newTransaction = new newTransaction();
    JPanel searchP = search.centerP,
            mainGUIP = mainGUI.centerP,
            mainGUI2P = mainGUI2.centerP,
            adminP = admin.centerP,
            inventoryP = inventory.centerP,
            settingsP = settings.centerP,
            supportP = support.centerP,
            transactionP = transaction.centerP,
            newTransactionP = newTransaction.centerP;
    JPanel searchPE = search.eastP,
            mainGUIPE = mainGUI.eastP,
            mainGUI2PE = mainGUI2.eastP,
            adminPE = admin.eastP,
            inventoryPE = inventory.eastP,
            transactionPE = transaction.eastP,
            newTransactionPE = newTransaction.eastP,
            nullPE = new JPanel(null);
    //Server Socket
    int length = 4;
    Socket[] socket = new Socket[length];
    DataInputStream[] dis = new DataInputStream[length];
    DataOutputStream[] dos = new DataOutputStream[length];
    NewSocket socks;
    JPanel easter = new JPanel(null);

    /**
     * ************************************************************************
     */
    public Login() {
        try {
            timer.start();
            UIManager.setLookAndFeel(new NimbusLookAndFeel());

            socks = new NewSocket();
            socket[0] = new Socket("localhost", 2121);
            socket[1] = new Socket("localhost", 2222);
            socket[2] = new Socket("localhost", 2323);
            socket[3] = new Socket("localhost", 2424);
            for (int i = 0; i < length; i++) {
                dis[i] = new DataInputStream(socket[i].getInputStream());
                dos[i] = new DataOutputStream(socket[i].getOutputStream());
            }

            getContentPane().add(p);

            easter.setPreferredSize(new Dimension(width / 8, height));
            easter.setBackground(background);
            easter.add(EastPanel());
            p.add(easter, BorderLayout.EAST);
            p.add(WestPanel(), BorderLayout.WEST);
            p.add(CenterPanel(), BorderLayout.CENTER);

            addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent ce) {
                }

                @Override
                public void componentMoved(ComponentEvent ce) {
                }

                @Override
                public void componentShown(ComponentEvent ce) {
                }

                @Override
                public void componentHidden(ComponentEvent ce) {
                    try {
                        for (int i = 0; i < length; i++) {
                            socket[i].close();
                            socks.ss[i].close();
                            socks.socket[i].close();
                        }
                    } catch (IOException ex) {
                    }
                }
            });
            setSize(width, height);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setTitle("Lombard");
            setLocationRelativeTo(null);
            setIconImage(new ImageIcon("pack.png").getImage());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Validate() {
        try {
            //tempSaveUser user = new tempSaveUser();
            String name = userF.getText().toLowerCase();
            String pass = new String(passF.getPassword());

            dos[0].writeUTF(name);
            dos[0].writeUTF(pass);
            boolean bool1 = dis[0].readBoolean();
            String fname = dis[0].readUTF();
            String lname = dis[0].readUTF();
            String regno = dis[0].readUTF();
            String nname = dis[0].readUTF();
            String cphone = dis[0].readUTF();
            String heig = dis[0].readUTF();
            String weig = dis[0].readUTF();
            String email = dis[0].readUTF();
            String addr = dis[0].readUTF();
            String tattoo = dis[0].readUTF();
            String uname = dis[0].readUTF();
            String pname = dis[0].readUTF();
            int wtfs = dis[0].read();

            if (wtfs == 1) {
                search.sFor.setModel(search.sForM2);
            } else {
                search.sFor.setModel(search.sForM1);
            }
            if (bool1) {
                startTimer(loginP, searchP, nullPE, searchPE);
                search.fields[0].setText(fname);
                search.fields[1].setText(lname);
                search.fields[2].setText(uname);
                search.fields[3].setText(pname);
                settings.userFirstF.setText(fname);
                settings.userLastF.setText(lname);
                settings.userRegisF.setText(regno);
                settings.userNationF.setText(nname);
                settings.userCellPhoneF.setText(cphone);
                settings.userHeightF.setText(heig);
                settings.userWeightF.setText(weig);
                settings.userEmailF.setText(email);
                settings.userAddressF.setText(addr);
                settings.userTattooF.setText(tattoo);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Username or password is wrong");
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startTimer(final JPanel hider, final JPanel shower, final JPanel ehide, final JPanel eshow) {
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mbool) {
                    shower.setBounds(x, 0, width - 300, height);
                    x += speed;
                    eshow.setBounds(x2, 0, width / 8, height);
                    x2 -= 140 / (900 / speed);
                    if (x > 20) {
                        eshow.setBounds(0, 0, width / 8, height);
                        x2 = 0;
                        mbool = false;
                        timer.stop();
                    }
                } else {
                    hider.setBounds(x, 0, width - 300, height);
                    x -= speed;
                    ehide.setBounds(x2, 0, width / 8, height);
                    x2 += 140 / (900 / speed);
                    if (x < -1000) {
                        ehide.setBounds(width / 8, 0, width / 8, height);
                        x2 = 150;
                        mbool = true;
                    }
                }
                setSelectedPanel(shower, eshow);
            }
        });
        timer.start();
    }

    private JPanel CenterPanel() {
        cent.setLayout(null);
        centerP.setLayout(null);
        centerP.setPreferredSize(new Dimension(width - 300, height));

        loginP.setBackground(new Color(41, 75, 110));

        loginP.add(titleL);
        titleL.setFont(new Font("Arial", Font.BOLD, 30));
        titleL.setForeground(Color.white);
        titleL.setBounds(15, 150, 870, 40);
        //titleL.setBorder(BorderFactory.createLineBorder(Color.yellow));
        titleL.setVerticalAlignment(JLabel.TOP);
        titleL.setHorizontalAlignment(JLabel.CENTER);

        userF.setBounds(320, 200, 250, 35);
        passF1.setBounds(320, 245, 250, 35);
        passF.setBounds(320, 245, 250, 35);
        signB.setBounds(320, 290, 250, 35);
        forgotL.setBounds(320, 325, 120, 20);

        userF.setFont(new Font("Times New Roman", Font.BOLD, 15));
        passF.setFont(new Font("Times New Roman", Font.BOLD, 15));
        passF1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        signB.setFont(new Font("Arial", Font.BOLD, 18));

        userF.setForeground(Color.gray);
        passF1.setForeground(Color.gray);

        userF.setText("Enter Username...");
        passF1.setText("Enter Password...");
        passF.setVisible(false);

        userF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userF.getText().equals("Enter Username...")) {
                    userF.setForeground(Color.black);
                    userF.setText("");
                } else {
                    userF.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userF.getText().equals("")) {
                    userF.setForeground(Color.gray);
                    userF.setText("Enter Username...");
                }
            }
        });
        passF1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passF1.setVisible(false);
                passF.setVisible(true);
                passF.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        userF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_TAB:
                        passF1.setVisible(false);
                        passF.setVisible(true);
                        passF.requestFocus();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        passF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!new String(passF.getPassword()).equals("")) {
                    passF.selectAll();
                }
                passF.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passF.getPassword()).equals("")) {
                    passF.setVisible(false);
                    passF1.setVisible(true);
                    passF1.setVisible(true);
                }
            }
        });
        passF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_ENTER:
                        Validate();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        signB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //**************************
                Validate();
                //**************************
            }
        });
        forgotL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgotPass forgotPanel = new ForgotPass();
                forgotPanel.show();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgotL.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotL.setForeground(Color.white);
            }
        });
        addListeners();

        userF.setBorder(lb);
        passF.setBorder(lb);
        passF1.setBorder(lb);

        userF.setHorizontalAlignment(JTextField.CENTER);
        passF.setHorizontalAlignment(JPasswordField.CENTER);
        passF1.setHorizontalAlignment(JTextField.CENTER);

        forgotL.setForeground(Color.white);

        loginP.add(userF);
        loginP.add(passF1);
        loginP.add(passF);
        loginP.add(signB);
        loginP.add(forgotL);

        loginP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        searchP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        mainGUIP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        mainGUI2P.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        adminP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        inventoryP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        settingsP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        supportP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        transactionP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        newTransactionP.setBounds((-1) * (width - (width / 8)), 0, width - (width / 8) - 130, height);
        centerP.add(loginP);
        centerP.add(searchP);
        centerP.add(mainGUIP);
        centerP.add(mainGUI2P);
        centerP.add(adminP);
        centerP.add(inventoryP);
        centerP.add(settingsP);
        centerP.add(supportP);
        centerP.add(transactionP);
        centerP.add(newTransactionP);

        centerP.setBackground(new Color(41, 75, 110));

        return centerP;
    }

    private JPanel EastPanel() {
        Dimension fieldDim = new Dimension(120, 29);
        eastP.setBounds(0, 0, width / 8, height);
        eastP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        eastP.setBackground(new Color(41, 75, 110));
        eastP.setPreferredSize(new Dimension(width / 8, height));
        eastP.setOpaque(false);

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

        nullPE.setBounds(0, 0, width / 8, height);
        searchPE.setBounds(150, 0, width / 8, height);
        mainGUIPE.setBounds(150, 0, width / 8, height);
        mainGUI2PE.setBounds(150, 0, width / 8, height);
        adminPE.setBounds(150, 0, width / 8, height);
        inventoryPE.setBounds(150, 0, width / 8, height);
        transactionPE.setBounds(150, 0, width / 8, height);
        newTransactionPE.setBounds(150, 0, width / 8, height);
        eastP.add(searchPE);
        eastP.add(mainGUIPE);
        eastP.add(mainGUI2PE);
        eastP.add(adminPE);
        eastP.add(inventoryPE);
        eastP.add(transactionPE);
        eastP.add(newTransactionPE);
        eastP.add(nullPE);

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

        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(getSelectedPanel().equals(searchP) || getSelectedPanel().equals(loginP))) {
                    startTimer(getSelectedPanel(), searchP, getSelectedEastPanel(), searchPE);
                } else {
                    vibrate(getSelectedPanel());
                }
            }
        });
        configB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(getSelectedPanel().equals(settingsP) || getSelectedPanel().equals(loginP))) {
                    startTimer(getSelectedPanel(), settingsP, getSelectedEastPanel(), nullPE);
                } else {
                    vibrate(getSelectedPanel());
                }
            }
        });
        supportB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(getSelectedPanel().equals(supportP) || getSelectedPanel().equals(loginP))) {
                    startTimer(getSelectedPanel(), supportP, getSelectedEastPanel(), nullPE);
                } else {
                    vibrate(getSelectedPanel());
                }
            }
        });
        inventoryB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(getSelectedPanel().equals(inventoryP) || getSelectedPanel().equals(loginP))) {
                    startTimer(getSelectedPanel(), inventoryP, getSelectedEastPanel(), inventoryPE);
                } else {
                    vibrate(getSelectedPanel());
                }
            }
        });
        adminB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(getSelectedPanel().equals(adminP) || getSelectedPanel().equals(loginP))) {
                    startTimer(getSelectedPanel(), adminP, getSelectedEastPanel(), adminPE);
                } else {
                    vibrate(getSelectedPanel());
                }
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
        new Login().setVisible(true);
    }

    private void setSelectedPanel(JPanel a, JPanel b) {
        if (a.equals(transactionP)) {
            selected = 8;
        }
        if (a.equals(loginP)) {
            selected = 0;
        }
        if (a.equals(searchP)) {
            selected = 1;
        }
        if (a.equals(mainGUIP)) {
            selected = 2;
        }
        if (a.equals(mainGUI2P)) {
            selected = 3;
        }
        if (a.equals(adminP)) {
            selected = 4;
        }
        if (a.equals(inventoryP)) {
            selected = 5;
        }
        if (a.equals(settingsP)) {
            selected = 6;
        }
        if (a.equals(supportP)) {
            selected = 7;
        }
        if (a.equals(newTransactionP)) {
            selected = 9;
        }

        if (b.equals(nullPE)) {
            eSelected = 1;
        }
        if (b.equals(searchPE)) {
            eSelected = 2;
        }
        if (b.equals(mainGUIPE)) {
            eSelected = 3;
        }
        if (b.equals(mainGUI2PE)) {
            eSelected = 4;
        }
        if (b.equals(adminPE)) {
            eSelected = 5;
        }
        if (b.equals(inventoryPE)) {
            eSelected = 6;
        }
        if (b.equals(transactionPE)) {
            eSelected = 7;
        }
        if (b.equals(newTransactionPE)) {
            eSelected = 8;
        }
    }

    private JPanel getSelectedEastPanel() {
        switch (eSelected) {
            case 1:
                return nullPE;
            case 2:
                return searchPE;
            case 3:
                return mainGUIPE;
            case 4:
                return mainGUI2PE;
            case 5:
                return adminPE;
            case 6:
                return inventoryPE;
            case 7:
                return transactionPE;
            case 8:
                return newTransactionPE;
            default:
                return nullPE;
        }
    }

    private JPanel getSelectedPanel() {
        switch (selected) {
            case 0:
                return loginP;
            case 1:
                return searchP;
            case 2:
                return mainGUIP;
            case 3:
                return mainGUI2P;
            case 4:
                return adminP;
            case 5:
                return inventoryP;
            case 6:
                return settingsP;
            case 7:
                return supportP;
            case 8:
                return transactionP;
            case 9:
                return newTransactionP;
            default:
                return loginP;
        }
    }
    int time = 10;

    public void vibrate(final JPanel vibe) {
        timer.stop();
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (time != 0) {
                    vibe.setBounds((int) (vibe.getAlignmentX()) + (int) (Math.random() * 10), (int) (vibe.getAlignmentY()) + (int) (Math.random() * 10), width - 300, height);
                    time--;
                } else {
                    vibe.setBounds(0, 0, width - 300, height);
                    time = 5;
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void addListeners() {
        search.addCus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(searchP, mainGUIP, searchPE, searchPE);
            }
        });
        search.addEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer(searchP, mainGUI2P, searchPE, searchPE);
            }
        });
        mainGUI.tranB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(mainGUIP, transactionP, mainGUIPE, transactionPE);
            }
        });
        mainGUI2.tranB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(mainGUI2P, searchP, mainGUI2PE, searchPE);
            }
        });
        transaction.tranB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(transactionP, newTransactionP, transactionPE, newTransactionPE);
            }
        });
        newTransaction.addT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Item
                //
                ///
                ////
                /////
                //////
                ///////
                ////////
                int i = 1;
                if (newTransaction.addMerch.isSelected()) {
                    try {
                        dos[i].write(1);
                        dos[i].write(newTransaction.merchCategoryF.getSelectedIndex());
                        dos[i].writeUTF(newTransaction.merchTypeF.getText());
                        dos[i].writeUTF(newTransaction.merchMakeF.getText());
                        dos[i].writeUTF(newTransaction.merchModelF.getText());
                        dos[i].writeUTF(newTransaction.merchSerialF.getText());
                        dos[i].writeUTF(newTransaction.merchDetailsF.getText());
                        dos[i].writeUTF(newTransaction.merchRateF.getText());
                        dos[i].writeUTF(newTransaction.merchSdateF.getText());
                        dos[i].writeUTF(newTransaction.merchEdateF.getText());
                        dos[i].writeUTF(newTransaction.merchAmountF.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        dos[i].write(2);
                        dos[i].write(newTransaction.jewTypeF.getSelectedIndex());
                        dos[i].writeUTF(newTransaction.jewStyleF.getSelectedItem().toString());
                        dos[i].writeUTF(newTransaction.jewMetalF.getSelectedItem().toString());
                        dos[i].writeUTF(newTransaction.jewKaratF.getText());
                        dos[i].writeUTF(newTransaction.jewSizeF.getText());
                        dos[i].writeUTF(newTransaction.jewRateF.getText());
                        dos[i].writeUTF(newTransaction.jewDetailF.getText());
                        dos[i].writeUTF(newTransaction.jewSdateF.getText());
                        dos[i].writeUTF(newTransaction.jewEdateF.getText());
                        dos[i].writeUTF(newTransaction.merchAmountF.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        //Menu Item Listener
        search.mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                passF.setText("");
                startTimer(searchP, loginP, searchPE, nullPE);
            }
        });
        mainGUI.mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(mainGUIP, searchP, mainGUIPE, searchPE);
            }
        });
        mainGUI2.mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(mainGUI2P, searchP, mainGUI2PE, searchPE);

            }
        });
        transaction.mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(transactionP, mainGUIP, transactionPE, searchPE);
            }
        });
        newTransaction.mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startTimer(newTransactionP, transactionP, newTransactionPE, transactionPE);
            }
        });
        search.logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passF.setText("");
                startTimer(getSelectedPanel(), loginP, getSelectedEastPanel(), nullPE);
            }
        });

        //Socket-Connection 
        mainGUI2.saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ((mainGUI2.nfPfirst.getText().equals("First Name")
                            || mainGUI2.nfPlast.getText().equals("Last Name")
                            || mainGUI2.nfPmiddle.getText().equals("Register No")
                            || mainGUI2.cfPnum6.getText().equals("Date of Birth")
                            || mainGUI2.afPaddress1.getText().equals("Address1")
                            || mainGUI2.afPaddress3.getText().equals("City"))) {
                        JOptionPane.showMessageDialog(rootPane, "Fill in");
                    } else {
                        dos[3].writeUTF("" + mainGUI2.nfPfirst.getText());
                        dos[3].writeUTF("" + mainGUI2.nfPlast.getText());
                        dos[3].writeUTF("" + mainGUI2.nfPmiddle.getText());

                        dos[3].writeUTF("" + mainGUI2.defPdesc1.getSelectedItem().toString());
                        dos[3].writeUTF("" + mainGUI2.defPdesc2.getSelectedItem().toString());
                        dos[3].writeUTF("" + mainGUI2.defPdesc6.getSelectedItem().toString());
                        if (mainGUI2.defPdesc3.getText().equals("Height")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.defPdesc3.getText());
                        }
                        if (mainGUI2.defPdesc5.getText().equals("Weight")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.defPdesc5.getText());
                        }
                        dos[3].writeUTF("" + mainGUI2.defPdesc7.getSelectedItem().toString());
                        if (mainGUI2.defPdesc8.getText().equals("Tattoos")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.defPdesc8.getText());
                        }
                        if (mainGUI2.defPdesc9.getText().equals("Notes")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.defPdesc9.getText());
                        }

                        dos[3].writeUTF("" + mainGUI2.defPdesc9.getText());
                        dos[3].writeUTF("" + mainGUI2.defPdesc10.getText());

                        dos[3].writeUTF("" + mainGUI2.Question1.getSelectedItem().toString());
                        dos[3].writeUTF("" + mainGUI2.Answer1.getText());
                        dos[3].writeUTF("" + mainGUI2.Question2.getSelectedItem().toString());
                        dos[3].writeUTF("" + mainGUI2.Answer2.getText());

                        if (mainGUI2.cffPnum1.getText().equals("Home Phone")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.cffPnum1.getText());
                        }
                        dos[3].writeUTF("" + mainGUI2.cffPnum2.getText());
                        dos[3].writeUTF("" + mainGUI2.cffPnum3.getText());
                        dos[3].writeUTF("" + mainGUI2.cfPnum6.getText());

                        dos[3].writeUTF("" + mainGUI2.afPaddress1.getText());
                        if (mainGUI2.afPaddress2.getText().equals("Address2")) {
                            dos[3].writeUTF("");
                        } else {
                            dos[3].writeUTF("" + mainGUI2.afPaddress2.getText());
                        }
                        dos[3].writeUTF("" + mainGUI2.afPaddress3.getText());
                        dos[3].writeUTF("" + mainGUI2.afPaddress4.getSelectedItem().toString());
                        dos[3].writeUTF("" + mainGUI2.afPaddress5.getText());
                        mainGUI2.reset();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mainGUI.saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (mainGUI.nfPfirst.getText().equals("First Name")
                            || mainGUI.nfPlast.getText().equals("Last Name")
                            || mainGUI.nfPmiddle.getText().equals("Register No")
                            || mainGUI.cfPnum4.getText().equals("Cell Phone")
                            || mainGUI.cfPnum5.getText().equals("E-mail")
                            || mainGUI.cfPnum6.getText().equals("Date of Birth")
                            || mainGUI.afPaddress1.getText().equals("Address1")
                            || mainGUI.afPaddress3.getText().equals("City")
                            || mainGUI.afPaddress5.getText().equals("Zip")
                            || mainGUI.cfPnum1.getText().equals("Home Phone")) {
                        JOptionPane.showMessageDialog(rootPane, "Fill in");
                    } else {
                        dos[2].writeUTF("" + mainGUI.nfPfirst.getText());
                        dos[2].writeUTF("" + mainGUI.nfPlast.getText());
                        dos[2].writeUTF("" + mainGUI.nfPmiddle.getText());


                        dos[2].writeUTF("" + mainGUI.defPdesc1.getSelectedItem().toString());
                        dos[2].writeUTF("" + mainGUI.defPdesc2.getSelectedItem().toString());
                        if (mainGUI.defPdesc5.getText().equals("Weight")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.defPdesc5.getText());
                        }
                        if (mainGUI.defPdesc7.getText().equals("Height")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.defPdesc7.getText());
                        }
                        dos[2].writeUTF("" + mainGUI.defPdesc3.getSelectedItem().toString());
                        dos[2].writeUTF("" + mainGUI.defPdesc6.getSelectedItem().toString());
                        if (mainGUI.defPdesc8.getText().equals("Tattoos")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.defPdesc8.getText());
                        }
                        if (mainGUI.defPdesc9.getText().equals("Notes")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.defPdesc9.getText());
                        }

                        if (mainGUI.cfPnum1.getText().equals("Home Phone")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.cfPnum1.getText());
                        }
                        if (mainGUI.cfPnum2.getText().equals("Employer")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.cfPnum2.getText());
                        }
                        if (mainGUI.cfPnum3.getText().equals("Employee Phone")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.cfPnum3.getText());
                        }
                        dos[2].writeUTF("" + mainGUI.cfPnum4.getText());
                        dos[2].writeUTF("" + mainGUI.cfPcom.getSelectedItem().toString());
                        dos[2].writeUTF("" + mainGUI.cfPnum5.getText());
                        dos[2].writeUTF("" + mainGUI.cfPnum6.getText());

                        dos[2].writeUTF("" + mainGUI.afPaddress1.getText());
                        if (mainGUI.afPaddress2.getText().equals("Address2")) {
                            dos[2].writeUTF("");
                        } else {
                            dos[2].writeUTF("" + mainGUI.afPaddress2.getText());
                        }
                        dos[2].writeUTF("" + mainGUI.afPaddress3.getText());
                        dos[2].writeUTF("" + mainGUI.afPaddress4.getSelectedItem().toString());
                        dos[2].writeUTF("" + mainGUI.afPaddress5.getText());

                        mainGUI.reset();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}

class ForgotPass extends JDialog {

    private Color background = new Color(41, 75, 110);
    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    public JTextField getName = new JTextField(),
            getSec1 = new JTextField(),
            getSec2 = new JTextField(),
            getEmail = new JTextField(),
            getRecode = new JTextField(),
            pass1 = new JTextField(),
            pass2 = new JTextField();
    public JPasswordField getPass1 = new JPasswordField(),
            getPass2 = new JPasswordField();
    public JLabel sec1L = new JLabel("Security question 1"),
            sec2L = new JLabel("Security question 2");
    public JButton recoverB = new JButton("Send Recovery Code"),
            changeB = new JButton("Change Password"),
            cancelB = new JButton("Cancel"),
            voidB = new JButton("Void");

    public ForgotPass() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        getContentPane().add(Main());
        getContentPane().add(voidB);
        getContentPane().add(cancelB);
        voidB.setPreferredSize(new Dimension(140, 30));
        cancelB.setPreferredSize(new Dimension(140, 30));

        voidB.setBackground(background.darker());
        voidB.setForeground(Color.WHITE);
        voidB.setFont(new Font("Arial", Font.BOLD, 15));

        cancelB.setBackground(background.darker());
        cancelB.setForeground(Color.WHITE);
        cancelB.setFont(new Font("Arial", Font.BOLD, 15));

        getContentPane().setBackground(background);
        getContentPane().requestFocus();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(310, 530);
        setTitle("Password Recovery");
        setLocationRelativeTo(null);
        setModal(true);
    }

    private JPanel Main() {
        Dimension dim2 = new Dimension(280, 30);
        EmptyBorder lb = new EmptyBorder(0, 0, 0, 0);
        Font fieldFont = new Font("Arial", Font.BOLD, 15);
        getName.setPreferredSize(dim2);
        sec1L.setPreferredSize(new Dimension(280, 20));
        getSec1.setPreferredSize(dim2);
        sec2L.setPreferredSize(new Dimension(280, 20));
        getSec2.setPreferredSize(dim2);
        getEmail.setPreferredSize(dim2);
        recoverB.setPreferredSize(dim2);
        getRecode.setPreferredSize(dim2);
        getPass1.setPreferredSize(dim2);
        getPass2.setPreferredSize(dim2);
        changeB.setPreferredSize(dim2);
        pass1.setPreferredSize(dim2);
        pass2.setPreferredSize(dim2);

        getPass1.setVisible(false);
        getPass2.setVisible(false);

        sec1L.setForeground(Color.white);
        sec2L.setForeground(Color.white);

        sec1L.setFont(new Font("Arial", Font.BOLD, 14));
        sec2L.setFont(new Font("Arial", Font.BOLD, 14));

        recoverB.setBackground(background.darker());
        recoverB.setForeground(Color.WHITE);
        recoverB.setFont(new Font("Arial", Font.BOLD, 15));

        changeB.setBackground(background.darker());
        changeB.setForeground(Color.WHITE);
        changeB.setFont(new Font("Arial", Font.BOLD, 15));

        getName.setBorder(lb);
        getSec1.setBorder(lb);
        getSec2.setBorder(lb);
        getEmail.setBorder(lb);
        getRecode.setBorder(lb);
        getPass1.setBorder(lb);
        getPass2.setBorder(lb);
        pass1.setBorder(lb);
        pass2.setBorder(lb);

        getName.setFont(fieldFont);
        getSec1.setFont(fieldFont);
        getSec2.setFont(fieldFont);
        getEmail.setFont(fieldFont);
        getRecode.setFont(fieldFont);
        getPass1.setFont(fieldFont);
        getPass2.setFont(fieldFont);
        pass1.setFont(fieldFont);
        pass2.setFont(fieldFont);

        getName.setForeground(Color.lightGray);
        getSec1.setForeground(Color.lightGray);
        getSec2.setForeground(Color.lightGray);
        getEmail.setForeground(Color.lightGray);
        getRecode.setForeground(Color.lightGray);
        getPass1.setForeground(Color.lightGray);
        getPass2.setForeground(Color.lightGray);
        pass1.setForeground(Color.lightGray);
        pass2.setForeground(Color.lightGray);

        getName.setHorizontalAlignment(JTextField.CENTER);
        getSec1.setHorizontalAlignment(JTextField.CENTER);
        getSec2.setHorizontalAlignment(JTextField.CENTER);
        getEmail.setHorizontalAlignment(JTextField.CENTER);
        getRecode.setHorizontalAlignment(JTextField.CENTER);
        getPass1.setHorizontalAlignment(JPasswordField.CENTER);
        getPass2.setHorizontalAlignment(JPasswordField.CENTER);
        pass1.setHorizontalAlignment(JTextField.CENTER);
        pass2.setHorizontalAlignment(JTextField.CENTER);

        panel.add(getName);
        panel.add(sec1L);
        panel.add(getSec1);
        panel.add(sec2L);
        panel.add(getSec2);
        panel.add(getEmail);
        panel.add(recoverB);
        panel.add(getRecode);
        panel.add(pass1);
        panel.add(getPass1);
        panel.add(pass2);
        panel.add(getPass2);
        panel.add(changeB);

        getName.setText("Enter username");
        getSec1.setText("Answer security question 1");
        getSec2.setText("Answer security question 2");
        getEmail.setText("Enter e-mail");
        getRecode.setText("Recovery code");
        pass1.setText("New password");
        pass2.setText("Confirm password");

        getName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getName.getText().equals("Enter username")) {
                    getName.setForeground(Color.black);
                    getName.setText("");
                } else {
                    getName.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getName.getText().equals("")) {
                    getName.setForeground(Color.lightGray);
                    getName.setText("Enter username");
                }
            }
        });
        getSec1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getSec1.getText().equals("Answer security question 1")) {
                    getSec1.setForeground(Color.black);
                    getSec1.setText("");
                } else {
                    getSec1.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getSec1.getText().equals("")) {
                    getSec1.setForeground(Color.lightGray);
                    getSec1.setText("Answer security question 1");
                }
            }
        });
        getSec2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getSec2.getText().equals("Answer security question 2")) {
                    getSec2.setForeground(Color.black);
                    getSec2.setText("");
                } else {
                    getSec2.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getSec2.getText().equals("")) {
                    getSec2.setForeground(Color.lightGray);
                    getSec2.setText("Answer security question 2");
                }
            }
        });
        getEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getEmail.getText().equals("Enter e-mail")) {
                    getEmail.setForeground(Color.black);
                    getEmail.setText("");
                } else {
                    getEmail.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getEmail.getText().equals("")) {
                    getEmail.setForeground(Color.lightGray);
                    getEmail.setText("Enter e-mail");
                }
            }
        });
        getRecode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getRecode.getText().equals("Recovery code")) {
                    getRecode.setForeground(Color.black);
                    getRecode.setText("");
                } else {
                    getRecode.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getRecode.getText().equals("")) {
                    getRecode.setForeground(Color.lightGray);
                    getRecode.setText("Recovery code");
                }
            }
        });
        pass1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pass1.setVisible(false);
                getPass1.setVisible(true);
                getPass1.requestFocus();

            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        getPass1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!new String(getPass1.getPassword()).equals("")) {
                    getPass1.selectAll();
                }
                getPass1.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(getPass1.getPassword()).equals("")) {
                    getPass1.setVisible(false);
                    pass1.setVisible(true);
                    pass1.setVisible(true);
                }
            }
        });
        pass2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pass2.setVisible(false);
                getPass2.setVisible(true);
                getPass2.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        getPass2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!new String(getPass2.getPassword()).equals("")) {
                    getPass2.selectAll();
                }
                getPass2.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(getPass2.getPassword()).equals("")) {
                    getPass2.setVisible(false);
                    pass2.setVisible(true);
                    pass2.setVisible(true);
                }
            }
        });

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setPreferredSize(new Dimension(310, 430));
        panel.setBackground(background.brighter());
        return panel;
    }
}
