package nesneproje;

import java.awt.*;
import javax.swing.*;
import java.util.Stack;
import javax.swing.plaf.metal.*;


class NesneProje extends JFrame {

    // Textin alanı
    JTextArea textField;
    //Kelime bulma ve değiştirme textleri
    JTextField findField;
    JTextField replaceField;
    //Geri alma tuşu
    JMenuItem undo;
    
    Stack<String> stack = new Stack();
    String temp = "";
    
    Boolean isRestoring = false;

    // Arayüz
    JFrame frame;

    // Constructor 
    NesneProje() {
        // Arayüz oluşturma
        frame = new JFrame("Text Editor");

        try {
            //tema ayarlama
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
        } catch (Exception e) {
        }

        // Menü oluşturma
        JMenu m1 = new JMenu("File");

        // Menü itemleri oluşturma
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save as...");
        JMenuItem mi4 = new JMenuItem("Check Typo");
        undo = new JMenuItem("Undo");
        undo.setEnabled(false);
        
        // Text component
        textField = new JTextArea();
        textField.getDocument().addDocumentListener(new TextFieldHandler(this)); 

        // Munü çubuğunun oluşturulması.
        JMenuBar mb = new JMenuBar();
        JMenuItem mc = new JMenuItem("Close");
        JPanel findAndReplaceMenubar = new JPanel();
        findAndReplaceMenubar.setLayout(new FlowLayout());

        JButton replaceAllButton = new JButton("Replace All");

        registerEvents(mi1, mi2, mi3, mi4, mc, replaceAllButton);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);
        m1.add(undo);

        findField = new JTextField("", 10);
        replaceField = new JTextField("", 10);

        findAndReplaceMenubar.add(new JLabel("Find: "));
        findAndReplaceMenubar.add(findField);
        findAndReplaceMenubar.add(new JLabel("Replace With: "));
        findAndReplaceMenubar.add(replaceField);
        findAndReplaceMenubar.add(replaceAllButton);

        mb.add(m1);
        mb.add(mc);

        frame.setJMenuBar(mb);
        frame.add(textField);
        frame.add(findAndReplaceMenubar, BorderLayout.SOUTH);
        frame.setSize(500, 500);
        frame.show();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //itemlara action listener eklenmesi.
    private void registerEvents(JMenuItem mi1, JMenuItem mi2, JMenuItem mi3, JMenuItem mi4, JMenuItem mc, JButton replaceAllButton) {
        mi1.addActionListener(new MenuBarHandler(this));
        mi2.addActionListener(new MenuBarHandler(this));
        mi3.addActionListener(new MenuBarHandler(this));
        mi3.addActionListener(new MenuBarHandler(this));
        mi4.addActionListener(new MenuBarHandler(this));
        mc.addActionListener(new MenuBarHandler(this));
        undo.addActionListener(new MenuBarHandler(this));
        replaceAllButton.addActionListener(new SearchAndReplaceHandler(this));
    }
    //undo butonu metodları.
    public void saveCurrentContent() {
        stack.push(temp);
        temp = textField.getText();
    }

    public void restoreOldContent() {
        textField.setText(stack.pop());
        isRestoring = false;
    }
            
    public static void main(String[] args) {
        
        NesneProje e = new NesneProje();
    }
}
