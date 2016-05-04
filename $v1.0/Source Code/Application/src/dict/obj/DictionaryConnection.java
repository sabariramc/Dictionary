/**
 * @(#)DictConnect.java
 *
 *
 * @author  Sabariram
 * @version 1.00 2016/2/2
 */
 
 /**
  * This class is  the connection point for the application to datasource
  */

package dict.obj;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import oracle.jdbc.pool.OracleDataSource;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.sql.Types;

class DictionaryConnection {

	/**dictSrc is of type DataSource - Holds the details to connect to the datasource*/
	private static DataSource dictSrc;
	
	/**conn is of type Connection - Holds the connection to the datasource*/
	private static Connection conn;
	
	/**username and password for the connection*/
	private String user,password;
	
	WordObject tempWO;
        
        private static String getWordQuery = 
                "SELECT W_ID, SYLLABLE,I_FLAG, PV_FLAG "
                + "FROM WORD_OBJECT WHERE WORD like ?";
        
        private static String getPSQuery = 
                "SELECT P_CODE, C_SEQ, CONTEXT "
                + "FROM PART_OF_SPEECH "
                + "WHERE W_ID = ? "
                + "ORDER BY C_SEQ ASC";
    	private static String getContextQuery =
    		"SELECT M_SEQ,MEANING, EXAMPLE "
                + "FROM CONTEXT_MEANING "
                + "WHERE W_ID = ? AND P_CODE = ? AND C_SEQ = ? "
                + "ORDER BY M_SEQ ASC";
    	private static String getIdiomQuery =
    		"SELECT P_CODE, I_SEQ, FORM, MEANING, EXAMPLE "
                + "FROM IDIOM WHERE W_ID = ? "
                + "ORDER BY I_SEQ ASC ";
    	private static String getPhrasalVerbQuery = 
    		"SELECT PV_SEQ,FORM, MEANING, EXAMPLE "
                + "FROM PHRASAL_VERB WHERE W_ID = ? "
                + "ORDER BY PV_SEQ ASC";
        
        private static String getPSCodesQuery =
                "SELECT P_CODE,ABBR,FULL_FORM "
                + "FROM PART_OF_SPEECH_CODE "
                + "ORDER BY P_CODE ASC";
        
        private static String getWIDQuery=
                "SELECT MAX(W_ID) FROM WORD_OBJECT";
        
	private static String insertWordQuery =
                "INSERT INTO WORD_OBJECT VALUES(?,?,?,?,?)";
        
        private static String insertPartOfSpeechQuery=
                "INSERT INTO PART_OF_SPEECH VALUES(?,?,?,?)";
        
        private static String insertContextMeaningQuery=
                "INSERT INTO CONTEXT_MEANING VALUES(?,?,?,?,?,?)";
        
        private static String insertIdiomQuery=
                "INSERT INTO IDIOM VALUES(?,?,?,?,?,?)";
        
        private static String insertPhrasalVerbQuery =
                "INSERT INTO PHRASAL_VERB VALUES(?,?,?,?,?)";
        
        
	DictionaryConnection(){
    	user = null;
    	password = null;
    	tempWO = null;
    }	
	
