import java.io.*;
import java.net.*;
import java.util.*;
public class Myserver1
{
	ArrayList al=new ArrayList();ServerSocket ss;Socket s;
	public Myserver1()
	{
		try{
			ss=new ServerSocket(10);
			while(true)
			{
				s=ss.accept();//waits for the client socket
				// System.out.println(Myclient1.nameOfClient+" connected");
			
				al.add(s);
				Runnable r=new MyThread(s,al);
				Thread t=new Thread(r);
				t.start();
			}
		}catch(Exception e){}
		}
		public static void main(String ar[])
		{
			new Myserver1();
		}
}
	class MyThread  implements Runnable
	{
		Socket s;ArrayList al;
		MyThread(Socket s,ArrayList al)
		{
			this.s=s;
			this.al=al;
		}
		public void run()
		{
			String s1;int i=0;
			try{
				DataInputStream din=new DataInputStream(s.getInputStream());
				do{

					s1=din.readUTF();
					if(i==0)//displaying the name of client everytime a client gets connected
					   System.out.println(s1+"  connected");
					else
						System.out.println(s1);
					i++;
					if(!s1.equals("stop"))
					{
					 tellEveryone(s1); 
                         }

						else
						{
							DataOutputStream dout=new DataOutputStream(s.getOutputStream());
							dout.writeUTF(s1);
							dout.flush();
							al.remove(s);
						}
						}while(!s1.equals("stop"));
				}catch(Exception e){}
			}
			public void tellEveryone(String s1)
			{
				Iterator i=al.iterator();
				while(i.hasNext())
				{
					try{
						Socket sc=(Socket)i.next();
						DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
                        dout.writeUTF(s1);
						dout.flush();
						System.out.println("client");
					}catch(Exception e){}
				}
			}
		}
	