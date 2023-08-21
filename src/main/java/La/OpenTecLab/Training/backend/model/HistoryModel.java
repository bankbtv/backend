package La.OpenTecLab.Training.backend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class HistoryModel {
    private Integer id;
    private ArrayList<Integer> likes;
    private ArrayList<Integer> dislikes;
    private LocalDateTime dates;
    private Integer userId;
    private Integer categoryId;
}
