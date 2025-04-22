package setting.collisionChecker;

import entity.Entity;
import object.*;
import setting.GameFrame;

public class CollisionChecker {
    GameFrame gf;

    int tileSize;

    public CollisionChecker(GameFrame gf) {
        this.gf = gf;
        this.tileSize = gf.gamePanel.tileSize;
    }

    public void updateSoildArea(Entity entity, int moveSize) {

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= moveSize;
                // index = checkIntersects(i, entity, player);
                break;

            case "down":
                entity.solidArea.y += moveSize;
                // index = checkIntersects(i, entity, player);
                break;

            case "left":
                entity.solidArea.x -= moveSize;
                // index = checkIntersects(i, entity, player);
                break;

            case "right":
                entity.solidArea.x += moveSize;
                // index = checkIntersects(i, entity, player);
                break;

            default:
                break;
        }
    }

    public int checkDoor(Entity entity, OBJ_Door[] obj_Door, boolean player) {
        int index = 999;

        for (int i = 0; i < obj_Door.length; i++) {
            if (obj_Door[i] != null) {

                index = checkIntersects(obj_Door, i, entity, player);
                if (index != 999) {
                    return index;
                }
            }
        }
        return index;
    }

    public int checkObject(Entity entity, SuperObject[] obj, boolean player) {
        int index = 999;

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                index = checkIntersects(obj, i, entity, player);
                if (index != 999) {
                    return index;
                }
            }
        }
        return index;
    }

    public int checkSigns(Entity entity, Signs[] signs, boolean player) {
        int index = 999;

        for (int i = 0; i < signs.length; i++) {
            if (signs[i] != null) {
                if (entity.solidArea.intersects(signs[i].solidArea2)) {
                    if (!player) {
                        entity.collisionOn = true;
                    } else {
                        if (signs[i].collision2) {
                            entity.collisionOn = true;
                        }
                        return i;
                    }
                } else if (entity.solidArea.intersects(signs[i].solidArea)) {
                    if (player) {
                        return i;
                    }
                }
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                // entity.solidArea.x = entity.entityX + entity.solidAreaDefaultX;
                // entity.solidArea.y = entity.entityY + entity.solidAreaDefaultY;
                // gp.obj[i].solidArea.x = gp.obj[i].objectX +
                // gp.obj[i].solidArea.x;
                // gp.obj[i].solidArea.y = gp.obj[i].objectY +
                // gp.obj[i].solidArea.y;

                index = checkIntersects(i, entity, target);
                if (index != 999) {
                    return index;
                }

                // entity.solidArea.x = entity.solidAreaDefaultX;
                // entity.solidArea.y = entity.solidAreaDefaultY;
                // gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                // gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }

    public int checkIntersects(SuperObject[] obj, int i, Entity entity, boolean player) {
        if (entity.solidArea.intersects(obj[i].solidArea)) {
            if (!player) {
                entity.collisionOn = true;
            } else {
                if (obj[i].collision) {
                    entity.collisionOn = true;
                }
                return i;
            }
        }
        return 999;
    }

    public int checkIntersects(int i, Entity entity, Entity[] target) {
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            return i;
        }
        return 999;
    }
}