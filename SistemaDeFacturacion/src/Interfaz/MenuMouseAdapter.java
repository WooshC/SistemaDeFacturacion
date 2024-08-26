/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author Woosh
 */
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuMouseAdapter extends MouseAdapter {
    private final JLabel menuLabel;
    private final JPanel panelToShow;
    private final JPanel[] allPanels;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickedColor;
    private final MenuInteractionHandler menuHandler;
    private final Runnable additionalAction; // Acción adicional a ejecutar

    public MenuMouseAdapter(JLabel menuLabel, JPanel panelToShow, JPanel[] allPanels, Color defaultColor, Color hoverColor, Color clickedColor, MenuInteractionHandler menuHandler) {
        this(menuLabel, panelToShow, allPanels, defaultColor, hoverColor, clickedColor, menuHandler, null);
    }

    public MenuMouseAdapter(JLabel menuLabel, JPanel panelToShow, JPanel[] allPanels, Color defaultColor, Color hoverColor, Color clickedColor, MenuInteractionHandler menuHandler, Runnable additionalAction) {
        this.menuLabel = menuLabel;
        this.panelToShow = panelToShow;
        this.allPanels = allPanels;
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        this.clickedColor = clickedColor;
        this.menuHandler = menuHandler;
        this.additionalAction = additionalAction;
        this.menuLabel.setOpaque(true); // Ensure background color is visible
        this.menuLabel.setBackground(defaultColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        menuHandler.setActiveMenu(menuLabel); // Notify the handler about the active menu
        menuLabel.setBackground(clickedColor);
        // Show the corresponding panel and hide others
        for (JPanel panel : allPanels) {
            if (panel.equals(panelToShow)) {
                panel.setVisible(true);
            } else {
                panel.setVisible(false);
            }
        }
        // Ejecutar la acción adicional si está definida
        if (additionalAction != null) {
            additionalAction.run();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!menuLabel.getBackground().equals(clickedColor)) {
            menuLabel.setBackground(hoverColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!menuLabel.getBackground().equals(clickedColor)) {
            menuLabel.setBackground(defaultColor);
        }
    }
}
