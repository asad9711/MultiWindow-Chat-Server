import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

//import com.controller.Controller;
public class EachClient {
    String clientName;
    // private static final long serialVersionUID = 1L;
    public JFrame frame;
    // private JPanel contentPane;
    public JPanel panel;
    public static JTextArea chatArea;
    public JTextArea clientsListArea;
    private JButton inputBtn;
    private JButton outputBtn;
    private JButton sortBtn;
    public JRadioButton firstButton;
    public JRadioButton secondButton;
    public JRadioButton thirdButton;
    public JSplitPane splitPane;
    //Controller controller;
    /**
     * Launch the application.
     */
    public EachClient(String name)
    {
        this.clientName=name;
        frame=new JFrame(name);
       
        panel=new JPanel();
        // panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setText("");
        // inputTextArea.setText("asdafd");
        clientsListArea = new JTextArea();
        clientsListArea.setEditable(false);
        clientsListArea.setText("");
        // put two TextArea to JScrollPane so text can be scrolled when too long
        JScrollPane scrollPanelLeft = new JScrollPane(chatArea);
        JScrollPane scrollPanelRight = new JScrollPane(clientsListArea);
        // put two JScrollPane into SplitPane 
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollPanelLeft, scrollPanelRight);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);

        panel.add(splitPane, BorderLayout.CENTER);
         frame.getContentPane().add(panel);
        // frame.add(panel);
        frame.setContentPane(panel);
         frame.setSize(500,500); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        chatArea.append(clientName+"  logged in\n");
        clientsListArea.setText("right");
    }
    // public static void main(String[] args) {
    //     new EachClient();
    // }
}