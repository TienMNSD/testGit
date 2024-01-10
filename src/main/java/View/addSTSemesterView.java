package View;

import ConnData.ConnJDBC;
import Controller.addSTSemesterController;
import Model.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static ConnData.ConnJDBC.*;

public class addSTSemesterView extends JPanel {
    public static String nameLogin;

    private static JTable classesTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;
    private JButton saveButton;
    private JButton findButton;
    private JButton refreshButton;

    private JTextField semesterTextField;

    private JTextField findTextField;

    private JTextField idTextField;
    private JComboBox<String> facultyCBB;
    private JTextField roomTextField;
    private JTextField numberSTTextField;
    private JTextField idSubjectTextField;
    private JComboBox<Object> lecturersCBB;
    private JComboBox<String> semesterCBB;
    private JComboBox<Object> idClassCBB;

    private JTextField subjectTextField;
    private JComboBox<String> idStudentCBB;
    private JComboBox<String> classStudentCBB;
    private JTextField nameStudentTextField;

    private JButton addButton;
    private JButton exportFileButton;
    private JButton openFileButton;

    public addSTSemesterView() {
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }

    private void initStudentMN() {
        this.setPreferredSize(new Dimension(955, 465));

        Action action = new addSTSemesterController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        JPanel panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());


        Font inputFont = new Font("Arial", Font.BOLD, 13);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 13);
        Font buttonFont = new Font("Arial", 1, 12);


        gbcSM.insets = new Insets(10, 5, 10, 0);
        gbcSM.anchor = GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel facultyLabel = new JLabel("Khoa");
        facultyLabel.setFont(inputFont);
        facultyLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 0;
        panelSM.add(facultyLabel, gbcSM);

        List<String> arrayList = ConnJDBC.getFaculty();
        String[] array = new String[arrayList.size() + 1];
        array[0] = "_";
        for (int i = 0; i < array.length - 1; i++) {
            array[i + 1] = arrayList.get(i);
        }
        facultyCBB = new JComboBox<>(array);
        facultyCBB.setFont(textFieldFont);
        facultyCBB.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 0;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(facultyCBB, gbcSM);

        gbcSM.insets = new Insets(0, 5, 10, 0);
//        -----------------

        JLabel semesterLabel = new JLabel("Kỳ học");
        semesterLabel.setFont(inputFont);
        semesterLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(semesterLabel, gbcSM);

        List<String> arrayList1 = ConnJDBC.getSemester();
        List<String> arrayList2 = new ArrayList<String>();
        for (String i : arrayList1) {
            if (!arrayList2.contains(i)) {
                arrayList2.add(i);
            }
        }
        String[] array1 = new String[arrayList2.size() + 1];
        array1[0] = "_";
        for (int i = 0; i < array1.length - 1; i++) {
            array1[i + 1] = arrayList2.get(i);
        }

        semesterCBB = new JComboBox<>(array1);
        semesterCBB.setFont(textFieldFont);
        semesterCBB.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(semesterCBB, gbcSM);
//------------------

