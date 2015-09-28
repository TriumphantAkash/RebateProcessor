/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rebateprocessing;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author achaturvedi
 */
/*
This class is basically used to keep the file
opened throughout the prgram's execution
*/

public class GlobalFileManager {
    public static File getFileInstance(){
        // This does not actually create a file

        File file = new File("temp.txt");

         

        try {

            //create a new file if it doesn't exist already

            file.createNewFile();

     

        } catch (IOException e) {

            e.printStackTrace();
        }
        return file;

    }
}
