package Tiles;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TilesBufferedImages {
    private Map<Integer,BufferedImage> sprites;

    public TilesBufferedImages() {
        sprites=new HashMap<>();
        try {
            sprites.put(0, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/0.jpg"))));
            sprites.put(1, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/1.jpg"))));
            sprites.put(2, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofLC2.png"))));
            sprites.put(3, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofLC3.png"))));
            sprites.put(4, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofLC4.png"))));
            sprites.put(5, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofRC1.png"))));
            sprites.put(7, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofRC3.png"))));
            sprites.put(8, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/roofRC4.png"))));
            sprites.put(9, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseLC1.png"))));
            sprites.put(10, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseLC2.png"))));
            sprites.put(11, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseLC3.png"))));
            sprites.put(12, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseLC4.png"))));
            sprites.put(13, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseRC1.png"))));
            sprites.put(14, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseRC2.png"))));
            sprites.put(15, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseRC3.png"))));
            sprites.put(16, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/houseRC4.png"))));
            sprites.put(6, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/6.png"))));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage getSpriteAtIndex(int i){
        return sprites.get(i);
    }
}
