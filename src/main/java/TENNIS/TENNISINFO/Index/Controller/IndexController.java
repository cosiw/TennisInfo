package TENNIS.TENNISINFO.Index.Controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/api/hello")
    public String hello(){
        return "테스트입니다.";
    }
}
