package at.pixsearch.mvc.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by michael on 17/06/15.
 */
@Service("MapReduceService")
public class MapReduceServiceImpl implements MapReduceService {

    @Override
    public void search(String featureString) throws IOException, InterruptedException {
        String processCommand[] = new String[]{"hadoop", "jar", "/Users/michael/Development/hadoopPictureRetrieval/" +
                "Mapreduce/target/Mapreduce-1.0-SNAPSHOT.jar", "pixsearch.mapred.Search", featureString};

        Process process = Runtime.getRuntime().exec(processCommand);

        InputStream in = process.getErrorStream();
        IOUtils.copy(in, System.out);
        process.waitFor();
        in.close();
    }

    @Override
    public void extractFeatures() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("hadoop jar /Users/michael/Development/hadoopPictureRetrieval/" +
                "Mapreduce/target/Mapreduce-1.0-SNAPSHOT.jar pixsearch.mapred.PictureMapReduce");

        InputStream in = process.getErrorStream();
        IOUtils.copy(in, System.out);
        process.waitFor();
        in.close();
    }
}
