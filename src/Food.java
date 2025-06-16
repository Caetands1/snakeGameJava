import java.awt.Point;
import java.util.Random;

public class Food {
    private final Random randint = new Random();
    private final Point location = new Point();

    final int maxCOORD = SnakeGame.width / GamePanel.BLOCK_SIZE;
    private final Snake snake;

    public Food(Snake snake){
        this.snake = snake;
        location.x = 7;
        location.y = 8;
    }

    public void setLocation() {
        do {
            location.x = randint.nextInt(maxCOORD);
            location.y = randint.nextInt(maxCOORD);
        } while (this.snake.getBody().contains(location));
        // System.out.println(location); // prints the location of the apple
        System.out.println("Food spawned at: " + location);
    }

    public Point getLocation() {
        return location;
    }
}
