package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.model.ChoiceModel;
import La.OpenTecLab.Training.backend.model.Li4dModel;
import La.OpenTecLab.Training.backend.model.ResponseModel;
import La.OpenTecLab.Training.backend.model.UserModel;
import La.OpenTecLab.Training.backend.service.BackendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/api")
public class AdminRestcontroller {

    private final BackendService backendServer;

    public AdminRestcontroller(BackendService backendService, BackendService backendServer) {
        this.backendServer = backendServer;
    }
    @PostMapping("/find/all/users")
    public List<UserModel> findAllUsers(){return this.backendServer.findAllUsers();}

    @PostMapping("/insert/li4d")
    public ResponseModel<Void> insertLi4d(@RequestBody Li4dModel model){return this.backendServer.insertLi4d(model);}

    @PostMapping("/insert/choices")
    public ResponseModel<Void> insertChoices(@RequestBody List<ChoiceModel> modelList){return this.backendServer.insertChoices(modelList);}


}
