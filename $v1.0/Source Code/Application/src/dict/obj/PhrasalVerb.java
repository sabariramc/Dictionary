/**
 *@(#)PhrasalVerb.java
 *
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

/**PhrasalVerb - This class is used to hold the phrasal verbs */
public class PhrasalVerb {
	
	/**pvSeq - Sequence in with the phrasal verb should be dispalyed*/
	private int pvSeq;
	
	/**form - hold the phrasal verb itself*/
	private String form;
	
	/**meaning - meaning of the phrasal verb*/
	private String meaning;
	
	/**example - example of usage of the phrasal verb*/
	private String example;
	
	PhrasalVerb(){
		
		pvSeq = 0;
		form = null;
		meaning = null;
		example = null;		
	}
	
	PhrasalVerb(int pvSeq, String form, String meaning, String example){
		this.pvSeq = pvSeq;
		this.form = form;
		this.meaning = meaning;
		this.example = example;
	}
	
	void setPVSeq(int pvSeq) {
		this.pvSeq = pvSeq;
	}
	
	public int getPVSeq() {
		return pvSeq;
	}
	
	void setForm(String form) throws NullPointerException {
		if(form == null)
			throw new NullPointerException("Phrasal verb's form cannot be null");
		this.form = form;
	}
	
	public String getForm() {
		return form;
	}
	
	void setMeaning(String meaning) throws NullPointerException {
		if(meaning == null)
			throw new NullPointerException("Phrasal verb's meaning cannot be null");
		this.meaning = meaning;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	void setExample(String example) throws NullPointerException{
		if(example == null)
			throw new NullPointerException("Phrasal verb's example cannot be null");
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
