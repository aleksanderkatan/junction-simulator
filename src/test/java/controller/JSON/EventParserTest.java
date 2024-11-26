package controller.JSON;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EventParserTest {
    @Test
    void parsesFlatJson() {
        var jsonObject = new JSONObject();
        jsonObject.put("key1", "value1");
        jsonObject.put("key2", "value2");

        Map<String, String> map = EventParser.parseFlatObject(jsonObject);

        assertEquals(Map.of("key1", "value1", "key2", "value2"), map);
    }

    @Test
    void parsesEventList() {
        var jsonString = """
                {
                  "commands": [
                    {
                      "type": "addVehicle",
                      "vehicleId": "vehicle1",
                      "startRoad": "south",
                      "endRoad": "north"
                    },
                    {
                      "type": "step"
                    },
                  ]
                }
                """;

        List<Map<String, String>> map = EventParser.parseEvents(jsonString);

        var expectedMap = List.of(
                Map.of("type", "addVehicle",
                       "vehicleId", "vehicle1",
                       "startRoad", "south",
                       "endRoad", "north"),
                Map.of("type", "step")
        );
        assertEquals(expectedMap, map);
    }

}