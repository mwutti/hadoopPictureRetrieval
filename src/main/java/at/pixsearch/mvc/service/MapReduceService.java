package at.pixsearch.mvc.service;

import java.io.IOException;

/**
 * Created by michael on 17/06/15.
 */
public interface MapReduceService {
    void search(String featureString) throws IOException, InterruptedException;

    void extractFeatures() throws IOException, InterruptedException;
}
