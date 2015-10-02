/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rebateprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author achaturvedi
 */
class FileHandler {
   
    private static final String FILE_NAME = "rebate_processing_data_file.txt";
    private static final String TEMP_FILE = "temp420.txt";
    
    
    //data object is passed to this function, it writes the data to file
    public static boolean writeData(AppDataModel appDataModel) {
              
        String data = appDataModel.getFirstName()
                +"\t"
                +appDataModel.getMInitial()
                +"\t"
                +appDataModel.getLastName()
                +"\t"
                +appDataModel.getAddrLine1()
                +"\t"
                +appDataModel.getAddrLine2()
                +"\t"
                +appDataModel.getCity()
                +"\t"
                +appDataModel.getState()
                +"\t"
                +appDataModel.getZipCode()
                    +"\t"
                +appDataModel.getPhone()
                +"\t"
                +appDataModel.getEmail()
                +"\t"
                +appDataModel.getDateReceived()
                +"\t"
                +appDataModel.getPoa();
        
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(FILE_NAME, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(data);
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + FILE_NAME + "'");
            return false;
            // Or we could just do this:
            // ex.printStackTrace();
        }
        
        //if written correctly, then 
        return true;
    }
    
   public static boolean varifyDuplicate( String fullName) {
        AppDataModel appData = new AppDataModel();
        //read from the file and store the data in appData object
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FILE_NAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                if(line.contains(fullName)){
                    return false;
                }
            }   

            // Always close files.
            bufferedReader.close(); 
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILE_NAME + "'"); 
            return false;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILE_NAME + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
            return false;
        }
        
        return true;
    }
   
   /*called by GUI as soon as program loads, cuz GUI needs this data to show in the Dropdown for phone numenr and name*/
   public static ArrayList<AppDataModel> loadAllData () {
       ArrayList<AppDataModel> appDataList = new ArrayList();
       /* a temporary variables */
       AppDataModel appData;
       String[] allFields = new String[12];
       
       /**/
       try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FILE_NAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                appData = new AppDataModel();
                allFields = line.split("\\t");
                appData.setFirstName(allFields[0]);
                appData.setMInitial(allFields[1]);
                appData.setLastName(allFields[2]);
                appData.setAddrLine1(allFields[3]);
                appData.setAddrLine2(allFields[4]);
                appData.setCity(allFields[5]);
                appData.setState(allFields[6]);
                appData.setZipCode(allFields[7]);
                appData.setPhone(allFields[8]);
                appData.setEmail(allFields[9]);
                appData.setDateReceived(allFields[10]);
                appData.setPoa(Boolean.valueOf(allFields[11]));
                
                appDataList.add(appData);
                
            }   

            // Always close files.
            bufferedReader.close(); 
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILE_NAME + "'"); 
            return null;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILE_NAME + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
            return null;
        }
       
       /*read data from file line by line*/
       
       return appDataList;
   }
   
   //remove the entry from file that contains the name specified
   /*   I am gonna do this
   
        * Open the old file for reading
        * Open a new (temporary) file for writing
        * Iterate over the lines in the old file (probably using a BufferedReader)
        * For each line, check if it matches what you are supposed to remove
        * If it matches, do nothing
        * If it doesn't match, write it to the temporary file
        * When done, close both files
        * Delete the old file
        * Rename the temporary file to the name of the original file
   
    */
   
   public static boolean deleteData (String fullName) {
       try {
           
           FileReader fileReader = 
                new FileReader(FILE_NAME);
                
           // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
           
           /********************************/
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(TEMP_FILE, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            
            /*********************************/
              String line;
            
            while((line = bufferedReader.readLine()) != null) {
                if(line.contains(fullName)){  //don't write this line to new file
                }else{ //write line to this new file
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            
            /**********************************/
            bufferedReader.close();
            bufferedWriter.close();
            
            /*delete old file and rename the new file to old file name*/
            File file = new File(FILE_NAME);
            file.delete();
            
            File newFile = new File(TEMP_FILE);
            if (newFile.exists()) {
                newFile.renameTo(file);
            }
            
            
           
       }catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILE_NAME + "'"); 
            return false;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILE_NAME + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
            return false;
        }
       
       return true;
   }
    
    boolean modifyData(String fullName) {
        //modify the data based on the parameter passed
        
        
        //return true or fales based on the action done successfully
        return true;
    }
    
    int getLatestRecordNumber()
    {
        int n = 0;
        return n;
    }
    
}
