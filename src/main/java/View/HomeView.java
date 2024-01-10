package View;

import Controller.HomeController;
import Controller.HomeMenuController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class HomeView extends JFrame {
    public static String nameLogin;


    private JLabel idloginLabel;
    private JPanel panelSM;
    private JPanel panelTop1;

    private JPanel panelTop2;
    private JButton studentMenuButton;
    private JButton facultyMenuButton;
    private JButton teacherMenuButton;
    private JButton subjectMenuButton;
    private JButton classMenuButton;
    private JButton classStudentMenuButton;
    private JButton scoreMenuButton;
    private JButton transcriptMenuButton;


    public HomeView(){
        this.initStudentMN();
    }



    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    private void initStudentMN(){
        this.setTitle("Student Management");
        this.setSize(1200,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Action action = new HomeController(this);
        Action action1 = new HomeMenuController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();
        GridBagConstraints gbcTop1 = new GridBagConstraints();
        GridBagConstraints gbcTop2 = new GridBagConstraints();
        GridBagConstraints gbcBot1 = new GridBagConstraints();
        GridBagConstraints gbcAll = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setLayout(new BorderLayout());


        panelTop1 = new JPanel();
        panelTop1.setBackground(new Color(204, 204, 255));
        panelTop1.setLayout(new GridBagLayout());

        panelTop2 = new JPanel();
        panelTop2.setBackground(new Color(204, 204, 255));
        panelTop2.setLayout(new GridBagLayout());

        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font fontTop = new Font("Verdana",Font.BOLD,28);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/images/Customer1.png")));
        gbcTop1.anchor=GridBagConstraints.LINE_START;
        gbcTop1.insets = new Insets(20,10,20,0);
        gbcTop1.gridheight = 2;
        gbcTop1.gridx = 0;
        gbcTop1.gridy = 0;
        panelTop1.add(iconLabel, gbcTop1);

        nameLogin = LoginView.nameLogin;
        idloginLabel = new JLabel();
        idloginLabel.setText("User: "+nameLogin);
        idloginLabel.setFont(inputFont);
        idloginLabel.setForeground(Color.blue);
        gbcTop1.insets = new Insets(25,5,0,0);
        gbcTop1.gridheight = 1;
        gbcTop1.gridx = 1;
        gbcTop1.gridy = 0;
        panelTop1.add(idloginLabel, gbcTop1);


        Date today = new Date();
        Locale local = new Locale("eng","VI");
        DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM, local);
        String date = d.format(today);
        JLabel todayLabel = new JLabel();
        todayLabel.setText("Today: "+date);
        todayLabel.setFont(inputFont);
        todayLabel.setForeground(Color.blue);
        gbcTop1.insets = new Insets(0,5,0,10);
        gbcTop1.gridx = 1;
        gbcTop1.gridy = 1;
        panelTop1.add(todayLabel, gbcTop1);

        //      ------top----------
        JLabel coverPanel = new JLabel();
        coverPanel.setIcon(new ImageIcon(getClass().getResource("/images/banner1.png")));
        gbcTop2.insets = new Insets(0,0,0,0);
        gbcTop2.fill = GridBagConstraints.HORIZONTAL;
        gbcTop2.gridwidth = 1;
        gbcTop2.gridheight = 1;
        gbcTop2.gridx = 0;
        gbcTop2.gridy = 0;
        panelTop2.add(coverPanel, gbcTop2);

        Border compound, raisedbevel, loweredbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        panelTop1.setBorder(compound);
        panelTop2.setBorder(compound);



        JPanel panelBot1 = new JPanel();
        panelBot1.setBackground(new Color(204, 204, 255));
        panelBot1.setLayout(new GridBagLayout());

        gbcBot1.anchor = GridBagConstraints.PAGE_START;

        JLabel menuLabel = new JLabel();
        menuLabel.setText("MENU");
        menuLabel.setFont(new Font("Arial",1,20));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setOpaque(true);
        menuLabel.setBackground(new Color(0,0,204));
        menuLabel.setPreferredSize(new Dimension(210,35));
        gbcBot1.insets = new Insets(0,0,0,0);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 0;
        panelBot1.add(menuLabel, gbcBot1);

        facultyMenuButton = new JButton("Nhập khoa");
        facultyMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        facultyMenuButton.setIconTextGap(10);
        facultyMenuButton.setMargin(new Insets(0, 14, 0,0 ));
        facultyMenuButton.addActionListener(action);
        facultyMenuButton.setPreferredSize(new Dimension(200,30));
        facultyMenuButton.setFont(buttonFont);
        facultyMenuButton.setForeground(Color.blue);
        facultyMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        facultyMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(20,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 1;
        panelBot1.add(facultyMenuButton, gbcBot1);

        studentMenuButton = new JButton("Nhập sinh viên");
        studentMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        studentMenuButton.setIconTextGap(10);
        studentMenuButton.addActionListener(action);
        studentMenuButton.setPreferredSize(new Dimension(200,30));
        studentMenuButton.setFont(buttonFont);
//        studentMenuButton.setBackground(new Color(0xeac086));
        studentMenuButton.setForeground(Color.blue);
        studentMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        studentMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 2;
        panelBot1.add(studentMenuButton, gbcBot1);

        teacherMenuButton = new JButton("Nhập giảng viên");
        teacherMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        teacherMenuButton.setIconTextGap(10);
        teacherMenuButton.addActionListener(action);
        teacherMenuButton.setPreferredSize(new Dimension(200,30));
        teacherMenuButton.setFont(buttonFont);
//        studentMenuButton.setBackground(new Color(0xeac086));
        teacherMenuButton.setForeground(Color.blue);
        teacherMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        teacherMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 3;
        panelBot1.add(teacherMenuButton, gbcBot1);

        subjectMenuButton = new JButton("Nhập môn học");
        subjectMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        subjectMenuButton.setIconTextGap(10);
        subjectMenuButton.addActionListener(action);
        subjectMenuButton.setPreferredSize(new Dimension(200,30));
        subjectMenuButton.setFont(buttonFont);
        subjectMenuButton.setForeground(Color.blue);
        subjectMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        subjectMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 4;
        panelBot1.add(subjectMenuButton, gbcBot1);

        classMenuButton = new JButton("Nhập lớp học");
        classMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        classMenuButton.setIconTextGap(10);
        classMenuButton.addActionListener(action);
        classMenuButton.setPreferredSize(new Dimension(200,30));
        classMenuButton.setFont(buttonFont);
        classMenuButton.setForeground(Color.blue);
        classMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        classMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 5;
        panelBot1.add(classMenuButton, gbcBot1);

        classStudentMenuButton = new JButton("Nhập danh sách lớp");
        classStudentMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        classStudentMenuButton.setIconTextGap(10);
        classStudentMenuButton.addActionListener(action);
        classStudentMenuButton.setPreferredSize(new Dimension(200,30));
        classStudentMenuButton.setFont(buttonFont);
        classStudentMenuButton.setForeground(Color.blue);
        classStudentMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        classStudentMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(20,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 6;
        panelBot1.add(classStudentMenuButton, gbcBot1);

        scoreMenuButton = new JButton("Nhập điểm");
        scoreMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        scoreMenuButton.setIconTextGap(10);
        scoreMenuButton.addActionListener(action);
        scoreMenuButton.setPreferredSize(new Dimension(200,30));
        scoreMenuButton.setFont(buttonFont);
        scoreMenuButton.setForeground(Color.blue);
        scoreMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        scoreMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,0,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 7;
        panelBot1.add(scoreMenuButton, gbcBot1);

        transcriptMenuButton = new JButton("Bảng điểm sinh viên");
        transcriptMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
        transcriptMenuButton.setIconTextGap(10);
        transcriptMenuButton.addActionListener(action);
        transcriptMenuButton.setPreferredSize(new Dimension(200,30));
        transcriptMenuButton.setFont(buttonFont);
        transcriptMenuButton.setForeground(Color.blue);
        transcriptMenuButton.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        transcriptMenuButton.setBorder(new RoundedBorder(10));
        gbcBot1.insets = new Insets(15,5,60,5);
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 8;
        panelBot1.add(transcriptMenuButton, gbcBot1);
        
        panelBot1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                new Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new Color(0, 0, 255)));

        panelSM.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new Color(0, 0, 255)));

