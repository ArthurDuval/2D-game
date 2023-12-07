package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
    protected BufferedImage image, image2, image3;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    protected Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
            worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
            worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
            worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
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
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public boolean isCollision() {
        return collision;
    }
    public String getName() {
        return name;
    }
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage getImage2() {
        return image2;
    }
    public BufferedImage getImage3() {
        return image3;
    }

}
