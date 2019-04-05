/*
 * Fix: delete and update function
 */
import java.net.UnknownHostException;


import javax.swing.JOptionPane;

import com.mongodb.*;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;
/**
 * This class deals with the all the requests related with Mongo Database
 * @author Zhaoduan, Akanimoh, Keitha
 * @vision v2.0
 * @since 2016-03-10
 */

public class MongoModel 
{
	private static DB dB;
	private static DBCollection dBCollection;
	private static BasicDBObject basicDBObject;
	
	/**
	 * This constructs MongoModel
	 */
	public MongoModel()
	{
		try {
			this.dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
			
			initialize();
			//this.basicDBObject = new BasicDBObject();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * This method initialize the connection of Mongo Database
	 */
	public void initialize(){
		this.dBCollection = dB.getCollection("UserDB");
		this.basicDBObject = new BasicDBObject();
	}
	
	/**
	 * This method detach the connection of Mongo Database
	 */
	public void detached(){
		this.dBCollection = null;
		this.basicDBObject = null;
	}
	
	/**
	 * This method insert a record in the Mongo Database
	 * @param first firstname
	 * @param last  lastname
	 * @param email email address
	 * @param pass  password
	 * @param type  type of the user, Home owner or dependent
	 */
	public void insertMongoDB(String first, String last, String email, String pass, String type)	
	{	
		try{
			initialize(); 
			this.basicDBObject.append("First Name",first);
			this.basicDBObject.append("Last Name",last);
			this.basicDBObject.append("Email",email);
			this.basicDBObject.append("Password",pass);
			this.basicDBObject.append("Type",type);
			this.dBCollection.insert(new DBObject[] {basicDBObject});
			detached();
			JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	
	}
	
	/**
	 * This method deletes a record from Mongo Database
	 * @param identifier
	 */
	public void deleteMongoDB(String identifier)
	{
		try{
			initialize();
			this.basicDBObject.put("First Name", identifier);
			this.dBCollection.remove(basicDBObject);
			JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
			detached();
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * This mothod updates a record which is in the Mongo Database
	 * @param first firstname
	 * @param last  lastname
	 * @param email email address
	 * @param pass  password
	 * @param type  type of user, Homeowner or dependent
	 */
	public void updateMongoDB(String first, String last, String email, String pass, String type)
	{
		try{
			initialize();
			this.basicDBObject.put("First Name", first);
			this.dBCollection.remove(basicDBObject);
			detached();
			
			initialize();
			this.basicDBObject = new BasicDBObject(); 
			
			this.basicDBObject.append("First Name",first);
			this.basicDBObject.append("Last Name",last);
			this.basicDBObject.append("Email",email);
			this.basicDBObject.append("Password",pass);
			this.basicDBObject.append("Type",type);
			
			this.dBCollection.insert(new DBObject[] {basicDBObject});
			
			JOptionPane.showMessageDialog(null, "Record Updated Successfully");
			
			detached();
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * This method inquires the Mongo Database with a key value
	 * @param key   Key for the Mongo DB
	 * @param value Value for the Mongo DB
	 * @return
	 */
	public String selectMongoDB(String key, String value)
	{
		String str = "";
		
		initialize();
		
		this.basicDBObject.put(key, value);
		DBCursor dbCursor = this.dBCollection.find(basicDBObject);
		
		while (dbCursor.hasNext()){ 
			str = str + " | " + dbCursor.next();
		}
		
		dbCursor.close();
		detached();
		
		
		return str;
	}
	
	
	/**
	 * This mothod browses all the user information that are stored in the Mongo Database
	 * @param theView  the view which calls the method
	 * @param theModel the model which relates the method
	 * @return
	 */
	public String browseMongoDB(MongoView theView, MongoModel theModel)
	{
		initialize();
		String str = "";
		String firstn, lastn, email, passwd, type/*, id*/;
		DBCursor dbCursor = dBCollection.find();
		DBObject obj;
		
		int rowCount = theView.tblmodelView.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			theView.tblmodelView.removeRow(i);
		}
		
		while (dbCursor.hasNext()) {
			obj = dbCursor.next();
			str = str +	"\n" + obj;
			firstn = (String)obj.get("First Name");
			lastn  = (String)obj.get("Last Name");
			email  = (String)obj.get("Email");
			passwd = (String)obj.get("Password");
			type   = (String)obj.get("Type");
			theView.tblmodelView.addRow(new Object[] {firstn, lastn, email, passwd, type/*, id*/});			
		}
		
		dbCursor.close();
		detached();
		return str;
	}
	
	/**
	 * This method inquires all the email information and return it as a string
	 * @return
	 */
	public String getAllEmails()
	{
		String str = "";
		String placeHolderString = "";
		String finalEmailString = "";
		
		
		BasicDBObject userTypeQuery = new BasicDBObject();
		BasicDBObject emailQuery = new BasicDBObject();
		
		
		userTypeQuery.append("Type", "DEPENDENT");
		emailQuery.append("Email","1");
		
		
		
		DBCursor dbCursor = dBCollection.find(userTypeQuery,emailQuery);
		
		while (dbCursor.hasNext()){ 
			str = ""+dbCursor.next();
			placeHolderString = (str.substring(str.indexOf(","), str.length()-1));
			finalEmailString += placeHolderString.substring(placeHolderString.indexOf(":"), placeHolderString.length()-1).substring(3) + ",";
		}
		
		
		dbCursor.close();
		
		return finalEmailString.substring(0, finalEmailString.length()-1);
	}
	
	/**
	 * This method removes the Mongo Database connection
	 * @return
	 */
	public int removeallMongo()
	{
		initialize();
		int i=0;
		
		this.dBCollection.remove(new BasicDBObject());
		detached();
		
		return i;
	}
	
	/**
	 * This methods is a empty method.
	 */
	public void close(){
	}

}