//                ------------------
        JLabel subjectLabel = new JLabel("Mã lớp");
        subjectLabel.setFont(inputFont);
        subjectLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(subjectLabel, gbcSM);

        idClassCBB = new JComboBox<>();
        idClassCBB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idClassCBB.removeAllItems();
                Classes c = new Classes();
                c.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
                c.setSemester(String.valueOf(semesterCBB.getSelectedItem()));
                List<String> arr1 = getIDClass(c);
                idClassCBB.addItem("_");
                for (int i = 0; i < arr1.size(); i++) {
                    idClassCBB.addItem(arr1.get(i));
                }

            }
        });
        idClassCBB.setFont(textFieldFont);
        idClassCBB.setPreferredSize(new Dimension(150, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(idClassCBB, gbcSM);

        addButton = new JButton("");
        addButton.addActionListener(action);
        addButton.setPreferredSize(new Dimension(30, 30));
        addButton.setFont(buttonFont);
        addButton.setForeground(Color.blue);
        addButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
//        addButton.setBackground(new Color(0xeac086));
        gbcSM.insets = new Insets(0, 155, 10, 0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(addButton, gbcSM);


        gbcSM.insets = new Insets(0, 5, 10, 0);

        JLabel SubjectLabel = new JLabel("Môn học");
        SubjectLabel.setFont(inputFont);
        SubjectLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(SubjectLabel, gbcSM);

        subjectTextField = new JTextField();
        subjectTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Classes c = new Classes();
                c.setId(String.valueOf(idClassCBB.getSelectedItem()));
                List<String> arrayList = getSubjectFromIDClass(c);
                String rs = arrayList.get(0);
                subjectTextField.setText(rs);
            }
        });
        subjectTextField.setFont(textFieldFont);
        subjectTextField.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(subjectTextField, gbcSM);

        JLabel classStudentLabel = new JLabel("Lớp sinh viên");
        classStudentLabel.setFont(inputFont);
        classStudentLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(classStudentLabel, gbcSM);

        List<String> arrayList3 = ConnJDBC.getCLassStudent();
        List<String> arrayList4 = new ArrayList<String>();
        for (String i : arrayList3) {
            if (!arrayList4.contains(i)) {
                arrayList4.add(i);
            }
        }
        String[] array3 = new String[arrayList4.size() + 1];
        array3[0] = "_";
        for (int i = 0; i < array3.length - 1; i++) {
            array3[i + 1] = arrayList4.get(i);
        }
        classStudentCBB = new JComboBox<>(array3);
        classStudentCBB.setFont(textFieldFont);
        classStudentCBB.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(classStudentCBB, gbcSM);

        //        --------input class---------
        JLabel idStudentLabel = new JLabel("Mã sinh viên");
        idStudentLabel.setFont(inputFont);
        idStudentLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(idStudentLabel, gbcSM);

        idStudentCBB = new JComboBox<>();
        idStudentCBB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idStudentCBB.removeAllItems();
                Student st = new Student();
                st.setClassStudent(String.valueOf(classStudentCBB.getSelectedItem()));
                List<String> arr1 = getIDStudent(st);
                Collections.sort(arr1);
                idStudentCBB.addItem("_");
                for (int i = 0; i < arr1.size(); i++) {
                    idStudentCBB.addItem(arr1.get(i));
                }

            }
        });
        idStudentCBB.setFont(textFieldFont);
        idStudentCBB.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
        panelSM.add(idStudentCBB, gbcSM);


        //        --------input Year----------
//        JLabel yearLabel = new JLabel("Kỳ học");
//        yearLabel.setFont(inputFont);
//        yearLabel.setPreferredSize(new Dimension(80,30));
//        gbcSM.gridx = 0;
//        gbcSM.gridy = 6;
//        panelSM.add(yearLabel, gbcSM);
//
//        semesterTextField = new JTextField();
//        semesterTextField.setFont(textFieldFont);
//        semesterTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 6;
//        panelSM.add(semesterTextField, gbcSM);


