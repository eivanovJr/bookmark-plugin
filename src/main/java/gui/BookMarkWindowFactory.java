package gui;

import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import data.DataCenter;
import org.jetbrains.annotations.NotNull;

public class BookMarkWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        BookmarkWindow Window = new BookmarkWindow(project, toolWindow);
        DataCenter.reset();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(Window.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
