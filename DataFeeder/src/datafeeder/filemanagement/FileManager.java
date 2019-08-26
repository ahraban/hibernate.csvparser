/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datafeeder.filemanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Adam
 */
public class FileManager 
{
    static void p(String s)
    {
        System.out.println(s);
    }
    
    
    
    
    /**
     * creates timestamp (used in processed files)
     * @return timestamp
     */
    public static String getTimeStamp()
    {
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");  
        String strDate = (String)dateFormat.format(date); 
        return strDate;
    }
    
    
    /**
     * Copy file to selected folder
     * @param f = input file to be copied
     * @throws IOException exception thrown
     */
    public static void copyFile(File f, String outFolder) throws IOException
    {
        //String fileName1 = "name1.csv";
        String fileName2 = outFolder + "inputdata_PROCESSED_" + FileManager.getTimeStamp() +".csv" ;
        File fileCopy = new File (fileName2);
        fileCopy.createNewFile();
        
        Files.copy(f.toPath(),fileCopy.toPath(),StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Zkopirovano");
    }
    
    /**
     * checks if theres input file that should be processed
     * @param f file to be checked
     * @return if file exists returns true else false
     */
    public static boolean inputFileExists (File f)
    {
        return f.exists();
    }
    
    
    /**
     * counts number of processed files in folder for processed files
     * @param pt directory that should be checked
     * @return numebr of processed files
     */
    public static int doneFilesChecker(String pt)
    {
        File f = new File (pt);
        int r = f.listFiles().length;
        //p("Number of files: " + f.listFiles().length);
        return r;
    }    
    
    //TST
    private String seznam [];
    private String slozkaCesta = "C:\\Users\\Adam\\Documents\\- DEVELOPMENT\\- DATA";
    
    // TST constructor
    public FileManager() throws IOException
    {
    
        

        
        //soubor        
        File soubor = new File("C:\\Users\\Adam\\Documents\\- DEVELOPMENT\\- DATA\\inputdata.csv");
        p("Soubor exists: "+ soubor.exists());

        //slozka
        File slozka = new File(slozkaCesta);
        seznam = slozka.list();
     
        p("Pocet souboru: " +seznam.length);
        p("Obsah slozky: " + seznam [0]);
    }        
}
