package LoginLombard;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ExampleSearch extends JFrame{
    
    JPanel panel = new JPanel(new FlowLayout());
    String[] cbmFor = {"Customer", "Inventory", "Transaction"};
    JComboBox cbFor = new JComboBox(cbmFor);
    String[] cbmBy = {"Name", "address", "phone", "License", "Date entered"};
    JComboBox cbBy = new JComboBox(cbmBy);
    JButton sb = new JButton("Search");
    JTextField tf = new JTextField();
    
    public ExampleSearch(){
        getContentPane().add(panel);
        
        cbFor.setPreferredSize(new Dimension(150, 25));
        cbBy.setPreferredSize(new Dimension(150, 25));
        tf.setPreferredSize(new Dimension(150, 25));
        
        tf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        panel.add(cbFor);
        panel.add(cbBy);
        panel.add(tf);
        
        setSize(500, 500);
        setTitle("Search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ExampleSearch().setVisible(true);
    }
}
