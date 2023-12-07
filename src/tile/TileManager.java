package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * TileManager
 */
public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[48];
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        loadTilesFromDirectory("/res/tiles/");
        loadMap("/res/maps/world01.csv");
    }
    public void loadTilesFromDirectory(String directoryPath) {
        File directory = new File(Paths.get(".").toAbsolutePath().normalize().toString() + directoryPath);

        // Vérifier si le chemin spécifié est un répertoire
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Le chemin spécifié n'est pas un répertoire.");
        }

        // Liste des fichiers dans le répertoire
        File[] files = directory.listFiles();

        // Trier les fichiers par longueur de titre, puis par ordre ascendant
        Arrays.sort(files, Comparator.comparing(File::getName, Comparator
                .comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder())));

        // Liste des tiles chargées
        tile = new Tile[files.length];

        for (int i = 0; i < files.length; i++) {
            try {
                // Charger uniquement les fichiers .png
                if (files[i].isFile() && files[i].getName().toLowerCase().endsWith(".png")) {
                    tile[i] = new Tile();
                    tile[i].setImage(ImageIO.read(files[i]));
                    if (i < 40) {
                        tile[i].setCollision(false);    
                    } else {
                        tile[i].setCollision(true);
                    }
                }
            } catch (IOException e) {
                // Gérer les erreurs de chargement d'image
                e.printStackTrace();
            }
        }
    }
    public void loadMap(String filePath) {
        try {
            
            InputStream is = new FileInputStream(new File(Paths.get(".").toAbsolutePath().normalize().toString() + filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
            worldCol++;
            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public int[][] getMapTileNum() {
        return mapTileNum;
    }
    public Tile[] getTile() {
        return tile;
    }
}