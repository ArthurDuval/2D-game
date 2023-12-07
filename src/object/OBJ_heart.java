package object;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/**
 * OBJ_Heart
 */
public class OBJ_heart extends SuperObject {
    public OBJ_heart() {
        name = "heart";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/heart_full.png"));
            image2 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/heart_half.png"));
            image3 = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}