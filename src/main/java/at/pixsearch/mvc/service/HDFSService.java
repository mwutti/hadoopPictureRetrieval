package at.pixsearch.mvc.service;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by michael wutti on 31.05.15.
 */

public interface HDFSService {

    /**
     *
     * @return HDFS Object
     * @throws URISyntaxException
     * @throws IOException
     */
    FileSystem getFileSystem() throws URISyntaxException, IOException;

    /**
     *
     * @param path Path to file
     * @return A text-based files by given Path from hdfs
     * @throws IOException
     * @throws URISyntaxException
     */
    File getTextFile(Path path) throws IOException, URISyntaxException;

    /**
     *
     * @param file file to save
     * @return true if file was successfully saved
     * @throws URISyntaxException
     * @throws IOException
     */
    Boolean saveFile(File file) throws URISyntaxException, IOException;

    /**
     *
     * @param path path to file
     * @return true if deleting was successful false otherwise
     * @throws URISyntaxException
     * @throws IOException
     */
    Boolean deleteFile(Path path) throws URISyntaxException, IOException;

    /**
     *
     * @param path Path to a feature vektor file
     * @return the path to the 10 most similar files
     * @throws IOException
     * @throws URISyntaxException
     */
    List<String> mostSimilar(Path path) throws IOException, URISyntaxException;

    /**
     *
     * @param path path to picture in hdfs
     * @return BufferedImage if picture exists, null otherwise
     * @throws IOException
     * @throws URISyntaxException
     */
    BufferedImage getImage(Path path) throws IOException, URISyntaxException;
}