//        --------input major----------
        JLabel nameStudentLabel = new JLabel("Tên sinh viên");
        nameStudentLabel.setFont(inputFont);
        nameStudentLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(nameStudentLabel, gbcSM);

        nameStudentTextField = new JTextField();
        nameStudentTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Student st = new Student();
                st.setId(Integer.parseInt(String.valueOf(idStudentCBB.getSelectedItem())));
                List<String> arrayList = getNameStudent(st);
                String rs = arrayList.get(0);
                nameStudentTextField.setText(rs);
            }
        });
        nameStudentTextField.setFont(textFieldFont);
        nameStudentTextField.setPreferredSize(new Dimension(180, 30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 6;
        panelSM.add(nameStudentTextField, gbcSM);


//        --------input address----------
        JLabel midScoreLabel = new JLabel("");
        midScoreLabel.setFont(inputFont);
        midScoreLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(midScoreLabel, gbcSM);

//        midScoreTextField = new JTextField();
//        midScoreTextField.setFont(textFieldFont);
//        midScoreTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 8;
//        panelSM.add(midScoreTextField, gbcSM);

        //        --------input email----------
        JLabel endScoreLabel = new JLabel("");
        endScoreLabel.setFont(inputFont);
        endScoreLabel.setPreferredSize(new Dimension(90, 30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(endScoreLabel, gbcSM);

        JLabel nullLabel = new JLabel("");
        nullLabel.setFont(inputFont);
        nullLabel.setPreferredSize(new Dimension(80, 20));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(nullLabel, gbcSM);

//        endScoreTextField = new JTextField();
//        endScoreTextField.setFont(textFieldFont);
//        endScoreTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 9;
//        panelSM.add(endScoreTextField, gbcSM);

        gbcSM.anchor = GridBagConstraints.CENTER;
        //        --------Button---------
        saveButton = new JButton("Thêm");
        saveButton.addActionListener(action);
        saveButton.setPreferredSize(new Dimension(90, 30));
        saveButton.setFont(buttonFont);
        saveButton.setBackground(new Color(0xeac086));
        saveButton.setForeground(Color.blue);
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
        gbcSM.insets = new Insets(15, 10, 20, 0);
        gbcSM.gridx = 0;
        gbcSM.gridy = 10;
        panelSM.add(saveButton, gbcSM);

//        deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(action);
//        deleteButton.setPreferredSize(new Dimension(100,50));
//        deleteButton.setFont(inputFont);

        refreshButton = new JButton("Làm mới");
        refreshButton.addActionListener(action);
        refreshButton.setPreferredSize(new Dimension(110, 30));
        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(new Color(0xeac086));
        refreshButton.setForeground(Color.blue);
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/images/reload.png")));
        gbcSM.insets = new Insets(15, 10, 20, 0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 10;
        panelSM.add(refreshButton, gbcSM);

        openFileButton = new JButton("Mở file");
        openFileButton.addActionListener(action);
        openFileButton.setPreferredSize(new Dimension(110, 30));
        openFileButton.setFont(buttonFont);
        openFileButton.setBackground(new Color(0xeac086));
        openFileButton.setForeground(Color.blue);
        openFileButton.setIcon(new ImageIcon(getClass().getResource("/images/File.png")));
        gbcSM.insets = new Insets(15, 0, 20, 0);
        gbcSM.gridx = 2;
        gbcSM.gridy = 10;
        panelSM.add(openFileButton, gbcSM);

        exportFileButton = new JButton("Xuất file");
        exportFileButton.addActionListener(action);
        exportFileButton.setPreferredSize(new Dimension(110, 30));
        exportFileButton.setFont(buttonFont);
        exportFileButton.setBackground(new Color(0xeac086));
        exportFileButton.setForeground(Color.blue);
        exportFileButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
        gbcSM.insets = new Insets(15, 30, 20, 0);
        gbcSM.gridx = 3;
        gbcSM.gridy = 10;
        panelSM.add(exportFileButton, gbcSM);

        //        -------------
        findTextField = new JTextField("Nhập tên hoặc mã sinh viên...");
        findTextField.setForeground(new Color(0xa8a7a7));
        findTextField.setFont(textFieldFont);
        findTextField.setPreferredSize(new Dimension(180, 30));
        findTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findTextField.setText("");
                findTextField.setForeground(Color.BLACK);
            }
        });
        gbcSM.insets = new Insets(15, 50, 20, 0);
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(findTextField, gbcSM);

        gbcSM.anchor = GridBagConstraints.LINE_START;
        findButton = new JButton("Tìm kiếm");
        findButton.addActionListener(action);
        findButton.setPreferredSize(new Dimension(115, 29));
        findButton.setFont(buttonFont);
        findButton.setBackground(new Color(0xeac086));
        findButton.setForeground(Color.blue);
        findButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
        gbcSM.insets = new Insets(15, 0, 20, 0);
        gbcSM.gridx = 5;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);

        gbcSM.anchor = GridBagConstraints.CENTER;
        classesTable = new JTable();
        classesTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String[]{"STT", "Mã lớp", "Môn học", "Mã sinh viên",
                        "Tên sinh viên", "Lớp sinh viên"}
        ));
