
package nesneproje;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldHandler implements DocumentListener {
    //textin alanını kontorl eder.
    private NesneProje nesneProje;

    public TextFieldHandler(NesneProje nesneProje) {
        this.nesneProje = nesneProje;
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        if (nesneProje.isRestoring) {
            return;
        }

        nesneProje.undo.setEnabled(true);
        nesneProje.saveCurrentContent();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
    }
    
}
