/*author: lyhieunghia
 * 
 * 
 * 
 */
package carstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IdManager {
    private int nextId;

    private List<Integer> availableId = new ArrayList<>();
    //Constructor
    public IdManager(String FILE_NAME){
        if(!readIDsFromFile(FILE_NAME)){
            nextId=1;
        };
    }

    //Id Generator:BEGIN
    // Method to generate a unique customer ID.
    // Checks if there are any reusable IDs in availableCustomerID list:
    // - If available, removes and returns the smallest ID from the list.
    // - If not available, returns the next incremented customer ID.

    public int idGenerator(){
        if(!availableId.isEmpty()){
            return availableId.remove(0);
        }
        return nextId++;
    }
    //ID Generator: END

    //ReleaseID:BEGIN
    // Method to release an ID by adding it to the availableID list.
    public void releaseId(int id) {
        availableId.add(id);
        Collections.sort(availableId);
        for (Integer integer : availableId) {
            System.out.println(integer);
        }
    }
    //ReleaseID: END


    private boolean readIDsFromFile(final String FILE_NAME) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line=br.readLine();
            if(line==null){
                return false;
            }
            nextId=Integer.parseInt(line);
            line=br.readLine();
            if(line!=null){
                availableId=parseIdList(line);
            }
            return true; // If the file was read successfully
        } catch (FileNotFoundException e) {
            return false; // File does not exist or could not be opened
        } catch (IOException e) {
            return false; // Error occurred during reading
        }
    }
    

    // Method to parse a string of IDs to Integer List
    public static List<Integer> parseIdList(String idList) {
        List<Integer> ids = new ArrayList<>();
        if (!idList.isEmpty()) {
            String[] idArray = idList.split("\\|");
            for (String id : idArray) {
                ids.add(Integer.parseInt(id));
            }
        }
        return ids;
    }
    //END

    public boolean writeIDsToFile(String FILE_NAME) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Write the next available ID on the first line
            bw.write(String.valueOf(nextId));
            bw.newLine();
    
            // Write the available IDs on the second line, joined by '|'
            for (Integer id : availableId) {
                bw.write(String.valueOf(id)+"|");
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
   

}
