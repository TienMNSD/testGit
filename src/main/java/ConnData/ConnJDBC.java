package ConnData;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.dbcp2.BasicDataSource;

public class ConnJDBC {
//    private static BasicDataSource dataSource;
//
//    private static BasicDataSource getDataSource()
//    {
//
//        if (dataSource == null)
//        {
//            BasicDataSource ds = new BasicDataSource();
//            ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
//            ds.setUsername("postgres");
//            ds.setPassword("lhyieltgjtgy1");
//
//
//            ds.setMinIdle(1);
//            ds.setMaxIdle(2);
//            ds.setMaxOpenPreparedStatements(10);
//
//            dataSource = ds;
//        }
//        return dataSource;
//    }

    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "lhyieltgjtgy1";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
//            BasicDataSource dataSource = ConnJDBC.getDataSource();
//            connection = dataSource.getConnection();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static List<Student> findAll(){
        List<Student> studentList = new ArrayList<>();
        String query = "select*from student";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Student st = new Student(rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getString("date"),
                                        rs.getString("gender"),
                                        rs.getString("class"),
                                        rs.getString("year"),
                                        rs.getString("major"),
                                        rs.getString("address"),
                                        rs.getString("email"));
                studentList.add(st);
            }
        } catch (Exception e) {

        }
        return studentList;
    }

    public static List<Classes> findAllClass(){
        List<Classes> listClasses = new ArrayList<>();
        String query = "select*from classes";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Classes cl = new Classes(rs.getString("id"),
                        rs.getString("faculty"),
                        rs.getString("subject"),
                        rs.getString("id_subject"),
                        rs.getString("lecturers"),
                        rs.getString("semester"),
                        rs.getString("room"),
                        rs.getString("num_of_student"));
                listClasses.add(cl);
            }
        } catch (Exception e) {

        }
        return listClasses;
    }

    public static List<AddStudent> findAllAddClass(Classes cl){
        String s1 ="class"+cl.getId();
        List<AddStudent> listAdd = new ArrayList<>();
        String query = "select*from "+s1;
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                AddStudent as = new AddStudent(
                        rs.getInt("stt"),
                        rs.getString("id_class"),
                        rs.getString("subject"),
                        rs.getString("id_student"),
                        rs.getString("name"),
                        rs.getString("class"));
                listAdd.add(as);
            }
        } catch (Exception e) {

        }
        return listAdd;
    }

    public static List<ScoreClass> findAllScoreClass(ScoreClass sl){
        String s1 ="class"+sl.getId_class();
        List<ScoreClass> listAdd = new ArrayList<>();
        String query = "select*from "+s1;
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                ScoreClass as = new ScoreClass(
                        rs.getInt("stt"),
                        rs.getString("id_class"),
                        rs.getString("subject"),
                        rs.getString("id_student"),
                        rs.getString("name"),
                        rs.getDouble("mid_score"),
                        rs.getDouble("end_score"),
                        rs.getDouble("totalscore"));
                listAdd.add(as);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listAdd;
    }

    public static List<Transcipt> findTranscript(Student st){
        String s1 ="transcript"+st.getId();
        List<Transcipt> listAdd = new ArrayList<>();
        String query = "select*from "+s1;
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Transcipt ts = new Transcipt(
                        rs.getInt("stt"),
                        rs.getString("semester"),
                        rs.getString("id_subject"),
                        rs.getString("subject"),
                        rs.getString("credits"),
                        rs.getDouble("midscore"),
                        rs.getDouble("endscore"),
                        rs.getString("anphascore"));
                listAdd.add(ts);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listAdd;
    }

    public static void insert(Student st){
        String query = "insert into student (id, name, date, gender, class, year, major, address, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, st.getId());
            pstmt.setString(2, st.getName());
            pstmt.setString(3, st.getDateOfBirth());
            pstmt.setString(4, st.getGender());
            pstmt.setString(5, st.getClassStudent());
            pstmt.setString(6, st.getYear());
            pstmt.setString(7, st.getMajor());
            pstmt.setString(8, st.getAddress());
            pstmt.setString(9, st.getEmail());
            pstmt.execute();
            connection.setAutoCommit(false);
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudentExcels(int batchSize, List<Student> list) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        List<Student> listStu = list;
        for (int i = 0; i< listStu.size(); i++) {
            String sql = "INSERT INTO student (id, name, date, gender, class, year, major, address, email) " +
                    "VALUES ('"+listStu.get(i).getId()+"', '"+listStu.get(i).getName()+"', '"+listStu.get(i).getDateOfBirth()+"', " +
                    "'"+listStu.get(i).getGender()+"', '"+listStu.get(i).getClassStudent()+"', '"+listStu.get(i).getYear()+"'," +
                    " '"+listStu.get(i).getMajor()+"', '"+listStu.get(i).getAddress()+"', '"+listStu.get(i).getEmail()+"');";
            statement.addBatch(sql);
            if (i % batchSize == 0) {
                statement.executeBatch();
            }
        }
        statement.executeBatch();
        connection.commit();
        connection.close();
    }

    public static void insertStudentClassExcels(int batchSize, List<AddStudent> list, Classes cl) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String s1 ="class"+cl.getId();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        List<AddStudent> listStu = list;
        for (int i = 0; i< listStu.size(); i++) {
            String sql = "INSERT INTO "+s1+" (id_class, subject, id_student, name, class) " +
                    "VALUES ('"+listStu.get(i).getId_class()+"', " +
                    "'"+listStu.get(i).getSubject()+"', '"+listStu.get(i).getId_student()+"'," +
                    " '"+listStu.get(i).getName_student()+"', '"+listStu.get(i).getClass_student()+"');";
            statement.addBatch(sql);
            if (i % batchSize == 0) {
                statement.executeBatch();
            }
        }
        statement.executeBatch();
        connection.commit();
        connection.close();
    }

    public static void insertAccountExcels(int batchSize, List<Account> list) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        List<Account> listAcc = list;
        for (int i = 0; i< listAcc.size(); i++) {
            String sql = "INSERT INTO account (rank, id_user, username, password) " +
                    "VALUES ('"+listAcc.get(i).getRank()+"', '"+listAcc.get(i).getId_user()+"'" +
                    ", '"+listAcc.get(i).getUsername()+"', '"+listAcc.get(i).getPassword()+"');";
            statement.addBatch(sql);
            if (i % batchSize == 0) {
                statement.executeBatch();
            }
        }
        statement.executeBatch();
        connection.commit();
        connection.close();
    }

    public static void insertClass(Classes cl){
        String query = "insert into classes (id, faculty, subject, id_subject, lecturers, semester, room, num_of_student) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, cl.getId());
            pstmt.setString(2, cl.getFaculty());
            pstmt.setString(3, cl.getSubject());
            pstmt.setString(4, cl.getIdsubject());
            pstmt.setString(5, cl.getLecturers());
            pstmt.setString(6, cl.getSemester());
            pstmt.setString(7, cl.getRoom());
            pstmt.setString(8, cl.getNumOfStudent());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    public static void insertStudentToClassSemester(Classes cl, Student st){
        String s1 ="class"+cl.getId();
        String query = "insert into "+s1+" (id_class, subject, id_student, name, class) values (?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, cl.getId());
            pstmt.setString(2, cl.getSubject());
            pstmt.setString(3, String.valueOf(st.getId()));
            pstmt.setString(4, st.getName());
            pstmt.setString(5, st.getClassStudent());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    public static void insertScoreClass(ScoreClass sc){
        String s1 ="class"+sc.getId_class();
        String query = "update "+s1+" set mid_score=?, end_score=?, totalscore=? where id_student='"+sc.getId_student()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sc.getMid_score());
            pstmt.setDouble(2, sc.getEnd_score());
            pstmt.setDouble(3, sc.getTotal_score());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    public static void insertToTranscript(Transcipt tr,ScoreClass sc){
        String s1 ="transcript"+sc.getId_student();
        String query = "insert into "+s1+" (semester, id_subject, subject, credits, midscore, endscore, anphascore) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, tr.getSemester());
            pstmt.setString(2, tr.getId_subject());
            pstmt.setString(3, tr.getSubject());
            pstmt.setString(4, tr.getCredits());
            pstmt.setDouble(5, tr.getMid_score());
            pstmt.setDouble(6, tr.getEnd_score());
            pstmt.setString(7, tr.getAnpha_score());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    public static void Update(Student st){
        String query = "Update student set name=?, date=?, gender=?, class=?, year=?, major=?, address=?, email=? where name='"+st.getName()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, st.getName());
            pstmt.setString(2, st.getDateOfBirth());
            pstmt.setString(3, st.getGender());
            pstmt.setString(4, st.getClassStudent());
            pstmt.setString(5, st.getYear());
            pstmt.setString(6, st.getMajor());
            pstmt.setString(7, st.getAddress());
            pstmt.setString(8, st.getEmail());
            pstmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void UpdateClass(Classes cl){
        String query = "Update classes set id=?, faculty=?, subject=?, id_subject=?, lecturers=?, semester=?, room=?, num_of_student=? where id='"+cl.getId()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, cl.getId());
            pstmt.setString(2, cl.getFaculty());
            pstmt.setString(3, cl.getSubject());
            pstmt.setString(4, cl.getIdsubject());
            pstmt.setString(5, cl.getLecturers());
            pstmt.setString(6, cl.getSemester());
            pstmt.setString(7, cl.getRoom());
            pstmt.setString(8, cl.getNumOfStudent());
            pstmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static List<Student>findByName(Student s){
        List<Student> studentl = new ArrayList<>();
        String query = "select*from student where student.name ='"+s.getName()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Student st = new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("gender"),
                        rs.getString("class"),
                        rs.getString("year"),
                        rs.getString("major"),
                        rs.getString("address"),
                        rs.getString("email"));
                studentl.add(st);
            }
        } catch (Exception e) {

        }
        return studentl;
    }

    public static List<Student>findByID(Student s){
        List<Student> studentl = new ArrayList<>();
        String query = "select*from student where student.id ='"+s.getId()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Student st = new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("gender"),
                        rs.getString("class"),
                        rs.getString("year"),
                        rs.getString("major"),
                        rs.getString("address"),
                        rs.getString("email"));
                studentl.add(st);
            }
        } catch (Exception e) {

        }
        return studentl;
    }

    public static List<Faculty> findFacultyAll(){
        List<Faculty> listFaculty = new ArrayList<>();
        String query = "select*from faculty";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Faculty fc = new Faculty(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"));
                listFaculty.add(fc);
            }
        } catch (Exception e) {

        }
        return listFaculty;
    }

    public static List<Subject> findSubjectAll(){
        List<Subject> listSubject = new ArrayList<>();
        String query = "select*from subject";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Subject sj = new Subject(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("credit"),
                        rs.getString("faculty"),
                        rs.getString("lecturers"));
                listSubject.add(sj);
            }
        } catch (Exception e) {

        }
        return listSubject;
    }

    public static void insertFaculty(Faculty fc) {
        String query = "insert into faculty (id, name, address) values (?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, fc.getId());
            pstmt.setString(2, fc.getName());
            pstmt.setString(3, fc.getAddress());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertSubject(Subject sj) {
        String query = "insert into subject (id, name, credit, faculty, lecturers) values (?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, sj.getId());
            pstmt.setString(2, sj.getName());
            pstmt.setString(3, sj.getCredit());
            pstmt.setString(4, sj.getFaculty());
            pstmt.setString(5, sj.getLecturers());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTeacher(Teacher tc) {
        String query = "insert into teacher (id, name, birth, faculty, phone, email, subject) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, tc.getId());
            pstmt.setString(2, tc.getName());
            pstmt.setString(3, tc.getBirth());
            pstmt.setString(4, tc.getFaculty());
            pstmt.setString(5, tc.getPhone());
            pstmt.setString(6, tc.getEmail());
            pstmt.setString(7, tc.getSubject());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Faculty>findFacultyName(Faculty f){
        List<Faculty> facultyl = new ArrayList<>();
        String query = "select*from faculty where faculty.name ='"+f.getName()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Faculty fc = new Faculty(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"));
                facultyl.add(fc);
            }
        } catch (Exception e) {

        }
        return facultyl;
    }

    public static List<Faculty>findFacultyID(Faculty f){
        List<Faculty> facultyl = new ArrayList<>();
        String query = "select*from faculty where faculty.id ='"+f.getId()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Faculty fc = new Faculty(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"));
                facultyl.add(fc);
            }
        } catch (Exception e) {

        }
        return facultyl;
    }

    public static void UpdateFaculty(Faculty fc) {
        String query = "Update faculty set id=?, name=?, address=? where id='"+fc.getId()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, fc.getId());
            pstmt.setString(2, fc.getName());
            pstmt.setString(3, fc.getAddress());
            pstmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static List getFaculty() {
        String query = "select name from faculty";
        List nameFaculty = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                nameFaculty.add(rs1.getString("name"));
            }
        } catch (Exception e) {

        }
        return nameFaculty;
    }

    public static List getCLassStudent() {
        String query = "select class from student";
        List classList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                classList.add(rs1.getString("class"));
            }
        } catch (Exception e) {

        }
        return classList;
    }

    public static List getCLassStudent2(Student st) {
        String query = "select class from student where student.major='"+st.getMajor()+"'";
        List classList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                classList.add(rs1.getString("class"));
            }
        } catch (Exception e) {

        }
        return classList;
    }


    public static List getIDStudent(Student st) {
        String query = "select id from student where student.class ='"+st.getClassStudent()+"'";
        List idStudentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idStudentList.add(rs1.getString("id"));
            }
        } catch (Exception e) {

        }
        return idStudentList;
    }

    public static List getNameStudentFromClass(Student st) {
        String query = "select name from student where student.class ='"+st.getClassStudent()+"'";
        List idStudentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idStudentList.add(rs1.getString("name"));
            }
        } catch (Exception e) {

        }
        return idStudentList;
    }

    public static List getIDStudentScore(String id) {
        String s1 = "class"+id;
        String query = "select id_student from "+s1;
        List idStudentList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idStudentList.add(rs1.getString("id_student"));
            }
        } catch (Exception e) {

        }
        return idStudentList;
    }
    public static List getSemester() {
        String query = "select semester from classes";
        List nameSemester = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                nameSemester.add(rs1.getString("semester"));
            }
        } catch (Exception e) {

        }
        return nameSemester;
    }

    public static List getTeacher(Teacher t) {
        String query = "select name from teacher where teacher.faculty ='"+t.getFaculty()+"'";
        List nameTeacher = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                nameTeacher.add(rs1.getString("name"));
            }
        } catch (Exception e) {

        }
        return nameTeacher;
    }

    public static List getSubject(Subject s) {
        String query = "select name from subject where subject.faculty ='"+s.getFaculty()+"'";
        List nameSubject = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                nameSubject.add(rs1.getString("name"));
            }
        } catch (Exception e) {

        }
        return nameSubject;
    }

    public static List getSubjectFromIDClass(Classes c) {
        String query = "select subject from classes where classes.id ='"+c.getId()+"'";
        List subjectL = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                subjectL.add(rs1.getString("subject"));
            }
        } catch (Exception e) {

        }
        return subjectL;
    }

    public static List getNameStudent(Student s) {
        String query = "select name from student where student.id ='"+s.getId()+"'";
        List student = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                student.add(rs1.getString("name"));
            }
        } catch (Exception e) {

        }
        return student;
    }

    public static Student getStudent(Student s) {
        String query = "select * from student where student.id ='"+s.getId()+"'";
        Student student = new Student();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                student.setName(rs1.getString("name"));
                student.setDateOfBirth(rs1.getString("date"));
                student.setGender(rs1.getString("gender"));
                student.setMajor(rs1.getString("major"));
                student.setClassStudent(rs1.getString("class"));
                student.setYear(rs1.getString("year"));
                student.setAddress(rs1.getString("address"));
                student.setEmail(rs1.getString("email"));
            }
        } catch (Exception e) {

        }
        return student;
    }


    public static List getIDClass(Classes c) {
        String query = "select id from classes where classes.faculty ='"+c.getFaculty()+"' and classes.semester ='"+c.getSemester()+"'";
        List idclass = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idclass.add(rs1.getString("id"));
            }
        } catch (Exception e) {

        }
        return idclass;
    }

    public static List getTeacherClass(Teacher t) {
        String query = "select lecturers from subject where subject.name ='"+t.getSubject()+"'";
        List teacherClass = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                teacherClass.add(rs1.getString("lecturers"));
            }
        } catch (Exception e) {

        }
        return teacherClass;
    }

    public static List getIDSubject2(Subject sj) {
        String query = "select id from subject where subject.name ='"+sj.getName()+"'";
        List idSubject = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idSubject.add(rs1.getString("id"));
            }
        } catch (Exception e) {

        }
        return idSubject;
    }

    public static List getCreditsSubject2(Subject sj) {
        String query = "select credit from subject where subject.name ='"+sj.getName()+"'";
        List creditsSubject = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                creditsSubject.add(rs1.getString("credit"));
            }
        } catch (Exception e) {

        }
        return creditsSubject;
    }

    public static List getidSubject(Subject s) {
        String query = "select id from subject where name ='"+s.getName()+"'";
        List idSubject = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                idSubject.add(rs1.getString("id"));
            }
        } catch (Exception e) {

        }
        return idSubject;
    }
    public static List<Subject>findSubjectName(Subject s){
        List<Subject> subjectL = new ArrayList<>();
        String query = "select*from subject where subject.name ='"+s.getName()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Subject sj = new Subject(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("credit"),
                        rs.getString("faculty"),
                        rs.getString("lecturers"));
                subjectL.add(sj);
            }
        } catch (Exception e) {

        }
        return subjectL;
    }

    public static List<Subject>findSubjectID(Subject s){
        List<Subject> subjectL = new ArrayList<>();
        String query = "select*from subject where subject.id ='"+s.getId()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Subject sj = new Subject(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("credit"),
                        rs.getString("faculty"),
                        rs.getString("lecturers"));
                subjectL.add(sj);
            }
        } catch (Exception e) {

        }
        return subjectL;
    }

    public static void UpdateSubject(Subject sj) {
        String query = "Update subject set id=?, name=?, credit=?, faculty=?, lecturers=? where id='"+sj.getId()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, sj.getId());
            pstmt.setString(2, sj.getName());
            pstmt.setString(3, sj.getCredit());
            pstmt.setString(4, sj.getFaculty());
            pstmt.setString(5, sj.getLecturers());

            pstmt.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static List<Teacher> findTeacherAll() {
        List<Teacher> listTeacher = new ArrayList<>();
        String query = "select*from teacher";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Teacher tc = new Teacher(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("faculty"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("subject"));
                listTeacher.add(tc);
            }
        } catch (Exception e) {

        }
        return listTeacher;
    }

    public static List<Teacher>findTeacherName(Teacher t){
        List<Teacher> teacherL = new ArrayList<>();
        String query = "select*from teacher where teacher.name ='"+t.getName()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Teacher tc = new Teacher(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("faculty"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("subject"));
                teacherL.add(tc);
            }
        } catch (Exception e) {

        }
        return teacherL;
    }

    public static List<Teacher>findTeacherID(Teacher t){
        List<Teacher> teacherL = new ArrayList<>();
        String query = "select*from teacher where teacher.id ='"+t.getId()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Teacher tc = new Teacher(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("faculty"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("subject"));
                teacherL.add(tc);
            }
        } catch (Exception e) {

        }
        return teacherL;
    }

    public static List<Classes>findClassByName(Classes c){
        List<Classes> classL = new ArrayList<>();
        String query = "select*from classes where classes.subject ='"+c.getSubject()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Classes cl = new Classes(
                        rs.getString("id"),
                        rs.getString("faculty"),
                        rs.getString("subject"),
                        rs.getString("id_subject"),
                        rs.getString("lecturers"),
                        rs.getString("semester"),
                        rs.getString("room"),
                        rs.getString("num_of_student"));
                classL.add(cl);
            }
        } catch (Exception e) {

        }
        return classL;
    }

    public static List<Classes>findClassByID(Classes c){
        List<Classes> classL = new ArrayList<>();
        String query = "select*from classes where classes.id ='"+c.getId()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Classes cl = new Classes(
                        rs.getString("id"),
                        rs.getString("faculty"),
                        rs.getString("subject"),
                        rs.getString("id_subject"),
                        rs.getString("lecturers"),
                        rs.getString("semester"),
                        rs.getString("room"),
                        rs.getString("num_of_student"));
                classL.add(cl);
            }
        } catch (Exception e) {

        }
        return classL;
    }

    public static List<Account> findAccountAll(){
        List<Account> listAccount = new ArrayList<>();
        String query = "select*from account";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Account ac = new Account(rs.getString("rank"),
                        rs.getString("id_user"),
                        rs.getString("username"),
                        rs.getString("password"));
                listAccount.add(ac);
            }
        } catch (Exception e) {

        }
        return listAccount;
    }

