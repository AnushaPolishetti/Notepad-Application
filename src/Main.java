import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener{
    // to add the functionalities

    //text area declaration
    JFrame f;
    JTextArea t;
   //constructor
    note(){
        // initializing the frame
        f = new JFrame("Notepad");
        // intializing the textarea
        t = new JTextArea();

        // create a menu bar
        JMenuBar menubar = new JMenuBar();

        // creating the file menu
        JMenu file = new JMenu("File");
        // creating options for file
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");

        //adding actionlisteners to each of the options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);


        // adding options to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);


        // creating the edit menu
        JMenu edit = new JMenu("Edit");
        // creating options for file
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        //adding actionlisteners to each of the options
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);



        // adding options to the file menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);



        //creating the close button
        JMenuItem close = new JMenuItem("Close");

        close.addActionListener(this);
        // adding menus to menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        f.setJMenuBar(menubar);
        f.add(t);
        f.setSize(1280,720);
        f.show();

    }

    public void actionPerformed(ActionEvent e){
           String s = e.getActionCommand();

           switch(s){
               case "New":
                   t.setText("");
                   break;
               case "Save":
                   JFileChooser j = new JFileChooser("C:");
                   int r = j.showSaveDialog(null);
                   if(r==0){
                   File fi = new File(j.getSelectedFile().getAbsolutePath());
                   try{
                       FileWriter fw = new FileWriter(fi);
                       BufferedWriter bw = new BufferedWriter(fw);
                       bw.write(t.getText());

                       bw.flush();
                       bw.close();
                   }
                   catch(IOException ex){
                       throw new RuntimeException(ex);
                   }
                   }
                   else{
                       JOptionPane.showMessageDialog(f,"the user has cancelled the operation save");
                   }
                   break;
               case "Open":
                   JFileChooser ji = new JFileChooser("C:");
                   int ri = ji.showOpenDialog(null);
                   if(ri==0){
                       File fi = new File(ji.getSelectedFile().getAbsolutePath());
                       try{
                           FileReader fw = new FileReader(fi);
                           BufferedReader br = new BufferedReader(fw);
                           String s1 = "", s2 = "";
                           // first line stored in s1
                           s1 = br.readLine();
                           while((s2= br.readLine())!=null){
                               s1 = s1+"\n"+s2;

                           }
                           t.setText(s1);

                       }
                       catch(IOException ex){
                             throw new RuntimeException(ex);
                       }
                   }
                   break;
               case "Print":
                   try{
                       t.print();
                   }
                   catch(PrinterException ex){
                       throw new RuntimeException(ex);
                   }
                   break;
               case "Cut":
                   t.cut();
                   break;
               case "Copy":
                   t.copy();
                   break;
               case "Paste":
                   t.paste();
                   break;
               case "Close":
                   f.setVisible(false);
                   break;

           }
    }

    public static void main(String args[]){
        note n = new note();
    }
}