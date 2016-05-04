/**
 * Dictionary 2.0
 * dict.obj
 * CtxOfUse.java
 * 08-Mar-2016
 */
package dict.obj;

/**
 * @author Sabari
 * @since Version 2.0
 */

import java.util.List;
import java.util.ArrayList;

public class CtxOfUse {
	
	private int seq;
	
	private String context;
	
	private List<Meaning> meaningList;
	
	public CtxOfUse(){
		seq = 0;
		context = null;
		meaningList = new ArrayList<Meaning>();
	}
	
	public CtxOfUse(CtxOfUse cu){
		seq = cu.getSeq();
		context = cu.getContext();
		meaningList = new ArrayList<Meaning>();
		List<Meaning> temp = cu.getMeaningList();
		for(Meaning me : temp){
			meaningList.add(new Meaning(me));
		}
	}
	
	CtxOfUse(int seq, String context, List<Meaning> meaningList){
		this.seq = seq;
		this.context = context;
		this.meaningList = meaningList;
	}
	
	public void setSeq(int seq) throws Exception{
		if(seq <=0){
			throw new Exception("Context seqence number is invalid");
		}
		
		this.seq = seq;
	}
	
	public int getSeq(){
		return seq;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getContext(){
		return context;
	}
	
	public void addMeaning(Meaning meaning) throws NullPointerException, Exception{
		if(meaning == null){
			throw new NullPointerException("Context of use - Meaning cannot be null");
		}
		if(meaningList.add(meaning) == false){
			throw new Exception("Context of use - Error occured while adding meaning");
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
		if(meaningList == null || meaningList.size() == 0){
			throw new NullPointerException("Context - Meaning cannot be empty");
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
		String str="";
		if(context != null){
			str = context+"\n";
		}
		
		for(Meaning meaning : meaningList){
			str += meaning;
		}
		
		return str;
	}
}
