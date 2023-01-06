package LoginLombard;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Settings extends JFrame {

    /**
     * ************************************************************************
     */
    //Container components
    private Color background = new Color(41, 75, 110);
    private JFrame frame = this;
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
    private int fontCount = 0;
    private JLabel titleL = new JLabel("Settings");
    private Border lb = BorderFactory.createEmptyBorder();
    private JPanel themeP = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private JPanel userP = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JPanel fontP = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
    private JLabel themeL = new JLabel("Display");
    private JLabel userL = new JLabel("User Settings");
    private JLabel fontL = new JLabel("Font");
    private DefaultTableModel themeModel = new DefaultTableModel();
    private JTable themeTable = new JTable(themeModel);
    private JScrollPane themeScroll = new JScrollPane(themeTable);
    private DefaultTableModel userModel = new DefaultTableModel();
    private JButton defaultB = new JButton("Default Settings");
    private JButton applyB = new JButton("Apply new Settings");
    
     //User Settings 
    private JLabel userFirstL = new JLabel("First name: ");
    private JLabel userLastL = new JLabel("Last name: ");
    private JLabel userRegisL = new JLabel("Register number: ");
    private JLabel userNationL = new JLabel("Nation name: ");
    private JLabel userPhoneL = new JLabel("Cell Phone: ");
    private JLabel userHeightL = new JLabel("Height: ");
    private JLabel userWeightL = new JLabel("Weight: ");
    private JLabel userEmailL = new JLabel("E-mail: ");
    private JLabel userAddressL = new JLabel("Address 1: ");
    private JLabel userTattooL = new JLabel("Tattoos: ");
    public JTextField userFirstF = new JTextField();
    public JTextField userLastF = new JTextField();
    public JTextField userRegisF = new JTextField();
    public JTextField userNationF = new JTextField();
    public JTextField userCellPhoneF = new JTextField();
    public JTextField userHeightF = new JTextField();
    public JTextField userWeightF = new JTextField();
    public JTextField userEmailF = new JTextField();
    public JTextField userAddressF = new JTextField();
    public JTextField userTattooF = new JTextField();
    public JButton userPassB = new JButton("Change Password?");
    private JLabel userCurrentPassL = new JLabel("Current password");
    private JLabel userNewPass1L = new JLabel("New Password");
    private JLabel userNewPass2L = new JLabel("Re-type Password");
    public JPasswordField userCurrentPassF = new JPasswordField();
    public JPasswordField userNewPass1F = new JPasswordField();
    public JPasswordField userNewPass2F = new JPasswordField();
    public JButton userPassChangeB = new JButton("Change Password");
    private JLabel[] userChange = new JLabel[10];
    private Boolean[] userBool = new Boolean[10];
    private JLabel userGap1 = new JLabel();
    private JLabel userGap2 = new JLabel();
    private JLabel userGap3 = new JLabel();
    private JLabel userGap4 = new JLabel();
    private Boolean userBool2 = new Boolean(false);
    //******************************************************
    
    private GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private Font[] fonts = e.getAllFonts();
    private String[] fontStr;
    private String[] fontStyles = {"Regular", "Bold", "Italic"};
    private String[] fontSizes = {"13", "15", "18", "21"};
    private String[] fontLanguages = {"English", "Монгол", "Русский", "Français", "Deutsch", "한국의", "中国的", "Italiano", "العربية"};
    private JLabel fFontL = new JLabel("Font:");
    private JLabel fFontStyleL = new JLabel("Font Style: ");
    private JLabel fSizeL = new JLabel("Size: ");
    private JLabel fLanguageL = new JLabel("Language: ");
    private JLabel fSampleL = new JLabel("Sample: ");
    private JLabel[] fgap = new JLabel[10];
    private JComboBox fFontCB;
    private JComboBox fFontStyleCB = new JComboBox(fontStyles);
    private JComboBox fSizeCB = new JComboBox(fontSizes);
    private JComboBox fLanguageCB = new JComboBox(fontLanguages);
    private JFormattedTextField fSampleT = new JFormattedTextField("Sample");
    private int a = 0;
    private int i;

    /**
     * ************************************************************************
     */
    public Settings() {
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

    private JPanel Theme() {

        return themeP;
    }

    private JPanel User() {
        //userP
        Dimension lDim = new Dimension(150, 25);
        Dimension fDim = new Dimension(200, 25);
        Dimension cDim = new Dimension(450, 25);

        for (i = 0; i < userChange.length; i++) {
            userChange[i] = new JLabel("Change");
            userChange[i].setHorizontalAlignment(JLabel.LEFT);
            userChange[i].setPreferredSize(cDim);
            userChange[i].setForeground(Color.lightGray);
            userBool[i] = new Boolean(false);
        }

        userChange[0].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[0] == false) {
                    userFirstF.setEnabled(true);
                    userChange[0].setText("Cancel");
                    userBool[0] = true;
                } else {
                    userFirstF.setEnabled(false);
                    userFirstF.setText("");
                    userChange[0].setText("Change");
                    userBool[0] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[0].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[0].setForeground(Color.lightGray);
            }
        });
        userChange[1].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[1] == false) {
                    userLastF.setEnabled(true);
                    userChange[1].setText("Cancel");
                    userBool[1] = true;
                } else {
                    userLastF.setEnabled(false);
                    userLastF.setText("");
                    userChange[1].setText("Change");
                    userBool[1] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[1].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[1].setForeground(Color.lightGray);
            }
        });
        userChange[2].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[2] == false) {
                    userRegisF.setEnabled(true);
                    userChange[2].setText("Cancel");
                    userBool[2] = true;
                } else {
                    userRegisF.setEnabled(false);
                    userRegisF.setText("");
                    userChange[2].setText("Change");
                    userBool[2] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[2].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[2].setForeground(Color.lightGray);
            }
        });
        userChange[3].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[3] == false) {
                    userNationF.setEnabled(true);
                    userChange[3].setText("Cancel");
                    userBool[3] = true;
                } else {
                    userNationF.setEnabled(false);
                    userNationF.setText("");
                    userChange[3].setText("Change");
                    userBool[3] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[3].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[3].setForeground(Color.lightGray);
            }
        });
        userChange[4].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[4] == false) {
                    userCellPhoneF.setEnabled(true);
                    userChange[4].setText("Cancel");
                    userBool[4] = true;
                } else {
                    userCellPhoneF.setEnabled(false);
                    userChange[4].setText("Change");
                    userBool[4] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[4].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[4].setForeground(Color.lightGray);
            }
        });
        userChange[5].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[5] == false) {
                    userHeightF.setEnabled(true);
                    userChange[5].setText("Cancel");
                    userBool[5] = true;
                } else {
                    userHeightF.setEnabled(false);
                    userHeightF.setText("");
                    userChange[5].setText("Change");
                    userBool[5] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[5].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[5].setForeground(Color.lightGray);
            }
        });
        userChange[6].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[6] == false) {
                    userWeightF.setEnabled(true);
                    userChange[6].setText("Cancel");
                    userBool[6] = true;
                } else {
                    userWeightF.setEnabled(false);
                    userWeightF.setText("");
                    userChange[6].setText("Change");
                    userBool[6] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[6].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[6].setForeground(Color.lightGray);
            }
        });
        userChange[7].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[7] == false) {
                    userEmailF.setEnabled(true);
                    userChange[7].setText("Cancel");
                    userBool[7] = true;
                } else {
                    userEmailF.setEnabled(false);
                    userChange[7].setText("Change");
                    userBool[7] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[7].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[7].setForeground(Color.lightGray);
            }
        });
        userChange[8].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[8] == false) {
                    userAddressF.setEnabled(true);
                    userChange[8].setText("Cancel");
                    userBool[8] = true;
                } else {
                    userAddressF.setEnabled(false);
                    userChange[8].setText("Change");
                    userBool[8] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[8].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[8].setForeground(Color.lightGray);
            }
        });
        userChange[9].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (userBool[9] == false) {
                    userTattooF.setEnabled(true);
                    userChange[9].setText("Cancel");
                    userBool[9] = true;
                } else {
                    userTattooF.setEnabled(false);
                    userTattooF.setText("");
                    userChange[9].setText("Change");
                    userBool[9] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                userChange[9].setForeground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                userChange[9].setForeground(Color.lightGray);
            }
        });

        userCurrentPassF.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                int key = ke.getKeyCode();
                String text = new String(userCurrentPassF.getPassword());
                if (!text.equals("")) {
                    userNewPass1F.setEnabled(true);
                }
                if (key == KeyEvent.VK_BACK_SPACE) {
                    if (userCurrentPassF.getCaretPosition() == 1) {
                        userNewPass1F.setEnabled(false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        userNewPass1F.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                int key = ke.getKeyCode();
                String text = new String(userNewPass1F.getPassword());
                if (!text.equals("")) {
                    userNewPass2F.setEnabled(true);
                }
                if (key == KeyEvent.VK_BACK_SPACE) {
                    if (userNewPass1F.getCaretPosition() == 1) {
                        userNewPass2F.setEnabled(false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        userPassB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (userBool2 == false) {
                    for (int a = 0; a < userChange.length; a++) {
                        userChange[a].setVisible(false);
                    }
                    userPassB.setText("Cancel");

                    userFirstL.setVisible(false);
                    userLastL.setVisible(false);
                    userRegisL.setVisible(false);
                    userNationL.setVisible(false);
                    userPhoneL.setVisible(false);
                    userHeightL.setVisible(false);
                    userWeightL.setVisible(false);
                    userEmailL.setVisible(false);
                    userAddressL.setVisible(false);
                    userTattooL.setVisible(false);

                    userFirstF.setVisible(false);
                    userLastF.setVisible(false);
                    userRegisF.setVisible(false);
                    userNationF.setVisible(false);
                    userCellPhoneF.setVisible(false);
                    userHeightF.setVisible(false);
                    userWeightF.setVisible(false);
                    userEmailF.setVisible(false);
                    userAddressF.setVisible(false);
                    userTattooF.setVisible(false);

                    userCurrentPassL.setVisible(true);
                    userCurrentPassF.setVisible(true);
                    userNewPass1L.setVisible(true);
                    userNewPass1F.setVisible(true);
                    userNewPass2L.setVisible(true);
                    userNewPass2F.setVisible(true);
                    userPassChangeB.setVisible(true);

                    userGap1.setVisible(true);
                    userGap2.setVisible(true);
                    userGap3.setVisible(true);
                    userBool2 = true;

                    userNewPass1F.setEnabled(false);
                    userNewPass2F.setEnabled(false);
                } else {
                    String txt1 = new String(userCurrentPassF.getPassword());
                    String txt2 = new String(userNewPass1F.getPassword());
                    String txt3 = new String(userNewPass2F.getPassword());
                    if (txt3.equals("")) {
                        if (txt2.equals("")) {
                            if (txt1.equals("")) {

                                for (int a = 0; a < userChange.length; a++) {
                                    userChange[a].setVisible(true);
                                }
                                userPassB.setText("Change Password?");

                                userFirstL.setVisible(true);
                                userLastL.setVisible(true);
                                userRegisL.setVisible(true);
                                userNationL.setVisible(true);
                                userPhoneL.setVisible(true);
                                userHeightL.setVisible(true);
                                userWeightL.setVisible(true);
                                userEmailL.setVisible(true);
                                userAddressL.setVisible(true);
                                userTattooL.setVisible(true);

                                userFirstF.setVisible(true);
                                userLastF.setVisible(true);
                                userRegisF.setVisible(true);
                                userNationF.setVisible(true);
                                userCellPhoneF.setVisible(true);
                                userHeightF.setVisible(true);
                                userWeightF.setVisible(true);
                                userEmailF.setVisible(true);
                                userAddressF.setVisible(true);
                                userTattooF.setVisible(true);

                                userCurrentPassL.setVisible(false);
                                userCurrentPassF.setVisible(false);
                                userNewPass1L.setVisible(false);
                                userNewPass1F.setVisible(false);
                                userNewPass2L.setVisible(false);
                                userNewPass2F.setVisible(false);
                                userPassChangeB.setVisible(false);

                                userGap1.setVisible(false);
                                userGap2.setVisible(false);
                                userGap3.setVisible(false);

                                userCurrentPassF.setText("");
                                userNewPass1F.setText("");
                                userNewPass2F.setText("");

                                userNewPass1F.setEnabled(false);
                                userNewPass1F.setEnabled(false);

                                userBool2 = false;
                            } else {
                                JOptionPane.showConfirmDialog(rootPane, "Are you sure that you want to cancel?", "Do you?", 0);
                            }
                        } else {
                            JOptionPane.showConfirmDialog(rootPane, "Are you sure that you want to cancel?", "Do you?", 0);
                        }
                    } else {
                        JOptionPane.showConfirmDialog(rootPane, "Are you sure want you want to cancel?", "Do you?", 0);
                    }
                }
                themeL.setForeground(Color.gray);
                fontL.setForeground(Color.gray);
            }
        });

        userFirstL.setForeground(Color.white);
        userLastL.setForeground(Color.white);
        userRegisL.setForeground(Color.white);
        userNationL.setForeground(Color.white);
        userPhoneL.setForeground(Color.white);
        userHeightL.setForeground(Color.white);
        userWeightL.setForeground(Color.white);
        userEmailL.setForeground(Color.white);
        userAddressL.setForeground(Color.white);
        userTattooL.setForeground(Color.white);

        userCurrentPassL.setForeground(Color.white);
        userNewPass1L.setForeground(Color.white);
        userNewPass2L.setForeground(Color.white);

        userFirstL.setPreferredSize(lDim);
        userLastL.setPreferredSize(lDim);
        userRegisL.setPreferredSize(lDim);
        userNationL.setPreferredSize(lDim);
        userPhoneL.setPreferredSize(lDim);
        userHeightL.setPreferredSize(lDim);
        userWeightL.setPreferredSize(lDim);
        userEmailL.setPreferredSize(lDim);
        userAddressL.setPreferredSize(lDim);
        userTattooL.setPreferredSize(lDim);

        userGap1.setPreferredSize(cDim);
        userGap2.setPreferredSize(cDim);
        userGap3.setPreferredSize(cDim);

        userCurrentPassL.setPreferredSize(lDim);
        userNewPass1L.setPreferredSize(lDim);
        userNewPass2L.setPreferredSize(lDim);

        userFirstL.setFont(new Font("Arial", Font.BOLD, 13));
        userLastL.setFont(new Font("Arial", Font.BOLD, 13));
        userRegisL.setFont(new Font("Arial", Font.BOLD, 13));
        userNationL.setFont(new Font("Arial", Font.BOLD, 13));
        userPhoneL.setFont(new Font("Arial", Font.BOLD, 13));
        userHeightL.setFont(new Font("Arial", Font.BOLD, 13));
        userWeightL.setFont(new Font("Arial", Font.BOLD, 13));
        userEmailL.setFont(new Font("Arial", Font.BOLD, 13));
        userAddressL.setFont(new Font("Arial", Font.BOLD, 13));
        userTattooL.setFont(new Font("Arial", Font.BOLD, 13));

        userCurrentPassL.setFont(new Font("Arial", Font.BOLD, 13));
        userNewPass1L.setFont(new Font("Arial", Font.BOLD, 13));
        userNewPass2L.setFont(new Font("Arial", Font.BOLD, 13));

        userPassB.setBackground(background.darker());
        userPassB.setForeground(Color.WHITE);
        userPassB.setFont(new Font("Arial", Font.BOLD, 15));

        userPassChangeB.setBackground(background.darker());
        userPassChangeB.setForeground(Color.WHITE);
        userPassChangeB.setFont(new Font("Arial", Font.BOLD, 15));

        userFirstF.setPreferredSize(fDim);
        userLastF.setPreferredSize(fDim);
        userRegisF.setPreferredSize(fDim);
        userNationF.setPreferredSize(fDim);
        userCellPhoneF.setPreferredSize(fDim);
        userHeightF.setPreferredSize(fDim);
        userWeightF.setPreferredSize(fDim);
        userEmailF.setPreferredSize(fDim);
        userAddressF.setPreferredSize(fDim);
        userTattooF.setPreferredSize(fDim);

        userCurrentPassF.setPreferredSize(fDim);
        userNewPass1F.setPreferredSize(fDim);
        userNewPass2F.setPreferredSize(fDim);

        userFirstF.setEnabled(false);
        userLastF.setEnabled(false);
        userRegisF.setEnabled(false);
        userNationF.setEnabled(false);
        userCellPhoneF.setEnabled(false);
        userHeightF.setEnabled(false);
        userWeightF.setEnabled(false);
        userEmailF.setEnabled(false);
        userAddressF.setEnabled(false);
        userTattooF.setEnabled(false);

        userP.add(userFirstL);
        userP.add(userFirstF);
        userP.add(userChange[0]);
        userP.add(userLastL);
        userP.add(userLastF);
        userP.add(userChange[1]);
        userP.add(userRegisL);
        userP.add(userRegisF);
        userP.add(userChange[2]);
        userP.add(userNationL);
        userP.add(userNationF);
        userP.add(userChange[3]);
        userP.add(userPhoneL);
        userP.add(userCellPhoneF);
        userP.add(userChange[4]);
        userP.add(userHeightL);
        userP.add(userHeightF);
        userP.add(userChange[5]);
        userP.add(userWeightL);
        userP.add(userWeightF);
        userP.add(userChange[6]);
        userP.add(userEmailL);
        userP.add(userEmailF);
        userP.add(userChange[7]);
        userP.add(userAddressL);
        userP.add(userAddressF);
        userP.add(userChange[8]);
        userP.add(userTattooL);
        userP.add(userTattooF);
        userP.add(userChange[9]);
        userP.add(userChange[9]);
        userP.add(userCurrentPassL);
        userP.add(userCurrentPassF);
        userP.add(userGap1);
        userP.add(userNewPass1L);
        userP.add(userNewPass1F);
        userP.add(userGap2);
        userP.add(userNewPass2L);
        userP.add(userNewPass2F);
        userP.add(userGap3);
        userP.add(userPassChangeB);
        userP.add(userPassB);

        userCurrentPassL.setVisible(false);
        userCurrentPassF.setVisible(false);
        userNewPass1L.setVisible(false);
        userNewPass1F.setVisible(false);
        userNewPass2L.setVisible(false);
        userNewPass2F.setVisible(false);
        userPassChangeB.setVisible(false);
        userGap1.setVisible(false);
        userGap2.setVisible(false);
        userGap3.setVisible(false);

        return userP;
    }

    private JPanel Font() {
        Dimension dim = new Dimension(250, 30);
        Dimension dimL = new Dimension(80, 30);
        Dimension dimgap = new Dimension(400, 30);
        for (int i=0; i<=e.getAllFonts().length; i++) {
            fontCount = i;
        }
        fontStr = new String[fontCount];
        for (Font f : fonts) {
            fontStr[a] = f.getFontName();
            a++;
        }
        fFontCB = new JComboBox(fontStr);

        for (int i = 0; i < fgap.length; i++) {
            fgap[i] = new JLabel();
            fgap[i].setPreferredSize(dimgap);
        }

        fSampleT.setHorizontalAlignment(JTextField.CENTER);
        fSampleT.setEditable(false);

        fFontL.setFont(new Font("Arial", Font.BOLD, 13));
        fFontStyleL.setFont(new Font("Arial", Font.BOLD, 13));
        fSizeL.setFont(new Font("Arial", Font.BOLD, 13));
        fLanguageL.setFont(new Font("Arial", Font.BOLD, 13));
        fSampleL.setFont(new Font("Arial", Font.BOLD, 13));

        fFontL.setForeground(Color.white);
        fFontStyleL.setForeground(Color.white);
        fSizeL.setForeground(Color.white);
        fLanguageL.setForeground(Color.white);
        fSampleL.setForeground(Color.white);

        fFontL.setPreferredSize(dimL);
        fFontCB.setPreferredSize(dim);
        fFontStyleL.setPreferredSize(dimL);
        fFontStyleCB.setPreferredSize(dim);
        fSizeL.setPreferredSize(dimL);
        fSizeCB.setPreferredSize(dim);
        fLanguageL.setPreferredSize(dimL);
        fLanguageCB.setPreferredSize(dim);
        fSampleL.setPreferredSize(dimL);
        fSampleT.setPreferredSize(new Dimension(250, 100));

        fontP.add(fFontL);
        fontP.add(fFontCB);
        fontP.add(fgap[0]);
        fontP.add(fFontStyleL);
        fontP.add(fFontStyleCB);
        fontP.add(fgap[1]);
        fontP.add(fSizeL);
        fontP.add(fSizeCB);
        fontP.add(fgap[2]);
        fontP.add(fLanguageL);
        fontP.add(fLanguageCB);
        fontP.add(fgap[3]);
        fontP.add(fSampleL);
        fontP.add(fSampleT);

        fFontCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ChangeSample();
            }
        });
        fFontStyleCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ChangeSample();
            }
        });
        fSizeCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ChangeSample();
            }
        });
        fLanguageCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ChangeSample();
            }
        });

        return fontP;
    }
    private void ChangeSample() {
        String font = (String) fFontCB.getSelectedItem();
        int style = 0;
        int size = Integer.valueOf(fSizeCB.getSelectedItem().toString());
        switch (fFontStyleCB.getSelectedIndex()) {
            case 0:
                style = 0;
                break;
            case 1:
                style = 1;
                break;
            case 2:
                style = 2;
                break;
        }
        //"English", "Монгол", "Русский", "Français", "Deutsch", "한국의", "中国的", "Italiano", "العربية"
        switch (fLanguageCB.getSelectedIndex()) {
            case 0:
                fSampleT.setText("Sample");
                break;
            case 1:
                fSampleT.setText("Русский");
                break;
            case 2:
                fSampleT.setText("Образец");
                break;
            case 3:
                fSampleT.setText("échantillon");
                break;
            case 4:
                fSampleT.setText("Probe");
                break;
            case 5:
                fSampleT.setText("견본");
                break;
            case 6:
                fSampleT.setText("样品");
                break;
            case 7:
                fSampleT.setText("campione");
                break;
            case 8:
                fSampleT.setText("عينة");
                break;
        }
        fSampleT.setFont(new Font(font, style, size));
    }

    private JPanel CenterPanel() {
        TitledBorder tbTheme = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbTheme.setTitleColor(Color.white);
        tbTheme.setTitle("Display");
        tbTheme.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tbUser = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbUser.setTitleColor(Color.white);
        tbUser.setTitle("             User Settings");
        tbUser.setTitleFont(new Font("Arial", Font.BOLD, 13));
        TitledBorder tbLanguage = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2));
        tbLanguage.setTitleColor(Color.white);
        tbLanguage.setTitle("                                    Font");
        tbLanguage.setTitleFont(new Font("Arial", Font.BOLD, 13));
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

        themeL.setBounds(18, 75, 50, 25);
        userL.setBounds(70, 75, 90, 25);
        fontL.setBounds(162, 75, 80, 25);

        themeP.setBounds(10, 80, 870, 500);
        userP.setBounds(10, 80, 870, 500);
        fontP.setBounds(10, 80, 870, 500);

        defaultB.setBounds(530, 585, 150, 40);
        applyB.setBounds(700, 585, 180, 40);

        themeP.add(themeScroll);

        themeL.setForeground(Color.gray);
        userL.setForeground(Color.gray);
        fontL.setForeground(Color.gray);

        themeL.setFont(new Font("Arial", Font.BOLD, 13));
        userL.setFont(new Font("Arial", Font.BOLD, 13));
        fontL.setFont(new Font("Arial", Font.BOLD, 13));

        themeL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                themeL.setVisible(false);
                themeP.setVisible(true);
                userP.setVisible(false);
                fontP.setVisible(false);
                userL.setVisible(true);
                fontL.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                themeL.setForeground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                themeL.setForeground(Color.gray);
            }
        });

        userL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userL.setVisible(false);
                userP.setVisible(true);
                themeP.setVisible(false);
                fontP.setVisible(false);
                themeL.setVisible(true);
                fontL.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                userL.setForeground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userL.setForeground(Color.gray);
            }
        });

        fontL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fontL.setVisible(false);
                fontP.setVisible(true);
                themeP.setVisible(false);
                userP.setVisible(false);
                themeL.setVisible(true);
                userL.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fontL.setForeground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fontL.setForeground(Color.gray);
            }
        });

        themeScroll.setPreferredSize(new Dimension(850, 450));

        themeP.setBackground(background);
        userP.setBackground(background);
        fontP.setBackground(background);

        themeP.setBorder(tbTheme);
        userP.setBorder(tbUser);
        fontP.setBorder(tbLanguage);

        defaultB.setBackground(background.darker());
        defaultB.setForeground(Color.WHITE);
        defaultB.setFont(new Font("Arial", Font.BOLD, 15));

        applyB.setBackground(background.darker());
        applyB.setForeground(Color.WHITE);
        applyB.setFont(new Font("Arial", Font.BOLD, 15));

        centerP.add(userL);
        centerP.add(fontL);
        centerP.add(themeL);
        centerP.add(Theme());
        centerP.add(User());
        centerP.add(Font());
        centerP.add(defaultB);
        centerP.add(applyB);

        themeL.setVisible(false);
        themeP.setVisible(true);
        userP.setVisible(false);
        fontP.setVisible(false);

        return centerP;
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