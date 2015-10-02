
/*
   1)   This is basically our controller class that will have all kinds of validation
        functions and I think that we will pass the appDataModel object to each of these
        functions of this class
   2)   If everything seems ok (it passes the validation), then we will forward the same
        object to the file handling class, that will do stuff like file reading/writing
        with this data object
    
*/
package rebateprocessing;

import java.io.File;

/**
 *
 * @author achaturvedi
 */
public class RebateProcessingController {
    
 //this function will first check the validity of data, then pass it to File handler class   
 ResponseEnum.Response addData(AppDataModel appDataModel) {
     //validate the data, if validation issues then return false from here itself 
     String fullName = appDataModel.getFirstName()+"\t"+appDataModel.getMInitial()+"\t"+appDataModel.getLastName();
     if((FileHandler.varifyDuplicate(fullName) == ResponseEnum.Response.OK) || (FileHandler.varifyDuplicate(fullName) == ResponseEnum.Response.FILENOTFOUND)) {
         return FileHandler.writeData(appDataModel);
     }else {
         System.out.println("can not enter duplicate data");
         return ResponseEnum.Response.DUPLICATEENTRY;  //may be some enum to represent error type
     }
     //if validation ok then call addData function in the FileHandler class
    // FileHandler fileHandler = new FileHandler();
     
 }
 
 //boolean deleteData()
 boolean deleteData(String fullName) {
     return FileHandler.deleteData(fullName);
 }
   
    
}
