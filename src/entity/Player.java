package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
/**
 * Player
 */
public class Player extends Entity {
    private KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private int hasArtifact = 0;
    private boolean findGuardian = false;
    private boolean gotVaporeon = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() /2);
        solidArea = new Rectangle();
        solidArea.x = 20;
        solidArea.y = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 20;
        setDefaultValues();
        getImage("player");
    }
    public void setDefaultValues() {
        worldX = gp.getTileSize() * 23;
        worldY = gp.getTileSize() * 21;
        speed = 5;
        direction = "sDown";

        maxLife = 6;
        life = maxLife;
    }
    public void update() {
        // CONTROL PLAYER + CHANGE PLAYER'S TILES
        if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
            if (keyH.isUpPressed()) {
                direction = "wUp";
            } else if (keyH.isDownPressed()) {
                direction = "wDown";
            } else if (keyH.isRightPressed()) {
                direction = "wRight";
            } else if (keyH.isLeftPressed()) {
                direction = "wLeft";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.getcChecker().checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNpc());
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            contactMonster(monsterIndex);
            if (gp.getPlayer().getLife() == 0) {
                gp.getUi().setGameFinished(true);
            }

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "wUp":
                        worldY -= speed;
                        break;
                    case "wDown":
                        worldY += speed;
                        break;
                    case "wLeft":
                        worldX -= speed;
                        break;
                    case "wRight":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        } else {
            switch (keyH.getLastReleased()) {
                case "z":
                    direction = "sUp";
                    break;
                case "s":
                    direction = "sDown";
                    break;
                case "d":
                    direction = "sRight";
                    break;
                case "q":
                    direction = "sLeft";
                    break;
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.getObj(i).getName();

            switch (objectName) {
                case "vaporeon":
                    gp.playSE(2);
                    if (gp.getNpc().length != 0) {
                        gp.getNpc(i).setDialogueIndex(4);
                        gotVaporeon = true;
                    }
                    gp.setObj(i, null);
                    gp.getUi().showMessage("You got a Vaporeon!");
                    break;
                case "rock":
                    if (hasArtifact > 0) {
                        gp.playSE(3);
                        gp.setObj(i, null);
                    }
                    break;
                case "runningShoes":
                    gp.playSE(1);
                    speed += 4;
                    gp.setObj(i, null);
                    gp.getUi().showMessage("You got RunningShoes!");
                    break;
                case "cup":
                    gp.playSE(1);
                    hasArtifact++;
                    gp.setObj(i, null);
                    gp.getUi().showMessage("You got GoldenCup!");
                    break;
                case "mask":
                    gp.playSE(1);
                    hasArtifact++;
                    gp.setObj(i, null);
                    gp.getUi().showMessage("You got AntikMask!");
                    break;
                case "necklace":
                    gp.playSE(1);
                    hasArtifact++;
                    gp.setObj(i, null);
                    gp.getUi().showMessage("You got MightyNecklace!");
                    break;
            }
        }
    }
    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.getKeyH().isePressed()) {
                if (i == 2 && hasArtifact == 3 && gotVaporeon) {
                    gp.getUi().setGameFinished(true);
                } else {
                    gp.setGameState(gp.getDialogueState());
                    gp.getNpc(i).speak();
                }
            }
            gp.getKeyH().setePressed(false);
        }
    }
    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life--;
                invincible = true;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        
        switch (direction) {
            case "wUp":
                if (spriteNumber == 1) {
                    image = wUp1;
                } else if (spriteNumber == 2) {
                    image = wUp2;
                }
                break;
            case "wDown":
                if (spriteNumber == 1) {
                    image = wDown1;
                } else if (spriteNumber == 2) {
                    image = wDown2;
                }
                break;
            case "wLeft":
                if (spriteNumber == 1) {
                    image = wLeft1;
                } else if (spriteNumber == 2) {
                    image = wLeft2;
                }
                break;
            case "wRight":
                if (spriteNumber == 1) {
                    image = wRight1;
                } else if (spriteNumber == 2) {
                    image = wRight2;
                }
                break;
            case "sUp":
                image = sUp;
                break;
            case "sDown":
                image = sDown;
                break;
            case "sLeft":
                image = sLeft;
                break;
            case "sRight":
                image = sRight;
                break;
        }
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public int getHasArtifact() {
        return hasArtifact;
    }
    public boolean isFindGuardian() {
        return findGuardian;
    }
    public void setFindGuardian(boolean findGuardian) {
        this.findGuardian = findGuardian;
    }
}