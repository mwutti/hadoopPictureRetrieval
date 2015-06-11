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

    @Override
    public void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
        String fileName =  ((FileSplit)context.getInputSplit()).getPath().toString();

        Text hFileName = new Text(fileName);

        //Feature extraction
        CEDD cedd = new CEDD();
        InputStream inputStream = new ByteArrayInputStream(value.getBytes());
        cedd.extract(ImageIO.read(inputStream));

        // Just interested in feature values
        String feature = cedd.getStringRepresentation().substring(9);
        Text hFeature = new Text(feature);


        context.write(hFileName, hFeature);
        }
    }

