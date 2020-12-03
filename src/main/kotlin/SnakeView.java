import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("pressed " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> game.keyTyped("w");
                    case KeyEvent.VK_A -> game.keyTyped("a");
                    case KeyEvent.VK_S -> game.keyTyped("s");
                    case KeyEvent.VK_D -> game.keyTyped("d");

                }
            }
        });
    }

    public void output(String text) {
        canvas.setText(text);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}