package La.OpenTecLab.Training.backend.service;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import La.OpenTecLab.Training.backend.entity.HistoryEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.*;
import La.OpenTecLab.Training.backend.repository.CategoryRepository;
import La.OpenTecLab.Training.backend.repository.ChoiceRepository;
import La.OpenTecLab.Training.backend.repository.HistoryRepository;
import La.OpenTecLab.Training.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UseService {
    private final ChoiceRepository choiceRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final HistoryRepository historyRepository;

    public UseService(ChoiceRepository choiceRepository, UserRepository userRepository, CategoryRepository categoryRepository, HistoryRepository historyRepository) {
        this.choiceRepository = choiceRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.historyRepository = historyRepository;
    }

    public ResponseModel<Void> userLogin(UserModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("created");
        res.setStatus(201);
        try {
            Optional<UserEntity> optional = this.userRepository.findByEmail(model.getEmail());
            if (optional.isPresent()){
                UserEntity entity = optional.get();
                if(entity.getCategoryU()==null){
                    res.setStatus(200);
                    res.setDescription(null);
                }else {
                    res.setStatus(200);
                    res.setDescription(entity.getCategoryU().getCategoryId().toString());
                }
            }else {
                UserEntity entity = new UserEntity();
                entity.setFirstName(model.getFirstName());
                entity.setLastName(model.getLastName());
                entity.setEmail(model.getEmail());
                entity.setImage(model.getImage());
                entity.setBirthdays(model.getBirthdays());
                entity.setGender(model.getGender());
                this.userRepository.save(entity);
            }
        }catch (Exception ex){
        ex.printStackTrace();
        res.setStatus(500);
        res.setDescription(ex.getMessage());
    }
        return res;
    }

    public List<HistoryModel> getAllUserHistory(String email) {
        Optional<UserEntity> optional = userRepository.findByEmail(email);
        if (optional.isPresent()) {
            List<HistoryEntity> list = optional.get().getHistoryList();
            List<HistoryModel> models = new ArrayList<>();
            int i =1;
            for (HistoryEntity e : list) {
                HistoryModel m = new HistoryModel();
                //Convert String to json array
                JSONArray jsonLike = new JSONArray(e.getLikes());
                JSONArray jsonDislike = new JSONArray(e.getDislikes());
                //create and set arraylist
                ArrayList<Integer> likeList = new ArrayList<>();
                ArrayList<Integer> dislikeList = new ArrayList<>();
                for (int j = 0; j < jsonLike.length(); j++) {likeList.add(jsonLike.getInt(j));}
                for (int j = 0; j < jsonDislike.length(); j++) {dislikeList.add(jsonDislike.getInt(j));}

                m.setId(i);
                m.setLikes(likeList);
                m.setDislikes(dislikeList);
                m.setDates(e.getDates());
                m.setUserId(e.getUserH().getUserId());
                m.setCategoryId(e.getCategoryH().getCategoryId());
                models.add(m);
                i++;
            }
            return models;
        }else {return null;}
    }

    public List<ChoiceModel> findAllChoices() {
        List<ChoiceEntity> list = this.choiceRepository.findAll();
        List<ChoiceModel> models = new ArrayList<>();
        for (ChoiceEntity e : list){
            ChoiceModel m = new ChoiceModel();
            m.setId(e.getChoiceId());
            m.setNameTh(e.getNameTh());
            m.setNameEn(e.getNameEn());
            models.add(m);
        }
        return models;
    }

    public ResponseModel<Void> getResult(DataModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setStatus(200);
        res.setDescription("success");
        try {
            int r1=0;
            int r2=0;
            int r3=0;
            int r4=0;
            Optional<UserEntity> optional = this.userRepository.findByEmail(model.getUserEmail());
            HistoryEntity history = new HistoryEntity();
            ObjectMapper mapper = new ObjectMapper();
            if (optional.isPresent()){
                history.setDates(LocalDateTime.now());
                history.setUserH(optional.get());
                UserEntity entity = optional.get();
                int role =0;
                for (ArrayList<Integer> a: model.getUserData() ){
                    if(role==0){
                        history.setLikes(mapper.writeValueAsString(a));
                        for (Integer b:a){
                            if(b<=21){r1=r1+1;}
                            if(b>=22&b<=42){r2=r2+1;}
                            if(b>=43&b<=63){r3=r3+1;}
                            if(b>=64){r4=r4+1;}
                        }
                    }else {
                        history.setDislikes(mapper.writeValueAsString(a));
                        for (Integer b:a){
                            if(b<=21){r1=r1-1;}
                            if(b>=22&b<=42){r2=r2-1;}
                            if(b>=43&b<=63){r3=r3-1;}
                            if(b>=64){r4=r4-1;}
                        }
                    }
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
                        Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(1);
                        if (optionalCategory.isPresent()) {
                            CategoryEntity category = optionalCategory.get();
                            entity.setCategoryU(category);
                            this.userRepository.save(entity);
                            history.setCategoryH(category);
                            this.historyRepository.save(history);
                        } else {
                            res.setStatus(500);
                            res.setDescription("Database error");
                            log.info("get result error : can't find category : 1");
                        }
                    }
                    case "Rat" -> {
                        Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(2);
                        if (optionalCategory.isPresent()) {
                            CategoryEntity category = optionalCategory.get();
                            entity.setCategoryU(category);
                            this.userRepository.save(entity);
                            history.setCategoryH(category);
                            this.historyRepository.save(history);
                        } else {
                            res.setStatus(500);
                            res.setDescription("Database error");
                            log.info("get result error : can't find category : 2");
                        }
                    }
                    case "Bear" -> {
                        Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(3);
                        if (optionalCategory.isPresent()) {
                            CategoryEntity category = optionalCategory.get();
                            entity.setCategoryU(category);
                            this.userRepository.save(entity);
                            history.setCategoryH(category);
                            this.historyRepository.save(history);
                        } else {
                            res.setStatus(500);
                            res.setDescription("Database error");
                            log.info("get result error : can't find category : 3");
                        }
                    }
                    case "Falcon" -> {
                        Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(4);
                        if (optionalCategory.isPresent()) {
                            CategoryEntity category = optionalCategory.get();
                            entity.setCategoryU(category);
                            this.userRepository.save(entity);
                            history.setCategoryH(category);
                            this.historyRepository.save(history);
                        } else {
                            res.setStatus(500);
                            res.setDescription("Database error");
                            log.info("get result error : can't find category : 4");
                        }
                    }
                }



            }else {
                res.setStatus(403);
                res.setDescription("Email is wrong");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }

    public List<OverviewModel> getOverview() {
        float bull=0,rat=0,bear=0,falcon=0;
        DecimalFormat df = new DecimalFormat("0");
        List<UserEntity> entityList = this.userRepository.findAll();
        for (UserEntity e:entityList){
            if(e.getCategoryU().getCategoryId().equals(1)){bull++;}
            if(e.getCategoryU().getCategoryId().equals(2)){rat++;}
            if(e.getCategoryU().getCategoryId().equals(3)){bear++;}
            if(e.getCategoryU().getCategoryId().equals(4)){falcon++;}
        }

        List<OverviewModel> models = new ArrayList<>();
        OverviewModel bullModel = new OverviewModel();
        OverviewModel ratModel = new OverviewModel();
        OverviewModel bearModel = new OverviewModel();
        OverviewModel falconModel = new OverviewModel();

        bullModel.setName("Bull");
        bullModel.setPercent(df.format((bull * 100) / entityList.size())+"%");
        bullModel.setPeople(entityList.size());
        models.add(bullModel);
        ratModel.setName("Rat");
        ratModel.setPercent(df.format((rat * 100) / entityList.size())+"%");
        ratModel.setPeople(entityList.size());
        models.add(ratModel);
        bearModel.setName("Bear");
        bearModel.setPercent(df.format((bear * 100) / entityList.size())+"%");
        bearModel.setPeople(entityList.size());
        models.add(bearModel);
        falconModel.setName("Falcon");
        falconModel.setPercent(df.format((falcon * 100) / entityList.size())+"%");
        falconModel.setPeople(entityList.size());
        models.add(falconModel);

        return models;
    }

    public CategoryModel getCategory(Integer id) {
        Optional<CategoryEntity> optional = this.categoryRepository.findById(id);
        if(optional.isPresent()){
            CategoryEntity e = optional.get();
            CategoryModel m = new CategoryModel();
            m.setId(e.getCategoryId());
            m.setNameTh(e.getNameTh());
            m.setNameEn(e.getNameEn());
            m.setBehaviorTh(e.getBehaviorTh());
            m.setBehaviorEn(e.getBehaviorEn());
            return m;
        }else return null;
    }
}
