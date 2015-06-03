package at.pixsearch.mvc;

import at.pixsearch.mvc.service.HDFSService;
import at.pixsearch.mvc.service.HDFSServiceImpl;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by michael on 03/06/15.
 */
public class HDFSIOTest {

    @Test
    public void testReadFile() throws IOException, URISyntaxException {
        HDFSService hdfsService = new HDFSServiceImpl();
        File file =hdfsService.getFile(new Path("/user/michael/input/input.txt"));



    }
}
