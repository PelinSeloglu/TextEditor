package nesneproje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAndReplaceHandler implements ActionListener {

    private NesneProje nesneProje;
    //arama ve değiştirme metodları


    public SearchAndReplaceHandler(NesneProje nesneProje) {
        this.nesneProje = nesneProje;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleReplaceAllButton();
    }

    private void handleReplaceAllButton() {
        nesneProje.saveCurrentContent();
        String textFieldText = nesneProje.textField.getText();
        String replacedText = textFieldText.replaceAll(nesneProje.findField.getText(), nesneProje.replaceField.getText());
        nesneProje.textField.setText(replacedText);
    }

}
