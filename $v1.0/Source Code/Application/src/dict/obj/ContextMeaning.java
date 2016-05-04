/**
 * @(#)ContextMeaning.java
 *
 *
 * @author Sabariram
 * @version 1.00 2016/2/2
 */

/**
 *This class holds the meaning of the word and example using the same meaning  
 */
package dict.obj;

public class ContextMeaning {
	
	/**mSeq is the sequence number, in that meaning has to appear within a context*/
	private int mSeq;
	
	private String meaning;
	
	private String example;
	
	public ContextMeaning() {
		mSeq = 0;
		meaning = null;
		example = null;
    }
	
	ContextMeaning(int mSeq, String meaning, String example){
		this.mSeq = mSeq;
		this.meaning = meaning;
		this.example = example;
	}
	
	void setMSeq(int mSeq) {
		this.mSeq = mSeq;
	}
    
	public int getMSeq() {
		return mSeq;
	}

	void setMeaning(String meaning) throws NullPointerException{
		if(meaning == null)
			throw new NullPointerException("Word's meaning cannot be null");
		this.meaning = meaning;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	void setExample(String example) throws NullPointerException {
		if(example == null)
			throw new NullPointerException("Word's example cannot be null");
		this.example = example;
	}
	
	public String getExample() {
		return example;
	}
	
	@Override
	public String toString() {
		String str  ="\t\t"; 
		str += Integer.toString(mSeq);
		str +=".   "+meaning+"\n\t\t\t"+example;
		return str;
	}
}