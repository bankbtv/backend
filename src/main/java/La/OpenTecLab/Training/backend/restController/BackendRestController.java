package La.OpenTecLab.Training.backend.restController;

import La.OpenTecLab.Training.backend.entity.ImageEntity;
import La.OpenTecLab.Training.backend.model.ImageModel;
import La.OpenTecLab.Training.backend.model.ResponseModel;
import La.OpenTecLab.Training.backend.model.UserModel;
import La.OpenTecLab.Training.backend.service.BackendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BackendRestController {
    private final BackendService backendServer;

    public BackendRestController(BackendService backendServer) {
        this.backendServer = backendServer;
    }
    @GetMapping("/find/all/image")
    public List<ImageModel> findAllImage(){return this.backendServer.findAllImage();}
    @GetMapping("/find/image")
    public ResponseEntity<?> findImage(@RequestParam String name)throws IOException {
        byte[] image = this.backendServer.findImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg")).body(image);}
    @GetMapping("/save/all")
    public ImageEntity insert(){
        return this.backendServer.insertImage();
    }

    @PostMapping("/insert/user")
    public ResponseModel insertUser(@RequestBody UserModel model){return this.backendServer.insertUser(model);}

}
