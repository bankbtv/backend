package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.*;
import La.OpenTecLab.Training.backend.service.BackendService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BackendRestController {
    private final BackendService backendServer;

    public BackendRestController(BackendService backendServer) {
        this.backendServer = backendServer;
    }

    @PostMapping("/insert/li4d")
    public ResponseModel<Void> insertLi4d(@RequestBody Li4dModel model){return this.backendServer.insertLi4d(model);}

    @PostMapping("/insert/choices")
    public ResponseModel<Void> insertChoices(@RequestBody List<ChoiceModel> modelList){return this.backendServer.insertChoices(modelList);}

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
