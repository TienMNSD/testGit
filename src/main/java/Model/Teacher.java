package Model;

import View.TeacherView;

public class Teacher {
    private String id;
    private String name;
    private String birth;
    private String faculty;
    private String phone;
    private String email;
    private String subject;
    public Teacher(){}
    public Teacher(String id, String name, String birth, String faculty, String phone, String email, String subject){
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.faculty = faculty;
        this.phone = phone;
        this.email = email;
        this.subject = subject;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String lecturers) {
        this.subject = lecturers;
    }
}
