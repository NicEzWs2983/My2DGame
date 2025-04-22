package setting.collisionChecker;

import java.awt.Rectangle;

import entity.*;
import object.SuperObject;
import panel.GamePanel;
import panel.LevelPanel;
import setting.GameFrame;

public class CollisionChecker_GP {

    GameFrame gf;
    GamePanel gp;
    LevelPanel lvp1;

    int tileSize;

    public CollisionChecker_GP(GameFrame gf) {
        this.gf = gf;
        this.gp = gf.gamePanel;
        this.lvp1 = gf.levelPanel[1];
        this.tileSize = gp.tileSize;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.entityX + entity.solidAreaDefaultX;
        int entityRightX = entityLeftX + entity.solidAreaDefaultWidth;
        int entityTopY = entity.entityY + entity.solidAreaDefaultY;
        int entityBottomY = entityTopY + entity.solidAreaDefaultHeight;

        int entityLeftCol = entityLeftX / tileSize;
        int entityRightCol = entityRightX / tileSize;
        int entityTopRow = entityTopY / tileSize;
        int entityBottomRow = entityBottomY / tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed * 2) / tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed * 2) / tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed * 2) / tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed * 2) / tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            default:

                break;
        }
    }

    public void checkNextMap(Entity entity) {
        Rectangle nextMapPortal = new Rectangle(tileSize * 2, 0, tileSize * 2, tileSize / 3);
        if (entity.solidArea.intersects(nextMapPortal)) {
            gp.gameState = gp.nextMapState;

            gf.player.inLevelPanel[1] = true;
            gf.player.numberOfKeys = 0;
            gf.player.level++;
            gf.layout.next(gf.cardPanel);
            gf.aSetter.setObject_LVP(lvp1);
            gf.aSetter.setDoor_0_LV1P();
            gf.aSetter.setDoor_LVP(lvp1, gp.nextMapDoorCapacity_1, gp.nextMapDoorCapacity_2, gp.nextMapDoorCapacity_3);

            lvp1.x = gf.player.entityX;
            lvp1.setupGame();
            lvp1.requestFocusInWindow();
            lvp1.startGameThread();
            lvp1.nextMapDoorCapacity_1 = gp.nextMapDoorCapacity_1;
            lvp1.nextMapDoorCapacity_2 = gp.nextMapDoorCapacity_2;
            lvp1.nextMapDoorCapacity_3 = gp.nextMapDoorCapacity_3;

            gf.player.inGamePanel = false;

        }
    }

    public void checkPlayer(Entity NPC, SuperObject[] obj) {

        int npcSolidAreaTopY = NPC.solidArea.y;
        int npcSolidAreaLeftX = NPC.solidArea.x;
        int npcSolidAreaBottomY = npcSolidAreaTopY + NPC.solidAreaDefaultHeight;
        int npcSolidAreaRightX = npcSolidAreaLeftX + NPC.solidAreaDefaultWidth;

        int npcEntityTopY = NPC.entityY + NPC.solidAreaDefaultY;
        int npcEntityLeftX = NPC.entityX + NPC.solidAreaDefaultX;
        int npcEntityBottomY = npcEntityTopY + NPC.solidAreaDefaultHeight;
        int npcEntityRightX = npcEntityLeftX + NPC.solidAreaDefaultWidth;

        int npcCheckObjectTop = npcSolidAreaTopY - gf.player.solidArea.height - gf.player.speed;
        int npcCheckObjectBottom = npcSolidAreaBottomY + gf.player.solidArea.height - gf.player.speed;
        int npcCheckObjectLeft = npcSolidAreaLeftX - gf.player.solidArea.width - gf.player.speed;
        int npcCheckObjectRight = npcSolidAreaRightX + gf.player.solidArea.width + gf.player.speed;

        int playerTopY = gf.player.entityY + gf.player.solidAreaDefaultY;
        int playerLeftX = gf.player.entityX + gf.player.solidAreaDefaultX;
        int playerBottomY = playerTopY + gf.player.solidAreaDefaultHeight;
        int playerRightX = playerLeftX + gf.player.solidAreaDefaultWidth;

        int topRow = playerTopY / tileSize;
        int boottomRow = playerBottomY / tileSize;
        int leftCol = playerLeftX / tileSize;
        int rightCol = playerRightX / tileSize;

        int tileNum1, tileNum2;
        int moveSize = NPC.speed * 2;

        switch (NPC.direction) {
            case "up":
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        if (obj[i].solidArea.intersectsLine(
                                npcSolidAreaLeftX, npcCheckObjectTop, npcSolidAreaRightX, npcCheckObjectTop)) {
                            NPC.collisionOn = true;
                        }
                    }
                }

                if (gf.player.solidArea.intersectsLine(
                        npcSolidAreaLeftX, npcSolidAreaTopY, npcSolidAreaRightX, npcSolidAreaTopY) ||
                        gf.player.solidArea.intersectsLine(
                                npcEntityLeftX, npcEntityTopY, npcEntityRightX, npcEntityTopY)) {

                    topRow = (playerTopY - gf.player.speed) / tileSize;
                    tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                    tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        gf.player.collisionOn = true;
                        gf.player.betweenNPCAndTile = true;
                    }

                    if (!gf.player.betweenNPCAndTile)
                        gf.player.entityY -= moveSize;
                    else if (gf.player.betweenNPCAndTile) {
                        NPC.speed = 0;
                    }
                }
                break;
            case "down":
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        if (obj[i].solidArea.intersectsLine(
                                npcSolidAreaLeftX, npcCheckObjectBottom, npcSolidAreaRightX, npcCheckObjectBottom)) {
                            NPC.collisionOn = true;
                        }
                    }
                }

                if (gf.player.solidArea.intersectsLine(
                        npcSolidAreaLeftX, npcSolidAreaBottomY, npcSolidAreaRightX, npcSolidAreaBottomY) ||
                        gf.player.solidArea.intersectsLine(
                                npcEntityLeftX, npcEntityBottomY, npcEntityRightX, npcEntityBottomY)) {

                    boottomRow = (playerBottomY + gf.player.speed) / tileSize;
                    tileNum1 = gp.tileM.mapTileNum[leftCol][boottomRow];
                    tileNum2 = gp.tileM.mapTileNum[rightCol][boottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        gf.player.collisionOn = true;
                        gf.player.betweenNPCAndTile = true;
                    }

                    if (!gf.player.betweenNPCAndTile)
                        gf.player.entityY += moveSize;
                    else if (gf.player.betweenNPCAndTile) {
                        NPC.speed = 0;
                    }
                }
                break;
            case "left":
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[i] != null) {
                        if (gp.obj[i].solidArea.intersectsLine(
                                npcCheckObjectLeft, npcSolidAreaTopY, npcCheckObjectLeft, npcSolidAreaBottomY)) {
                            NPC.collisionOn = true;
                        }
                    }
                }

                if (gf.player.solidArea.intersectsLine(
                        npcSolidAreaLeftX, npcSolidAreaTopY, npcSolidAreaLeftX, npcSolidAreaBottomY) ||
                        gf.player.solidArea.intersectsLine(
                                npcEntityLeftX, npcEntityTopY, npcEntityLeftX, npcEntityBottomY)) {

                    leftCol = (playerLeftX - gf.player.speed) / tileSize;
                    tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                    tileNum2 = gp.tileM.mapTileNum[leftCol][boottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        gf.player.collisionOn = true;
                        gf.player.betweenNPCAndTile = true;
                    }

                    if (!gf.player.betweenNPCAndTile)
                        gf.player.entityX -= moveSize;
                    else if (gf.player.betweenNPCAndTile) {
                        NPC.speed = 0;
                    }
                }
                break;
            case "right":
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[i] != null) {
                        if (gp.obj[i].solidArea.intersectsLine(
                                npcCheckObjectRight, npcSolidAreaTopY, npcCheckObjectRight, npcSolidAreaBottomY)) {
                            NPC.collisionOn = true;
                        }
                    }
                }

                if (gf.player.solidArea.intersectsLine(
                        npcSolidAreaRightX, npcSolidAreaTopY, npcSolidAreaRightX, npcSolidAreaBottomY) ||
                        gf.player.solidArea.intersectsLine(
                                npcEntityRightX, npcEntityTopY, npcEntityRightX, npcEntityBottomY)) {

                    rightCol = (playerRightX + gf.player.speed) / tileSize;
                    tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                    tileNum2 = gp.tileM.mapTileNum[rightCol][boottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        gf.player.collisionOn = true;
                        gf.player.betweenNPCAndTile = true;
                    }

                    if (!gf.player.betweenNPCAndTile)
                        gf.player.entityX += moveSize;
                    else if (gf.player.betweenNPCAndTile) {
                        NPC.speed = 0;
                    }

                }
                break;

            default:
                break;
        }
    }

}
