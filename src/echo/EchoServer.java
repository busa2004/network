package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static final int PORT = 6000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			//2. Binding
			String localhostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(localhostAddress, PORT));
			log("binding " + localhostAddress + ":" + PORT);

			//3. accept
			Socket socket = serverSocket.accept();
				
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			log("connected by client[" + inetRemoteSocketAddress.getAddress().getHostAddress() + ":" + inetRemoteSocketAddress.getPort() + "]");
			
			try {
				//4. IOStream 생성(받아오기)
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				
				while(true) {
					//5. 데이터 읽기(수신)
					String data = br.readLine();
					if(data == null) {
						log("closed by client");
						break;
					}
					log("received:" + data);
					
					//6. 데이터 쓰기(전송)
					pw.println(data);
				}
			} catch(SocketException e) {
				log("abnormal closed by client");
			} catch (IOException e) {
				log("error:" + e);
			} finally {
				try {
					//7. 자원정리(소켓 닫기)
					if(socket != null && socket.isClosed() == false) {
						socket.close();
					}
				} catch(IOException e) {
					log("error:" + e);
				}
			}
			
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if( serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}
	
	private static void log(String log) {
		System.out.println("[server] " + log);
	}
}