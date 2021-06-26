import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Hello
{

    public static void main(String[] args)
    {

        JDialog dialog = new JDialog();
        dialog.setSize(300, 150);

        JPanel buttons = new JPanel();
        JButton button1 = new JButton("ok");
        JButton button2 = new JButton("abbrechen");
        buttons.add(button1);
        buttons.add(button2);

        JPanel betrag = new JPanel();
        JTextField textfield0 = new JTextField("10");
        JTextField textfield1 = new JTextField("Bereits Bezahlt");
        JTextField textfield2 = new JTextField("noch zu zahlen");
        betrag.add(textfield0);
        betrag.add(textfield1);
        betrag.add(textfield2);

        JPanel titel = new JPanel();
        JLabel label1 = new JLabel("Gesamtbetrag");
        JLabel label2 = new JLabel("Bereits Bezahlt");
        JLabel label3 = new JLabel("noch zu zahlen");
        titel.add(label1);
        titel.add(label2);
        titel.add(label3);

        dialog.add(titel, BorderLayout.NORTH);
        dialog.add(betrag, BorderLayout.CENTER);
        dialog.add(buttons, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

}
