package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_artifact;
import object.OBJ_heart;
import object.SuperObject;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private Font arial_40, arial_80B;
    private BufferedImage artifactImage, heart_full, heart_half, heart_blank;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;
    private String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 30);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_artifact artifact = new OBJ_artifact();
        artifactImage = artifact.getImage();

        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_heart();
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_blank = heart.getImage3();
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);



        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {
            drawPlayerLife();
        }
        // PAUSE STATE
        else if (gp.getGameState() == gp.getPauseState()) {
            drawPauseScreen();
            drawPlayerLife();
        } 
        // DIALOGUE STATE
        else if (gp.getGameState() == gp.getDialogueState()) {
            drawDialogueScreen();
            drawPlayerLife();
        }

        if (gameFinished) {
            String text;
            int x, y;
            if (gp.getPlayer().getLife() == 0) {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                text = "You loose...";
                x = getXforCenteredText(text);
                y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
                g2.drawString(text, x, y);

                g2.setFont(arial_80B);
                g2.setColor(Color.yellow);
                text = "Try again!";
                x = getXforCenteredText(text);
                y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);
                g2.drawString(text, x, y);
            } else {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                text = "You win!";
                x = getXforCenteredText(text);
                y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
                g2.drawString(text, x, y);

                g2.setFont(arial_80B);
                g2.setColor(Color.yellow);
                text = "Congratulations!";
                x = getXforCenteredText(text);
                y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);
                g2.drawString(text, x, y);
            }
            gp.setGameThread(null);
        } 
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(artifactImage, gp.getTileSize() / 3, 80, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawString(": " + gp.getPlayer().getHasArtifact() + "/3", 80, 123);
    
            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.getTileSize(), gp.getTileSize() * 11);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
    public void drawPlayerLife() {
        int x = gp.getTileSize() / 2;
        int y = gp.getTileSize() / 2;
        
        // DRAW MAX LIFE
        for (int i = 0; i < gp.getPlayer().getMaxLife() / 2; i++) {
            g2.drawImage(heart_blank, x, y, null);
            x += 40;
        }

        // RESET
        x = gp.getTileSize() / 2;
        y = gp.getTileSize() / 2;

        // DRAW CURRENT LIFE
        for (int i = 0; i < gp.getPlayer().getLife(); i++) {
            i++;
            g2.drawImage(heart_half, x, y, null);
            if (i < gp.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }   
            x += 40;
        }
    }
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() * 8;
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int heigth = gp.getTileSize() * 3;

        drawSubWindow(x, y, width, heigth);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.getTileSize();
        y += gp.getTileSize();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int heigth) {
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, heigth, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, heigth - 10, 25, 25);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.getScreenWidth() / 2 - length /2;
    }
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
