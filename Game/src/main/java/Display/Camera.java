package Display;

import java.awt.*;

public class Camera {
    private int offsetMaxX,offsetMaxY, offsetMinX,offsetMinY;
    private int cameraX,cameraY;

    public Camera(int offsetMaxX, int offsetMaxY, int offsetMinX, int offsetMinY) {
        this.offsetMaxX = offsetMaxX;
        this.offsetMaxY = offsetMaxY;
        this.offsetMinX = offsetMinX;
        this.offsetMinY = offsetMinY;
    }

    public void updateCamera(int viewportX,int viewportY,int playerX,int playerY){
        cameraX=playerX- viewportX/2;
        cameraY=playerY-viewportY/2;
        if(cameraX>offsetMaxX)
            cameraX=offsetMaxX;
        if(cameraX<offsetMinX)
            cameraX=offsetMinX;
        if(cameraY>offsetMaxY)
            cameraY=offsetMaxY;
        if(cameraY<offsetMinY)
            cameraY=offsetMinY;
    }
    public void drawCamera(Graphics2D g){
        g.translate(-cameraX,-cameraY);
    }

}
