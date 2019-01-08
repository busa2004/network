package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient2
{
	public static void main(String[] args)
	{
		String str = "";
		Socket socket = null;
		try
		{
			socket = new Socket("127.0.0.1", 8000);
			
			// 서버에게 보내기 위한 준비
			
			Scanner s = new Scanner(System.in);
			while(true) {
			BufferedWriter bufferedWriter = 
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"EUC_KR"));
			
			
			
			System.out.print(">>");
			 str = s.next();
			bufferedWriter.write(str);
			bufferedWriter.newLine(); //readLine()으로 읽기 때문에 개행 추가
			bufferedWriter.flush();
			if(str.equals("exit")) {
				return;
			}
			BufferedReader bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(),"EUC_KR"));
			
			// 서버부터 메시지 입력받음
			String clientMessage = bufferedReader.readLine();
			
			// 입력받은 내용을 서버 콘솔에 출력
			System.out.println("<<" + clientMessage);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			if(socket != null && socket.isClosed() == false) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
