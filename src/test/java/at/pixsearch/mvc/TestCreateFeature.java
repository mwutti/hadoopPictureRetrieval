package at.pixsearch.mvc;


import net.semanticmetadata.lire.imageanalysis.CEDD;
import net.semanticmetadata.lire.imageanalysis.LireFeature;
import net.semanticmetadata.lire.imageanalysis.sift.Feature;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by michael on 03/06/15.
 */
public class TestCreateFeature {

    @Test
    public void testFeature() throws IOException {
        BufferedImage image;

        image = ImageIO.read(new File("src/test/img/Hadoop_logo.jpg"));
        LireFeature feature = new CEDD();
        feature.extract(image);
        System.out.println(String.format("Picture Histogram: %s", Arrays.toString(feature.getDoubleHistogram())));

        image = ImageIO.read(new File("src/test/img/F0SHC7MGGBZPKM4.LARGE.jpg"));
        LireFeature feature2 = new CEDD();
        feature2.extract(image);
        System.out.println(String.format("Picture Histogram: %s", Arrays.toString(feature2.getDoubleHistogram())));

    }
}
