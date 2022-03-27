package lab5;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Catalog {
    private List<Item> itemList;

    public Catalog() {
        itemList = new LinkedList<>();
    }

    public Catalog(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }


    @Override
    public String toString() {
        return "comands.Catalog{" +
                "itemList=" + itemList +
                '}';
    }
}
