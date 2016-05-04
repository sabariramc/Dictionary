/**
 * * @(#)PartOfSpeech.java
 *
 *
 * @author Sabariram
 * @version 1.00 2016/2/3
 */

package dict.obj;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Sabari
 *
 */
public class PartOfSpeech {
	
	/** pCodeRef - refers a part of speech a word can be used as*/
	private PartOfSpeechCode pCode;
	
	/** cSeq - the sequence number of the context of use of the word for this part of speech*/
	private int cSeq;
	
	/**context - the context in which the word is used*/
	private String context;
	
	/**meanings - List of meanings a word can have in the particular context*/
	private List<ContextMeaning> meanings;
	
	private int mIndex;  
	
	{
		meanings = new ArrayList<ContextMeaning>();
		mIndex = 0;
	}
	
	PartOfSpeech(){
		pCode = null;
		cSeq = 0;
		context = null;
	}
	
	PartOfSpeech(PartOfSpeechCode pCode, int cSeq, String context) {
		this.pCode = pCode;
		this.cSeq = cSeq;
		this.context = context;
	}
	
	void setPCodeRef(PartOfSpeechCode pCode) {
		if(pCode== null)
			throw new NullPointerException("Word's Part of Speech cannot be null");
		this.pCode = pCode;
	}
	
	public PartOfSpeechCode getPCode() {
		return pCode;
	}
	
	void setCSeq(int cSeq) {
		this.cSeq = cSeq;
	}
	
	public int getCSeq() {
		return cSeq;
	}
	
	void setContext(String context){
		this.context = context;
	}
	
	public String getContext() {
		return context;
	}
	
	void addMeaning(ContextMeaning meaning) throws Exception{
		if(!meanings.add(meaning)) {
			throw new Exception("Adding meaning to word failed");
		}
	}
	
	List<ContextMeaning> getMeaningList() {
		return meanings;
	}
	
	/**Interface for the UI*/
	public ContextMeaning getNextMeaning() {
		if(mIndex<meanings.size()) {
			return meanings.get(mIndex++);
		}
		else
			return null;
	}
	
	@Override
	public String toString() {
		String str = pCode.getName();
		str += "\n\t";
		if(context != null)
			str += context;
		return str;
	}
	
}
