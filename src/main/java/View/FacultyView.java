package View;

import ConnData.ConnJDBC;
import Controller.FacultyController;
import Model.Faculty;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FacultyView extends JPanel {
    public static String nameLogin;

    private static JTable fcTable;
    private JScrollPane scrollPane;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JButton saveButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JTextField findTextField;
    private JPanel panelSM;
    private JTextField idTextField;


    public FacultyView(){
        this.initStudentMN();
    }
    private void initStudentMN(){
        this.setPreferredSize(new Dimension(955,465));

        Action action = new FacultyController(this);

        GridBagConstraints gbcSM = new GridBagConstraints();

        panelSM = new JPanel();
        panelSM.setBackground(new Color(0xdef3f6));
        panelSM.setLayout(new GridBagLayout());

        Font inputFont = new Font("Arial",Font.BOLD,13);
        Font textFieldFont = new Font("Arial",Font.PLAIN,13);
        Font buttonFont = new Font("Arial",1,12);

        gbcSM.insets = new Insets(10,5,10,0);
        gbcSM.anchor=GridBagConstraints.CENTER;

        //      --------input ID----------
        JLabel idLabel = new JLabel("Mã khoa");
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

        gbcSM.insets = new Insets(0,5,10,0);

//        --------input name----------
        JLabel nameLabel = new JLabel("Tên khoa");
        nameLabel.setFont(inputFont);
        nameLabel.setPreferredSize(new Dimension(80,30));
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
        JLabel addressLabel = new JLabel("Văn Phòng");
        addressLabel.setFont(inputFont);
        addressLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 3;
        panelSM.add(addressLabel, gbcSM);

        addressTextField = new JTextField();
        addressTextField.setFont(textFieldFont);
        addressTextField.setPreferredSize(new Dimension(180,30));
        gbcSM.gridx = 1;
        gbcSM.gridy = 3;
        panelSM.add(addressTextField, gbcSM);
//
////        --------input gender----------
        JLabel genderLabel = new JLabel("");
        genderLabel.setFont(inputFont);
        genderLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 4;
        panelSM.add(genderLabel, gbcSM);
//
//        //        --------input class---------
        JLabel classLabel = new JLabel("");
        classLabel.setFont(inputFont);
        classLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 5;
        panelSM.add(classLabel, gbcSM);

//        //        --------input Year----------
        JLabel yearLabel = new JLabel("");
        yearLabel.setFont(inputFont);
        yearLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 6;
        panelSM.add(yearLabel, gbcSM);

////        --------input major----------
        JLabel majorLabel = new JLabel("");
        majorLabel.setFont(inputFont);
        majorLabel.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 7;
        panelSM.add(majorLabel, gbcSM);

////        --------input address----------
        JLabel address1Label = new JLabel("");
        address1Label.setFont(inputFont);
        address1Label.setPreferredSize(new Dimension(80,30));
        gbcSM.gridx = 0;
        gbcSM.gridy = 8;
        panelSM.add(address1Label, gbcSM);

//        //        --------input email----------
        JLabel emailLabel = new JLabel("");
        emailLabel.setFont(inputFont);
        emailLabel.setPreferredSize(new Dimension(80,60));
        gbcSM.gridx = 0;
        gbcSM.gridy = 9;
        panelSM.add(emailLabel, gbcSM);

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

        findTextField = new JTextField("Nhập tên hoặc mã khoa...");
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


        fcTable = new JTable();
        fcTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null}
                },
                new String[] {"Mã khoa", "Tên khoa","Văn phòng"}
        ));
//        stTable.setModel(studentTable);
        fcTable.setFont(new Font("Arial",Font.PLAIN,14));
        fcTable.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
        fcTable.setGridColor(new Color(0xbcbcbc));
        fcTable.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        showFacultyData(ConnJDBC.findFacultyAll());
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
        ((DefaultTableCellRenderer) fcTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(fcTable.getModel());
        fcTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        scrollPane = new JScrollPane(fcTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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

        showFacultyData(ConnJDBC.findFacultyAll());
    }
    public void showFacultyData(List<Faculty>facultyL){
        List<Faculty> listFaculty = new ArrayList<>();
        listFaculty = facultyL;
        DefaultTableModel tableModel;
        fcTable.getModel();
        tableModel = (DefaultTableModel) fcTable.getModel();
        tableModel.setRowCount(0);
        listFaculty.forEach((faculty) -> {
            tableModel.addRow(new Object[]{
                    faculty.getId(), faculty.getName(), faculty.getAddress()
            });
        });
    }

    public void refresh() {
        idTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        showFacultyData(ConnJDBC.findFacultyAll());
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
        showFacultyData(ConnJDBC.findFacultyAll());
    }

    public void searchFaculty() {
        Faculty fc = new Faculty();
        if (findTextField.getText().length() == 3){
            idTextField.setText(findTextField.getText());
            nameTextField.setText("");
            fc.setId(findTextField.getText());
            showFacultyData(ConnJDBC.findFacultyID(fc));

        } else {
            fc.setName(findTextField.getText());
            nameTextField.setText(findTextField.getText());
            idTextField.setText("");
            showFacultyData(ConnJDBC.findFacultyName(fc));
        }
    }

    public void updateFaculty() {
        Faculty fc = new Faculty();
        fc.setId(idTextField.getText());
        fc.setName(nameTextField.getText());
        fc.setAddress(addressTextField.getText());
        ConnJDBC.UpdateFaculty(fc);
        JOptionPane.showMessageDialog(null, "Save success! ");
        showFacultyData(ConnJDBC.findFacultyAll());
    }
}
