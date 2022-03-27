package comands;

import lab5.Catalog;
import lab5.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command<Catalog> {

    @Override
    public void implement(Catalog C) {
        JSONArray outerObj = new JSONArray();

        for (Item i : C.getItemList()) {
            JSONObject inerObj = new JSONObject();
            inerObj.put("id", i.getId());
            inerObj.put("title", i.getTitle());
            inerObj.put("location", i.getLocation());
            inerObj.put("year", i.getYear());
            inerObj.put("author", i.getAuthor());
            if (i.getType() != null)
                inerObj.put("type", i.getType());
            outerObj.add(inerObj);
        }
        try {
            FileWriter file = new FileWriter(new File("D:/facultate/E2_java_scripts/GitHub/Lab5/JsonSaves", "json_save.json"));
            file.write(outerObj.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
