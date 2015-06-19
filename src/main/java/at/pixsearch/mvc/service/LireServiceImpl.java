package at.pixsearch.mvc.service;

import net.semanticmetadata.lire.imageanalysis.CEDD;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * Created by michael on 17/06/15.
 */
@Service("lireService")
public class LireServiceImpl implements LireService {

    @Override
    public String getCEDDString(BufferedImage image) {
        CEDD feature = new CEDD();
        feature.extract(image);
        return feature.getStringRepresentation().substring(9);
    }
}
