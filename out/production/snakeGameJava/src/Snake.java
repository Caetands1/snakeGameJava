import enums.Direction;
import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> snakeParts;
    public int BLOCK_SIZE = GamePanel.BLOCK_SIZE;

    public Snake() {

        snakeParts = new LinkedList<>();

        snakeParts.add(new Point(5,5));
        snakeParts.add(new Point(4,5));
        snakeParts.add(new Point(3,5));
        snakeParts.add(new Point(2,5));

    }

    public LinkedList<Point> getBody() {
        return snakeParts;
    }

    public void moveSnake(Direction snakeDirection) {
        switch (snakeDirection) {
            case UP:
                snakeParts.addFirst(new Point((int)snakeParts.getFirst().getX(), (int)snakeParts.getFirst().getY()-1));
                snakeParts.removeLast();
                break;

            case DOWN:
                snakeParts.addFirst(new Point((int)snakeParts.getFirst().getX(), (int)snakeParts.getFirst().getY()+1));
                snakeParts.removeLast();
                break;

            case LEFT:
                snakeParts.addFirst(new Point((int)snakeParts.getFirst().getX()-1, (int)snakeParts.getFirst().getY()));
                snakeParts.removeLast();
                break;

            case RIGHT:
                snakeParts.addFirst(new Point((int)snakeParts.getFirst().getX()+1, (int)snakeParts.getFirst().getY()));
                snakeParts.removeLast();
                break;

        }
    }

    public void growSnake(Direction snakeDirection) {
        Point lastPart = snakeParts.peekLast();
        Point snakeSegment = new Point();

        switch (snakeDirection) {
            case UP:
                snakeSegment.x = lastPart.x;
                snakeSegment.y = lastPart.y + 1;
                snakeParts.add(snakeSegment);
                break;
            case DOWN:
                snakeSegment.x = lastPart.x;
                snakeSegment.y = lastPart.y - 1;
                snakeParts.add(snakeSegment);
                break;
            case LEFT:
                snakeSegment.x = lastPart.x - 1;
                snakeSegment.y = lastPart.y;
                snakeParts.add(snakeSegment);
                break;
            case RIGHT:
                snakeSegment.x = lastPart.x + 1;
                snakeSegment.y = lastPart.y;
                snakeParts.add(snakeSegment);
                break;
        }
    }

}
