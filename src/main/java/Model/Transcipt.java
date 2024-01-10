package Model;

public class Transcipt {
    private int stt;
    private String semester;

    private String id_subject;
    private String subject;
    private String credits;
    private double mid_score;
    private double end_score;
    private String anpha_score;
    public Transcipt(){}
    public Transcipt(int stt, String semester,String id_subject, String subject, String credits,
                      double mid_score, double end_score, String anpha_score){
        this.stt = stt;
        this.semester = semester;
        this.id_subject = id_subject;
        this.subject = subject;
        this.credits = credits;
        this.mid_score = mid_score;
        this.end_score = end_score;
        this.anpha_score = anpha_score;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
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

    public String getAnpha_score() {
        return anpha_score;
    }

    public void setAnpha_score(String anpha_score) {
        this.anpha_score = anpha_score;
    }
}
