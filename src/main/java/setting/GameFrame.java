package setting;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import button.*;
import entity.Player;

import entity.action.*;
import object.*;
import object.UIimage.WatchingSign;
import panel.*;
import panel.staticPanel.*;
import setting.collisionChecker.*;

public class GameFrame extends JFrame {
    public GetText getText = new GetText(this);
    public OptionPanel optionPanel = new OptionPanel(this);

    public TitlePanel titlePanel = new TitlePanel(this);
    public GamePanel gamePanel = new GamePanel(this);
    public GameOverPanel gameOverPanel = new GameOverPanel(this);

    public int numberOfLevel = 4;
    public LevelPanel[] levelPanel = new LevelPanel[] {
            new LevelPanel(this), new LevelPanel(this), new LevelPanel(this), new LevelPanel(this)
    };

    // public LevelPanel level1Panel = new LevelPanel(this);
    // public LevelPanel level2Panel = new LevelPanel(this);
    // public LevelPanel level3Panel = new LevelPanel(this);

    public CardLayout layout = new CardLayout();
    public JPanel cardPanel = new JPanel(layout);

    public CheckState cState = new CheckState(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    public DrawAnything drawAnything = new DrawAnything(this);
    public WatchingSign watchingSign = new WatchingSign(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public CollisionChecker_GP cCheckerGP = new CollisionChecker_GP(this);
    public CollisionChecker_LVP cCheckerLVP = new CollisionChecker_LVP(this);

    public EntityAction_GP eAction_GP = new EntityAction_GP(this, keyH);
    public EntityAction_LVP eAction_LVP = new EntityAction_LVP(this, keyH);

    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public final String title = "title";
    public final String game = "game";
    public final String lv[] = { "lv0", "lv1", "lv2", "lv3" };
    // public final String lv1 = "lv1";
    // public final String lv2 = "lv2";
    // public final String lv3 = "lv3";
    public final String gameOver = "game over";
    public final String option = "option";

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("The Door of Destiny");

        this.add(cardPanel);

        cardPanel.add(titlePanel, title);
        cardPanel.add(gamePanel, game);

        for (int i = 1; i < numberOfLevel; i++) {
            cardPanel.add(levelPanel[i], lv[i]);
        }
        // cardPanel.add(level1Panel, lv1);
        // cardPanel.add(level2Panel, lv2);
        // cardPanel.add(level3Panel, lv3);

        cardPanel.add(gameOverPanel, gameOver);
        cardPanel.add(optionPanel, option);

        layout.show(cardPanel, title);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.addKeyListener(keyH);
        for (int i = 1; i < numberOfLevel; i++) {
            levelPanel[i].addKeyListener(keyH);
        }
    }

    public void setting() {
        layout = new CardLayout();
        cardPanel = new JPanel(layout);

        cState = new CheckState(this);
        keyH = new KeyHandler(this);
        ui = new UI(this);

        cChecker = new CollisionChecker(this);
        cCheckerGP = new CollisionChecker_GP(this);
        cCheckerLVP = new CollisionChecker_LVP(this);

        eAction_GP = new EntityAction_GP(this, keyH);
        eAction_LVP = new EntityAction_LVP(this, keyH);

        aSetter = new AssetSetter(this);
        player = new Player(this, keyH);
    }

}
