package pixsearch.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by michael on 04/06/15.
 */
public class WholeFileRecordReader extends RecordReader<NullWritable, BytesWritable>{

    private FileSplit split;
    private Configuration configuration;

    private final BytesWritable currentValue = new BytesWritable();
    private boolean fileProcessed = false;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        split = (FileSplit)inputSplit;
        configuration = taskAttemptContext.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(configuration);

        if(fileSystem.isDirectory(split.getPath())) {
            return false;
        }

        if (fileProcessed) {
            return  false;
        }

        int fileLength = (int) split.getLength();
        byte[] result = new byte[fileLength];


        FSDataInputStream inputStream = null;

        try {
            inputStream = fileSystem.open(split.getPath());
            IOUtils.readFully(inputStream, result, 0, fileLength);
            currentValue.set(result, 0, fileLength);
        } finally {
            IOUtils.closeStream(inputStream);
        }
        fileProcessed = true;
        return true;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return currentValue;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
