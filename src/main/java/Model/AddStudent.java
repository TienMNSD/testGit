package Model;

public class AddStudent {
    private int stt;
    private String id_class;
    private String subject;
    private String id_student;
    private String name_student;
    private String class_student;
    public AddStudent(){}
    public AddStudent(int stt, String id_class, String subject, String id_student, String name_student, String class_student){
        this.stt = stt;
        this.id_class = id_class;
        this.subject = subject;
        this.id_student = id_student;
        this.name_student = name_student;
        this.class_student = class_student;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getClass_student() {
        return class_student;
    }

    public void setClass_student(String class_student) {
        this.class_student = class_student;
    }
}
