package utils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "activeDrivers")
    public Object[][] activeDrivers() throws Exception {
        return GoogleSheetReader.getFilteredRowMap("Sheet1", "FlowName", "LoginFlow");
    }
}
