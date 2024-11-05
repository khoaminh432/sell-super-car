package carstore;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IManagementUltility {
    public abstract void display();
    public abstract void readFromFile()throws IOException,FileNotFoundException;
    public abstract void writeToFile()throws IOException,FileNotFoundException;
    public abstract void add();
    public abstract void delete();
    public abstract void update();
}
