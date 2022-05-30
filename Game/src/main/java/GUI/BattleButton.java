package GUI;

import Entities.Collider;
import Entities.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BattleButton   {
    private final int bWidth=220,bHeight=90;
    private int xpoz,ypoz;
    private String content;
    private int buttonNb;
    private BufferedImage image;

    public BattleButton(String content,int xpoz, int ypoz, int buttonNb,BufferedImage image) {
        this.xpoz = xpoz;
        this.ypoz = ypoz;
        this.content = content;
        this.buttonNb = buttonNb;
        this.image=image;
    }

    public BattleButton(String content, int buttonNb,BufferedImage image) {
        this.content = content;
        this.buttonNb = buttonNb;
        ypoz=GamePanel.screenHeight-220+100*((1+buttonNb)/2-1);
        if(buttonNb%2==0)
            xpoz=300;
        else xpoz=50;
        this.image=image;

    }

    public void setContent(String content) {
        this.content = content;
    }
    public void draw(Graphics2D g){
        g.drawImage(image,xpoz,ypoz,bWidth,bHeight,null);
        if (content!=null){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawChars(content.toCharArray(),0,content.length(),xpoz+10,ypoz+45);

        }
    }
    public boolean isClickedOn(int x,int y){
        if(xpoz<=x && x<=xpoz+bWidth && ypoz<=y && y<=ypoz+bHeight)
            return true;
        return false;
    }

    public int getbWidth() {
        return bWidth;
    }

    public int getbHeight() {
        return bHeight;
    }

    public int getXpoz() {
        return xpoz;
    }

    public int getYpoz() {
        return ypoz;
    }

    public int getButtonNb() {
        return buttonNb;
    }
}
