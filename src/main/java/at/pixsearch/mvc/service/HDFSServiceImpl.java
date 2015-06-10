package at.pixsearch.mvc.service;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
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
    private Integer saveHelp = 0;

    @Override
    public FileSystem getFileSystem() throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
//        configuration.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//        configuration.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        return  FileSystem.get( new URI(HDFS), configuration );

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
    public Boolean saveFile(File file) throws URISyntaxException, IOException {
        String result = null;
        InputStream inputStream = new FileInputStream(file);

        Configuration conf = new Configuration();
        FileSystem fileSystem = getFileSystem();
        String filename = getFilename(file.getName());
        Path path = new Path(String.format("/user/michael/uploaded/%s", filename));
        FSDataOutputStream out = fileSystem.create(path);

        out.write(IOUtils.toByteArray(inputStream));

        inputStream.close();
        out.close();
        fileSystem.close();

        return true;
    }

    private synchronized String getFilename(String originalFilename) {
        return String.format("%s_%s", saveHelp++, originalFilename);
    }
}
