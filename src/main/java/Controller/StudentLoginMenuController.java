package Controller;

import View.AccountView;
import View.StudentLoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class StudentLoginMenuController implements Action {
    public StudentLoginView view;
    public StudentLoginMenuController(StudentLoginView view){this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Đổi mật khẩu")){

        }  else if (cm.equals("About me")){
            JOptionPane.showMessageDialog(null, "Student management 1.0 \nCopyright by lytienbka@gmail.com");
        }  else if (cm.equals("Log out")){
            this.view.logout();
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
