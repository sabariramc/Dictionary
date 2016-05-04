/**
 * PartOfSpeechCode.java
 * Sabari
 * @version 
 */
package dict.obj;

/**
 * @author Sabari
 * @since Version 2.0
 */
public class PartOfSpeech {
	
	private final int psCode;
	
	private final String abbr;
	
	private final String name;
	
	PartOfSpeech(int psCode, String abbr, String name){
		this.psCode = psCode;
		this.abbr = abbr;
		this.name = name;
	}
	
	public int getPSCode(){
		return psCode;
	}
	
	public String getAbbr(){
		return abbr;
	}
	
	public String getName(){
		return name;
	}
	
}
