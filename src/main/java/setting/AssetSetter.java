package setting;

import java.util.Arrays;
import java.util.Random;

import entity.NPC_Giant;
import object.*;
import panel.GamePanel;
import panel.LevelPanel;

public class AssetSetter {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lvp1;
    int tileSize;

    public String nOpenLeftWoodenDoorPath = "/objects/DoorLeft.png";
    public String nOpenRightWoodenDoorPath = "/objects/DoorRight.png";
    public String openLeftWoodenDoorPath = "/objects/OpenDoorLeft.png";
    public String openRightWoodenDoorPath = "/objects/OpenDoorRight.png";

    public int[] values = new int[20];
    public int pairValue;

    public AssetSetter(GameFrame gf) {
        this.gf = gf;
        this.gp = gf.gamePanel;
        this.lvp1 = gf.levelPanel[1];
        this.tileSize = gf.gamePanel.tileSize;
    }

    public void setPlayer(int x, int y) {

        gf.player.entityX = x;
        gf.player.entityY = y;
        gf.player.getSolidAreaXY();

        /*
         * e.g. x = (gf.gamePanel.screenWidth - gf.player.width) / 2;
         * e.g. y = gf.gamePanel.tileSize * 8;
         * e.g. speed = 4;
         */
    }

    public void setDoor_GP() {
        System.out.println("set GP door");
        gp.obj_Door[0] = new OBJ_Door(gf);
        OBJ_Door door = gp.obj_Door[0];

        door.objectX = 2 * tileSize;
        door.objectY = 0 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();
    }

    public void setNPC_GP() {
        gp.npc[0] = new NPC_Giant(gf);
    }

    public void setDoor_0_LV1P() {
        // Door 0
        lvp1.obj_Door[0] = new OBJ_Door(gf);
        OBJ_Door door = lvp1.obj_Door[0];

        door.Enbale = false;
        door.objectX = 2 * tileSize;
        door.objectY = 11 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();
    }

    public void setDoor_0_LVP(LevelPanel lvp) {

        // Door 0
        lvp.obj_Door[0] = new OBJ_Door(gf);
        OBJ_Door door = lvp.obj_Door[0];

        door.Enbale = false;
        door.objectX = 7 * tileSize;
        door.objectY = 11 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();
    }

    public void setDoor_LVP(LevelPanel lvp, int doorCapacity_1, int doorCapacity_2, int doorCapacity_3) {
        values = getRandomValue(null, 20);
        pairValue = getPairValue();
        System.out.println(Arrays.toString(values));

        // Door 1
        lvp.obj_Door[1] = new OBJ_Door(gf);
        OBJ_Door door = lvp.obj_Door[1];

        door.Enbale = true;
        door.objectX = 1 * tileSize;
        door.objectY = 0 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();

        door.pairValues = new int[doorCapacity_1];

        for (int i = 0; i < doorCapacity_1; i++) {
            door.pairValues[i] = values[i];
        }

        if (checkpair(door.pairValues)) {
            door.trueDoor = true;
        }
        System.out.println("Door_1(" + doorCapacity_1 + ") : " + door.trueDoor + Arrays.toString(door.pairValues));

        // Door 2
        lvp.obj_Door[2] = new OBJ_Door(gf);
        door = lvp.obj_Door[2];

        door.Enbale = true;
        door.objectX = 7 * tileSize;
        door.objectY = 0 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();

        door.pairValues = new int[doorCapacity_2];
        int[] doorValues = null;
        int limit = 20;
        if (doorCapacity_1 < limit) {
            doorValues = new int[limit - doorCapacity_1];
        }

        if (limit - doorCapacity_1 > doorCapacity_2) {
            limit = doorCapacity_1 + doorCapacity_2;
            doorValues = new int[doorCapacity_2];
        }
        for (int i = doorCapacity_1; i < limit; i++) {
            doorValues[i - doorCapacity_1] = values[i];
        }
        door.pairValues = getRandomValue(doorValues, doorCapacity_2);

        if (checkpair(door.pairValues)) {
            door.trueDoor = true;
        }
        System.out.println("Door_2(" + doorCapacity_2 + ") : " + door.trueDoor + Arrays.toString(door.pairValues));

        // Door 3
        lvp.obj_Door[3] = new OBJ_Door(gf);
        door = lvp.obj_Door[3];

        door.Enbale = true;
        door.objectX = 13 * tileSize;
        door.objectY = 0 * tileSize;
        door.setSolidArea();
        door.nOpenLeftImagePath = nOpenLeftWoodenDoorPath;
        door.nOpenRightImagePath = nOpenRightWoodenDoorPath;
        door.openLeftImagePath = openLeftWoodenDoorPath;
        door.openRightImagePath = openRightWoodenDoorPath;
        door.setDoorImage();

        door.pairValues = new int[doorCapacity_3];

        doorValues = null;
        limit = 20;
        if (doorCapacity_1 + doorCapacity_2 < limit) {
            doorValues = new int[limit - doorCapacity_1 - doorCapacity_2];
        }

        if (limit - doorCapacity_1 - doorCapacity_2 > doorCapacity_3) {
            limit = doorCapacity_1 + doorCapacity_2 + doorCapacity_3;
            doorValues = new int[doorCapacity_3];
        }
        for (int i = doorCapacity_1 + doorCapacity_2; i < limit; i++) {
            doorValues[i - doorCapacity_1 - doorCapacity_2] = values[i];
        }
        door.pairValues = getRandomValue(doorValues, doorCapacity_3);

        if (checkpair(door.pairValues)) {
            door.trueDoor = true;
        }
        System.out.println("Door_3(" + doorCapacity_3 + ") : " + door.trueDoor + Arrays.toString(door.pairValues));

    }

