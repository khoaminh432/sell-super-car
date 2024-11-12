/* author: lyhieunghia
 * 
 * 
 */

package carstore;


public interface IFeatures<T> {
    public abstract void display();
    public abstract void readFromFile();
    public abstract void writeToFile();
    public abstract void add();
    public abstract void delete(T item);
    public abstract void update();
    public abstract T search();
}