//        stTable.setModel(studentTable);
        classesTable.setFont(new Font("Arial", Font.PLAIN, 12));
        classesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        classesTable.setGridColor(new Color(0xbcbcbc));
        classesTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        classesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < classesTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < classesTable.getRowCount(); row++) {
                TableCellRenderer renderer = classesTable.getCellRenderer(row, column);
                Component comp = classesTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 2, width);
            }
            if (width > 300)
                width = 300;
            classesTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        classesTable.getColumnModel().getColumn(0).setMinWidth(60);
        classesTable.getColumnModel().getColumn(1).setMinWidth(80);
        classesTable.getColumnModel().getColumn(2).setMinWidth(180);
        classesTable.getColumnModel().getColumn(3).setMinWidth(100);
        classesTable.getColumnModel().getColumn(4).setMinWidth(140);
        classesTable.getColumnModel().getColumn(5).setMinWidth(100);
        ((DefaultTableCellRenderer) classesTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(classesTable.getModel());
        classesTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(classesTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660, 20));
//        scrollPane.setViewportView(stTable);

        gbcSM.insets = new Insets(0, 10, 10, 0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 4;
        gbcSM.gridheight = 10;
        gbcSM.gridx = 2;
        gbcSM.gridy = 0;
        panelSM.add(scrollPane, gbcSM);

        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);
    }


    public void showDataClass(List<AddStudent> as) {
        List<AddStudent> listStudent = new ArrayList<>();
        listStudent = as;
        DefaultTableModel tableClassModel;
        classesTable.getModel();
        tableClassModel = (DefaultTableModel) classesTable.getModel();
        tableClassModel.setRowCount(0);
        listStudent.forEach((st1) -> {
            tableClassModel.addRow(new Object[]{
                    st1.getStt(),
                    st1.getId_class(), st1.getSubject(), st1.getId_student(),
                    st1.getName_student(), st1.getClass_student()
            });
        });
    }

    public void refresh() {
        idStudentCBB.setSelectedIndex(-1);
        classStudentCBB.setSelectedIndex(-1);
        nameStudentTextField.setText("");
        Classes cl = new Classes();
        cl.setId(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllAddClass(cl));
    }

    public void addStudent() {
        try {
            if (idClassCBB.getSelectedIndex() == 0 || String.valueOf(subjectTextField.getText()).equals("")
                    || idStudentCBB.getSelectedIndex() == 0 || String.valueOf(nameStudentTextField.getText()).equals("")
                    || classStudentCBB.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin lớp học!");
            } else {
                Classes cl = new Classes();
                Student st = new Student();
                cl.setId(String.valueOf(idClassCBB.getSelectedItem()));
                cl.setSubject(subjectTextField.getText());
                st.setId(Integer.parseInt(String.valueOf(idStudentCBB.getSelectedItem())));
                st.setName(nameStudentTextField.getText());
                st.setClassStudent(String.valueOf(classStudentCBB.getSelectedItem()));
                ConnJDBC.insertStudentToClassSemester(cl, st);
                JOptionPane.showMessageDialog(null, "Lưu thành công! ");
                refresh();
            }
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
        Classes cl = new Classes();
        cl.setId(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllAddClass(cl));
    }


    public void searchClass() {
        Classes cl = new Classes();
        if (findTextField.getText().length() == 8) {
            idTextField.setText(findTextField.getText());
            int idInt = Integer.parseInt(findTextField.getText());
            cl.setId(findTextField.getText());
//            showDataClass(ConnJDBC.findClassByID(cl));

        } else {
            idTextField.setText("");
            cl.setSubject(findTextField.getText());
            idClassCBB.setSelectedItem(findTextField.getText());
//            showDataClass(ConnJDBC.findClassByName(cl));
        }

    }

    public void updateClass() {
        Classes cl = new Classes();
        cl.setId(idTextField.getText());
        cl.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
        cl.setSubject(String.valueOf(idClassCBB.getSelectedItem()));
        cl.setIdsubject(idSubjectTextField.getText());
        cl.setLecturers(String.valueOf(lecturersCBB.getSelectedItem()));
        cl.setSemester(semesterTextField.getText());
        cl.setRoom(roomTextField.getText());
        cl.setNumOfStudent(numberSTTextField.getText());
        ConnJDBC.UpdateClass(cl);
        JOptionPane.showMessageDialog(null, "Lưu thành công! ");
        cl.setId(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllAddClass(cl));
    }


    public void showAll() {
        Classes c = new Classes();
        c.setId(String.valueOf(idClassCBB.getSelectedItem()));
        List<String> arrayList = getSubjectFromIDClass(c);
        String rs = arrayList.get(0);
        subjectTextField.setText(rs);
        Classes cl = new Classes();
        cl.setId(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllAddClass(cl));
    }

    public void readFile() {
        List<AddStudent> studentList = new ArrayList<>();
        Classes cl = new Classes();
        if(semesterCBB.getSelectedItem() == "_"){
            JOptionPane.showMessageDialog(null, "Hãy nhập mã lớp!");
        }
        else {}
        cl.setId(idClassCBB.getSelectedItem().toString());
        String fileDictName = "";
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        fc.setFileFilter(fnef);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileDictName = fc.getSelectedFile().getAbsolutePath();
        }
        File file = new File(fileDictName);
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                AddStudent addSt = new AddStudent();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            String idclass = NumberToTextConverter.toText(cell.getNumericCellValue());
                            addSt.setId_class(idclass);
                        case 1:
                            addSt.setSubject(cell.toString());
                        case 2:
                            DataFormatter dataFormatter = new DataFormatter();
                            String formattedCellStr = dataFormatter.formatCellValue(cell);
                            addSt.setId_student(formattedCellStr);
                        case 3:
                            addSt.setName_student(cell.toString());
                        case 4:
                            addSt.setClass_student(cell.toString());
                        default: {
                        }
                        cellIndex++;
                    }
                }
                studentList.add(addSt);
