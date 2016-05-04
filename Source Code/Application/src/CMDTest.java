/**
 * @(#)CMDTest.java
 *
 *
 * @author 
 * @version 1.00 2016/3/15
 */
 
 import dict.obj.DictionaryManager;
 import java.sql.SQLException;

public class CMDTest {
        
    /**
     * Creates a new instance of <code>CMDTest</code>.
     */
    public CMDTest() {
    }
    
    /**
     * @param args the command line arguments
     */
     
     void searchWord() throws SQLException{
     	/*DictionaryManager dm = null;
     	try{
	     	dm = new DictionaryManager();
	     	dm.login();
	     	System.out.println(dm.searchWord("abandon"));
	     	System.out.println(dm.searchWord("ability"));
     	}
     	catch(Exception e){
     		e.printStackTrace();
     	}
     	finally{
     		if(dm != null)
     			dm.logout();	
     	}*/
     }
     
    public static void init(String[] args) {
        // TODO code application logic here
        CMDTest cm = new CMDTest();
        try{
        	cm.searchWord();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
}
