import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI implements ActionListener {
	private JFrame frame;
	
	JButton btn12; //결제 버튼
	JTextArea txt1; //출력창
	int n; //결제확인
	ArrayList<String> orderList = new ArrayList<String>();
	ArrayList<Integer> priceList = new ArrayList<Integer>();
	
	public GUI() {
		frame = new JFrame("주문 정보 관리 프로그램");
		
		buildGUI();
		
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void buildGUI() {
		createMenu();//메뉴바

		frame.setLayout(new GridLayout(0, 2));
		frame.add(westPanel()); //계산
		frame.add(centerPanel()); //메뉴
	}
	
	private void createMenu() { //메뉴바
		JMenuBar mb = new JMenuBar();
		JMenu fm1 = new JMenu("    메 뉴    ");
		mb.add(fm1);
		
		JMenuItem [] menuList = new JMenuItem [3];
		String[] itemTitle = {"최근 영수증 불러오기", "총 매출액 출력", "종료"};

		MenuActionListener mal = new MenuActionListener();
		
		for(int i=0; i<menuList.length; i++) {
			menuList[i] = new JMenuItem(itemTitle[i]); //메뉴아이템 생성
			menuList[i].addActionListener(mal);
			fm1.add(menuList[i]);
		}
		
		frame.setJMenuBar(mb);
	}
	
	class MenuActionListener implements ActionListener{ //메뉴아이템 처리 액션 리스너

		public void actionPerformed(ActionEvent e) { //사용자가 선택한 메뉴아이템의 문자열 리턴
			String cmd = e.getActionCommand();
			Calculate c1 = new Calculate();
			
			switch(cmd) { //메뉴아이템의 종류 구분
				case "최근 영수증 불러오기" :
					c1.read();
					break;
					
				case "총 매출액 출력" :
					txt1.append("(현재 총 매출액은 " + c1.total + " 원 입니다.)\n");
					break;
					
				case "종료" :
					System.exit(0);
					break;
			}
		}
		
	}

	private JPanel westPanel() { 
		JPanel panel = new JPanel();
		
		txt1 = new JTextArea(38, 55);

		panel.add(txt1, BorderLayout.CENTER);
		panel.add(calPanel(), BorderLayout.SOUTH);
		
		txt1.append("(※주의사항: 초기화를 하지 않으면 데이터가 쌓여 중복 계산이 될 수 있습니다.※)\n\n");
		
		return panel;
	}
	
	private JPanel centerPanel() { 
		JPanel panel = new JPanel(new GridLayout(4, 0));
		
		JButton btn1 = new JButton("아메리카노(3000)");
		btn1.addActionListener(handler_menu1);
		JButton btn2 = new JButton("카페라떼(3000)");
		btn2.addActionListener(handler_menu2);
		JButton btn3 = new JButton("카라멜마끼야또(3000)");
		btn3.addActionListener(handler_menu3);
		JButton btn4 = new JButton("핫초코(3000)");
		btn4.addActionListener(handler_menu4);
		JButton btn5 = new JButton("유자차(3000)");
		btn5.addActionListener(handler_menu5);
		JButton btn6 = new JButton("녹차(3000)");
		btn6.addActionListener(handler_menu6);
		JButton btn7 = new JButton("아이스티(3000)");
		btn7.addActionListener(handler_menu7);
		JButton btn8 = new JButton("딸기스무디(3000)");
		btn8.addActionListener(handler_menu8);
		JButton btn9 = new JButton("망고스무디(3000)");
		btn9.addActionListener(handler_menu9);
		JButton btn10 = new JButton("청포도스무디(3000)");
		btn10.addActionListener(handler_menu10);
		
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);
		panel.add(btn7);
		panel.add(btn8);
		panel.add(btn9);
		panel.add(btn10);
		
		return panel;
	}

///////////////////////////////////////////////////////////////////////
	
	private JPanel calPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 2));
		
		JButton btn11 = new JButton("초기화");
		btn11.addActionListener(handler_clear);
		btn12 = new JButton("결제");
		//btn12.addActionListener(handler_cal);
		btn12.addActionListener(this);
		
		panel.add(btn11);
		panel.add(btn12);
		
		return panel;
	}
	
///////////////////////////////////////////////////////////////////////
	
	private ActionListener handler_menu1 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("아메리카노");
			priceList.add(3000);
			txt1.append("아메리카노(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu2 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("카페라떼");
			priceList.add(3000);
			txt1.append("카페라떼(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu3 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("카라멜마끼야또");
			priceList.add(3000);
			txt1.append("카라멜마끼야또(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu4 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("핫초코");
			priceList.add(3000);
			txt1.append("핫초코(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu5 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("유자차");
			priceList.add(3000);
			txt1.append("유자차(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu6 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("녹차");
			priceList.add(3000);
			txt1.append("녹차(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu7 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("아이스티");
			priceList.add(3000);
			txt1.append("아이스티(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu8 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("딸기스무디");
			priceList.add(3000);
			txt1.append("딸기스무디(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu9 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("망고스무디");
			priceList.add(3000);
			txt1.append("망고스무디(3000)\n");
		}
		
	};
	
	private ActionListener handler_menu10 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.add("청포도스무디");
			priceList.add(3000);
			txt1.append("청포도스무디(3000)\n");
		}
		
	};
	
	private ActionListener handler_clear = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			orderList.clear();
			priceList.clear();
			Calculate c1 = new Calculate();
			c1.pocket = 0;
			txt1.setText("");
		}
		
	};
		
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == btn12) {
			n = JOptionPane.showConfirmDialog(btn12, "결제 하시겠습니까?", "결제 확인", JOptionPane.YES_NO_OPTION);
			switch (n) {
			case JOptionPane.YES_OPTION: 
				Calculate c1 = new Calculate(orderList, priceList); //계산 생성자 
				c1.write(orderList, priceList);
				break;
			case JOptionPane.NO_OPTION: 
				txt1.append("(결제가 취소되었습니다.)\n"); break;
			}
		}
	
	}
	
}


