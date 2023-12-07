package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_artifact extends SuperObject {
    public OBJ_artifact() {
        name = "artifact";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/artifact.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}