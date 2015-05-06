/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

/**
 *
 * @author Chris
 */
public class MainView extends JSplitPane {
    
    JTable _overviewTable;
    JComponent _detailView;
    
    public MainView(String[] colNames, JComponent[] comps) {
        super(JSplitPane.HORIZONTAL_SPLIT);
        fillJTable(colNames);
        setupDetailView(comps);
        setResizeWeight(1.0);
        setBorder(null);
    }
    
    private void setupDetailView(JComponent[] comps) {
        JPanel detailView = new JPanel(new BorderLayout());
        for (JComponent comp: comps) {
            if (comp instanceof JButton) {
                detailView.add(comp, BorderLayout.SOUTH);
            } else {
                detailView.add(comp, BorderLayout.CENTER);
            }
        }
        add(detailView);
    }
    
    private void setupOverview() {
        JTable overviewTable = new JTable(5, 3);
        JScrollPane overview = new JScrollPane(overviewTable);
        overviewTable.setFillsViewportHeight(true);
        overviewTable.setVisible(true);
        System.out.println(this.getPreferredSize());
        overview.setPreferredSize(new Dimension(
                (int) (this.getPreferredSize().width * 0.8), this.getPreferredSize().height));
        System.out.println(this.getPreferredSize());
        overview.setVisible(true);
        add(overview);
    }
    
    public void fillJTable(String[] colNames) {
        _overviewTable = new JTable(new String[0][0], colNames);
        JScrollPane overview = new JScrollPane(_overviewTable);
        _overviewTable.setFillsViewportHeight(true);
        _overviewTable.setVisible(true);
        overview.setPreferredSize(new Dimension(
                (int) (this.getPreferredSize().width * 0.8), this.getPreferredSize().height));
        overview.setVisible(true);
        add(overview);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            leftComponent.setPreferredSize(new Dimension(
                (int) (this.getSize().width * 0.8), this.getSize().height));
        }
    }
}
