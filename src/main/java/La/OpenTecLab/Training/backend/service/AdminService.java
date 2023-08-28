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
import La.OpenTecLab.Training.backend.test.TestNativeEntity;
import La.OpenTecLab.Training.backend.test.TestNativeRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    private final CategoryRepository categoryRepository;
    private final ChoiceRepository choiceRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final TestNativeRepository testNAtiveRepository;

    public AdminService(CategoryRepository categoryRepository, ChoiceRepository choiceRepository, UserRepository userRepository, HistoryRepository historyRepository, TestNativeRepository testNAtiveRepository) {
        this.categoryRepository = categoryRepository;
        this.choiceRepository = choiceRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.testNAtiveRepository = testNAtiveRepository;
    }

    public ResponseModel<Void> updateCategory(CategoryModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("updated");
        res.setStatus(201);
        try {
            Optional<CategoryEntity> optional = this.categoryRepository.findById(model.getId());
            if (optional.isPresent()){
                CategoryEntity e = new CategoryEntity();
                if(model.getNameTh() != null){e.setNameTh(model.getNameTh());}
                if(model.getNameEn() != null){e.setNameEn(model.getNameEn());}
                if(model.getBehaviorTh() != null){e.setBehaviorTh(model.getBehaviorTh());}
                if(model.getBehaviorEn() != null){e.setBehaviorEn(model.getBehaviorEn());}
                this.categoryRepository.save(e);}else {
                res.setDescription("The Category is wrong");
                res.setStatus(403);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }

    public ResponseModel<Void> updateChoices(ChoiceModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("updated");
        res.setStatus(201);
        try {
            log.info("do");
            Optional<ChoiceEntity> optionalChoice = this.choiceRepository.findById(model.getId());
            Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(model.getCategoryId());
            if(optionalChoice.isPresent()){
                if(optionalCategory.isPresent()){
                    ChoiceEntity e = optionalChoice.get();
                    if(model.getNameEn() != null){e.setNameEn(model.getNameEn());}
                    if(model.getNameTh() != null){e.setNameTh(model.getNameTh());}
                    e.setCategoryC(optionalCategory.get());
                    this.choiceRepository.save(e);
                }else {
                    res.setDescription("The Category id is wrong");
                    res.setStatus(403);}
            }else {
                res.setDescription("The Choice is wrong");
                res.setStatus(403);}
        }catch (Exception ex){
            ex.printStackTrace();
            res.setStatus(500);
            res.setDescription(ex.getMessage());
        }
        return res;
    }

    public List<UserModel> findAllUsers(){
        List<UserEntity> list = this.userRepository.findAll();
        List<UserModel> models = new ArrayList<>();
        for (UserEntity e:list){
            UserModel m = new UserModel();
            m.setId(e.getUserId());
            m.setFirstName(e.getFirstName());
            m.setLastName(e.getLastName());
            m.setEmail(e.getEmail());
            m.setImage(e.getImage());
            m.setGender(e.getGender());
            m.setBirthdays(e.getBirthdays());
            if(e.getCategoryU()!=null){
            m.setCategoryId(e.getCategoryU().getCategoryId());}
            models.add(m);
        }
        return models;
    }

    public List<CategoryEntity> findAllCategory() {
        return this.categoryRepository.findAll();
    }

    public List<ChoiceModel> adminFindAllChoice(){
        List<ChoiceEntity> list = this.choiceRepository.findAll();
        List<ChoiceModel> models = new ArrayList<>();
        for (ChoiceEntity e:list){
            ChoiceModel m = new ChoiceModel();
            m.setId(e.getChoiceId());
            m.setNameEn(e.getNameEn());
            m.setNameTh(e.getNameTh());
            m.setCategoryId(e.getCategoryC().getCategoryId());
            models.add(m);
        }
        return models;
    }

    public List<HistoryModel> findAllHistory() {
        List<HistoryEntity> list = this.historyRepository.findAll();
        List<HistoryModel> models = new ArrayList<>();
        for (HistoryEntity e:list){
            HistoryModel m = new HistoryModel();
            //Convert String to json array
            JSONArray jsonLike = new JSONArray(e.getLikes());
            JSONArray jsonDislike = new JSONArray(e.getDislikes());
            //create and set arraylist
            ArrayList<Integer> likeList = new ArrayList<>();
            ArrayList<Integer> dislikeList = new ArrayList<>();
            for (int j = 0; j < jsonLike.length(); j++) {likeList.add(jsonLike.getInt(j));}
            for (int j = 0; j < jsonDislike.length(); j++) {dislikeList.add(jsonDislike.getInt(j));}

            m.setId(e.getHistoryId());
            m.setLikes(likeList);
            m.setDislikes(dislikeList);
            m.setDates(e.getDates());
            m.setUserId(e.getUserH().getUserId());
            m.setCategoryId(e.getCategoryH().getCategoryId());
            models.add(m);
        }
        return models;
    }

    public ResponseModel<Void> testNative(Integer id) {
        ResponseModel<Void> resp = new ResponseModel<>();
        resp.setStatus(200);
        TestNativeEntity userName = this.testNAtiveRepository.findUserNameById(id);
        resp.setDescription(userName.getFirstName()+";"+userName.getLastName());
        return resp;
    }
}
