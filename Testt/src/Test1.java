import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Test1 {

   public Test1() {
       String[] defaultCols = {"Id", "Username", "Password", "email","Name","Age","Gender"};
       DefaultTableModel model = new DefaultTableModel(defaultCols, 0);
       JTable table = new JTable(model);

       JButton button = createButton(table);
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add( new JScrollPane(table));
       frame.add(button, BorderLayout.SOUTH);
       frame.pack();
       frame.setLocationByPlatform(true);
       frame.setVisible(true);
   }

   private JButton createButton(final JTable table) {
       JButton button = new JButton("Get File");
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               JFileChooser chooser = new JFileChooser();
               int result = chooser.showOpenDialog(table);
               if (result == JFileChooser.APPROVE_OPTION) {
                   File file = chooser.getSelectedFile();
                   DefaultTableModel model = createModel(file);
                   table.setModel(model);
               }
           }
       });
       return button;
   }

   private DefaultTableModel createModel(File file) {
       DefaultTableModel model = null;

       try {
           BufferedReader txtReader = new BufferedReader(
                   new FileReader(file));
           String header = txtReader.readLine();
           model = new DefaultTableModel(header.split("\\s+"), 0);
           String line;
           while ((line = txtReader.readLine()) != null) {
               model.addRow(line.split("\\s+"));
           }
       } catch (IOException ex) {
           ex.printStackTrace();
       }

       return model;
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               new Test1();
           }
       });
   }
} 