package pixsearch.mapred;

import net.semanticmetadata.lire.imageanalysis.CEDD;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by michael on 10/06/15.
 */
public class SearchMapper extends Mapper<LongWritable, Text, FloatWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        CEDD queryCEDD = new CEDD();
        CEDD targetCEDD = new CEDD();

        String queryFeature = "cedd " + context.getConfiguration().get("featureVektor");
        String[] splitLine = value.toString().split("\t");

        queryCEDD.setStringRepresentation(queryFeature);

        targetCEDD.setStringRepresentation("cedd " + splitLine[1]);
        Float distance = queryCEDD.getDistance(targetCEDD);

        Text hFileName = new Text(splitLine[0]);
        FloatWritable hDistance = new FloatWritable(distance);

        context.write(hDistance, hFileName);
    }

}
