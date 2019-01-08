package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2
{
	public static void main(String[] args)
	{
		
		ServerSocket serverSocket =null;
		try
		{
			 serverSocket = new ServerSocket(8000); // 서버소켓 선언

			while (true)
			{
				System.out.println("[서버] 연결 기다림");
				Socket socket = serverSocket.accept(); // 서버소켓으로부터 소켓 객체 가져오기
				
				System.out.println("[서버] 연결됨 from " + socket.getInetAddress());

				while(true) {
				// 위의 세줄을 한줄로 표현
				BufferedReader bufferedReader = 
						new BufferedReader(new InputStreamReader(socket.getInputStream(),"EUC_KR"));

				// 클라이언트로부터 메시지 입력받음
				String clientMessage = bufferedReader.readLine();

				// 입력받은 내용을 서버 콘솔에 출력
				System.out.println("[서버] 데이터 수신 : " + clientMessage);
				if(clientMessage.equals("exit")) {
					return;
				}
				// 클라이언트에게 보내기 위한 준비
				BufferedWriter bufferedWriter = 
						new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"EUC_KR"));
				
				
				bufferedWriter.write(clientMessage);
				bufferedWriter.newLine(); //readLine()으로 읽으므로 한줄끝을 알림
				bufferedWriter.flush();
				
				
				//PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				//printWriter.println("JAVA의 세계에 오신 것을 환영합니다!!");
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				System.out.println("[서버] 클라이언트로 부터 연결끊김");
				if(serverSocket != null && 
				   serverSocket.isClosed() == false)
				   serverSocket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