//                ConnJDBC.creatTranscriptStudent(st);
                }
            ConnJDBC.insertStudentClassExcels(5, studentList, cl);
//            ConnJDBC.insertAccountExcels(200,accountList);
            JOptionPane.showMessageDialog(null, "Lưu thành công!");
            refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile() throws IOException {
        List<Student> stList = new ArrayList<>(ConnJDBC.findAll());
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        fileChooser.setFileFilter(fnef);
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            file = new File(file.getParentFile(), file.getName() + ".xlsx");
        }
        FileOutputStream file2 = new FileOutputStream(file);
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        SXSSFSheet sheet = workbook.createSheet("Books");
        sheet.trackAllColumnsForAutoSizing();
        CellStyle cellStyle = createStyleForHeader(sheet);
        int rowIndex = 0;
        SXSSFRow row = sheet.createRow(rowIndex);
        SXSSFCell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("MSSV");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Họ và tên");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Lớp");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Khóa");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Khoa");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
        rowIndex++;
        for (Student st : stList) {
            row = sheet.createRow(rowIndex);
            writeStudent(st, row);
            rowIndex++;
        }
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        workbook.write(file2);
        JOptionPane.showMessageDialog(null, "Lưu file thành công!");
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(false);
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        return cellStyle;
    }

    private static void writeStudent(Student st, SXSSFRow row) {
        SXSSFCell cell = row.createCell(0);
        cell.setCellValue(st.getId());

        cell = row.createCell(1);
        cell.setCellValue(st.getName());

        cell = row.createCell(2);
        cell.setCellValue(st.getDateOfBirth());

        cell = row.createCell(3);
        cell.setCellValue(st.getGender());

        cell = row.createCell(4);
        cell.setCellValue(st.getClassStudent());

        cell = row.createCell(5);
        cell.setCellValue(st.getYear());

        cell = row.createCell(6);
        cell.setCellValue(st.getMajor());

        cell = row.createCell(7);
        cell.setCellValue(st.getAddress());

        cell = row.createCell(8);
        cell.setCellValue(st.getEmail());
    }

}
