package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

public class GoogleDrive {

    public static void downloadFileFromDrive(String fileId, String destinationPath) throws IOException {
        String fileUrl = "https://drive.google.com/uc?export=download&id=" + fileId;
        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
