package Controller;

import View.AccountView;
import View.FacultyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class AccountMenuController implements Action {
    public AccountView view;
    public AccountMenuController(AccountView view){this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Quản lý sinh viên")){
            this.view.openSM();
        } else if (cm.equals("Quản lý môn học")){
            this.view.openSubject();
        } else if (cm.equals("Tạo lớp tín chỉ")){
            this.view.openClassMN();
        } else if (cm.equals("About me")){
            JOptionPane.showMessageDialog(null, "Student management 1.0 \nCopyright by lytienbka@gmail.com");
        } else if (cm.equals("Quản lý điểm")){
            this.view.openClassMN();
        } else if (cm.equals("Log out")){
            this.view.logout();
        } else if (cm.equals("Quản lý giảng viên")){
            this.view.openTeacherV();
        } else if (cm.equals("Danh sách lớp")){
            this.view.openaddST();
        } else if (cm.equals("Điểm lớp học")){
            this.view.scoreClass();
        }
    }
    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void putValue(String key, Object value) {

    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

}
