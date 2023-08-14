package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import La.OpenTecLab.Training.backend.entity.HistoryEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.CategoryModel;
import La.OpenTecLab.Training.backend.model.ChoiceModel;
import La.OpenTecLab.Training.backend.model.ResponseModel;
import La.OpenTecLab.Training.backend.model.UserModel;
import La.OpenTecLab.Training.backend.service.AdminService;
import La.OpenTecLab.Training.backend.service.BackendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/api")
public class AdminRestController {

    private final AdminService adminServer;

    public AdminRestController(BackendService backendService, BackendService backendServer, AdminService adminServer) {
        this.adminServer = adminServer;
    }
    @PostMapping("/find/all/users")
    public List<UserEntity> findAllUsers(){return this.adminServer.findAllUsers();}

    @PostMapping("/find/all/category")
    public List<CategoryEntity> findAllCategory(){return this.adminServer.findAllCategory();}

    @PostMapping("/find/all/choice")
    public List<ChoiceEntity> adminFindAllChoice(){return this.adminServer.adminFindAllChoice();}

    @PostMapping("/find/all/history")
    public List<HistoryEntity> findAllHistory(){return this.adminServer.findAllHistory();}


    @PostMapping("/update/category")
    public ResponseModel<Void> updateCategory(@RequestBody CategoryModel model){return this.adminServer.updateCategory(model);}

    @PostMapping("/update/choices")
    public ResponseModel<Void> updateChoices(@RequestBody ChoiceModel model){return this.adminServer.updateChoices(model);}



}
