package View;

import ConnData.ConnJDBC;
import Controller.StudentManagementController;
import Model.Account;
import Model.Classes;
import Model.Student;
import Model.StudentManagementModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StudentManagementView extends JPanel {
    public static String nameLogin;

    private static JTable stTable;
    private StudentManagementModel studentManagementModel;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JComboBox<String> genderCBB;
    private JTextField classTextField;
    private JTextField yearTextField;

    private JTextField addressTextField;
    private JTextField emailTextField;
    private JButton saveButton;

    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;


    private JTable classTable;
    private JTextField findTextField;

    private JPanel panelSM;
    private JPanel panelTop1;
    private JTextField idTextField;
    private JComboBox<String> facultyCBB;
    private JButton openFileButton;
    private JButton exportFileButton;
    private XSSFWorkbook workbook;


    public StudentManagementView(){
        this.studentManagementModel = new StudentManagementModel();
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new StudentManagementController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());


        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font fontTop = new Font("Verdana",Font.BOLD,28);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);



        Border compound, raisedbevel, loweredbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);


        gbcSM.insets = new Insets(10,10,10,0);
        gbcSM.anchor=GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel idLabel = new JLabel("Mã sinh viên");
        idLabel.setFont(inputFont);
        idLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 0;
        panelSM.add(idLabel, gbcSM);

        idTextField = new JTextField();
        idTextField.setFont(textFieldFont);
        idTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 0;
        panelSM.add(idTextField, gbcSM);

        gbcSM.insets = new Insets(0,10,10,0);

//        --------input name----------
        JLabel nameLabel = new JLabel("Họ và tên");
        nameLabel.setFont(inputFont);
        nameLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 1;
        panelSM.add(nameLabel, gbcSM);

        nameTextField = new JTextField();
        nameTextField.setFont(textFieldFont);
        nameTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 1;
        panelSM.add(nameTextField, gbcSM);

//        --------input age----------
        JLabel ageLabel = new JLabel("Ngày sinh");
        ageLabel.setFont(inputFont);
        ageLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 2;
        panelSM.add(ageLabel, gbcSM);



//        ageTextField = new JTextField();
//        ageTextField.setFont(textFieldFont);
//        ageTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 2;
//        panelSM.add(ageTextField, gbcSM);

        ageTextField = new JTextField("dd/mm/yy");
        ageTextField.setForeground(new Color(0xa8a7a7));
        ageTextField.setFont(textFieldFont);
        ageTextField.setPreferredSize(new Dimension(180,30));
        ageTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                ageTextField.setText("");
                ageTextField.setForeground(Color.BLACK);
            }
        });
        gbcSM.gridx = 1;
        gbcSM.gridy = 2;
        panelSM.add(ageTextField, gbcSM);

//        --------input gender----------
        JLabel genderLabel = new JLabel("Giới tính");
        genderLabel.setFont(inputFont);
        genderLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(genderLabel, gbcSM);

        //        --------input class---------
        JLabel classLabel = new JLabel("Lớp");
        classLabel.setFont(inputFont);
        classLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(classLabel, gbcSM);

        classTextField = new JTextField();
        classTextField.setFont(textFieldFont);
        classTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 4;
        panelSM.add(classTextField, gbcSM);

        //        --------input Year----------
        JLabel yearLabel = new JLabel("Khóa");
        yearLabel.setFont(inputFont);
        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(yearLabel, gbcSM);

        yearTextField = new JTextField();
        yearTextField.setFont(textFieldFont);
        yearTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 5;
        panelSM.add(yearTextField, gbcSM);


//        --------input major----------
        JLabel majorLabel = new JLabel("Khoa");
        majorLabel.setFont(inputFont);
        majorLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(majorLabel, gbcSM);

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
        gbcSM.gridy = 6;
        panelSM.add(facultyCBB, gbcSM);

