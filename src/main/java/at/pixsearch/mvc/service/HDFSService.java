package at.pixsearch.mvc.service;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by michael wutti on 31.05.15.
 */

public interface HDFSService {

    FileSystem getFileSystem() throws URISyntaxException, IOException;

    File getFile(Path path) throws IOException, URISyntaxException;

    Boolean saveFile(File file);

}