//    public static List<Transcipt> findTranscriptAll(Student st){
//        String s1 = "transcript"+st.getId();
//        List<Transcipt> listTS = new ArrayList<>();
//        String query = "select*from "+s1;
//        try {
//            Connection connection = getConnection();
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()){
//                Transcipt ts = new Transcipt(rs.getString("semester"),
//                        rs.getString(""),
//                        rs.getString("username"),
//                        rs.getString("password"),
//                        rs.getString("username"),
//                        rs.getString("username"),
//                        rs.getString("username"));
//                listTS.add(ts);
//            }
//        } catch (Exception e) {
//
//        }
//        return listTS;
//    }

    public static void UpdateAccount(Account ac) {
        String query = "Update account set password=? where username='"+ac.getUsername()+"'";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, ac.getPassword());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static List<Account>findAccount(Account a){
        List<Account> accountl = new ArrayList<>();
        String query = "select*from account where account.username ='"+a.getUsername()+"'";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Account ac = new Account(
                        rs.getString("rank"),
                        rs.getString("id_user"),
                        rs.getString("username"),
                        rs.getString("password"));
                accountl.add(ac);
            }
        } catch (Exception e) {

        }
        return accountl;
    }

    public static void insertAccountST(Account ac){
        String query = "insert into account (rank, id_user, username, password) values (?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, ac.getRank());
            pstmt.setString(2, ac.getId_user());
            pstmt.setString(3, ac.getUsername());
            pstmt.setString(4, ac.getPassword());
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void creatTableClass(Classes cl){
        String s1 ="class"+cl.getId();
        String query = "CREATE TABLE " +s1+
                " (stt INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "id_class VARCHAR, "+
                "subject VARCHAR, "+
                "id_student VARCHAR, " +
                "name VARCHAR, " +
                "class VARCHAR, " +
                "mid_score DOUBLE PRECISION, " +
                "end_score Double Precision," +
                "totalScore Double Precision)";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void creatTranscriptStudent(Student st){
        String s1 ="transcript"+st.getId();
        String query = "CREATE TABLE " +s1+
                " (stt INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "semester VARCHAR, "+
                "id_subject VARCHAR, "+
                "subject VARCHAR, "+
                "credits VARCHAR, " +
                "midScore DOUBLE PRECISION, " +
                "endScore DOUBLE PRECISION, " +
                "anphaScore VARCHAR)";
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
