package button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import panel.staticPanel.TitlePanel;

public class StartButton extends JButton {
    TitlePanel tp;

    public StartButton(TitlePanel tp) {
        this.tp = tp;

        this.setSize(tp.tileSize * 4, tp.tileSize * 2);
        this.setName("startButton");
        this.setFocusable(false);
        this.setBackground(Color.BLACK);
        this.setText("START");

        this.setVisible(true);
    }
}
