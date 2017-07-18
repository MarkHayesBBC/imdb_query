package bbc.hackday.imdbquery.restcontroller;

import java.io.File;

public interface RequestHandler {

    public File getS3File(String pid);
}
