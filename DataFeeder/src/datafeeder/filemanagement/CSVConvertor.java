/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datafeeder.filemanagement;

import com.opencsv.CSVReader;
import datafeeder.datasource.Company;
import datafeeder.datasource.Employee;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Adam
 */
public class CSVConvertor 
{
    
    /**
     * Simple printing method
     * @param s variable to be printed
     */
    static void p(String s)
    {
        System.out.println(s);
    }
    
    
    
    File soubor = new File("C:\\Users\\Adam\\Documents\\- DEVELOPMENT\\- DATA\\inputdata.csv");
    CSVReader reader= null;
    ArrayList <String> table = new ArrayList<>();
    
    
    public CSVConvertor()
    {
        
    }
    
    
    
    /** if input file has more lines method returns true, not used
     * 
     * @param f file to be checked
     * @return true if file contains more tahn 1 record to be processed
     */
    public static boolean inputSizeScanner(File f)
    {
        CSVReader reader= null;
        ArrayList <String> table = new ArrayList<>();
        int c = 0;
        
        try
        {
            reader =  new CSVReader(new FileReader (f),',');
            String [] nextLine;
            while ((nextLine=reader.readNext()) != null)
            {
                for (String token : nextLine)
                {
                    table.add(token);
                }
            }
            c = table.size() ;
            p("Size" + c);
        }
            catch (Exception e)
        {
             e.printStackTrace();
        }
            if (c>7)
            {
                return true;
            } 
            else 
            {
                return false;
            }
    }
    
    
        
    /**
     * reads data from CSV, sends Employee data to EMPLOYEE_TABLE in DB. CSV can contain any amount of records (in predefined format)
     * 
     * @param f file to be processed (CSV with data)
     * @param outputFold folder where processed file should be copied to
     * @param userI UI will receive updates about data upload progress
     * @param sessFactory Hibernate SessionFactory used to upload data to DB
     */
    public static void makeEmployeeX (File f, String outputFold, UserInterface userI, SessionFactory sessFactory)
    {
        CSVReader reader= null;
        ArrayList <String> table = new ArrayList<>();
        Employee employee = new Employee();
        int c;
        try
        {
            // populate the arraylist with all data from *.CSV
            reader =  new CSVReader(new FileReader (f),',');
            String [] nextLine;
            while ((nextLine=reader.readNext()) != null)
            {
                for (String token : nextLine)
                {
                    table.add(token);
                }
            }

            /*
            read table size, divide by 7 (number of columns in single CSV record line)--> number of rows to be inserted in database
            */ 
            c= table.size() ;
            p("Size" + c);
            int s = (c/7)-1;
            int b = 0;
            
            //update IU
            userI.labelStatus.setText("Application status: processing data");
            
            //send all data to database
            for (int i = 0; i<=s;i++)
            {
                //set employee variables
                employee.setEmail(table.get(b+3));
                employee.setFirstName(table.get(b+4));
                employee.setSecondName(table.get(b+5));
                employee.setDate(new Date());
                b=b+7;
                
                //session to update data to database
                Session sess = sessFactory.openSession();
                sess.beginTransaction();
                sess.save(employee);
                sess.getTransaction().commit();
                sess.close();
            }
        }
        catch (Exception e)
            {
                 e.printStackTrace();
            }
        //update UI status + nr of files processed
        userI.labelStatus.setText("Application status: scanning folder to fetch data");
        userI.labelFiles.setText("Number of processed files: " + FileManager.doneFilesChecker(outputFold));
    }
    
    
    
    
        /**
     * reads data from CSV, sends Company data to COMPANY_TABLE in DB. CSV can contain any amount of records (in predefined format)
     * 
     * @param f file to be processed (CSV with data)
     * @param outputFold folder where processed file should be copied to
     * @param userI UI will receive updates about data upload progress
     * @param sessFactory Hibernate SessionFactory used to upload data to DB
     */
    public static void makeCompanyX (File f, String outputFold, UserInterface userI, SessionFactory sessFactory)
    {
        CSVReader reader= null;
        ArrayList <String> table = new ArrayList<>();
        Company company = new Company();
        int c;
        try
        {
            // populate the arraylist with all data from *.CSV
            reader =  new CSVReader(new FileReader (f),',');
            String [] nextLine;
            while ((nextLine=reader.readNext()) != null)
            {
                for (String token : nextLine)
                {
                    table.add(token);
                }
            }

            /*
            read table size, divide by 7 (number of columns in CSV record)--> number of rows to be inserted in database
            */ 
            c = table.size() ;
            int s = (c/7)-1;
            int b = 0;
            
            //update UI status
            userI.labelStatus.setText("Application status: processing data");
            for (int i = 0; i<=s;i++)
            {
                //set company variables
                company.setIco(Long.parseLong(table.get(b+0).replaceAll("\\s+","")));
                company.setCompanyName(table.get(b+1));
                company.setAddress(table.get(b+2));
                company.setEmployeeEmail(table.get(b+3));
                b=b+7;
                
                
                Session sess = sessFactory.openSession();
                sess.beginTransaction();
                sess.save(company);
                sess.getTransaction().commit();
                sess.close();
            }
        }
        catch (Exception e)
            {
                 e.printStackTrace();
            }
        
        //update UI status + nr of files processed
        userI.labelStatus.setText("Application status: scanning folder to fetch data");
        userI.labelFiles.setText("Number of processed files: " + FileManager.doneFilesChecker(outputFold));
    }
}
    
    

