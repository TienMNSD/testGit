package View;

import Controller.LoginController;
import Model.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginView extends javax.swing.JFrame {

    public static String nameLogin;
    private LoginModel loginModel;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField idTextField;
    private javax.swing.JPasswordField passwordField;
    private JLabel userLoginLabel;
    private JLabel coverLabel;
    public String userGet;
    public String idtxt;
//    GridBagConstraints gbc = new GridBagConstraints();

    public LoginView(){
        this.loginModel = new LoginModel();
        this.initLogin();
    }

    private void initLogin(){
        this.setTitle("Student Management");
        this.setSize(390,320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Action action = new LoginController(this);

        Font button = new Font("Arial",1,12);
        Font inputFont = new Font("Arial",1,12);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

//        --------Button---------
        loginButton = new JButton("Đăng nhập");
        loginButton.setFont(button);
        loginButton.setPreferredSize(new Dimension(125,30));
        loginButton.addActionListener(action);
        loginButton.setBackground(new Color(0xeac086));
        loginButton.setIcon(new ImageIcon(getClass().getResource("/images/login.png")));
        loginButton.setForeground(Color.blue);
        gbc.insets = new Insets(20,40,10,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(loginButton, gbc);

        cancelButton = new JButton("Thoát");
        cancelButton.setPreferredSize(new Dimension(125,30));
        cancelButton.setFont(button);
        cancelButton.addActionListener(action);
        cancelButton.setBackground(new Color(0xeac086));
        cancelButton.setIcon(new ImageIcon(getClass().getResource("/images/Logoff.png")));
        cancelButton.setForeground(Color.blue);
        gbc.insets = new Insets(20,0,10,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(cancelButton,gbc);


//        --------Top---------
//        Font fontTop = new Font("Arial",Font.BOLD,32);
//        JLabel userLoginLabel = new JLabel("User Login");
//        userLoginLabel.setHorizontalAlignment(JLabel.CENTER);
//        userLoginLabel.setFont(fontTop);
//        userLoginLabel.setForeground(Color.blue);

//        --------Middle---------
        JLabel idLabel = new JLabel("Tài khoản");
        idLabel.setFont(inputFont);
//        idLabel.setPreferredSize(new Dimension(20,40));
//        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,60,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(idLabel, gbc);



        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordLabel.setFont(inputFont);
//        passwordLabel.setPreferredSize(new Dimension(20,40));
//        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10,60,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);


        passwordField = new JPasswordField();
        passwordField.setFont(inputFont);
        passwordField.setPreferredSize(new Dimension(150,32));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        idTextField = new JTextField();
        idTextField.setFont(inputFont);
        idTextField.setPreferredSize(new Dimension(150,32));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(idTextField, gbc);

        userLoginLabel = new JLabel("Nhập tài khoản và mật khẩu của bạn!");
        userLoginLabel.setIcon(new ImageIcon(getClass().getResource("/images/Customers.png")));
//        userLoginLabel.setPreferredSize(new Dimension(00,50));
        userLoginLabel.setForeground(Color.blue);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,10,0);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLoginLabel, gbc);

        coverLabel = new JLabel();
        coverLabel.setIcon(new ImageIcon(getClass().getResource("/images/manager.png")));
//        userLoginLabel.setPreferredSize(new Dimension(00,50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,10,0);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(coverLabel, gbc);



        panel.setBackground(new Color(0xdef3f6));
        this.add(panel);
//        this.pack();
        this.setVisible(true);
    }


    public void userLogin(){
        userGet = idTextField.getText();
        Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String userJDBC = "postgres";
        String passwordJDBC = "lhyieltgjtgy1";
        idtxt = String.valueOf(idTextField.getText());
        String passwordtxt = String.valueOf(passwordField.getPassword());
        String queryLogin = "select*from account where username='"+idTextField.getText()+"' and password='"+passwordField.getText()+"'";
        nameLogin = idtxt;

            try {
                conn = DriverManager.getConnection(url, userJDBC, passwordJDBC);
//                PreparedStatement pstmt = conn.prepareStatement(queryLogin);
//                pstmt.setString(1, idTextField.getText());
//                pstmt.setString(2, String.valueOf(passwordField.getPassword()));
                PreparedStatement pstmt = conn.prepareCall(queryLogin);
                ResultSet rs = pstmt.executeQuery();
                System.out.println(rs);
                if (idtxt.equals("")||passwordtxt.equals("")){
                    JOptionPane.showMessageDialog(this,"Hãy nhập đầy đủ tài khoản và mật khẩu!");
                }else if (idtxt.equals("admin") && passwordtxt.equals("admin")) {
                    JOptionPane.showMessageDialog(this, "Admin đăng nhập thành công!");
                    AccountView aMN = new AccountView();
                    aMN.setVisible(true);
                    this.dispose();
                } else if (rs.next()) {
                    if (idtxt.length()==8) {
                        JOptionPane.showMessageDialog(this, "Sinh viên đăng nhập thành công!");
                        StudentLoginView stView = new StudentLoginView();
                        stView.setVisible(true);
                        this.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Giảng viên đăng nhập thành công!");
                        HomeView homeView = new HomeView();
                        homeView.setVisible(true);
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!");
                }
            } catch (Exception e) {

            }
        }


    public void resetLogin() {
        idTextField.setText("");
        passwordField.setText("");
    }
    public void Exit() {
        System.exit(0);
    }

}
