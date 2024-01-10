package Model;

public class ScoreClass {
    private int stt;
    private String id_class;
    private String subject;
    private String id_student;
    private String name_student;

    private double mid_score;
    private double end_score;
    private double total_score;
    public ScoreClass(){}
    public ScoreClass(int stt, String id_class, String subject, String id_student, String name_student,
                       double mid_score, double end_score, double total_score){
        this.stt = stt;
        this.id_class = id_class;
        this.subject = subject;
        this.id_student = id_student;
        this.name_student = name_student;
        this.mid_score = mid_score;
        this.end_score = end_score;
        this.total_score = total_score;
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

    public double getMid_score() {
        return mid_score;
    }

    public void setMid_score(double mid_score) {
        this.mid_score = mid_score;
    }

    public double getEnd_score() {
        return end_score;
    }

    public void setEnd_score(double end_score) {
        this.end_score = end_score;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }
}
