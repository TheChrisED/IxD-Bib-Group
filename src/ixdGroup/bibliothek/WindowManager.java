/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import com.bulenkov.iconloader.IconLoader;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private Dimension _defaultDimensions = new Dimension(1000, 700);

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
        
        JTextField search = new JTextField(10);
        TextPrompt searchPrompt = new TextPrompt("Suche", search);
        searchPrompt.changeAlpha(0.5f);
        search.add(searchPrompt);
        searchPrompt.setVisible(true);
        JButton addMediumButton = new JButton(IconLoader.findIcon("/ixdGroup/images/add-medium.png"));
        addMediumButton.setMinimumSize(new Dimension(40, 40));
        addMediumButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMediumDialog(_mainFrame).setVisible(true);
            }
        });
        JButton addUserButton = new JButton(IconLoader.findIcon("/ixdGroup/images/add-user.png"));
        addUserButton.setMinimumSize(new Dimension(40, 40));
        addUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserDialog(_mainFrame).setVisible(true);
            }
        });
 
        _toolBar.setFloatable(true);
        _toolBar.setPreferredSize(new Dimension(500, 50));
        _toolBar.add(addUserButton);
        _toolBar.addSeparator();
        _toolBar.add(search);
        _toolBar.add(addMediumButton);
        _toolBar.setVisible(true);
        System.out.println(_toolBar.getLayout());
        _mainPanel.add(_toolBar, BorderLayout.NORTH);
        
        // ----- Setup TabbedPane ------
        _viewSelector = new JTabbedPane();
        addTabToViewSelector("Bestand", new String[] {"Titel", "Autor", "ISBN"},
                new JComponent[] {new JLabel("<html>Exemplare: 10<br>Verfügbar: 2<br>"
                        + "Standorte: AF453, AF454</html>"), new JButton("Exemplar entfernen")});
        addTabToViewSelector("Verliehen", new String[] {"Titel", "Entleiher", "Entliehen bis"},
                new JComponent[] {new JLabel("<html>Verfügbare Exemplare: 0<br>Vorbestellungen: 2<br>"
                        + "Etc.</html>"), new JButton("Exemplar entfernen")});
        addTabToViewSelector("Zurückgegeben", new String[] {"Standort zum Einsortieren", "Titel", "ISBN"},
                new JComponent[] {new JLabel("<html>Standort zum Einsortieren: BG635<br>"
                        + "Vorbestellungen für dieses Medium: 2<br>"
                        + "Letzter Entleiher: Achmann, Achmed</html>"), new JButton("Als einsortiert markieren")});
        addTabToViewSelector("Benutzer", new String[] {"Name", "Vorname", "Beitritt"},
                new JComponent[] {new JLabel("<html>Name: 10<br>Vorname: 2<br>"
                        + "Beitritt: AF453, AF454<br>ID: 1249234937<br>"
                        + "Adresse: Ulfmann Platz 42<br>"
                        + "Mitgliedschaftsende: 12.05.2442</html>"), new JButton("Mitgliedschaft ändern"),
                new JButton("Benutzerinforationen bearbeiten")});
        _viewSelector.setVisible(true);
        
        _mainPanel.add(_viewSelector, BorderLayout.CENTER);
                
    }
    
    private void addTabToViewSelector(String tabName, String[] colNames, JComponent[] details) {
        _viewSelector.add(createMainView(tabName, colNames, details), tabName);
    }
    
    private MainView createMainView(String name, String[] colNames, JComponent[] details) {
        MainView view = new MainView(colNames, details);
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
