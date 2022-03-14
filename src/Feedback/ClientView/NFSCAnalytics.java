package Feedback.ClientView;


import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import Feedback.Controller.QuestionnaireController;
import Feedback.Model.Analytics;
import Feedback.Model.Brand;



public class NFSCAnalytics extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3321508601240021277L;
	private JPanel contentPane;

	QuestionnaireController questionnaireController = new QuestionnaireController();
	private int selectedBrandId = 1;
	private String link;
	private Image image;
	private URL url;
	private List<Brand> BrandList;
	private List<Analytics> getAnalyticsList = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NFSCAnalytics frame = new NFSCAnalytics();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public NFSCAnalytics() throws Exception
	{
		BrandList = questionnaireController.BrandList();
		
		try {
			getAnalyticsList = questionnaireController.getAnalytics(selectedBrandId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1100, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(35, 85, 1020, 710);
		contentPane.add(lblNewLabel_1);
		
		try {
			link = questionnaireController.chartConfiguration("bar", getAnalyticsList, 0);
			url = new URL(link);
			image = ImageIO.read(url);
			lblNewLabel_1.setIcon(new ImageIcon(image));

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Choice choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedBrandId = choice.getSelectedIndex() + 1;
				try {
					getAnalyticsList = questionnaireController.getAnalytics(selectedBrandId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					link = questionnaireController.chartConfiguration("bar", getAnalyticsList, 0);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		choice.setBounds(130, 20, 108, 18);
		for (int i = 0; i < BrandList.size(); i++) {
			choice.add(BrandList.get(i).getName());
		}
		contentPane.add(choice);
		
		JLabel lblNewLabel = new JLabel("Brand");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 20, 106, 23);
		contentPane.add(lblNewLabel);
		
		JLabel btnNewButton = new JLabel("");
		btnNewButton.setIcon(new ImageIcon(NFSCAnalytics.class.getResource("/Feedback/Images/gmail_32px.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					questionnaireController.sendEmailWithAttachments("uniccornuc@gmail.com");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Mail sended successfully");
			}
		});
		
		btnNewButton.setBounds(965, 20, 40, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_12 = new JLabel("");
		
		lblNewLabel_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NFSCDashboard introPage = new NFSCDashboard();
				introPage.setVisible(true);
				NFSCAnalytics.this.dispose();
			}
		});
		
		lblNewLabel_12.setIcon(new ImageIcon(NFSCAnalytics.class.getResource("/Feedback/Images/shutdown_32px.png")));
		lblNewLabel_12.setBackground(new Color(240, 240, 240));
		lblNewLabel_12.setBounds(1013, 22, 42, 35);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_12_1 = new JLabel("");
		lblNewLabel_12_1.setBackground(SystemColor.menu);
		lblNewLabel_12_1.setBounds(1003, 20, 42, 35);
		contentPane.add(lblNewLabel_12_1);

		JLabel lblC_1 = new JLabel("Question No");
		lblC_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblC_1.setBounds(562, 20, 106, 23);
		contentPane.add(lblC_1);

		lblC_1.setVisible(false);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(674, 20, 259, 18);
		choice_1.add("1 Comparison between our products and competitors’ products ?");
		choice_1.add("2 How do you rate our delivery System ?");
		choice_1.add("3 What would you think of the quality of our packaging ?");
		choice_1.add("4 Rate your overall satisfaction with the products you used from our Company ?");
		choice_1.add("5 How do you rate our Customer Care ?");
	
		choice_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					link = questionnaireController.chartConfiguration("pie", getAnalyticsList,
							choice_1.getSelectedIndex() + 1);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		contentPane.add(choice_1);

		choice_1.setVisible(false);

		JLabel lblNewLabel_12_2 = new JLabel("");
		lblNewLabel_12_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choice_1.setVisible(false);
				lblC_1.setVisible(false);
				try {
					link = questionnaireController.chartConfiguration("bar", getAnalyticsList, 0);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		lblNewLabel_12_2.setIcon(new ImageIcon(NFSCAnalytics.class.getResource("/Feedback/Images/bar_chart_32px.png")));
		lblNewLabel_12_2.setBackground(SystemColor.menu);
		lblNewLabel_12_2.setBounds(452, 10, 42, 35);
		contentPane.add(lblNewLabel_12_2);
		
		JLabel lblNewLabel_12_2_1 = new JLabel("");
		lblNewLabel_12_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				choice_1.setVisible(true);
				lblC_1.setVisible(true);
				try {
					link = questionnaireController.chartConfiguration("pie", getAnalyticsList, 1);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_12_2_1.setIcon(new ImageIcon(NFSCAnalytics.class.getResource("/Feedback/Images/pie_chart_32px.png")));
		lblNewLabel_12_2_1.setBackground(SystemColor.menu);
		lblNewLabel_12_2_1.setBounds(512, 10, 42, 35);
		contentPane.add(lblNewLabel_12_2_1);

		
	}
	
}
