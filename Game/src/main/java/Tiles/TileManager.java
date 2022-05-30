package Tiles;

import Entities.Player;
import GUI.GamePanel;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TileManager {
    private int height, width;
    private int tileSize;
    private Map<Integer, List<Tile>> tileLayers;
    private TilesBufferedImages mySprites;
    private Player player;


    public TileManager(Player player,int height, int width, int tileSize) {
        this.player=player;
        mySprites=new TilesBufferedImages();
        tileLayers = new HashMap<>();
        this.height = height;
        this.width = width;
        this.tileSize = tileSize;
        initBackgroundTiles();
        initColliders();
        initInteractableTiles();
        initOverlayTiles();
    }

    public List<Tile> getTileColliders() {
        return tileLayers.get(1);
    }
    public List<Tile> getTileInteractable(){return tileLayers.get(2);}

    public int getHeight() {
        return height;
    }
    public int getWorldHeight(){return height*tileSize;}
    public int getWorldWidth(){return width*tileSize;}

    public int getWidth() {
        return width;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void initColliders() {
        try {
            tileLayers.put(1, new LinkedList<>());
            InputStream is= getClass().getResourceAsStream("map_layer1.txt");
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String st;
            for (int i = 0; i < width; i++) {
                st = br.readLine();

                String[] values = st.split(",[ ]*");

                for (int j = 0; j < width; j++)
                    if (!values[j].equals("0")) {
                        TileCollider t = new TileCollider(i * tileSize, j * tileSize,
                                tileSize , mySprites.getSpriteAtIndex(Integer.parseInt(values[j])));
                        tileLayers.get(1).add(t);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initOverlayTiles(){
        try {
            tileLayers.put(3, new LinkedList<>());
            InputStream is= getClass().getResourceAsStream("map_layer3.txt");
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String st;
            for (int i = 0; i < width; i++) {
                st = br.readLine();

                String[] values = st.split(",[ ]*");

                for (int j = 0; j < width; j++)
                if(!values[j].equals("0"))
                {
                    Tile t = new Tile(i * tileSize, j * tileSize,
                            tileSize, mySprites.getSpriteAtIndex(Integer.parseInt(values[j])));
                    tileLayers.get(3).add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBackgroundTiles() {
        tileLayers.put(0, new LinkedList<>());
        try {
            InputStream is= getClass().getResourceAsStream("map_layer0.txt");
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String st;
            for (int i = 0; i < width; i++) {
                st = br.readLine();

                String[] values = st.split(",[ ]*");

                for (int j = 0; j < width; j++) {
                    Tile t = new Tile(i * tileSize, j * tileSize,
                            tileSize, mySprites.getSpriteAtIndex(Integer.parseInt(values[j])));
                    tileLayers.get(0).add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void initInteractableTiles(){
        tileLayers.put(2, new LinkedList<>());
        try {
            InputStream is= getClass().getResourceAsStream("map_layer2.txt");
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String st;
            for (int i = 0; i < width; i++) {
                st = br.readLine();

                String[] values = st.split(",[ ]*");

                for (int j = 0; j < width; j++)
                    if(!values[j].equals("0"))
                {
                    TileInteractable t = new TileInteractable(i * tileSize, j * tileSize,
                            tileSize, mySprites.getSpriteAtIndex(Integer.parseInt(values[j])));
                    tileLayers.get(2).add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawBackground(Graphics2D g){
        int cameraXTop=player.getPosX()- GamePanel.screenWidth/2;
        int cameraYTop=player.getPosY()- GamePanel.screenHeight/2;
        int cameraXBot=player.getPosX()+ GamePanel.screenWidth/2;
        int cameraYBot=player.getPosY()+GamePanel.screenHeight/2;

        tileLayers.get(0).stream().forEach(tile -> {
            int offset=tile.getDimension()*8;
            if(cameraXTop-offset<=tile.getPosx() && cameraXBot+offset>=tile.getPosx() &&
        cameraYTop-offset<=tile.getPosy() && cameraYBot+offset>=tile.getPosy())
            tile.draw(g);
        });
    }
    public void drawColliders(Graphics2D g){
        int cameraXTop=player.getPosX()- GamePanel.screenWidth/2;
        int cameraYTop=player.getPosY()- GamePanel.screenHeight/2;
        int cameraXBot=player.getPosX()+ GamePanel.screenWidth/2;
        int cameraYBot=player.getPosY()+GamePanel.screenHeight/2;
        tileLayers.get(1).stream().forEach(tile -> {
            int offset=tile.getDimension()*8;
            if(cameraXTop-offset<=tile.getPosx() && cameraXBot+offset>=tile.getPosx() &&
                    cameraYTop-offset<=tile.getPosy() && cameraYBot+offset>=tile.getPosy())
                tile.draw(g);
        });
    }
    public void drawInteractable(Graphics2D g){
        int cameraXTop=player.getPosX()- GamePanel.screenWidth/2;
        int cameraYTop=player.getPosY()- GamePanel.screenHeight/2;
        int cameraXBot=player.getPosX()+ GamePanel.screenWidth/2;
        int cameraYBot=player.getPosY()+GamePanel.screenHeight/2;
        tileLayers.get(2).stream().forEach(tile -> {
            int offset=tile.getDimension()*8;
            if(cameraXTop-offset<=tile.getPosx() && cameraXBot+offset>=tile.getPosx() &&
                    cameraYTop-offset<=tile.getPosy() && cameraYBot+offset>=tile.getPosy())
                tile.draw(g);
        });
    }
    public void drawOverlay(Graphics2D g){
        int cameraXTop=player.getPosX()- GamePanel.screenWidth/2;
        int cameraYTop=player.getPosY()- GamePanel.screenHeight/2;
        int cameraXBot=player.getPosX()+ GamePanel.screenWidth/2;
        int cameraYBot=player.getPosY()+GamePanel.screenHeight/2;
        tileLayers.get(3).stream().forEach(tile -> {
            int offset=tile.getDimension()*8;
            if(cameraXTop-offset<=tile.getPosx() && cameraXBot+offset>=tile.getPosx() &&
                    cameraYTop-offset<=tile.getPosy() && cameraYBot+offset>=tile.getPosy())
                tile.draw(g);
        });
    }

}
