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

    @PostMapping("/user/login")
    public ResponseModel<Void> userLogin(@RequestBody UserModel model){return this.backendServer.userLogin(model);}

    @GetMapping("/get/all/user/history")
    public List<HistoryModel> getAllUserHistory(@RequestParam String email){return this.backendServer.getAllUserHistory(email);}

    @GetMapping("/find/all/choices")
    public List<ChoiceModel> findAllChoices(){
        return this.backendServer.findAllChoices();
    }

    @PostMapping("/get/result")
    public ResponseModel<Void> getResult(@RequestBody DataModel model){return this.backendServer.getResult(model);}

    @GetMapping("/get/overview")
    public List<OverviewModel> getOverview(){return this.backendServer.getOverview();}

}
