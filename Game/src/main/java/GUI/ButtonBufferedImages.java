package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ButtonBufferedImages {
    private Map<Integer, BufferedImage> images;
    public ButtonBufferedImages() {
        images=new HashMap<>();
        try {
            images.put(0, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/0.jpg"))));
            images.put(1, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/1.jpg"))));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage getSpriteAtIndex(int i){
        return images.get(i);
    }
}
