import javax.swing.*;
import java.awt.*;

public class SnakeView {
    private JTextArea canvas;
    private JPanel panel1;

    private Game game;

    public SnakeView() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

    public void output(String text) {
        canvas.setText(text);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}