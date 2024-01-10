package View;

import ConnData.ConnJDBC;
import Controller.AccountController;
import Controller.AccountMenuController;
import Model.Account;
import Model.Faculty;
import Model.StudentManagementModel;

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

public class AccountView extends JFrame {
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
    private JPanel panelSM;
    private JPanel panelTop;
    private JTextField idTextField;
    private JTextField passwTextField;
    private JTextField userTextField;
    private JComboBox<String> rankCBB;


    public AccountView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setTitle("Student Management");
        this.setSize(1260,630);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Action action = new AccountController(this);
        Action action1 = new AccountMenuController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();
        GridBagConstraints gbcTop = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());

        panelTop = new JPanel();
        panelTop.setBackground(new Color(0xdef3f6));
        panelTop.setLayout(new GridBagLayout());

        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font fontTop = new Font("Verdana",Font.BOLD,28);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/images/Customer1.png")));
        gbcTop.anchor=GridBagConstraints.LINE_START;
        gbcTop.insets = new Insets(0,0,0,0);
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


        gbcSM.insets = new Insets(0,50,10,0);
        gbcSM.anchor=GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel rankLabel = new JLabel("");
        rankLabel.setFont(inputFont);
        rankLabel.setPreferredSize(new Dimension(100,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(rankLabel, gbcSM);

//        String rank[] = {"_","Giảng viên","Sinh viên"};
//        rankCBB = new JComboBox<>(rank);
//        rankCBB.setFont(textFieldFont);
//        rankCBB.setPreferredSize(new Dimension(160,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 1;
//        panelSM.add(rankCBB, gbcSM);
//
////        --------input name----------
        JLabel idLabel = new JLabel("");
        idLabel.setFont(inputFont);
        idLabel.setPreferredSize(new Dimension(100,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(idLabel, gbcSM);
//
//        nameTextField = new JTextField();
//        nameTextField.setFont(textFieldFont);
//        nameTextField.setPreferredSize(new Dimension(160,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 2;
//        panelSM.add(nameTextField, gbcSM);

//        --------input age----------
        JLabel userLabel = new JLabel("Tên đăng nhập");
        userLabel.setFont(inputFont);
        userLabel.setPreferredSize(new Dimension(100,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(userLabel, gbcSM);


//
////        --------input gender----------
        JLabel passwLabel = new JLabel("Mật khẩu");
        passwLabel.setFont(inputFont);
        passwLabel.setPreferredSize(new Dimension(100,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(passwLabel, gbcSM);

        gbcSM.insets = new Insets(0,5,10,0);
        
        userTextField = new JTextField();
        userTextField.setFont(textFieldFont);
        userTextField.setPreferredSize(new Dimension(160,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        panelSM.add(userTextField, gbcSM);

        passwTextField = new JTextField();
        passwTextField.setFont(textFieldFont);
        passwTextField.setPreferredSize(new Dimension(160,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(passwTextField, gbcSM);
//

//        //        --------input class---------
        JLabel classLabel = new JLabel("");
        classLabel.setFont(inputFont);
        classLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(classLabel, gbcSM);
//

//
//        //        --------input Year----------
        JLabel yearLabel = new JLabel("");
        yearLabel.setFont(inputFont);
        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(yearLabel, gbcSM);
//
//        yearTextField = new JTextField();
//        yearTextField.setFont(textFieldFont);
//        yearTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 6;
//        panelSM.add(yearTextField, gbcSM);
//
//
////        --------input major----------
        JLabel majorLabel = new JLabel("");
        majorLabel.setFont(inputFont);
        majorLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(majorLabel, gbcSM);
//
//        majorTextField = new JTextField();
//        majorTextField.setFont(textFieldFont);
//        majorTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 7;
//        panelSM.add(majorTextField, gbcSM);
//
////        --------input address----------
        JLabel address1Label = new JLabel("");
        address1Label.setFont(inputFont);
        address1Label.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(address1Label, gbcSM);
//
//        addressTextField = new JTextField();
//        addressTextField.setFont(textFieldFont);
//        addressTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 8;
//        panelSM.add(addressTextField, gbcSM);
//
//        //        --------input email----------
        JLabel emailLabel = new JLabel("");
        emailLabel.setFont(inputFont);
        emailLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(emailLabel, gbcSM);
//
//        emailTextField = new JTextField();
//        emailTextField.setFont(textFieldFont);
//        emailTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 9;
//        panelSM.add(emailTextField, gbcSM);
//
        //        --------Button---------
//        saveButton = new JButton("Lưu");
//        saveButton.addActionListener(action);
//        saveButton.setPreferredSize(new Dimension(90,30));
//        saveButton.setFont(buttonFont);
//        saveButton.setBackground(new Color(0xeac086));
//        saveButton.setForeground(Color.blue);
//        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
//        gbcSM.insets = new Insets(15,10,20,0);
//        gbcSM.gridx = 0;
//        gbcSM.gridy = 10;
//        panelSM.add(saveButton, gbcSM);

//        deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(action);
//        deleteButton.setPreferredSize(new Dimension(100,50));
//        deleteButton.setFont(inputFont);

        gbcSM.insets = new Insets(10,55,25,0);
        updateButton = new JButton("Cập nhật");
        updateButton.addActionListener(action);
        updateButton.setPreferredSize(new Dimension(110,30));
        updateButton.setFont(buttonFont);
        updateButton.setBackground(new Color(0xeac086 ));
        updateButton.setForeground(Color.blue);
        updateButton.setIcon(new ImageIcon(getClass().getResource("/images/Update.png")));
        gbcSM.gridx = 0;
        gbcSM.gridy = 10;
        panelSM.add(updateButton, gbcSM);

        refreshButton = new JButton("Làm mới");
        refreshButton.addActionListener(action);
        refreshButton.setPreferredSize(new Dimension(110,30));
        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(new Color(0xeac086));
        refreshButton.setForeground(Color.blue);
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/images/reload.png")));
//        gbcSM.insets = new Insets(15,10,20,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 10;
        panelSM.add(refreshButton, gbcSM);
//
//
//
//
//
//        //        ------CBB gender-------
//        String gender[] = {"","Nam","Nữ"};
//        genderCBB = new JComboBox<>(gender);
//        genderCBB.setFont(textFieldFont);
//        genderCBB.setPreferredSize(new Dimension(120,25));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 4;
//        panelSM.add(genderCBB, gbcSM);

        findTextField = new JTextField("Tên đăng nhập...");
        findTextField.setForeground(new Color(0xa8a7a7));
        findTextField.setFont(textFieldFont);
        findTextField.setPreferredSize(new Dimension(180,30));
        findTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                findTextField.setText("");
                findTextField.setForeground(Color.BLACK);
            }
        });
        gbcSM.insets = new Insets(10,20,25,0);
        gbcSM.gridx = 3;
        gbcSM.gridy = 10;
        panelSM.add(findTextField, gbcSM);

        findButton = new JButton("Tìm kiếm");
        findButton.addActionListener(action);
        findButton.setPreferredSize(new Dimension(115,30));
        findButton.setFont(buttonFont);
        findButton.setBackground(new Color(0xeac086));
        findButton.setForeground(Color.blue);
        findButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
        gbcSM.insets = new Insets(10,0,25,0);
        gbcSM.anchor = GridBagConstraints.LINE_START;
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        fcTable = new JTable();
        fcTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[] {"Người dùng", "Mã người dùng","Tên đăng nhập", "Mật khẩu"}
        ));
//        stTable.setModel(studentTable);
        fcTable.setFont(new Font("Arial",Font.PLAIN,14));
        fcTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
        fcTable.setGridColor(new Color(0xbcbcbc));
        fcTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showAccountData(ConnJDBC.findAccountAll());
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

        fcTable.getColumnModel().getColumn(0).setMinWidth(120);
        fcTable.getColumnModel().getColumn(1).setMinWidth(120);
        fcTable.getColumnModel().getColumn(2).setMinWidth(110);
        fcTable.getColumnModel().getColumn(3).setMinWidth(110);
//        fcTable.getColumnModel().getColumn(2).setMinWidth(85);
//        fcTable.getColumnModel().getColumn(3).setMinWidth(60);
//        fcTable.getColumnModel().getColumn(5).setMinWidth(40);
        ((DefaultTableCellRenderer) fcTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(fcTable.getModel());
        fcTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(fcTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660,20));
//        scrollPane.setViewportView(stTable);

        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 3;
        gbcSM.gridheight = 9;
        gbcSM.gridx = 2;
        gbcSM.gridy = 1;
        panelSM.add(scrollPane, gbcSM);

        gbcSM.anchor = GridBagConstraints.FIRST_LINE_END;
        gbcSM.insets = new Insets(0,0,30,0);
        gbcSM.gridwidth = 5;
        gbcSM.gridheight = 1;
        gbcSM.gridx = 0;
        gbcSM.gridy = 0;
        panelSM.add(panelTop, gbcSM);

//        ----------------
//        panelSMV = new JPanel();
//        panelSMV.setLayout(new BorderLayout());
//        panelSMV.add(panelSM, BorderLayout.CENTER);
//        panelSMV.add(buttonPanel, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);

//-----Menu Bar--------
        JMenuBar menuBar = new JMenuBar();
        JMenu menuMenu = new JMenu("Menu");
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem studentMenuItem = new JMenuItem("Quản lý sinh viên");
        studentMenuItem.setPreferredSize(new Dimension(200, studentMenuItem.getPreferredSize().height));
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



//---------------------------------------------------

        this.setVisible(true);

        showAccountData(ConnJDBC.findAccountAll());
    }

    public void showAccountData(List<Account>accountL){
        List<Account> listAccount = new ArrayList<>();
        listAccount = accountL;
        DefaultTableModel tableModel;
        fcTable.getModel();
        tableModel = (DefaultTableModel) fcTable.getModel();
        tableModel.setRowCount(0);
        listAccount.forEach((account) -> {
            tableModel.addRow(new Object[]{
                    account.getRank(), account.getId_user(), account.getUsername(), account.getPassword()
            });
        });
    }



    public void refresh() {
        userTextField.setText("");
        passwTextField.setText("");
        showAccountData(ConnJDBC.findAccountAll());
    }



    public void saveFaculty() {
        try {
            if (String.valueOf(nameTextField.getText()).equals("") || String.valueOf(idTextField.getText()).equals("")
                    || String.valueOf(addressTextField.getText()).equals("")){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin của khoa!");
            } else {
                Faculty fc = new Faculty();
                fc.setId(idTextField.getText());
                fc.setName(nameTextField.getText());
                fc.setAddress(addressTextField.getText());
                ConnJDBC.insertFaculty(fc);
                JOptionPane.showMessageDialog(null, "Lưu thành công!");
                refresh();
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
        showAccountData(ConnJDBC.findAccountAll());
    }




    public void searchAccount() {
        Account ac = new Account();
        userTextField.setText(findTextField.getText());
        ac.setUsername(userTextField.getText());
        showAccountData(ConnJDBC.findAccount(ac));


    }

    public void updateAccount() {
        Account ac = new Account();
        ac.setUsername(userTextField.getText());
        ac.setPassword(passwTextField.getText());
        ConnJDBC.UpdateAccount(ac);
        JOptionPane.showMessageDialog(null, "Lưu thành công!");
        showAccountData(ConnJDBC.findAccountAll());
    }




    public void openSM() {
        StudentManagementView smView = new StudentManagementView();
        smView.setVisible(true);
        this.dispose();
    }

    public void openScore() {
        panelSM.setVisible(false);
    }

    public void logout() {
        JOptionPane.showMessageDialog(null, "Bạn muốn đăng xuất khỏi hệ thống?");
        LoginView lgView = new LoginView();
        lgView.setVisible(true);
        this.dispose();
    }

    public void openClassMN() {
        ClassesView clview = new ClassesView();
        clview.setVisible(true);
        this.dispose();
    }

    public void openSubject() {
        SubjectView sjview = new SubjectView();
        sjview.setVisible(true);
        this.dispose();
    }

    public void openTeacherV() {
        TeacherView tcview = new TeacherView();
        tcview.setVisible(true);
        this.dispose();
    }

    public void openaddST() {
        addSTSemesterView addview = new addSTSemesterView();
        addview.setVisible(true);
        this.dispose();
    }

    public void scoreClass() {
        ScoreView sciew = new ScoreView();
        sciew.setVisible(true);
        this.dispose();
    }
}
