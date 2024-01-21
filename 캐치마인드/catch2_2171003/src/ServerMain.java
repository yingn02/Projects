import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

public class ServerMain{
	private Vector<UserThread> client_list = new Vector<>();
    private ServerSocket server_socket;
    private Socket client_socket;
    
    private ArrayList<String> nicknames = new ArrayList<String>(); //닉네임들
    public int start = 0; //시작했는가
    public int end = 0; //끝났는가
    public int answer = 0; //정답이 나왔는가
    public int turn = 1; //몇번이 그릴 차례인가
    public String[] words = new String[10]; //문제 배열
    public int answer_word = 0; //문제 인덱스
    public String[] category1 = {"디바", "둠피스트", "라마트라", "라인하르트", "레킹볼", "로드호그", "마우가", "시그마", "오리사", "윈스턴", "자리야", "정커퀸", "겐지", "리퍼", "메이", "바스티온", "소전", "솔저76", "솜브라", "시메트라", "애쉬", "에코", "위도우메이커", "정크랫", "캐서디", "토르비욘", "트레이서", "파라", "한조", "라이프위버", "루시우", "메르시", "모이라", "바티스트", "브리기테", "아나", "일리아리", "젠야타", "키리코", "66번국도", "감시기지지브롤터", "도라도", "리알토", "샴발리수도원", "서킷로얄", "쓰레기촌", "하바나", "눔바니", "미드타운", "블리자드월드", "아이헨발데", "왕의길", "파라이수", "할리우드", "남극반도", "네팔", "리장타워", "사모아", "부산", "오아시스", "일리오스", "뉴퀸스트리트", "이스페란사", "콜로세오", "뉴정크시티", "수라바사", "말레벤토", "샤토기야르", "카네자카", "페트라", "볼스카야인더스트리", "아누비스신전", "파리", "하나무라", "호라이즌달기지", "검은숲", "네크로폴리스", "카스티요", "탐사기지남극", "아유타야"}; //카테고리1 - 오버워치
    public String[] category2 = {"초원", "밍밍", "리나", "빅보", "DnD", "나르시스", "마키", "러프", "히든러프", "바다", "카이", "유키", "쿠로", "아벨", "하루", "베라", "손오공", "시호", "루시", "미호", "알", "하랑", "라라", "연오", "블러디베라", "시오넬", "담연", "클로에", "앙리3세", "닥터헬", "문어아빠", "문어엄마", "도마뱀삼촌", "도마뱀약혼녀", "마도로스죠"}; //카테고리2 - 테일즈런너-인물
    public String[] category3 = {"슈퍼점프", "스피드", "허들", "블럭", "철인3종경기", "빙하시대", "혼돈의 지역", "잊혀진천사신전외전", "초스피드튜브", "스페이스익스트림", "학교괴담", "타이핑스트라이커", "도깨비가라사대", "도깨비와춤을", "대시블럭", "스페이스대시블럭", "가든페스티벌", "좌충우돌점심시간", "드림스타디움", "기쁨의왕국탐험대", "곰발바닥섬", "허둥지둥출근길", "아이스넷이서한마음", "해와달", "흥부와놀부", "제크와콩나무", "설녀를찾아서", "복숭아동자", "피터팬블럭", "복숭아흥부놀부", "인어공주", "피노키오의모험", "피리부는사나이", "알리바바와40인의도적", "개구리왕자", "알라딘", "피터팬", "신데렐라의유리구두", "신비아파트", "성냥팔이소녀", "오즈의마법사", "설녀의나라로", "넷이서한마음", "토끼와거북이", "문어아빠의분노", "문어엄마의분노", "도마뱀삼촌의분노", "도마뱀삼촌의위기", "마도로스죠의습격", "삼촌과아빠의협공", "셋이서화났네", "세뇌당한문어아빠", "DJ문어아빠8비트", "DJ문어아빠16비트", "전설의삼바댄스", "전설의삼바카니발", "락에빠진마도로스죠", "락에홀린마도로스", "문어할배의대노", "도마뱀할배의위기", "문어청년의반항", "도마뱀청년의반항", "달려라암산왕", "서둘러라암산왕", "달려라세계왕", "달려라과목왕", "화가난빨간모자", "우마왕의도주", "성냥팔이소녀의분노", "혼돈의설국", "혼돈의우주", "닥터헬연구소", "박스위를샤샤샤", "초초초스피드", "바람의나라", "앙리성지하감옥", "위험한공놀이", "짬뽕킹", "돌아온짬뽕킹", "삼도천건너기"}; //카테고리3 - 테일즈런너-맵
    public String[] category4 = {"tellyourworld", "blessing", "alive", "넥스트네스트", "멜트", "하츠네미쿠의소실", "월드이즈마인", "handinhand", "gimmegimme", "정키나이트타운오케스트라", "ontherocks", "39뮤직", "leia", "롤링걸", "겉과속의러버즈", "언노운마더구스", "사랑받지못해도네가있어", "oddsends", "그린라이츠세레나데", "헬로플래닛", "천본앵", "블레스유어브레스", "월즈엔드댄스홀", "울려퍼져라", "brandnewday", "미쿠미쿠하게해줄게", "하츠네미쿠의격창", "꽃을노래하다", "거품미래", "그랬지", "magicnumber", "노심융해", "머신건포엠돌", "친애하는도플갱어에게", "토키오펑카", "누군가의심장이될수있다면", "우견의나비", "식오브하우스", "하츠네천지개벽신화", "악의딸", "악의하인", "떠나는사람들의왈츠", "라스트스코어", "상드리용", "너의색깔마린스노우", "쥬다스", "miku", "마트료시카", "도넛홀", "모래의행성", "루카루카나이트피버", "피아노포르테스캔들", "천년의독주가", "oneself", "블랙록슈터", "미완성찬가", "뇌장작렬걸", "우리들의16bit전쟁", "프로일라인비블리오테카", "그래도괜찮아", "퓨처이브", "언해피리플레인", "더블래리어트", "인비저블", "badendnight", "여기는행복안심위원회입니다", "amara", "하나둘하고셋에", "changeme", "파라디클로로벤젠", "별가루유토피아", "앗타카이토", "양지의찰나", "마법같은뮤직", "what'suppop", "썩은외도와초콜릿", "칸타렐라", "freelytomorrow", "린쨩나우", "새벽과반딧불이", "ph", "goodbye", "snowmix", "세카이쨩과카후쨩의장보기합소곡", "천사의날개", "copycat", "레이니스노우드롭", "whereshallwego", "마음", "스트로보라스트", "팔레트에는네가가득", "q", "야미나베", "39", "bluestar", "에필로그에넌없어", "highlight", "트래시앤드트래시", "이름없는혁명", "악식녀콘치타", "잠재우는공주로부터의선물", "엔비자카의재봉사", "악덕의저지먼트", "sharingtheworld", "이터널아리아", "인생", "wonderstyle", "스타더스트메들리", "underwater", "afterglow", "심박페어링", "decade", "신인류", "즌다파티나잇", "discono39", "블루플래닛", "hero", "circuspanic", "럭키오브", "아임마인", "뜻대로", "돼지가되어yeahyeah", "데빌이아닌걸", "츠가이코가라시", "dear", "coolmedown", "onon", "세카이", "와와월드", "군청찬가", "journey", "neo", "엔드마크에희망과눈물을더하며", "theemperror", "don'tfightthemusic", "아이덴티티", "푸른색물감", "코스모스파이스", "도쿠가와컵누들금지령", "사라만다", "bethemusic"};
    public String[] category5 = {"로키", "테오", "히바나", "내일의밤하늘초계반", "푸르게달려라", "갯나리해저담", "드라마트루기", "타임머신", "fromytoy", "가지마", "육조년과하룻밤이야기", "아지랑이데이즈", "로스트원의호곡", "ray", "helloworld", "고스트룰", "심박수0822", "우리는아직언더그라운드", "나팔꽃질무렵에", "로스트앤파운드", "calc", "천성의약함", "밤새도록널생각해", "helloworker", "어트랙트라이트", "쾌청", "glow", "서머타임레코드", "천악", "needle", "스텔라", "날이개길기다려", "1", "프롬도쿄", "유성의펄스", "stageofsekai", "peakypeaky", "오더메이드", "테라테라", "voices", "thewall", "후레이", "flyway", "상생", "별을잇다", "purpose"};
    public String[] category6 = {"해피신디사이저", "nostalogic", "니아", "누덕누덕스타카토", "비바해피", "멜티랜드나이트메어", "연애재판", "밀크크라운온소네치카", "드리밍츄츄", "로미오와신데렐라", "지구최후의고백을", "마슈마리", "happyhalloween", "소녀레이", "꼭두각시피에로", "마음예보", "달링댄스", "뱀파이어", "토리노코시티", "론리유니버스", "변덕쟁이메르시", "booo", "심해소녀", "세츠나트립", "두근어질", "하얀눈의프린세스는", "악역에게키스신을", "아이돌신예대", "모어점프모어", "colorofdrops", "천사의클로버", "사랑의마테리얼", "아이스드롭", "월드와이드원더", "메타모리본", "이프", "파라솔사이다", "dereamplace", "플로트플래너", "나는우리는", "복숭아색열쇠", "팀메이트", "허그"};
    public String[] category7 = {"열등상등", "닥터펑크비트", "요바나시디세이브", "프래질", "justbefriends", "echo", "droppopcandy", "위풍당당", "유령도쿄", "트래픽잼", "칠드런레코드", "악마가춤추는법", "얼터에고", "갈란드", "춤", "비와페트라", "palllsensation", "yy", "daybreakfrontline", "제국소녀", "펠레스", "에고이스트", "끊임없는남빛", "샨티", "뇌내혁명걸", "춘람", "readysteady", "forward", "reddogs", "시네마", "beateater", "미래", "flyer", "월광", "awakenow", "거리", "공허를부추기다", "가사화", "양이한마리", "하극상", "리얼라이즈", "cr에이zy"};
    public String[] category8 = {"브리키노댄스", "스위트매직", "모험의서가사라졌습니다", "탈법록", "댄스로봇댄스", "미라클페인트", "츄루리라츄루리라땃땃따", "포지티브댄스타임", "마음에드시는대로", "king", "텔레캐스터비보이", "픽서", "넌센스문학", "이얼팬클럽", "리모콘", "신의뜻대로", "굿바이선언", "에일리언에일리언", "춤춰라오케스트라", "에고록", "라브카", "넷게임폐인슈프레히코어", "1925", "어린이전쟁", "아아훌륭한고양이의삶", "태양계디스코", "좋아하는것만으로도괜찮아요", "제멋대로공주", "세상은아직시작조차하지않았어", "potato가되어가", "무지갯빛스토리즈", "원스어폰어드림", "톤데모원더즈", "glorysteadygo", "쇼타임룰러", "빙그레조사대의테마", "88별", "별하늘의멜로디", "어떤결말을원하니", "별하늘오케스트라", "mrshowtime", "모형정원의코랄", "키라피피키라피카", "필라멘트피버"};
    public String[] category9 = {"샤를", "하로하와유", "자상무색", "생명에게미움받고있어", "소녀해부", "혼자놀이엔비", "너무아파아프고싶어", "보카델라베리타", "밤을달리다", "비터초코데코레이션", "커틀러리", "베놈", "빌런", "시끄러워", "사랑해줘사랑해줘사랑해줘", "망상감상대상연맹", "메류", "게헨나", "포니", "진흙속에피다", "논블레스오블리주", "그것이당신의행복이라할지라도", "목숨뿐", "아이러니", "정말멋진6월이었습니다", "마음탓", "신같네", "큐트한그녀", "후회한다쓰고미래", "휴대연화", "잭팟새드걸", "끝없이잿빛으로", "아이디스마일", "카나데토모스소라", "재생", "로워", "토리콜로주", "노마드", "버그", "너의밤을줘", "I난데스", "잠자", "키티", "연극", "트와일라잇라이트"};
    
