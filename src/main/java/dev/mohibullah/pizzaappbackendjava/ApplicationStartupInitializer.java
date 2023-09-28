package dev.mohibullah.pizzaappbackendjava;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ApplicationStartupInitializer implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
        String productImagesFolderPath = "src/main/resources/static/ProductImages";
        String LOGOsFolderPath = "src/main/resources/static/LOGOs";
        String splashScreensFolderPath = "src/main/resources/static/SplashScreens";

        try {
            Path productImagePath = Paths.get(productImagesFolderPath);
            Path LOGOPath = Paths.get(LOGOsFolderPath);
            Path splashScreenPath = Paths.get(splashScreensFolderPath);
            Files.createDirectories(productImagePath);
            Files.createDirectories(LOGOPath);
            Files.createDirectories(splashScreenPath);
//            System.out.println("Folder structure created successfully.");
        } catch (FileAlreadyExistsException e) {
//            System.out.println("Folder structure already exists.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
