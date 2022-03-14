package Feedback.RmiInterface;

import java.rmi.Remote;
import java.util.List;

import Feedback.Model.Admin;
import Feedback.Model.Analytics;
import Feedback.Model.Brand;
import Feedback.Model.Questionnaire;

public interface RemoteInterface extends Remote 
{
	/***
	 * 
	 * @return Questionnaire List
	 * @throws Exception
	 */
	public List<Questionnaire> getQuestions() throws Exception ;
	/**
	 * Read Brand list from the database
	 * 
	 * @return Brand list
	 * @throws Exception
	 */
	public List<Brand> BrandList() throws Exception ;
	/**
	 * check the admin data that client application provided with admin data in the
	 * database
	 * 
	 * @param admin
	 * @return the provided admin data is correct or not
	 * @throws Exception
	 */
	public Boolean signInData(Admin admin) throws Exception;

	/**
	 * Update the selected lecturer's answer count for each question
	 * 
	 * @param analytics
	 * @throws Exception
	 */
	public void setAnalytics(Analytics analytics) throws Exception;

	/**
	 * Read the answer count for each question of selected lecturer
	 * 
	 * @param lecId
	 * @return Analytics list
	 * @throws Exception
	 */
	public List<Analytics> getAnalytics(int brandId) throws Exception;

	/**
	 * quickchart.io API integration. This method can generate bar and pie charts according to the admin's choice.
	 * 
	 * @param chartType
	 * @param analyticsList
	 * @param questionId
	 * @return chart url
	 * @throws Exception
	 */
	public String chartConfigure(String chartType, List<Analytics> analyticsList, int questionId) throws Exception;

	/**
	 * Send email with attachments
	 * 
	 * @param toAddress
	 * @throws Exception
	 */
	public void sendEmailWithAttachments(String toAddress) throws Exception;


}
