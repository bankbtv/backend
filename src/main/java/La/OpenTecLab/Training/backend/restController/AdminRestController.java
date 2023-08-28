package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import La.OpenTecLab.Training.backend.model.*;
import La.OpenTecLab.Training.backend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/api")
public class AdminRestController {

    private final AdminService adminServer;

    public AdminRestController(AdminService adminServer) {
        this.adminServer = adminServer;
    }
    @PostMapping("/find/all/users")
    public List<UserModel> findAllUsers(){return this.adminServer.findAllUsers();}

    @PostMapping("/find/all/category")
    public List<CategoryEntity> findAllCategory(){return this.adminServer.findAllCategory();}

    @PostMapping("/find/all/choice")
    public List<ChoiceModel> adminFindAllChoice(){return this.adminServer.adminFindAllChoice();}

    @PostMapping("/find/all/history")
    public List<HistoryModel> findAllHistory(){return this.adminServer.findAllHistory();}

    @PostMapping("/update/category")
    public ResponseModel<Void> updateCategory(@RequestBody CategoryModel model){return this.adminServer.updateCategory(model);}

    @PostMapping("/update/choice")
    public ResponseModel<Void> updateChoices(@RequestBody ChoiceModel model){return this.adminServer.updateChoices(model);}

    @GetMapping("/test")
    public ResponseModel<Void> testNative(@RequestParam Integer id){return this.adminServer.testNative(id);}



}
