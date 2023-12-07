package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_oldLady extends Entity {
    public NPC_oldLady(GamePanel gp) {
        super(gp);
        direction = "sDown";
        speed = 1;
        getImage("npc/oldLady");
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0] = "Have you heard the tales of the mystical \nforest? They say only the Guardian holds the \nkey to unlock its secrets.";
        dialogues[1] = "Looking for the Guardian, are you? I heard she \noften meditates by the ancient tree near the \nvillage entrance.";
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
            if (dialogues[dialogueIndex] == null || dialogueIndex > 1) {
                dialogueIndex = 0;
            }
            gp.getUi().setCurrentDialogue(dialogues[dialogueIndex]);
            dialogueIndex++;
        } else {
            gp.getUi().setCurrentDialogue(dialogues[0]);
        }
        switch (gp.getPlayer().getDirection()) {
            case "wUp": direction = "sDown"; break;
            case "wDown": direction = "sUp"; break;
            case "wLeft": direction = "sRight"; break;
            case "wRight": direction = "sLeft"; break;
        }
    }
}
