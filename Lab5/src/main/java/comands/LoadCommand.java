package comands;

import lab5.Catalog;
import lab5.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadCommand extends Command<Catalog> {
    @Override
    public void implement(Catalog C) {
        JSONParser jsonP = new JSONParser();
        try (FileReader fr = new FileReader("D:/facultate/E2_java_scripts/GitHub/Lab5/JsonSaves/json_save.json")) {
            Object obj = jsonP.parse(fr);
            JSONArray outerObj = (JSONArray) obj;

            for(Object it: outerObj){
                JSONObject item= (JSONObject) it;
                C.getItemList().add(new Item((String) item.get("id"), (String) item.get("title"), (String) item.get("location"),
                        (String) item.get("author"), (String) item.get("type"), (String) item.get("year")));
            }
            //outerObj.forEach(item -> parseItems((JSONObject) item));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
