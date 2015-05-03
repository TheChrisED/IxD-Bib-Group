/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Ein flexibler JDialog, der mit OK und Abbrechen Buttons ausgestattet ist.
 * Weitere Funktionalität kann durch Implementation der createInputPanel()
 * Methode hinzugefügt werden.
 *
 * @author Chris
 */
public abstract class AbstractDialog extends JDialog {

    protected String _okButtonText;

    public AbstractDialog(Frame owner, String title, boolean modal, String okButtonText) {
        super(owner, title, modal);
        _okButtonText = okButtonText;
    }

    protected JButton createCloseButton(String title) {
        JButton btn = new JButton(title);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractDialog.this.setVisible(false);
                AbstractDialog.this.dispose();
            }
        });
        return btn;
    }

    protected JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(createCloseButton("Abbrechen"));
        JButton okButton = createCloseButton(_okButtonText);
        getRootPane().setDefaultButton(okButton);
        btnPanel.add(okButton);
        btnPanel.setVisible(true);
        return btnPanel;
    }

    protected void setup() {
        // --- Main Panel ---
        add(createInputPanel(), BorderLayout.CENTER);
        // --- Button Panel ---
        JPanel btnPanel = createButtonPanel();
        add(btnPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Das Panel, das mit dieser Operation erstellt wird, wird zentral im
     * Dialogsfenster dargestellt. Auf diesem Panel sollen Elemente stehen mit
     * denen man Eingaben tätigen kann.
     *
     * @return Das mit dieser Methode erstellte Panel wird intern zur Erzeugung
     * des Dialogs verwendet
     */
    protected abstract JPanel createInputPanel();

    /**
     * Erstellt ein JPanel mit FlowLayout und Left Alignment und fügt die
     * übergebenen Komponenten hinzu
     *
     * @param components Die Komponenten die hinzugefügt werden
     * @return Ein Panel mit den Komponenten
     */
    public JPanel createFlowPanelWithComponents(JComponent[] components) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JComponent component : components) {
            panel.add(component);
        }
        return panel;
    }
}
