package La.OpenTecLab.Training.backend.model;

import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import lombok.Data;

@Data
public class ChoiceModel {
    private Integer choiceId;
    private String choiceNameTh;
    private String choiceNameEn;
    private Integer li4dId;
}
