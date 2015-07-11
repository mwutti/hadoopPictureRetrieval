package pixsearch.mapred;

import net.semanticmetadata.lire.imageanalysis.CEDD;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by michael on 04/06/15.
 */
public class PictureMapper extends Mapper<NullWritable, BytesWritable, Text, Text > {
    /**
     * Receives a Single File as value(WholeInputFormat).
     * After that the FilePath of the Input Image is extracted.
     * Next step is to Extract the CEDD Feature Vektor from value.
     * Writes (key, value) -> (filePath, featureVektor)
     *
     * @param key Key
     * @param value Value
     * @param context Context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {

        String filePath =  ((FileSplit)context.getInputSplit()).getPath().toString().split("/images/")[1];
        Text hFilePath = new Text(filePath);

        //Feature extraction
        CEDD cedd = new CEDD();
        InputStream inputStream = new ByteArrayInputStream(value.getBytes());
        cedd.extract(ImageIO.read(inputStream));

        // Just interested in feature values
        //substring because of buggy implementation of CEDD.setStringRepresentation() in Lire Framework
        String feature = cedd.getStringRepresentation().substring(9);
        Text hFeature = new Text(feature);

        context.write(hFilePath, hFeature);
        }
    }

