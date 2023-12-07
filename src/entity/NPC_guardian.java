package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_guardian extends Entity {
    public NPC_guardian(GamePanel gp) {
        super(gp);
        direction = "sDown";
        speed = 1;
        getImage("npc/guardian");
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0] = "Greetings, seeker of the legendary treasure. \nBefore I reveal the secrets of the magical gate, \nI have a personal request.";
        dialogues[1] = "My beloved Vaporeon, Aquara, has gone \nmissing. Can you help me find her?";
        dialogues[2] = "Aquara is a gentle Water-type Pokémon. \nShe often wanders near the riverbank to the \neast of Clairbois.";
        dialogues[3] = "Please bring her back to me, and I shall aid \nyou in your quest for the enchanted artifacts.";
        dialogues[4] = "Thanks for bringing me back my Vaporeon! \nSeeking the legendary treasure, are you?";
        dialogues[5] = "To open the magical gate, you must gather \nthe enchanted artifacts scattered around the \nvillage.";
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
        gp.getPlayer().setFindGuardian(true);
        if (gp.getPlayer().getHasArtifact() == 0) {
            if (dialogues[dialogueIndex] == null || dialogueIndex == 4) {
                dialogueIndex = 0;
            }
            gp.getUi().setCurrentDialogue(dialogues[dialogueIndex]);
            dialogueIndex++;
        } else {
            if (dialogues[dialogueIndex] == null || dialogueIndex > 5) {
                dialogueIndex = 4;
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
