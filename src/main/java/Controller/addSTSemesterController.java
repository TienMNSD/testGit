package Controller;

import View.ClassesView;
import View.addSTSemesterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class addSTSemesterController implements Action {
    public addSTSemesterView view;
    public addSTSemesterController(addSTSemesterView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
        if(cm.equals("Làm mới")){
            this.view.refresh();
        } else if (cm.equals("Thêm")) {
            this.view.addStudent();
        }  else if (cm.equals("Tìm kiếm")) {
            this.view.searchClass();
        } else if (cm.equals("Cập nhật")) {
            this.view.updateClass();
        } else if (cm.equals("")) {
            this.view.showAll();
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

