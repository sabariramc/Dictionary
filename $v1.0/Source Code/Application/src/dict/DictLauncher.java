package dict;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sabari
 */

import dict.gui.swing.LoginPreface;
import javax.swing.*;


public class DictLauncher {
    void start(){
        try{
            SwingUtilities.invokeLater(
        		new Runnable(){
		        	public void run(){
		        		new LoginPreface();
		        	}
	        	}
            );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        DictLauncher dl = new DictLauncher();
        dl.start();
    }
}
