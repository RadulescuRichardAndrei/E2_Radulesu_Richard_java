package Controllers;

import FileSystem.FileManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean up,down,left,right,save,heal;

    public KeyHandler() {
        up=false; down=false; left=false; right=false; heal=false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isSave() {
        return save;
    }

    public boolean isHeal() {
        return heal;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_D:
                right=true;
                break;
            case KeyEvent.VK_A:
                left=true;
                break;
            case  KeyEvent.VK_O:
                save=true;
                break;
            case KeyEvent.VK_H:
                heal=true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                up=false;
                break;
            case KeyEvent.VK_S:
                down=false;
                break;
            case KeyEvent.VK_D:
                right=false;
                break;
            case KeyEvent.VK_A:
                left=false;
                break;
            case  KeyEvent.VK_O:
                save=false;
                break;
            case KeyEvent.VK_H:
                heal=false;
                break;
        }
    }
}
