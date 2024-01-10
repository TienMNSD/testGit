package View;

import ConnData.ConnJDBC;
import Controller.StudentLoginMenuController;
import Model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentLoginView extends JFrame {
    public static String nameLogin;

    private static JTable fcTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JButton saveButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JPanel panelSMV;
    private JPanel panelClassMNV;
    private JTextField idclassTextField;
    private JTextField subjectTextField;
    private JTextField semesterTextField;
    private JTextField lecturersTextField;
    private JTextField numSTTextField;
    private JTextField classRoomTextField;
    private JButton saveclassButton;
    private JButton findClassButton;
    private JButton updateClassButton;
    private JButton refreshClassButton;
    private JTable classTable;
    private JScrollPane classscrollPane;
    private JTextField facultyTextField;
    private JTextField findTextField;
    private JLabel idloginLabel;
    private JPanel bot1panel;
    private JPanel panelTop;
    private JTextField idTextField;
    private JTextField passwTextField;
    private JTextField userTextField;
    private JComboBox<String> rankCBB;
    private JPanel bot2panel;


    public StudentLoginView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setTitle("Student Management");
        this.setSize(1260,720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Action action1 = new StudentLoginMenuController(this);



        GridBagConstraints gbcBot1 = new GridBagConstraints();
        GridBagConstraints gbcBot2 = new GridBagConstraints();
        GridBagConstraints gbcTop = new GridBagConstraints();
        GridBagConstraints gbcAll = new GridBagConstraints();

        bot1panel = new JPanel();
        bot1panel.setBackground(new Color(0xdef3f6));
        bot1panel.setLayout(new GridBagLayout());
//        bot1panel.setPreferredSize(new Dimension(290,540));
        bot1panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new Color(0, 0, 255)));

        bot2panel = new JPanel();
        bot2panel.setBackground(new Color(0xdef3f6));
        bot2panel.setLayout(new GridBagLayout());
        bot2panel.setPreferredSize(new Dimension(945,540));
        bot2panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                        new Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new Color(0, 0, 255)));

        panelTop = new JPanel();
        panelTop.setBackground(new Color(0xdef3f6));
        panelTop.setLayout(new GridBagLayout());

        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/images/Customer1.png")));
        gbcTop.anchor=GridBagConstraints.LINE_START;
        gbcTop.insets = new Insets(0,5,0,0);
        gbcTop.gridheight = 2;
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        panelTop.add(iconLabel, gbcTop);

        nameLogin = LoginView.nameLogin;
        idloginLabel = new JLabel();
        idloginLabel.setText("User: "+nameLogin);
        idloginLabel.setFont(inputFont);
        idloginLabel.setForeground(Color.blue);
        gbcTop.insets = new Insets(10,5,0,0);
        gbcTop.gridheight = 1;
        gbcTop.gridx = 1;
        gbcTop.gridy = 0;
        panelTop.add(idloginLabel, gbcTop);


        Date today = new Date();
        Locale local = new Locale("eng","VI");
        DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM, local);
        String date = d.format(today);
        JLabel todayLabel = new JLabel();
        todayLabel.setText("Today: "+date);
        todayLabel.setFont(inputFont);
        todayLabel.setForeground(Color.blue);
        gbcTop.insets = new Insets(0,5,0,0);
        gbcTop.gridx = 1;
        gbcTop.gridy = 1;
        panelTop.add(todayLabel, gbcTop);

        //      ------top----------
        JLabel coverPanel = new JLabel();
        coverPanel.setIcon(new ImageIcon(getClass().getResource("/images/banner1.PNG")));
        gbcTop.insets = new Insets(0,90,0,0);
        gbcTop.fill = GridBagConstraints.HORIZONTAL;
        gbcTop.gridwidth = 3;
        gbcTop.gridheight = 2;
        gbcTop.gridx = 2;
        gbcTop.gridy = 0;
        panelTop.add(coverPanel, gbcTop);

        Border compound, raisedbevel, loweredbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        panelTop.setBorder(compound);


        gbcBot1.insets = new Insets(0,0,0,0);
        gbcBot1.anchor=GridBagConstraints.CENTER;

        JLabel inforLabel = new JLabel("THÔNG TIN SINH VIÊN");
        inforLabel.setFont(new Font("Arial",1,20));
        inforLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inforLabel.setForeground(Color.WHITE);
        inforLabel.setOpaque(true);
        inforLabel.setBackground(new Color(0,0,204));
        inforLabel.setPreferredSize(new Dimension(290,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 0;
        bot1panel.add(inforLabel, gbcBot1);

        JLabel infor1Label = new JLabel();
        infor1Label.setIcon(new ImageIcon(getClass().getResource("/images/information1.png")));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 1;
        bot1panel.add(infor1Label, gbcBot1);

        gbcBot1.insets = new Insets(20,10,0,0);
        gbcBot1.anchor=GridBagConstraints.LINE_START;

        JLabel idLabel = new JLabel("MSSV: "+nameLogin);
        idLabel.setFont(inputFont);
//        idLabel.setPreferredSize(new Dimension(100,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 2;
        bot1panel.add(idLabel, gbcBot1);

//        --------input age----------
        JLabel nameLabel = new JLabel("Họ và tên");
        nameLabel.setFont(inputFont);
//        nameLabel.setPreferredSize(new Dimension(100,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 3;
        bot1panel.add(nameLabel, gbcBot1);


//
////        --------input gender----------
        JLabel DOBLabel = new JLabel("Ngày sinh");
        DOBLabel.setFont(inputFont);
//        DOBLabel.setPreferredSize(new Dimension(100,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 4;
        bot1panel.add(DOBLabel, gbcBot1);


        JLabel genderLabel = new JLabel("Giới tính");
        genderLabel.setFont(inputFont);
//        genderLabel.setPreferredSize(new Dimension(80,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 5;
        bot1panel.add(genderLabel, gbcBot1);

        JLabel classLabel = new JLabel("Lớp");
        classLabel.setFont(inputFont);
//        classLabel.setPreferredSize(new Dimension(80,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 6;
        bot1panel.add(classLabel, gbcBot1);

        JLabel yearLabel = new JLabel("Khóa học");
        yearLabel.setFont(inputFont);
//        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 7;
        bot1panel.add(yearLabel, gbcBot1);

        JLabel majorLabel = new JLabel("Khoa");
        majorLabel.setFont(inputFont);
//        majorLabel.setPreferredSize(new Dimension(80,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 8;
        bot1panel.add(majorLabel, gbcBot1);

        JLabel addressLabel = new JLabel("Địa chỉ");
        addressLabel.setVerticalAlignment(JLabel.TOP);
        addressLabel.setFont(inputFont);
        addressLabel.setPreferredSize(new Dimension(280,40));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 9;
        bot1panel.add(addressLabel, gbcBot1);

        gbcBot1.insets = new Insets(20,10,30,0);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(inputFont);
//        emailLabel.setPreferredSize(new Dimension(80,30));
        gbcBot1.gridx = 0;
        gbcBot1.gridy = 10;
        bot1panel.add(emailLabel, gbcBot1);

        Student st = new Student();
        st.setId(Integer.parseInt(nameLogin));
        Student st1 = ConnJDBC.getStudent(st);
        nameLabel.setText("Họ và tên: " +st1.getName());
        DOBLabel.setText("Ngày sinh: "+st1.getDateOfBirth());
        genderLabel.setText("Giới tính: "+st1.getGender());
        classLabel.setText("Lớp: "+st1.getClassStudent());
        yearLabel.setText("Khóa học: "+st1.getYear());
        addressLabel.setText("Địa chỉ: "+st1.getAddress());
        emailLabel.setText("Email: "+st1.getEmail());
        majorLabel.setText("Khoa: "+st1.getMajor());

        fcTable = new JTable();
        fcTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                },
                new String[] {"STT", "Học kỳ","Mã HP", "Tên HP","Tín chỉ", "Điểm QT", "Điểm thi", "Điểm chữ"}
        ));
        fcTable.setFont(new Font("Arial",Font.PLAIN,14));
        fcTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
        fcTable.setGridColor(new Color(0xbcbcbc));
        fcTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showTranscriptData(ConnJDBC.findTranscript(st));
        fcTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < fcTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < fcTable.getRowCount(); row++) {
                TableCellRenderer renderer = fcTable.getCellRenderer(row, column);
                Component comp = fcTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +4 , width);
            }
            if(width > 300)
                width=300;
            fcTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        fcTable.getColumnModel().getColumn(0).setMinWidth(60);
        fcTable.getColumnModel().getColumn(1).setMinWidth(80);
        fcTable.getColumnModel().getColumn(2).setMinWidth(80);
        fcTable.getColumnModel().getColumn(3).setMinWidth(200);
        fcTable.getColumnModel().getColumn(4).setMinWidth(80);
        fcTable.getColumnModel().getColumn(5).setMinWidth(80);
        fcTable.getColumnModel().getColumn(6).setMinWidth(80);
        fcTable.getColumnModel().getColumn(7).setMinWidth(80);
        ((DefaultTableCellRenderer) fcTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(fcTable.getModel());
        fcTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(fcTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(940,500));

        gbcBot2.anchor=GridBagConstraints.PAGE_START;
        gbcBot2.insets = new Insets(0,0,0,0);
        gbcBot2.gridx = 0;
        gbcBot2.gridy = 1;
        bot2panel.add(scrollPane, gbcBot2);

        gbcBot2.insets = new Insets(0,0,5,0);
        JLabel transcriptLabel = new JLabel("BẢNG ĐIỂM SINH VIÊN");
        transcriptLabel.setFont(new Font("Arial",1,20));
        transcriptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transcriptLabel.setForeground(Color.WHITE);
        transcriptLabel.setOpaque(true);
        transcriptLabel.setBackground(new Color(0,0,204));
        transcriptLabel.setPreferredSize(new Dimension(940,30));
        gbcBot2.gridx = 0;
        gbcBot2.gridy = 0;
        bot2panel.add(transcriptLabel, gbcBot2);

        gbcAll.insets = new Insets(5,2,0,0);
        this.setLayout(new GridBagLayout());
        gbcAll.gridx = 0;
        gbcAll.gridy = 1;
        this.add(bot1panel, gbcAll);

        gbcAll.insets = new Insets(5,5,0,0);
        gbcAll.gridx = 1;
        gbcAll.gridy = 1;
        this.add(bot2panel, gbcAll);

        gbcAll.insets = new Insets(0,0,0,0);
        gbcAll.gridwidth = 2;
        gbcAll.gridheight = 1;
        gbcAll.gridx = 0;
        gbcAll.gridy = 0;
        this.add(panelTop, gbcAll);


//-----Menu Bar--------
        JMenuBar menuBar = new JMenuBar();
        JMenu menuMenu = new JMenu("Menu");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem changePassMenuItem = new JMenuItem("Đổi mật khẩu");
        changePassMenuItem.setPreferredSize(new Dimension(200, changePassMenuItem.getPreferredSize().height));
        changePassMenuItem.addActionListener(action1);
        JMenuItem logoutMenuItem = new JMenuItem("Log out");
        logoutMenuItem.addActionListener(action1);
        menuMenu.add(changePassMenuItem);
        menuMenu.add(new JSeparator());
        menuMenu.add(logoutMenuItem);

        JMenuItem aboutMeMenuItem = new JMenuItem("About me");
        aboutMeMenuItem.setPreferredSize(new Dimension(150, aboutMeMenuItem.getPreferredSize().height));
        aboutMenu.add(aboutMeMenuItem);
        aboutMeMenuItem.addActionListener(action1);

        menuBar.add(menuMenu);
        menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);



//---------------------------------------------------

        this.setVisible(true);

        showTranscriptData(ConnJDBC.findTranscript(st));


    }

    public void showTranscriptData(List<Transcipt> list){
        List<Transcipt> listTranscript = new ArrayList<>();
        listTranscript = list;
        DefaultTableModel tableModel;
        fcTable.getModel();
        tableModel = (DefaultTableModel) fcTable.getModel();
        tableModel.setRowCount(0);
        listTranscript.forEach((transcipt) -> {
            tableModel.addRow(new Object[]{ transcipt.getStt(),
                    transcipt.getSemester(), transcipt.getId_subject(), transcipt.getSubject(), transcipt.getCredits(),
                    transcipt.getMid_score(), transcipt.getEnd_score(), transcipt.getAnpha_score()
            });
        });
    }

    public void logout() {
        JOptionPane.showMessageDialog(null, "Bạn muốn đăng xuất khỏi hệ thống?");
        LoginView lgView = new LoginView();
        lgView.setVisible(true);
        this.dispose();
    }
}
