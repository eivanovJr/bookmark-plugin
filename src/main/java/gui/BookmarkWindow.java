package gui;

import actions.GoToMark;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import data.DataCenter;
import data.MyBookmark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookmarkWindow {
    private JTable Bookmarks;
    private JPanel contentPanel;
    private JPopupMenu popupMenu;

    private void init(){
        Bookmarks.setModel(DataCenter.TABLE_MODEL);
        Bookmarks.setEnabled(false);
    }

    public BookmarkWindow(Project project, ToolWindow toolWindow){
        init();
        Bookmarks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point p = e.getPoint();
                int row = Bookmarks.rowAtPoint(p);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    createPopupMenu(row);
                    popupMenu.show(Bookmarks, e.getX(), e.getY());
                    return;
                }
                if (e.getClickCount() == 2){
                    MyBookmark mbk = DataCenter.bookmark_list.get(row);
                    GoToMark.goto_mark(mbk, project);
                }
            }
        });
    }

    private void createPopupMenu(int index){
        popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem();
        deleteItem.setText("Delete Markbook");
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //该操作需要做的事
                DataCenter.delete(index);
            }
        });
        popupMenu.add(deleteItem);

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
