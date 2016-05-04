/**
 * Dictionary 2.0
 * dict.obj
 * Phrase.java
 * 08-Mar-2016
 */
package dict.obj;

/**
 * @author Sabari
 * @since Version 2.0
 */

import java.util.List;
import java.util.ArrayList;

public class Phrase {
	
	private int seq;
	
	private String form;
	
	private List<Meaning> meaningList;
	
	public Phrase(){
		seq = 0;
		form = null;
		meaningList = new ArrayList<Meaning>();
	}
	
	public Phrase(Phrase ph){
		seq = ph.getSeq();
		form = ph.getForm();
		meaningList = new ArrayList<Meaning>();
		List<Meaning> temp = ph.getMeaningList();
		for(Meaning me : temp){
			meaningList.add(new Meaning(me));
		}
	}
	
	Phrase(int seq, String form, List<Meaning> meaningList){
		this.seq = seq;
		this.form = form;
		this.meaningList = meaningList;
	}
	
	public void setSeq(int seq) throws Exception{
		if(seq<=0){
			throw new Exception("Phrase sequence invalid");
		}
		this.seq = seq;
	}
	
	public int getSeq(){
		return seq;
	}
	
	public void setForm(String form) throws NullPointerException{
		if(form == null){
			throw new NullPointerException("Phrase form cannot be null");
		}
		
		this.form = form;
		
	}
	
	public String getForm(){
		return form;
	}
	
	public void addMeaning(Meaning meaning) throws NullPointerException, Exception{
		if(meaning == null){
			throw new NullPointerException("Phrase - Meaning cannot be null");
		}
		if(meaningList.add(meaning) == false){
			throw new Exception("Phrase - Error occured while adding meaning");
		}
	}
	
	public void addMeaning(int index, Meaning meaning) throws NullPointerException, Exception{
		if(meaning == null){
			throw new NullPointerException("Context of use - Meaning cannot be null");
		}
		meaningList.add(index, meaning);
	}
	
	
	public List<Meaning> getMeaningList(){
		return meaningList;
	}
	
	void validate() throws NullPointerException, Exception{
		if(form == null){
			throw new NullPointerException("Phrase form cannot be null");
		}
		if(meaningList == null || meaningList.size() == 0){
			throw new NullPointerException("Phrase - Meaning cannot be null");
		}
		int seq = 1;
		for(Meaning mg : meaningList){
			mg.setSeq(seq);
			seq++;
			mg.validate();
		}
	}
	
	@Override
	public String toString(){
		String str = form+"\n";
		for(Meaning meaning :(List<Meaning>)DictionaryManager.isListSafe(meaningList)){
			str += meaning;
		}
		return str;
	}
	
}
