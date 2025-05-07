package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import panel.OriginalPanel;
import setting.*;

public class TileManager {
    OriginalPanel op;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(OriginalPanel op, String mapFilePath) {
        this.op = op;
        tile = new Tile[12];
        mapTileNum = new int[op.maxScreenCol][op.maxScreenRow];
        getTileImage();
        loadMap(mapFilePath);
    }

    public void getTileImage() {

        setup(0, "/tiles/TileWall_0.png", true);
        setup(1, "/tiles/TileGround_1.png", false);
        setup(2, "/tiles/GroundBorderT_2.png", false);
        setup(3, "/tiles/GroundBorderB_3.png", false);
        setup(4, "/tiles/GroundBorderL_4.png", false);
        setup(5, "/tiles/GroundBorderR_5.png", false);
        setup(6, "/tiles/GroundCornerLB_6.png", false);
        setup(7, "/tiles/GroundCornerRB_7.png", false);
        setup(8, "/tiles/GroundCornerRT_8.png", false);
        setup(9, "/tiles/GroundCornerLT_9.png", false);
        setup(10, "/tiles/DoorLeft.png", true);
        setup(11, "/tiles/DoorRight.png", true);

    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, op.tileSize,
                    op.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < op.maxScreenCol && row < op.maxScreenRow) {

                String line = br.readLine();
                String Numbers[] = line.split(" ");

                // System.out.println(Arrays.toString(Numbers));

                while (col < op.maxScreenCol) {

                    int num = Integer.parseInt(Numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == op.maxScreenCol) {
                    col = 0;
                    row++;
                }

            }
            // System.out.println("------------------------------------------------");
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < op.maxScreenCol && row < op.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2D.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += op.tileSize;

            if (col == op.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += op.tileSize;
            }
        }

        // g2D.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2D.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
    }
}
