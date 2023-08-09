package La.OpenTecLab.Training.backend.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DataModel {
    private String userEmail;
    private ArrayList<ArrayList<Integer>> userData;
}
