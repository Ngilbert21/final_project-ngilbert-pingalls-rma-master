package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;

@SuppressWarnings("WeakerAccess")
public class CollegeParser {

    public String parseCollegeNames(InputStream resource) throws NullPointerException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(resource);
        JsonArray results = parser.parse(reader).getAsJsonObject().getAsJsonArray("results");

        API_ElementPicker elementPicker = new API_ElementPicker();

        String allCollegeNames = "";

        for (int i = 0; i < results.size(); i++) {
            String collegeName = elementPicker.collegeName(results, i);
            allCollegeNames = allCollegeNames.concat(collegeName + "\n");
        }
        return allCollegeNames;
    }

    public String parseCollegeData(InputStream resource) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(resource);
        JsonArray results = parser.parse(reader).getAsJsonObject().getAsJsonArray("results");

        API_ElementPicker elementPicker = new API_ElementPicker();
        DropDownPopulator populator = new DropDownPopulator();

        int schoolStateID = elementPicker.schoolStateID(results, 0);
        int stateIdIndex = populator.stateIDList().indexOf(schoolStateID);
        String schoolState = populator.statesList().get(stateIdIndex);

        int regionID = elementPicker.schoolRegionID(results, 0);
        String schoolRegion = populator.regionsList().get(regionID - 1);

        int tuitionCost = elementPicker.schoolTuitionCost(results, 0);
        DecimalFormat moneyFormatter = new DecimalFormat("$##,###");
        String formattedCost = moneyFormatter.format(tuitionCost);

        String schoolURL = elementPicker.schoolURL(results, 0);

        String allCollegeData;

        allCollegeData = "State: " + schoolState +
                "\nRegion: " + schoolRegion +
                "\nAnnual Tuition: " + formattedCost +
                "\nWebsite: " + schoolURL;

        return allCollegeData;
    }
}