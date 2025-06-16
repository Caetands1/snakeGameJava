import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

import enums.Direction;

public class GamePanel extends JPanel {
    private final Snake snake;
    private final Food food;
    private int score = 0; // set to zero to show player not moving
    public static final int BLOCK_SIZE = 40;
    int width = SnakeGame.width;
    boolean gameEnd = false;
    private Direction snakeDirection;
    private final KeyAdapter keyListener;
    JLabel scoreLabel;

    public GamePanel(){
        // Instantiate variables
        this.snake = new Snake();
        this.food = new Food(snake);
        scoreLabel = new JLabel("Score: " +score);
        // Initialise variables

        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        scoreLabel.setBounds(0,0,120,120);

        this.setPreferredSize(new Dimension(SnakeGame.width, SnakeGame.height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(scoreLabel);
        this.setFocusable(true);
        this.requestFocusInWindow();

        keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (!(snakeDirection == Direction.DOWN)) {
                            snakeDirection = Direction.UP;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!(snakeDirection == Direction.UP)) {
                            snakeDirection = Direction.DOWN;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (!(snakeDirection == Direction.RIGHT)) {
                            snakeDirection = Direction.LEFT;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!(snakeDirection == Direction.LEFT)) {
                            snakeDirection = Direction.RIGHT;
                        }
                        break;
                }
            }
        };
        this.addKeyListener(keyListener);
    }

    public void startGame() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameEnd) {
                    this.cancel();
                }
                if (snakeDirection != null && snakeDirection != Direction.STOP) {
                    snake.moveSnake(snakeDirection);
                }
                checkCollision();
                repaint();
            }
        };

        timer.schedule(task, 0, 150);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawSnake(g);
        drawFood(g);
    }
    private  void drawBackground(Graphics g){
        // draw the background
        g.setColor(Color.green);
        g.fillRect(0,0, getWidth(),getHeight());

    }

    private void drawSnake(Graphics g){
        // draw the snake

        for (Point segment : snake.getBody()){
            int x = segment.x * BLOCK_SIZE;
            int y = segment.y * BLOCK_SIZE;

            g.setColor(Color.blue);
            g.fillRect(x,y, BLOCK_SIZE, BLOCK_SIZE);

        }
    }

    private void drawFood(Graphics g) {
        // draw the food

        int foodx = food.getLocation().x * BLOCK_SIZE;
        int foody = food.getLocation().y * BLOCK_SIZE;

        g.setColor(Color.red);
        g.fillRect(foodx, foody, BLOCK_SIZE, BLOCK_SIZE);
    }

    private void checkCollision() {
        // variables
        LinkedList<Point> snakeBody = snake.getBody();
        Point foodLocation = food.getLocation();
        int borderBoundary = (width / BLOCK_SIZE) - 1;
        List<Point> bodyWithoutHead = snakeBody.subList(1, snakeBody.size());
        Set<Point> bodySet = new HashSet<>(bodyWithoutHead);

        // Checks for collisions between snake and food
        if (snakeBody.peekFirst().getX() == foodLocation.getX() && snakeBody.peekFirst().getY() == foodLocation.getY()) {
            food.setLocation();
            snake.growSnake(snakeDirection);
            score++;
            scoreLabel.setText("Score: " + score);
        }
        // Checks for snake colliding with boundary
        if ( (!(snakeBody.peekFirst().y >= 0) || !(snakeBody.peekFirst().y <= borderBoundary)) || (!(snakeBody.peekFirst().x >= 0) || !(snakeBody.peekFirst().x <= borderBoundary)) ) {
            System.out.println("Exited border");
            endGame();
        }

        // Checks for snake colliding with itself
        // using sets https://docs.oracle.com/javase/8/docs/api/java/util/Set.html

        if (bodySet.contains(snakeBody.getFirst())) {
            System.out.println("Collided with self");
            endGame();
        }
    }

    private void endGame(){
        snakeDirection = Direction.STOP; // Stops the snake from moving
        this.removeKeyListener(keyListener); // Removes player input
        gameEnd = true;
    }
}
