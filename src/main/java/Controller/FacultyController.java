package Controller;

import View.FacultyView;
import View.StudentManagementView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class FacultyController implements Action {
    public FacultyView view;
    public FacultyController (FacultyView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Làm mới")){
            this.view.refresh();
        } else if (cm.equals("Lưu")) {
            this.view.saveFaculty();
//        } else if (cm.equals("Delete")) {
//            this.view.deleteStudent();
        } else if (cm.equals("Tìm kiếm")) {
            this.view.searchFaculty();
        } else if (cm.equals("Cập nhật")) {
            this.view.updateFaculty();
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
