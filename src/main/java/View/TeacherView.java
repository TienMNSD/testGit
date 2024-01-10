package View;

import ConnData.ConnJDBC;
import Controller.TeacherController;
import Model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import static ConnData.ConnJDBC.getSubject;

public class TeacherView extends JPanel {
    public static String nameLogin;

    private static JTable tcTable;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField creditTextField;
    private JButton saveButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JTextField findTextField;
    private JLabel idloginLabel;
    private JPanel panelSM;
    private JTextField idTextField;
    private JComboBox facultyCBB;
    private JComboBox<String> subjectCBB;
    private JTextArea subjectTextArea;
    private JTextField phonenumberTextField;
    private JTextField emailTextField;
    private JTextField birthTextField;
    private JButton addButton;


    public TeacherView(){
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new TeacherController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());


        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font fontTop = new Font("Verdana",Font.BOLD,28);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.gray);

        gbcSM.insets = new Insets(10,5,10,0);
        gbcSM.anchor=GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel idLabel = new JLabel("Mã giảng viên");
        idLabel.setFont(inputFont);
        idLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(idLabel, gbcSM);

        idTextField = new JTextField();
        idTextField.setFont(textFieldFont);
        idTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        panelSM.add(idTextField, gbcSM);

        gbcSM.insets = new Insets(0,5,10,0);

//        --------input name----------
        JLabel nameLabel = new JLabel("Tên GV");
        nameLabel.setFont(inputFont);
        nameLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(nameLabel, gbcSM);

        nameTextField = new JTextField();
        nameTextField.setFont(textFieldFont);
        nameTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(nameTextField, gbcSM);

//        --------input age----------
        JLabel birthLabel = new JLabel("Ngày sinh");
        birthLabel.setFont(inputFont);
        birthLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(birthLabel, gbcSM);

        birthTextField = new JTextField();
        birthTextField.setFont(textFieldFont);
        birthTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(birthTextField, gbcSM);
//
////        --------input gender----------
        JLabel facultyLabel = new JLabel("Khoa");
        facultyLabel.setFont(inputFont);
        facultyLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(facultyLabel, gbcSM);
//
//        //        --------input class---------
        JLabel lecturersLabel = new JLabel("Môn dạy");
        lecturersLabel.setFont(inputFont);
        lecturersLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(lecturersLabel, gbcSM);

        JLabel exLabel = new JLabel("");
        exLabel.setFont(inputFont);
        exLabel.setPreferredSize(new Dimension(90,1));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(exLabel, gbcSM);
//

//
//        //        --------input Year----------
        JLabel phonenumberLabel = new JLabel("Số ĐT");
        phonenumberLabel.setFont(inputFont);
        phonenumberLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(phonenumberLabel, gbcSM);


        phonenumberTextField = new JTextField();
        phonenumberTextField.setFont(textFieldFont);
        phonenumberTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
        panelSM.add(phonenumberTextField, gbcSM);
