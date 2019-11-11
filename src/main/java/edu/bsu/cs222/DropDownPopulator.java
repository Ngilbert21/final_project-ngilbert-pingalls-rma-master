package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DropDownPopulator {

    public List<String> statesList() {
        return Arrays.asList(
                "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "District of Columbia",
                "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
                "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
                "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
                "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
                "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
                "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
    }

    public List<Integer> stateIDList() {
        List<Integer> stateIDList = new ArrayList<>();
        List<Integer> emptyRegionIDs = Arrays.asList(3, 7, 14, 43, 52);
        for (int i = 1; i < 57; i++) {
            if (emptyRegionIDs.contains(i)) {
                //noinspection UnnecessaryContinue
                continue;
            } else {
                Integer stateID = i;
                stateIDList.add(stateID);
            }
        }
        return stateIDList;
    }

    public List<String> regionsList() {
        return Arrays.asList(
                "New England (CT, ME, MA, NH, RI, VT)",
                "Mideast (DE, DC, MD, NJ, NY, PA)",
                "Great Lakes (IL, IN, MI, OH, WI)",
                "Plains (IA, KS, MN, MO, NE, ND, SD)",
                "Southeast (AL, AR, FL, GA, KY, LA, MS, NC, SC, TN, VA, WV)",
                "Southwest (AZ, NM, OK, TX)",
                "Rocky Mountain (CO, ID, MT, UT, WY)",
                "Far West (AK, CA, HI, NV, OR, WA)"
        );
    }

    public List<Integer> costRange() {
        List<Integer> costRange = new ArrayList<>();

        for (int cost = 0; cost < 40000; cost += 2000) {
            costRange.add(cost);
        }
        for (int cost = 40000; cost < 100001; cost += 4000) {
            costRange.add(cost);
        }
        return costRange;
    }
}
