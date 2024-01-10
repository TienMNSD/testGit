package View;

import ConnData.ConnJDBC;
import Controller.SubjectController;
import Model.StudentManagementModel;
import Model.Subject;
import Model.Teacher;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import static ConnData.ConnJDBC.getTeacher;

public class SubjectView extends JPanel {
    public static String nameLogin;

    private static JTable sjTable;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField creditTextField;
    private JButton saveButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JTextField findTextField;
    private JPanel panelSM;
    private JTextField idTextField;
    private JComboBox facultyCBB;
    private JComboBox<String> lecturersCBB;
    private JTextArea lecurersTextArea;
    private JButton addButton;


    public SubjectView(){
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new SubjectController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());


        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font fontTop = new Font("Verdana",Font.BOLD,28);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);


        Border compound, raisedbevel, loweredbevel, blackline;
        blackline = BorderFactory.createLineBorder(Color.gray);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);


        gbcSM.insets = new Insets(10,5,10,0);
        gbcSM.anchor=GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel idLabel = new JLabel("Mã học phần");
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
        JLabel nameLabel = new JLabel("Tên học phần");
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
        JLabel creditLabel = new JLabel("Số tín chỉ");
        creditLabel.setFont(inputFont);
        creditLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(creditLabel, gbcSM);

        creditTextField = new JTextField();
        creditTextField.setFont(textFieldFont);
        creditTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(creditTextField, gbcSM);
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
        JLabel lecturersLabel = new JLabel("Giảng viên");
        lecturersLabel.setFont(inputFont);
        lecturersLabel.setPreferredSize(new Dimension(90,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(lecturersLabel, gbcSM);

//        //        --------input Year----------
        JLabel yearLabel = new JLabel("");
        yearLabel.setFont(inputFont);
        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(yearLabel, gbcSM);
//

        lecurersTextArea = new JTextArea();
        lecurersTextArea.setFont(textFieldFont);
        lecurersTextArea.setPreferredSize(new Dimension(180,80));
        lecurersTextArea.setLineWrap(true);
        lecurersTextArea.setBorder(blackline);
        gbcSM.insets = new Insets(0,5,0,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 6;
        panelSM.add(lecurersTextArea, gbcSM);


//
////        --------input major----------
        JLabel majorLabel = new JLabel("");
        majorLabel.setFont(inputFont);
        majorLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.insets = new Insets(0,5,6,10);
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(majorLabel, gbcSM);

////        --------input address----------
        JLabel address1Label = new JLabel("");
        address1Label.setFont(inputFont);
        address1Label.setPreferredSize(new Dimension(80,40));
        gbcSM.insets = new Insets(0,5,0,10);
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(address1Label, gbcSM);

        JLabel nullLabel = new JLabel("");
        nullLabel.setFont(inputFont);
        nullLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.insets = new Insets(0,5,0,10);
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(nullLabel, gbcSM);

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
        gbcSM.gridy = 5;
        panelSM.add(addButton, gbcSM);


        lecturersCBB = new JComboBox<>();
        lecturersCBB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                lecturersCBB.removeAllItems();
                Teacher t = new Teacher();
                t.setFaculty(String.valueOf(facultyCBB.getSelectedItem()));
                List<String> arrayList1 = getTeacher(t);
                lecturersCBB.addItem("_");
                for(int i = 0; i < arrayList1.size(); i++) {
                    lecturersCBB.addItem(arrayList1.get(i));
                }

            }
        });
        lecturersCBB.setFont(textFieldFont);
        lecturersCBB.setPreferredSize(new Dimension(150,30));
        gbcSM.insets = new Insets(0,5,0,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(lecturersCBB, gbcSM);

        findTextField = new JTextField("Nhập tên hoặc mã môn học...");
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
        gbcSM.insets = new Insets(15,0,20,200);
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        sjTable = new JTable();
        sjTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[] {"Mã học phần", "Tên học phần","Số tín chỉ", "Khoa", "Giảng viên"}
        ));
