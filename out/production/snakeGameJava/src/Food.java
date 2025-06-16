import java.awt.Point;
import java.util.Random;

public class Food {
    private Random randint = new Random();
    private Point location = new Point();
    private int maxCOORD = SnakeGame.width / GamePanel.BLOCK_SIZE;
    public Food(){
        location.x = 7;
        location.y = 8;
    }

    public void setLocation() {
        location.x = randint.nextInt(maxCOORD);
        location.y = randint.nextInt(maxCOORD);

        System.out.println(location);
    }

    public Point getLocation() {
        return location;
    }
}
