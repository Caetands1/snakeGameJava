import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import enums.Direction;

public class GamePanel extends JPanel {
    private Snake snake;
    private Food food;
    private int score;
    public static final int BLOCK_SIZE = 40;
    private Direction snakeDirection;
    JLabel scoreLabel;

    public GamePanel(){
        snake = new Snake();
        food = new Food();
        score = 0;

        this.setPreferredSize(new Dimension(SnakeGame.width, SnakeGame.height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        scoreLabel = new JLabel("Score: " + Integer.toString(score));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        scoreLabel.setBounds(0,0,120,120);

        this.add(scoreLabel);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(!(snakeDirection == Direction.DOWN)){snakeDirection = Direction.UP;}
                        break;
                    case KeyEvent.VK_DOWN:
                        if(!(snakeDirection == Direction.UP)) {snakeDirection = Direction.DOWN;}
                        break;
                    case KeyEvent.VK_LEFT:
                        if(!(snakeDirection == Direction.RIGHT)){snakeDirection = Direction.LEFT;}
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(!(snakeDirection == Direction.LEFT)){snakeDirection = Direction.RIGHT;}
                        break;
                }

            }
        });
    }

    public void startGame() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (snakeDirection != null) {
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
        // check for collision

        if (snake.getBody().peekFirst().getX() == food.getLocation().getX() && snake.getBody().peekFirst().getY() == food.getLocation().getY()) {
            food.setLocation();
            snake.growSnake(snakeDirection);
            score++;
            scoreLabel.setText("Score: " + Integer.toString(score));
        }
    }
}
