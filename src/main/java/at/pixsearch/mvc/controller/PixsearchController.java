package at.pixsearch.mvc.controller;

import at.pixsearch.mvc.service.LireService;
import at.pixsearch.mvc.service.MapReduceService;
import at.pixsearch.mvc.service.UtilService;
import org.apache.hadoop.fs.Path;
import at.pixsearch.mvc.service.HDFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.List;


@Controller
@RequestMapping("/")
public class PixsearchController {
	@Autowired
	private HDFSService hdfsService;

	@Autowired
	private LireService lireService;

	@Autowired
	private UtilService utilService;

	@Autowired
	private MapReduceService mapReduceService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "hadoop/extractFeatures", method = RequestMethod.GET)
	public String hadoopMapred(ModelMap model) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
       	mapReduceService.extractFeatures();
		return "index";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	 public String upload(@RequestParam("file") MultipartFile file, ModelMap model) throws IOException, URISyntaxException, InterruptedException {
		BufferedImage queryImage = utilService.wrap(file);

		String featureString = lireService.getCEDDString(queryImage);

		//TODO Create ID to identify the output of the MAPRED Job to retrieve the result
		//If outputFile exists delete
		hdfsService.deleteFile(new Path("/user/michael/output/search"));

		mapReduceService.search(featureString);

		List<String> mostSimiliar = hdfsService.mostSimilar((new Path("/user/michael/output/search/part-r-00000")));

		model.addAttribute("mostSimilar", mostSimiliar);
		return "result";
	}

	@RequestMapping(value="getFile/", method = RequestMethod.GET)
	@ResponseBody
	public void getFile(@RequestParam("src") String src,  HttpServletResponse response) throws IOException, URISyntaxException {
		response.setContentType("image/png");

		BufferedImage image = hdfsService.getImage(new Path(String.format("/images/%s", src)));
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "png", outputStream);
		outputStream.close();
	}

}