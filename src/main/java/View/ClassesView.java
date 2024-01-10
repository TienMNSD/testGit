package View;

import ConnData.ConnJDBC;
import Controller.ClassesController;
import Model.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static ConnData.ConnJDBC.*;

public class ClassesView extends JPanel {
    public static String nameLogin;

    private static JTable classesTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JComboBox<String> genderCBB;
    private JTextField classTextField;
    private JTextField yearTextField;
    private JTextField majorTextField;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JPanel panelSMV;
    private JPanel panelClassMNV;

    private JTextField semesterTextField;

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
    private JComboBox<String> facultyCBB;
    private JTextField roomTextField;
    private JTextField numberSTTextField;
    private JComboBox<Object> subjectCBB;
    private JTextField idSubjectTextField;
    private JComboBox<Object> lecturersCBB;


    public ClassesView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new ClassesController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());

        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        gbcSM.insets = new Insets(10,10,10,0);
        gbcSM.anchor=GridBagConstraints.LINE_START;

        //      --------input ID----------
        JLabel idLabel = new JLabel("Mã lớp học");
        idLabel.setFont(inputFont);
        idLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(idLabel, gbcSM);

        idTextField = new JTextField();
        idTextField.setFont(textFieldFont);
        idTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        panelSM.add(idTextField, gbcSM);

        gbcSM.insets = new Insets(0,10,10,0);

//        --------input name----------
        JLabel facultyLabel = new JLabel("Khoa");
        facultyLabel.setFont(inputFont);
        facultyLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(facultyLabel, gbcSM);

        roomTextField = new JTextField();
        roomTextField.setFont(textFieldFont);
        roomTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 7;
        panelSM.add(roomTextField, gbcSM);

//        --------input age----------

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
        subjectCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.insets = new Insets(0,10,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
//        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(subjectCBB, gbcSM);


//        --------input gender----------
        JLabel subjectLabel = new JLabel("Môn học");
        subjectLabel.setFont(inputFont);
        subjectLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(subjectLabel, gbcSM);

        JLabel idSubjectLabel = new JLabel("Mã môn học");
        idSubjectLabel.setFont(inputFont);
        idSubjectLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(idSubjectLabel, gbcSM);

        idSubjectTextField = new JTextField();
        idSubjectTextField.setFont(textFieldFont);
        idSubjectTextField.setPreferredSize(new Dimension(180,30));
        idSubjectTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                Subject s = new Subject();
                s.setName(String.valueOf(subjectCBB.getSelectedItem()));
                List<String> arrayList = getidSubject(s);
                String rs = arrayList.get(0);
                idSubjectTextField.setText(rs);
            }
        });
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        panelSM.add(idSubjectTextField, gbcSM);

        //        --------input class---------
        JLabel lecturersLabel = new JLabel("Giảng viên");
        lecturersLabel.setFont(inputFont);
        lecturersLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(lecturersLabel, gbcSM);

        lecturersCBB = new JComboBox<>();
        lecturersCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                lecturersCBB.removeAllItems();
                Teacher t = new Teacher();
                t.setSubject(String.valueOf(subjectCBB.getSelectedItem()));
                List<String> arrayList2 = getTeacherClass(t);
                String eg = arrayList2.get(0);
                String[] arrayList3 = eg.split(", ");
                lecturersCBB.addItem("_");
                for(int i = 0; i < arrayList3.length; i++) {
                    lecturersCBB.addItem(arrayList3[i]);
                }

            }
        });
        lecturersCBB.setFont(textFieldFont);
        lecturersCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.insets = new Insets(0,10,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
//        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(lecturersCBB, gbcSM);

        //        --------input Year----------
        JLabel yearLabel = new JLabel("Kỳ học");
        yearLabel.setFont(inputFont);
        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(yearLabel, gbcSM);

        semesterTextField = new JTextField();
        semesterTextField.setFont(textFieldFont);
        semesterTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 6;
        panelSM.add(semesterTextField, gbcSM);


//        --------input major----------
        JLabel roomLabel = new JLabel("Phòng học");
        roomLabel.setFont(inputFont);
        roomLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(roomLabel, gbcSM);

        List<String> arrayList = ConnJDBC.getFaculty();
        String[] array = new String[arrayList.size()+1];
        array[0] = "_";
        for(int i = 0; i < array.length-1; i++) {
            array[i+1] = arrayList.get(i);
        }
        facultyCBB = new JComboBox<>(array);
        facultyCBB.setFont(textFieldFont);
        facultyCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.insets = new Insets(0,10,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(facultyCBB, gbcSM);

//        --------input address----------
        JLabel numberSTLabel = new JLabel("Số lượng SV");
        numberSTLabel.setFont(inputFont);
        numberSTLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(numberSTLabel, gbcSM);

        numberSTTextField = new JTextField();
        numberSTTextField.setFont(textFieldFont);
        numberSTTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 8;
        panelSM.add(numberSTTextField, gbcSM);

        //        --------input email----------
        JLabel emailLabel = new JLabel("");
        emailLabel.setFont(inputFont);
        emailLabel.setPreferredSize(new Dimension(80,60));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(emailLabel, gbcSM);

        gbcSM.anchor = GridBagConstraints.CENTER;
        //        --------Button---------
        saveButton = new JButton("Lưu");
        saveButton.addActionListener(action);
        saveButton.setPreferredSize(new Dimension(90,30));
        saveButton.setFont(buttonFont);
        saveButton.setBackground(new Color(0xeac086));
        saveButton.setForeground(Color.blue);
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
        gbcSM.insets = new Insets(15,10,20,0);
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
        gbcSM.insets = new Insets(15,10,20,0);
        gbcSM.gridx = 2;
        gbcSM.gridy = 10;
        panelSM.add(refreshButton, gbcSM);


        //        ------CBB gender-------

        gbcSM.anchor = GridBagConstraints.LINE_START;
        findTextField = new JTextField("Nhập tên hoặc mã lớp học...");
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
        gbcSM.insets = new Insets(15,0,20,0);
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        classesTable = new JTable();
        classesTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                },
                new String[] {"Mã lớp", "Khoa","Môn học","Mã môn học",
                            "Giảng viên", "Kỳ học", "Phòng học","Sỹ số"}
        ));
