/**
 *@(#)DictInterface.java
 * @author Sabari 
 * @version 1.00 Feb 4, 2016
 */

package dict.obj;
import java.util.List;




/**
 * @author Sabari
 *
 */



/**DictInterface - This class is the controller and interface for the UI to the backend */

public class DictionaryInterface {
	
	/**word - refers the current word in focus*/
	WordObject word;
	
	/**psCodes - refers to all the available Part of Speech codes*/
	static List<PartOfSpeechCode> psCodes;
	
	/**conn - connection to the dictionary database*/
	
	private DictionaryConnection	dConn;
	
	private int pIndex, cIndex, iIndex, pvIndex, mIndex;
	
	static {
		DictionaryConnection.initilizeDataSource();
		DictionaryConnection.createConnection();
		psCodes = DictionaryConnection.getPSCodes();
	}
	
	public DictionaryInterface(){
		word = null;
		dConn = null;
	}
	
	public void login(String user, String password) throws Exception {
		dConn = new DictionaryConnection();
		dConn.setUsername(user);
		dConn.setPassword(password);
	}
	
	public void logout() {
		
	}
	
	public static void exitApplication() {
		DictionaryConnection.releaseConnection();
	}
	
	public WordObject searchWord(String word) throws Exception{
		return (dConn.getWord(word));
	}
	
        public boolean isDuplicate(String word) throws Exception{
            return dConn.isDuplicate(word);
        }
        
	public void createWord(String word, String syllable ) {
		this.word = new WordObject();
		this.word.setWord(word);
		this.word.setSyllable(syllable);
		cIndex =1;
		iIndex = 1;
		pvIndex = 1;
		pIndex = -1;
	}
	
	public static PartOfSpeechCode getPartOfSpeechCode(int index) {
		return psCodes.get(index);
	}
	
        public static String[] getPartOfSpeechCode(){
                String[] codes = new String[psCodes.size()];
                for(PartOfSpeechCode pc : psCodes){
                    codes[pc.getCode()] = pc.getAbbr();
                }
                return codes;
        }
        
	public void addPartOfSpeech(PartOfSpeechCode pCode, String context) throws Exception {
		PartOfSpeech ps = new PartOfSpeech();
		pIndex++;
		ps.setPCodeRef(pCode);
		ps.setCSeq(cIndex);
		ps.setContext(context);
		cIndex++;
		mIndex = 1;
		word.addPartOfSpeech(ps);
	}
	
        public void addPartOfSpeech(PartOfSpeech ps)throws Exception{
                word.addPartOfSpeech(ps);
        }
        
	public void addContextMeaning(String meaning, String example) throws Exception{
		word.addMeaningToPartOfSpeech(pIndex, mIndex, meaning, example);
		mIndex++;
	}
        
        
	public void addIdiom(PartOfSpeechCode pCode, String form, String meaning, String example) throws Exception{
		Idiom idiom = new Idiom();
		idiom.setPCode(pCode);
		idiom.setISeq(iIndex);
		idiom.setForm(form);
		idiom.setMeaning(meaning);
		idiom.setExample(example);
		iIndex++;
		word.addIdiom(idiom);
	}
	
	public void addPhrasalVerb(String form, String meaning, String example) throws Exception{
		PhrasalVerb phrasalVerb = new PhrasalVerb();
		phrasalVerb.setPVSeq(pvIndex);
		phrasalVerb.setForm(form);
		phrasalVerb.setMeaning(meaning);
		phrasalVerb.setExample(example);
		pvIndex++;
		word.addPhrasalVerb(phrasalVerb);
	}
	
	public void submitWordToDictionary() throws Exception{
		dConn.addWord(word);
	}
		
}
