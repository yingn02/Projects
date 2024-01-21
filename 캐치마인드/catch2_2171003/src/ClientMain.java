import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;

public class ClientMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel userInfo_panel = new JPanel();
	private JTextField nickname_txt; //닉네임
	private JTextField ip_txt; //IP 주소

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ClientMain();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/login_favicon.png"));
		setResizable(false);
		setTitle("캐치마인드");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 10, 400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		userInfo_panel.setBackground(new Color(0, 128, 255));
		userInfo_panel.setBounds(0, 108, 120, 122);
		contentPane.add(userInfo_panel);
		userInfo_panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel nickname_lbl = new JLabel("닉네임");
		nickname_lbl.setForeground(new Color(255, 255, 255));
		nickname_lbl.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 18));
		nickname_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		userInfo_panel.add(nickname_lbl);
		
		JLabel IP_lbl = new JLabel("IP 주소");
		IP_lbl.setForeground(new Color(255, 255, 255));
		IP_lbl.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 18));
		IP_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		userInfo_panel.add(IP_lbl);
		
		JLabel title_lbl = new JLabel("");
		title_lbl.setIcon(new ImageIcon("./img/login_title.png"));
		title_lbl.setFont(new Font("굴림", Font.BOLD, 25));
		title_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title_lbl.setBounds(0, 0, 384, 77);
		contentPane.add(title_lbl);
		
		nickname_txt = new JTextField();
		nickname_txt.setBackground(new Color(255, 255, 255));
		nickname_txt.setHorizontalAlignment(SwingConstants.CENTER);
		nickname_txt.setBounds(120, 125, 120, 25);
		contentPane.add(nickname_txt);
		nickname_txt.setColumns(10);
		
		ip_txt = new JTextField();
		ip_txt.setText("127.0.0.1");
		ip_txt.setHorizontalAlignment(SwingConstants.CENTER);
		ip_txt.setBounds(120, 185, 120, 25);
		contentPane.add(ip_txt);
		ip_txt.setColumns(10);
		
		JButton connect_btn = new JButton("시작");
		connect_btn.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 18));
		connect_btn.setBounds(275, 150, 90, 40);
		contentPane.add(connect_btn);
		
		ConnectAction connect_action = new ConnectAction();
		nickname_txt.addActionListener(connect_action);
		ip_txt.addActionListener(connect_action);
		connect_btn.addActionListener(connect_action);
	}
	
	class ConnectAction implements ActionListener { //로그인 버튼 누르면 서버로 접속
		@Override
		public void actionPerformed(ActionEvent e) {
			String nickname = nickname_txt.getText().trim();
			if(nickname.length() > 10) {
				nickname_txt.setText("10자 이내로 입력");
			}
			else if(nickname.length() == 0) {
				nickname_txt.setText("닉네임을 입력");
			}
			else {
				String ip = ip_txt.getText().trim();
				ClientGUI client = new ClientGUI(nickname, ip);
				setVisible(false);
			}
		}
	}

}
