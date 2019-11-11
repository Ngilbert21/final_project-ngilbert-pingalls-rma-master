package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class SampleDataTests {
    private JsonParser parser = new JsonParser();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
    private Reader reader = new InputStreamReader(inputStream);
    private JsonElement rootElement = parser.parse(reader);
    private JsonObject rootObject = rootElement.getAsJsonObject();
    private JsonObject collegeList = rootObject.getAsJsonObject("collegeList").getAsJsonObject("pages");
    private JsonArray array = null;

    @Test
    public void testCountColleges() {
        for (Map.Entry<String,JsonElement> entry : collegeList.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("colleges");
        }
        Assert.assertEquals(10, array.size());
    }

    @Test
    public void testFirstCollege() {
        for (Map.Entry<String,JsonElement> entry : collegeList.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("colleges");
        }
        String collegeName = array.get(0).getAsJsonObject().get("name").getAsString();
        Assert.assertEquals("Ball State University", collegeName);
    }

    @Test
    public void testCountCollegesInArea() {
        String chosenArea = "Great Lakes (IL, IN, MI, OH, WI)";
        int areaCount = 0;

        for (Map.Entry<String,JsonElement> entry : collegeList.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("colleges");
        }

        for (int i = 0; i < array.size(); i++) {
            String area = array.get(i).getAsJsonObject().get("area").getAsString();
            if (area.equals(chosenArea)) {
                areaCount += 1;
            }
        }
        Assert.assertEquals(3, areaCount);
    }

    @Test
    public void testCountCollegesInState() {
        String chosenState = "Texas";
        int areaCount = 0;

        for (Map.Entry<String,JsonElement> entry : collegeList.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("colleges");
        }

        for (int i = 0; i < array.size(); i++) {
            String area = array.get(i).getAsJsonObject().get("state").getAsString();
            if (area.equals(chosenState)) {
                areaCount += 1;
            }
        }
        Assert.assertEquals(1, areaCount);
    }
}
