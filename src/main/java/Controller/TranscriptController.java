package Controller;

import View.ScoreView;
import View.TranscriptView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class TranscriptController implements Action {
    public TranscriptView view;
    public TranscriptController(TranscriptView view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Just click: "+cm);
         if (cm.equals("Tìm kiếm")) {
            this.view.showAll();
        }  else if (cm.equals("")) {
            this.view.showStudent();
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

