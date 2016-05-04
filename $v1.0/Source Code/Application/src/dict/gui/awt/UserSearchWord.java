/**
 *@(#)SearchUser.java
 * @author Sabari 
 * @version 1.00 Feb 15, 2016
 */
package dict.gui.awt;

import java.awt.*;
import java.awt.event.*;
import dict.obj.*;
/**
 * @author Sabari
 *
 */
public class UserSearchWord extends Frame{
	
	DictionaryInterface di;
	String user, password;
	
	Button searchButton;
	
	TextField searchField;
	
	TextArea resultArea;
	
	String msg;
	
	public UserSearchWord(){
		di = new DictionaryInterface();
		user = "";
		password = "";
		setTitle("Dictionary");
		setSize(500,500);
		setLayout(new FlowLayout());
		setResizable(false);
		//setBackground(new Color(Color.HSBtoRGB()));
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				setVisible(false);
				dispose();
			}
		});
		
		searchField = new TextField(42);
		
		searchButton = new Button("Search");

		setVisible(true);
		
		Insets ins = getInsets();
		
		
		
		msg = "Insets.top = "+ins.left+"\n";
		msg+="Insets.left = "+ins.left+"\n";
		msg+="Insets.right = "+ins.right+"\n";
		msg+="Insets.bottom = "+ins.bottom+"\n";
		
		resultArea = new TextArea(msg,25,56);
		add(searchField);
		add(searchButton);
		add(resultArea);
		
	}
	
	public void init(){
		try{
			di.login(user, password);
			setVisible(true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g){
		
	}
	
}
