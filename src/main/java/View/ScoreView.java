package View;

import ConnData.ConnJDBC;
import Controller.*;
import Model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.List;
import java.util.*;

import static ConnData.ConnJDBC.*;

public class ScoreView extends JPanel {
    public static String nameLogin;

    private static JTable classesTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;
    private JButton saveButton;

    private JButton findButton;

    private JButton refreshButton;

    private JTextField semesterTextField;

    private JTextField findTextField;
    private JLabel idloginLabel;
    private JPanel panelSM;
    private JPanel panelTop;
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
    private JTextField midScoreTextField;
    private JTextField endScoreTextField;
    private JButton addButton;
    private JTextField weightTextField;


    public ScoreView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new ScoreController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());


        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);



        gbcSM.insets = new Insets(10,5,10,0);
        gbcSM.anchor=GridBagConstraints.LINE_START;

        //      --------input ID----------
        JLabel facultyLabel = new JLabel("Khoa");
        facultyLabel.setFont(inputFont);
        facultyLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(facultyLabel, gbcSM);

        List<String> arrayList = ConnJDBC.getFaculty();
        String[] array = new String[arrayList.size()+1];
        array[0] = "_";
        for(int i = 0; i < array.length-1; i++) {
            array[i+1] = arrayList.get(i);
        }
        facultyCBB = new JComboBox<>(array);
        facultyCBB.setFont(textFieldFont);
        facultyCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(facultyCBB, gbcSM);

        gbcSM.insets = new Insets(0,5,10,0);

        JLabel semesterLabel = new JLabel("Kỳ học");
        semesterLabel.setFont(inputFont);
        semesterLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(semesterLabel, gbcSM);

        List<String> arrayList1 = ConnJDBC.getSemester();
        List<String> arrayList2 = new ArrayList<String>();
        for (String i: arrayList1) {
            if (!arrayList2.contains(i)) {
                arrayList2.add(i);
            }
        }
        String[] array1 = new String[arrayList2.size()+1];
        array1[0] = "_";
        for(int i = 0; i < array1.length-1; i++) {
            array1[i+1] = arrayList2.get(i);
        }

        semesterCBB = new JComboBox<>(array1);
        semesterCBB.setFont(textFieldFont);
        semesterCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(semesterCBB, gbcSM);
//------------------

//                ------------------
        JLabel subjectLabel = new JLabel("Mã lớp");
        subjectLabel.setFont(inputFont);
        subjectLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(subjectLabel, gbcSM);

        idClassCBB = new JComboBox<>();
        idClassCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                idClassCBB.removeAllItems();
                Classes c = new Classes();
                c.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
                c.setSemester(String.valueOf(semesterCBB.getSelectedItem()));
                List<String> arr1 = getIDClass(c);
                idClassCBB.addItem("_");
                for(int i = 0; i < arr1.size(); i++) {
                    idClassCBB.addItem(arr1.get(i));
                }

            }
        });
        idClassCBB.setFont(textFieldFont);
        idClassCBB.setPreferredSize(new Dimension(150,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(idClassCBB, gbcSM);

        addButton = new JButton("");
        addButton.addActionListener(action);
        addButton.setPreferredSize(new Dimension(30,30));
        addButton.setFont(buttonFont);
        addButton.setForeground(Color.blue);
        addButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
//        addButton.setBackground(new Color(0xeac086));
        gbcSM.insets = new Insets(0,155,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(addButton, gbcSM);


        gbcSM.insets = new Insets(0,5,10,0);

        JLabel SubjectLabel = new JLabel("Môn học");
        SubjectLabel.setFont(inputFont);
        SubjectLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(SubjectLabel, gbcSM);

        subjectTextField = new JTextField();
        subjectTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                Classes c = new Classes();
                c.setId(String.valueOf(idClassCBB.getSelectedItem()));
                List<String> arrayList = getSubjectFromIDClass(c);
                String rs = arrayList.get(0);
                subjectTextField.setText(rs);
            }
        });
        subjectTextField.setFont(textFieldFont);
        subjectTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        panelSM.add(subjectTextField, gbcSM);


        //        --------input class---------
        JLabel idStudentLabel = new JLabel("Mã sinh viên");
        idStudentLabel.setFont(inputFont);
        idStudentLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(idStudentLabel, gbcSM);

        idStudentCBB = new JComboBox<>();
        idStudentCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                idStudentCBB.removeAllItems();
                String s1 = String.valueOf(idClassCBB.getSelectedItem());
                List<String> arr1 = getIDStudentScore(s1);
                Collections.sort(arr1);
                idStudentCBB.addItem("_");
                for(int i = 0; i < arr1.size(); i++) {
                    idStudentCBB.addItem(arr1.get(i));
                }

            }
        });
        idStudentCBB.setFont(textFieldFont);
        idStudentCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
        panelSM.add(idStudentCBB, gbcSM);

