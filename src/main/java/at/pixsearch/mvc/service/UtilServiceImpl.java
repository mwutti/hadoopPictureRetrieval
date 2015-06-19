package at.pixsearch.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by michael on 17/06/15.
 */
@Service("utilService")
public class UtilServiceImpl implements  UtilService {

    @Override
    public BufferedImage wrap(MultipartFile file) throws IOException {
        File result = new File(file.getOriginalFilename());
        file.transferTo(result);
        return ImageIO.read(result);
    }
}
