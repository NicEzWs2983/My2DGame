package button;

import java.awt.Color;

import javax.swing.JButton;

import panel.staticPanel.GameOverPanel;

public class RestartButton extends JButton {
    GameOverPanel gop;

    public RestartButton(GameOverPanel gop) {
        this.gop = gop;

        this.setSize(gop.tileSize * 4, gop.tileSize * 2);
        this.setName("restartButton");
        this.setFocusable(false);
        this.setBackground(Color.BLACK);
        this.setText("RESTART");

        this.setVisible(true);
    }
}