//

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(inputFont);
        emailLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(emailLabel, gbcSM);

        emailTextField = new JTextField();
        emailTextField.setFont(textFieldFont);
        emailTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 6;
        panelSM.add(emailTextField, gbcSM);

        subjectTextArea = new JTextArea();
        subjectTextArea.setFont(textFieldFont);
        subjectTextArea.setPreferredSize(new Dimension(180,70));
        subjectTextArea.setLineWrap(true);
        subjectTextArea.setBorder(blackline);
        gbcSM.insets = new Insets(0,5,0,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 8;
        panelSM.add(subjectTextArea, gbcSM);

        JLabel nullLabel = new JLabel("");
        nullLabel.setFont(inputFont);
        nullLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(nullLabel, gbcSM);




        //        --------Button---------
        saveButton = new JButton("Lưu");
        saveButton.addActionListener(action);
        saveButton.setPreferredSize(new Dimension(90,30));
        saveButton.setFont(buttonFont);
        saveButton.setBackground(new Color(0xeac086));
        saveButton.setForeground(Color.blue);
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
        gbcSM.insets = new Insets(15,5,20,0);
        gbcSM.gridx = 0;
        gbcSM.gridy = 10;
        panelSM.add(saveButton, gbcSM);

        updateButton = new JButton("Cập nhật");
        updateButton.addActionListener(action);
        updateButton.setPreferredSize(new Dimension(105,30));
        updateButton.setFont(buttonFont);
        updateButton.setBackground(new Color(0xeac086 ));
        updateButton.setForeground(Color.blue);
        updateButton.setIcon(new ImageIcon(getClass().getResource("/images/Update.png")));
        gbcSM.gridx = 1;
        gbcSM.gridy = 10;
        panelSM.add(updateButton, gbcSM);

        refreshButton = new JButton("Làm mới");
        refreshButton.addActionListener(action);
        refreshButton.setPreferredSize(new Dimension(110,30));
        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(new Color(0xeac086));
        refreshButton.setForeground(Color.blue);
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/images/reload.png")));
        gbcSM.insets = new Insets(15,5,20,0);
        gbcSM.gridx = 2;
        gbcSM.gridy = 10;
        panelSM.add(refreshButton, gbcSM);
//
//
//
//
//
        //        ------CBB gender-------
        List<String> arrayList = ConnJDBC.getFaculty();
        String[] array = new String[arrayList.size()+1];
        array[0] = "_";
        for(int i = 0; i < array.length-1; i++) {
            array[i+1] = arrayList.get(i);
        }
        facultyCBB = new JComboBox<>(array);
        facultyCBB.setFont(textFieldFont);
        facultyCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(facultyCBB, gbcSM);

        addButton = new JButton("");
        addButton.addActionListener(action);
        addButton.setPreferredSize(new Dimension(30,30));
        addButton.setFont(buttonFont);
        addButton.setForeground(Color.blue);
        addButton.setIcon(new ImageIcon(getClass().getResource("/images/new.png")));
        gbcSM.insets = new Insets(0,155,0,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 7;
        panelSM.add(addButton, gbcSM);

        subjectCBB = new JComboBox<>();
        subjectCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                subjectCBB.removeAllItems();
                Subject s = new Subject();
                s.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
                List<String> arrayList1 = getSubject(s);
                subjectCBB.addItem("_");
                for(int i = 0; i < arrayList1.size(); i++) {
                    subjectCBB.addItem(arrayList1.get(i));
                }

            }
        });
        subjectCBB.setFont(textFieldFont);
        subjectCBB.setPreferredSize(new Dimension(150,30));
        gbcSM.insets = new Insets(0,5,0,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 7;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(subjectCBB, gbcSM);

        findTextField = new JTextField("Nhập tên hoặc mã giảng viên...");
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
        gbcSM.insets = new Insets(15,50,20,0);
        gbcSM.gridx = 3;
        gbcSM.gridy = 10;
        panelSM.add(findTextField, gbcSM);

        findButton = new JButton("Tìm kiếm");
        findButton.addActionListener(action);
        findButton.setPreferredSize(new Dimension(115,29));
        findButton.setFont(buttonFont);
        findButton.setBackground(new Color(0xeac086));
        findButton.setForeground(Color.blue);
        findButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
        gbcSM.insets = new Insets(15,1,20,200);
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        tcTable = new JTable();
        tcTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                new String[] {"Mã giảng viên", "Tên GV","Ngày sinh", "Khoa", "Số ĐT", "Email", "Môn dạy"}
        ));
