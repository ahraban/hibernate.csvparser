/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafeeder;

import datafeeder.filemanagement.CSVConvertor;
import datafeeder.filemanagement.FileManager;
import datafeeder.filemanagement.UserInterface;
import java.io.File;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Adam
 */
public class DataFeeder {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException Exception thrown for IO exceptions
     */
    public static void main(String[] args) throws IOException 
    {   
        //variables
        boolean appController = true;
        String inputFile = "C:\\Users\\Adam\\Documents\\- DEVELOPMENT\\- DATA\\inputdata.csv";
        String outputFolder = "C:\\Users\\Adam\\Documents\\- DEVELOPMENT\\- DATA\\- DONE\\";
        File soubor = new File(inputFile);
        
        
        //SessionFactory + User interface
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        UserInterface ui = new UserInterface(FileManager.doneFilesChecker(outputFolder));

        //application runtime
        if (appController = true)
        {
            //upload input data to database
            CSVConvertor.makeEmployeeX(soubor,outputFolder, ui, sessionFactory);
            CSVConvertor.makeCompanyX(soubor,outputFolder,ui, sessionFactory);

            //copy processed file + delete processed file
            FileManager.copyFile(soubor,outputFolder);
            soubor.delete();
        }
    }
    
}
