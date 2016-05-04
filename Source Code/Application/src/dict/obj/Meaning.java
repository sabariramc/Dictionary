/**
 * Meaning.java
 */

package dict.obj;

/**
 *@author Sabariram
 *@since Version 2.0
 */

import java.util.List;
import java.util.ArrayList;

public class Meaning {
	
	private int seq;
	
	private String meaning;
	
	private List<String> exampleList;
	
	public Meaning(){
		seq = 0;
		meaning = null;
		exampleList = new ArrayList<String>();		
	}
	
	public Meaning(Meaning me){
		seq =  me.getSeq();
		meaning = me.getMeaning();
		exampleList = new ArrayList<String>();
		List<String> temp = me.getExampleList();
		for(String str : temp){
			exampleList.add(new String(str));
		}
	}
	
	Meaning(int seq, String meaning, List<String> exampleList){
		this.seq = seq;
		this.meaning = meaning;
		this.exampleList = exampleList;
	}
	
	public void setSeq(int seq) throws Exception{
		if(seq<=0){
			throw new Exception("Meaning sequence invalid");
		}
		this.seq = seq;
	}
	
	public int getSeq(){
		return seq;
	}
	
	public void setMeaning(String meaning) throws NullPointerException{
		if(meaning == null){
			throw new NullPointerException("Meaning cannot be empty");
		}
		this.meaning = meaning;
	}
	
	public String getMeaning(){
		return meaning;
	}
	
	public void addExample(String example)throws NullPointerException, Exception{
		if(example == null){
			throw new NullPointerException("Example cannot be null");
		}
		if(exampleList.add(example) == false){
			throw new Exception("Error occured while adding example");
		}
	}
	
	public void addExample(int index, String example)throws NullPointerException, Exception{
		if(example == null){
			throw new NullPointerException("Example cannot be null");
		}
		exampleList.add(index, example);
	}
	
	public List<String> getExampleList(){
		return exampleList;
	}
	
	void validate() throws NullPointerException{
		if(meaning == null){
			throw new NullPointerException("Meaning cannot be empty");
		}
	}
	
	@Override
	public String toString(){
		String str = seq+".\t";
		str += meaning+"\n\t";
		for(String example : (List<String>) DictionaryManager.isListSafe(exampleList)){
			str += "--"+example;
		}
		str += "\n";
		return str;
	}
}
