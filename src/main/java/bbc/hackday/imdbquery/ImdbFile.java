package bbc.hackday.imdbquery;

/**
 * Created by hayesm13 on 18/07/2017.
 */
public enum ImdbFile {

    BASICS("title.basics.tsv.gz"),
    CREW("title.crew.tsv.gz"),
    EPISODE("title.episode.tsv.gz"),
    PRICIPALS("title.principals.tsv.gz"),
    RATINGS("title.ratings.tsv.gz"),
    NAME("name.basics.tsv.gz");

    private String fileName;

    ImdbFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
