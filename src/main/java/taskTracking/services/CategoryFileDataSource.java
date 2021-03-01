package taskTracking.services;

import taskTracking.model.WorksCategory.CategoryWork;
import taskTracking.model.WorksCategory.GeneralWork;

import java.io.*;

public class CategoryFileDataSource implements DataSource{
    private String fileDirectoryName;
    private String fileName;
    private DataList dataList;

    public CategoryFileDataSource(String fileDirectoryName, String fileName){
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

    public void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            CategoryWork categoryWork = new CategoryWork(data[0].trim(),Integer.parseInt(data[1].trim()), Integer.parseInt(data[2].trim()), Integer.parseInt(data[3].trim()), Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()));
            dataList.addCategory(categoryWork);
        }
        reader.close();
    }

    @Override
    public DataList getData() {
        try {
            dataList = new DataList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return dataList;
    }

    @Override
    public void setData(DataList list) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (CategoryWork work : list.getCategoryArrayList()) {
                String line = work.getName() + ","
                        + work.getAll()+ ","
                        + work.getCountGeneral() + ","
                        + work.getCountWeekly() + ","
                        + work.getCountForward() + ","
                        + work.getCountProject();

                writer.append(line);
                writer.newLine();

            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

}
