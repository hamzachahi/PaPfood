package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;

/*
 * Created on Jan 24, 2007
 *
 */

public class GestionProprietes {

	public GestionProprietes() {
		super();
	}
	
	public void saveProperties(Properties props, String fileLocation, String comments) throws FileNotFoundException,
			IOException {
		OutputStream out = new FileOutputStream(fileLocation);
		props.store(out, comments);
		out.flush();
		out.close();
	}

	public Properties loadProperties(String propertiesFileLocation) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(propertiesFileLocation));
		return props;
	}	
	
	public void displayProperties(Properties props) {
		@SuppressWarnings("rawtypes")
		Iterator it = props.keySet().iterator();
		while (it.hasNext()) {
			String propertyName = (String) it.next();
			String propertyValue = props.getProperty(propertyName);
			System.out.println(propertyName + "=" + propertyValue);
		}
	}

	public static void main(String[] args) {
		GestionProprietes demo = new GestionProprietes();

		//Emplacement où sera stocké le fichier
		String propertiesFileLocation = "dao.properties";
		
		//On instancie un nouvel objet Properties
		Properties myProps = new Properties();
		//On y insère des paires [clé,valeur]
		myProps.setProperty("url","jdbc:mysql://localhost:3306/papfood");
		myProps.setProperty("driver","com.mysql.jdbc.Driver");
		myProps.setProperty("nomutilisateur","root");
		myProps.setProperty("motdepasse","0000");

		
		try {
		//	demo.saveProperties(myProps, propertiesFileLocation, "This is a demo on Properties by HackTrack");
			Properties loadedProps = demo.loadProperties(propertiesFileLocation);
			demo.displayProperties(loadedProps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
