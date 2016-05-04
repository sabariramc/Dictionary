/**
 * Dictionary 2.0
 * dict.obj
 * ExplanationUnit.java
 * 08-Mar-2016
 */
package dict.obj;

/**
 * @author Sabari
 * @since Version 2.0
 */

import java.util.List;
import java.util.ArrayList;

public class ExplanationUnit {
	
	private int seq;
	
	private PartOfSpeech ps; 
	
	private List<CtxOfUse> contextList;
	
	private List<Phrase> idiomList;
	
	private List<Phrase> phrasalVerbList;
	
	public ExplanationUnit(){
		seq = 0;
		ps = null;
		contextList = new ArrayList<CtxOfUse>();
		idiomList = new ArrayList<Phrase>();
		phrasalVerbList = new ArrayList<Phrase>();
	}
	
	ExplanationUnit(int seq, PartOfSpeech ps, List<CtxOfUse> contextList, 
			List<Phrase> idiomList, List<Phrase> phrasalVerbList){
		
		this.seq = seq;
		this.ps = ps;
		this.contextList = contextList;
		this.idiomList = idiomList;
		this.phrasalVerbList = phrasalVerbList;
	}
	
	public ExplanationUnit(ExplanationUnit eu){
		seq = eu.getSeq();
		ps = eu.getPartOfSpeech();
		contextList = new ArrayList<CtxOfUse>();
		List<CtxOfUse> tempCtx = eu.getContextList();
		for(CtxOfUse cu : tempCtx){
			contextList.add(new CtxOfUse(cu));
		}
		idiomList = new ArrayList<Phrase>();
		List<Phrase> tempPhr = eu.getIdiomList();
		for(Phrase idiom : tempPhr){
			idiomList.add(new Phrase(idiom));
		}
		phrasalVerbList = new ArrayList<Phrase>();
		tempPhr = eu.getPhrasalVerbList();
		for(Phrase pverb : tempPhr){
			phrasalVerbList.add(new Phrase(pverb));
		}
		
	}
	
	void setSeq(int seq) throws Exception{
		if(seq<=0){
			throw new Exception("Explanation unit seqenece number invalid");
		}
		this.seq = seq; 
	}
	
	public int getSeq(){
		return seq;
	}
	
	public void setPartOfSpeech(PartOfSpeech ps) throws NullPointerException{
		if(ps == null){
			throw new NullPointerException("Part of speech cannot be null");
		}
		this.ps = ps;
	}
	
	public PartOfSpeech getPartOfSpeech(){
		return ps;
	}
	
	public void addContext(CtxOfUse context) throws NullPointerException, Exception{
		if(context == null){
			throw new NullPointerException("Context cannot be null");
		}
		if(contextList.add(context) == false){
			throw new Exception("Error occured while adding context");
		}
	}
	
	public void addContext(int index, CtxOfUse context) throws NullPointerException, Exception{
		if(context == null){
			throw new NullPointerException("Context cannot be null");
		}
		contextList.add(index, context);
	}
	
	public List<CtxOfUse> getContextList(){
		return contextList;
	}
	
	public void addIdiom(Phrase idiom) throws NullPointerException, Exception{
		if(idiom == null){
			throw new NullPointerException("Idiom cannot be null");
		}
		if(idiomList.add(idiom) == false){
			throw new Exception("Error occured while adding idiom");
		}
	}
	
	public void addIdiom(int index, Phrase idiom) throws NullPointerException, Exception{
		if(idiom == null){
			throw new NullPointerException("Idiom cannot be null");
		}
		idiomList.add(index, idiom);
	}
	
	public List<Phrase> getIdiomList(){
		return idiomList;
	}
	
	public void addPhrasalVerb(Phrase phrasalVerb) throws NullPointerException, Exception{
		if(phrasalVerb == null){
			throw new NullPointerException("Phrasal verb cannot be null");
		}
		if(phrasalVerbList.add(phrasalVerb) == false){
			throw new Exception("Error occured while adding phrasal verb");
		}
	}
	
	public void addPhrasalVerb(int index, Phrase phrasalVerb) throws NullPointerException, Exception{
		if(phrasalVerb == null){
			throw new NullPointerException("Phrasal verb cannot be null");
		}
		phrasalVerbList.add(index,phrasalVerb);
	}
	
	public List<Phrase> getPhrasalVerbList(){
		return phrasalVerbList;
	}
	
	void validate() throws NullPointerException, Exception{
		if(ps == null){
			throw new NullPointerException("Part of speech cannot be null");
		}
                if((contextList == null || contextList.size()==0) &&
                   (idiomList == null || idiomList.size() == 0) &&
                    (phrasalVerbList == null || phrasalVerbList.size() == 0)){
                    
                    throw new Exception(ps.getName()+" - Empty explanation");
                }
		int seq = 1;
		for(CtxOfUse cu : (List<CtxOfUse>)DictionaryManager.isListSafe(contextList)){
			cu.setSeq(seq);
			seq++;
			cu.validate();
		}
		seq = 1;
		for(Phrase idiom : (List<Phrase>)DictionaryManager.isListSafe(idiomList)){
			idiom.setSeq(seq);
			seq++;
			idiom.validate();
		}
		
		final int verbCode = 4;
		
		if(phrasalVerbList !=null && phrasalVerbList.size()>0 && ps.getPSCode() != verbCode){
			throw new Exception("Invalid association of phrasal verb with "+ps.getAbbr());
		}
		
		seq = 1;
		
		for(Phrase phrasalVerb : (List<Phrase>)DictionaryManager.isListSafe(phrasalVerbList)){
			phrasalVerb.setSeq(seq);
			seq++;
			phrasalVerb.validate();
		}
	}
	
	@Override
	public String toString(){
		String str = ps.getName()+"\n";
		
		if(contextList != null && contextList.size() > 0 ){
			for(CtxOfUse cts : contextList){
				str += cts;
			}
		}
		
		if(idiomList != null && idiomList.size()>0){
			str += "IDIOMS:\n";
			for(Phrase ph : idiomList){
				str += ph;
			}
		}
		
		if(phrasalVerbList != null && phrasalVerbList.size() > 0){
			str += "PHRASAL VERB: \n";
			for(Phrase ph : phrasalVerbList){
				str += ph;
			}
		}
		return str;
	}
	
}