//        --------input major----------
        JLabel nameStudentLabel = new JLabel("Tên sinh viên");
        nameStudentLabel.setFont(inputFont);
        nameStudentLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(nameStudentLabel, gbcSM);

        nameStudentTextField = new JTextField();
        nameStudentTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                Student st = new Student();
                st.setId(Integer.parseInt(String.valueOf(idStudentCBB.getSelectedItem())));
                List<String> arrayList = getNameStudent(st);
                String rs = arrayList.get(0);
                nameStudentTextField.setText(rs);
            }
        });
        nameStudentTextField.setFont(textFieldFont);
        nameStudentTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 6;
        panelSM.add(nameStudentTextField, gbcSM);


//        --------input address----------
        JLabel midScoreLabel = new JLabel("Điểm giữa kì");
        midScoreLabel.setFont(inputFont);
        midScoreLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(midScoreLabel, gbcSM);

        midScoreTextField = new JTextField();
        midScoreTextField.setFont(textFieldFont);
        midScoreTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 7;
        panelSM.add(midScoreTextField, gbcSM);

        //        --------input email----------
        JLabel endScoreLabel = new JLabel("Điểm cuối kỳ");
        endScoreLabel.setFont(inputFont);
        endScoreLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(endScoreLabel, gbcSM);

        endScoreTextField = new JTextField();
        endScoreTextField.setFont(textFieldFont);
        endScoreTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 8;
        panelSM.add(endScoreTextField, gbcSM);

        JLabel weightLabel = new JLabel("Trọng số GK");
        weightLabel.setFont(inputFont);
        weightLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(weightLabel, gbcSM);

        weightTextField = new JTextField();
        weightTextField.setFont(textFieldFont);
        weightTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 9;
        panelSM.add(weightTextField, gbcSM);

        JLabel nullLabel = new JLabel("");
        nullLabel.setFont(inputFont);
        nullLabel.setPreferredSize(new Dimension(90,20));
        gbcSM.gridx = 0;
        gbcSM.gridy = 10;
        panelSM.add(nullLabel, gbcSM);

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
        gbcSM.gridy = 11;
        panelSM.add(saveButton, gbcSM);

//        deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(action);
//        deleteButton.setPreferredSize(new Dimension(100,50));
//        deleteButton.setFont(inputFont);

        refreshButton = new JButton("Làm mới");
        refreshButton.addActionListener(action);
        refreshButton.setPreferredSize(new Dimension(110,30));
        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(new Color(0xeac086));
        refreshButton.setForeground(Color.blue);
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/images/reload.png")));
        gbcSM.insets = new Insets(15,10,20,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 11;
        panelSM.add(refreshButton, gbcSM);

        //        -------------
        findTextField = new JTextField("Nhập tên hoặc mã sinh viên...");
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
        gbcSM.gridx = 2;
        gbcSM.gridy = 11;
        panelSM.add(findTextField, gbcSM);

        findButton = new JButton("Tìm kiếm");
        findButton.addActionListener(action);
        findButton.setPreferredSize(new Dimension(115,29));
        findButton.setFont(buttonFont);
        findButton.setBackground(new Color(0xeac086));
        findButton.setForeground(Color.blue);
        findButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
        gbcSM.insets = new Insets(15,0,20,0);
        gbcSM.gridx = 3;
        gbcSM.gridy = 11;
        panelSM.add(findButton, gbcSM);


        classesTable = new JTable();
        classesTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                },
                new String[] {"STT","Mã lớp", "Môn học","Mã sinh viên",
                            "Tên sinh viên", "Điểm giữa kỳ", "Điểm cuối kỳ","Tổng kết"}
        ));
//        stTable.setModel(studentTable);
        classesTable.setFont(new Font("Arial",Font.PLAIN,12));
        classesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,12));
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
                width = Math.max(comp.getPreferredSize().width +2 , width);
            }
            if(width > 300)
                width=300;
            classesTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        classesTable.getColumnModel().getColumn(0).setMinWidth(40);
        classesTable.getColumnModel().getColumn(1).setMinWidth(60);
        classesTable.getColumnModel().getColumn(2).setMinWidth(170);
        classesTable.getColumnModel().getColumn(3).setMinWidth(80);
        classesTable.getColumnModel().getColumn(4).setMinWidth(120);
        classesTable.getColumnModel().getColumn(5).setMinWidth(82);
        classesTable.getColumnModel().getColumn(6).setMinWidth(82);
        classesTable.getColumnModel().getColumn(7).setMinWidth(60);
        ((DefaultTableCellRenderer) classesTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(classesTable.getModel());
        classesTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(classesTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660,20));
