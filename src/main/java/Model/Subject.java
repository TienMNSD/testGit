package Model;

public class Subject {
    private String id;
    private String name;
    private String credit;
    private String faculty;
    private String lecturers;
    public Subject(){
    }
    public Subject(String id, String name, String credit, String faculty, String lecturers){
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.faculty = faculty;
        this.lecturers = lecturers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLecturers() {
        return lecturers;
    }

    public void setLecturers(String lecturers) {
        this.lecturers = lecturers;
    }
}
