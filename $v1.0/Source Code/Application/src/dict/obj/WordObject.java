/**
 *@(#)WordObject.java
 * @author Sabari 
 * @version 1.00 Feb 3, 2016
 */
package dict.obj;

/**
 * @author Sabari
 *
 */

import java.util.List;
import java.util.ArrayList;

/**WordObject - this class is used to hold the word and its dictionary components*/

public class WordObject {
	
	/**wID - unique ID for the word*/
	private int wID;
	
	/**word - the word itself*/
	private String word;
	
	/**syllable - the syllable breakdown of the word*/
	private String syllable;
	
	/**psList - list of part of speech this word can be used in along with the context of use and their meaning in that context*/
	private List<PartOfSpeech> psList;
	private int psIndex;
	
	/**idioms - List of idioms formed using this word as the base*/
	private List<Idiom> idioms;
	private int iIndex;
	
	/**phrasalVerbs - List of phrasal verbs formed usign this word as the base*/
	private List<PhrasalVerb> phrasalVerbs;
	private int pvIndex;
	
	{
		psList = new ArrayList<PartOfSpeech>();
		psIndex = 0;
		idioms= new ArrayList<Idiom>();
		iIndex = 0;
		phrasalVerbs = new ArrayList<PhrasalVerb>();
		pvIndex = 0;
	}
	
	public WordObject(){
		wID = 0;
		word = null;
		syllable = null;
	}
	
	WordObject(int wID, String word, String syllable) {
		this.wID = wID;
		this.word = word;
		this.syllable = syllable;
	}
	
	void setWID(int wID) {
		this.wID = wID;
	}
	
	public int getWID() {
		return wID;
	}
	
	void setWord(String word) throws NullPointerException {
		if(word == null)
			throw new NullPointerException("Word form cannot be null");
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	void setSyllable(String syllable) throws NullPointerException{
		if(syllable == null) {
			throw new NullPointerException("Word syllable cannot be null");
		}
		this.syllable = syllable;
	}
	
	public String getSyllable() {
		return syllable;
	}
	
	void addPartOfSpeech(PartOfSpeech ps) throws Exception{
		if(psList.add(ps) == false)
			throw new Exception("Adding part of speech block to the word failed");
	}
	
	List<PartOfSpeech> getPartOfSpeechList(){
		return psList;
	}
	
	public PartOfSpeech getNextPartOfSpeech() {
		if(psIndex < psList.size()) {
			return psList.get(psIndex++);
		}
		else
			return null;
	}
	
	void addIdiom(Idiom idiom) throws Exception{
		if(idioms.add(idiom) == false)
			throw new Exception("Adding idiom to the word failed");
	}
	
	List<Idiom> getIdioms(){
		return idioms;
	}
	
	public Idiom getNextIdiom() {
		if(iIndex<idioms.size()) {
			return idioms.get(iIndex++);
		}
		else
			return null;
	}
	
	void addPhrasalVerb(PhrasalVerb phrasalVerb) throws Exception{
		if(phrasalVerbs.add(phrasalVerb) == false) {
			throw new Exception("Adding phrasal verb to the word failed");
		}
	}
	
	List<PhrasalVerb> getPhrasalVerbs(){
		return phrasalVerbs;
	}
	
	public PhrasalVerb getNextPhrasalVerb() {
		if(pvIndex < phrasalVerbs.size()) {
			return phrasalVerbs.get(pvIndex++);
		}
		else
			return null;
	}
	
	void addMeaningToPartOfSpeech(int pIndex, int mIndex, String meaning, String example) throws Exception{
		if(pIndex >= psList.size())
			throw new IndexOutOfBoundsException("pIndex is out of range");
		(psList.get(pIndex)).addMeaning(new ContextMeaning(mIndex,meaning,example));
	}
	
	@Override
	public String toString() {
		String str = word;
		str +="\n"+syllable;
		return str;
	}
	
}
