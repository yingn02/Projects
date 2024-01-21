import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ClientGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea userList_txt;
	
	private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;
    private String nickname;
    
	public ClientGUI(String nickname, String ip) { //GUI
		setBackground(new Color(0, 0, 160));
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/login_favicon.png"));
		setResizable(false);
		setTitle("캐치마인드");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 10, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		createMenu(); //주제 선택, 소리설정
		contentPane.add(northPanel(), BorderLayout.NORTH); //시작 버튼
		contentPane.add(centerPanel(), BorderLayout.CENTER); //캔버스
		contentPane.add(southPanel(), BorderLayout.SOUTH); //채팅창
		contentPane.add(westPanel(), BorderLayout.WEST); //유저 목록
		contentPane.add(eastPanel(), BorderLayout.EAST); //도구
		
		//배경 음악
		setBGM(); 
        playBGM("1");
		
        try {
            socket = new Socket(ip, 7272);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());

            ClientThread client_thread = new ClientThread();
            client_thread.start();
            
            this.nickname = nickname; //닉네임 저장
            os.writeUTF("login/" + nickname);//서버에 닉네임 전송
            os.writeUTF("chat/" + nickname+ " 님이 입장하셨습니다.");//환영 인사
            
        } 
        catch (IOException e) {
        	System.out.println("errer: GUI");
        }
	}
	
    class ClientThread extends Thread { //클라이언트와 서버의 모든 통신
        public void run() {
			try {
				String server_signal;
	        	String[] my_signal;
				while ((server_signal = is.readUTF()) != null) {
					my_signal = server_signal.split("/");
					
					loginSignal(my_signal); //서버에서 유저들의 닉네임들을 받아 출력
            		chatSignal(my_signal); //다른유저가 보낸 채팅을 서버에서 받아 출력
            		canvasSignal(my_signal); //색이나 펜 굵기를 설정함
            		startSignal(my_signal); //시작 버튼 비활성화 
            		endSignal(my_signal); //시작 버튼 활성화 
            		turnSignal(my_signal); //자신의 턴인 사람만 그림을 그림
            		colorSignal(my_signal); //서버로부터 색 변경을 명령받음
            		boldSignal(my_signal); //서버로부터 펜 굵기 변경을 명령받음
            		resultSignal(my_signal); //서버에게 받은 점수 결과 출력
            		
            		//스크롤을 맨 아래로 이동시킴
                    JScrollBar verticalScrollBar = chatScroll_txt.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
				}
			} 
			catch (Exception e) {
				System.out.println("errer: signals(client)");
			}
        }
    }
    
    //////////////////////////////////////////////////category//////////////////////////////////////////////////
    
    String category = "1";
    String BGM = "1";
    
    private void createMenu() { //메뉴바
		JMenuBar mb = new JMenuBar();
		mb.setForeground(new Color(255, 255, 255));
		mb.setBackground(new Color(0, 0, 160));
		
		//주제 선택
		JMenu fm1 = new JMenu("   주제 선택   ");
		fm1.setForeground(new Color(255, 255, 255));
		fm1.setBackground(new Color(0, 0, 160));
		mb.add(fm1);
		
		JMenuItem [] menuList = new JMenuItem [9];
		String[] itemTitle = {"오버워치", "테일즈런너-인물", "테일즈런너-맵", "프로세카-버싱곡", "프로세카-레오니드곡", "프로세카-모모점곡", "프로세카-비배스곡", "프로세카-원더쇼곡", "프로세카-니고곡"};

		MenuActionListener mal = new MenuActionListener();
		
		for(int i=0; i<menuList.length; i++) {
			menuList[i] = new JMenuItem(itemTitle[i]);
			menuList[i].addActionListener(mal);
			fm1.add(menuList[i]);
		}
		
		//소리 설정
		JMenu fm2 = new JMenu("   소리 설정   ");
		fm2.setForeground(new Color(255, 255, 255));
		fm2.setBackground(new Color(0, 0, 160));
		mb.add(fm2);
		
		JMenuItem [] menuList2 = new JMenuItem [2];
		String[] itemTitle2 = {"BGM 켜기", "BGM 끄기"};

		MenuActionListener2 mal2 = new MenuActionListener2();
		
		for(int i=0; i<menuList2.length; i++) {
			menuList2[i] = new JMenuItem(itemTitle2[i]);
			menuList2[i].addActionListener(mal2);
			fm2.add(menuList2[i]);
		}
		
		setJMenuBar(mb);
	}
	
	class MenuActionListener implements ActionListener{ //게임시작 전, 주제 선택에 따른 설정
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			switch(cmd) {
				case "오버워치" :
					category = "1";
					break;
					
				case "테일즈런너-인물" :
					category = "2";
					break;
					
				case "테일즈런너-맵" :
					category = "3";
					break;
					
				case "프로세카-버싱곡" :
					category = "4";
					break;
					
				case "프로세카-레오니드곡" :
					category = "5";
					break;
					
				case "프로세카-모모점곡" :
					category = "6";
					break;	
					
				case "프로세카-비배스곡" :
					category = "7";
					break;
					
				case "프로세카-원더쇼곡" :
					category = "8";
					break;
					
				case "프로세카-니고곡" :
					category = "9";
					break;
					
			}
		}
	}
	
	class MenuActionListener2 implements ActionListener{ //소리 선택에 따른 설정
		public void actionPerformed(ActionEvent e) {
			String cmd2 = e.getActionCommand();
			
			switch(cmd2) {
				case "BGM 끄기" :
					if(BGM.equals("1")) {
						playBGM("0");
					}
					BGM = "0";
					
					break;
					
				case "BGM 켜기" :
					if(BGM.equals("0")) {
						playBGM("1");
					}
					BGM = "1";
					
					break;
			}
		}
	}
	
	//////////////////////////////////////////////////sound//////////////////////////////////////////////////
	AudioInputStream sound1;
	Clip clip1;
	
	public void setBGM() {
		try {
			sound1 = AudioSystem.getAudioInputStream(new File("./sound/main_BGM.wav"));
		} 
		catch (UnsupportedAudioFileException e) {
			System.out.println("errer: sound1)");
		} 
		catch (IOException e) {
			System.out.println("errer: sound1");
		}
		
		try {
			clip1 = AudioSystem.getClip();
		} 
		catch (LineUnavailableException e) {
			System.out.println("errer: clip1");
		}
	}
	
    public void playBGM(String on) { //소리 재생 함수 (무한 루프)
        try {
        	if(on.equals("1")) {
        		setBGM();
                clip1.stop();
                clip1.open(sound1);
                clip1.loop(Clip.LOOP_CONTINUOUSLY);
                clip1.start();
        	}
        	else if(on.equals("0")) {
                clip1.stop();
        	}
        }
        catch (Exception e) {
        	System.out.println("error:sound");
        }
    }
    
    public void playSound(String fileName) { //소리 재생 함수
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(sound);
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e) {
        	System.out.println("error:sound");
        }
    }
    
    //////////////////////////////////////////////////menu & game//////////////////////////////////////////////////
    
    public int drawer = 0;
    JButton start = new JButton(""); //게임시작
	
	private JPanel northPanel() { //시작 버튼
		JPanel start_panel = new JPanel(new BorderLayout());
		start_panel.setBackground(new Color(0, 0, 160));
		
		start.setIcon(new ImageIcon("./img/room_start.png"));
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
		start_panel.add(start, BorderLayout.CENTER);
		
		startAction start_action1 = new startAction();
        start.addActionListener(start_action1);
		
		return start_panel;
	}
	
	class startAction implements ActionListener { //선택된 주제에 맞게 시작, 기본값은 1
		@Override
		public void actionPerformed(ActionEvent e) {
            try {
				os.writeUTF("start" + category + "/"); 
				os.flush();
			}
            catch (IOException e1) {
            	System.out.println("errer: start");
			}
		}
	}
	
    private void startSignal(String[] my_signal) { //시작 버튼 비활성화 
		if (my_signal[0].equals("start")) {
			start.setEnabled(false);
		}
	}
	
    private void endSignal(String[] my_signal) { //시작 버튼 활성화 
		if (my_signal[0].equals("end")) {
			start.setEnabled(true);
		}
	}
    
    private void resultSignal(String[] my_signal) { //서버에게 받은 점수 결과 출력
		if (my_signal[0].equals("result")) {
			String result = "";
			
			for(int i=1; i<=my_signal.length-1; i+=2) { //결과 받아오기
				result += ("[" + my_signal[i] + "]님의 점수: " + my_signal[i+1] + "\n");
			}
			
			playSound("./sound/end_sound.wav");
			JOptionPane.showMessageDialog(this, "<게임 결과>\n" + result, "게임 결과", JOptionPane.INFORMATION_MESSAGE);
		}
	}
    
    private void turnSignal(String[] my_signal) { //자신의 턴인 사람만 그림을 그림
		if (my_signal[0].equals("turn")) {
			//캔버스초기화
			Graphics2D g = (Graphics2D) canvas_panel.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, canvas_panel.getWidth(), canvas_panel.getHeight());
			
			//그림 권한 부여
			drawer = 1;
			red_btn.setEnabled(true);
			orange_btn.setEnabled(true);
			yellow_btn.setEnabled(true);
			yellowgreen_btn.setEnabled(true);
			green_btn.setEnabled(true);
			skyblue_btn.setEnabled(true);
			blue_btn.setEnabled(true);
			purple_btn.setEnabled(true);
			pink_btn.setEnabled(true);
			brown_btn.setEnabled(true);
			apricot_btn.setEnabled(true);
			black_btn.setEnabled(true);
			gray_btn.setEnabled(true);
			bold1_btn.setEnabled(true);
			bold2_btn.setEnabled(true);
			bold3_btn.setEnabled(true);
			erase_btn.setEnabled(true);
			clear_btn.setEnabled(true);
			
			//제시어 알려주기
			JOptionPane.showMessageDialog(this, "제시어: '" + my_signal[1] + "'", "제시어", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (my_signal[0].equals("notturn")) {
			playSound("./sound/answer_sound.wav"); //정답이 나오면 다음턴으로 넘어가는데, 이때 모든 사람이 notturn으로 설정되는 순간이 존재하므로 정답소리 들을 수 있음
			
			//캔버스초기화
			Graphics2D g = (Graphics2D) canvas_panel.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, canvas_panel.getWidth(), canvas_panel.getHeight());
			
			//그림 권한 회수
			drawer = 0;
			red_btn.setEnabled(false);
			orange_btn.setEnabled(false);
			yellow_btn.setEnabled(false);
			yellowgreen_btn.setEnabled(false);
			green_btn.setEnabled(false);
			skyblue_btn.setEnabled(false);
			blue_btn.setEnabled(false);
			purple_btn.setEnabled(false);
			pink_btn.setEnabled(false);
			brown_btn.setEnabled(false);
			apricot_btn.setEnabled(false);
			black_btn.setEnabled(false);
			gray_btn.setEnabled(false);
			bold1_btn.setEnabled(false);
			bold2_btn.setEnabled(false);
			bold3_btn.setEnabled(false);
			erase_btn.setEnabled(false);
			clear_btn.setEnabled(false);
		}
	}

	//////////////////////////////////////////////////canvas//////////////////////////////////////////////////
	
    JPanel canvas_panel;
	private int oldX = -1, oldY = -1, newX = -1, newY = -1;
	
	
	private JPanel centerPanel() { //캔버스
		canvas_panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        
        canvas_panel.setPreferredSize(new Dimension(800, 600));
        canvas_panel.setBackground(Color.WHITE);

        canvas_panel.addMouseMotionListener(new MouseMotionAdapter() { //캔버스에 마우스 드래그하면 그려지고, 공유함
            @Override
            public void mouseDragged(MouseEvent e) {
            	newX = e.getX();
            	newY = e.getY();
                if (oldX != -1 && oldY != -1 && drawer == 1) {
                	Graphics2D g = (Graphics2D) canvas_panel.getGraphics();
                    g.drawLine(oldX, oldY, newX, newY);
                    sendDrawCommand(oldX, oldY, newX, newY);
                }
                oldX = newX;
                oldY = newY;
            }
        });

        canvas_panel.addMouseListener(new MouseAdapter() { //캔버스에서 마우스를 떼면 그려지지 않음
            @Override
            public void mouseReleased(MouseEvent e) {
            	oldX = -1;
            	oldY = -1;
            }
        });

		return canvas_panel;
	}
	
	private void sendDrawCommand(int oldX, int oldY, int newX, int newY) { //그림이 그려지고 있는 좌표를 서버로 전송
        try {
            os.writeUTF("canvas/" + oldX + "/" + oldY + "/" + newX + "/" + newY);
            os.flush();
        } catch (IOException e) {
        	System.out.println("errer: sendDrawCommend");
        }
    }
    
    private void canvasSignal(String[] my_signal) { //색이나 펜 굵기를 설정함
		if (my_signal[0].equals("canvas")) {
            int oldX2 = Integer.parseInt(my_signal[1]);
            int oldY2 = Integer.parseInt(my_signal[2]);
            int newX2 = Integer.parseInt(my_signal[3]);
            int newY2 = Integer.parseInt(my_signal[4]);
            SwingUtilities.invokeLater(() -> {
                Graphics2D g = (Graphics2D) canvas_panel.getGraphics();
                
                //펜 색 변경
                if(color.equals("red")) {
                	g.setColor(Color.RED);
                }
                else if(color.equals("orange")) {
                	g.setColor(Color.ORANGE);
                }
                else if(color.equals("yellow")) {
                	g.setColor(Color.YELLOW);
                }
                else if(color.equals("yellowgreen")) {
                	g.setColor(Color.GREEN);
                }
                else if(color.equals("green")) {
                	g.setColor(new Color(0, 200, 0));
                }
                else if(color.equals("skyblue")) {
                	g.setColor(Color.CYAN);
                }
                else if(color.equals("blue")) {
                	g.setColor(Color.BLUE);
                }
                else if(color.equals("purple")) {
                	g.setColor(new Color(200, 0, 200));
                }
                else if(color.equals("pink")) {
                	g.setColor(new Color(255, 150, 255));
                }
                else if(color.equals("brown")) {
                	g.setColor(new Color(160, 82, 45));
                }
                else if(color.equals("apricot")) {
                	g.setColor(new Color(255, 228, 196));
                }
                else if(color.equals("black")) {
                	g.setColor(Color.BLACK);
                }
                else if(color.equals("gray")) {
                	g.setColor(Color.LIGHT_GRAY);
                }
                else if(color.equals("erase")) { //지우개
                	g.setColor(Color.WHITE);
                }
                else {
                	g.setColor(Color.BLACK);
                }
                               
                //펜 굵기 변경
                if(bold.equals("bold1")) {
                	g.setStroke(new BasicStroke(1));
                }
                else if(bold.equals("bold2")) {
                	g.setStroke(new BasicStroke(2));
                }
                else if(bold.equals("bold3")) {
                	g.setStroke(new BasicStroke(4));
                }
                else if(bold.equals("clear")) { //전체지우기
                	g.setColor(Color.WHITE);
                    g.fillRect(0, 0, canvas_panel.getWidth(), canvas_panel.getHeight());
                }
                else {
                	g.setStroke(new BasicStroke(1));
                }
                
                g.drawLine(oldX2, oldY2, newX2, newY2);
            }); 
		} 
	}
	
	//////////////////////////////////////////////////chat//////////////////////////////////////////////////
	
	JPanel chat_panel;
	private JTextArea chatLog_txt;
    private JTextField chat_txt;
    private JScrollPane chatScroll_txt;
    
	private JPanel southPanel(){ //채팅창
		chat_panel = new JPanel(new BorderLayout());
		chat_panel.setBackground(new Color(255, 255, 255));
		
		chatScroll_txt = new JScrollPane();
		chatScroll_txt.setBounds(0, 0, 300, 244);
		chat_panel.add(chatScroll_txt, BorderLayout.CENTER);
		
		chatLog_txt = new JTextArea();
		chatLog_txt.setRows(7);
		chatLog_txt.setEditable(false);
		chatScroll_txt.setViewportView(chatLog_txt);

		chat_txt = new JTextField();
		chat_txt.setBackground(new Color(255, 255, 255));
		chat_panel.add(chat_txt, BorderLayout.SOUTH);
		
		chat_txt.addActionListener(new ActionListener() { //다른유저에게 채팅을 보내기 위해서 서버에 보냄
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(chat_txt.getText().length() > 0) {
						os.writeUTF("chat/" +"["+ nickname + "]:"+ chat_txt.getText());
						chat_txt.setText("");
					}
					
				} 
				catch (Exception e2) {
					System.out.println("error:chat");
				}
			}
		});
		
		return chat_panel;
	}
	
    private void chatSignal(String[] my_signal) { //다른유저가 보낸 채팅을 서버에서 받아 출력
		if (my_signal[0].equals("chat")) {
			chatLog_txt.append(my_signal[1] + "\n");
		} 
	}
	
	//////////////////////////////////////////////////user list//////////////////////////////////////////////////
	
	private JPanel westPanel() { //유저 목록
		JPanel userList_panel = new JPanel(new BorderLayout());
		userList_panel.setBackground(new Color(0, 0, 160));
		
		JLabel userList_lbl = new JLabel("  ===== 유저 목록 =====  ");
		userList_lbl.setBackground(new Color(0, 128, 255));
		userList_lbl.setForeground(new Color(255, 255, 255));
		userList_lbl.setFont(new Font("굴림", Font.PLAIN, 12));
		userList_panel.add(userList_lbl, BorderLayout.NORTH);
		
		userList_txt = new JTextArea();
		userList_txt.setBackground(new Color(0, 128, 255));
		userList_txt.setForeground(new Color(255, 255, 255));
		userList_txt.setFont(new Font("굴림", Font.BOLD, 12));
		userList_txt.setEditable(false);
		userList_panel.add(userList_txt, BorderLayout.CENTER);
		
		return userList_panel;
	}
	
    private void loginSignal(String[] my_signal) { //서버에서 유저들의 닉네임들을 받아 출력
		if (my_signal[0].equals("login")) {
			userList_txt.setText("");
			for(int i=1; i<=my_signal.length-1; i++) {
				userList_txt.append(my_signal[i] + "\n");
			}
		} 
	}
	
	//////////////////////////////////////////////////tool//////////////////////////////////////////////////
    
    public String color = "black";
    public String bold = "bold1";
    JButton red_btn, orange_btn, yellow_btn, yellowgreen_btn, green_btn, skyblue_btn, blue_btn, purple_btn, pink_btn, brown_btn, apricot_btn, black_btn, gray_btn;
    JButton erase_btn, clear_btn;
    JButton bold1_btn, bold2_btn, bold3_btn;
    
	private JPanel eastPanel() { //도구
		JPanel tool_panel = new JPanel(new GridLayout(6, 3));
		tool_panel.setBackground(new Color(0, 128, 255));
		tool_panel.setPreferredSize(new Dimension(200, 50));


		red_btn = new JButton(""); //빨강
		red_btn.setIcon(new ImageIcon("./img/room_red.png"));
		red_btn.setBorderPainted(false);
		red_btn.setContentAreaFilled(false);
		tool_panel.add(red_btn);
		redAction red_action = new redAction();
		red_btn.addActionListener(red_action);
		
		
		orange_btn = new JButton(""); //주황
		orange_btn.setIcon(new ImageIcon("./img/room_orange.png"));
		orange_btn.setBorderPainted(false);
		orange_btn.setContentAreaFilled(false);
		tool_panel.add(orange_btn);
		orangeAction orange_action = new orangeAction();
		orange_btn.addActionListener(orange_action);
		
		yellow_btn = new JButton(""); //노랑
		yellow_btn.setIcon(new ImageIcon("./img/room_yellow.png"));
		yellow_btn.setBorderPainted(false);
		yellow_btn.setContentAreaFilled(false);
		tool_panel.add(yellow_btn);
		yellowAction yellow_action = new yellowAction();
		yellow_btn.addActionListener(yellow_action);
		
		yellowgreen_btn = new JButton(""); //연두
		yellowgreen_btn.setIcon(new ImageIcon("./img/room_yellowgreen.png"));
		yellowgreen_btn.setBorderPainted(false);
		yellowgreen_btn.setContentAreaFilled(false);
		tool_panel.add(yellowgreen_btn);
		yellowgreenAction yellowgreen_action = new yellowgreenAction();
		yellowgreen_btn.addActionListener(yellowgreen_action);
		
		green_btn = new JButton(""); //초록
		green_btn.setIcon(new ImageIcon("./img/room_green.png"));
		green_btn.setBorderPainted(false);
		green_btn.setContentAreaFilled(false);
		tool_panel.add(green_btn);
		greenAction green_action = new greenAction();
		green_btn.addActionListener(green_action);
		
		skyblue_btn = new JButton(""); //하늘
		skyblue_btn.setIcon(new ImageIcon("./img/room_skyblue.png"));
		skyblue_btn.setBorderPainted(false);
		skyblue_btn.setContentAreaFilled(false);
		tool_panel.add(skyblue_btn);
		skyblueAction skyblue_action = new skyblueAction();
		skyblue_btn.addActionListener(skyblue_action);
		
		blue_btn = new JButton(""); //파랑
		blue_btn.setIcon(new ImageIcon("./img/room_blue.png"));
		blue_btn.setBorderPainted(false);
		blue_btn.setContentAreaFilled(false);
		tool_panel.add(blue_btn);
		blueAction blue_action = new blueAction();
		blue_btn.addActionListener(blue_action);
		
		purple_btn = new JButton(""); //보라
		purple_btn.setIcon(new ImageIcon("./img/room_purple.png"));
		purple_btn.setBorderPainted(false);
		purple_btn.setContentAreaFilled(false);
		tool_panel.add(purple_btn);
		purpleAction purple_action = new purpleAction();
		purple_btn.addActionListener(purple_action);
		
		pink_btn = new JButton(""); //분홍
		pink_btn.setIcon(new ImageIcon("./img/room_pink.png"));
		pink_btn.setBorderPainted(false);
		pink_btn.setContentAreaFilled(false);
		tool_panel.add(pink_btn);
		pinkAction pink_action = new pinkAction();
		pink_btn.addActionListener(pink_action);
		
		brown_btn = new JButton(""); //갈색
		brown_btn.setIcon(new ImageIcon("./img/room_brown.png"));
		brown_btn.setBorderPainted(false);
		brown_btn.setContentAreaFilled(false);
		tool_panel.add(brown_btn);
		brownAction brown_action = new brownAction();
		brown_btn.addActionListener(brown_action);
		
		apricot_btn = new JButton(""); //살구
		apricot_btn.setIcon(new ImageIcon("./img/room_apricot.png"));
		apricot_btn.setBorderPainted(false);
		apricot_btn.setContentAreaFilled(false);
		tool_panel.add(apricot_btn);
		apricotAction apricot_action = new apricotAction();
		apricot_btn.addActionListener(apricot_action);
		
		black_btn = new JButton(""); //검정
		black_btn.setIcon(new ImageIcon("./img/room_black.png"));
		black_btn.setBorderPainted(false);
		black_btn.setContentAreaFilled(false);
		tool_panel.add(black_btn);
		blackAction black_action = new blackAction();
		black_btn.addActionListener(black_action);
		
		gray_btn = new JButton(""); //회색
		gray_btn.setIcon(new ImageIcon("./img/room_gray.png"));
		gray_btn.setBorderPainted(false);
		gray_btn.setContentAreaFilled(false);
		tool_panel.add(gray_btn);
		grayAction gray_action = new grayAction();
		gray_btn.addActionListener(gray_action);
		
		//
		
		erase_btn = new JButton(""); //지우개(흰색)
		erase_btn.setIcon(new ImageIcon("./img/room_eraser.png"));
		erase_btn.setBorderPainted(false);
		erase_btn.setContentAreaFilled(false);
		tool_panel.add(erase_btn);
		eraseAction erase_action = new eraseAction();
		erase_btn.addActionListener(erase_action);
		
		clear_btn = new JButton(""); //전체지우기
		clear_btn.setIcon(new ImageIcon("./img/room_clear.png"));
		clear_btn.setBorderPainted(false);
		clear_btn.setContentAreaFilled(false);
		tool_panel.add(clear_btn);
		clearAction clear_action = new clearAction();
		clear_btn.addActionListener(clear_action);
		
		//
		
		bold1_btn = new JButton(""); //얇은 펜
		bold1_btn.setIcon(new ImageIcon("./img/room_bold1.png"));
		bold1_btn.setBorderPainted(false);
		bold1_btn.setContentAreaFilled(false);
		tool_panel.add(bold1_btn);
		bold1Action bold1_action = new bold1Action();
		bold1_btn.addActionListener(bold1_action);
		
		bold2_btn = new JButton(""); //중간 펜
		bold2_btn.setIcon(new ImageIcon("./img/room_bold2.png"));
		bold2_btn.setBorderPainted(false);
		bold2_btn.setContentAreaFilled(false);
		tool_panel.add(bold2_btn);
		bold2Action bold2_action = new bold2Action();
		bold2_btn.addActionListener(bold2_action);
		
		bold3_btn = new JButton(""); //굵은 펜
		bold3_btn.setIcon(new ImageIcon("./img/room_bold3.png"));
		bold3_btn.setBorderPainted(false);
		bold3_btn.setContentAreaFilled(false);
		tool_panel.add(bold3_btn);
		bold3Action bold3_action = new bold3Action();
		bold3_btn.addActionListener(bold3_action);
		
		return tool_panel;
	}
	
    private void colorSignal(String[] my_signal) { //서버로부터 색 변경을 명령받음
		if (my_signal[0].equals("color")) {       
                color = my_signal[1];
		} 
	}
    
    private void boldSignal(String[] my_signal) { //서버로부터 펜 굵기 변경을 명령받음
		if (my_signal[0].equals("bold")) {       
                bold = my_signal[1];
		} 
	}
	
	//red
	class redAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "red");
			} catch (IOException e1) {
				System.out.println("error:red");
			}
		}
	}
	
	//orange
	class orangeAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "orange");
			} catch (IOException e1) {
				System.out.println("error:orange");
			}
		}
	}
	
	//yellow
	class yellowAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "yellow");
			} catch (IOException e1) {
				System.out.println("error:yellow");
			}
		}
	}

	//yellowgreen
	class yellowgreenAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "yellowgreen");
			} catch (IOException e1) {
				System.out.println("error:yellowgreen");
			}
		}
	}
	
	//green
	class greenAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "green");
			} catch (IOException e1) {
				System.out.println("error:green");
			}
		}
	}
	
	//skyblue
	class skyblueAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "skyblue");
			} catch (IOException e1) {
				System.out.println("error:skyblue");
			}
		}
	}	
		
	//blue
	class blueAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "blue");
			} catch (IOException e1) {
				System.out.println("error:blue");
			}
		}
	}		
		
	//purple
	class purpleAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "purple");
			} catch (IOException e1) {
				System.out.println("error:purple");
			}
		}
	}
	
	//pink
	class pinkAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "pink");
			} catch (IOException e1) {
				System.out.println("error:pink");
			}
		}
	}		
	
	//brown
	class brownAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "brown");
			} catch (IOException e1) {
				System.out.println("error:brown");
			}
		}
	}	
	
	
	//apricot
	class apricotAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "apricot");
			} catch (IOException e1) {
				System.out.println("error:apricot");
			}
		}
	}
		
	//black
	class blackAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "black");
			} catch (IOException e1) {
				System.out.println("error:black");
			}
		}
	}
	
	//gray
	class grayAction implements ActionListener { //서버에 색 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "gray");
			} catch (IOException e1) {
				System.out.println("error:gray");
			}
		}
	}
	
	//
	
	//erase
	class eraseAction implements ActionListener { //서버에 펜에서 지우개로 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("color/" + "erase");
			} catch (IOException e1) {
				System.out.println("error:erase");
			}
		}
	}
	
	//clear
	class clearAction implements ActionListener { //서버에 캔버스 전제지우기를 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("bold/" + "clear");
			} catch (IOException e1) {
				System.out.println("error:clear");
			}
		}
	}
	
	//
	
	//bold1
	class bold1Action implements ActionListener { //서버에 펜 굵기 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("bold/" + "bold1");
			} catch (IOException e1) {
				System.out.println("error:bold1");
			}
		}
	}
	
	//bold2
	class bold2Action implements ActionListener { //서버에 펜 굵기 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("bold/" + "bold2");
			} catch (IOException e1) {
				System.out.println("error:bold2");
			}
		}
	}
	
	//bold3
	class bold3Action implements ActionListener { //서버에 펜 굵기 변경을 요구
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				os.writeUTF("bold/" + "bold3");
			} catch (IOException e1) {
				System.out.println("error:bold3");
			}
		}
	}			
}
