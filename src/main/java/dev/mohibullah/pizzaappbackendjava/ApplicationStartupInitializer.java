package dev.mohibullah.pizzaappbackendjava;

import dev.mohibullah.pizzaappbackendjava.models.Setting;
import dev.mohibullah.pizzaappbackendjava.models.User;
import dev.mohibullah.pizzaappbackendjava.repositories.SettingRepository;
import dev.mohibullah.pizzaappbackendjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class ApplicationStartupInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;


    public void run(ApplicationArguments args) {
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

            User doesAdminExist = userRepository.findAdminUser();

            if (doesAdminExist == null) {
                System.out.println("Creating default admin user");
                User newUser = new User();
                newUser.setEmail("admin@pizzaapp.com");
                newUser.setRole("admin");
                newUser.setPassword("ABC123as$");
                newUser.setFirstName("admin");
                newUser.setLastName("admin");
                newUser.setMobileNo("0000000");
                newUser.setAddress("nill");

                userRepository.save(newUser);
                doesAdminExist = newUser;
                System.out.println("Admin user created");
            }

            Optional<Setting> doesSettingExist = settingRepository.findById(1);

            if (doesSettingExist.isEmpty()) {
                Setting setting = new Setting();
                setting.setReviewSetting(false);
                setting.setPaymentMethod(false);
                setting.setAppleIdSignIn(false);
                setting.setGoogleIdSignIn(false);
                setting.setCurrency("$");

                setting.setRestaurant_name("Restaurant's Name here*");
                setting.setRestaurant_description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vel ornare orci. Ut consequat sapien ante, a posuere justo fermentum sed. Etiam euismod malesuada urna nec efficitur. Phasellus nec justo sit amet justo volutpat gravida. Morbi tristique euismod nisi, sit amet vulputate augue viverra nec. Maecenas a metus at arcu pulvinar venenatis vel nec nibh.");
                setting.setRestaurant_contactNos("00000000");
                setting.setRestaurant_socialLinks("https://www.facebook.com/pizza-app;https://www.youtube.com/@pizza-app;https://twitter.com/pizza-app;https://www.instagram.com/pizza-app;https://www.pizza-app.com");
                setting.setRestaurant_timings("01:00;23:00");
                setting.setRestaurant_cuisines("Restaurant's Cuisines here*");
                setting.setRestaurant_location("Restaurant's Locations here*");
                setting.setCreatedBy(doesAdminExist);
                setting.setUpdatedBy(doesAdminExist);
                settingRepository.save(setting);
                System.out.println("Default setting applied");

            }

        } catch (FileAlreadyExistsException e) {
            System.out.println("Folder structure already exists.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
