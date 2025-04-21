package panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import setting.GameFrame;

public class OriginalPanel extends JPanel implements Runnable {
    public GameFrame gf;

    int FPS = 60;

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeigth = tileSize * maxScreenRow;

    public Thread gamThread;

    public int gameState;

    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int openDoorState = 4;
    public final int offLimitsState = 5;

    public final int previousMapState = 998;
    public final int nextMapState = 999;

    public OriginalPanel(GameFrame gf) {
        this.gf = gf;
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setEnabled(true);
        gameState = previousMapState;
    }

    public void startGameThread() {
        gamThread = new Thread(this);
        gamThread.start();
    }

    @Override
    public void run() {
        long nanoSecond = 1000000000;
        double drawInterval = nanoSecond / FPS; // 1000000000 = 1s , drawInterval = 0.1667s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gamThread != null && gameState != nextMapState && gameState != previousMapState) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
                drawCount++;

            }

            if (timer >= nanoSecond) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
    }
}
