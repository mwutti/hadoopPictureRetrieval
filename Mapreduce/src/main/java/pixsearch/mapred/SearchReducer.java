package pixsearch.mapred;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by michael on 10/06/15.
 */
public class SearchReducer extends Reducer<FloatWritable, Text, FloatWritable, Text> {

    @Override
    public void reduce(FloatWritable distance, Iterable<Text> filenames, Context context) throws IOException, InterruptedException {

        for (Text filename : filenames) {
            context.write(distance, filename);
        }

    }
}

