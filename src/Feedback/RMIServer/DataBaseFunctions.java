package Feedback.RMIServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;
import Feedback.Model.Admin;
import Feedback.Model.Analytics;
import Feedback.Model.Brand;
import Feedback.Model.Questionnaire;
import Feedback.RmiInterface.RemoteInterface;

public class DataBaseFunctions extends UnicastRemoteObject implements RemoteInterface
{

	private static final long serialVersionUID = 890263157264184289L;
	Connection connection = null;
	Statement statement = null;
	
	public DataBaseFunctions() throws Exception 
	{
		super();
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/nfs_database", "root", "");
			System.out.print("Connected database & ");

		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}

	}
	/***
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Questionnaire> getQuestions() throws Exception
	{
	ArrayList<Questionnaire> questionnaireList = new ArrayList<>();
	
	String sql = "SELECT * FROM questions";
	
	statement = connection.createStatement();
	ResultSet resultSet = statement.executeQuery(sql);
	
	while (resultSet.next())
	{
		Questionnaire questionnaire = new Questionnaire(resultSet.getInt("QuestionID"),
				resultSet.getString("Question"),resultSet.getString("Answer1"),
				resultSet.getString("Answer2"),resultSet.getString("Answer3"),
				resultSet.getString("Answer4"));
		questionnaireList.add(questionnaire);
	}
	resultSet.close();
	return questionnaireList;
	}
	
	/***
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Brand> BrandList() throws Exception 
	{
		ArrayList<Brand> brandList = new ArrayList<>();
		 String sql = "SELECT * FROM brand";
		 
		 statement =  connection.createStatement();
		 ResultSet resultSet = statement.executeQuery(sql);
		 
		 while (resultSet.next()) 
		 {
			Brand brandInformation = new Brand(resultSet.getInt("BrandID"),
					resultSet.getString("BrandName"));
			brandList.add(brandInformation);
		}
		 resultSet.close();
		 
		return brandList;
	}
	
	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public Boolean signInData(Admin admin) throws Exception {
		ResultSet resultSet = null;
		
		statement = connection.createStatement();
		String sql =" SELECT * FROM admin WHERE UserName = '" + admin.getName() + "' AND Password ='"
				+ admin.getPassword() + "'"; 
		
		try 
		{
			resultSet = statement.executeQuery(sql);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if (resultSet.next())
		{
			resultSet.close();
			return true;
		}
		else
		{
			resultSet.close();
			return false;
		}
	}
	
	/***
	 * Implementation of remote interface method.
	 */
	@Override
	public void setAnalytics(Analytics analytics) throws Exception {
		statement = connection.createStatement();
		
		String sql = "UPDATE analytics SET Answer1Count = '" + analytics.getAnswer1Count() + "', Answer2Count = " + " '"
				+ analytics.getAnswer2Count() + "', Answer3Count = " + " '" + analytics.getAnswer3Count()
				+ "', Answer4Count = " + " '" + analytics.getAnswer4Count() + "' WHERE BrandID = '"
				+ analytics.getBrandID() + "'AND QuestionID = '" + analytics.getQuestionID() + "'";
		
		statement.executeUpdate(sql);
	}
	
	/***
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Analytics> getAnalytics(int brandId) throws Exception
	{
		ArrayList<Analytics> analyticsList = new ArrayList<>();
		
		String sql = "SELECT * FROM analytics WHERE BrandID = '" + brandId + "'";
		
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) 
		{
			Analytics analytics = new Analytics(resultSet.getInt("BrandID"), resultSet.getInt("QuestionID"),
					resultSet.getInt("Answer1Count"), resultSet.getInt("Answer2Count"),
					resultSet.getInt("Answer3Count"), resultSet.getInt("Answer4Count"));
			analyticsList.add(analytics);
			
		}
		return analyticsList;
	}

	/***
	 * Implementation of remote interface method.
	 */
	 @Override
	public String chartConfigure(String chartType, List<Analytics> analyticsList, int questionId) throws Exception {
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		
		Analytics analytics1 = new Analytics(analyticsList.get(0).getBrandID(), analyticsList.get(0).getQuestionID(),
				analyticsList.get(0).getAnswer1Count(), analyticsList.get(0).getAnswer2Count(),
				analyticsList.get(0).getAnswer3Count(), analyticsList.get(0).getAnswer4Count());
		Analytics analytics2 = new Analytics(analyticsList.get(1).getBrandID(), analyticsList.get(1).getQuestionID(),
				analyticsList.get(1).getAnswer1Count(), analyticsList.get(1).getAnswer2Count(),
				analyticsList.get(1).getAnswer3Count(), analyticsList.get(1).getAnswer4Count());
		Analytics analytics3 = new Analytics(analyticsList.get(2).getBrandID(), analyticsList.get(2).getQuestionID(),
				analyticsList.get(2).getAnswer1Count(), analyticsList.get(2).getAnswer2Count(),
				analyticsList.get(2).getAnswer3Count(), analyticsList.get(2).getAnswer4Count());
		Analytics analytics4 = new Analytics(analyticsList.get(3).getBrandID(), analyticsList.get(3).getQuestionID(),
				analyticsList.get(3).getAnswer1Count(), analyticsList.get(3).getAnswer2Count(),
				analyticsList.get(3).getAnswer3Count(), analyticsList.get(3).getAnswer4Count());
		Analytics analytics5 = new Analytics(analyticsList.get(4).getBrandID(), analyticsList.get(4).getQuestionID(),
				analyticsList.get(4).getAnswer1Count(), analyticsList.get(4).getAnswer2Count(),
				analyticsList.get(4).getAnswer3Count(), analyticsList.get(4).getAnswer4Count());
		
		String str = "";
		
		if (chartType.equals("bar"))
		{
			str = "{\"chart\": {\"type\":\"" + chartType + "\",\"data\":"
					+ "{\"labels\":[\"Question 1\",\"Question 2\",\"Question 3\",\"Question 4\",\"Question 5\"],"
					+ "\"datasets\":[{\"label\":\"Very Good\",\"backgroundColor\":\"rgba(0, 255, 255, 0.5)\",\"borderColor\":\"rgb(0, 255, 255)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer1Count() + "," + analytics2.getAnswer1Count() + ","
					+ analytics3.getAnswer1Count() + "," + analytics4.getAnswer1Count() + ","
					+ analytics5.getAnswer1Count() + "]},"
					+ "{\"label\":\"Good\",\"backgroundColor\":\"rgba(0, 255, 0, 0.5)\",\"borderColor\":\"rgb(0, 255, 0)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer2Count() + "," + analytics2.getAnswer2Count() + ","
					+ analytics3.getAnswer2Count() + "," + analytics4.getAnswer2Count() + ","
					+ analytics5.getAnswer2Count() + "]},"
					+ "{\"label\":\"Poor\",\"backgroundColor\":\"rgba(255, 0, 255, 0.5)\",\"borderColor\":\"rgb(255, 0, 255)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer3Count() + "," + analytics2.getAnswer3Count() + ","
					+ analytics3.getAnswer3Count() + "," + analytics4.getAnswer3Count() + ","
					+ analytics5.getAnswer3Count() + "]},"
					+ "{\"label\":\"Very Poor\",\"backgroundColor\":\"rgba(128, 0, 0, 0.5)\",\"borderColor\":\"rgb(128, 0, 0)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer4Count() + "," + analytics2.getAnswer4Count() + ","
					+ analytics3.getAnswer4Count() + "," + analytics4.getAnswer4Count() + ","
					+ analytics5.getAnswer4Count() + "]}]},"
					+ "\"options\":{\"responsive\":true,\"legend\":{\"position\":\"top\"},\"title\":{\"display\":true,\"text\":\"Analytics Chart\"}}}}";
		}
		else
		{
			Analytics analytics = null;

			if (questionId == 1)
			{
				analytics = analytics1;
			} 
			else if (questionId == 2) 
			{
				analytics = analytics2;
			} 
			else if (questionId == 3) 
			{
				analytics = analytics3;
			} 
			else if (questionId == 4) 
			{
				analytics = analytics4;
			} 
			else if (questionId == 5) 
			{
				analytics = analytics5;
			}
			
			str = "{\"chart\": {\"type\":\"" + chartType + "\",\"data\":{\"datasets\":[{\"data\":["
					+ analytics.getAnswer1Count() + "," + analytics.getAnswer2Count() + ","
					+ analytics.getAnswer3Count() + "," + analytics.getAnswer4Count()
					+ "],\"backgroundColor\":[\"rgb(0, 255, 255)\",\"rgb(0, 255, 0)\",\"rgb(255, 0, 255)\",\"rgb(128, 0, 0)\"],\"label\":\"Dataset 1\"}],\"labels\":[\"Very Good\",\"Good\",\"Poor\",\"Very Poor\"]}}}";
		}
		
		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(str))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();
		
		HttpResponse<String> response = null;
		try 
		{
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		}
		catch (IOException | InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		String url = null;
		
		JSONObject jsonObject = new  JSONObject(response.body());
		url = (String) jsonObject.get("url");

		URL url_ = new URL(url);
		InputStream is = url_.openStream();
		OutputStream os = new FileOutputStream("chart.jpg");

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();
		
		return url;
	}
	 
	 /**
	 * Implementation of remote interface method.
	 * 
	 * This method create a new session with mail authenticator. If the
	 * authentication success it will able to create email and send to particular
	 * mail address.
	 * 
	 * 
	 */
	 
	 @Override
	public void sendEmailWithAttachments(String toAddress) throws AddressException,MessagingException
	 {
		 Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", 587);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.user", "mevansenanayakems@gmail.com");
			properties.put("mail.password", "19990428ms");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("mevansenanayakems@gmail.com", "19990428ms");
				}
			};
			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress("mevansenanayakems@gmail.com"));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("feedback Analytics reports");
			msg.setSentDate(new Date());

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Please find the attachments.", "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// adds attachments
			MimeBodyPart attachPart = new MimeBodyPart();

			try {
				attachPart.attachFile("/Feedback/Images/chart.jpg");
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);
	}
	 
	 
}
