package La.OpenTecLab.Training.backend.service;

import La.OpenTecLab.Training.backend.entity.ImageEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.ImageModel;
import La.OpenTecLab.Training.backend.model.ResponseModel;
import La.OpenTecLab.Training.backend.model.UserModel;
import La.OpenTecLab.Training.backend.repository.ImageRepository;
import La.OpenTecLab.Training.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BackendService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public BackendService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public List<ImageModel> findAllImage() {
        List<ImageEntity> list = this.imageRepository.findAll();
        List<ImageModel> models = new ArrayList<>();
        for (ImageEntity e:list){
            ImageModel m =new ImageModel();
            m.setImageId(e.getImageId());
            m.setImageName(e.getImageName());
            m.setImageGroup(e.getImageGroup());
            m.setImagePath(":8000/api/find/image?name="+e.getImageName());
            models.add(m);
        }
        return models;
    }


    public byte[] findImage(String name) throws IOException {
        Optional<ImageEntity> optional = imageRepository.findByImageName(name);
        String path = optional.get().getImagePath();
        return Files.readAllBytes(new File(path).toPath());
    }


    public ResponseModel insertUser(UserModel model){
        boolean unequal = true;
        ResponseModel res = new ResponseModel();
        res.setStatus(200);
        res.setDescription("success");
        List<UserEntity> list = this.userRepository.findAll();
        for (UserEntity e:list){
            if (model.getUserGmail().equals(e.getUserGmail())) {
                unequal = false;
                break;
            }
        }
        if (unequal) {
            UserEntity e = new UserEntity();
            e.setUserName(model.getUserName());
            e.setUserGmail(model.getUserGmail());
            e.setUserImage(model.getUserImage());
            e.setIsDelete("N");
            this.userRepository.save(e);
        }else {
            res.setStatus(400);
            res.setDescription("had this email");
        }
        return res;
    }

    public ImageEntity insertImage() {
        String path = "C:\\Users\\ASUS TUF GAMING\\Desktop\\fullStackWebDev\\backend\\backend\\src\\main\\resources\\images\\";
        List<ImageEntity> list = new ArrayList<>();
        for (int i=1;i<85;i++){
            ImageEntity entity = new ImageEntity();
            entity.setImageName("image"+i);
            if (i<=21){entity.setImageGroup("Bull");}
            if (i>21&i<=42){entity.setImageGroup("Rat");}
            if (i>42&i<=63){entity.setImageGroup("Bear");}
            if (i>63){entity.setImageGroup("Falcon");}
            entity.setImagePath(path+i+".jpg");
            list.add(entity);
        }
        this.imageRepository.saveAll(list);
        return null;
    }
}
