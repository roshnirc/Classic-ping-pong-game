import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
    static int GAME_WIDTH; // pass it in a copy using the constructor
    static int GAME_HEIGHT;
    int player1; // score of 1
    int player2; // score of 2

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor (Color.white);
        g.setFont (new Font ("Comic", Font.BOLD, 60));
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);  //starting x&y position and ending x&y position

        //to show scores on top. (in single digits)-
        //g.drawString (String.valueOf (player1), (GAME_WIDTH / 2) - 85, 50);
        //g.drawString (String.valueOf (player2), (GAME_WIDTH / 2) + 20, 50);

        //to make the scores on top(with double digits)
        g.drawString (String.valueOf (player1 / 10) + String.valueOf (player1 % 10), (GAME_WIDTH / 2) - 85, 50);
        g.drawString (String.valueOf (player2 / 10) + String.valueOf (player2 % 10), (GAME_WIDTH / 2) + 20, 50);

    }
}
