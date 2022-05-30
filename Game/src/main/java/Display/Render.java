package Display;

import Entities.Drawable;
import Entities.Player;
import GUI.GamePanel;
import Tiles.TileManager;

import java.awt.*;

public class Render implements Drawable {
    private TileManager tileManager;
    private HitBoxCheck hitBoxCheck;
    private Player player;
    private Camera camera;
    public Render(Player player) {
        this.player=player;
        tileManager =new TileManager(player,40,40,64);
        hitBoxCheck=new HitBoxCheck(tileManager.getHeight(),tileManager.getWidth(),
                tileManager.getTileSize(),tileManager.getTileColliders(),tileManager.getTileInteractable());
        camera=new Camera(tileManager.getWorldWidth()-GamePanel.screenWidth,
                tileManager.getWorldHeight()-GamePanel.screenHeight,0,0);

    }

    @Override
    public void update() {
        player.update();
        camera.updateCamera(GamePanel.screenWidth,GamePanel.screenHeight,player.getPosX(),player.getPosY());
    }

    @Override
    public void draw(Graphics2D g) {
        camera.drawCamera(g);
        tileManager.drawBackground(g);
        tileManager.drawColliders(g);
        player.draw(g);
        tileManager.drawInteractable(g);
        tileManager.drawOverlay(g);
        camera.drawCamera(g);

    }
}
