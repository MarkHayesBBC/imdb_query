package bbc.hackday.imdbquery.filehandling;

import bbc.hackday.imdbquery.scvclient.S3Client;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileHandler {

    private static final Logger logger = LoggerFactory
            .getLogger(S3Client.class);
    private static final String OUTPUT_FOLDER = "D:/hackday_data/";

    public static void writeFile(InputStream input, String name){
        byte[] buf = new byte[1024 * 1024];
        try (OutputStream out = new FileOutputStream(OUTPUT_FOLDER + name)) {
            int count;
            while ((count = input.read(buf)) != -1) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                out.write(buf, 0, count);
            }
        } catch (IOException | InterruptedException e) {
            logger.error("error writing file", e);
        }

    }

    public static void unzipFile(File gzFile) {
        String origFileName = gzFile.getName();

        byte[] buf = new byte[1024 * 1024];


        try (InputStream zippedInput = new FileInputStream(gzFile);
             GZIPInputStream input = new GZIPInputStream(zippedInput);
             OutputStream out = new FileOutputStream(OUTPUT_FOLDER + origFileName.substring(0, origFileName.length()-3))) {
            int count;
            while ((count = input.read(buf)) != -1) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                out.write(buf, 0, count);
            }
            out.close();
            input.close();
        } catch (IOException | InterruptedException e) {
            logger.error("error unzipping file", e);
        }
    }

    private static void parseTSVFile(File tsvFile) throws FileNotFoundException {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);

        // parses all rows in one go.
        List<String[]> allRows = parser.parseAll(new FileReader(tsvFile));
    }
}

