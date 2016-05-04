/**
 * Source.java
 */
package dict.obj;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;
import java.util.List;
import java.util.ArrayList;

/**
 *@author Sabariram
 *@since Version 2.0
 */
/**
 * Handles the connection to the database
 */

public class Source {
	
	private static DataSource src;
	private Connection conn;
        private static final String user = "Sabari";
        private static final String password = "Sabari";
	
	/**
	 * SQL queries to interact with database
	 */
	private static final String sqlInsertWord;
	private static final String sqlGetWord;
	private static final String sqlUpdateWordPVerbFlag;
	private static final String sqlInsertExpUnt;
	private static final String sqlGetExpUnt;
	private static final String sqlInsertCtxOfUse;
	private static final String sqlGetCtxOfUse;
	private static final String sqlInsertCtxMeang;
	private static final String sqlGetCtxMeang;
	private static final String sqlInsertIdiom;
	private static final String sqlGetIdiom;
	private static final String sqlInsertIdmMeang;
	private static final String sqlGetIdmMeang;
	private static final String sqlInsertPVerb;
	private static final String sqlGetPVerb;
	private static final String sqlInsertPVMeang;
	private static final String sqlGetPVMeang;
	private static final String sqlInsertMeaning;
	private static final String sqlGetMeaning;
	private static final String sqlInsertExample;
	private static final String sqlGetExample;
	private static final String sqlGetPSCode;
	private static final String sqlCheckDuplicateIdiom;
	private static final String sqlCheckDuplicatePhrasalVerb;
	
