/**
 * Word.java
 */

package dict.obj;



/**
 *@author Sabariram
 *@since Version 2.0
 */

import java.util.List;
import java.util.ArrayList;

public class Word {
	
	private String word;
	
	private String syllable;
	
	private List<ExplanationUnit> explanationUnitList;
	
	public Word(){
		word = null;
		syllable = null;
		explanationUnitList = new ArrayList<ExplanationUnit>();
	}
	
	public Word(Word wo){
		word = wo.getWord();
		syllable = wo.getSyllable();
		explanationUnitList = new ArrayList<ExplanationUnit>();
		List<ExplanationUnit> temp = wo.getExplanationUnitList();
		for(ExplanationUnit eu : temp){
			explanationUnitList.add(new ExplanationUnit(eu));
		}
	}
	
	Word(String word, String syllable, List<ExplanationUnit> explanationUnitList){
		this.word = word;
		this.syllable = syllable;
		this.explanationUnitList = explanationUnitList;
	}
	
		
	public void setWord(String word) throws NullPointerException{
		if(word == null){
			throw new NullPointerException("Word cannot be null");
		}
		this.word = word;
	}
	
	public String getWord(){
		return word;
	}
	
	public void setSyllable(String syllable) throws NullPointerException{
		if(syllable == null){
			throw new NullPointerException("Syllable cannot be null");
		}
		this.syllable = syllable;
	}
	
	public String getSyllable(){
		return syllable;
	}
	
	public void addExplanationUnit(ExplanationUnit eu) throws NullPointerException, Exception{
		if(eu == null){
			throw new NullPointerException("Explanation unit cannot be null");
		}
		if(explanationUnitList.add(eu) == false){
			throw new Exception("Error occured while adding Explanation unit");
		}
	}
	
	public void addExplanationUnit(int index, ExplanationUnit eu) throws NullPointerException, Exception{
		if(eu == null){
			throw new NullPointerException("Explanation unit cannot be null");
		}
		explanationUnitList.add(index, eu);
	}
	
	
	public List<ExplanationUnit> getExplanationUnitList(){
		return explanationUnitList;
	}
	
	void validate() throws NullPointerException, Exception{
		if(word == null){
			throw new NullPointerException("Word is null");
		}
		if(syllable == null ){
			throw new NullPointerException("Syllable cannot be null");
		}
		if(explanationUnitList == null || explanationUnitList.size() == 0){
			throw new NullPointerException("Explanation unit cannot be null");
		}
		int seq = 1;
		boolean psflag[] = new boolean[DictionaryManager.psList.size()];
		for(int i =0 ; i<psflag.length;i++){
			psflag[i] = false;
		}
		for(ExplanationUnit eu : explanationUnitList){
			if(psflag[eu.getPartOfSpeech().getPSCode()]){
				throw new Exception("There should be only one explanation unit per part of speech");
			}
			psflag[eu.getPartOfSpeech().getPSCode()] = true;
			eu.setSeq(seq);
			seq++;
			eu.validate();
			
		}
	}
	
	@Override
	public String toString(){
		String str = word +" "+syllable+"\n";
		for(ExplanationUnit eu : explanationUnitList){
			str += eu;
		}
		return str;
	}
}
