package pixsearch.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by michael on 10/06/15.
 */
public class Search {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length == 0) {
            throw new IllegalArgumentException("usage: - featureVector");
        }

        String featureVector = args[0];
        Configuration configuration = new Configuration();
        configuration.set("featureVektor", featureVector);

        Job job = Job.getInstance(configuration, "Search Image");
        job.setJarByClass(Search.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setMapperClass(SearchMapper.class);
        job.setCombinerClass(SearchReducer.class);
        job.setReducerClass(SearchReducer.class);
        job.setOutputKeyClass(FloatWritable.class);
        job.setOutputValueClass(Text.class);


        FileInputFormat.addInputPath(job, new Path("/user/michael/output"));
        FileOutputFormat.setOutputPath(job, new Path("/user/michael/output/search"));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
