/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * // * This class is a JFrame that automatically adapts to the platform it is
 * running on. E.g. if the platform is Mac OSX, all BibFrames display a menu
 * bar. Otherwise it functions the same way as a JFrame
 *
 * @author Chris
 */
public class BibFrame extends JFrame {

    private JMenuBar _menuBar;

    public BibFrame() {
        super();
        _menuBar = new BibMenuBar(this);
        setPlatformLook();
    }
    
    public BibFrame(String title) {
        super(title);
        _menuBar = new BibMenuBar(this);
        setPlatformLook();
    }
    
    public BibFrame(String title, boolean menuBarOn) {
        super(title);
        _menuBar = new BibMenuBar(this);
        setPlatformLook();
        setMenuBarOn(menuBarOn);
    }

    private void setPlatformLook() {
        if (BibliothekStartupGroup.OPERATING_SYSTEM.startsWith("mac")
                && getJMenuBar() == null) {
            setJMenuBar(_menuBar);
        }
    }

    @Override
    public void setJMenuBar(JMenuBar menuBar) {
        _menuBar = menuBar;
        super.setJMenuBar(menuBar);
    }

    /**
     * Toggles the integrated Menu Bar on or off
     *
     * @param menuBarOn true, if the menu Bar should be turned on
     */
    public void setMenuBarOn(boolean menuBarOn) {
        if (menuBarOn && getJMenuBar() == null) {
            _menuBar = new BibMenuBar(this);
            setJMenuBar(_menuBar);
        } else if (!menuBarOn){
            setJMenuBar(null);
        }
    }
}
