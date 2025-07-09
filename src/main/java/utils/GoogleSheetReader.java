package utils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.openqa.selenium.WebElement;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleSheetReader {

    private static Sheets sheetsService;
    private static final String SHEET_ID = "17vx7HgEWZPLQAJp2Gd8BsYZEhGH1KQ_kvREcNIEyhpI";
    private static final String APPLICATION_NAME = "AutomationSheetReader";
    private static final String SERVICE_ACCOUNT_KEY_PATH = "src/test/resources/Token.json"; 

    private static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        if (sheetsService == null) {
            GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                    .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
            sheetsService = new Sheets.Builder(
                    credential.getTransport(),
                    credential.getJsonFactory(),
                    credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        return sheetsService;
    }

    public static List<List<Object>> readSheetData(String spreadsheetId, String range)
            throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        return response.getValues();
    }

    public static Object[][] getFilteredRowMap(
        String sheetName,
        String conditionColumn,
        String expectedValue
) throws IOException, GeneralSecurityException {

    String finalRange = sheetName + "!A1:Z1000"; 
    List<List<Object>> rawData = readSheetData(SHEET_ID, finalRange);

    if (rawData == null || rawData.size() < 2) {
        throw new RuntimeException("Sheet is empty or missing header: " + sheetName);
    }

    List<Object> headers = rawData.get(0);
    int conditionIndex = headers.indexOf(conditionColumn);

    if (conditionIndex == -1) {
        throw new IllegalArgumentException("Condition column not found: " + conditionColumn);
    }

    List<Map<String, String>> matchedRows = new java.util.ArrayList<>();

    for (int i = 1; i < rawData.size(); i++) {
        List<Object> row = rawData.get(i);

        if (conditionIndex < row.size()) {
            String cellValue = row.get(conditionIndex).toString().trim();

            if (cellValue.equalsIgnoreCase(expectedValue)) {
                Map<String, String> rowMap = new java.util.HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    String key = headers.get(j).toString();
                    String value = j < row.size() ? row.get(j).toString() : "";
                    rowMap.put(key, value);
                }
                matchedRows.add(rowMap);
            }
        }
    }

    Object[][] result = new Object[matchedRows.size()][1];
    for (int i = 0; i < matchedRows.size(); i++) {
        result[i][0] = matchedRows.get(i);
    }

    return result;
}

}
