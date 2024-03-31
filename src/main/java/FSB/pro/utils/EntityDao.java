package FSB.pro.utils;

import javafx.scene.image.Image;

public interface EntityDao {
    public String fetchUsername(long id);
    public Image fetchProfileImage(long id);
}