//        ----------------
        this.setLayout(new GridBagLayout());
        gbcAll.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcAll.insets = new Insets(0,0,0,0);
        gbcAll.gridx = 0;
        gbcAll.gridy = 0;
        this.add(panelTop1, gbcAll);

        gbcAll.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcAll.insets = new Insets(0,5,0,0);
        gbcAll.gridx = 1;
        gbcAll.gridy = 0;
        this.add(panelTop2, gbcAll);

        gbcAll.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcAll.insets = new Insets(5,0,0,0);
        gbcAll.gridx = 0;
        gbcAll.gridy = 1;
        this.add(panelBot1, gbcAll);

        gbcAll.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcAll.insets = new Insets(5,5,0,0);
        gbcAll.gridx = 1;
        gbcAll.gridy = 1;
        this.add(panelSM, gbcAll);


//-----Menu Bar--------
        JMenuBar menuBar = new JMenuBar();
        JMenu menuMenu = new JMenu("Menu");
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem studentMenuItem = new JMenuItem("Quản lý sinh viên");
        studentMenuItem.setPreferredSize(new Dimension(180, studentMenuItem.getPreferredSize().height));
        studentMenuItem.addActionListener(action1);
        JMenuItem facultyMenuItem = new JMenuItem("Quản lý Khoa");
        facultyMenuItem.addActionListener(action1);
        JMenuItem subjectMenuItem = new JMenuItem("Quản lý môn học");
        subjectMenuItem.addActionListener(action1);
        JMenu classMenu = new JMenu("Quản lý lớp học");
        JMenuItem creatClassMenu = new JMenuItem("Tạo lớp tín chỉ");
        creatClassMenu.addActionListener(action1);
        JMenuItem addSTClassMenu = new JMenuItem("Danh sách lớp");
        addSTClassMenu.addActionListener(action1);
        classMenu.add(creatClassMenu);
        classMenu.add(addSTClassMenu);
        JMenu scoreMenu = new JMenu("Quản lý điểm");
        JMenuItem addScore = new JMenuItem("Điểm lớp học");
        addScore.addActionListener(action1);
        JMenuItem transcriptScore = new JMenuItem("Bảng điểm sinh viên");
        transcriptScore.addActionListener(action1);
        scoreMenu.add(addScore);
        scoreMenu.add(transcriptScore);
        JMenuItem logoutMenuItem = new JMenuItem("Log out");
        logoutMenuItem.addActionListener(action1);
        JMenuItem teacherMenuItem = new JMenuItem("Quản lý giảng viên");
        teacherMenuItem.addActionListener(action1);
        menuMenu.add(studentMenuItem);
        menuMenu.add(teacherMenuItem);
        menuMenu.add(facultyMenuItem);
        menuMenu.add(subjectMenuItem);
        menuMenu.add(classMenu);
        menuMenu.add(scoreMenu);
        menuMenu.add(new JSeparator());
        menuMenu.add(logoutMenuItem);
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        openFile.setPreferredSize(new Dimension(150, openFile.getPreferredSize().height));
        saveFile.setPreferredSize(new Dimension(150, saveFile.getPreferredSize().height));
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        JMenuItem aboutMeMenuItem = new JMenuItem("About me");
        aboutMeMenuItem.setPreferredSize(new Dimension(150, saveFile.getPreferredSize().height));
        aboutMenu.add(aboutMeMenuItem);
        aboutMeMenuItem.addActionListener(action1);


        menuBar.add(menuMenu);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);

