package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("> ");
			String  line = scanner.nextLine();
			
			if(line.equals("exit")) {
				return;
			}
			
		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName(line);
			for(InetAddress s : inetAddresses) {
				System.out.print(line + " : ");
				System.out.println(s.getHostAddress());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
}
