package controller.parsers;

import model.Car;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ResultStringifier {
    public static String toString(List<List<Car>> result) {
        var jsonObject = resultToJson(result);
        return jsonObject.toString();
    }

    private static JSONObject resultToJson(List<List<Car>> result) {
        JSONArray stepStatuses = new JSONArray();
        for (var cars : result) {
            JSONArray leftVehicles = new JSONArray();
            for (var car : cars) {
                leftVehicles.put(car.identifier());
            }
            JSONObject leftVehiclesObject = new JSONObject();
            leftVehiclesObject.put("leftVehicles", leftVehicles);
            stepStatuses.put(leftVehiclesObject);
        }
        JSONObject stepStatusesObject = new JSONObject();
        stepStatusesObject.put("stepStatuses", stepStatuses);
        return stepStatusesObject;
    }
}
