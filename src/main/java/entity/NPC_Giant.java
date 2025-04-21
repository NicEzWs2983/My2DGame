package entity;

import java.awt.Rectangle;
import java.util.Random;

import setting.GameFrame;

public class NPC_Giant extends Entity {

    public NPC_Giant(GameFrame gf) {
        super(gf);

        setDefaultValues();
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {

        up1 = setup("/nudeplayer/NPC_giant/giant_up1.png");
        up2 = setup("/nudeplayer/NPC_giant/giant_up2.png");
        up3 = setup("/nudeplayer/NPC_giant/giant_up3.png");

        down1 = setup("/nudeplayer/NPC_giant/giant_down1.png");
        down2 = setup("/nudeplayer/NPC_giant/giant_down2.png");
        down3 = setup("/nudeplayer/NPC_giant/giant_down3.png");

        left1 = setup("/nudeplayer/NPC_giant/giant_left1.png");
        left2 = setup("/nudeplayer/NPC_giant/giant_left2.png");
        left3 = setup("/nudeplayer/NPC_giant/giant_left3.png");

        right1 = setup("/nudeplayer/NPC_giant/giant_right1.png");
        right2 = setup("/nudeplayer/NPC_giant/giant_right2.png");
        right3 = setup("/nudeplayer/NPC_giant/giant_right3.png");
    }

    public void setDefaultValues() {
        name = "Giant";
        direction = down;
        speedDefault = 1;
        speed = speedDefault;
        entityX = 100;
        entityY = 100;
        width = tileSize * 2;
        height = tileSize * 2;
        solidArea = new Rectangle(width / 3, (height / 3) * 2, width / 3, height / 3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidAreaDefaultWidth = solidArea.width;
        solidAreaDefaultHeight = solidArea.height;
        solidAreaMoveSize = 4;
        nextFrameSecond = 15;
    }

    public void setDialogue() {
        dialogues[0] = "Hello! Are you a noob?";
        dialogues[1] = "I'm just a giant, nothing more.";
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter % 3 == 1)
            speed = speedDefault;
        else
            speed = 0;

        if (actionLockCounter == 360 || collisionOn) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            // int i = 1;

            if (i < 25)
                direction = up;
            else if (i < 50)
                direction = down;
            else if (i < 75)
                direction = left;
            else
                direction = right;

            actionLockCounter = 0;
        }
    }

    public void speak() {
        gf.ui.currentDialogue = dialogues[dialogueIndex];
        super.speak();
    }

}
