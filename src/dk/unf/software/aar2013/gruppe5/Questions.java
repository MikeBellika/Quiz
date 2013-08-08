package dk.unf.software.aar2013.gruppe5;

import java.util.ArrayList;

public class Questions {
	
	String question;
	ArrayList<String> answers;
	
	public Questions(String question, ArrayList<String> answers){
		this.question = question;
		this.answers = answers;
	}
	public ArrayList<String> getAnswers(){
		return answers;
	}
	
}
