package setting.collisionChecker;

import entity.Entity;
import panel.LevelPanel;
import setting.GameFrame;

public class CollisionChecker_LVP {
    GameFrame gf;
    LevelPanel lvp[];

    int tileSize;
    int screenWidth;
    int MAXLimit = 19;
    int minLimit = 3;

    LevelPanel nextMap;
    String panelName = "";

    public CollisionChecker_LVP(GameFrame gf) {
        this.gf = gf;
        this.lvp = new LevelPanel[gf.numberOfLevel];
        for (int i = 0; i < gf.numberOfLevel; i++) {
            this.lvp[i] = gf.levelPanel[i];
        }

        this.tileSize = lvp[0].tileSize;
        this.screenWidth = lvp[0].screenWidth;
    }

    public void checkTile(Entity entity, LevelPanel lvp) {
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
                tileNum1 = lvp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = lvp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (lvp.tileM.tile[tileNum1].collision || lvp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed * 2) / tileSize;
                tileNum1 = lvp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = lvp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (lvp.tileM.tile[tileNum1].collision || lvp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed * 2) / tileSize;
                tileNum1 = lvp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = lvp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (lvp.tileM.tile[tileNum1].collision || lvp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed * 2) / tileSize;
                tileNum1 = lvp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = lvp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (lvp.tileM.tile[tileNum1].collision || lvp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            default:

                break;
        }
    }

    public void checkNextMap(Entity entity, LevelPanel lvp) {

        checkNextMapPanel();
        nextMap.mapFilePath = "/map/levelMapRoom";

        if (entity.solidArea.intersects(lvp.nextMapDoor_1)) {

            lvp.gameState = lvp.nextMapState;
            gf.player.numberOfKeys = 0;

            if (lvp.obj_Door[1] != null) {
                if (lvp.obj_Door[1].trueDoor) {

                    // if (lvp.nextMapDoorCapacity_1 > lvp.nextMapDoorCapacity_2) {
                    // if (lvp.nextMapDoorCapacity_1 > lvp.nextMapDoorCapacity_3) {
                    // decreasCapacity(lvp);
                    // } else if (lvp.nextMapDoorCapacity_1 <= lvp.nextMapDoorCapacity_3) {
                    // operateCapacity(lvp);
                    // } else if (lvp.nextMapDoorCapacity_2 == lvp.nextMapDoorCapacity_3) {
                    // operateCapacity(lvp);
                    // }
                    // } else if (lvp.nextMapDoorCapacity_1 == lvp.nextMapDoorCapacity_2) {
                    // if (lvp.nextMapDoorCapacity_1 > lvp.nextMapDoorCapacity_3) {
                    // operateCapacity(lvp);
                    // } else if (lvp.nextMapDoorCapacity_1 <= lvp.nextMapDoorCapacity_3) {
                    // increaseCapacity(lvp);
                    // }
                    // } else if (lvp.nextMapDoorCapacity_1 < lvp.nextMapDoorCapacity_2) {
                    // if (lvp.nextMapDoorCapacity_1 > lvp.nextMapDoorCapacity_3) {
                    // operateCapacity(lvp);
                    // } else if (lvp.nextMapDoorCapacity_1 <= lvp.nextMapDoorCapacity_3) {
                    // increaseCapacity(lvp);
                    // }
                    // }

                    changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_1, lvp.nextMapDoorCapacity_2,
                            lvp.nextMapDoorCapacity_3);

                    limitCapacity(lvp);
                    setNextMapDoorCapacity(lvp);
                    nextMapPortal(lvp);

                    changeNextPanelState();

                } else if (!lvp.obj_Door[1].trueDoor) {
                    gf.layout.show(gf.cardPanel, gf.gameOver);
                    changeGameOverState();
                }

            }
        }

