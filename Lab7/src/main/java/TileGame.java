import java.util.List;
import java.util.Map;
import java.util.Set;

public class TileGame implements Runnable {
    private Map<Tile, Integer> letterMap;
    private int scoreP1, scoreP2;


    public boolean validateCount(Map<Character, Integer> charMap, String word) {

        for (int i = 0; i < word.length(); i++)
            if (!charMap.containsKey(word.charAt(i)))
                return false;
            else
                charMap.computeIfPresent(word.charAt(i), (k, v) -> v - 1);

        for(Integer i:charMap.values())
            if(i<0)
                return false;
        return true;
    }

    @Override
    public void run() {

    }
}
