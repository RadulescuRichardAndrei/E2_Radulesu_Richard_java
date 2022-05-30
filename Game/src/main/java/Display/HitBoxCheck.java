package Display;

import Tiles.Tile;
import Tiles.TileCollider;
import Tiles.TileInteractable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class HitBoxCheck {
    private static int height;
    private static int width;
    private static int tileSize;
    private static List<TileCollider> tileColliders;
    private static List<TileInteractable> tileInteractables;

    public HitBoxCheck(int height, int width, int tileSize, List<Tile> tileCol,List<Tile> tileCol2) {
        this.height = height;
        this.width = width;
        this.tileSize = tileSize;
        tileColliders= new LinkedList<>();
        tileInteractables= new LinkedList<>();
        tileCol.stream().forEach(tile -> tileColliders.add((TileCollider) tile));
        tileCol2.stream().forEach(tile -> tileInteractables.add((TileInteractable) tile));



    }

    public static boolean CanMoveHere(Rectangle rec){
        if(!(rec.x>=0 && rec.x+rec.width<= width*tileSize &&
         rec.y>=0 && rec.y+rec.height <=height*tileSize))
            return false;

        TileCollider answer= tileColliders.stream().filter(tileCollider -> tileCollider.getCollider().getHitBox()
                .intersects(rec)
        ).findFirst().orElse(null);
        if(answer==null)
            return true;
        return false;
    }
    public static boolean IsInteractHere(Rectangle rec){

        TileInteractable answer= tileInteractables.stream().filter(tileInteractable -> tileInteractable.intersect(rec)).findFirst().orElse(null);
        if(answer==null)
            return false;
        return true;
    }

}
