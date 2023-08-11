package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.model.*;
import La.OpenTecLab.Training.backend.service.BackendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UseAPIRestController {
    private final BackendService backendServer;

    public UseAPIRestController(BackendService backendServer) {
        this.backendServer = backendServer;
    }

    @PostMapping("/find/user")
    public ResponseModel<Void> findUser(@RequestBody UserModel model){return this.backendServer.insertUser(model);}

    @PostMapping("/userData")
    public ResponseModel<Void> userData(@RequestBody DataModel model){
        return this.backendServer.userData(model);
    }

    @GetMapping("/reset/response")
    public ResponseModel<Void> resetResponse(@RequestParam String userEmail){
        return this.backendServer.resetResponse(userEmail);
    }

    @GetMapping("/find/all/choices")
    public List<ChoiceModel> findAllChoices(){
        return this.backendServer.findAllChoices();
    }

}