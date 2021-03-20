package taskTracking.services;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenPDFFile {
    private String fileDirectoryName;
    private String fileName;

    public OpenPDFFile(String fileDirectoryName,String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    public void openPDFFileKu() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