	static{
		sqlInsertWord = "INSERT INTO WORD(WORD, SYLLABLE, PVFLAG) VALUES(?,?,?)";
		sqlGetWord = "SELECT WID, SYLLABLE, PVFLAG FROM WORD WHERE WORD LIKE ?";
		sqlUpdateWordPVerbFlag = "UPDATE WORD SET PVFLAG = 'Y' WHERE WID = ?";
		sqlInsertExpUnt = "INSERT INTO EXP_UNT VALUES(?,?,?,?)";
		sqlGetExpUnt = "SELECT EXPSEQ, PCODE, IFLAG FROM EXP_UNT WHERE WID = ? ORDER BY EXPSEQ";
		sqlInsertCtxOfUse = "INSERT INTO CTX_OF_USE VALUES(?,?,?,?)";
		sqlGetCtxOfUse = "SELECT CSEQ, CONTEXT FROM CTX_OF_USE WHERE WID = ? AND EXPSEQ = ? ORDER BY CSEQ";
		sqlInsertCtxMeang = "INSERT INTO CTX_MEANG(WID, EXPSEQ, CSEQ, MSEQ, MID) VALUES(?,?,?,?,?)";
		sqlGetCtxMeang = "SELECT MSEQ, MID FROM CTX_MEANG WHERE WID = ? AND EXPSEQ = ? AND CSEQ = ? ORDER BY MSEQ";
		sqlInsertIdiom = "INSERT INTO IDIOM VALUES(?,?,?,?)";
		sqlGetIdiom = "SELECT ISEQ, FORM FROM IDIOM WHERE WID = ? AND EXPSEQ = ? ORDER BY ISEQ";
		sqlInsertIdmMeang = "INSERT INTO IDM_MEANG(WID,EXPSEQ,ISEQ,MSEQ,MID) VALUES(?,?,?,?,?)";
		sqlGetIdmMeang = "SELECT MSEQ, MID FROM IDM_MEANG WHERE WID = ? AND EXPSEQ = ? AND ISEQ = ? ORDER BY MSEQ";
		sqlInsertPVerb = "INSERT INTO P_VERB VALUES(?,?,?)";
		sqlGetPVerb = "SELECT PVSEQ, FORM FROM P_VERB WHERE WID = ? ORDER BY PVSEQ";
		sqlInsertPVMeang = "INSERT INTO PV_MEANG(WID,PVSEQ,MSEQ, MID) VALUES (?,?,?,?)";
		sqlGetPVMeang = "SELECT MSEQ, MID FROM PV_MEANG WHERE WID = ? AND PVSEQ = ? ORDER BY MSEQ";
		sqlInsertMeaning = "INSERT INTO MEANING(MEANING) VALUES(?)";
		sqlGetMeaning = "SELECT MEANING FROM MEANING WHERE MID = ?";
		sqlInsertExample = "INSERT INTO EXAMPLE VALUES(?,?,?)";
		sqlGetExample = "SELECT ESEQ, EXAMPLE FROM EXAMPLE WHERE MID = ?  ORDER BY ESEQ";
		sqlGetPSCode = "SELECT PCODE, ABBR, NAME FROM PS_CODE";
		sqlCheckDuplicateIdiom = "SELECT * FROM IDIOM WHERE FORM LIKE ?";
		sqlCheckDuplicatePhrasalVerb = "SELECT * FROM P_VERB WHERE FORM LIKE ?";
			
		try{
			OracleDataSource osrc = new OracleDataSource();
			osrc.setDriverType("thin");
			osrc.setServerName("localhost");
			osrc.setPortNumber(1521);
			osrc.setDatabaseName("oracle");
			osrc.setNetworkProtocol("tcp");
			osrc.setUser("c##dict_admin");
			osrc.setPassword("dict1991");
			src = osrc;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	Source(){
		
	}
	
        void userVerification(String user, String password) throws Exception{
            if(user.equals(this.user) == false || password.equals(this.password) == false){
                throw new Exception("Invalid user and/or password combination");
            }
        }
        
	void getConnectionToSource() throws SQLException{
		try{
			conn = src.getConnection();
		}
		catch(SQLException e){
			throw e;
		}
			
	}
	
	void closeConnection() throws SQLException{
		try{
			conn.close();
		}
		catch(SQLException e){
			throw e;
		}
		
	}
	
	void addWordToDictionary(Word word) throws SQLException, Exception{
		try{
			conn.setAutoCommit(false);
			int[] colIndex = new int[1];
			colIndex[0] = 1;
			PreparedStatement pstmt = conn.prepareStatement(sqlInsertWord,colIndex);
			pstmt.setString(1, word.getWord());
			pstmt.setString(2, word.getSyllable());
			pstmt.setString(3, "N");
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int wid;
			if(rs.next()){
				wid = rs.getInt(1);
			}
			else{
				throw new Exception("Error occured when adding word");
			}
			
			pstmt = conn.prepareStatement(sqlInsertExpUnt);
			pstmt.setInt(1, wid);
			
			for(ExplanationUnit eu : word.getExplanationUnitList()){
				pstmt.setInt(2, eu.getSeq());
				pstmt.setInt(3, eu.getPartOfSpeech().getPSCode());
				String iflag = "Y";
				if(eu.getIdiomList() == null)
					iflag = "N";
				pstmt.setString(4, iflag);
				pstmt.executeUpdate();
				insertContextOfUse(wid, eu);
				insertIdiom(wid,eu);
				insertPhrasalVerb(wid,eu);
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	private void insertContextOfUse(int wid, ExplanationUnit eu) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlInsertCtxOfUse);
		pstmt.setInt(1,wid);
		pstmt.setInt(2, eu.getSeq());
		for(CtxOfUse cu : (List<CtxOfUse>)DictionaryManager.isListSafe(eu.getContextList())){
			pstmt.setInt(3, cu.getSeq());
			pstmt.setString(4,cu.getContext());
			pstmt.executeUpdate();
			PreparedStatement tpstmt = conn.prepareStatement(sqlInsertCtxMeang);
			tpstmt.setInt(1, wid);
			tpstmt.setInt(2, eu.getSeq());
			tpstmt.setInt(3, cu.getSeq());
			for(Meaning mg : (List<Meaning>)DictionaryManager.isListSafe(cu.getMeaningList())){
				tpstmt.setInt(4, mg.getSeq());
				tpstmt.setInt(5, insertMeaning(mg));
				tpstmt.executeUpdate();
			}
		}
	}
	
	private void insertIdiom(int wid, ExplanationUnit eu) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlInsertIdiom);
		pstmt.setInt(1,wid);
		pstmt.setInt(2, eu.getSeq());
		for(Phrase ph: (List<Phrase>)DictionaryManager.isListSafe(eu.getIdiomList())){
			pstmt.setInt(3, ph.getSeq());
			pstmt.setString(4,ph.getForm());
			pstmt.executeUpdate();
			PreparedStatement tpstmt = conn.prepareStatement(sqlInsertIdmMeang);
			tpstmt.setInt(1, wid);
			tpstmt.setInt(2, eu.getSeq());
			tpstmt.setInt(3, ph.getSeq());
			for(Meaning mg : (List<Meaning>)DictionaryManager.isListSafe(ph.getMeaningList())){
				tpstmt.setInt(4, mg.getSeq());
				tpstmt.setInt(5, insertMeaning(mg));
				tpstmt.executeUpdate();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void insertPhrasalVerb(int wid, ExplanationUnit eu) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlInsertPVerb);
		pstmt.setInt(1,wid);
		boolean flag = true;
		for(Phrase ph: (List<Phrase>)DictionaryManager.isListSafe(eu.getPhrasalVerbList())){
			if(flag){
				updatePVFlag(wid);
				flag = false;
			}
			pstmt.setInt(2, ph.getSeq());
			pstmt.setString(3,ph.getForm());
			pstmt.executeUpdate();
			PreparedStatement tpstmt = conn.prepareStatement(sqlInsertPVMeang);
			tpstmt.setInt(1, wid);
			tpstmt.setInt(2, ph.getSeq());
			for(Meaning mg : (List<Meaning>)DictionaryManager.isListSafe(ph.getMeaningList())){
				tpstmt.setInt(3, mg.getSeq());
				tpstmt.setInt(4, insertMeaning(mg));
				tpstmt.executeUpdate();
			}
		}
	}
	
	private void updatePVFlag(int wid) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sqlUpdateWordPVerbFlag);
		pstmt.setInt(1, wid);
		pstmt.executeUpdate();
	}
	