//        --------input address----------
        JLabel addressLabel = new JLabel("Địa chỉ");
        addressLabel.setFont(inputFont);
        addressLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(addressLabel, gbcSM);

        addressTextField = new JTextField();
        addressTextField.setFont(textFieldFont);
        addressTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 7;
        panelSM.add(addressTextField, gbcSM);

        //        --------input email----------
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(inputFont);
        emailLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(emailLabel, gbcSM);

//        emailTextField = new JTextField();
//        emailTextField.setFont(textFieldFont);
//        emailTextField.setPreferredSize(new Dimension(180,30));
//        gbcSM.gridx = 1;
//        gbcSM.gridy = 8;
//        panelSM.add(emailTextField, gbcSM);

        emailTextField = new JTextField("ex@example.com");
        emailTextField.setForeground(new Color(0xa8a7a7));
        emailTextField.setFont(textFieldFont);
        emailTextField.setPreferredSize(new Dimension(180,30));
        emailTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                emailTextField.setText("");
                emailTextField.setForeground(Color.BLACK);
            }
        });
        gbcSM.gridx = 1;
        gbcSM.gridy = 8;
        panelSM.add(emailTextField, gbcSM);

        JLabel nullLabel = new JLabel("");
        nullLabel.setFont(inputFont);
        nullLabel.setPreferredSize(new Dimension(80,20));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(nullLabel, gbcSM);

        //        --------Button---------
        gbcSM.anchor = GridBagConstraints.CENTER;

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

//        deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(action);
//        deleteButton.setPreferredSize(new Dimension(100,50));
//        deleteButton.setFont(inputFont);

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
        gbcSM.insets = new Insets(15,0,20,0);
        gbcSM.gridx = 2;
        gbcSM.gridy = 10;
        panelSM.add(refreshButton, gbcSM);





        //        ------CBB gender-------
        String gender[] = {"_","Nam","Nữ"};
        genderCBB = new JComboBox<>(gender);
        genderCBB.setFont(textFieldFont);
        genderCBB.setPreferredSize(new Dimension(180,30));
        gbcSM.insets = new Insets(0,10,10,0);
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        gbcSM.anchor = GridBagConstraints.LINE_START;
        panelSM.add(genderCBB, gbcSM);

        openFileButton = new JButton("Mở file");
        openFileButton.addActionListener(action);
        openFileButton.setPreferredSize(new Dimension(110,30));
        openFileButton.setFont(buttonFont);
        openFileButton.setBackground(new Color(0xeac086));
        openFileButton.setForeground(Color.blue);
        openFileButton.setIcon(new ImageIcon(getClass().getResource("/images/File.png")));
        gbcSM.insets = new Insets(15,10,20,0);
        gbcSM.gridx = 3;
        gbcSM.gridy = 10;
        panelSM.add(openFileButton, gbcSM);

        exportFileButton = new JButton("Xuất file");
        exportFileButton.addActionListener(action);
        exportFileButton.setPreferredSize(new Dimension(110,30));
        exportFileButton.setFont(buttonFont);
        exportFileButton.setBackground(new Color(0xeac086));
        exportFileButton.setForeground(Color.blue);
        exportFileButton.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
        gbcSM.insets = new Insets(15,10,20,0);
        gbcSM.gridx = 4;
        gbcSM.gridy = 10;
        panelSM.add(exportFileButton, gbcSM);
        
        
        findTextField = new JTextField("Nhập tên hoặc mã sinh viên..");
        findTextField.setForeground(new Color(0xa8a7a7));
        findTextField.setFont(textFieldFont);
        findTextField.setPreferredSize(new Dimension(170,30));
        findTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                findTextField.setText("");
                findTextField.setForeground(Color.BLACK);
            }
        });
        gbcSM.insets = new Insets(15,20,20,0);
        gbcSM.gridx = 5;
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
        gbcSM.gridx = 6;
        gbcSM.gridy = 10;
        panelSM.add(findButton, gbcSM);


        stTable = new JTable();
        stTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null}
                },
                new String[] {"MSSV", "Họ và tên","Ngày sinh","Giới tính",
                            "Lớp", "Khóa", "Khoa","Địa chỉ", "Email"}
        ));