    public static void main(String[] args) { //메인
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	new ServerMain();
                } 
                catch (Exception e) {
                	System.out.println("errer: main");
                }
            }
        });
    }

    public ServerMain() { //서버 실행
                try {
                	server_socket = new ServerSocket(7272);
                	System.out.println("캐치마인드 서버 실행중"); 
                } 
                catch (IOException e) {  
                	System.out.println("errer: server on");
                }
                ServerThread server_thread = new ServerThread();
                server_thread.start();        
    }

    class ServerThread extends Thread { //서버 스레드
        public void run() {
            while (true) {
                try {
                    client_socket = server_socket.accept();
                    UserThread user_thread = new UserThread(client_socket);
                    client_list.add(user_thread);
                    user_thread.start();
                }
                catch (IOException e) {
                	System.out.println("errer: server thread");
                }
            }
        }
    }

    class UserThread extends Thread { //유저 스레드
        private DataInputStream is;
        private DataOutputStream os;

        public int user_number = 0;
        public int score;

        public UserThread(Socket client_socket) { //클라이언트 입장
            try {
            	is = new DataInputStream(client_socket.getInputStream());
                os = new DataOutputStream(client_socket.getOutputStream());
            }
            catch (Exception e) {
            	System.out.println("errer: user enter");
            }
        }
   
        public void run() { //클라이언트와 서버의 모든 통신
			try {
				String client_signal;
	        	String[] my_signal;
				while ((client_signal = is.readUTF()) != null) {
					my_signal = client_signal.split("/");
					
					loginSignal(my_signal); //닉네임 저장, 클라이언트 유저목록에 닉네임 추가
            		chatSignal(my_signal); //채팅, 게임 도중 채팅에 정답이 올라오면 처리함
            		canvasSignal(my_signal); //그림이 그려지고 있는 좌표 전송, 그리는 사람의 그림이 모두에게 공유됨
            		startSignal(my_signal);  //카테고리에 따른 시작 세팅
            		//endSignal(); //게임이 끝났으니 값을 초기화하고, 점수에 대한 결과창을 띄우도록 함
            		turnSignal(); //자신의 턴인 사람만 그림을 그림
            		colorSignal(my_signal); //입력받은 색을 적용시킴 
            		boldSignal(my_signal); //입력받은 펜 굵기를 적용시킴
				}
			} 
			catch (Exception e) {
				System.out.println("errer: signals(server)");
			}
        }
        
        public void WriteOne(String one) { //클라이언트에게 통신 보내기
            try {
                os.writeUTF(one);
            } 
            catch (IOException e) {
            	System.out.println("errer: write one");
                try {
                    os.close();
                    is.close();
                    client_socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                client_list.removeElement(this);
            }
        }
        
    	//////////////////////////////////////////////////login//////////////////////////////////////////////////
        
        private void loginSignal(String[] my_signal) { //닉네임 저장, 클라이언트 유저목록에 닉네임 추가
    		if (my_signal[0].equals("login")) {
    			nicknames.add(my_signal[1]);
    			
    			String userList = "login/";
    			for (int i = 0; i < client_list.size(); i++) {
                	UserThread user = client_list.get(i);
                	userList += nicknames.get(i) + "/";
                }
    			
    			for (int i = 0; i < client_list.size(); i++) {
                    UserThread user = client_list.get(i);
                    user.WriteOne(userList);
                }
    		}
    	}
        
    	//////////////////////////////////////////////////chat//////////////////////////////////////////////////
        
        private void chatSignal(String[] my_signal) { //채팅, 게임 도중 채팅에 정답이 올라오면 처리함
    		if (my_signal[0].equals("chat")) {
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.WriteOne("chat/" + my_signal[1]);
    			}
    			
    			String[] chatStr = my_signal[1].split(":");
    			
        		//채팅중에 정답 발견
        		if (start == 1 && chatStr[1].equals(words[answer_word])) {
        			
    				for (int i = 0; i < client_list.size(); i++) { //정답 처리
    					UserThread user = client_list.get(i);
    					user.WriteOne("chat/" + chatStr[0] + " 님이 정답을 맞추셨습니다!" + " (정답: " + words[answer_word] + ")");
    					
    					answer = 1;
    				}
    				
    				for (int i = 0; i < client_list.size(); i++) { //맞춘사람의 점수를 1 증가
    					UserThread user = client_list.get(i);
    					
    					if(chatStr[0].equals("[" + nicknames.get(i) +"]")) {
    						user.score++;
    					}
    				}
    				
    				if (answer_word < words.length) { //다음 문제로
    					answer_word++;
    				}
    				
    				if (turn < client_list.size()) { //다음 턴으로
						turn++;
					}
    				else if(turn >= client_list.size()) { //마지막 유저가 턴이었으면 1번 유저부터 다시
    					turn = 1;
    				}
    			}
    		}
    	}
        
    	//////////////////////////////////////////////////canvas//////////////////////////////////////////////////
        
        private void canvasSignal(String[] my_signal) { //그림이 그려지고 있는 좌표를 받고 클라이언트로 재전송, 그리는 사람의 그림이 모두에게 공유됨
    		if (my_signal[0].equals("canvas")) {
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.WriteOne("canvas/" + my_signal[1] + "/" + my_signal[2] + "/" + my_signal[3] + "/" + my_signal[4]);
    			}

    		}
    	}
        
    	//////////////////////////////////////////////////start//////////////////////////////////////////////////
        
        private void startSignal(String[] my_signal) { //카테고리에 따른 시작 세팅
    		if (my_signal[0].equals("start1")) {
    			//주제1에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category1.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category1[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		else if (my_signal[0].equals("start2")) {
    			//주제2에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category2.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category2[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		else if (my_signal[0].equals("start3")) {
    			//주제3에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category3.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category3[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start4")) {
    			//주제4에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category4.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category4[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start5")) {
    			//주제5에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category5.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category5[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start6")) {
    			//주제6에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category6.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category6[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start7")) {
    			//주제2에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category7.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category7[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start8")) {
    			//주제8에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category8.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category8[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    		
    		else if (my_signal[0].equals("start9")) {
    			//주제9에서 10문제 출제
    			Random random = new Random();
    			int[] selectArr = new int[20];
    			for(int i=0; i<10; i++) {
    				selectArr[i] = random.nextInt(category9.length);
    				
    				for(int j=0; j<i ; j++) { //중복 검사
    					if(selectArr[i] == selectArr[j]) {
    						i--;
    						break;
    					}
    				}
    				int select = selectArr[i];
    				words[i] = category9[select];
    			}
    			
    			//값들을 초기화
    			start = 1;
				end = 0;
				answer = 1;
				answer_word = 0;
				
    			for (int i = 0; i < client_list.size(); i++) {
    				UserThread user = client_list.get(i);
    				user.user_number = i + 1;
    				user.score = 0;
    				user.WriteOne("chat/" + "게임을 시작합니다.");
    				user.WriteOne("start/"); //게임시작 버튼 비활성화
    			}
    		}
    	}
        
    	//////////////////////////////////////////////////end//////////////////////////////////////////////////
        
        private void endSignal() { //게임이 끝났으니 값을 초기화하고, 점수에 대한 결과창을 띄우도록 함
    		if (start == 1 && end == 1) {
    			String result = "result/";
    			
				for (int i = 0; i < client_list.size(); i++) { //결과 기록
					UserThread user = client_list.get(i);
					result += (nicknames.get(i) + "/" + user.score + "/");
				}
    			
				for (int i = 0; i < client_list.size(); i++) {
					UserThread user = client_list.get(i);
					user.WriteOne("end/");
					user.WriteOne("chat/" + "게임이 끝났습니다.");
					user.WriteOne(result);//결과 보내기
				}
				turn = 1;
				answer = 0;
				answer_word = 0;
				start = 0;
				end = 0;
    		}
    	}
        
    	//////////////////////////////////////////////////turn//////////////////////////////////////////////////
        
        private void turnSignal() { //자신의 턴인 사람만 그림을 그림
			if (start == 1 && answer == 1) {
				if(answer_word >= words.length) { //문제 다 풀었으면 게임 끝
					end = 1;
					answer = 0;
					endSignal();
				}
				else {
					for (int i = 0; i < client_list.size(); i++) { //턴 바꾸기
						UserThread user = client_list.get(i);
						user.WriteOne("chat/" + "[" + nicknames.get(turn-1) + "]" + " 님이 그릴 차례 입니다."); //turn
						
						user.WriteOne("notturn/");
						if (user.user_number == turn) { //턴인 사람 찾으면 turn 덮어쓰기
							user.WriteOne("turn/" + words[answer_word]);					
						}	
						
						answer = 0;
					}
				}
			}
    	}
        
        //////////////////////////////////////////////////tool//////////////////////////////////////////////////
        
		private void colorSignal(String[] my_signal) { //입력받은 색을 적용시킴 
			if (my_signal[0].equals("color")) {
				for(int i = 0; i < client_list.size(); i++) {
					UserThread user = client_list.get(i);
					user.WriteOne("color/"+ my_signal[1]);
				}
			}
		}
		
		private void boldSignal(String[] my_signal) { //입력받은 펜 굵기를 적용시킴
			if (my_signal[0].equals("bold")) {
				for(int i = 0; i < client_list.size(); i++) {
					UserThread user = client_list.get(i);
					user.WriteOne("bold/"+ my_signal[1]);
				}
			}
		}
    }
}
