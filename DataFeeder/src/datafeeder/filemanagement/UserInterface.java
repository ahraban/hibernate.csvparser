

package datafeeder.filemanagement;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author adam.hraban
 */
public class UserInterface  
{
    public JFrame frame = new JFrame("DataFeeder 1.0");
    public String status; //= "ready 1";
    int files = 0;
    public JLabel labelFiles = new JLabel("Number of processed files: ");
    public JLabel labelStatus = new JLabel("Application status: scanning folder to fetch data");
    public JButton button = new JButton();
    
    
    /**
     * Simple GUI to inform about application status (processing data or scaning input folder), number of filec processed and button to close tha application
     * 
     * @param fi  number of processed files to be displayed in UI (use method FileManager.doneFilesChecker)
     */
    
    public UserInterface(int fi)
    {   
        frame.setLayout(new GridLayout(5,1));
        labelFiles.setText("Number of processed files: " + fi);
        button.setText("Close application");
        button.addActionListener(e -> System.exit(0));
        frame.add(labelStatus);
        frame.add(labelFiles);
        frame.add(button);
 
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    /**
     * end UI
     */
    public void uiEnd()
    {
        frame.dispose();
    }
}
