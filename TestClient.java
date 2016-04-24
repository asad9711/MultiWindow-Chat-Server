import java.io.*;
import java.net.*;
import java.util.*;
 class TestClient
{
	Socket s;
	DataOutputStream dout;DataInputStream din;BufferedReader br;

	static String nameOfClient=null;	

	EachClient eachClient;
public TestClient()
	{
		
		try{
			
			s=new Socket("localhost",10);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
	     br=new BufferedReader(new InputStreamReader(System.in));
				System.out.println("enter ur name");

			 nameOfClient=new Scanner(System.in).nextLine();
			 dout.writeUTF(nameOfClient);//writing the name of client
   		dout.flush();
       eachClient= new EachClient(nameOfClient);//calling GUI from here
			clientchat();

		}catch(Exception e){}	
	}
	public void clientchat()throws IOException 
   {
   	My m=new My(this,din);
   	Thread t1=new Thread(m);
   	t1.start();
   	// BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   	String s1;
   	do{
   		s1=br.readLine();
   		dout.writeUTF(nameOfClient+" : " +s1);
   		dout.flush();

   	}while(!s1.equals("stop"));
   }

   public static void main(String ar[])
   {
  new TestClient();
   }
}
class My implements Runnable
{
	DataInputStream din;
	TestClient testClient;
	My(TestClient testClient, DataInputStream din)
	{
		this.din=din;
		this.testClient=testClient;

	}
	public void run()
	{
		String s2="";
		do{
			try{
				s2=din.readUTF();
				System.out.println(s2);

				// testClient.eachClient.chatArea.append(testClient.nameOfClient +" said " +s2+"\n");
				String nameArray[]=s2.split(":");
				char[] array1=nameArray[0].toCharArray();
				char[] array2=testClient.nameOfClient.toCharArray();
				int flag=0;
				for(int z=0;z<array2.length;z++)
				{
					if(array1[z]!=array2[z])
                        flag=1;
				}
				if(flag==0)
					testClient.eachClient.chatArea.append("me : "+nameArray[1]+"\n");
				else
				  testClient.eachClient.chatArea.append(s2+"\n");


			}catch(Exception e){}
		}while(!s2.equals("stop"));
	}
}
