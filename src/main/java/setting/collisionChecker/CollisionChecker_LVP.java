package setting.collisionChecker;

import java.util.Arrays;

import entity.Entity;
import panel.LevelPanel;
import setting.GameFrame;

public class CollisionChecker_LVP {
    GameFrame gf;
    LevelPanel lvp[];

    int tileSize;
    int screenWidth;
    int MAXLimit = 17;
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

                    int[] values = changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_1,
                            lvp.nextMapDoorCapacity_2, lvp.nextMapDoorCapacity_3);
                    lvp.nextMapDoorCapacity_1 = values[0];
                    lvp.nextMapDoorCapacity_2 = values[1];
                    lvp.nextMapDoorCapacity_3 = values[2];

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

                    int[] values = changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_2, lvp.nextMapDoorCapacity_1,
                            lvp.nextMapDoorCapacity_3);
                    lvp.nextMapDoorCapacity_2 = values[0];
                    lvp.nextMapDoorCapacity_1 = values[1];
                    lvp.nextMapDoorCapacity_3 = values[2];

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

                    int[] values = changeNextDoorCapacity(lvp, lvp.nextMapDoorCapacity_3, lvp.nextMapDoorCapacity_2,
                            lvp.nextMapDoorCapacity_1);
                    lvp.nextMapDoorCapacity_3 = values[0];
                    lvp.nextMapDoorCapacity_2 = values[1];
                    lvp.nextMapDoorCapacity_1 = values[2];

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
        if (gf.player.level % 5 == 0 && MAXLimit < 19) {
            MAXLimit++;
            // System.out.println("MAXLimit++");
        }
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

    public int[] decreasCapacity(int max, int midle, int min) {
        if (gf.player.level < 5) {
            max = max;
            midle += 1;
            min += 2;
        } else if (gf.player.level < 10) {
            max -= 1;
            midle = midle;
            min += 1;
        } else if (gf.player.level < 15) {
            max -= 2;
            midle -= 1;
            min = min;
        } else if (gf.player.level < 20) {
            max -= 3;
            midle -= 2;
            min -= 1;
        } else {
            max -= 4; // Door -20%
            midle -= 3; // Door -15%
            min -= 2; // Door -10%
        }
        return new int[] { max, midle, min };
    }

    public int[] operateCapacity(int midle, int max, int min) {
        if (gf.player.level < 5) {
            max += 2;
            midle += 5;
            min += 6;
        } else if (gf.player.level < 10) {
            max += 1;
            midle += 4;
            min += 5;
        } else if (gf.player.level < 15) {
            max = max;
            midle += 3;
            min += 4;
        } else if (gf.player.level < 20) {
            max -= 1;
            midle += 2;
            min += 3;
        } else {
            max -= 2; // Door -10%
            midle += 1; // Door +5%
            min += 2; // Door +10%
        }

        return new int[] { midle, max, min };
    }

    public int[] increaseCapacity(int min, int max, int midle) {
        if (gf.player.level < 5) {
            max += 7;
            midle += 7;
            min += 7;
        } else if (gf.player.level < 10) {
            max += 6;
            midle += 6;
            min += 6;
        } else if (gf.player.level < 15) {
            max += 5;
            midle += 5;
            min += 5;
        } else if (gf.player.level < 20) {
            max += 4;
            midle += 4;
            min += 4;
        } else {
            max += 3; // Door 1 +15%
            midle += 3; // Door 2 +15%
            min += 3; // Door 3 +15%
        }

        return new int[] { min, max, midle };
    }

    public int[] operateCapacity_2(int max, int midle, int min) {
        // System.out.println(max + " , " + midle + " , " + min);
        if (midle > 10) {
            // System.out.println(midle + " > 10");
            max -= 3; // -15%
            midle -= 2; // -10%
            min += 2; // +10%
        } else if (midle == 10) {
            // System.out.println(midle + " == 10");
            max -= 2; // -10%
            midle -= 1; // -5%
            min += 2; // +10%
        } else if (midle < 10) {
            // System.out.println(midle + " < 10");
            max -= 1; // -5%
            midle += 3; // +15%
            min += 3; // +15%
        }
        return new int[] { max, midle, min };
    }

    public void setNextMapDoorCapacity(LevelPanel currentlvp) {
        nextMap.nextMapDoorCapacity_1 = currentlvp.nextMapDoorCapacity_1;
        nextMap.nextMapDoorCapacity_2 = currentlvp.nextMapDoorCapacity_2;
        nextMap.nextMapDoorCapacity_3 = currentlvp.nextMapDoorCapacity_3;
    }

    public void nextMapPortal(LevelPanel currentlvp) {

        gf.player.level++;
        gf.player.numberOfKeys = 0;
        gf.layout.show(gf.cardPanel, panelName);
        gf.aSetter.setObject_LVP(nextMap);
        gf.aSetter.setSign_LVP(nextMap);
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
        gf.gameOverPanel.gameState = gf.gameOverPanel.playState;
        gf.gameOverPanel.startGameThread();
    }

    public int[] changeNextDoorCapacity(LevelPanel currentlvp, int main, int x, int y) {
        int[] values = new int[3];
        if (x > y) {
            // System.out.println("1. x > y");

            if (main > x) {
                values = decreasCapacity(main, x, y);
            } else if (x > main && main > y) {
                values = operateCapacity(main, x, y);
            } else if (y > main) {
                values = increaseCapacity(main, x, y);
            }

            if (values[0] != 0) {
                main = values[0];
                x = values[1];
                y = values[2];
            }

            if (main == y) {
                values = operateCapacity_2(x, main, y);
                // System.out.println(Arrays.toString(values));
                x = values[0];
                main = values[1];
                y = values[2];
            } else if (main == x) {
                values = operateCapacity_2(main, x, y);
                // System.out.println(Arrays.toString(values));
                main = values[0];
                x = values[1];
                y = values[2];
            }
        }

        else if (y > x) {
            // System.out.println("1. y > x");
            if (main > y) {
                values = decreasCapacity(main, y, x);
            } else if (y > main && main > x) {
                values = operateCapacity(main, y, x);
            } else if (x > main) {
                values = increaseCapacity(main, y, x);
            }

            if (values[0] != 0) {
                main = values[0];
                y = values[1];
                x = values[2];
            }

            if (main == y) {
                values = operateCapacity_2(main, y, x);
                // System.out.println(Arrays.toString(values));
                main = values[0];
                y = values[1];
                x = values[2];
            } else if (main == x) {
                values = operateCapacity_2(y, main, x);
                // System.out.println(Arrays.toString(values));
                y = values[0];
                main = values[1];
                x = values[2];
            }
        }

        else if (main == x && main == y) {
            // System.out.println("1. x == main == y ");
            if (main == MAXLimit) {
                main -= 11;
                x -= 9;
                y -= 7;
            } else if (main == 3) {
                main += 12;
                x += 10;
                y += 8;
            } else if (main > 15) {
                main -= 3;
                x -= 2;
                y -= 1;
            } else if (main < 15 && main > 6) {
                values = operateCapacity(x, main, y);
                x = values[0];
                main = values[1];
                y = values[2];
            } else if (main < 6) {
                y += 3;
                x += 2;
                main += 1;
            }
        }

        else if (x == y) {
            // System.out.println("1. x == y");
            if (main > x) {
                values = operateCapacity_2(main, x, y);
                main = values[0];
                x = values[1];
                y = values[2];
            } else if (y > main) {
                values = operateCapacity_2(x, y, main);
                x = values[0];
                y = values[1];
                main = values[2];
            }
        }
        return new int[] { main, x, y };
    }
}
