import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {
    int id; // either 1 or 2 for 2 paddles
    int yVelocity; // for for fast the paddles are moving
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;

    }

    public void keyPressed(KeyEvent e) {
        switch (id) {

			case 1:  //if an user types w on keyboard, this'll run
				if (e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(-speed);  //this'll move up on y axis
					move();
				}
                if (e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(speed);
					move();
				}

				break;
            case 2:
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(-speed);
					move();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(speed);
					move();
				}
				break ;
        }
    }
	public void keyReleased(KeyEvent e) {
        switch (id) {

			case 1:
				if (e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(0);  //its 0 orelse it'll keep moving forever
					move();
				}

				if (e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(0);
					move();
				}

				break;

			case 2:
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(0);
					move();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(0);
					move();
				}

				break;
		}

    }

    public void setYDirection(int YDirection) {
        yVelocity = YDirection;

    }

    public void move() {
        y = y + yVelocity;

    }

    public void draw(Graphics g) {
        if (id == 1)
			g.setColor(Color.blue);
		else{
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);

    }
}
