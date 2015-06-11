package pixsearch.mapred;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by michael on 04/06/15.
 */
public class PictureReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text filename, Iterable<Text> feature, Context context) throws IOException, InterruptedException {
        context.write(filename, feature.iterator().next());
    }
}

