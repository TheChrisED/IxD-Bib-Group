/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Formatter;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Chris
 */
public class AddUserDialog extends AbstractDialog {

    public AddUserDialog(Frame owner) {
        super(owner, "Neuen Benutzer anlegen", true, "Aufnehmen");
        setup();
    }

    @Override
    /**
     * @see{AbstractDialog}
     */
    protected JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        JComponent[] row1 = {new JLabel("Name: "), new JTextField(15), 
            new JLabel("Geburtsdatum: "), new JTextField("Hier soll ein DatePicker rein!")};
        JPanel row1Panel = createFlowPanelWithComponents(row1);
        panel.add(row1Panel, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 1;
//        UtilDateModel model = new UtilDateModel();
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
//        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
        JRadioButton m = new JRadioButton("m");
        JRadioButton w = new JRadioButton("w");
        ButtonGroup gender = new ButtonGroup();
        gender.add(m);
        gender.add(w);
        JComponent[] row2 = {new JLabel("Vorname: "), new JTextField(15), 
            new JLabel("Geschlecht: "), m, w};
        panel.add(createFlowPanelWithComponents(row2), gbc);
        
        gbc.gridy = 2;
        
        JComponent[] row3 = {new JLabel("Adresse: "), new JComboBox(new Object[]{"Deutschland", "USA", "Afghanistan"}), 
            new JComboBox(new Object[]{"Hamburg", "Schleswig-Holstein", "Nordrheinwestfahlen"}), 
            new JComboBox(new Object[]{"Hamburg", "Berlin", "Bremen"})};
        panel.add(createFlowPanelWithComponents(row3), gbc);
        
        gbc.gridy = 3;
        JComponent[] row4 = {new JLabel("Straße: "), new JTextField(15), new JLabel("Hausnr.:"),
            new JTextField(2), new JLabel("PLZ: "), new JTextField(5)};
        panel.add(createFlowPanelWithComponents(row4), gbc);
        
        gbc.gridy = 4;
        JComponent[] row5 = {new JLabel("ID: "), new JTextField(10), 
            new JLabel("Aufnahmedatum: "), new JTextField(10)};
        panel.add(createFlowPanelWithComponents(row5), gbc);
        
        return panel;
    }
    
}
