
/*
   1)   This is basically our controller class that will have all kinds of validation
        functions and I think that we will pass the appDataModel object to each of these
        functions of this class
   2)   If everything seems ok (it passes the validation), then we will forward the same
        object to the file handling class, that will do stuff like file reading/writing
        with this data object
    
*/
package rebateprocessing;

/**
 *
 * @author achaturvedi
 */
public class RebateProcessingController {
    
 //this function will first check the validity of data, then pass it to File handler class   
 boolean addData(AppDataModel appDataModel) {
     //validate the data, if ok then call addData function in the FileHandler class
     FileHandler fileHandler = new FileHandler();
     return fileHandler.addDataToFile(appDataModel);
 }

   
    
}
