package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_runningShoes extends SuperObject {
    public OBJ_runningShoes() {
        name = "runningShoes";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/itemPokeball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
