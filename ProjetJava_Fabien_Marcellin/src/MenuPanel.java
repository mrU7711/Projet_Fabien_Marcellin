import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuPanel extends JPanel {
    private BufferedImage buttonAtlas;
    private Rectangle playButtonBounds;
    private Rectangle optionsButtonBounds;
    private Rectangle quitButtonBounds;
    private int hoverIndex = -1; // -1 = aucun bouton survolé

    public MenuPanel() {
        try {
            buttonAtlas = ImageIO.read(new File("./img/button_atlas.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dimensions des boutons
        int buttonWidth = buttonAtlas.getWidth() / 3;
        int buttonHeight = buttonAtlas.getHeight() / 3;

        // Définir les zones cliquables pour les boutons
        playButtonBounds = new Rectangle(100, 100, buttonWidth, buttonHeight);
        optionsButtonBounds = new Rectangle(100, 200, buttonWidth, buttonHeight);
        quitButtonBounds = new Rectangle(100, 300, buttonWidth, buttonHeight);

        // Gestion des événements de souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (playButtonBounds.contains(e.getPoint())) {
                    System.out.println("Play button clicked! Starting the game...");
                    // Lancer le jeu en appelant la méthode main de la classe Main
                    try {
                        Main.main(new String[]{});  // Appel de la méthode main de Main
                    } catch (Exception ex) {
                        ex.printStackTrace();  // Gérer l'exception si elle se produit
                    }
                } else if (optionsButtonBounds.contains(e.getPoint())) {
                    System.out.println("Options button clicked! Displaying options...");
                    // Affiche les options ici
                } else if (quitButtonBounds.contains(e.getPoint())) {
                    System.out.println("Quit button clicked! Exiting...");
                    System.exit(0); // Quitte le programme
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                  if (playButtonBounds.contains(e.getPoint())) {
                    hoverIndex = 0;
                } else if (optionsButtonBounds.contains(e.getPoint())) {
                    hoverIndex = 1;
                } else if (quitButtonBounds.contains(e.getPoint())) {
                    hoverIndex = 2;
                } else {
                    hoverIndex = -1;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner les boutons
        drawButton(g, playButtonBounds, 0);
        drawButton(g, optionsButtonBounds, 1);
        drawButton(g, quitButtonBounds, 2);
    }

    private void drawButton(Graphics g, Rectangle bounds, int buttonIndex) {
        int buttonWidth = buttonAtlas.getWidth() / 3;
        int buttonHeight = buttonAtlas.getHeight() / 3;

        int spriteIndex = 0; // Par défaut, état normal
        if (hoverIndex == buttonIndex) {
            spriteIndex = 1; // État survolé
        }

        g.drawImage(buttonAtlas,
                bounds.x, bounds.y, // Destination (haut-gauche)
                bounds.x + bounds.width, bounds.y + bounds.height, // Destination (bas-droite)
                spriteIndex * buttonWidth, buttonIndex * buttonHeight, // Source (haut-gauche)
                (spriteIndex + 1) * buttonWidth, (buttonIndex + 1) * buttonHeight, // Source (bas-droite)
                null);
    }
}