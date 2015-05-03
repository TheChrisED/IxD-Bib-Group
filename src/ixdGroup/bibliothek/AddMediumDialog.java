/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Chris
 */
public class AddMediumDialog extends AbstractDialog {

    public AddMediumDialog(Frame owner) {
        super(owner, "Neues Medium", true, "Aufnehmen");
        setup();
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row1Panel.add(new JLabel("Medienart: "));
        row1Panel.add(new JComboBox(new Object[] {"Buch", "DVD", "CD", "Videospiel"}));
        panel.add(row1Panel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        JComponent[] row2 = {new JLabel("Titel: "), new JTextField(29)};
        panel.add(createFlowPanelWithComponents(row2), gbc);
        
        gbc.gridy = 2;
        JComponent[] row3 = {new JLabel("Autor: "), new JTextField(15), 
            new JLabel("ISBN: "), new JTextField(10)};
        panel.add(createFlowPanelWithComponents(row3), gbc);
        
        gbc.gridy = 3;
        gbc.gridheight = 2;
        JComponent[] row4 = {new JTextArea("Stichworte", 3, 35)};
        panel.add(createFlowPanelWithComponents(row4), gbc);
        
        return panel;
    }
    
}
