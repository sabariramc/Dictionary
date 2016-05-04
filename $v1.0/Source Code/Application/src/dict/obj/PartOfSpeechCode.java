/**
 * @(#)PartOfSpeechCode.java
 *
 *
 * @author Sabariram
 * @version 1.00 2016/2/2
 */

/**
 *This class models the parts of the speech a word can be used as
 **/

package dict.obj;

public class PartOfSpeechCode {
	
	/**code for the part of speech*/
	final private int code;
	
	/**abbreviated form of the part of speech*/
	final private String abbr;
	
	/**name of part of speech*/
	final private String name;
	
	PartOfSpeechCode(int code, String abbr, String name) {
    	this.code = code;
    	this.abbr = abbr;
    	this.name = name;
    }
    
    public int getCode(){
    	return code;
    }
    
    public String getAbbr(){
    	return abbr;
    }
    
    public String getName(){
    	return name;
    }
    
}