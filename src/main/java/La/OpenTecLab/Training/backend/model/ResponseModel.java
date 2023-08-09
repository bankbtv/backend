package La.OpenTecLab.Training.backend.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private Integer status;
    private String description;
    private T data;
}
