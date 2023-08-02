package La.OpenTecLab.Training.backend.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer userId;
    private String userName;
    private String userImage;
    private String userGmail;
    private String isDelete;
}
