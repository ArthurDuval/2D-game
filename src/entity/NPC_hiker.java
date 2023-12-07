package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_hiker extends Entity {
    public NPC_hiker(GamePanel gp) {
        super(gp);
        direction = "sDown";
        speed = 1;
        getImage("npc/hiker");
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0] = "Hey, trainer! You look like you've got the spirit \nof adventure. Listen up, I've explored many \nregions. Want some tips?";
        dialogues[1] = "Every step you take is a lesson. Enjoy the \njourney, and you'll discover more than you \never imagined.";
        dialogues[2] = "Guardian, you say? She's a wanderer like me. \nI last saw her heading towards the hills to the \nwest. Maybe she found something there.";
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "wUp";
            } else if (i > 25 && i <= 50) {
                direction = "wDown";
            } else if(i > 50 && i <= 75) {
                direction = "wLeft";
            } else if (i > 75 && i <= 100) {
                direction = "wRight";
            }
            actionLockCounter = 0;
        }
    }
    @Override
    public void speak() {
        if (!gp.getPlayer().isFindGuardian()) {
            if (dialogues[dialogueIndex] == null || dialogueIndex > 2) {
                dialogueIndex = 0;
            }
            gp.getUi().setCurrentDialogue(dialogues[dialogueIndex]);
            dialogueIndex++;
        } else {
            if (dialogues[dialogueIndex] == null || dialogueIndex > 1) {
                dialogueIndex = 0;
            }
            gp.getUi().setCurrentDialogue(dialogues[dialogueIndex]);
            dialogueIndex++;
        }
        switch (gp.getPlayer().getDirection()) {
            case "wUp": direction = "sDown"; break;
            case "wDown": direction = "sUp"; break;
            case "wLeft": direction = "sRight"; break;
            case "wRight": direction = "sLeft"; break;
        }
    }
}