//        -------------------------------------------------------------------------------


        this.setVisible(true);

    }


    public void logout() {
        JOptionPane.showMessageDialog(null, "Bạn muốn đăng xuất khỏi hệ thống?");
        LoginView lgView = new LoginView();
        lgView.setVisible(true);
        this.dispose();
    }

    public void openClassMN() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new Color(0xeac086));
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new JButton().getBackground());
        ClassesView view = new ClassesView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openFaculty() {
        facultyMenuButton.setBackground(new Color(0xeac086));
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new JButton().getBackground());
        FacultyView view = new FacultyView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openSubject() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new Color(0xeac086));
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new JButton().getBackground());
        SubjectView view = new SubjectView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openTeacherV() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new Color(0xeac086));
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new JButton().getBackground());
        TeacherView view = new TeacherView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openaddST() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new Color(0xeac086));
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new JButton().getBackground());
        addSTSemesterView view = new addSTSemesterView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openStudent() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new Color(0xeac086));
        scoreMenuButton.setBackground(new JButton().getBackground());
        StudentManagementView view = new StudentManagementView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openTranscript() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new Color(0xeac086));
        scoreMenuButton.setBackground(new JButton().getBackground());
        TranscriptView view = new TranscriptView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }

    public void openScore() {
        facultyMenuButton.setBackground(new JButton().getBackground());
        studentMenuButton.setBackground(new JButton().getBackground());
        teacherMenuButton.setBackground(new JButton().getBackground());
        subjectMenuButton.setBackground(new JButton().getBackground());
        classMenuButton.setBackground(new JButton().getBackground());
        classStudentMenuButton.setBackground(new JButton().getBackground());
        transcriptMenuButton.setBackground(new JButton().getBackground());
        scoreMenuButton.setBackground(new Color(0xeac086));
        ScoreView view = new ScoreView();
        panelSM.removeAll();
        panelSM.revalidate();
        panelSM.repaint();
        panelSM.add(view);
    }
}
