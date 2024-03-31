package FSB.pro.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserFetchImage {
    // Assuming the user's photo attribute contains the path to the image file
    public static Image fetchProfileImage(String photoPath) {
        try {
            // Load the image from the specified path
            Image profileImage = new Image("file:" + photoPath);

            return profileImage;
        } catch (Exception e) {
            System.err.println("Error loading profile image: " + e.getMessage());
            return null;
        }
    }
}
