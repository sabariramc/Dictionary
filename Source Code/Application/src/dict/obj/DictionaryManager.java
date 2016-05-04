/**
 * Dictionary 2.0
 * dict.obj
 * DictionaryManager.java
 * 09-Mar-2016
 */
package dict.obj;

import java.util.Collections;

/**
 * @author Sabari
 * @since Version 2.0
 */

import java.util.List;
import java.sql.SQLException;

public class DictionaryManager {
	
	private String user;
	
	private String password;
	
	private Source src;
		
	static List<PartOfSpeech> psList = null;
        
        private Word word;
	
	public DictionaryManager(){
		user = null;
		password = null;
		src = new Source();
	}
	
	public void setUser(String user) throws NullPointerException{
		if(user == null){
			throw new NullPointerException("User cannot be null");
		}
		this.user = user;
	}
	
	public void setPassword(String password) throws NullPointerException{
		if(password == null){
			throw new NullPointerException("Password cannot be null");
		}
		this.password = password;
	}
	
	public void start() throws SQLException{
		src.getConnectionToSource();
		if(psList == null){
			psList = src.getPSCodeList();
		}
	}
        
	public void stop() throws SQLException{
		src.closeConnection();
	}
	
        public void login() throws Exception{
                src.userVerification(user, password);
        }

        public void logout() throws Exception{
                user = null;
                password = null;
        }
        
	public Word searchWord(String word) throws SQLException, Exception{
		return src.getWord(word);
	}
	
        public void validateWord(Word word) throws Exception{
                src.checkForDuplicateWord(word.getWord());
                word.validate();
                this.word = word;
        }
        
        public void submitWordToDictionary() throws SQLException, NullPointerException, Exception{
		
		for(ExplanationUnit eu : word.getExplanationUnitList()){
			try{
				for(Phrase idiom : (List<Phrase>)isListSafe(eu.getIdiomList())){
					src.checkForDuplicateIdiom(idiom.getForm());
				}
				for(Phrase pverb : (List<Phrase>)isListSafe(eu.getIdiomList())){
					src.checkForDuplicatePhrasalVerb(pverb.getForm());
				}
			}
			catch(SQLException e){
				throw e;
			}
			catch(Exception e){
				String msg = eu.getPartOfSpeech().getName()+e.getMessage();
				throw new Exception(msg);
			}
		}
		src.addWordToDictionary(word);
	}
        
	private void submitWordToDictionary(Word word) throws SQLException, NullPointerException, Exception{
		
		for(ExplanationUnit eu : word.getExplanationUnitList()){
			try{
				for(Phrase idiom : (List<Phrase>)isListSafe(eu.getIdiomList())){
					src.checkForDuplicateIdiom(idiom.getForm());
				}
				for(Phrase pverb : (List<Phrase>)isListSafe(eu.getIdiomList())){
					src.checkForDuplicatePhrasalVerb(pverb.getForm());
				}
			}
			catch(SQLException e){
				throw e;
			}
			catch(Exception e){
				String msg = eu.getPartOfSpeech().getName()+e.getMessage();
				throw new Exception(msg);
			}
		}
		src.addWordToDictionary(word);
	}
		
	public static PartOfSpeech getPartOfSpeech(int pcode){
		return psList.get(pcode);
	}
	
        public static PartOfSpeech getPartOfSpeech(String name){
            for(PartOfSpeech ps : psList){
                if(name.equals(ps.getName())){
                    return ps;
                }
            }
            return null;
        }
        
        public static List<PartOfSpeech> getPartOfSpeechList(){
            return psList;
        }
        
	static List isListSafe (List ls){
		return ls == null ? Collections.emptyList() : ls;
	}
}
