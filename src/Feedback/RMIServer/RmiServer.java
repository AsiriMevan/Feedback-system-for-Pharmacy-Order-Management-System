package Feedback.RMIServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiServer 
{
	public RmiServer() throws Exception 
	{
		
	}

	public static void main(String args[]) 
	{
		System.out.println("Attempting to start the Nexas Feedback System Server...");
		try
		{
			DataBaseFunctions dbObject = new DataBaseFunctions();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("QuestionService", dbObject);

			System.out.println("Service started. Welcome to the Nexas Feedback System RMI Service!");

		} 
		catch (Exception e) 
		{
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
