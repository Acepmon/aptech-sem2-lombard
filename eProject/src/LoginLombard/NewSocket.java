package LoginLombard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewSocket extends Thread {

    int length = 4;
    ServerSocket[] ss = new ServerSocket[length];
    Socket[] socket = new Socket[length];
    DataInputStream[] dis = new DataInputStream[length];
    DataOutputStream[] dos = new DataOutputStream[length];
    Thread[] mthread = new Thread[length];
    //This is useful --> ObjectOutputStream oos = new ObjectOutputStream(socket[0].getOutputStream());

    public NewSocket() {
        try {
            ss[0] = new ServerSocket(2121);
            ss[1] = new ServerSocket(2222);
            ss[2] = new ServerSocket(2323);
            ss[3] = new ServerSocket(2424);
            mthread[0] = new Thread(t2121());
            mthread[1] = new Thread(t2222());
            mthread[2] = new Thread(t2323());
            mthread[3] = new Thread(t2424());
            for (int i = 0; i < length; i++) {
                socket[i] = ss[i].accept();
                dis[i] = new DataInputStream(socket[i].getInputStream());
                dos[i] = new DataOutputStream(socket[i].getOutputStream());
            }

            mthread[0].start();
            mthread[1].start();
            mthread[2].start();
            mthread[3].start();
            //mthread[2].start();
        } catch (Exception ex) {
            //Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new NewSocket();
    }

    @Override
    public void run() {
    }

    //Main GUI 2aaa
    private Runnable t2424() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 3;
                    int contactid = 0;
                    String fname = dis[i].readUTF(),
                            lname = dis[i].readUTF(),
                            regno = dis[i].readUTF();

                    String nation = dis[i].readUTF(),
                            gend = dis[i].readUTF(),
                            eye = dis[i].readUTF(),
                            height = dis[i].readUTF(),
                            weight = dis[i].readUTF(),
                            hair = dis[i].readUTF(),
                            tattoos = dis[i].readUTF(),
                            notes = dis[i].readUTF(),
                            username = dis[i].readUTF(),
                            password = dis[i].readUTF();

                    String ques1 = dis[i].readUTF(),
                            ans1 = dis[i].readUTF(),
                            ques2 = dis[i].readUTF(),
                            ans2 = dis[i].readUTF();

                    String homep = dis[i].readUTF(),
                            cellp = dis[i].readUTF(),
                            email = dis[i].readUTF(),
                            emdob = dis[i].readUTF();

                    String addr1 = dis[i].readUTF(),
                            addr2 = dis[i].readUTF(),
                            city = dis[i].readUTF(),
                            state = dis[i].readUTF(),
                            zip = dis[i].readUTF();
                    System.out.println("" + fname);
                    System.out.println("" + lname);

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop?useUnicode=yes&characterEncoding=utf8", "root", "");
                    Statement stm = con.createStatement();
                    stm.executeUpdate("INSERT INTO `employee_contact` (`Contact_id`, `Home_Phone`, `Cell_Phone`, `E_mail`, `Date_Birth`, `Address_1`, `Address_2`, `City` , `State_name`, `Zip`) VALUES "
                            + "('" + 0 + "', '" + homep + "', '" + cellp + "', '" + email + "', '" + emdob + "', '" + addr1 + "', '" + addr2 + "','" + city + "','" + state + "','" + zip + "')");

                    //add employee
                    PreparedStatement stm2 = con.prepareCall("Select * from employee_contact ORDER BY Contact_id DESC LIMIT 1");
                    ResultSet rs = stm2.executeQuery();
                    while (rs.next()) {
                        contactid = rs.getInt("Contact_id");
                    }
                    Statement stm3 = con.createStatement();
                    stm3.executeUpdate("INSERT INTO `employee` (`Employee_id`, `Contact_id`, `Employee_firs"
                            + "tname`, `Employee_lastname`, `Employee_registernumber`, `Nation_name`, `Gender_type`, `Employee_height`,`Employee_weight` ,`Eye_color`,`Hair_color`, `Employee_tattoos`, `Employee_username`, `Employee_password`, `Employee_question1`,`Employee_question2` ,`Employee_answer1`, `Employee_answer2`) VALUES "
                            + "('" + 0 + "', '" + contactid + "','" + fname + "', '" + lname + "', '" + regno + "', '" + nation + "', '" + gend + "', '" + height + "','" + weight + "','" + eye + "','" + hair + "', '" + tattoos + "', '" + username + "', '" + password + "', '" + ques1 + "', '" + ques2 + "', '" + ans1 + "','" + ans2 + "')");

                } catch (Exception ex) {
                    Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    //Main GUI
    private Runnable t2323() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 2;
                    int contactid = 0;
                    String fname = dis[i].readUTF(),
                            lname = dis[i].readUTF(),
                            regno = dis[i].readUTF();

                    String nation = dis[i].readUTF(),
                            gend = dis[i].readUTF(),
                            height = dis[i].readUTF(),
                            weight = dis[i].readUTF(),
                            eye = dis[i].readUTF(),
                            hair = dis[i].readUTF(),
                            tattoos = dis[i].readUTF(),
                            notes = dis[i].readUTF();

                    String homep = dis[i].readUTF(),
                            emcom = dis[i].readUTF(),
                            emplp = dis[i].readUTF(),
                            cellp = dis[i].readUTF(),
                            career = dis[i].readUTF(),
                            email = dis[i].readUTF(),
                            emdob = dis[i].readUTF();

                    String addr1 = dis[i].readUTF(),
                            addr2 = dis[i].readUTF(),
                            city = dis[i].readUTF(),
                            state = dis[i].readUTF(),
                            zip = dis[i].readUTF();
                    System.out.println("" + fname);
                    System.out.println("" + lname);

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop?useUnicode=yes&characterEncoding=utf8", "root", "");
                    Statement stm = con.createStatement();
                    stm.executeUpdate("INSERT INTO `customer_contact` (`Contact_id`, `Home_Phone`, `Employer_company`, `Employee_phone`, `Cell_Phone`, `Career_name`, `E_mail`, `Date_Birth`, `Address_1`, `Address_2`, `City` , `State_name`, `Zip`) VALUES "
                            + "('" + 0 + "', '" + homep + "', '" + emcom + "', '" + emplp + "', '" + cellp + "', '" + career + "', '" + email + "', '" + emdob + "', '" + addr1 + "', '" + addr2 + "','" + city + "','" + state + "','" + zip + "')");

                    //add customer
                    PreparedStatement stm2 = con.prepareCall("Select * from customer ORDER BY Contact_id DESC LIMIT 1");
                    ResultSet rs = stm2.executeQuery();
                    while (rs.next()) {
                        contactid = rs.getInt("Contact_id");
                    }
                    Statement stm3 = con.createStatement();
                    stm3.executeUpdate("INSERT INTO `customer` (`Customer_id`, `Contact_id`, `Customer_firstname`,"
                            + " `Customer_lastname`, `Customer_registernumber`, `Nation_name`, `Gender_type`, `Customer_height`,`Customer_weight` ,`Eye_color`,`Hair_color`, `Customer_tattoos`, `Customer_note`) VALUES "
                            + "('" + 0 + "', '" + contactid + "','" + fname + "', '" + lname + "', '" + regno + "', '" + nation + "', '" + gend + "', '" + height + "','" + weight + "','" + eye + "','" + hair + "', '" + tattoos + "', '" + notes + "')");

                } catch (Exception ex) {
                    Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

    }

    //New Transaction
    private Runnable t2222() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 1;
                    if (dis[i].read() == 1) {
                        int cate = dis[i].read();
                        String type = dis[i].readUTF();
                        String make = dis[i].readUTF();
                        String mode = dis[i].readUTF();
                        String seno = dis[i].readUTF();
                        String note = dis[i].readUTF();
                        String rate = dis[i].readUTF();
                        String sate = dis[i].readUTF();
                        String eate = dis[i].readUTF();
                        String amou = dis[i].readUTF();

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop?useUnicode=yes&characterEncoding=utf8", "root", "");
                        
                        int custid = 0;
                        PreparedStatement stm4 = con.prepareCall("Select * from customer ORDER BY Customer_Id DESC LIMIT 1");
                        ResultSet rs4 = stm4.executeQuery();
                        while (rs4.next()) {
                            custid = rs4.getInt("Customer_id");
                        }

                        Statement stm3 = con.createStatement();
                        stm3.executeUpdate("INSERT INTO `ticket` (`Ticket_Id`, `Customer_id`, `Ticket_type`) VALUES "
                                + "('" + 0 + "', '" + custid + "', 'new')");

                        int ticketid = 0;
                        PreparedStatement stm2 = con.prepareCall("Select * from ticket");
                        ResultSet rs2 = stm2.executeQuery();
                        while (rs2.next()) {
                            ticketid = rs2.getInt("Ticket_id");
                        }

                        int cateid = 0;
                        PreparedStatement stm5 = con.prepareCall("Select * from category_all");
                        ResultSet rs3 = stm5.executeQuery();
                        while (rs3.next()) {
                            cateid = rs3.getInt("Category_id");
                        }

                        Statement stm = con.createStatement();
                        stm.executeUpdate("INSERT INTO `ticket_item` (`Ticket_item_id`, `Ticket_id`, `Category_id`, `Type`, `Make`, `Model`, `Serial_Number`, `Details`, `Loan_Rate`, `Start_Date`, `End_Date`, `amount` , `Ownership`) VALUES "
                                + "(0, '" + ticketid + "', '" + cateid + "', '" + type + "', '" + make + "', '" + mode + "', '" + seno + "', '" + note + "', '" + rate + "', '" + sate + "', '" + eate + "', '" + amou + "', 'lombard')");


                    } else {
                        int cate = dis[i].read();
                        String type = dis[i].readUTF();
                        String meta = dis[i].readUTF();
                        String kara = dis[i].readUTF();
                        String size = dis[i].readUTF();
                        String rate = dis[i].readUTF();
                        String deta = dis[i].readUTF();
                        String sate = dis[i].readUTF();
                        String eate = dis[i].readUTF();
                        String amou = dis[i].readUTF();

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop?useUnicode=yes&characterEncoding=utf8", "root", "");
                        Statement stm = con.createStatement();
                        stm.executeUpdate("INSERT INTO `Jewelry` (`Jewelry_Id`, `Style_id`, `Metal_Id`, `Karat`, `Size`, `Loan_Rate`, `Details`, `Start_Date`, `End_Date`, `Amount` , `Ownership`) VALUES "
                                + "('" + cate + "', '" + type + "', '" + meta + "', '" + kara + "', '" + size + "', '" + rate + "', '" + deta + "', '" + sate + "', '" + eate + "', '" + amou + "', 'lombard')");

                        int jewelid = 0;
                        PreparedStatement stm2 = con.prepareCall("Select * from Jewelry_Category ORDER BY Ticket_id DESC LIMIT 1");
                        ResultSet rs = stm2.executeQuery();
                        while (rs.next()) {
                            jewelid = rs.getInt("Jewelry_id");
                        }
                        Statement stm3 = con.createStatement();
                        stm3.executeUpdate("INSERT INTO `Ticket` (`Ticket_Id`, `Ticket_type`) VALUES ('" + 0 + "', 'new')");

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    //Log in
    private Runnable t2121() {
        return new Runnable() {
            @Override
            public void run() {
                while (!socket[0].isClosed()) {
                    try {
                        String username = dis[0].readUTF(),
                                password = dis[0].readUTF();
                        Boolean boo = false, mboo = false;
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawn_shop", "root", "");
                        PreparedStatement stm = con.prepareCall("Select * from admin");
                        ResultSet rs = stm.executeQuery();
                        PreparedStatement stm2 = con.prepareCall("Select * from employee");
                        ResultSet rs2 = stm2.executeQuery();
                       // PreparedStatement stm3 = con.prepareCall("Select * from employee_contact");
                       // ResultSet rs3 = stm3.executeQuery();
                       // PreparedStatement stm4 = con.prepareCall("Select * from ticket INNER JOIN");
                       // ResultSet rs4 = stm4.executeQuery();
                        while (rs.next()) {
                            if (username.equals(rs.getString("Admin_username")) && password.equals(rs.getString("Admin_password"))) {
                                dos[0].writeBoolean(true);
                                dos[0].writeUTF(rs.getString("Admin_firstname"));
                                dos[0].writeUTF(rs.getString("Admin_lastname"));
                                dos[0].writeUTF(rs.getString("Admin_registernumber"));
                                dos[0].writeUTF(rs.getString("Nation_name"));
                                dos[0].writeUTF(rs.getString("Cell_Phone"));
                                dos[0].writeUTF(rs.getString("Admin_height"));
                                dos[0].writeUTF(rs.getString("Admin_weight"));
                                dos[0].writeUTF(rs.getString("E_mail"));
                                dos[0].writeUTF(rs.getString("Address_1"));
                                dos[0].writeUTF(rs.getString("Admin_tattoos"));
                                dos[0].writeUTF(rs.getString("Admin_username"));
                                dos[0].writeUTF(rs.getString("Admin_password"));
                                dos[0].write(1);
                                mboo = true;
                            }
                        }
                        if (!mboo) {
                            while (rs2.next()) {
                                if (username.equals(rs2.getString("Employee_username")) && password.equals(rs2.getString("Employee_password"))) {
                                    dos[0].writeBoolean(true);
                                    dos[0].writeUTF(rs2.getString("Employee_firstname"));
                                    dos[0].writeUTF(rs2.getString("Employee_lastname"));
                                    dos[0].writeUTF(rs2.getString("Employee_registernumber"));
                                    dos[0].writeUTF(rs2.getString("Nation_name"));
                                   /*while (rs3.next()) {
                                        dos[0].writeUTF(rs3.getString("Cell_Phone"));
                                    }*/
                                   // dos[0].writeUTF(rs2.getString("Employee_height"));
                                   // dos[0].writeUTF(rs2.getString("Employee_weight"));
                                    /*while (rs3.next()) {
                                        dos[0].writeUTF(rs3.getString("E_mail"));
                                        dos[0].writeUTF(rs3.getString("Address_1"));
                                    }*/
                                   // dos[0].writeUTF(rs2.getString("Employee_tattoos"));
                                   // dos[0].writeUTF(rs2.getString("Employee_username"));
                                   // dos[0].writeUTF(rs2.getString("Employee_password"));
                                   // dos[0].write(2);
                                    boo = true;
                                }
                            }
                        }
                        if (!boo) {
                            dos[0].writeBoolean(false);
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                            dos[0].writeUTF("");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(NewSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }
}
