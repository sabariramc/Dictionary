/**
 * @(#)CMDTest.java
 *
 *
 * @author 
 * @version 1.00 2016/2/5
 */

import dict.obj.*;
import java.util.List;
import java.util.ArrayList;

public class CMDTest {

    public CMDTest() {
    }
    
    public static void main(String args[]){
    	DictionaryInterface df = null;
    	try{
    		df = new DictionaryInterface();
	    	df.login("xyz","xyz");
	    	if(args.length == 0)
	    		throw new Exception("command line argument cannot be null");
	    	WordObject wo = df.searchWord(args[0]);
	    	//WordObject wo = df.searchWord("act");
	    	System.out.println(wo);
	    	PartOfSpeech ps = wo.getNextPartOfSpeech();
	    	while(ps != null) {
	    		System.out.println(ps);
	    		ContextMeaning cm = ps.getNextMeaning();
	    		while(cm != null) {
	    			System.out.println(cm);
	    			cm = ps.getNextMeaning();
	    		}
	    		ps = wo.getNextPartOfSpeech();
	    	}
	    	Idiom idiom = wo.getNextIdiom();
	    	if(idiom != null)
	    		System.out.println("\n\nIDIOMS:");
	    	while(idiom != null) {
	    		System.out.println(idiom);
	    		idiom = wo.getNextIdiom();
	    	}

	    	PhrasalVerb pv = wo.getNextPhrasalVerb();
	    	if(pv != null)
	    		System.out.println("\n\nPhrasal Verbs:");
	    	while(pv != null) {
	    		System.out.println(pv);
	    		pv = wo.getNextPhrasalVerb();
	    	}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		DictionaryInterface.exitApplication();
    
    	}
    }
    
}