/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * Der Window Manager verwaltet alle Fenster und Views der IxD Bibliothek 
 * @author Chris
 */
public class WindowManager {

    private JFrame _mainFrame;
    private JMenuBar _menuBar;
    private JPanel _mainPanel;
    private JToolBar _toolBar;
    public JTabbedPane _viewSelector;

    private Dimension _defaultDimensions = new Dimension(500, 500);

    /**
     * 
     * @param title Titel des Hauptfensters
     */
    public WindowManager(String title) {
        _mainFrame = new JFrame(title);
        setupUI();
    }

    private void setupUI() {
        _mainFrame.setSize(_defaultDimensions);

        _menuBar = new BibMenuBar(_mainFrame);

        _mainFrame.setJMenuBar(_menuBar);
        setupMainPanel();
        ((BibMenuBar) _menuBar).setViewSelector(_viewSelector);
        _mainFrame.add(_mainPanel);

        _mainPanel.setVisible(true);
        _mainFrame.setVisible(true);
    }

    private void setupMainPanel() {
        _mainPanel = new JPanel(new BorderLayout());
        
        // ----- Setup Toolbar --------
        _toolBar = new JToolBar();
//        JPanel leftToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JPanel rightToolBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JTextField search = new JTextField(10);
        TextPrompt searchPrompt = new TextPrompt("Suche", search);
        searchPrompt.changeAlpha(0.5f);
        search.add(searchPrompt);
        searchPrompt.setVisible(true);
        JButton addUserButton = createJButton("Neuer Nutzer", 20);
        addUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserDialog(_mainFrame).setVisible(true);
            }
        });
        JButton addMediumButton = createJButton("Neues Medium", 20);
        addMediumButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMediumDialog(_mainFrame).setVisible(true);
            }
        });
 
//        leftToolBar.add(testButton);
//        
//        rightToolBar.add(search);
        
//        leftToolBar.setVisible(true);
//        rightToolBar.setVisible(true);
        _toolBar.setFloatable(true);
        _toolBar.setPreferredSize(new Dimension(500, 40));
        _toolBar.add(addUserButton);
        _toolBar.addSeparator();
        _toolBar.add(search);
        _toolBar.add(addMediumButton);
        _toolBar.setVisible(true);
        System.out.println(_toolBar.getLayout());
        _mainPanel.add(_toolBar, BorderLayout.NORTH);
        
        // ----- Setup TabbedPane ------
        _viewSelector = new JTabbedPane();
        addTabToViewSelector("Bestand", new String[] {"Titel", "Autor", "ISBN"});
        addTabToViewSelector("Verliehen", new String[] {"Titel", "Entleiher", "Entliehen bis"});
        addTabToViewSelector("Zurückgegeben", new String[] {"Standort zum Einsortieren", "Titel", "ISBN"});
        addTabToViewSelector("Benutzer", new String[] {"Name", "Vorname", "Beitritt"});
        _viewSelector.setVisible(true);
        
        _mainPanel.add(_viewSelector, BorderLayout.CENTER);
                
    }
    
    private void addTabToViewSelector(String tabName, String[] colNames) {
        _viewSelector.add(createMainView(tabName, colNames), tabName);
    }
    
    private MainView createMainView(String name, String[] colNames) {
        MainView view = new MainView(colNames);
        view.setName(name);
        return view;
    }
    
    public static JButton createJButton(String title, int fontSize) {
        JButton testButton = new JButton(title);
        testButton.setFont(new Font(testButton.getFont().getFontName(), testButton.getFont().getStyle(), fontSize));
//        testButton.setMinimumSize(new Dimension(85, 150));
//        testButton.setPreferredSize(new Dimension(100, 150));
        return testButton;
    }
}
