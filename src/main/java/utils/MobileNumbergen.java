package utils;
import java.io.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MobileNumbergen {

    private static final String[] MALE_NAMES = {"Ramesh", "Suresh", "Mahesh", "Rajesh", "Naresh"};
    private static final String[] FEMALE_NAMES = {"Sita", "Radha", "Lakshmi", "Anjali", "Pooja"};

    public static String generateDriverCSV(String filename, int count) throws IOException {
        String dirPath = System.getProperty("user.dir") + "/src/test/resources/data";
        new File(dirPath).mkdirs(); // create dir if not exist
        String filePath = dirPath + "/" + filename;
    
        FileWriter writer = new FileWriter(filePath);
        writer.append("driver_name,driver_phone_number\n");
    
        for (int i = 0; i < count; i++) {
            String name;
            if (i % 2 == 0) {
                name = MALE_NAMES[i % MALE_NAMES.length];
            } else {
                name = FEMALE_NAMES[i % FEMALE_NAMES.length];
            }
            String phone = "9000000" + String.format("%03d", i + 1); // ensures 9000000001+
            writer.append(name + "," + phone + "\n");
        }
    
        writer.flush();
        writer.close();
    
        return new File(filePath).getAbsolutePath();
    }
    
    
}
