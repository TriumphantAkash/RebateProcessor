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

/**
 *
 * @author achaturvedi
 */
class FileHandler {
   
    private static final String FILE_NAME = "rebate_processing_data_file.txt";
    
    
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
    
   public static AppDataModel readData( String fullName) {
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
                System.out.println(line);
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
        
        return appData;
    }
    
    boolean modifyData(String fullName) {
        //modify the data based on the parameter passed
        
        
        //return true or fales based on the action done successfully
        return true;
    }
    
}
