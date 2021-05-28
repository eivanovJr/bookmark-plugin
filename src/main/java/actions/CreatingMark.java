package actions;

import com.intellij.ide.bookmarks.Bookmark;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.IPopupChooserBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import data.DataCenter;
import data.MyBookmark;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class CreatingMark extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            Messages.showMessageDialog("The cursor is not in editor!", "Creating Mark", Messages.getWarningIcon());
        } else {
            Project current_project = event.getData(PlatformDataKeys.PROJECT);
            if (current_project == null) {
                return;
            }
            String proj_name = current_project.getName();

            VirtualFile vfile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
            assert vfile != null;
            String filename = Objects.requireNonNull(vfile.getName());
            Integer lineNumber = editor.getCaretModel().getLogicalPosition().line + 1;
            String refname = Messages.showInputDialog("Enter the name of the bookmark: ", "Bookmark Name", Messages.getInformationIcon());
            assert refname != null;
            if (refname.isEmpty() || !refname.matches("[a-zA-Z][a-zA-Z0-9 _.]*") || refname.length() > 25){
                Messages.showMessageDialog("Not appropriate mark name!", "Creating Mark", Messages.getWarningIcon());
                return;
            }
            lineNumber--;
            MyBookmark mbk = new MyBookmark(refname, vfile, lineNumber);
            DataCenter.add(mbk);

        }
    }
}
