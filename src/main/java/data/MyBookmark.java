package data;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class MyBookmark {

    private String name;
    private VirtualFile file;
    private Integer column;


    public MyBookmark(String name, VirtualFile file, Integer column) {
        this.name = name;
        this.file = file;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public VirtualFile getFile() {
        return file;
    }

    public Integer getColumn() {
        return column;
    }


}
