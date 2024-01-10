package Controller;

import View.HomeView;
import View.StudentManagementView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class HomeController implements Action {
    public HomeView view;
    public HomeController(HomeView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if (cm.equals("Nhập khoa")) {
            this.view.openFaculty();
        } else if (cm.equals("Nhập sinh viên")) {
            this.view.openStudent();
        } else if (cm.equals("Nhập giảng viên")) {
            this.view.openTeacherV();
        } else if (cm.equals("Nhập môn học")) {
            this.view.openSubject();
        } else if (cm.equals("Nhập lớp học")) {
            this.view.openClassMN();
        } else if (cm.equals("Nhập danh sách lớp")) {
            this.view.openaddST();
        } else if (cm.equals("Nhập điểm")) {
            this.view.openScore();
        } else if (cm.equals("Bảng điểm sinh viên")) {
            this.view.openTranscript();
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

