package tests;
import base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoginHelper;
import java.util.Map;
import java.io.IOException;

public class  LoginTest extends BaseClass{
 
@Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
        public void loginAsOperator(Map<String, String> row) throws IOException, InterruptedException {
        String operatorMobile = row.get("OperatorMobileNumber");
        LoginHelper loginHelper = new LoginHelper(driver, env);
        loginHelper.loginAsOperator(operatorMobile);
    
    }

    }



