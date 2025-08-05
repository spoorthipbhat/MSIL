package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpFetcher {

    public static String getOtpBasedOnEnv(String env, String mobileNumber) {
        System.out.println("📞 Fetching OTP for number: " + mobileNumber + " [ENV: " + env + "]");

        if (env.equalsIgnoreCase("master")) {
            System.out.println("🔐 Static OTP used for master: 7891");
            return "7891";
        } else {
            ConfigReader configReader = new ConfigReader();
            configReader.loadProperties(env);
            String token = configReader.get("otp.api.token");
            return getOtpFromApi(mobileNumber, token);  // Pass only the 10-digit number
        }
    }

    public static String getOtpFromApi(String mobileNumber, String token) {
        String apiUrl = "https://engage-api.digo.link/v1/reports/dlr/transactional?page_size=1";
        int retries = 5;
        int delayMs = 2000;
    
        for (int i = 0; i < retries; i++) {
            System.out.println("🔁 OTP Fetch attempt: " + (i + 1));
    
            Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Authorization", token)
                .header("Accept", "application/json")
                .when()
                .get(apiUrl)
                .then()
                .extract()
                .response();
    
            List<Map<String, Object>> dataList = response.jsonPath().getList("data");
    
            if (dataList != null) {
                for (Map<String, Object> entry : dataList) {
                    String number = (String) entry.get("mobile_number");
                    String message = (String) entry.get("message");
    
                    if (number != null && number.contains(mobileNumber)) {
                        Matcher matcher = Pattern.compile("\\b\\d{4,6}\\b").matcher(message);
                        if (matcher.find()) {
                            System.out.println("✅ OTP received from API: " + matcher.group());
                            return matcher.group();
                        }
                    }
                }
            }
    
            // Wait and retry
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ignored) {}
        }
    
        throw new RuntimeException("❌ OTP not found for: " + mobileNumber);
    }
    
}
