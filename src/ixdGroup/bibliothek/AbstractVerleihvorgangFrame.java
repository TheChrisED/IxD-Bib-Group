/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import com.bulenkov.iconloader.IconLoader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Chris
 */
public abstract class AbstractVerleihvorgangFrame extends AbstractDialog {

    protected String _selectedMediaPanelText;
    
    public AbstractVerleihvorgangFrame(Frame owner, String title, boolean modal, 
            String selMediaText, String okButtonText) {
        super(owner, title, modal, okButtonText);
        _selectedMediaPanelText = selMediaText;
        setup();
    }

    protected JPanel createSelectedMediaPanel() {
        JPanel selPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        selPanel.add(new JLabel(_selectedMediaPanelText), gbc);
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        selPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        JPanel toBeRentedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toBeRentedPanel.setBackground(Color.white);
        try {
            JComponent book = createToBeRentedMedium("Buch", "Der Herr<br>der Ringe", "3608938281");
            toBeRentedPanel.add(book);
            JComponent cd = createToBeRentedMedium("CD", "Jack Johnson:<br>In Between Dreams", "ID-12443633");
            toBeRentedPanel.add(cd);
        } catch (IOException exc) {
            System.err.println("Fehler beim Einlesen vom Icon");
        }
        ;
        selPanel.add(toBeRentedPanel, gbc);
        selPanel.setBackground(Color.white);
        selPanel.setVisible(true);
        return selPanel;
    }

    protected JPanel createToBeRentedMedium(String medienart, String titel, String ISBN) throws IOException {
        Icon image = null;
        switch (medienart) {
            case "Buch":
                //                image = ImageIO.read(getClass().getResource("/ixd/images/book.png"));
                image = IconLoader.getIcon("/ixdGroup/images/book.png");
                break;
            case "CD":
                //                image = ImageIO.read(getClass().getResource("/ixd/images/cd_dvd_drive.png"));
                image = IconLoader.getIcon("/ixdGroup/images/cd_dvd_drive.png");
                break;
        }
        JLabel medium = new JLabel("<html>" + titel + "<br>" + ISBN + "</html>");
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(2, 2, 2, 2));
        JPanel panel = new JPanel();
        JLabel icon = new JLabel(image);
        icon.setPreferredSize(new Dimension(40, 40));
        panel.add(icon);
        panel.add(medium);
        panel.setBorder(border);
        return panel;
    }


    @Override
    protected void setup() {
        // --- Main Panel ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(createSelectedMediaPanel(), BorderLayout.CENTER);
        mainPanel.setVisible(true);
        add(mainPanel);
        // --- Button Panel ---
        JPanel btnPanel = createButtonPanel();
        add(btnPanel, BorderLayout.SOUTH);
        pack();
    }

    
    protected JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        
        JPanel row1 = new JPanel(new FlowLayout());
        row1.add(new JLabel("Medienart: "));
        Object[] elements = {"Buch", "DVD", "CD", "Videospiel"};
        row1.add(new JComboBox(elements));
        row1.setVisible(true);
        inputPanel.add(row1, 0);
        
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Titel: "));
        row2.add(new JTextField(29));
        
        row2.setVisible(true);
        inputPanel.add(row2, 1);
        
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("ISBN: "));
        row3.add(new JTextField(10));
        row3.add(new JLabel("Medium-ID: "));
        row3.add(new JTextField(10));
        
        row3.setVisible(true);
        inputPanel.add(row3, 2);
        
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Entleiher: "));
        row4.add(new JTextField(20));
        row4.add(new JButton("+"));
        inputPanel.add(row4, 3);
        
        inputPanel.setVisible(true);
        return inputPanel;
    }
    
}
