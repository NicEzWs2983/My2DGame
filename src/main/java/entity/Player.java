package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import panel.LevelPanel;
import setting.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

    KeyHandler keyH;

    public boolean betweenNPCAndTile = false;
    public boolean inGamePanel = false;
    public boolean inLevel1Panel = false;
    public boolean inLevel2Panel = false;
    public boolean inLevel3Panel = false;
    public int numberOfKeys = 0;

    public Player(GameFrame gf, KeyHandler keyH) {
        super(gf);
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 22;
        solidArea.height = tileSize - solidAreaDefaultY - 2;
        solidAreaDefaultWidth = solidArea.width;
        solidAreaDefaultHeight = solidArea.height;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        speed = 4;
        solidAreaMoveSize = speed;
        direction = "up";

    }

    public void getPlayerImage() {

        up1 = setup("/nudeplayer/playerBack.png");
        up2 = setup("/nudeplayer/playerBackWalk1.png");
        up3 = setup("/nudeplayer/playerBackWalk2.png");

        down1 = setup("/nudeplayer/playerFront.png");
        down2 = setup("/nudeplayer/playerFrontWalk1.png");
        down3 = setup("/nudeplayer/playerFrontWalk2.png");

        left1 = setup("/nudeplayer/playerLeft.png");
        left2 = setup("/nudeplayer/playerLeftWalk1.png");
        left3 = setup("/nudeplayer/playerLeftWalk2.png");

        right1 = setup("/nudeplayer/playerRight.png");
        right2 = setup("/nudeplayer/playerRightWalk1.png");
        right3 = setup("/nudeplayer/playerRightWalk2.png");
    }

    public void update() {

        getSolidAreaXY();

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = up;
            } else if (keyH.downPressed) {
                direction = down;
            } else if (keyH.leftPressed) {
                direction = left;
            } else if (keyH.rightPressed) {
                direction = right;
            }

            cChecker.updateSoildArea(this, solidAreaMoveSize);

            collisionOn = false;
            collisionOnPlayer = false;
            betweenNPCAndTile = false;

            if (inGamePanel && gp.gameState == gp.playState) {
                cCheckerGP.checkTile(this);
                cCheckerGP.checkNextMap(this);
                check_GP();
            }
            if (inLevel1Panel) {
                cCheckerLVP.checkTile(this, lv1p);
                cCheckerLVP.checkNextMap(this, lv1p);
                check_LVP(lv1p);
            }
            if (inLevel2Panel) {
                cCheckerLVP.checkTile(this, lv2p);
                cCheckerLVP.checkNextMap(this, lv2p);
                check_LVP(lv2p);
            }
            if (inLevel3Panel) {
                cCheckerLVP.checkTile(this, lv3p);
                check_LVP(lv3p);
            }

            boolean inFrame = true;
            inFrame = solidArea.x - speed > 0 && solidArea.x + solidArea.width + speed < gp.screenWidth
                    && solidArea.y - speed > 0 && solidArea.y + solidArea.height + speed * 2 < gp.screenHeigth;

            if (!collisionOn && inFrame) {
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
            if (spriteCounter > 6) {
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
        } else if (keyH.enterPressed) {
            cChecker.updateSoildArea(this, solidAreaMoveSize * 5);

            if (inGamePanel) {
                check_GP();
            }
            if (inLevel1Panel) {
                check_LVP(lv1p);
            }
            if (inLevel2Panel) {
                check_LVP(lv2p);
            }
            if (inLevel3Panel) {
                check_LVP(lv3p);
            }

        } else
            spriteNum = 1;

    }

    public void check_GP() {
        // objIndex = cChecker.checkObject(this, gp.obj, true);
        // eAction_GP.touchObject(objIndex);

        doorIndex = cChecker.checkDoor(this, gp.obj_Door, true);
        eAction_GP.touchDoor(doorIndex);

        npcIndex = cChecker.checkEntity(this, gp.npc);
        eAction_GP.touchNPC(npcIndex);
    }

    public void check_LVP(LevelPanel lvp) {
        doorIndex = cChecker.checkDoor(this, lvp.obj_Door, true);
        eAction_LVP.touchDoor(lvp, doorIndex, numberOfKeys);

        objIndex = cChecker.checkObject(this, lvp.obj, true);
        eAction_LVP.touchObject(lvp, objIndex);
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

        // g2D.setColor(Color.RED);
        // g2D.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        // g2D.setColor(Color.green);
        // g2D.drawRect(playerX, playerY, gp.tileSize, gp.tileSize);

    }

}
