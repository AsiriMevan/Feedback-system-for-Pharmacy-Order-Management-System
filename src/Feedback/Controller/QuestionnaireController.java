package Feedback.Controller;

import java.rmi.Naming;
import java.util.List;

import Feedback.Model.Admin;
import Feedback.Model.Analytics;
import Feedback.Model.Brand;
import Feedback.Model.Questionnaire;
import Feedback.RmiInterface.RemoteInterface;
/***
 * 
 * @author Mevan
 *
 */
public class QuestionnaireController 
{
	RemoteInterface remoteInterface;
	
	public QuestionnaireController()
	{
		try 
		{
			remoteInterface = (RemoteInterface) Naming.lookup("rmi://localhost/QuestionService");
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public List<Brand> BrandList() throws Exception 
	{
		return remoteInterface.BrandList();
	}
	
	public List<Questionnaire> getQuestions() throws Exception 
	{
		return remoteInterface.getQuestions();
	}
	public List<Analytics> getAnalytics(int BrandId) throws Exception 
	{
		return remoteInterface.getAnalytics(BrandId);
	}

	public void updateAnalytics(Analytics analytics)throws Exception 
	{
		remoteInterface.setAnalytics(analytics);	
	}
	
	public Boolean signIn(Admin admin) throws Exception {
		return remoteInterface.signInData(admin);
	}
	public String chartConfiguration(String chartType, List<Analytics> analyticsList, int questionId) throws Exception 
	{
		return remoteInterface.chartConfigure(chartType, analyticsList, questionId);
	}
	public void sendEmailWithAttachments(String toAddress) throws Exception 
	{
		remoteInterface.sendEmailWithAttachments(toAddress);
	}
}