//        scrollPane.setViewportView(stTable);

        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 3;
        gbcSM.gridheight = 10;
        gbcSM.gridx = 2;
        gbcSM.gridy = 1;
        panelSM.add(scrollPane, gbcSM);


//        ----------------

        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);



//        -------------------------------------------------------------------------------

    }


    public void showDataClass(List<ScoreClass> as){
        List<ScoreClass> listStudent = new ArrayList<>();
        listStudent = as;
        DefaultTableModel tableClassModel;
        classesTable.getModel();
        tableClassModel = (DefaultTableModel)classesTable.getModel();
        tableClassModel.setRowCount(0);
        listStudent.forEach((st1) -> {
            tableClassModel.addRow(new Object[]{
                    st1.getStt(), st1.getId_class(), st1.getSubject(),
                    st1.getId_student(), st1.getName_student(),st1.getMid_score(),
                    st1.getEnd_score(), st1.getTotal_score()

            });
        });
    }

    public void refresh() {
        idStudentCBB.setSelectedIndex(-1);
        midScoreTextField.setText("");
        endScoreTextField.setText("");
        weightTextField.setText("");
        ScoreClass sc  = new ScoreClass();
        sc.setId_class(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllScoreClass(sc));
    }

    public void addScore() {
        try {
            if (idStudentCBB.getSelectedIndex()==0 || String.valueOf(midScoreTextField.getText()).equals("")
                    ||  String.valueOf(endScoreTextField.getText()).equals("") ||  String.valueOf(weightTextField.getText()).equals("")){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin điểm của sinh viên!");
            } else {
                ScoreClass sc = new ScoreClass();
                sc.setMid_score(Double.parseDouble(midScoreTextField.getText()));
                sc.setEnd_score(Double.parseDouble(endScoreTextField.getText()));
                double midScore = Double.parseDouble(midScoreTextField.getText());
                double endScore = Double.parseDouble(endScoreTextField.getText());
                double weight = Double.parseDouble(weightTextField.getText());
                double totalScore = (midScore*weight)+(endScore*(1-weight));
                totalScore = (double)Math.round(totalScore * 10d) / 10d;
                sc.setTotal_score(totalScore);
                sc.setId_class(String.valueOf(idClassCBB.getSelectedItem()));
                sc.setId_student(String.valueOf(idStudentCBB.getSelectedItem()));
                ConnJDBC.insertScoreClass(sc);

                Transcipt tr = new Transcipt();
                Subject sj = new Subject();
                sj.setName(subjectTextField.getText());
                tr.setId_subject(String.valueOf(ConnJDBC.getIDSubject2(sj).get(0)));
                String rs1 = String.valueOf(ConnJDBC.getCreditsSubject2(sj).get(0));
                tr.setCredits(String.valueOf(rs1.charAt(0)));
                tr.setSemester(String.valueOf(semesterCBB.getSelectedItem()));
                tr.setSubject(subjectTextField.getText());
                tr.setMid_score(Double.parseDouble(midScoreTextField.getText()));
                tr.setEnd_score(Double.parseDouble(endScoreTextField.getText()));
                String anpha = "";
                if(0.0 <= totalScore && totalScore < 3.95){
                    anpha = "F";
                } else if (totalScore < 4.95){
                    anpha = "D";
                } else if (totalScore < 5.45){
                    anpha = "D+";
                } else if (totalScore < 6.45){
                    anpha = "C";
                } else if (totalScore < 6.95){
                    anpha = "C+";
                } else if (totalScore < 7.95){
                    anpha = "B";
                } else if (totalScore < 8.45){
                    anpha = "B+";
                } else if (totalScore < 9.45){
                    anpha = "A";
                } else anpha = "A+";
                tr.setAnpha_score(anpha);
                ConnJDBC.insertToTranscript(tr, sc);
                JOptionPane.showMessageDialog(null, "Lưu thành công! ");
                refresh();
            }
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
        ScoreClass sc  = new ScoreClass();
        sc.setId_class(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllScoreClass(sc));
    }



    public void searchClass() {
        Classes cl = new Classes();
        if (findTextField.getText().length() == 8){
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
        ScoreClass sc  = new ScoreClass();
        sc.setId_class(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllScoreClass(sc));
    }


    public void showAll() {
        Classes c = new Classes();
        c.setId(String.valueOf(idClassCBB.getSelectedItem()));
        List<String> arrayList = getSubjectFromIDClass(c);
        String rs = arrayList.get(0);
        subjectTextField.setText(rs);
        ScoreClass sc  = new ScoreClass();
        sc.setId_class(String.valueOf(idClassCBB.getSelectedItem()));
        showDataClass(ConnJDBC.findAllScoreClass(sc));
    }

}
