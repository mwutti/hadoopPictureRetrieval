package at.pixsearch.mvc.service;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


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

        return FileSystem.get(new URI(HDFS), configuration);
    }

    @Override
    public File getTextFile(Path path) throws IOException, URISyntaxException {
        FileSystem fileSystem = getFileSystem();

        File result = File.createTempFile("tmp", Long.toString(System.nanoTime()));
        if (fileSystem.exists(path)) {

            FSDataInputStream inputStream = fileSystem.open(path);
            OutputStream outputStream = new FileOutputStream(result);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            String line = "";
            //logger.info("Reading from HDFS:");
            while ((line = reader.readLine()) != null) {

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
    public BufferedImage getImage(Path path) throws IOException, URISyntaxException {
        //TODO care about filesystem.close()
        FileSystem fileSystem = getFileSystem();

        File result = File.createTempFile("tmp", Long.toString(System.nanoTime()));

        if (fileSystem.exists(path)) {

            FSDataInputStream inputStream = fileSystem.open(path);
            OutputStream outputStream = new FileOutputStream(result);

            IOUtils.copy(inputStream, outputStream);
        }

        return ImageIO.read(result);
    }

    @Override
    public Boolean saveFile(File file) throws URISyntaxException, IOException {
        InputStream inputStream = new FileInputStream(file);

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

    @Override
    public Boolean deleteFile(Path path) throws URISyntaxException, IOException {
        FileSystem fileSystem = getFileSystem();
        return fileSystem.delete(path, true);
    }

    @Override
    public List<String> mostSimilar(Path path) throws IOException, URISyntaxException {
        List<String> result = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getFileSystem().open(path)));
        String line = reader.readLine();
        int i = 0;
        while (line != null && i < 10) {
            i++;
            //TODO validate uploaded File name must not contain tilde
            result.add(line.split("\t")[1]);
            line = reader.readLine();
        }

        return result;
    }

    private synchronized String getFilename(String originalFilename) {
        return String.format("%s_%s", saveHelp++, originalFilename);
    }
}
