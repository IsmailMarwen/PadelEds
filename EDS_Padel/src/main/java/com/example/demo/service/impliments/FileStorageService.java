package com.example.demo.service.impliments;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileStorageService {
    private final String uploadDir = "E:/PFA/PadelEds/EDS_Padel/Images/logo";

    public String storeFile(Object fileOrUrlOrBase64) throws IOException {
        if (fileOrUrlOrBase64 instanceof MultipartFile) {
            // Handle MultipartFile
            return storeMultipartFile((MultipartFile) fileOrUrlOrBase64);
        } else if (fileOrUrlOrBase64 instanceof String) {
            String fileOrUrl = (String) fileOrUrlOrBase64;
            if (fileOrUrl.startsWith("http")) {
                // Handle URL
                return storeFileFromUrl(fileOrUrl);
            } else if (fileOrUrl.startsWith("data:")) {
                // Handle Base64
                return storeBase64File(fileOrUrl);
            } else {
                throw new IllegalArgumentException("Unsupported file or URL format");
            }
        } else {
            throw new IllegalArgumentException("Unsupported file or URL format");
        }
    }

    private String storeMultipartFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toAbsolutePath().toString();
    }

    private String storeFileFromUrl(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        String fileName = StringUtils.getFilename(url.getPath());
        Path uploadPath = Paths.get(uploadDir);

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(fileName);
        FileUtils.copyURLToFile(url, filePath.toFile());

        return filePath.toAbsolutePath().toString();
    }

    private String storeBase64File(String base64Data) throws IOException {
        // Extract base64 data
        String[] parts = base64Data.split(",");
        String base64Encoded = parts[1];

        // Decode base64 to byte array
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);

        // Determine file name from base64 data (assuming it's a generic name here)
        String fileName = "base64file";

        Path uploadPath = Paths.get(uploadDir);

        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, decodedBytes);

        return filePath.toAbsolutePath().toString();
    }
}
