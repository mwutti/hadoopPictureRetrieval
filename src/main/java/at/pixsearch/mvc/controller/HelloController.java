package at.pixsearch.mvc.controller;


import at.pixsearch.mvc.model.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;

import at.pixsearch.mvc.service.HDFSService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HelloController {
	@Autowired
	private HDFSService hdfsService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "hadoop/output", method = RequestMethod.GET)
	public String hadoop(ModelMap model) throws URISyntaxException, IOException {
		File result = hdfsService.getFile(new Path("/user/michael/input/input1.txt"));

		model.addAttribute("result", "success");
		return "hello";
	}

	@RequestMapping(value = "hadoop/input", method = RequestMethod.GET)
	public String hadoopWrite(ModelMap model) throws URISyntaxException, IOException {
		String result = null;

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);

		Path file = new Path("/user/michael/output/result.txt");
		FSDataOutputStream out = fs.create(file);

		out.write("Test".getBytes());
		out.write(" succes".getBytes());


		out.close();
		fs.close();

		model.addAttribute("result", "File read success");
		return "hello";
	}

	@RequestMapping(value = "hadoop/mapred", method = RequestMethod.GET)
	public String hadoopMapred(ModelMap model) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
       Process process = Runtime.getRuntime().exec("hadoop jar /Users/michael/IdeaProjects/hdfs/target/hdfs-1.0-SNAPSHOT.jar at.pixsearch.mapred.PictureMapReduce");

        InputStream in = process.getErrorStream();
        IOUtils.copy(in, System.out);
		process.waitFor();

		return "hello";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	List<UploadedFile> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

		// Getting uploaded files from the request object
		Map<String, MultipartFile> fileMap = request.getFileMap();

		// Maintain a list to send back the files info. to the client side
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

		// Iterate through the map
		for (MultipartFile multipartFile : fileMap.values()) {


			UploadedFile fileInfo = getUploadedFileInfo(multipartFile);



			// adding the file info to the list
			uploadedFiles.add(fileInfo);
		}

		return uploadedFiles;
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