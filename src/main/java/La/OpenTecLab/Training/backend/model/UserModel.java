package La.OpenTecLab.Training.backend.model;

import La.OpenTecLab.Training.backend.entity.Li4dEntity;
import lombok.Data;

@Data
public class UserModel {
    private Integer userId;
    private String userEmail;
    private Integer li4dId;
}