	/** initilizeDataSource() - Method to initalize the datasource*/
    static void initilizeDataSource(){
    	try{
	    	OracleDataSource ods = new OracleDataSource();
	    	ods.setDriverType("oci");
	    	ods.setServerName("localhost");
	    	ods.setPortNumber(1521);
	    	ods.setDatabaseName("oracle");
	    	ods.setNetworkProtocol("tcp");
	    	ods.setUser("c##dict_admin");
	    	ods.setPassword("dict1991");
	    	dictSrc = ods;
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    /**createConnection() - Method to establish connection to the datasource */
    static void createConnection(){
    	try{
    		conn = dictSrc.getConnection();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    /**releaseConnection() - Method to close the connection to the datasource*/
    static void releaseConnection(){
    	try{
    		conn.close();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    static List<PartOfSpeechCode> getPSCodes(){
    	List<PartOfSpeechCode> psCodes  = new ArrayList<PartOfSpeechCode>();
    	try {
    		PreparedStatement pstmt = 
    				conn.prepareStatement(getPSCodesQuery);
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			psCodes.add(new PartOfSpeechCode(rs.getInt(1),rs.getString(2),rs.getString(3)));
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return psCodes;
    }
      
    /** setUsername(String user)- Method to set username for the connection
     *takes one parameter of type String */
    void setUsername(String user){
    	this.user = user;
    }
    
    /**setPassword(String password) - Method to set password for the connection
     *takes one String parameter*/
    void setPassword(String password){
    	this.password = password;
    }
    
    void addWord(WordObject word) throws Exception{
        
        try{
            conn.setAutoCommit(false);
            if(word == null){
                throw new NullPointerException("Word object is null");
            }

            PreparedStatement pstmt = conn.prepareStatement(getWIDQuery);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            word.setWID(rs.getInt(1)+1);
            String i_flag;
            String pv_flag;

            List<PartOfSpeech> ps = word.getPartOfSpeechList();
            List<Idiom> idioms = word.getIdioms();
            List<PhrasalVerb> pvs = word.getPhrasalVerbs();
            
            if(ps == null){
                throw new NullPointerException("Part of Speech list is null");
            }
            
            if(idioms == null){
                throw new NullPointerException("Idioms list is null");
            }

            if(idioms.size() == 0){
                i_flag = "n";
            }
            else{
                i_flag = "y";
            }

            if(pvs == null){
                throw new NullPointerException("Phrasal verb list is null");
            }

            if(pvs.size() == 0){
                pv_flag = "n";
            }
            else{
                pv_flag = "y";
            }

            pstmt = conn.prepareStatement(insertWordQuery);
            pstmt.setInt(1, word.getWID());
            pstmt.setString(2,word.getWord());
            pstmt.setString(3, word.getSyllable());
            pstmt.setString(4, i_flag);
            pstmt.setString(5,pv_flag);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(insertPartOfSpeechQuery);
            PreparedStatement cmPstmt = conn.prepareStatement(insertContextMeaningQuery);
            pstmt.setInt(1, word.getWID());
            cmPstmt.setInt(1,word.getWID());
            
            for(PartOfSpeech tps:ps){
                if(tps.getPCode() == null){
                    throw new NullPointerException("Part of Speech code is null");
                }
                pstmt.setInt(2, tps.getPCode().getCode());
                pstmt.setInt(3, tps.getCSeq());
                if(tps.getContext() == null){
                    pstmt.setNull(4,Types.VARCHAR);
                }
                else{
                    pstmt.setString(4,tps.getContext());
                }
                pstmt.executeUpdate();
                
                List<ContextMeaning> cms = tps.getMeaningList();
                
                if(cms.size() == 0){
                    throw new NullPointerException("Meaning List is empty code is null");
                }
                cmPstmt.setInt(2,tps.getPCode().getCode());
                cmPstmt.setInt(3, tps.getCSeq());
                for(ContextMeaning cm:cms){
                    cmPstmt.setInt(4,cm.getMSeq());
                    cmPstmt.setString(5,cm.getMeaning());
                    cmPstmt.setString(6,cm.getExample());
                    cmPstmt.executeUpdate();
                }
                
            }
            
            if(i_flag.equals("y")){
                pstmt = conn.prepareStatement(insertIdiomQuery);
                pstmt.setInt(1, word.getWID());
                for(Idiom idiom : idioms){
                    pstmt.setInt(2, idiom.getPCode().getCode());
                    pstmt.setInt(3, idiom.getISeq());
                    pstmt.setString(4,idiom.getForm());
                    pstmt.setString(5,idiom.getMeaning());
                    pstmt.setString(6,idiom.getExample());
                    pstmt.executeUpdate();
                }
            }
            
            if(pv_flag.equals("y")){
                pstmt = conn.prepareStatement(insertPhrasalVerbQuery);
                pstmt.setInt(1, word.getWID());
                for(PhrasalVerb pv : pvs){
                    pstmt.setInt(2, pv.getPVSeq());
                    pstmt.setString(3,pv.getForm());
                    pstmt.setString(4,pv.getMeaning());
                    pstmt.setString(5,pv.getExample());
                    pstmt.executeUpdate();
                }
            }
                
                
            pstmt.close();
            cmPstmt.close();
            conn.commit();
            conn.setAutoCommit(true);
       }
       catch(Exception e){
           conn.rollback();
           throw e;
       }
    }
    
    void getWord(boolean iflag, boolean pvflag) throws SQLException, Exception {
    	    	
    	PreparedStatement pstmt = conn.prepareStatement(getPSQuery);
    	pstmt.setInt(1,tempWO.getWID());
    	ResultSet rs = pstmt.executeQuery();
    	while(rs.next()) {
    		PartOfSpeech ps = new PartOfSpeech(DictionaryInterface.getPartOfSpeechCode(rs.getInt(1)), rs.getInt(2), rs.getString(3));
    		PreparedStatement pstmt_1 = conn.prepareStatement(getContextQuery);
    		pstmt_1.setInt(1,tempWO.getWID());
    		pstmt_1.setInt(2,rs.getInt(1));
    		pstmt_1.setInt(3, rs.getInt(2));
    		ResultSet rs_1 =  pstmt_1.executeQuery();
    		while(rs_1.next()) {
    			ContextMeaning meaning = new ContextMeaning(rs_1.getInt(1),rs_1.getString(2),rs_1.getString(3));
    			ps.addMeaning(meaning);
    		}
    		tempWO.addPartOfSpeech(ps);
    	}
    	
    	if(iflag) {
    		pstmt = conn.prepareStatement(getIdiomQuery);
    		pstmt.setInt(1,tempWO.getWID());
    		rs = pstmt.executeQuery();
    		while(rs.next()) {
    			Idiom idiom = new Idiom(DictionaryInterface.getPartOfSpeechCode(rs.getInt(1)), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
    			tempWO.addIdiom(idiom);
    		}
    	}
    	
    	if(pvflag) {
    		pstmt = conn.prepareStatement(getPhrasalVerbQuery);
    		pstmt.setInt(1,tempWO.getWID());
    		rs = pstmt.executeQuery();
    		while(rs.next()) {
    			PhrasalVerb phrasalVerb = new PhrasalVerb(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    			tempWO.addPhrasalVerb(phrasalVerb);
    		}
    	}
    	
    }
    
    WordObject getWord(String word) throws Exception{
    	tempWO = null;
    	try {
    		boolean iflag = false, pvflag = false; 
    		PreparedStatement pstmt = conn.prepareStatement(getWordQuery);
    		pstmt.setString(1,word);
    		ResultSet rs = pstmt.executeQuery();
    		if(!rs.isBeforeFirst()) {
    			throw new Exception("Word not found");
    		}
    		rs.next();
    		tempWO  = new WordObject();
    		tempWO.setWID(rs.getInt(1));
    		tempWO.setWord(word);
    		tempWO.setSyllable(rs.getString(2));
    		String temp =rs.getString(3);
    		
    		if(temp.charAt(0) == 'y') {
    			iflag = true;
    		}
    		
    		temp = rs.getString(4);
    		
    		if(temp.charAt(0) == 'y') {
    			pvflag = true;
    		}
    		
    		getWord( iflag, pvflag);
    	}
    	catch(SQLException e) {
    		throw e;    		
    	}
    	catch(Exception e) {
    		throw e;
    	}
    	return tempWO;
    }
    
    boolean isDuplicate(String word) throws SQLException{
        try{
            boolean flag = true;
            PreparedStatement pstmt = conn.prepareStatement(getWordQuery);
            pstmt.setString(1,word);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.isBeforeFirst())
                flag = false;
            return flag;
        }
        catch(SQLException e){
            throw e;
        }
    }
    
}