package ixdGroup.bibliothek;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class RuecknahmeFrame extends AbstractVerleihvorgangFrame {

    public RuecknahmeFrame(Frame owner) {
        super(owner, "Rücknahmevorgang", true, "   Zurückzunehmende Medien", "Zurücknehmen");
    }

//    @Override
//    protected JPanel createInputPanel() {
//        JPanel inputPanel = new JPanel();
//        inputPanel.add(new JLabel("Entleiher: "));
//        inputPanel.add(new JTextField(20));
//        inputPanel.add(new JButton("+"));
//        return inputPanel;
//    }
//    
}
