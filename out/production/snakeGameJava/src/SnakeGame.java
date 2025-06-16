import javax.swing.*;
import java.awt.*;

public class SnakeGame {
        JFrame gameWindow;
        public static final int width = 800;
        public static final int height = width;

    public SnakeGame() {
        gameWindow = new JFrame();
        GamePanel panel = new GamePanel();

        gameWindow.add(panel);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setTitle("Snake Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);

        panel.startGame();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

}