//        stTable.setModel(studentTable);
        sjTable.setFont(new Font("Arial",Font.PLAIN,14));
        sjTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
        sjTable.setGridColor(new Color(0xbcbcbc));
        sjTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showSubjectData(ConnJDBC.findSubjectAll());
        sjTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < sjTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < sjTable.getRowCount(); row++) {
                TableCellRenderer renderer = sjTable.getCellRenderer(row, column);
                Component comp = sjTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +4 , width);
            }
            if(width > 300)
                width=300;
            sjTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        sjTable.getColumnModel().getColumn(0).setMinWidth(120);
//        fcTable.getColumnModel().getColumn(2).setMinWidth(85);
//        fcTable.getColumnModel().getColumn(3).setMinWidth(60);
//        fcTable.getColumnModel().getColumn(5).setMinWidth(40);
        ((DefaultTableCellRenderer) sjTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(sjTable.getModel());
        sjTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(sjTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660,20));
//        scrollPane.setViewportView(stTable);

        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 3;
        gbcSM.gridheight = 9;
        gbcSM.gridx = 2;
        gbcSM.gridy = 1;
        panelSM.add(scrollPane, gbcSM);


        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);


        showSubjectData(ConnJDBC.findSubjectAll());
    }

    public void showSubjectData(List<Subject>subjectL){
        List<Subject> listSubject = new ArrayList<>();
        listSubject = subjectL;
        DefaultTableModel tableModel;
        sjTable.getModel();
        tableModel = (DefaultTableModel) sjTable.getModel();
        tableModel.setRowCount(0);
        listSubject.forEach((subject) -> {
            tableModel.addRow(new Object[]{
                    subject.getId(), subject.getName(), subject.getCredit(), subject.getFaculty(), subject.getLecturers()
            });
        });
    }



    public void refresh() {
        idTextField.setText("");
        nameTextField.setText("");
        creditTextField.setText("");
        lecurersTextArea.setText("");
        lecturersCBB.setSelectedIndex(0);
        facultyCBB.setSelectedIndex(0);
        showSubjectData(ConnJDBC.findSubjectAll());
    }



    public void saveSubject() {
        try {
            if (String.valueOf(nameTextField.getText()).equals("") || String.valueOf(idTextField.getText()).equals("")
                    || String.valueOf(creditTextField.getText()).equals("") || facultyCBB.getSelectedIndex()==0
                    || String.valueOf(lecurersTextArea.getText()).equals("")){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin của môn học!");
            } else {
                Subject sj = new Subject();
                sj.setId(idTextField.getText());
                sj.setName(nameTextField.getText());
                sj.setCredit(creditTextField.getText());
                sj.setFaculty(String.valueOf(facultyCBB.getItemAt(facultyCBB.getSelectedIndex())));
                sj.setLecturers(lecurersTextArea.getText());
                ConnJDBC.insertSubject(sj);
                JOptionPane.showMessageDialog(null, "Lưu thành công!");
                refresh();
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
        showSubjectData(ConnJDBC.findSubjectAll());
    }




    public void searchSubject() {
        Subject sj = new Subject();
        if (findTextField.getText().length() == 6){
            idTextField.setText(findTextField.getText());
            nameTextField.setText("");
            sj.setId(findTextField.getText());
            showSubjectData(ConnJDBC.findSubjectID(sj));

        } else {
            sj.setName(findTextField.getText());
            nameTextField.setText(findTextField.getText());
            idTextField.setText("");
            showSubjectData(ConnJDBC.findSubjectName(sj));
        }

    }

    public void updateSubject() {
        Subject sj = new Subject();
        sj.setId(idTextField.getText());
        sj.setName(nameTextField.getText());
        sj.setCredit(creditTextField.getText());
        sj.setFaculty(String.valueOf(facultyCBB.getItemAt(facultyCBB.getSelectedIndex())));
        sj.setLecturers(lecurersTextArea.getText());
        ConnJDBC.UpdateSubject(sj);
        JOptionPane.showMessageDialog(null, "Save success! ");
        showSubjectData(ConnJDBC.findSubjectAll());
    }




    public void openScore() {
        panelSM.setVisible(false);
    }

    public void addLecturers() {
        if (String.valueOf(lecurersTextArea.getText()).equals("")) {
            lecurersTextArea.append(String.valueOf(lecturersCBB.getSelectedItem()));
        } else {
            lecurersTextArea.append(", " + String.valueOf(lecturersCBB.getSelectedItem()));
        }
    }

}
