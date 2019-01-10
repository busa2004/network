package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import chat.client.win.ChatWindow;

public class ChatClientThread extends Thread {
	private Socket socket;
	private BufferedReader br;
	private ChatWindow cw;
	public ChatClientThread( Socket socket ) {
		this.socket = socket;
	}
	
	public ChatClientThread(ChatWindow cw, BufferedReader br) {
		this.cw = cw;
		this.br = br;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try{

			while( true ) {
				String message = br.readLine();
				System.out.println(message);
				if( message == null ) {
					break;
				}
				//System.out.println( message );
				cw.setTextArea(message);
			}
		} catch( SocketException ex ){
			//ChatClient.consoleLog( "다음 이유로 프로그램을 종료 합니다 :" + ex );	
			System.exit( 0 );
		} catch( IOException ex ){
			ChatClient.consoleLog( "다음 이유로 프로그램을 종료 합니다 :" + ex );	
			System.exit( 0 );
		}
	}
}