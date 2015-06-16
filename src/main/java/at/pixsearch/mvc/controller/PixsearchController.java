package at.pixsearch.mvc.controller;


import at.pixsearch.mvc.model.UploadedFile;
import net.semanticmetadata.lire.imageanalysis.CEDD;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;

import at.pixsearch.mvc.service.HDFSService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PixsearchController {
	@Autowired
	private HDFSService hdfsService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "index";
	}

	@RequestMapping(value = "hadoop/output", method = RequestMethod.GET)
	public String hadoop(ModelMap model) throws URISyntaxException, IOException {
		File result = hdfsService.getFile(new Path("/user/michael/input/input1.txt"));

		model.addAttribute("result", "success");
		return "index";
	}

	@RequestMapping(value = "hadoop/input", method = RequestMethod.GET)
	public String hadoopWrite(ModelMap model) throws URISyntaxException, IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);

		Path file = new Path("/user/michael/output/result.txt");
		FSDataOutputStream out = fs.create(file);

		out.close();
		fs.close();

		model.addAttribute("result", "File read success");
		return "index";
	}

	@RequestMapping(value = "hadoop/extractFeatures", method = RequestMethod.GET)
	public String hadoopMapred(ModelMap model) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
       Process process = Runtime.getRuntime().exec("hadoop jar /Users/michael/IdeaProjects/hdfs/target/hdfs-1.0-SNAPSHOT.jar at.pixsearch.mapred.PictureMapReduce");

        InputStream in = process.getErrorStream();
        IOUtils.copy(in, System.out);
		process.waitFor();

		return "hello";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	 public String upload(@RequestParam("file") MultipartFile file, ModelMap model) throws IOException, URISyntaxException, InterruptedException {


		File toSave = new File(file.getOriginalFilename());
		file.transferTo(toSave);

		//Query feature and Mapreduce for Searching
		BufferedImage image = ImageIO.read(toSave);

		CEDD feature = new CEDD();
		feature.extract(image);
		String featureString = feature.getStringRepresentation().substring(9);

		//TODO Create ID to identify the output of the MAPRED Job to retrieve the result

		//If outputFile exists delete
		hdfsService.deleteFile(new Path("/user/michael/output/search"));
		String processCommand[] = new String[]{"hadoop", "jar", "/Users/michael/Development/hadoopPictureRetrieval/" +
				"Mapreduce/target/Mapreduce-1.0-SNAPSHOT.jar", "pixsearch.mapred.Search", featureString};
		Process process = Runtime.getRuntime().exec(processCommand);

		InputStream in = process.getErrorStream();
		IOUtils.copy(in, System.out);
		process.waitFor();

		//Load Result

		List<String> mostSimiliar = hdfsService.mostSimilar((new Path("/user/michael/output/search/part-r-00000")));
		model.addAttribute("mostSimilar", mostSimiliar);
		return "result";

	}

	private UploadedFile getUploadedFileInfo(MultipartFile multipartFile) throws IOException {

		UploadedFile fileInfo = new UploadedFile();
		fileInfo.setName(multipartFile.getOriginalFilename());
		fileInfo.setSize(multipartFile.getSize());
		fileInfo.setType(multipartFile.getContentType());
		fileInfo.setLocation("Test");

		return fileInfo;
	}


}