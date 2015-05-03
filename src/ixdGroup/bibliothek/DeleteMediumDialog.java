/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Chris
 */
public class DeleteMediumDialog extends AbstractVerleihvorgangFrame {

    public DeleteMediumDialog(Frame owner) {
        super(owner, "Medium entfernen", true, "Zu Entfernende Medien", "Entfernen");
//        setup();
    }
    
    @Override
    protected JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        
        
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Titel: "));
        row2.add(new JTextField(29));
        row2.setVisible(true);
        inputPanel.add(row2, 0);
        
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Medium-ID: "));
        row3.add(new JTextField(10));
        
        row3.setVisible(true);
        inputPanel.add(row3, 1);
        
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Entleiher: "));
        row4.add(new JTextField(20));
        row4.add(new JButton("+"));
        inputPanel.add(row4, 2);
        
        inputPanel.setVisible(true);
        return inputPanel;
    }
}
