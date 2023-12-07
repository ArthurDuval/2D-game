package main;

import entity.Entity;

/**
 * CollisionChecker
 */
public class CollisionChecker {
    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "wUp":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileM().getTile()[tileNum1].getCollision() || gp.getTileM().getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "wDown":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].getCollision() || gp.getTileM().getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "wRight":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].getCollision() || gp.getTileM().getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "wLeft":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].getCollision() || gp.getTileM().getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.getObj().length; i++) {
            if (gp.getObj(i) != null) {
                
                // Get entity's solid area position
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY());
                
                // Get the object's solid area position
                gp.getObj(i).setSolidAreaX(gp.getObj(i).getWorldX() + gp.getObj(i).getSolidAreaX());
                gp.getObj(i).setSolidAreaY(gp.getObj(i).getWorldY() + gp.getObj(i).getSolidAreaY());
                
                switch (entity.getDirection()) {
                    case "wUp":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        break;
                    case "wDown":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        break;
                    case "wLeft":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        break;
                    case "wRight":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        break;
                }
                if (entity.getSolidArea().intersects(gp.getObj(i).getSolidArea())) {
                    if (gp.getObj(i).isCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                gp.getObj(i).setSolidAreaX(gp.getObj(i).getSolidAreaDefaultX());
                gp.getObj(i).setSolidAreaY(gp.getObj(i).getSolidAreaDefaultY());
            }
        }
        return index;
    }
    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                
                // Get entity's solid area position
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY());
                
                // Get the object's solid area position
                target[i].setSolidAreaX(target[i].getWorldX() + target[i].getSolidAreaX());
                target[i].setSolidAreaY(target[i].getWorldY() + target[i].getSolidAreaY());
                
                switch (entity.getDirection()) {
                    case "wUp":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        break;
                    case "wDown":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        break;
                    case "wLeft":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        break;
                    case "wRight":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        break;
                }
                if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                    if (target[i] != entity) {
                        entity.setCollisionOn(true);
                        index = i;
                    }
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                target[i].setSolidAreaX(target[i].getSolidAreaDefaultX());
                target[i].setSolidAreaY(target[i].getSolidAreaDefaultY());
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;

        // Get entity's solid area position
        entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX());
        entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY());
        
        // Get the object's solid area position
        gp.getPlayer().setSolidAreaX(gp.getPlayer().getWorldX() + gp.getPlayer().getSolidAreaX());
        gp.getPlayer().setSolidAreaY(gp.getPlayer().getWorldY() + gp.getPlayer().getSolidAreaY());
        
        switch (entity.getDirection()) {
            case "wUp":
                entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                break;
            case "wDown":
                entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                break;
            case "wLeft":
                entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                break;
            case "wRight":
                entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                break;
        }
        if (entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
            entity.setCollisionOn(true);
            contactPlayer = true;
        }
        entity.setSolidAreaX(entity.getSolidAreaDefaultX());
        entity.setSolidAreaY(entity.getSolidAreaDefaultY());
        gp.getPlayer().setSolidAreaX(gp.getPlayer().getSolidAreaDefaultX());
        gp.getPlayer().setSolidAreaY(gp.getPlayer().getSolidAreaDefaultY());

        return contactPlayer;
    }
}