package pixsearch.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by michael on 04/06/15.
 */
public class PictureMapReduce {
    
    public static void main (String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();

//        // this should be like defined in your yarn-site.xml
//        configuration.set("yarn.resourcemanager.address", "localhost:8088");
//        // framework is now "yarn", should be defined like this in mapred-site.xm
//        configuration.set("mapreduce.framework.name", "yarn");
//        // like defined in hdfs-site.xml
//        configuration.set("fs.default.name", "hdfs://localhost:50070");


        Job job = Job.getInstance(configuration, "PictureMapReduce");
        job.setJobName("Feature Extraction");
        job.setJarByClass(PictureMapReduce.class);
        WholeFileInputFormat.setInputDirRecursive(job,true);
        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setMapperClass(PictureMapper.class);
        job.setCombinerClass(PictureReducer.class);
        job.setReducerClass(PictureReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        FileInputFormat.addInputPath(job, new Path("/images"));
        FileOutputFormat.setOutputPath(job, new Path("/features"));
        System.exit(job.waitForCompletion(true)?0:1);    
    }

    
}
