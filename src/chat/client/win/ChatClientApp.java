package chat.client.win;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.ChatClientThread;

public class ChatClientApp {
	private static final String SERVER_IP = "218.39.221.77";
	private static final int SERVER_PORT = 9999;
	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
	//	scanner.close();
		
		
		

		// 3.connect to server
		try {
			
			
			// 2.create socket
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			 pw = new PrintWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8" ), true );
			 br = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF-8" ) );
			pw.println( "JOIN:" + name );
			String ack = br.readLine();
			if( "JOIN:OK".equals( ack ) ) {
				System.out.println( "입장하였습니다. 즐거운 채팅 되세요" );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// JOIN 처리
		// Response가 "JOIN:OK" 이면
		//
		
		
		ChatWindow cw = new ChatWindow(name,pw);
		
		new ChatClientThread(cw,br).start();
		cw.show();
	}

}
