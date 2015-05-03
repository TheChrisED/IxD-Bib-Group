/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 * Eine JMenuBar mit den Menüs Datei, Datenbank, Fenster, Hilfe für das IxD Bib
 * System
 *
 * @author Chris
 */
public class BibMenuBar extends JMenuBar {

    private JTabbedPane _viewSelector;
    private Frame _owner;

    private JMenu _fileMenu;
    private static final String FILE_MENU_TITLE = "Datei";
    private JMenuItem _verleihMenuItem;
    private static final String TEST_MENUITEM_TITLE = "Neuer Benutzer";

    private JMenu _databaseMenu;
    private static final String DATABASE_MENU_TITLE = "Datenbank";

    private JMenu _windowMenu;
    private static final String WINDOW_MENU_TITLE = "Fenster";

    private JMenu _helpMenu;
    private static final String HELP_MENU_TITLE = "Hilfe";

    public BibMenuBar(Frame owner) {
        super();
        _owner = owner;
        setupMenuBar();
    }

    private void setupMenuBar() {
        // Menu Setup
        _fileMenu = new JMenu(FILE_MENU_TITLE);
        this.add(_fileMenu);
        _databaseMenu = new JMenu(DATABASE_MENU_TITLE);
        this.add(_databaseMenu);
        _windowMenu = new JMenu(WINDOW_MENU_TITLE);
        this.add(_windowMenu);
        _helpMenu = new JMenu(HELP_MENU_TITLE);
        this.add(_helpMenu);

        // MenuItem Setup
        // - FileMenu
        _verleihMenuItem = new JMenuItem("Verleihen");
        _verleihMenuItem.addActionListener(ae -> new VerleihFrame(_owner).setVisible(true));
        _verleihMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _fileMenu.add(_verleihMenuItem);

        JMenuItem rueckgabeMenuItem = new JMenuItem("Rückgabe");
        rueckgabeMenuItem.addActionListener(ae -> new RuecknahmeFrame(_owner).setVisible(true));
        rueckgabeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _fileMenu.add(rueckgabeMenuItem);

        // - DatabaseMenu
        JMenuItem newUserMenuItem = new JMenuItem("Neuer Benutzer");
        newUserMenuItem.addActionListener(ae -> new AddUserDialog(_owner).setVisible(true));
        newUserMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _databaseMenu.add(newUserMenuItem);

        JMenuItem newMediumMenuItem = new JMenuItem("Neues Medium aufnehmen");
        newMediumMenuItem.addActionListener(ae -> new AddMediumDialog(_owner).setVisible(true));
        newMediumMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _databaseMenu.add(newMediumMenuItem);
        
        JMenuItem removeMediumMenuItem = new JMenuItem("Medium entfernen");
        removeMediumMenuItem.addActionListener(ae -> new DeleteMediumDialog(_owner).setVisible(true));
        removeMediumMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        _databaseMenu.add(removeMediumMenuItem);
    }

    /**
     * Erstellt unter dem Menü Fenster Einträge, die auf Tabs eines JTabbedPane
     * verweisen. Der JTabbedPane wird mittels Menüeinträgen steuerbar gemacht.
     *
     * @param viewSelector Damit die Benennung korrekt funktioniert, muss der
     * Tabname über die getName() Methode erreichbar sein
     */
    public void setViewSelector(JTabbedPane viewSelector) {
        _viewSelector = viewSelector;
        Component[] tabs = viewSelector.getComponents();

        for (int i = 0; i < tabs.length; ++i) {
            JMenuItem tabSelector = new JMenuItem(tabs[i].getName());
            tabSelector.setAccelerator(KeyStroke.getKeyStroke(0x30 + i + 1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            tabSelector.addActionListener(new ViewSelectorListener(i));
            _windowMenu.add(tabSelector);
        }
    }

    class ViewSelectorListener implements ActionListener {

        private int index;

        public ViewSelectorListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            BibMenuBar.this._viewSelector.setSelectedIndex(index);
        }

    }
}
