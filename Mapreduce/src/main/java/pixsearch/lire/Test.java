package pixsearch.lire;

import net.semanticmetadata.lire.imageanalysis.CEDD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by michael on 10/06/15.
 */
public class Test {

    public  static void main ( String args[] ) throws FileNotFoundException {
        String queryFeature = "cedd 0 0 7 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 2 2 0 0 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";

        CEDD queryCedd = new CEDD();
        CEDD targetCedd = new CEDD();

        queryCedd.setStringRepresentation(queryFeature);

        BufferedReader reader = new BufferedReader(new FileReader("/Users/michael/IdeaProjects//hdfs/src/main/java/at/pixsearch/lire/part-r-00000"));

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\t");
//                System.out.println(line);
//                System.out.println(split[0]);
//                System.out.println(split[1]);
                targetCedd.setStringRepresentation("cedd " + split[1]);
                System.out.println(queryCedd.getDistance(targetCedd));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
