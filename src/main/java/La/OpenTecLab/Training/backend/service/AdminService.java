package La.OpenTecLab.Training.backend.service;

import La.OpenTecLab.Training.backend.entity.CategoryEntity;
import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import La.OpenTecLab.Training.backend.model.CategoryModel;
import La.OpenTecLab.Training.backend.model.ChoiceModel;
import La.OpenTecLab.Training.backend.model.ResponseModel;
import La.OpenTecLab.Training.backend.model.UserModel;
import La.OpenTecLab.Training.backend.repository.CategoryRepository;
import La.OpenTecLab.Training.backend.repository.ChoiceRepository;
import La.OpenTecLab.Training.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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

    public AdminService(CategoryRepository categoryRepository, ChoiceRepository choiceRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.choiceRepository = choiceRepository;
        this.userRepository = userRepository;
    }

    public ResponseModel<Void> updateCategory(CategoryModel model) {
        ResponseModel<Void> res = new ResponseModel<>();
        res.setDescription("updated");
        res.setStatus(201);
        try {
            Optional<CategoryEntity> optional = this.categoryRepository.findById(model.getId());
            if (optional.isEmpty()){
                CategoryEntity e = new CategoryEntity();
                e.setName(model.getName());
                e.setDescriptionEn(model.getDescriptionEn());
                e.setDescriptionTh(model.getDescriptionTh());
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
            Optional<ChoiceEntity> optionalChoice = this.choiceRepository.findById(model.getId());
            Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(model.getCategoryId());
            if(optionalChoice.isPresent()){
                if(optionalCategory.isPresent()){
                    ChoiceEntity e = optionalChoice.get();
                    e.setNameEn(model.getNameEn());
                    e.setNameTh(model.getNameTh());
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

    public List<UserEntity> findAllUsers(){
        return this.userRepository.findAll();
    }

    public List<CategoryEntity> findAllCategory() {
        return this.categoryRepository.findAll();
    }

    public List<ChoiceEntity> adminFindAllChoice(){
        return this.choiceRepository.findAll();
    }

}
