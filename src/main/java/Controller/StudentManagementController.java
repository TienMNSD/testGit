package Controller;

import ConnData.ConnJDBC;
import Model.Student;
import View.AccountView;
import View.StudentManagementView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class StudentManagementController implements Action {
    public StudentManagementView view;
    public StudentManagementController (StudentManagementView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Làm mới")){
            this.view.refresh();
        } else if (cm.equals("Lưu")) {
            this.view.saveStudent();
        }  else if (cm.equals("Tìm kiếm")) {
            this.view.searchStudent();
        } else if (cm.equals("Cập nhật")) {
            this.view.updateStudent();
        } else if (cm.equals("Mở file")) {
            this.view.readFile();
        } else if (cm.equals("Xuất file")) {
            try {
                this.view.writeFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
    public boolean accept(Object sender) {
        return Action.super.accept(sender);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}

