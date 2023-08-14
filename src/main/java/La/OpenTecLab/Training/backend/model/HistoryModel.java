package La.OpenTecLab.Training.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class HistoryModel {
    private Integer id;
    private JsonIgnore like;
    private JsonIgnore dislike;
    private Date date;
    private Integer userId;
    private Integer categoryId;
}
