package La.OpenTecLab.Training.backend.model;

import La.OpenTecLab.Training.backend.entity.ChoiceEntity;
import La.OpenTecLab.Training.backend.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class Li4dModel {

    private Integer li4dId;
    private String li4dName;
    private String li4dDescriptionTh;
    private String li4dDescriptionEn;
}
