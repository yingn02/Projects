import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Calculate {
	
	public static int pocket = 0; //계산액
	public static int total = 0; //총 매출액
	public static int ordernumber = 1; //주문 번호
	
	public Calculate() { //아무것도 하지 않음
		
	}

	public Calculate(ArrayList orderList, ArrayList priceList) { //계산
		
		for(int i=0; i<priceList.size(); i++) { 
			pocket += (int) priceList.get(i);
		}
		total += pocket;
	
	}
	
	static void write(ArrayList orderList, ArrayList priceList) { //영수증 출력//////////////////
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(ordernumber + ".txt");
			ordernumber++;
			bw = new BufferedWriter(fw);
			
			for(int i=0; i<priceList.size(); i++) { 
				String str = orderList.get(i) + " -------------------- " + priceList.get(i) + " 원";
				
				bw.write(str);
				bw.newLine();
			}
			bw.newLine();
			bw.write("총 합계" + " -------------------- " + pocket + " 원");
			bw.newLine();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				if (bw != null) bw.close();
				if (fw != null) fw.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	static void read() {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader((ordernumber-1) + ".txt");
			br = new BufferedReader(fr);
			
			String msg;
			
			while((msg = br.readLine()) != null)
				System.out.println(msg);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
}
