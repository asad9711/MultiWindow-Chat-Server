import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 class TestClient8 implements ActionListener
{
	Socket s;
	DataOutputStream dout;DataInputStream din;BufferedReader br;

	static String nameOfClient=null;
	static String message=null;
	JFrame frame;
	JTextArea textArea;
	JTextField textField;
	JButton button;
public TestClient8 ()  
	{         //just at the time of login
		System.out.println("enter ur name");

			 nameOfClient=new Scanner(System.in).nextLine();
frame=new JFrame(nameOfClient);
    textArea=new JTextArea();
    textArea.setBounds(0,0,500,400);
    textArea.setEditable(false);
    textField=new JTextField();
    textField.setBounds(0,405,350,30);

    // textField.setText("test");
    
    button=new JButton("send");
    button.setBounds(355,405,100,30);
    // button.addActionListener(this);
    button.addActionListener(this);

    frame.add(textField);
    frame.add(textArea);
    frame.add(button);
    frame.setSize(500,500);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			
			s=new Socket("localhost",10);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());

	     br=new BufferedReader(new InputStreamReader(System.in));//to put the name of client in the server socket
				

			 // dout.writeUTF(nameOfClient);//writing the name of client
   		     // dout.flush();


       // eachClient= new EachClient(nameOfClient);//calling GUI from here
   		// clientLayout=new ClientLayout(nameOfClient);


			// clientchat();

		}catch(Exception e){}	
	}

	public void actionPerformed(ActionEvent ae)
	{
		message=textField.getText();
		System.out.println("message is "+message);
		// textArea.append(nameOfClient+": "+message+"\n");
		textField.setText("");
		try{
		clientchat();
	}catch(Exception e){}
	}
	public void clientchat() throws IOException
   {                           // //receiving thread to receive message from server
   	System.out.println("after start "+Thread.currentThread().getName());   //Main thread

   	// BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   	String s1=null;          
   			s1=message;
   			System.out.println("s1 is "+s1);

   		// y++;
   		dout.writeUTF(nameOfClient+" : " +s1);  
        // }

     
       // y++;

   		message=null;
   		// dout.writeUTF("testing");      //writing to the server DOS
   		dout.flush();
   	
   		My m=new My(this,din);//obtaining the thread to listen from a server thread.
    
   	Thread t1=new Thread(m);
   	t1.start();
   	
   }

   public static void main(String ar[])
   {
     new TestClient8();
   }
}

class My implements Runnable
{
	DataInputStream din;
	TestClient8 testClient8;
	My(TestClient8 testClient8, DataInputStream din)
	{
		this.din=din;
		this.testClient8=testClient8;

	}
	public void run()
	{
   	System.out.println("inside run "+Thread.currentThread().getName());	
		String s2="";
		// do{
			// if(TestClient5.param==1){
			try{
				s2=din.readUTF();//reading from server
				System.out.println("message sent from server is "+ s2);
               testClient8.textArea.append(s2+"\n");
               // testClient8.textArea.append("just appending\n");	
               System.out.println("inside while of thread t");
				// testClient.eachClient.chatArea.append(testClient.nameOfClient +" said " +s2+"\n");
				// String nameArray[]=s2.split(":");
				// char[] array1=nameArray[0].toCharArray();
				// char[] array2=testClient2.nameOfClient.toCharArray();
				// int flag=0;
				// for(int z=0;z<array2.length;z++)
				// {
				// 	if(array1[z]!=array2[z])
    //                     flag=1;
				// }
				// if(flag==0)
				// 	testClient2.layoutTest.chatArea.append("me : "+nameArray[1]+"\n");
				// else
				//   testClient2.layoutTest.chatArea.append(s2+"\n");

   }catch(Exception e){}

			// }

		// }while(!s2.equals("stop"));
	}
}