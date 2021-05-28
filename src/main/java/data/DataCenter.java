package data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class DataCenter {
    public static List<MyBookmark> bookmark_list = new LinkedList<>();
    public static String[] column_name = {"Bookmark"};
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, column_name);

    public static void add(MyBookmark bkm){
        DataCenter.bookmark_list.add(bkm);
        DataCenter.TABLE_MODEL.addRow(new String[]{bkm.getName()});
    }

    public static void reset() {
        bookmark_list.clear();
        TABLE_MODEL.setDataVector(null, column_name);
    }

    public static void delete(int index){
        bookmark_list.remove(index);
        TABLE_MODEL.removeRow(index);
    }
}