        if (entity.solidArea.intersects(lvp.nextMapDoor_2)) {

            lvp.gameState = lvp.nextMapState;
            gf.player.numberOfKeys = 0;

            if (lvp.obj_Door[2] != null) {
                if (lvp.obj_Door[2].trueDoor) {

                    changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_2, lvp.nextMapDoorCapacity_1,
                            lvp.nextMapDoorCapacity_3);

                    limitCapacity(lvp);
                    setNextMapDoorCapacity(lvp);
                    nextMapPortal(lvp);

                    changeNextPanelState();

                } else if (!lvp.obj_Door[2].trueDoor) {
                    gf.layout.show(gf.cardPanel, gf.gameOver);
                    changeGameOverState();
                }

            }
        }

        if (entity.solidArea.intersects(lvp.nextMapDoor_3)) {

            lvp.gameState = lvp.nextMapState;
            gf.player.numberOfKeys = 0;

            if (lvp.obj_Door[3] != null) {
                if (lvp.obj_Door[3].trueDoor) {

                    changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_3, lvp.nextMapDoorCapacity_2,
                            lvp.nextMapDoorCapacity_1);

                    limitCapacity(lvp);
                    setNextMapDoorCapacity(lvp);
                    nextMapPortal(lvp);

                    changeNextPanelState();

                } else if (!lvp.obj_Door[3].trueDoor) {

                    gf.layout.show(gf.cardPanel, gf.gameOver);
                    changeGameOverState();
                }

            }
        }
    }

    public void limitCapacity(LevelPanel currentlvp) {
        // At least 3%
        if (currentlvp.nextMapDoorCapacity_1 < minLimit) {
            currentlvp.nextMapDoorCapacity_1 = minLimit;
        }
        if (currentlvp.nextMapDoorCapacity_2 < minLimit) {
            currentlvp.nextMapDoorCapacity_2 = minLimit;
        }
        if (currentlvp.nextMapDoorCapacity_3 < minLimit) {
            currentlvp.nextMapDoorCapacity_3 = minLimit;
        }
        // most 95%
        if (currentlvp.nextMapDoorCapacity_1 > MAXLimit) {
            currentlvp.nextMapDoorCapacity_1 = MAXLimit;
        }
        if (currentlvp.nextMapDoorCapacity_2 > MAXLimit) {
            currentlvp.nextMapDoorCapacity_2 = MAXLimit;
        }
        if (currentlvp.nextMapDoorCapacity_3 > MAXLimit) {
            currentlvp.nextMapDoorCapacity_3 = MAXLimit;
        }
    }

    public void decreasCapacity(LevelPanel currentlvp) {
        currentlvp.nextMapDoorCapacity_1 -= 4; // Door 1 -20%
        currentlvp.nextMapDoorCapacity_2 -= 3; // Door 2 -15%
        currentlvp.nextMapDoorCapacity_3 -= 2; // Door 3 -10%
    }

    public void operateCapacity(LevelPanel currentlvp) {
        currentlvp.nextMapDoorCapacity_1 -= 2; // Door 1 -10%
        currentlvp.nextMapDoorCapacity_2 -= 1; // Door 2 -5%
        currentlvp.nextMapDoorCapacity_3 += 1; // Door 3 +5%
    }

    public void increaseCapacity(LevelPanel currentlvp) {
        currentlvp.nextMapDoorCapacity_1 += 3; // Door 1 +15%
        currentlvp.nextMapDoorCapacity_2 += 3; // Door 2 +15%
        currentlvp.nextMapDoorCapacity_3 += 3; // Door 3 +15%
    }

    public void setNextMapDoorCapacity(LevelPanel currentlvp) {
        nextMap.nextMapDoorCapacity_1 = currentlvp.nextMapDoorCapacity_1;
        nextMap.nextMapDoorCapacity_2 = currentlvp.nextMapDoorCapacity_2;
        nextMap.nextMapDoorCapacity_3 = currentlvp.nextMapDoorCapacity_3;
    }

    public void nextMapPortal(LevelPanel currentlvp) {

        gf.layout.show(gf.cardPanel, panelName);
        gf.aSetter.setObject_LVP(nextMap);
        gf.aSetter.setDoor_0_LVP(nextMap);
        gf.aSetter.setDoor_LVP(nextMap,
                currentlvp.nextMapDoorCapacity_1,
                currentlvp.nextMapDoorCapacity_2,
                currentlvp.nextMapDoorCapacity_3);

        nextMap.x = (screenWidth - gf.player.width) / 2;
        nextMap.setupGame();
        nextMap.requestFocusInWindow();
        nextMap.startGameThread();
    }

    public void checkNextMapPanel() {
        if (gf.player.inLevelPanel[1]) {
            panelName = gf.lv[2];
            nextMap = lvp[2];
        } else if (gf.player.inLevelPanel[2]) {
            panelName = gf.lv[3];
            nextMap = lvp[3];
        } else if (gf.player.inLevelPanel[3]) {
            panelName = gf.lv[2];
            nextMap = lvp[2];
        } else {
            panelName = gf.title;
            nextMap = null;
        }
    }

    public void changeNextPanelState() {

        if (gf.player.inLevelPanel[1]) {
            gf.player.inLevelPanel[1] = false;
            gf.player.inLevelPanel[2] = true;
        }

        else if (gf.player.inLevelPanel[2]) {
            gf.player.inLevelPanel[2] = false;
            gf.player.inLevelPanel[3] = true;
        }

        else if (gf.player.inLevelPanel[3]) {
            gf.player.inLevelPanel[3] = false;
            gf.player.inLevelPanel[2] = true;
        }
    }

    public void changeGameOverState() {
        for (int i = 1; i < gf.numberOfLevel; i++) {
            if (gf.player.inLevelPanel[i]) {
                gf.player.inLevelPanel[i] = false;
            }
        }
    }

    public void changeNextDoorCapacity(LevelPanel currentlvp, int main, int x, int y) {
        if (main > x) {
            if (main > y) {
                decreasCapacity(currentlvp);
            }

            else if (main <= y) {
                operateCapacity(currentlvp);
            }

            else if (x == y) {
                operateCapacity(currentlvp);
            }
        }

        else if (main == x) {
            if (main > y) {
                operateCapacity(currentlvp);
            }

            else if (main <= y) {
                increaseCapacity(currentlvp);
            }
        }

        else if (main < x) {
            if (main > y) {
                operateCapacity(currentlvp);
            }

            else if (main <= y) {
                increaseCapacity(currentlvp);
            }
        }
    }
}
