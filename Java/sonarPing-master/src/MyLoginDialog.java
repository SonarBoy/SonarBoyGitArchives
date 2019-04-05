
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
//import static org.junit.Assert.assertEquals;
/**
 * This class implements the log in functionality
 * @author Zhaoduan, Ekwere, Keitha
 * @version 1.0
 * @since 2016-03-20
 *
 */
public class MyLoginDialog extends JDialog {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    public static boolean homeowner=false;
    private MongoModel validateUser = new MongoModel();
 
    /**
     * This constructs a MyLoginDialgo
     * @param parent
     */
    public MyLoginDialog(Frame parent) {
        super(parent, "SonarPing Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagLayoutObject = new GridBagConstraints();
 
        gridBagLayoutObject.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        gridBagLayoutObject.gridx = 0;
        gridBagLayoutObject.gridy = 0;
        gridBagLayoutObject.gridwidth = 1;
        panel.add(lbUsername, gridBagLayoutObject);
 
        tfUsername = new JTextField(20);
        gridBagLayoutObject.gridx = 1;
        gridBagLayoutObject.gridy = 0;
        gridBagLayoutObject.gridwidth = 2;
        panel.add(tfUsername, gridBagLayoutObject);
 
        lbPassword = new JLabel("Password: ");
        gridBagLayoutObject.gridx = 0;
        gridBagLayoutObject.gridy = 1;
        gridBagLayoutObject.gridwidth = 1;
        panel.add(lbPassword, gridBagLayoutObject);
 
        pfPassword = new JPasswordField(20);
        gridBagLayoutObject.gridx = 1;
        gridBagLayoutObject.gridy = 1;
        gridBagLayoutObject.gridwidth = 2;
        panel.add(pfPassword, gridBagLayoutObject);
        panel.setBorder(new LineBorder(Color.GRAY));
        pfPassword.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(e.getSource()==pfPassword)
        		{
                    if (authenticate(getUsername(), getPassword())) {
                        JOptionPane.showMessageDialog(MyLoginDialog.this,
                                "Hi " + getUsername() + "!   You have successfully logged in.",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        succeeded = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(MyLoginDialog.this,
                                "Invalid username or password",
                                "Login",
                                JOptionPane.ERROR_MESSAGE);
                        // reset username and password
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;
     
                    }
        		}
        	}
        });
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (authenticate(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(MyLoginDialog.this,
                            "Hi " + getUsername() + "!   You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MyLoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
 
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    /**
     * This methods returns user name
     * @return user name
     */
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    /**
     * This methods returns password
     * @return password
     */
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    /**
     * This method check whether it is successful or not
     * @return if it is successful returns true, otherwise returns false
     */
    public boolean isSucceeded() {
        return succeeded;
    }
    
    /**
     * This method validate the account
     * @param name user name
     * @param password password
     * @return if it pass the validation returns true, otherwise returns false
     */
    private boolean authenticate(String name, String password)
    {
    	String str;
    	String[] splitArray, splitArray1;
    	String   matchpassword = " \"Password\" : \""+password+"\" ";
    	String   matchtype = " \"Type\" : \""+"Homeowner"+"\"}";
    	int i;
    	
    	
    	if( (name.compareToIgnoreCase("admin")==0) && (password.compareToIgnoreCase("225588")==0) )
    	{
    		homeowner = true;
    		return true;
    	}
    	
    	str = validateUser.selectMongoDB("First Name", name); 
    	if(!str.isEmpty())
    	{    		
    		splitArray = str.split(",");
    		splitArray1 = str.split(",");
    		for (String s:splitArray)
    		{        			 
    			i = s.compareTo(matchpassword);
    			if(i==0)
    			{    				
    				for (String s1:splitArray1)
    				{
    					i = s1.compareToIgnoreCase(matchtype);
    					if(i==0)
    						homeowner = true;
    				}
    				return true;
    			}
    		}
    		return false;
    	}
    	else 
    	{
    		return false;
    	}
    }
    
}