//        stTable.setModel(studentTable);
        tcTable.setFont(new Font("Arial",Font.PLAIN,14));
        tcTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
        tcTable.setGridColor(new Color(0xbcbcbc));
        tcTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showTeacherData(ConnJDBC.findTeacherAll());
        tcTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < tcTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < tcTable.getRowCount(); row++) {
                TableCellRenderer renderer = tcTable.getCellRenderer(row, column);
                Component comp = tcTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +4 , width);
            }
            if(width > 300)
                width=300;
            tcTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        tcTable.getColumnModel().getColumn(0).setMinWidth(110);
        ((DefaultTableCellRenderer) tcTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tcTable.getModel());
        tcTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(tcTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660,20));

        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 3;
        gbcSM.gridheight = 9;
        gbcSM.gridx = 2;
        gbcSM.gridy = 1;
        panelSM.add(scrollPane, gbcSM);


        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);

        showTeacherData(ConnJDBC.findTeacherAll());
    }

    public void showTeacherData(List<Teacher>teacherL){
        List<Teacher> listTeacher = new ArrayList<>();
        listTeacher = teacherL;
        DefaultTableModel tableModel;
        tcTable.getModel();
        tableModel = (DefaultTableModel) tcTable.getModel();
        tableModel.setRowCount(0);
        listTeacher.forEach((teacher) -> {
            tableModel.addRow(new Object[]{
                    teacher.getId(), teacher.getName(), teacher.getBirth(), teacher.getFaculty(), teacher.getPhone(),
                    teacher.getEmail(), teacher.getSubject()
            });
        });
    }



    public void refresh() {
        idTextField.setText("");
        nameTextField.setText("");
        birthTextField.setText("");
        emailTextField.setText("");
        phonenumberTextField.setText("");
        facultyCBB.setSelectedIndex(-1);
        subjectCBB.setSelectedIndex(-1);
        subjectTextArea.setText("");

        showTeacherData(ConnJDBC.findTeacherAll());
    }



    public void saveTeacher() {
        try {
            if (String.valueOf(nameTextField.getText()).equals("") || String.valueOf(idTextField.getText()).equals("")
                    || String.valueOf(birthTextField.getText()).equals("") || facultyCBB.getSelectedIndex()==0
                    || String.valueOf(subjectTextArea.getText()).equals("") || String.valueOf(phonenumberTextField.getText()).equals("")
                    || String.valueOf(emailTextField.getText()).equals("")){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin của giảng viên!");
            } else {
                Teacher tc = new Teacher();
                tc.setId(idTextField.getText());
                tc.setName(nameTextField.getText());
                tc.setBirth(birthTextField.getText());
                tc.setFaculty(String.valueOf(facultyCBB.getItemAt(facultyCBB.getSelectedIndex())));
                tc.setPhone(phonenumberTextField.getText());
                tc.setEmail(emailTextField.getText());
                tc.setSubject(subjectTextArea.getText());
                ConnJDBC.insertTeacher(tc);
                saveAccountTC();
                JOptionPane.showMessageDialog(null, "Lưu thành công!");
                refresh();
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
        showTeacherData(ConnJDBC.findTeacherAll());
    }

    public void saveAccountTC() {
        try {
            Account ac = new Account();
            ac.setRank("Giảng viên");
            ac.setId_user(idTextField.getText());
            ac.setPassword(idTextField.getText());
            ac.setUsername(idTextField.getText());
            ConnJDBC.insertAccountST(ac);
            refresh();
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }


    public void searchTeacher() {
        Teacher tc = new Teacher();
        if (findTextField.getText().length() == 6){
            idTextField.setText(findTextField.getText());
            nameTextField.setText("");
            tc.setId(findTextField.getText());
            showTeacherData(ConnJDBC.findTeacherID(tc));

        } else {
            tc.setName(findTextField.getText());
            nameTextField.setText(findTextField.getText());
            idTextField.setText("");
            showTeacherData(ConnJDBC.findTeacherName(tc));
        }

    }

    public void updateSubject() {
        Subject sj = new Subject();
        sj.setId(idTextField.getText());
        sj.setName(nameTextField.getText());
        sj.setCredit(creditTextField.getText());
        sj.setFaculty(String.valueOf(facultyCBB.getItemAt(facultyCBB.getSelectedIndex())));
        sj.setLecturers(subjectTextArea.getText());
        ConnJDBC.UpdateSubject(sj);
        JOptionPane.showMessageDialog(null, "Save success! ");
        showTeacherData(ConnJDBC.findTeacherAll());
    }


    public void addSubject() {
        if (String.valueOf(subjectTextArea.getText()).equals("")) {
            subjectTextArea.append(String.valueOf(subjectCBB.getSelectedItem()));
        } else {
            subjectTextArea.append(", " + String.valueOf(subjectCBB.getSelectedItem()));
        }
    }
}
