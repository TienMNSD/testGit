package View;

import ConnData.ConnJDBC;
import Controller.TranscriptController;
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

public class TranscriptView extends JPanel {
    public static String nameLogin;

    private static JTable classesTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;

    private JButton saveButton;
    private JButton deleteButton;
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
    private JTextField nameStudentTextField;
    private JTextField midScoreTextField;
    private JTextField endScoreTextField;
    private JButton addButton;
    private JTextField weightTextField;
    private JComboBox<Object> classCBB;
    private JComboBox<Object> nameStudentCBB;


    public TranscriptView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new TranscriptController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

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


        Border compound, raisedbevel, loweredbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);


        gbcSM.insets = new Insets(20,5,10,0);
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

//        -----------------

        JLabel classLabel = new JLabel("Lớp");
        classLabel.setFont(inputFont);
        classLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(classLabel, gbcSM);

        classCBB = new JComboBox<>();
        classCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                classCBB.removeAllItems();
                Student st = new Student();
                st.setMajor(String.valueOf(facultyCBB.getSelectedItem()));
                List<String> arrayList3 = getCLassStudent2(st);
                List<String> arrayList4 = new ArrayList<String>();
                for (String i: arrayList3) {
                    if (!arrayList4.contains(i)) {
                        arrayList4.add(i);
                    }
                }
                String[] array3 = new String[arrayList4.size()+1];
                array3[0] = "_";
                for(int i = 0; i < array3.length-1; i++) {
                    array3[i+1] = arrayList4.get(i);
                }
                classCBB.addItem("_");
                for(int i = 0; i < arrayList4.size(); i++) {
                    classCBB.addItem(arrayList4.get(i));
                }

            }
        });
        classCBB.setFont(textFieldFont);
        classCBB.setPreferredSize(new Dimension(150,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(classCBB, gbcSM);
//------------------

//                ------------------
        JLabel idStudentLabel = new JLabel("Mã sinh viên");
        idStudentLabel.setFont(inputFont);
        idStudentLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(idStudentLabel, gbcSM);

        idStudentCBB = new JComboBox<>();
        idStudentCBB.setFont(textFieldFont);
        idStudentCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        panelSM.add(idStudentCBB, gbcSM);

        addButton = new JButton("");
        addButton.addActionListener(action);
        addButton.setPreferredSize(new Dimension(30,30));
        addButton.setFont(buttonFont);
        addButton.setForeground(Color.blue);
        addButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
//        addButton.setBackground(new Color(0xeac086));
        gbcSM.insets = new Insets(0,155,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(addButton, gbcSM);


        gbcSM.insets = new Insets(0,5,10,0);

        JLabel SubjectLabel = new JLabel("Tên sinh viên");
        SubjectLabel.setFont(inputFont);
        SubjectLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(SubjectLabel, gbcSM);

        nameStudentCBB = new JComboBox<>();
        nameStudentCBB.setFont(textFieldFont);
        nameStudentCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(nameStudentCBB, gbcSM);


        //        --------input class---------
        JLabel zzLabel = new JLabel("");
        zzLabel.setFont(inputFont);
        zzLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(zzLabel, gbcSM);


//        --------input major----------
        JLabel zz2Label = new JLabel("");
        zz2Label.setFont(inputFont);
        zz2Label.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(zz2Label, gbcSM);




//        --------input address----------
        JLabel midScoreLabel = new JLabel("");
        midScoreLabel.setFont(inputFont);
        midScoreLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(midScoreLabel, gbcSM);


        //        --------input email----------
        JLabel endScoreLabel = new JLabel("");
        endScoreLabel.setFont(inputFont);
        endScoreLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(endScoreLabel, gbcSM);

        JLabel weightLabel = new JLabel("");
        weightLabel.setFont(inputFont);
        weightLabel.setPreferredSize(new Dimension(90,60));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(weightLabel, gbcSM);

        gbcSM.anchor = GridBagConstraints.LINE_START;


        findButton = new JButton("Tìm kiếm");
        findButton.addActionListener(action);
        findButton.setPreferredSize(new Dimension(115,29));
        findButton.setFont(buttonFont);
        findButton.setBackground(new Color(0xeac086));
        findButton.setForeground(Color.blue);
        findButton.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
        gbcSM.insets = new Insets(5,0,20,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        classesTable = new JTable();
        classesTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null}
                },
                new String[] {"STT","Học kỳ", "Mã môn học","Môn học",
                            "Tín chỉ", "Điểm giữa kỳ", "Điểm cuối kỳ","Điểm chữ"}
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
        classesTable.getColumnModel().getColumn(2).setMinWidth(80);
        classesTable.getColumnModel().getColumn(3).setMinWidth(180);
        classesTable.getColumnModel().getColumn(4).setMinWidth(45);
        classesTable.getColumnModel().getColumn(5).setMinWidth(85);
        classesTable.getColumnModel().getColumn(6).setMinWidth(85);
        classesTable.getColumnModel().getColumn(7).setMinWidth(65);
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


    }


    public void showDataTranscript(List<Transcipt> ts){
        List<Transcipt> listTS = new ArrayList<>();
        listTS = ts;
        DefaultTableModel tableClassModel;
        classesTable.getModel();
        tableClassModel = (DefaultTableModel)classesTable.getModel();
        tableClassModel.setRowCount(0);
        listTS.forEach((st1) -> {
            tableClassModel.addRow(new Object[]{
                    st1.getStt(), st1.getSemester(), st1.getId_subject(),
                    st1.getSubject(), st1.getCredits(),st1.getMid_score(),
                    st1.getEnd_score(), st1.getAnpha_score()

            });
        });
    }




    public void showAll() {
        Student st = new Student();
        if (nameStudentCBB.getSelectedIndex() !=0){
            idStudentCBB.setSelectedIndex(nameStudentCBB.getSelectedIndex());
        }
        if (idStudentCBB.getSelectedIndex() != 0){
            nameStudentCBB.setSelectedIndex(idStudentCBB.getSelectedIndex());
        }
        st.setId(Integer.parseInt(idStudentCBB.getSelectedItem().toString()));
        showDataTranscript(ConnJDBC.findTranscript(st));
    }

    public void showStudent() {
        idStudentCBB.removeAllItems();
        Student st = new Student();
        st.setClassStudent(String.valueOf(classCBB.getSelectedItem()));
        List<String> arr1 = getIDStudent(st);
        idStudentCBB.addItem("_");
        for(int i = 0; i < arr1.size(); i++) {
            idStudentCBB.addItem(arr1.get(i));
        }

        nameStudentCBB.removeAllItems();
        List<String> arr2 = getNameStudentFromClass(st);
        nameStudentCBB.addItem("_");
        for(int i = 0; i < arr2.size(); i++) {
            nameStudentCBB.addItem(arr2.get(i));
        }
    }
}