//        stTable.setModel(studentTable);
        classesTable.setFont(new Font("Arial",Font.PLAIN,12));
        classesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,12));
        classesTable.setGridColor(new Color(0xbcbcbc));
        classesTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showDataClass(ConnJDBC.findAllClass());
        classesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < classesTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < classesTable.getRowCount(); row++) {
                TableCellRenderer renderer = classesTable.getCellRenderer(row, column);
                Component comp = classesTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +2 , width);
            }
            if(width > 300)
                width=300;
            classesTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        classesTable.getColumnModel().getColumn(0).setMinWidth(60);
        classesTable.getColumnModel().getColumn(3).setMinWidth(80);
        classesTable.getColumnModel().getColumn(5).setMinWidth(55);
        classesTable.getColumnModel().getColumn(6).setMinWidth(70);
        classesTable.getColumnModel().getColumn(7).setMinWidth(40);
        ((DefaultTableCellRenderer) classesTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(classesTable.getModel());
        classesTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(classesTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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

        showDataClass(ConnJDBC.findAllClass());
    }


    public void showDataClass(List<Classes> classses){
        List<Classes> listClasses = new ArrayList<>();
        listClasses = classses;
        DefaultTableModel tableClassModel;
        classesTable.getModel();
        tableClassModel = (DefaultTableModel)classesTable.getModel();
        tableClassModel.setRowCount(0);
        listClasses.forEach((class1) -> {
            tableClassModel.addRow(new Object[]{
                    class1.getId(), class1.getFaculty(), class1.getSubject(),
                    class1.getIdsubject(),class1.getLecturers(), class1.getSemester(),
                    class1.getRoom(), class1.getNumOfStudent()
            });
        });
    }

    public void refresh() {
        idTextField.setText("");
        facultyCBB.setSelectedIndex(-1);
        subjectCBB.setSelectedIndex(-1);
        idSubjectTextField.setText("");
        lecturersCBB.setSelectedIndex(-1);
        semesterTextField.setText("");
        roomTextField.setText("");
        numberSTTextField.setText("");
        showDataClass(ConnJDBC.findAllClass());
    }

    public void saveClass() {
        try {
            if (String.valueOf(idTextField.getText()).equals("") || String.valueOf(idSubjectTextField.getText()).equals("")
                     || String.valueOf(semesterTextField.getText()).equals("") || String.valueOf(roomTextField.getText()).equals("")
                    || String.valueOf(numberSTTextField.getText()).equals("") || facultyCBB.getSelectedIndex()==0
                    || subjectCBB.getSelectedIndex()==0 || lecturersCBB.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin lớp học!");
            } else {
                Classes cl = new Classes();
                cl.setId(idTextField.getText());
                cl.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
                cl.setSubject(String.valueOf(subjectCBB.getSelectedItem()));
                cl.setIdsubject(idSubjectTextField.getText());
                cl.setLecturers(String.valueOf(lecturersCBB.getSelectedItem()));
                cl.setSemester(semesterTextField.getText());
                cl.setRoom(roomTextField.getText());
                cl.setNumOfStudent(numberSTTextField.getText());
                ConnJDBC.insertClass(cl);
                ConnJDBC.creatTableClass(cl);
                JOptionPane.showMessageDialog(null, "Lưu thành công! ");
                refresh();
            }
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
        showDataClass(ConnJDBC.findAllClass());
    }



    public void searchClass() {
        Classes cl = new Classes();
        if (findTextField.getText().length() == 8){
            idTextField.setText(findTextField.getText());
            int idInt = Integer.parseInt(findTextField.getText());
            cl.setId(findTextField.getText());
            showDataClass(ConnJDBC.findClassByID(cl));

        } else {
            idTextField.setText("");
            cl.setSubject(findTextField.getText());
            subjectCBB.setSelectedItem(findTextField.getText());
            showDataClass(ConnJDBC.findClassByName(cl));
        }

    }

    public void updateClass() {
        Classes cl = new Classes();
        cl.setId(idTextField.getText());
        cl.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
        cl.setSubject(String.valueOf(subjectCBB.getSelectedItem()));
        cl.setIdsubject(idSubjectTextField.getText());
        cl.setLecturers(String.valueOf(lecturersCBB.getSelectedItem()));
        cl.setSemester(semesterTextField.getText());
        cl.setRoom(roomTextField.getText());
        cl.setNumOfStudent(numberSTTextField.getText());
        ConnJDBC.UpdateClass(cl);
        JOptionPane.showMessageDialog(null, "Lưu thành công! ");
        showDataClass(ConnJDBC.findAllClass());
    }
}
