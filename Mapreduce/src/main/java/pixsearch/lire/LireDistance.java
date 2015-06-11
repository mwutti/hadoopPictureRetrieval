package pixsearch.lire;
import net.semanticmetadata.lire.imageanalysis.CEDD;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by michael on 07/06/15.
 */
public class LireDistance {
    public static void main (String args[]) {
        File input = new File("/Users/michael/Pictures/251_c.png");
        try {
            BufferedImage image = ImageIO.read(input);
            CEDD cedd = new CEDD();
            cedd.extract(image);
            String stringRepr = "cedd 0 1 7 0 1 1 0 0 1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";

            System.out.println(stringRepr);

//            System.out.println(Arrays.toString(cedd.getDoubleHistogram()));




//            String feature = "cedd " + cedd.getStringRepresentation().substring(9);
            CEDD second = new CEDD();
            second.setStringRepresentation(stringRepr);

            System.out.println("distance" + cedd.getDistance(second));
            System.out.println("distance" + second.getDistance(cedd));



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
