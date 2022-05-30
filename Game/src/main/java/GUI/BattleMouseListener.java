package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BattleMouseListener implements MouseListener {
    private int clickPosX,clickPosY;


    public int getClickPosX() {
        return clickPosX;
    }

    public int getClickPosY() {
        return clickPosY;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        clickPosX= e.getX();
        clickPosY=e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
