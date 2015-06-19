package at.pixsearch.mvc.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by michael on 17/06/15.
 */
public interface UtilService {

    BufferedImage wrap(MultipartFile file) throws IOException;
}
