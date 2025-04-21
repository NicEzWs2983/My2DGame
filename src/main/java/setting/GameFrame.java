package setting;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Player;

import entity.action.*;
import panel.*;
import panel.staticPanel.*;
import setting.collisionChecker.*;

public class GameFrame extends JFrame {
    public TitlePanel titlePanel = new TitlePanel(this);
    public GamePanel gamePanel = new GamePanel(this);
    public GameOverPanel gameOverPanel = new GameOverPanel(this);

    public LevelPanel level1Panel = new LevelPanel(this);
    public LevelPanel level2Panel = new LevelPanel(this);
    public LevelPanel level3Panel = new LevelPanel(this);

    public CardLayout layout = new CardLayout();
    public JPanel cardPanel = new JPanel(layout);

    public CheckState cState = new CheckState(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public CollisionChecker_GP cCheckerGP = new CollisionChecker_GP(this);
    public CollisionChecker_LVP cCheckerLVP = new CollisionChecker_LVP(this);

    public EntityAction_GP eAction_GP = new EntityAction_GP(this, keyH);
    public EntityAction_LVP eAction_LVP = new EntityAction_LVP(this, keyH);

    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public final String title = "title";
    public final String game = "game";
    public final String lv1 = "lv1";
    public final String lv2 = "lv2";
    public final String lv3 = "lv3";
    public final String gameOver = "game over";

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Hello World");

        this.add(cardPanel);

        cardPanel.add(titlePanel, title);
        cardPanel.add(gamePanel, game);
        cardPanel.add(level1Panel, lv1);
        cardPanel.add(level2Panel, lv2);
        cardPanel.add(level3Panel, lv3);
        cardPanel.add(gameOverPanel, gameOver);

        layout.show(cardPanel, title);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.addKeyListener(keyH);
        level1Panel.addKeyListener(keyH);
        level2Panel.addKeyListener(keyH);
        level3Panel.addKeyListener(keyH);
    }

}
