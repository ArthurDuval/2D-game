package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyHandler
 */
public class KeyHandler implements KeyListener {
    private GamePanel gp;
    private boolean upPressed, downPressed, rightPressed, leftPressed, ePressed;
    private String lastReleased = "";

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {

        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    upPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_Q:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.setGameState(gp.getPauseState());
                    break;
                case KeyEvent.VK_E:
                    ePressed = true;
                    break;
            }
        } // PAUSE STATE
        else if (gp.getGameState() == gp.getPauseState()) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                gp.setGameState(gp.getPlayState());
            }
        } // DIALOGUE STATE
        else if (gp.getGameState() == gp.getDialogueState()) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gp.setGameState(gp.getPlayState());
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                upPressed = false;
                lastReleased = "z";
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                lastReleased = "s";
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                lastReleased = "d";
                break;
            case KeyEvent.VK_Q:
                leftPressed = false;
                lastReleased = "q";
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public String getLastReleased() {
        return lastReleased;
    }
    public boolean isePressed() {
        return ePressed;
    }
    public void setePressed(boolean ePressed) {
        this.ePressed = ePressed;
    }
}