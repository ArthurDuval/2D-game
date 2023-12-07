package main;

import entity.NPC_guardian;
import entity.NPC_hiker;
import entity.NPC_oldLady;
import monster.MON_gengar;
import object.OBJ_vaporeon;
import object.OBJ_cup;
import object.OBJ_mask;
import object.OBJ_necklace;
import object.OBJ_rock;
import object.OBJ_runningShoes;

public class AssetSetter {
    private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.setObj(0, new OBJ_vaporeon());
        gp.getObj(0).setWorldX(35 * gp.getTileSize());
        gp.getObj(0).setWorldY(40 * gp.getTileSize());

        gp.setObj(1, new OBJ_rock());
        gp.getObj(1).setWorldX(11 * gp.getTileSize());
        gp.getObj(1).setWorldY(11 * gp.getTileSize());

        gp.setObj(2, new OBJ_runningShoes());
        gp.getObj(2).setWorldX(22 * gp.getTileSize());
        gp.getObj(2).setWorldY(20 * gp.getTileSize());

        gp.setObj(3, new OBJ_cup());
        gp.getObj(3).setWorldX(37 * gp.getTileSize());
        gp.getObj(3).setWorldY(12 * gp.getTileSize());

        gp.setObj(4, new OBJ_mask());
        gp.getObj(4).setWorldX(11 * gp.getTileSize());
        gp.getObj(4).setWorldY(10 * gp.getTileSize());

        gp.setObj(5, new OBJ_necklace());
        gp.getObj(5).setWorldX(11 * gp.getTileSize());
        gp.getObj(5).setWorldY(40 * gp.getTileSize());

        gp.setObj(6, new OBJ_rock());
        gp.getObj(6).setWorldX(10 * gp.getTileSize());
        gp.getObj(6).setWorldY(11 * gp.getTileSize());
    }
    public void setNPC() {
        gp.setNpc(0, new NPC_hiker(gp));
        gp.getNpc(0).setWorldX(gp.getTileSize() * 35);
        gp.getNpc(0).setWorldY(gp.getTileSize() * 25);
        
        gp.setNpc(1, new NPC_oldLady(gp));
        gp.getNpc(1).setWorldX(gp.getTileSize() * 15);
        gp.getNpc(1).setWorldY(gp.getTileSize() * 15);
        
        gp.setNpc(2, new NPC_guardian(gp));
        gp.getNpc(2).setWorldX(gp.getTileSize() * 20);
        gp.getNpc(2).setWorldY(gp.getTileSize() * 40);
    }
    public void setMonster() {
        gp.setMonster(0, new MON_gengar(gp));
        gp.getMonster(0).setWorldX(gp.getTileSize() * 30);
        gp.getMonster(0).setWorldY(gp.getTileSize() * 21);
    }
}
