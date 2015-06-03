package at.pixsearch.mvc;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.ImageSearcher;
import net.semanticmetadata.lire.imageanalysis.CEDD;
import net.semanticmetadata.lire.impl.GenericFastImageSearcher;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: Mathias Lux, mathias@juggle.at
 * Date: 25.05.12
 * Time: 12:19
 */
public class LireSearchTest {

    @Before
    public void init() throws IOException {
        File file = new File("src/test/index");

        if (!file.exists()) {
            LireIndexTest lireIndexTest = new LireIndexTest();
            lireIndexTest.testCreateIndex();
        }
    }

    @Test
    public void findPictureTest() throws IOException {
        BufferedImage image;
        File file = new File("src/test/img/Hadoop_logo.jpg");

        image = ImageIO.read(file);

        IndexReader ir = DirectoryReader.open(FSDirectory.open(new File("src/test/index")));
        ImageSearcher searcher = new GenericFastImageSearcher(30, CEDD.class);
//        ImageSearcher searcher = new GenericFastImageSearcher(30, AutoColorCorrelogram.class);

        // searching with a image file ...
        ImageSearchHits hits = searcher.search(image, ir);
        // searching with a Lucene document instance ...
//        ImageSearchHits hits = searcher.search(ir.document(0), ir);
        for (int i = 0; i < hits.length(); i++) {
            String fileName = hits.doc(i).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            System.out.println(hits.score(i) + ": \t" + fileName);
        }
    }

}