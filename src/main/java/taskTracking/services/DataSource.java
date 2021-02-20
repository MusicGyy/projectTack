package taskTracking.services;

public interface DataSource {
    DataList getData();
    void setData(DataList accountList);
//    void readData() throws IOException;
}
