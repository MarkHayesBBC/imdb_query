package bbc.hackday.imdbquery.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Main Request Mappings used to control application. Some of these operations are only valid for testing locally, on INT, or on TEST. This is controlled by the spring profile

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";


    RequestHandler handler;

    @RequestMapping(value = "/set_dax_send_pending")
    public ResponseEntity setDaxSendToPending(@RequestBody String pid) {
            handler.getS3File(pid);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

}**/
