package assignment_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {
	
	private Socket client;
    private DataInputStream in=null;
    private DataOutputStream out=null;
    public static String path;
    String writeFile="";
    static int x=0;
	public MyClient() throws IOException {
		
		client= new Socket("localhost",6000);
	}
	
	public void sendMessage(String s) throws IOException {
		out=new DataOutputStream(client.getOutputStream());
		out.writeUTF(s);
		out.flush();
	}
	
	public String receiveMessage() throws IOException {
		in=new DataInputStream(client.getInputStream());
		return in.readUTF();
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}
	
	public void sendFile(File s) throws Exception
	{
		String x="File,";
		String temp;
		DataOutputStream out=new DataOutputStream(client.getOutputStream());
		FileReader fr=new FileReader(s.getAbsolutePath());
		BufferedReader br=new BufferedReader(fr);
		if((temp=br.readLine())!=null)
		{
			x=x+temp+"\n";
			while((temp=br.readLine())!=null)
			{
				x=x+temp+"\n";
			}
			br.close();
			out.writeUTF(x);
		}else
		{
			out.writeUTF("File,   " );
		}
		
	}
	
	public void recieveFile() throws Exception
	{
		DataInputStream in=new DataInputStream(client.getInputStream());
		File abc=new File("C:\\Users\\The Beast\\Desktop\\Recieve Files\\"+x+".txt");
		FileWriter fw=new FileWriter(abc.getAbsolutePath());
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(in.readUTF());
		bw.close();
	}
	
}
