package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * Entity
 */
public class Entity {
    protected GamePanel gp;
    protected int worldX, worldY;
    protected int speed;
    protected BufferedImage sDown, sLeft, sRight, sUp, wDown1, wDown2, wLeft1, wLeft2, wRight1, wRight2, wUp1, wUp2;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    protected Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected boolean collisionOn = false;
    protected int actionLockCounter = 0;
    protected boolean invincible = false;
    protected int invincibleCounter = 0;
    protected String dialogues[] = new String[20];
    protected int dialogueIndex = 0;
    protected int type;

    // CHARACTER STATUS
    protected int maxLife;
    protected int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void getImage(String path) {
        try {
            sDown = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/stand_down.png"));
            sLeft = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/stand_left.png"));
            sRight = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/stand_right.png"));
            sUp = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/stand_up.png"));
            wDown1 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_down1.png"));
            wDown2 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_down2.png"));
            wLeft1 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_left1.png"));
            wLeft2 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_left2.png"));
            wRight1 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_right1.png"));
            wRight2 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_right2.png"));
            wUp1 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_up1.png"));
            wUp2 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/" + path + "/walk_up2.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction() {}
    public void speak() {}
    public void update() {
        setAction();
        
        collisionOn = false;
        gp.getcChecker().checkTile(this);
        gp.getcChecker().checkObject(this, false);
        boolean contactPlayer = gp.getcChecker().checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if (!gp.getPlayer().isInvincible()) {
                gp.getPlayer().life--;
                gp.getPlayer().invincible = true;
            }
        }
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
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
            worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
            worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
            worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
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
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public int getSolidAreaX() {
        return solidArea.x;
    }
    public void setSolidAreaX(int x) {
        solidArea.x = x;
    }
    public int getSolidAreaY() {
        return solidArea.y;
    }
    public void setSolidAreaY(int y) {
        solidArea.y = y;
    }
    public String getDirection() {
        return direction;
    }
    public int getSpeed() {
        return speed;
    }
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public int getLife() {
        return life;
    }
    public boolean isInvincible() {
        return invincible;
    }
}