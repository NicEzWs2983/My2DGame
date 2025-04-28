package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.action.EntityAction_GP;
import entity.action.EntityAction_LVP;
import panel.GamePanel;
import panel.LevelPanel;
import setting.GameFrame;
import setting.UtilityTool;
import setting.collisionChecker.CollisionChecker;
import setting.collisionChecker.CollisionChecker_GP;
import setting.collisionChecker.CollisionChecker_LVP;

public class Entity {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lvp[];

    CollisionChecker cChecker;
    CollisionChecker_GP cCheckerGP;
    CollisionChecker_LVP cCheckerLVP;

    EntityAction_GP eAction_GP;
    EntityAction_LVP eAction_LVP;

    public final String up = "up";
    public final String down = "down";
    public final String left = "left";
    public final String right = "right";
    public String name;
    public int tileSize;
    public int entityX, entityY;
    public int width, height;
    public int speed;
    public int speedDefault;
    public int solidAreaMoveSize;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteLoop = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int solidAreaDefaultWidth, solidAreaDefaultHeight;
    public boolean collisionOn = false;
    public boolean collisionOnPlayer = false;
    public int actionLockCounter = 0;
    public int nextFrameSecond = 6;
    public String dialogues[] = new String[10];
    public int dialogueIndex = 0;
    public String originalDirection;
    public int npcIndex, objIndex, doorIndex, signIndex;

    public Entity(GameFrame gf) {
        this.gf = gf;
        this.gp = gf.gamePanel;
        this.lvp = new LevelPanel[gf.numberOfLevel];
        for (int i = 0; i < gf.numberOfLevel; i++) {
            this.lvp[i] = gf.levelPanel[i];
        }

        this.eAction_GP = gf.eAction_GP;
        this.eAction_LVP = gf.eAction_LVP;

        this.cChecker = gf.cChecker;
        this.cCheckerGP = gf.cCheckerGP;
        this.cCheckerLVP = gf.cCheckerLVP;

        this.tileSize = lvp[0].tileSize;
        this.width = tileSize;
        this.height = tileSize;
        solidArea = new Rectangle(width / 4, height / 2, width / 2, height / 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDefaultValues() {

    }

    public void setAction() {

    }

    public void speak() {

        originalDirection = direction;
        switch (gf.player.direction) {
            case up:
                direction = down;
                break;
            case down:
                direction = up;
                break;
            case left:
                direction = right;
                break;
            case right:
                direction = left;
                break;

            default:
                break;
        }
    }

    public void update() {

        getSolidAreaXY();
        setAction();
        setDialogue();

        collisionOn = false;
        cChecker.updateSoildArea(this, solidAreaMoveSize);

        if (gp.gameState != gp.nextMapState) {
            if (gf.player.inGamePanel) {
                cCheckerGP.checkTile(this);
                cCheckerGP.checkPlayer(this, gp.obj_Door);
                objIndex = cChecker.checkObject(this, gp.obj, false);
                objIndex = cChecker.checkDoor(this, gp.obj_Door, false);
            }
        }

        if (!collisionOn) {
            switch (direction) {
                case up:
                    entityY -= speed;
                    break;
                case down:
                    entityY += speed;
                    break;
                case left:
                    entityX -= speed;
                    break;
                case right:
                    entityX += speed;
                    break;
                default:
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > nextFrameSecond) {
            if (spriteNum == 1) {
                switch (spriteLoop) {
                    case 1:
                        spriteNum = 2;
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    default:
                        break;
                }
            } else if (spriteNum == 2) {
                spriteNum = 1;
                spriteLoop = 2;
            } else if (spriteNum == 3) {
                spriteNum = 1;
                spriteLoop = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2D) {
        // g2D.setColor(Color.white);
        // g2D.fillRect(entityX, entityY, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case up:
                switch (spriteNum) {
                    case 1:
                        image = up1;
                        break;
                    case 2:
                        image = up2;
                        break;
                    case 3:
                        image = up3;
                        break;
                    default:
                        break;
                }
                break;
            case down:
                switch (spriteNum) {
                    case 1:
                        image = down1;
                        break;
                    case 2:
                        image = down2;
                        break;
                    case 3:
                        image = down3;
                        break;
                    default:
                        break;
                }
                break;

            case left:
                switch (spriteNum) {
                    case 1:
                        image = left1;
                        break;
                    case 2:
                        image = left2;
                        break;
                    case 3:
                        image = left3;
                        break;
                    default:
                        break;
                }
                break;

            case right:
                switch (spriteNum) {
                    case 1:
                        image = right1;
                        break;
                    case 2:
                        image = right2;
                        break;
                    case 3:
                        image = right3;
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }

        g2D.drawImage(image, entityX, entityY, null);

        // draw solidArea
        // g2D.setColor(Color.RED);
        // g2D.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);

        // draw entity size
        // g2D.setColor(Color.green);
        // g2D.drawRect(entityX, entityY, width, height);

    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getSolidAreaXY() {
        solidArea.x = entityX + solidAreaDefaultX;
        solidArea.y = entityY + solidAreaDefaultY;
    }

    public void setDialogue() {
    }
}
