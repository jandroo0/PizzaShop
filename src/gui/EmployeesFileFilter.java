package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class EmployeesFileFilter extends FileFilter {
    public EmployeesFileFilter() {
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String name = file.getName();
            String extension = Utils.getFileExtension(name);
            if (extension == null) {
                return false;
            } else {
                return extension.equals("stu");
            }
        }
    }

    public String getDescription() {
        return "Student Database Files (*.stu)";
    }
}