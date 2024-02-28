package models;


import javax.json.bind.annotation.JsonbProperty;

public class Students {
    @JsonbProperty("studentCount")
    private int studentsCount;
    public Students(int studentsCount){
        this.studentsCount = studentsCount;
    }
    public Students(){}
    public int getStudentsCount() {
        return studentsCount;
    }
    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String toString() {
        return "Students: "+studentsCount;
    }
}
