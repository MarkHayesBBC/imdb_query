package bbc.hackday.imdbquery;

import bbc.hackday.imdbquery.filehandling.FileHandler;
import bbc.hackday.imdbquery.filehandling.OneToManyConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import bbc.hackday.imdbquery.scvclient.S3Client;

import java.io.File;

/** bbc.hackday.imdbquery.Launcher for the application. This class ensures that all Spring wiring/dependency injection is completed before running the application **/
@SpringBootApplication
@ImportResource("classpath:/spring-context.xml")
public class Launcher {

	public static void main(String[] args) {
		ConfigurableApplicationContext springContext = SpringApplication.run(Launcher.class);
		//S3Client.downloadAllIMDBFiles();
		//FileHandler.unzipFile(new File("D:/hackday_data/" + ImdbFile.PRICIPALS.getFileName()));
		OneToManyConverter.convert(new File("D:/hackday_data/" + ImdbFile.PRICIPALS.getFileName()));
	}


}
