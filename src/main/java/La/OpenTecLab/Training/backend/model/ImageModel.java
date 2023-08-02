package La.OpenTecLab.Training.backend.model;

import lombok.Data;

@Data
public class ImageModel {
    private Integer imageId;
    private String imageName;
    private String imageGroup;
    private String imagePath;
}
