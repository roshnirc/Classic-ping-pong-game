import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable { // to run it on a thread

    static final int GAME_WIDTH = 1000; // static cuz if incase we have multiple instances of our gamePanel class,
                                        // they'll all share the same variable GAME_WIDTH and they wouldnt each have
                                        // their individual game with, they'd all be sharing the same one. Final is used
                                        // cuz it pevents from later on modifying the value, also it runs a bit faster.
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread; // since we're implementing a runnable so, thread
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1; // player1
    Paddle paddle2; // player2
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true); // focusses when a key is pressed
        this.addKeyListener(new AL()); // responds to keystrokes
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this); // to start the thread
        gameThread.start();

    }

    public void newBall() { // creates a new ball
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER) , BALL_DIAMETER, BALL_DIAMETER);
        //((GAME_HEIGHT / 2) - (BALL_DIAMETER / 2)) on the place of y parameter--> if i wanna start the ball from the middle
    }

    public void newPaddles() { // when i want to reset a level or a game
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
                PADDLE_HEIGHT, 1); // where i want the paddle to be in x-axis
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,
                (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
                PADDLE_HEIGHT, 2);

    }

    public void paint(Graphics g) { // to paint stuff on screen
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this); // this- jpanel called gamepanel

    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() { // to move things after each iteration of our game loop
        paddle1.move(); // for smooth movement of paddle1
        paddle2.move(); // for smooth movement of paddle1
        ball.move();
    }

    public void checkCollision() {

        // bounces ball off paddles for paddle 1
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // for more x velocity after a bounce(more difficulty)
            if (ball.yVelocity > 0) {
                ball.yVelocity++; // for more x velocity after a bounce(more difficulty)
            } else {
                ball.yVelocity--; // if y velocity is -ve its going upwards
            }
            ball.setXDirection(ball.xVelocity); // set x direction with the new values
            ball.setYDirection(ball.yVelocity); // set y direction with the new values
        }

        // bounces ball off paddles for paddle 2
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // for more x velocity after a bounce(more difficulty)
            if (ball.yVelocity > 0) {
                ball.yVelocity++; // for more x velocity after a bounce(more difficulty)
            } else {
                ball.yVelocity--; // if y velocity is -ve its going upwards
            }
            ball.setXDirection(-ball.xVelocity); // set x direction with the new values
            ball.setYDirection(ball.yVelocity); // set y direction with the new values
        }

        // bounce ball off top & bottom window edges
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }

        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) { // checks ball's diameter
            ball.setYDirection(-ball.yVelocity);
        }

        // to prevent the paddles from going off the screen
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // gives a player 1 point and creates new paddles & ball
        if (ball.x <= 0) {  //ball falls off left boundary and 2nd player point + 1
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
          }
      
          if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {  //ball falls off right boundary and 1st player point + 1
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player2);
          }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
                // System.out.println("Test");
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }
}
