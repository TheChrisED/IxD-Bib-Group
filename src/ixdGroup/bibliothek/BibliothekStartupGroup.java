/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ixdGroup.bibliothek;

import javax.swing.UIManager;

/**
 *
 * @author Chris
 */
public class BibliothekStartupGroup {

    private static final String MAIN_WINDOW_TITLE = "IxD Bibliothek";
    public static String OPERATING_SYSTEM;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            
        }
        OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();
        setPlatformProperties();
        new WindowManager(MAIN_WINDOW_TITLE);
        System.out.println(UIManager.getLookAndFeel());
    }
    
    
    private static void setPlatformProperties() {
        if (OPERATING_SYSTEM.startsWith("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }
}
