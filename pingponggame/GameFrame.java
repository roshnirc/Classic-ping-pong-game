
//is going to hold a game panel. its the window frame that has a minimise button, maximize button and an x frame. The panel is going to be surrounded with the frame
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel panel;

    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // to set size of JFrame
        this.setVisible(true);
        this.setLocationRelativeTo(null); // to set the window in the middle

    }
}
