package dev._xdbe.booking.creelhouse.infrastructure.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VulnerableControllerCopy {

    @GetMapping("/xss-copy")
    public ResponseEntity<String> getXssCopy(
        @RequestParam(defaultValue = "https://http.cat/images/400.jpg") String imageLocation
    ) {
        String htmlString = "<img src=%s width=\"400\" height=\"300\"/>";
        String payload = String.format(htmlString, imageLocation);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
