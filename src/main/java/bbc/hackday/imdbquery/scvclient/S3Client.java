package bbc.hackday.imdbquery.scvclient;

import bbc.hackday.imdbquery.filehandling.FileHandler;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S3Client {

    private static final Logger logger = LoggerFactory
            .getLogger(S3Client.class);

    private static final String BUCKET = "imdb-datasets";
    private static final String FOLDER = "documents/v1/current/";
    
    public static void downloadAllIMDBFiles() {
        downloadIMDBFile("title.basics.tsv.gz");
        downloadIMDBFile("title.crew.tsv.gz");
        downloadIMDBFile("title.episode.tsv.gz");
        downloadIMDBFile("title.principals.tsv.gz");
        downloadIMDBFile("title.ratings.tsv.gz");
        downloadIMDBFile("name.basics.tsv.gz");
    }

    public static void downloadIMDBFile(String fileName) {
        ProfileCredentialsProvider credentialsProvider =
                new ProfileCredentialsProvider("C://Users//hayesm13//.aws//credentials", "default");

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(Regions.US_EAST_1).build();

        try {
            // Note: It's necessary to set RequesterPays to true
            GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET, FOLDER + fileName)
                    .withRequesterPays(true);

            logger.info("Downloading file: " + fileName);

            S3Object s3object = s3Client.getObject(getObjectRequest);

            logger.info("Content-Type: "  +
                    s3object.getObjectMetadata().getContentType());

            FileHandler.writeFile(s3object.getObjectContent(), fileName);
        } catch (AmazonClientException e) {
            logger.error("error occurred", e);
        }
    }

}
