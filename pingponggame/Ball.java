import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
    Random random;  //instance of random class
    int xVelocity;  //ball's speed in x axis
    int yVelocity;  //ball's speed in y axis
    int initialSpeed = 2;


    Ball(int x, int y, int width, int height){
        super (x, y, width, height); //height and width are ball's diameter
        random = new Random ();
        int randomXDirection = random.nextInt (2);  //its a local variable for the ball constructor

        if (randomXDirection == 0){  //if 0 ball will go left
            randomXDirection--; //(value becomes -1)
        }
        setXDirection (randomXDirection*initialSpeed);
        
        int randomYDirection = random.nextInt (2);  //its a local variable for the ball constructor

        if (randomYDirection == 0){  //if 0 ball will go left
            randomYDirection--; //(value becomes -1)
        }  
        setYDirection (randomYDirection*initialSpeed);
            
    }
    public void setXDirection (int randomXDirection){
        xVelocity = randomXDirection;
    }

    public void setYDirection (int randomYDirection){
        yVelocity = randomYDirection;  
    }

    public void move (){
        x += xVelocity;
        y += yVelocity;
    }

    public void draw (Graphics g){
        g.setColor (Color.white);
        g.fillOval (x, y, height, width);
    }
}
