import javax.swing.*;

public class Menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dungeon Crawler Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);

        MenuPanel menuPanel = new MenuPanel();
        frame.add(menuPanel);

        frame.setVisible(true);
    }
}