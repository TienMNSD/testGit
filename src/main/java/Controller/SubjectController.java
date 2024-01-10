package Controller;

import View.SubjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class SubjectController implements Action {
    public SubjectView view;
    public SubjectController(SubjectView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Làm mới")){
            this.view.refresh();
        } else if (cm.equals("Lưu")) {
            this.view.saveSubject();
//        } else if (cm.equals("Delete")) {
//            this.view.deleteStudent();
        } else if (cm.equals("Tìm kiếm")) {
            this.view.searchSubject();
        } else if (cm.equals("Cập nhật")) {
            this.view.updateSubject();
        } else if (cm.equals("")) {
            this.view.addLecturers();
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
