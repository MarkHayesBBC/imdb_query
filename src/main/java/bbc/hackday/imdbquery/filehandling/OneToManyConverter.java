package bbc.hackday.imdbquery.filehandling;

import bbc.hackday.imdbquery.scvclient.S3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by hayesm13 on 18/07/2017.
 */
public class OneToManyConverter {

    private static final Logger logger = LoggerFactory
            .getLogger(S3Client.class);
    private static final String OUTPUT_FOLDER = "D:/hackday_data/";

    public static void unzipFile(File gzFile) {
        String origFileName = gzFile.getName();

        byte[] buf = new byte[1024 * 1024];


        try (InputStream zippedInput = new FileInputStream(gzFile); GZIPInputStream input = new GZIPInputStream(zippedInput)) {
            ;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
