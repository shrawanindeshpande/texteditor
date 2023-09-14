import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaration
    JFrame frame;
    JMenuBar jmb;
    JMenu file;
    JMenu edit;
    JMenuItem newf,open,save;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea jta;

    TextEditor(){
        //frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame=new JFrame("Text Editor");
        //initialize menu bar
        jmb=new JMenuBar();
        //initialize test area
        jta=new JTextArea();
        //create content panel
        //initialize file menu items;
        newf=new JMenuItem("New");
        open=new JMenuItem("Open");
        save=new JMenuItem("Save");
        //initailize file menu
        file=new JMenu("File");
        //add ,enu items to file menu
        file.add(newf);
        file.add(open);
        file.add(save);
        //add action listener to file menu items
        newf.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        //Intialize edit menuitems
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //iniyialize edit menu
        edit=new JMenu("Edit");
        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //add menu to menubar
        jmb.add(file);
        jmb.add(edit);
        //set menu to frame
        frame.setJMenuBar(jmb);
        //add test area to frame
        //frame.add(jta);
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area
        panel.add(jta,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane sp=new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(sp);
        frame.add(panel);
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    public static void main(String[] args) {
        TextEditor te=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==newf){
            //perform new file operation
            TextEditor te1=new TextEditor();
        }
        if (e.getSource()==open){
            //perform open file operation
            JFileChooser filechooser=new JFileChooser("C:");
            int chooseOption=filechooser.showOpenDialog(null);
            //if we have clickes open button
            if (chooseOption==JFileChooser.APPROVE_OPTION){
                File file=filechooser.getSelectedFile();
                //get the path of selected file
                String filepath=file.getPath();
                try{
                    //initialize file reader
                    FileReader fr=new FileReader(filepath);
                    //initialize bufferedreader
                    BufferedReader br=new BufferedReader(fr);
                    String intermediate="",output="";
                    //read contents of file line by line
                    while((intermediate= br.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    jta.setText(output);
                }
                catch(FileNotFoundException fne){
                    fne.printStackTrace();
                }
                catch(IOException ioexe){
                    ioexe.printStackTrace();
                }
            }
        }
        if (e.getSource()==save){
            //perform save file operation
            JFileChooser fc=new JFileChooser("C:");
            int chooseOption=fc.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory and file name
                File file=new File(fc.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize file writer
                    FileWriter fw=new FileWriter(file);
                    //initialize buffered writer
                    BufferedWriter bw=new BufferedWriter(fw);
                    //Write contents of text area to file
                    jta.write((bw));
                    bw.close();
                }
                catch(IOException ioexe){
                    ioexe.printStackTrace();
                }
            }
        }
        if (e.getSource()==cut){
            //perform cut operation
            jta.cut();
        }
        if (e.getSource()==copy){
            //perform cut operation
            jta.copy();
        }
        if (e.getSource()==paste){
            //perform paste operation
            jta.paste();
        }
        if (e.getSource()==selectAll){
            //perform select all operation
            jta.selectAll();
        }
        if (e.getSource()==close){
            //perform close operation
            System.exit(0);
        }
    }
}
