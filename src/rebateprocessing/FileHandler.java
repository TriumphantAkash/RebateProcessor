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
    private static final String MOD_FILE = "temp421.txt";
    
    
    //data object is passed to this function, it writes the data to file
    public static ResponseEnum.Response writeData(AppDataModel appDataModel) {
              
        String data = appDataModel.getRecordNum()
                +"\t"
                +appDataModel.getFirstName()
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
            return ResponseEnum.Response.FILEWRITEERROR;
            // Or we could just do this:
            // ex.printStackTrace();
        }
        
        //if written correctly, then 
        return ResponseEnum.Response.OK;
    }
    
   public static ResponseEnum.Response varifyDuplicate( String fullName) {
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
                    return ResponseEnum.Response.DUPLICATEENTRY;
                }
            }   

            // Always close files.
            bufferedReader.close(); 
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILE_NAME + "'"); 
            return ResponseEnum.Response.FILENOTFOUND;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILE_NAME + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
            return ResponseEnum.Response.FILENOTFOUND;
        }
        
        return ResponseEnum.Response.OK;
    }
   
   /*called by GUI as soon as program loads, cuz GUI needs this data to show in the Dropdown for phone numenr and name*/
   public static ArrayList<AppDataModel> loadAllData () {
       ArrayList<AppDataModel> appDataList = new ArrayList();
       /* a temporary variables */
       AppDataModel appData;
       String[] allFields = new String[13];
       
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
                if(line.equals("")){
                    return null;
                }
                appData = new AppDataModel();
                allFields = line.split("\\t");
                appData.setRecordNum(Integer.parseInt(allFields[0]));
                appData.setFirstName(allFields[1]);
                appData.setMInitial(allFields[2]);
                appData.setLastName(allFields[3]);
                appData.setAddrLine1(allFields[4]);
                appData.setAddrLine2(allFields[5]);
                appData.setCity(allFields[6]);
                appData.setState(allFields[7]);
                appData.setZipCode(allFields[8]);
                appData.setPhone(allFields[9]);
                appData.setEmail(allFields[10]);
                appData.setDateReceived(allFields[11]);
                appData.setPoa(Boolean.valueOf(allFields[12]));
                
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
            if(file.delete()){
                System.out.println("file deleted");
            }else {
                System.out.println("file not deleted");
            }
            
            File newFile = new File(TEMP_FILE);
           // if (newFile.exists()) {
                
                if(newFile.renameTo(file)){
                    System.out.println("file renamed");
                }else{
                    System.out.println("file not renamed");
                }
            //}
            
            
           
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
    
   public static boolean modifyData(AppDataModel appDataModel) {
        //modify the data based on the parameter passed
        
       try {
           
           FileReader fileReader = 
                new FileReader(FILE_NAME);
                
           // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
           
           /********************************/
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(MOD_FILE, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            
            /*********************************/
              String line;
            
            while((line = bufferedReader.readLine()) != null) {
                if(line.contains(appDataModel.getRecordNum()+"\t")){  //change this line with the content of appDataModel and write to file
                    line = appDataModel.getRecordNum()
                +"\t"
                +appDataModel.getFirstName()
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
                    
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    
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
            
            File newFile = new File(MOD_FILE);
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
    
    public static int getLatestRecordNum()
    {
        String[] allFields = new String[13];
        int n = 0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FILE_NAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                if(line.equals("")){
                    return 0;
                }
                allFields = line.split("\\t");
                n = Integer.parseInt(allFields[0]);
            }
            
        }catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILE_NAME + "'"); 
            return 0;
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILE_NAME + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
            return 0;
        }
        return n;
    }
    
}
