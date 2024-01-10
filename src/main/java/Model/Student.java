package Model;

public class Student {
    private int id;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String classStudent;
    private String year;
    private String major;
    private String address;
    private String email;

    public Student() {
    }

    public Student(int id, String name, String dateOfBirth, String gender, String classStudent,
                   String year, String major, String address, String email) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.classStudent = classStudent;
        this.year = year;
        this.major = major;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String classStudent) {
        this.classStudent = classStudent;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
