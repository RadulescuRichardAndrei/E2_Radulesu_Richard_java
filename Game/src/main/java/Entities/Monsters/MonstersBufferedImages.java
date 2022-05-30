package Entities.Monsters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MonstersBufferedImages {
    private Map<Integer, BufferedImage> mSprites;

    public MonstersBufferedImages() {
        mSprites=new HashMap<>();
        try {
            mSprites.put(0, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/eel_front.png"))));
            mSprites.put(1, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Monsters/eel_back.png"))));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage getSpriteAtIndex(int i){
        return mSprites.get(i);
    }

}
