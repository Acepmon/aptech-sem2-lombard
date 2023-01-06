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
import java.util.logging.Level;
import java.util.logging.Logger;

public class LombardServer {

    private static String user = "";
    private static String pas = "";
    
    public static void main(String[] args) {
        try {
            Boolean boo = false;
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            user = input.readUTF();
            pas = input.readUTF();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hockshop", "root", "");
            PreparedStatement stm = con.prepareCall("Select * from personalinformation");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (user.equals(rs.getString("username")) && pas.equals(rs.getString("password"))) {
                    output.writeUTF(user);
                    output.writeUTF(pas);
                    boo = true;
                }
                if (!boo) {
                    output.writeUTF("");
                    output.writeUTF("");
                }
            }
            socket.close();
        } catch (SQLException ex) {
            Logger.getLogger(LombardServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LombardServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("aldaa");
        }

    }
}