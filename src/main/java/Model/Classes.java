package Model;

public class Classes {
    private String id;
    private String subject;
    private String idsubject;
    private String faculty;
    private String semester;
    private String lecturers;
    private String numOfStudent;
    private String room;

    public Classes() {
    }

    public Classes(String id, String faculty, String subject, String idsubject, String lecturers, String semester,
                   String room, String numOfStudent) {
        this.id = id;
        this.subject = subject;
        this.faculty = faculty;
        this.semester = semester;
        this.lecturers = lecturers;
        this.numOfStudent = numOfStudent;
        this.room = room;
        this.idsubject = idsubject;
    }

    public String getIdsubject() {
        return idsubject;
    }

    public void setIdsubject(String idsubject) {
        this.idsubject = idsubject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLecturers() {
        return lecturers;
    }

    public void setLecturers(String lecturers) {
        this.lecturers = lecturers;
    }

    public String getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(String numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}