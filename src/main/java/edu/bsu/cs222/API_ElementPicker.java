package edu.bsu.cs222;

import com.google.gson.JsonArray;

@SuppressWarnings("WeakerAccess")
public class API_ElementPicker {
    public String collegeName(JsonArray results, int i) {
        return results.get(i).getAsJsonObject().getAsJsonPrimitive("school.name").getAsString();
    }

    public String schoolURL(JsonArray results, int i) {
        try {
            return results.get(i).getAsJsonObject().getAsJsonPrimitive("school.school_url").getAsString();
        } catch (Exception e) {
            return "Website not listed.";
        }
    }

    public int schoolStateID(JsonArray results, int i) {
        return results.get(i).getAsJsonObject().getAsJsonPrimitive("school.state_fips").getAsInt();
    }

    public int schoolRegionID(JsonArray results, int i) {
        return results.get(i).getAsJsonObject().getAsJsonPrimitive("school.region_id").getAsInt();
    }

    public int schoolTuitionCost(JsonArray results, int i) {
        return results.get(i).getAsJsonObject().getAsJsonPrimitive("2015.cost.tuition.out_of_state").getAsInt();
    }
}
