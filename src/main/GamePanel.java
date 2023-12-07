package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int originalTileSize = 32;
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    // FPS
    private int FPS = 60;

    // SYSTEM
    private TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler(this);
    private Sound music = new Sound();
    private Sound se = new Sound();
    private CollisionChecker cChecker = new CollisionChecker(this);
    private AssetSetter aSetter = new AssetSetter(this);
    private UI ui = new UI(this);
    private Thread gameThread;

    // ENTITY AND OBJECT
    private Player player = new Player(this, keyH);
    private SuperObject obj[] = new SuperObject[7];
    private Entity npc[] = new Entity[3];
    private Entity monster[] = new Entity[1];

    // GAME STATE
    private int gameState;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        stopMusic(); // remove to hear the music
        gameState = playState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        if (gameState == playState) {
            player.update();
            for (Entity entity : npc) {
                entity.update();
            }
            for (Entity entity : monster) {
                entity.update();
            }
        } else if (gameState == pauseState) {}
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileM.draw(g2);

        // OBJECT
        for (SuperObject sObject : obj) {
            if (sObject != null) {
                sObject.draw(g2, this);
            }
        }

        // NPC
        for (Entity nEntity : npc) {
            if (nEntity != null) {
                nEntity.draw(g2);
            }
        }

        // MONSTER
        for (Entity mEntity : monster) {
            if (mEntity != null) {
                mEntity.draw(g2);
            }
        }

        // PLAYER
        player.draw(g2);
        
        // UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMaxScreenCol() {
        return maxScreenCol;
    }
    public int getMaxScreenRow() {
        return maxScreenRow;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public Player getPlayer() {
        return player;
    }
    public CollisionChecker getcChecker() {
        return cChecker;
    }
    public TileManager getTileM() {
        return tileM;
    }
    public SuperObject[] getObj() {
        return obj;
    }
    public SuperObject getObj(int index) {
        return obj[index];
    }
    public void setObj(int index, SuperObject sObject) {
        obj[index] = sObject;
    }
    public UI getUi() {
        return ui;
    }
    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public int getPlayState() {
        return playState;
    }
    public int getPauseState() {
        return pauseState;
    }
    public int getDialogueState() {
        return dialogueState;
    }
    public Entity[] getNpc() {
        return npc;
    }
    public Entity getNpc(int index) {
        return npc[index];
    }
    public void setNpc(int index, Entity sEntity) {
        npc[index] = sEntity;
    }
    public KeyHandler getKeyH() {
        return keyH;
    }
    public Entity[] getMonster() {
        return monster;
    }
    public Entity getMonster(int index) {
        return monster[index];
    }
    public void setMonster(int index, Entity sEntity) {
        monster[index] = sEntity;
    }
}