/* author: lyhieunghia
 * 
 * 
 */

package carstore;


public interface IFeatures<T> {
    public abstract void display();
    public abstract void readFromFile();
    public abstract void writeToFile();
    public abstract boolean add();
    public abstract void delete(T item);
    public abstract void update(T item);
    public abstract T search();
}
