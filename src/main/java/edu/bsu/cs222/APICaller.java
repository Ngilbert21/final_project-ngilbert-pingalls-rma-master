package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@SuppressWarnings("WeakerAccess")
public class APICaller {
    private URL url = null;

    public InputStream dataResource (URL url) {
        InputStream resource = null;
        try {
            URLConnection connection = url.openConnection();
            resource = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }

    public String pullAllCollegeData(int schoolRegionID, int minCost, int maxCost) {
        CollegeParser parser = new CollegeParser();

        try {
            url = new URL("https://api.data.gov/ed/collegescorecard/v1/schools.json?" +
                    "api_key=rSeZD4fMR5SbAO3P1smnLw8ddY9I4689g3qXRN6n&" +
                    "school.degrees_awarded.highest=4&" +
                    "school.degrees_awarded.predominant=3&" +
                    "school.carnegie_undergrad=5,6,7,8,9,10,11,12,13,14,15&" +
                    "school.region_id=" + schoolRegionID +"&" +
                    "2015.cost.tuition.out_of_state__range=" + minCost + ".." + maxCost + "&" +
                    "_fields=" +
                    "school.name," +
                    "school.region_id," +
                    "school.carnegie_undergrad," +
                    "school.state_fips," +
                    "2015.cost.tuition.in_state," +
                    "2015.cost.tuition.out_of_state&" +
                    "_per_page=100");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream resource = dataResource(url);

        return parser.parseCollegeNames(resource);
    }

    public String pullOneCollegeData(String schoolName) throws IOException {
        CollegeParser parser = new CollegeParser();

        String adjustedSchoolName;

        if (schoolName.length() > 31) {
            adjustedSchoolName = schoolName.substring(0,31);
        } else {
            adjustedSchoolName = schoolName;
        }

        try {
            url = new URL("https://api.data.gov/ed/collegescorecard/v1/schools.json?" +
                    "api_key=rSeZD4fMR5SbAO3P1smnLw8ddY9I4689g3qXRN6n&" +
                    "school.name=" + URLEncoder.encode(adjustedSchoolName,"UTF-8") + "&" +
                    "_fields=" +
                    "school.name," +
                    "school.region_id," +
                    "school.school_url," +
                    "school.state_fips," +
                    "2015.cost.tuition.in_state," +
                    "2015.cost.tuition.out_of_state&" +
                    "_per_page=100");

            //noinspection UnusedAssignment
            schoolName = "";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream resource = dataResource(url);

        return parser.parseCollegeData(resource);
    }
}