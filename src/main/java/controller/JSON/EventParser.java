package controller.JSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventParser {
    public static List<Map<String,String>> parse(String jsonString) {
        var result = new ArrayList<Map<String, String>>();

        JSONObject jsonObject = new JSONObject(jsonString);
        var jsonCommandsArray = jsonObject.getJSONArray("commands");

        // jsonCommandsArray is not Iterable (the objects might be of different types)
        for (int i = 0; i < jsonCommandsArray.length(); i++) {
            result.add(parseFlatObject(jsonCommandsArray.getJSONObject(i)));
        }

        return result;
    }

    public static Map<String, String> parseFlatObject(JSONObject jsonObject) {
        var result = new HashMap<String, String>();

        for (var key : jsonObject.keySet()) {
            result.put(key, jsonObject.getString(key));
        }

        return result;
    }
}
