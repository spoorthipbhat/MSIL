package utils;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "activeDrivers")
    public Object[][] activeDrivers(ITestContext context) throws Exception {

        String sheetName = context.getCurrentXmlTest().getParameter("SheetName");
        String flowName = context.getCurrentXmlTest().getParameter("FlowName");

        if (sheetName == null) sheetName = "Sheet";
        if (flowName == null) flowName = "LoginFlow";

        return GoogleSheetReader.getFilteredRowMap(sheetName, "FlowName", flowName);
    }
}
