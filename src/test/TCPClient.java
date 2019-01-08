package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	private static final String SERVER_IP = "218.39.221.77";
	private static final int SERVER_PORT = 5000;
	public static void main(String[] args) {
		//1. 소켓 생성
		Socket socket = new Socket();
		//2. 서버 연결
		try {
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			System.out.println("[client] connected");
			
			//3. IOStream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
		
			//4. 쓰기
			String data = "Hello World\n";
			os.write(data.getBytes("UTF-8"));
			
			//5. 읽기
			byte[] buffer = new byte[255];
			int readByteCount = is.read(buffer); //blocking
			if(readByteCount == -1) {
				System.out.println("[client] closed by server");
				return;
			}
			
			data = new String(buffer, 0 , readByteCount, "UTF-8");
			System.out.println("[client] received:" + data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
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
