package La.OpenTecLab.Training.backend.service;

import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.*;
import La.OpenTecLab.Training.backend.repository.ChoiceRepository;
import La.OpenTecLab.Training.backend.repository.Li4dRepository;
import La.OpenTecLab.Training.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BackendService {
    private final ChoiceRepository choiceRepository;
    private final UserRepository userRepository;
    private final Li4dRepository li4dRepository;

    public BackendService(ChoiceRepository choiceRepository, UserRepository userRepository, Li4dRepository li4dRepository) {
        this.choiceRepository = choiceRepository;
        this.userRepository = userRepository;
        this.li4dRepository = li4dRepository;
    }

    public ResponseModel<Void> insertLi4d(Li4dModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("created");
        res.setStatus(201);
        try {
            Optional<Li4dEntity> optional = this.li4dRepository.findByLi4dName(model.getLi4dName());
            if (optional.isEmpty()){
            Li4dEntity e = new Li4dEntity();
            e.setLi4dName(model.getLi4dName());
            this.li4dRepository.save(e);}else {
                res.setDescription("haded it before");
                res.setStatus(403);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }


        return res;
    }

    public ResponseModel<Void> insertChoices(List<ChoiceModel> modelList) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("created");
        res.setStatus(201);
        try {
            List<ChoiceEntity> choices = new ArrayList<>();
            for (ChoiceModel m:modelList){
                try {
                    Optional<ChoiceEntity> optional = this.choiceRepository.findByChoiceNameEn(m.getChoiceNameEn());
                    Optional<Li4dEntity> optionalLi4d = this.li4dRepository.findById(m.getLi4dId());
                    if (optional.isEmpty()&optionalLi4d.isPresent()){
                        Li4dEntity li4d = optionalLi4d.get();
                        ChoiceEntity e = new ChoiceEntity();
                        e.setChoiceNameTh(m.getChoiceNameTh());
                        e.setChoiceNameEn(m.getChoiceNameEn());
                        e.setLi4dc(li4d);
                        choices.add(e);
                    }else {
                        log.info("haded "+m.getChoiceNameEn()+" before");}
                }catch (Exception ex){
                    log.info(ex.getMessage());
                }
            }
            this.choiceRepository.saveAll(choices);
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }

    public ResponseModel<Void> insertUser(UserModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("created");
        res.setStatus(201);
        try {
            Optional<UserEntity> optional = this.userRepository.findByUserEmail(model.getUserEmail());
            if (optional.isPresent()){
                UserEntity entity = optional.get();
                if(entity.getLi4du()==null){
                    res.setStatus(200);
                    res.setDescription(null);
                }else {
                    res.setStatus(200);
                    res.setDescription(entity.getLi4du().getLi4dName());
                }
            }else {
                UserEntity entity = new UserEntity();
                entity.setUserEmail(model.getUserEmail());
                this.userRepository.save(entity);
            }
        }catch (Exception ex){
        ex.printStackTrace();
        res.setStatus(500);
        res.setDescription(ex.getMessage());
    }
        return res;
    }

    public List<ChoiceModel> findAllChoices() {
        List<ChoiceEntity> list = this.choiceRepository.findAll();
        List<ChoiceModel> models = new ArrayList<>();
        for (ChoiceEntity e : list){
            ChoiceModel m = new ChoiceModel();
            m.setChoiceId(e.getChoiceId());
            m.setChoiceNameTh(e.getChoiceNameTh());
            m.setChoiceNameEn(e.getChoiceNameEn());
            models.add(m);
        }
        return models;
    }

    public ResponseModel<Void> userData(DataModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setStatus(200);
        res.setDescription("success");
        try {
            int r1=0;
            int r2=0;
            int r3=0;
            int r4=0;
            Optional<UserEntity> optional = this.userRepository.findByUserEmail(model.getUserEmail());
            if (optional.isPresent()){
                UserEntity entity = optional.get();
                int role =0;
                for (ArrayList<Integer> a: model.getUserData() ){
                    if(role==0){
                        for (Integer b:a){
                            if(b<=21){r1=r1+1;}
                            if(b>=22&b<=42){r2=r2+1;}
                            if(b>=43&b<=63){r3=r3+1;}
                            if(b>=64){r4=r4+1;}
                        }
                    }else {for (Integer b:a){
                        if(b<=21){r1=r1-1;}
                        if(b>=22&b<=42){r2=r2-1;}
                        if(b>=43&b<=63){r3=r3-1;}
                        if(b>=64){r4=r4-1;}
                    }}
                    role=role+1;
                }
                int max = 0;
                for (int i=0;i<4;i++){
                    if(i==0&max<r1){
                        max = r1;
                        res.setDescription("Bull");
                    }
                    if(i==1&max<r2){
                        max = r2;
                        res.setDescription("Rat");
                    }
                    if(i==2&max<r3){
                        max = r3;
                        res.setDescription("Bear");
                    }
                    if(i==3&max<r4){
                        max = r4;
                        res.setDescription("Falcon");
                    }
                }
                switch (res.getDescription()) {
                    case "Bull" -> {
                        Optional<Li4dEntity> optionalLi4d = this.li4dRepository.findById(1);
                        if (optionalLi4d.isPresent()) {
                            Li4dEntity li4d = optionalLi4d.get();
                            entity.setLi4du(li4d);
                            this.userRepository.save(entity);
                        } else {
                            res.setDescription("can't find li4d : 1");
                        }
                    }
                    case "Rat" -> {
                        Optional<Li4dEntity> optionalLi4d = this.li4dRepository.findById(2);
                        if (optionalLi4d.isPresent()) {
                            Li4dEntity li4d = optionalLi4d.get();
                            entity.setLi4du(li4d);
                            this.userRepository.save(entity);
                        } else {
                            res.setDescription("can't find li4d : 2");
                        }
                    }
                    case "Bear" -> {
                        Optional<Li4dEntity> optionalLi4d = this.li4dRepository.findById(3);
                        if (optionalLi4d.isPresent()) {
                            Li4dEntity li4d = optionalLi4d.get();
                            entity.setLi4du(li4d);
                            this.userRepository.save(entity);
                        } else {
                            res.setDescription("can't find li4d : 3");
                        }
                    }
                    case "Falcon" -> {
                        Optional<Li4dEntity> optionalLi4d = this.li4dRepository.findById(4);
                        if (optionalLi4d.isPresent()) {
                            Li4dEntity li4d = optionalLi4d.get();
                            entity.setLi4du(li4d);
                            this.userRepository.save(entity);
                        } else {
                            res.setDescription("can't find li4d : 4");
                        }
                    }
                }

            }else {
                res.setStatus(403);
                res.setDescription("The user's email is invalid.");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }

    public ResponseModel<Void> resetResponse(String userEmail) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setStatus(200);
        res.setDescription("success");
        try {
            Optional<UserEntity> optional = this.userRepository.findByUserEmail(userEmail);
            if(optional.isPresent()){
                UserEntity entity = optional.get();
                entity.setLi4du(null);
                this.userRepository.save(entity);
            }else {
                res.setStatus(403);
                res.setDescription("user not found");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }


}
