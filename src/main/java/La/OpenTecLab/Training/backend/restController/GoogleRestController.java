package La.OpenTecLab.Training.backend.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GoogleRestController {
//    @RequestMapping("/")
    public String home(){
        return "hi home";
    }
//    @RequestMapping("/secured")
    public String secured(){
        return "hi secured";
    }
}
