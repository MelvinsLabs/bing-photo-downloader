/*
 * 
 */

package me.melvins.labs;

import me.melvins.labs.pojo.model.BingImageModel;
import me.melvins.labs.pojo.model.BingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFormatMessageFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Mels
 */
@SpringBootApplication
public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class, new MessageFormatMessageFactory());

    private static final String URL_SEPARATOR = "/";

    private static String bingPhotoOfTheDayUrlTemplate = "http://www.bing.com/HPImageArchive" +
            ".aspx?format=js&idx=0&n={0}&mkt=en-US";

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        String numberOfImagesToDownload = args[0];
        // Form the URL to invoke Bing Photo of the Day API.
        String bingPhotoOfTheDayUrl = MessageFormat.format(App.bingPhotoOfTheDayUrlTemplate, numberOfImagesToDownload);

        String folderPath = "C:\\Users\\melvin_mathai\\Pictures\\Bing\\";

        RestTemplate restTemplate = new RestTemplate();
        BingModel bingModel = restTemplate.getForObject(bingPhotoOfTheDayUrl, BingModel.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (BingImageModel bingImageModel : bingModel.getBingImageModelList()) {
            LOGGER.debug("Processing {0}", bingImageModel);

            String imageUri = bingImageModel.getUrl();
            String imageName = imageUri.split(URL_SEPARATOR)[4];
            String imageUrl = MessageFormat.format("http://bing.com{0}", imageUri);

            LOGGER.info("Downloading Image {0}", imageName);
            ResponseEntity<byte[]> response = restTemplate.exchange(imageUrl, GET, entity, byte[].class, "1");

            HttpStatus statusCode = response.getStatusCode();
            if (statusCode == OK) {
                LOGGER.info("Writing Image {0} To Disk", imageName);
                try {
                    Files.write(Paths.get(folderPath + imageName), response.getBody());

                } catch (IOException ex) {
                    LOGGER.error("Unexpected Exception While Writing Image {0}", imageName, ex);
                }

            } else {
                LOGGER.error("Image {0} Could Not Be Downloaded Due To {1}", imageName, statusCode);
            }
        }
    }

}
