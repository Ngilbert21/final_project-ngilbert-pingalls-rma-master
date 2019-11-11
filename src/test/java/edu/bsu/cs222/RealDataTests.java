package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RealDataTests {

    @Test
    public void testCollegeRegions0thru9() {
        APICaller caller = new APICaller();
        for (int i = 0; i < 10; i++) {
            String collegeList = caller.pullAllCollegeData(i,0,100000);
            Assert.assertNotEquals(collegeList, "");
        }
    }

    @Test
    public void testCollegeRegionsAbove9() {
        APICaller caller = new APICaller();
        String collegeList = caller.pullAllCollegeData(99,0,100000);
        Assert.assertEquals(collegeList, "");
    }

    @Test
    public void testNumberOfCollegesPulled() {
        APICaller caller = new APICaller();
        String collegeList = caller.pullAllCollegeData(3,0,100000);
        String[] CollegeNamesArrayList = collegeList.split("\n");
        Assert.assertEquals(CollegeNamesArrayList.length, 100);
    }

    @Test
    public void testNameListLengthMatchesIdListLength() {
        DropDownPopulator populator = new DropDownPopulator();

        int nameListLength = populator.statesList().size();
        int IdListLength = populator.stateIDList().size();

        Assert.assertEquals(nameListLength, IdListLength);
    }

    @Test
    public void testNameLengthSearchable() throws IOException {
        APICaller caller = new APICaller();
        String testSearch = caller.pullOneCollegeData("Trine University-Regional/Non-Traditional Campuses");
        String[] testSearchArrayList = testSearch.split("\n");
        Assert.assertEquals(testSearchArrayList[0], "State: Indiana");
    }
}
