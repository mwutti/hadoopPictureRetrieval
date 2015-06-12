package pixsearch.mapred;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;


/**
 * Created by michael on 10/06/15.
 */
public class SearchReducer extends Reducer<FloatWritable, Text, FloatWritable, Text> {

    @Override
    public void reduce(FloatWritable distance, Iterable<Text> filenames, Context context) throws IOException, InterruptedException {
/**
 * TODO refactor, write only 10 most similar images to file
 *
 *      String[][] minDistances = new String[][]{
 *           {Float.MAX_VALUE + "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""},
 *           {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}, {Float.MAX_VALUE+ "", ""}
 *       };
 */

        Iterator<Text> iterator = filenames.iterator();

        while (iterator.hasNext()) {
            context.write(distance, iterator.next());
        }


    }
}