	private int insertMeaning(Meaning me) throws SQLException, Exception{
		int[] clmind = new int[1];
		clmind[0] = 1;
		int mid = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlInsertMeaning,clmind);
		pstmt.setString(1, me.getMeaning());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()){
			mid = rs.getInt(1);
		}
		else{
			throw new Exception("Error occured while adding meaning");
		}
		int seq = 1;
		for(String example : (List<String>) DictionaryManager.isListSafe(me.getExampleList())){
			PreparedStatement tpstmt = conn.prepareStatement(sqlInsertExample);
			tpstmt.setInt(1, mid);
			tpstmt.setInt(2, seq);
			seq++;
			tpstmt.setString(3,example);
			tpstmt.executeUpdate();
		}
		
		return mid;
	}
	
	Word getWord(String word)throws SQLException, Exception{
		
		PreparedStatement pstmt = conn.prepareStatement(sqlGetWord);
		pstmt.setString(1, word);
		ResultSet rs = pstmt.executeQuery();
		if(rs.isBeforeFirst() == false){
			throw new Exception("Word not found");
		}
		if(rs.next()){
			/*String tsyllable = rs.getString("SYLLABLE");
			List<ExplanationUnit> eu = getExplanationUnitList(rs.getInt("WID"), rs.getString("PVFLAG"));
			return new Word(word,tsyllable,eu);*/
			return new Word(word, rs.getString("SYLLABLE"),getExplanationUnitList(rs.getInt("WID"), rs.getString("PVFLAG")));
		}
		return null;
	}
	
	private List<ExplanationUnit> getExplanationUnitList(int wid, String pvflag) throws SQLException, Exception{
		List<ExplanationUnit>eul = new ArrayList<ExplanationUnit>();
		PreparedStatement pstmt = conn.prepareStatement(sqlGetExpUnt);
		pstmt.setInt(1,wid);
		ResultSet rs = pstmt.executeQuery();
		final int verbCode = 4;
		while(rs.next()){
			List<CtxOfUse> ctxList = getContextList(wid, rs.getInt("EXPSEQ"));
			List<Phrase> idiomList = null;
			if(rs.getString("IFLAG").charAt(0)=='Y'){
				idiomList = getIdiomList(wid, rs.getInt("EXPSEQ"));
			}
			List<Phrase> phrasalVerbList = null;
			if(pvflag.charAt(0) == 'Y' && rs.getInt("PCODE") == verbCode){
				phrasalVerbList = getPhrasalVerbList(wid); 
			}
			eul.add( new ExplanationUnit(
							rs.getInt("EXPSEQ"), 
							DictionaryManager.getPartOfSpeech(rs.getInt("PCODE")),
							ctxList ,idiomList, phrasalVerbList 
							)
					);
		}
		return eul;
	}
	
	private List<CtxOfUse> getContextList(int wid, int expseq) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlGetCtxOfUse);
		pstmt.setInt(1, wid);
		pstmt.setInt(2, expseq);
		ResultSet rs = pstmt.executeQuery();
		List<CtxOfUse> ctl = null;
		if(rs.isBeforeFirst()){
			ctl = new ArrayList<CtxOfUse>();
		}
		while(rs.next()){
			pstmt = conn.prepareStatement(sqlGetCtxMeang);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, expseq);
			pstmt.setInt(3, rs.getInt("CSEQ"));
			ResultSet trs = pstmt.executeQuery();
			List<Meaning> ml = new ArrayList<Meaning>();
			while(trs.next()){
				ml.add(getMeaning(trs.getInt("MID"), trs.getInt("MSEQ")));
			}
			ctl.add(new CtxOfUse(rs.getInt("CSEQ"),rs.getString("CONTEXT"),ml));
		}
		return ctl;
	}
	
	private List<Phrase> getIdiomList(int wid, int exseq) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlGetIdiom);
		pstmt.setInt(1,wid);
		pstmt.setInt(2, exseq);
		ResultSet rs = pstmt.executeQuery();
		List<Phrase> idiom = new ArrayList<Phrase>();
		while(rs.next()){
			pstmt = conn.prepareStatement(sqlGetIdmMeang);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, exseq);
			pstmt.setInt(3, rs.getInt("ISEQ"));
			ResultSet trs = pstmt.executeQuery();
			List<Meaning> mlist = new ArrayList<Meaning>();
			while(trs.next()){
				mlist.add(getMeaning(trs.getInt("MID"), trs.getInt("MSEQ")));
			}
			idiom.add(new Phrase(rs.getInt("ISEQ"), rs.getString("FORM"),mlist));
		}
		return idiom;
	}
	
	private List<Phrase> getPhrasalVerbList(int wid) throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlGetPVerb);
		pstmt.setInt(1,wid);
		ResultSet rs = pstmt.executeQuery();
		List<Phrase> phrasalVerb = new ArrayList<Phrase>();
		while(rs.next()){
			pstmt = conn.prepareStatement(sqlGetPVMeang);
			pstmt.setInt(1, wid);
			pstmt.setInt(2, rs.getInt("PVSEQ"));
			ResultSet trs = pstmt.executeQuery();
			List<Meaning> mlist = new ArrayList<Meaning>();
			while(trs.next()){
				mlist.add(getMeaning(trs.getInt("MID"), trs.getInt("MSEQ")));
			}
			phrasalVerb.add(new Phrase(rs.getInt("PVSEQ"), rs.getString("FORM"),mlist));
		}
		return phrasalVerb;
	}
	private Meaning getMeaning(int mid, int mseq)throws SQLException, Exception{
		PreparedStatement pstmt = conn.prepareStatement(sqlGetMeaning);
		pstmt.setInt(1, mid);
		ResultSet rs = pstmt.executeQuery();
		String meaning=null;
		while(rs.next()){
			meaning = rs.getString("MEANING");
		}
		pstmt = conn.prepareStatement(sqlGetExample);
		pstmt.setInt(1, mid);
		rs = pstmt.executeQuery();
		List<String> exampleList = null;
		if(rs.isBeforeFirst())
			exampleList = new ArrayList<String>();
		while(rs.next()){
			exampleList.add(rs.getString("EXAMPLE"));
		}
		return new Meaning(mseq,meaning,exampleList);
	}
	
	List<PartOfSpeech> getPSCodeList() throws SQLException{
		List<PartOfSpeech> psList = new ArrayList<PartOfSpeech>();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sqlGetPSCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				psList.add(new PartOfSpeech(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		}
		catch(SQLException e){
			throw e;
		}
		return psList;
	}
	
	void checkForDuplicateWord(String word) throws SQLException, Exception{
		try{
			PreparedStatement pstmt = conn.prepareStatement(sqlGetWord);
			pstmt.setString(1,word);
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst())
				throw new Exception("Word ("+word+") already exist");
		}
		
		catch(SQLException e){
			throw e;
		}
		
	}
	
	void checkForDuplicateIdiom(String idiom) throws SQLException, Exception{
		try{
			PreparedStatement pstmt = conn.prepareStatement(sqlCheckDuplicateIdiom);
			pstmt.setString(1,idiom);
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst())
				throw new Exception("Idiom("+idiom+") already exist");
		}
		catch(SQLException e){
			throw e;
		}
	}
	
	void checkForDuplicatePhrasalVerb(String pverb) throws SQLException, Exception{
		try{
			PreparedStatement pstmt = conn.prepareStatement(sqlCheckDuplicatePhrasalVerb);
			pstmt.setString(1,pverb);
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst())
				throw new Exception("Phrasal verb ("+pverb+") already exist");
		}
		catch(SQLException e){
			throw e;
		}
	}
}
