package at.pixsearch.mvc;

import at.pixsearch.mvc.service.HDFSService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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

	@RequestMapping(value="hadoop/output" , method = RequestMethod.GET)
	public String hadoop(ModelMap model) throws URISyntaxException, IOException {
		File result = hdfsService.getFile( new Path("/user/michael/input/input1.txt") );

		model.addAttribute("result", "success");
		return "hello";
	}

	@RequestMapping(value="hadoop/input" , method = RequestMethod.GET)
	public String hadoopWrite(ModelMap model) throws URISyntaxException, IOException {
		String result = null;

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get( new URI("hdfs://laptop:9000"), conf);

		Path file = new Path("/user/michael/output/result.txt");
		FSDataOutputStream out = fs.create(file);

		out.write("Test".getBytes());
		out.write(" succes".getBytes());


		out.close();
		fs.close();

		model.addAttribute("result", "File read success");
		return "hello";
	}

	@RequestMapping(value="hadoop/mapred" , method = RequestMethod.GET)
	public String hadoopMapred(ModelMap model) throws URISyntaxException, IOException {
		String result = null;

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get( new URI("hdfs://laptop:9000"), conf);

		Path file = new Path("/user/michael/input/input1.txt");
		FSDataInputStream getIt = fs.open(file);
		BufferedReader d = new BufferedReader(new InputStreamReader(getIt));

		String s = "";
		while ((s = d.readLine()) != null) {
			result += s;
		}
		d.close();
		fs.close();

		model.addAttribute("result", result);
		return "hello";
	}
}