    public void setObject_LVP(LevelPanel lvp) {
        lvp.obj[0] = new Key(gf);
        SuperObject obj = lvp.obj[0];

        obj.objectX = (lvp.screenWidth - obj.width) / 2;
        obj.objectY = (lvp.screenHeigth - obj.height) / 2;
        obj.setSolidArea();
        obj.loadImage();
    }

    public void setSign_LVP(LevelPanel lvp) {
        // signs 0
        lvp.signs[0] = new Signs(gf);
        Signs signs = lvp.signs[0];

        signs.objectX = 3 * tileSize;
        signs.objectY = tileSize / 2;
        signs.setSolidArea();
        signs.loadImage();

        // signs 1
        lvp.signs[1] = new Signs(gf);
        signs = lvp.signs[1];

        signs.objectX = 9 * tileSize;
        signs.objectY = tileSize / 2;
        signs.setSolidArea();
        signs.loadImage();

        // signs 2
        lvp.signs[2] = new Signs(gf);
        signs = lvp.signs[2];

        signs.objectX = 14 * tileSize;
        signs.objectY = (1 * tileSize) + (tileSize / 2);
        signs.setSolidArea();
        signs.loadImage();
    }

    public int[] getRandomValue(int[] doorValues, int capacity) {
        Random r = new Random();
        int[] value = new int[capacity];
        boolean againt = true;
        int tamp = 1;
        int vLen = 1;

        if (doorValues == null) {
            value[0] = r.nextInt(20) + 1;
        } else {
            vLen = doorValues.length;
            for (int i = 0; i < vLen; i++) {
                value[i] = doorValues[i];
            }
        }

        for (int i = vLen; i < capacity; i++) {
            againt = true;
            while (againt) {
                tamp = r.nextInt(20) + 1;
                for (int j = i; j > 0; j--) {
                    if (tamp == value[j - 1]) {
                        againt = true;
                        break;
                    }
                    againt = false;
                }
            }
            value[i] = tamp;
        }
        return value;
    }

    public int getPairValue(/* OBJ_Door[] door */) {
        Random r = new Random();
        return r.nextInt(20) + 1;
        // while (true) {
        // int tamp = r.nextInt(20) + 1;
        // for (int i = 1; i < 4; i++) {
        // for (int j = 0; j < door[i].pairValues.length; j++) {
        // if (tamp == door[i].pairValues[j]) {
        // return tamp;
        // }
        // }
        // }
        // }
    }

    public boolean checkpair(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == pairValue)
                return true;
        }
        return false;
    }

}
