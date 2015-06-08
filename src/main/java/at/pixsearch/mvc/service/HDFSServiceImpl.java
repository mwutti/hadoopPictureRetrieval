package at.pixsearch.mvc.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by michael wutti on 31.05.15.
 */
@Service("hdfsService")
public class HDFSServiceImpl implements HDFSService {

    private static final String HDFS = "hdfs://localhost:9000";



    @Override
    public FileSystem getFileSystem() throws URISyntaxException, IOException {
        return  FileSystem.get( new URI(HDFS), new Configuration() );

    }

    @Override
    public File getFile(Path path) throws IOException, URISyntaxException {
        FileSystem fileSystem = getFileSystem();

        File result = File.createTempFile("tmp", Long.toString(System.nanoTime()));
        if(fileSystem.exists(path)) {

            FSDataInputStream inputStream = fileSystem.open(path);
            OutputStream outputStream = new FileOutputStream(result);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            String line = "";
            //logger.info("Reading from HDFS:");
            while ( (line = reader.readLine()) != null ) {

                writer.write(line);
               //logger.info(line);
            }
            reader.close();
            writer.close();
            fileSystem.close();
        }
        return result;
    }

    @Override
    public Boolean saveFile(File file) {
        return null;
    }
}
