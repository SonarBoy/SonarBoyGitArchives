
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;
/**
 * This is the class which is between the MongMVC and MongoControl, implements information verification etc.
 * @author Zhaoduan, Ekwere, Keitha
 * @version 2.0
 * @since 2016-03-10
 */
public class MongoControl {
	private MongoView theView;
	private MongoModel theModel;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String type;
	
	/**
	 * This constructs a MongoControl with a specified View and Model
	 * @param theView
	 * @param theModel
	 */
	public MongoControl(MongoView theView, MongoModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addMongoButtonListener(new MongoButtonListener());		
	}
	
	/**
	 * This method validate the firstName, lastName, password and email which input from View
	 * @return if pass the validation, return true, otherwise return false
	 */
	public boolean validate()
	{
		boolean rt=false;
		String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		String EmailText;
		
		if( theView.firstName.getText().isEmpty() ) 
		{
			JOptionPane.showMessageDialog(null,"You need to input first name!");
			rt = false;
		}
		else if( theView.lastName.getText().isEmpty() ) 
		{
			JOptionPane.showMessageDialog(null,"You need to input last name!");
			rt = false;
		}
		else if( theView.password.getText().isEmpty() ) 
		{
			JOptionPane.showMessageDialog(null,"You need to input password!");
			rt = false;
		}
		else if( theView.email.getText().isEmpty() ) 
		{
			JOptionPane.showMessageDialog(null,"You need to input email!");
			rt = false;
			
		}else if( !theView.email.getText().isEmpty() )
		{
			EmailText = theView.email.getText();
			if(EmailText.matches(EMAIL_REGEX)){
				
				rt = true;
			}
			else {
				JOptionPane.showMessageDialog(null,"You need to input a correct email!");
				rt = false;
			}
		}
		else if( theView.type.getText().isEmpty() ) 
		{
			JOptionPane.showMessageDialog(null,"You need to input type!");
			rt = false;
		}
		else if ( !theView.type.getText().equals("DEPENDENT") )
		{
			JOptionPane.showMessageDialog(null,"You need to input type to be DEPENDENT!");
			rt = false;
		}
		else
		{		
			rt = true;
		}
		
		return rt;
	}
	
	/**
	 * This method clear the textfield which are in the View.
	 */
	public void clearFields(){
		theView.firstName.setText("");
		theView.lastName.setText("");
		theView.password.setText("");
		theView.email.setText("");
		theView.type.setText("");
	}
	
	/**
	 * This class responds the user activies like click 'Create','Delete' button in the View
	 * @author Zhaoduan
	 *
	 */
	class MongoButtonListener implements ActionListener
	{
		/**
		 * This method deals with the click activities that user click the button in View
		 */
		public void actionPerformed(ActionEvent e) 
		{
			String str;
			int dialogreturn;
			String key, value;
			boolean ret;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
						
			try
			{
				if(e.getActionCommand().equals("CreateButton"))
				{
					ret = validate();
					if(ret)
						theModel.insertMongoDB(firstName,lastName,email,password,type);
				}
				else if(e.getActionCommand().equals("deleteButton"))
				{
					key = theView.getFirstName();
					theModel.deleteMongoDB(key);
				}
				else if(e.getActionCommand().equals("updateButton"))
				{
					theModel.updateMongoDB(firstName, lastName, email, password,type);				
				}
				else if(e.getActionCommand().equals("browseButton"))
				{
					str = theModel.browseMongoDB(theView, theModel);
				}	
				else if(e.getActionCommand().equals("removeallButton"))
				{
					dialogreturn = theModel.removeallMongo();
					JOptionPane.showMessageDialog(null,"Remove all the data in MongoDB");	
				}				
				
				
				clearFields();
			}
			catch(Exception ex)
			{
				theView.displayErrorMessage("Mongo DB operation fail.");;
			}
		}
	}
}
