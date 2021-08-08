package nesneproje;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
            //Menü çubuğundaki metodları içerir
public class MenuBarHandler implements ActionListener {

    private final NesneProje nesneProje;

    MenuBarHandler(NesneProje nesneProje) {
        this.nesneProje = nesneProje;
    }

    @Override
            //Tüm itemların atandığı metodlar
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();

        switch (buttonText) {
            case "Undo":
                handleUndoButton();
                break;

            case "Save as...":
                handleSaveAsButton();
                break;

            case "Open":
                handleOpenButton();
                break;

            case "Check Typo":
                handleCheckTypoButton();
                break;

            case "New":
                handleNewButton();
                break;

            case "Close":
                nesneProje.frame.setVisible(false);
                break;
        }
    }

    private void handleNewButton() {
        nesneProje.saveCurrentContent();
        nesneProje.textField.setText("");
    }

    private void handleCheckTypoButton() {
        TypoChecker typoChecker = new TypoChecker();
        String duzeltilmisString = typoChecker.kelimeleriDuzelt(nesneProje.textField.getText());
        nesneProje.textField.setText(duzeltilmisString);
    }
    
            //kullanıcının belirlemiş olduğu dosyaya kayıt yapılması
    private void handleSaveAsButton() throws HeadlessException {

        JFileChooser j = new JFileChooser(System.getProperty("user.home") + "\\Desktop");

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            File file = new File(j.getSelectedFile().getAbsolutePath());

            try {

                FileWriter file_writer = new FileWriter(file, false);

                BufferedWriter writer = new BufferedWriter(file_writer);

                writer.write(nesneProje.textField.getText());

                writer.flush();
                writer.close();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(nesneProje.frame, evt.getMessage());
            }
        } // kullanıcı kaydetmeyi iptal etmek isterse
        else {
            JOptionPane.showMessageDialog(nesneProje.frame, "The user cancelled the operation.");
        }
    }

            // kullanıcının seçtiği dosyanın açılıp metnin arayüze yansıtılması
    private void handleOpenButton() throws HeadlessException {

        JFileChooser j = new JFileChooser(System.getProperty("user.home") + "\\Desktop");

        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            File fi = new File(j.getSelectedFile().getAbsolutePath());

            try {

                String s1 = "", sl = "";

                FileReader fr = new FileReader(fi);

                BufferedReader br = new BufferedReader(fr);

                sl = br.readLine();

                while ((s1 = br.readLine()) != null) {
                    sl = sl + "\n" + s1;
                }

                nesneProje.saveCurrentContent();
                nesneProje.textField.setText(sl);
            } catch (IOException evt) {
                JOptionPane.showMessageDialog(nesneProje.frame, evt.getMessage());
            }
        } // kullanıcı dosya açmayı iptal etmek isterse
        else {
            JOptionPane.showMessageDialog(nesneProje.frame, "The user cancelled the operation.");
        }
    }

            //geri alma butonuun metodu
    private void handleUndoButton() {

        nesneProje.isRestoring = true;
        nesneProje.restoreOldContent();
        nesneProje.undo.setEnabled(!nesneProje.stack.empty());
    }

}
