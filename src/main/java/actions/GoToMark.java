package actions;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import data.MyBookmark;
import org.jetbrains.annotations.NotNull;

public class GoToMark {
    public static void goto_mark(MyBookmark bookmark, Project current_project){
        if (!bookmark.getFile().exists()){
            Messages.showMessageDialog("File Doesn't exist", "Creating Mark", Messages.getWarningIcon());
            return;
        }
        var opening = new OpenFileDescriptor(current_project, bookmark.getFile(), bookmark.getColumn(), 0);
        var navigation = opening.setUseCurrentWindow(true);
        if (navigation.canNavigate()) {
            navigation.navigate(true);
        }

    }
}
