import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor() {
        frame = new JFrame();
        //Initialize menubar
        menuBar = new JMenuBar();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        //Initialize File menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action listners to file menu item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add File menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listners to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Add Edit menu items to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Initialize TextArea
        textArea = new JTextArea();

        frame.setJMenuBar(menuBar);
        // Create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(3, 3, 3, 3));
        panel.setLayout(new BorderLayout(0, 0));
        // Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // Create scroll panel
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        // Add panel to frame
        frame.add(panel);
        frame.setTitle("Text Editor Dev. By Saddam");
        frame.setBounds(100,100, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut) {
            textArea.cut();
        }

        if(actionEvent.getSource() == copy) {
            textArea.copy();
        }

        if(actionEvent.getSource() == paste) {
            textArea.paste();
        }

        if(actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }

        if(actionEvent.getSource() == close) {
            System.exit(0);
        }

        if(actionEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int choseOption = fileChooser.showOpenDialog(null);

            if(choseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output+=intermediate+"\n";
                    }
                    textArea.setText(output);
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource() == saveFile) {
            // Initialize file peaker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get chose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // If click save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                // Create new file with file directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialized Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write textarea content to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}