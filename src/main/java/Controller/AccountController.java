package Controller;

import View.AccountView;
import View.FacultyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class AccountController implements Action {
    public AccountView view;
    public AccountController(AccountView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Làm mới")){
            this.view.refresh();
        }  else if (cm.equals("Tìm kiếm")) {
            this.view.searchAccount();
        } else if (cm.equals("Cập nhật")) {
            this.view.updateAccount();
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
