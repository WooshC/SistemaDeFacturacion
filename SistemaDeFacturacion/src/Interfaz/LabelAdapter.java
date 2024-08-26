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
import javax.swing.JPanel;

public class LabelAdapter extends MouseAdapter {
    private final JLabel menuLabel;
    private final JPanel panelToShow;
    private final JPanel[] allPanels;
    private final Color defaultColor;
    private final Color hoverColor;
    private final Color clickedColor;
    private final Runnable additionalAction;

    // Referencia estática para recordar el JLabel activo
    private static JLabel activeLabel = null;

    public LabelAdapter(JLabel menuLabel, JPanel panelToShow, JPanel[] allPanels, Color defaultColor, Color hoverColor, Color clickedColor) {
        this(menuLabel, panelToShow, allPanels, defaultColor, hoverColor, clickedColor, null);
    }

    public LabelAdapter(JLabel menuLabel, JPanel panelToShow, JPanel[] allPanels, Color defaultColor, Color hoverColor, Color clickedColor, Runnable additionalAction) {
        this.menuLabel = menuLabel;
        this.panelToShow = panelToShow;
        this.allPanels = allPanels;
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        this.clickedColor = clickedColor;
        this.additionalAction = additionalAction;
        this.menuLabel.setOpaque(true); // Asegura que el color de fondo sea visible
        this.menuLabel.setBackground(defaultColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Cambiar el color del JLabel previamente activo
        if (activeLabel != null && activeLabel != menuLabel) {
            activeLabel.setBackground(defaultColor);
        }

        // Establecer el nuevo JLabel como activo
        menuLabel.setBackground(clickedColor);
        activeLabel = menuLabel;

        // Mostrar el panel correspondiente y ocultar los demás
        for (JPanel panel : allPanels) {
            panel.setVisible(panel.equals(panelToShow));
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