//        stTable.setModel(studentTable);
        stTable.setFont(new Font("Arial",Font.PLAIN,12));
        stTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,12));
        stTable.setGridColor(new Color(0xbcbcbc));
        stTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showData(ConnJDBC.findAll());
        stTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < stTable.getColumnCount(); column++) {
            int width = 10; // Min width
            for (int row = 0; row < stTable.getRowCount(); row++) {
                TableCellRenderer renderer = stTable.getCellRenderer(row, column);
                Component comp = stTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +2 , width);
            }
            if(width > 300)
                width=300;
            stTable.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        stTable.getColumnModel().getColumn(0).setMinWidth(70);
        stTable.getColumnModel().getColumn(1).setMinWidth(120);
        stTable.getColumnModel().getColumn(2).setMinWidth(80);
        stTable.getColumnModel().getColumn(3).setMinWidth(60);
        stTable.getColumnModel().getColumn(4).setMinWidth(60);
        stTable.getColumnModel().getColumn(5).setMinWidth(40);
        stTable.getColumnModel().getColumn(6).setMinWidth(60);
        stTable.getColumnModel().getColumn(7).setMinWidth(60);
        stTable.getColumnModel().getColumn(8).setMinWidth(60);
        ((DefaultTableCellRenderer)stTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(stTable.getModel());
        stTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(stTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(660,20));
//        scrollPane.setViewportView(stTable);

        gbcSM.insets = new Insets(0,5,10,0);
        gbcSM.fill = GridBagConstraints.BOTH;
        gbcSM.gridwidth = 5;
        gbcSM.gridheight = 10;
        gbcSM.gridx = 2;
        gbcSM.gridy = 0;
        panelSM.add(scrollPane, gbcSM);

        this.setLayout(new BorderLayout());
        this.add(panelSM, BorderLayout.CENTER);

//        -------------------------------------------------------------------------------

        showData(ConnJDBC.findAll());
    }

    public void showData(List<Student>studentl){
        List<Student> listStudent = new ArrayList<>();
        listStudent = studentl;
        DefaultTableModel tableModel;
        stTable.getModel();
        tableModel = (DefaultTableModel)stTable.getModel();
        tableModel.setRowCount(0);
        listStudent.forEach((student) -> {
            tableModel.addRow(new Object[]{
                    student.getId(), student.getName(), student.getDateOfBirth(),
                    student.getGender(), student.getClassStudent(), student.getYear(),
                    student.getMajor(), student.getAddress(), student.getEmail()
            });
        });
    }

    public void showDataClass(List<Classes> classses){
        List<Classes> listClasses = new ArrayList<>();
        listClasses = classses;
        DefaultTableModel tableClassModel;
        classTable.getModel();
        tableClassModel = (DefaultTableModel)classTable.getModel();
        tableClassModel.setRowCount(0);
        listClasses.forEach((class1) -> {
            tableClassModel.addRow(new Object[]{
                    class1.getId(), class1.getSubject(), class1.getFaculty(),
                    class1.getSemester(), class1.getLecturers(),
                    class1.getNumOfStudent(), class1.getRoom()
            });
        });
    }

    public void refresh() {
        idTextField.setText("");
        nameTextField.setText("");
        ageTextField.setText("");
        genderCBB.setSelectedIndex(-1);
        classTextField.setText("");
        yearTextField.setText("");
        facultyCBB.setSelectedIndex(-1);
        addressTextField.setText("");
        emailTextField.setText("");
        showData(ConnJDBC.findAll());
    }



    public void saveStudent() {
        String dateCheck = ageTextField.getText();
        int c1 = 0;
        for (int i = 0; i < dateCheck.length(); i++){
            if (dateCheck.charAt(i) == '/'){
                c1++;
            }
        }

        String emailCheck = emailTextField.getText();
        int c2 = 0;
        for (int i = 0; i < emailCheck.length(); i++){
            if (emailCheck.charAt(i) == '@'){
                c2++;
            }
        }
        try {
            if (String.valueOf(nameTextField.getText()).equals("") || String.valueOf(ageTextField.getText()).equals("")
                    || genderCBB.getSelectedIndex() == 0 || String.valueOf(emailTextField.getText()).equals("")
                    || facultyCBB.getSelectedItem().equals("") || String.valueOf(addressTextField.getText()).equals("")
                    || String.valueOf(classTextField.getText()).equals("") || String.valueOf(yearTextField.getText()).equals("")){
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin sinh viên!");
            } else if (c1 != 2){
                JOptionPane.showMessageDialog(this, "Hãy nhập đúng định dạng ngày sinh!");
            }
            else if (c2 != 1){
                JOptionPane.showMessageDialog(this, "Hãy nhập đúng định dạng email!");
            }
            else {
                Student st = new Student();
                st.setId(Integer.parseInt(idTextField.getText()));
                st.setName(nameTextField.getText());
                st.setDateOfBirth(ageTextField.getText());
                st.setGender(String.valueOf(genderCBB.getSelectedItem()));
                st.setClassStudent(classTextField.getText());
                st.setYear(yearTextField.getText());
                st.setMajor(String.valueOf(facultyCBB.getSelectedItem()));
                st.setAddress(addressTextField.getText());
                st.setEmail(emailTextField.getText());
                ConnJDBC.insert(st);
                ConnJDBC.creatTranscriptStudent(st);
                saveAccount();
                JOptionPane.showMessageDialog(null, "Lưu thành công!");
                refresh();
            }
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
        showData(ConnJDBC.findAll());
    }

    public void saveAccount() {
        try {
            Account ac = new Account();
            ac.setRank("Sinh viên");
            ac.setId_user(idTextField.getText());
            ac.setUsername(idTextField.getText());
            ac.setPassword(idTextField.getText());
            ConnJDBC.insertAccountST(ac);
            refresh();
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
    }



    public void searchStudent() {
        Student st = new Student();
        if (findTextField.getText().length() == 8){
            idTextField.setText(findTextField.getText());
            int idInt = Integer.parseInt(findTextField.getText());
            st.setId(idInt);
            showData(ConnJDBC.findByID(st));

        } else {
            st.setName(findTextField.getText());
            nameTextField.setText(findTextField.getText());
            showData(ConnJDBC.findByName(st));
        }

    }

    public void updateStudent() {
        Student st = new Student();
        st.setName(nameTextField.getText());
        st.setDateOfBirth(ageTextField.getText());
        st.setGender(String.valueOf(genderCBB.getSelectedItem()));
        st.setClassStudent(classTextField.getText());
        st.setYear(yearTextField.getText());
        st.setMajor(String.valueOf(facultyCBB.getSelectedItem()));
        st.setAddress(addressTextField.getText());
        st.setEmail(emailTextField.getText());
        ConnJDBC.Update(st);
        JOptionPane.showMessageDialog(null, "Lưu thành công! ");
        showData(ConnJDBC.findAll());
    }

    public void readFile() {
        List<Student> studentList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
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
                Student st = new Student();
                Account ac = new Account();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0: {st.setId((int) cell.getNumericCellValue());
                                ac.setRank("Sinh viên");
                                ac.setUsername(String.valueOf(st.getId()));
                                ac.setPassword(String.valueOf(st.getId()));
                                ac.setId_user(String.valueOf(st.getId()));}
                        case 1: st.setName(cell.toString());
                        case 2: st.setDateOfBirth(cell.toString());
                        case 3: st.setGender(cell.toString());
                        case 4: st.setClassStudent(cell.toString());
                        case 5: st.setYear(cell.toString());
                        case 6: st.setMajor(cell.toString());
                        case 7: st.setAddress(cell.toString());
                        case 8: st.setEmail(cell.toString());
                        default:{}
                    }
                    cellIndex++;
                }
                studentList.add(st);
                accountList.add(ac);
//                ConnJDBC.creatTranscriptStudent(st);
            }
            ConnJDBC.insertStudentExcels(200,studentList);
//            ConnJDBC.insertAccountExcels(200,accountList);
            JOptionPane.showMessageDialog(null, "Lưu thành công!");
            refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
