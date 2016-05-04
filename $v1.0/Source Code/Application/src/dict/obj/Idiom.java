/**
 *@(#)Idiom.java
 *
 *
 *
 * @author Sabari
 * @version 1.00 Feb 3, 2016
 */
package dict.obj;

/**
 * @author Sabari
 *
 */

/**Idiom - This class hold the idioms formed with the associated word*/
public class Idiom {
	
	/**pCode - Part of speech a idiom belongs to*/
	private PartOfSpeechCode pCode;
	
	/**iSeq - Sequence number at which the idiom will appear*/
	private int iSeq;
	
	/**form - holds the idiom itself */
	private String form;
	
	/**meaning - meaning of the idiom*/
	private String meaning;
	
	/**example - example of usage of the idiom*/
	private String example;
	
	Idiom(){
		
		pCode = null;
		iSeq = 0;
		form = null;
		meaning = null;
		example = null;
	}
	
	Idiom(PartOfSpeechCode pCode, int iSeq, String form, String meaning, String example){
		
		this.pCode = pCode;
		this.iSeq = iSeq;
		this.form = form;
		this.meaning = meaning;
		this.example = example;
	}
	
	void setPCode(PartOfSpeechCode pCode) throws NullPointerException{
		if(pCode== null)
			throw new NullPointerException("Idiom's Part of Speech cannot be null");
		this.pCode = pCode;
	}
	
	public PartOfSpeechCode getPCode() {
		return pCode;
	}
	
	void setISeq(int iSeq) {
		this.iSeq = iSeq;
	}
	
	public int getISeq() {
		return iSeq;
	}
	
	void setForm(String form) throws NullPointerException{
		if(form == null)
			throw new NullPointerException("Idiom's form cannot be null");
		this.form = form;
	}
	
	public String getForm() {
		return form;
	}
	
	void setMeaning(String meaning) throws NullPointerException {
		if(meaning == null)
			throw new NullPointerException("Idiom's meaning cannot be null");
		this.meaning  = meaning;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	void setExample(String example) throws NullPointerException{
		if(example == null)
			throw new NullPointerException("Idiom's example cannot be null");
		this.example = example;
	}
	
	public String getExample() {
		return example;
	}
	
	@Override
	public String toString() {
		String str ="\t"+ form;
		str += "\n\t\t" +meaning;
		str += "\n\t\t\t"+example;
		return str;
	}
}
