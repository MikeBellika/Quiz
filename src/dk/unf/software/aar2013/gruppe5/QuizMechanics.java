package dk.unf.software.aar2013.gruppe5;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.util.ArrayList;

public class QuizMechanics {
	int score=0;
	int questionsRequired = 11;

	ArrayList<Questions> allQuestions; //Questions
	
	public QuizMechanics() {
		//a1 er altid det rigtige resultat
		allQuestions = new ArrayList<Questions>();
		
		String q = "Hvor mange millimeter går der på en meter?";
		String a1 = "1.000mm";
		String a2 = "100mm";
		String a3 = "10.000mm";
		String a4 = "10mm";
		
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		

		
		q = "Hvad er C++ og JAVA eksempler på?";
		a1 = "Programmeringssprog";
		a2 = "Aviser";
		a3 = "Browsere";
		a4 = "Kaffe";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		
		
		q = "Hvilken af de store data producenter lavede den første diskette?";
		a1 = "IBM";
		a2 = "Apple";
		a3 = "Microsoft";
		a4 = "Xerox";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));

		
		
		q = "I CD-rom, Hvad står CD for??";
		a1 = "Compact Disc";
		a2 = "Computer Disc";
		a3 = "Count Down";
		a4 = "Cheese Disc";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvem var det første menneske i rummet?";
		a1 = "Jurij Gagarin";
		a2 = "Niel Armstrong";
		a3 = "Chuck Norris";
		a4 = "Lance Armstong";
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		

		
		q = "Når du hører Radio, hvad står FM for? ";
		a1 = "Frekvensmodulation";
		a2 = "Football Manager";
		a3 = "F*ck me";
		a4 = "Free Meal";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvilken dag bliver Juleøl udgivet?";
		a1 = "1. fredag i november";
		a2 = "1. december";
		a3 = "sidste fredag i november";
		a4 = "1. advent";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvor mange dage er der i august?";
		a1 = "31";
		a2 = "29";
		a3 = "30";
		a4 = "28";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvad består molekyler af?";
		a1 = "Atomer";
		a2 = "Mol";
		a3 = "Ram";
		a4 = "Celler";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvad hedder SI-enheder for stofmængde?";
		a1 = "Mol";
		a2 = "Masse";
		a3 = "Molarmasse";
		a4 = "Molekyler";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvad koster en cheeseburger hos Burger King";
		a1 = "10 kroner";
		a2 = "20 kroner";
		a3 = "13 kroner";
		a4 = "15 kroner";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		
		q = "Hvad er en CPU?";
		a1 = "Processor";
		a2 = "Hukommelse";
		a3 = "Strømforsyning";
		a4 = "Modstander i spil";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad er en PSU?";
		a1 = "Strømforsyning";
		a2 = "Kabinet";
		a3 = "Processor";
		a4 = "Grafik kort";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad er en Neurolog ekspert i?";
		a1 = "Hjernen";
		a2 = "Fødderne";
		a3 = "Kroppen";
		a4 = "Fly";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad er grundstof nummer 47?";
		a1 = "Ag";
		a2 = "Au";
		a3 = "Br";
		a4 = "Cu";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad nummer er Br i det periodiske system?";
		a1 = "35";
		a2 = "17";
		a3 = "46";
		a4 = "22";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad er pokémon en forkortelse for?";
		a1 = "Pocket monsters";
		a2 = "det er ikke en forkortelse";
		a3 = "Poke monsters";
		a4 = "Digital monsters";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvad hedder verdens største producent af TV-spil konsoller?";
		a1 = "Sony";
		a2 = "X-box";
		a3 = "Ps 3";
		a4 = "Nintendo";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvilket redskab skal man bruge for at hugge gennem junglen?";
		a1 = "Machete";
		a2 = "Manchete";
		a3 = "Sabel";
		a4 = "Kårde";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvilken sport er opkaldet efter en engelsk by?";
		a1 = "Badminton";
		a2 = "Soccer";
		a3 = "Squash";
		a4 = "Cricket";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvor langt plejer et ultralandistance orienterinsløb at være for mænd?";
		a1 = "25 km";
		a2 = "42 km";
		a3 = "21 km";
		a4 = "75 km";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));		
		q = "Hvor mange spillere er der på hvert hold i flagball?";
		a1 = "5";
		a2 = "7";
		a3 = "11";
		a4 = "6";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		q = "Hvad er Fucapo?";
		a1 = "Energitilskudsmiddel";
		a2 = "Tøjmærkde";
		a3 = "Skomærke";
		a4 = "Drikkevarer";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		q = "Hvad hedder hovedstaden på Hawaii";
		a1 = "Honolulu";
		a2 = "Hawaii";
		a3 = "Hawaii City";
		a4 = "Iuloka";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er hovedstaden i Tyrkiet?";
		a1 = "Ankara";
		a2 = "Istanbul";
		a3 = "Alanya";
		a4 = "Athen";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er hovedstaden i Australien";
		a1 = "Canberra";
		a2 = "Sidney";
		a3 = "Melbourne";
		a4 = "Wellington";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er hovedstaden i Wales";
		a1 = "Cardiff";
		a2 = "Swansea";
		a3 = "Wales City";
		a4 = "London";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad hedder hovedstaden i Iran?";
		a1 = "Teheran";
		a2 = "Bagdad";
		a3 = "Kabul";
		a4 = "Kairo";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvor mange spillere er der på hvert hold i amerikansk fodbold?";
		a1 = "11";
		a2 = "15";
		a3 = "17";
		a4 = "21";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvor mange spillere er der på hvert hold i vandpolo?";
		a1 = "5";
		a2 = "7";
		a3 = "4";
		a4 = "6";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvilket land er frihedsgudinden fra?";
		a1 = "Frankrig";
		a2 = "USA";
		a3 = "Canada";
		a4 = "England";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		q = "Hvad giver 2+3*4";
		a1 = "14";
		a2 = "20";
		a3 = "9";
		a4 = "24";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er tidszonen i Danmark";
		a1 = "GMT +1";
		a2 = "GMT +2";
		a3 = "GMT +0";
		a4 = "GMT -1";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad slår man med i badminton?";
		a1 = "En ketcher";
		a2 = "Et bat ";
		a3 = "En kølle";
		a4 = "Hånden";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvilket land omtales som 'riget i midten'?";
		a1 = "Kina";
		a2 = "Vietnam";
		a3 = "Østrig";
		a4 = "Mongoliet";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er det største land i areal?";
		a1 = "Rusland";
		a2 = "Australien";
		a3 = "Kina";
		a4 = "Canada";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvilket land er verdens største ø?";
		a1 = "Grønland";
		a2 = "Australien";
		a3 = "Hawaii";
		a4 = "Fyn";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad hedder verdens mindste land?";
		a1 = "Vertikanet";
		a2 = "Langeland";
		a3 = "Luxemborg";
		a4 = "Tchad";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad er verdens mest befolkede land?";
		a1 = "Kina";
		a2 = "Indien";
		a3 = "USA";
		a4 = "Indonesien";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvad hedder Daft Punk nyeste album?";
		a1 = "Random access memories";
		a2 = "Get lucky";
		a3 = "Discovery";
		a4 = "Alive";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvilket engelsk fodboldhold har flest Barclays Premier Leauge titler?";
		a1 = "Manchester United";
		a2 = "Liverpool";
		a3 = "Chelsea";
		a4 = "Arsenal";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		q = "Hvem har vundet flest kampe til VM i fodbold?";
		a1 = "Mexico";
		a2 = "Tyskland";
		a3 = "Brasilien";
		a4 = "Spanien";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));
		
		q = "Hvem opfandt Facebook?";
		a1 = "Mark Zuckerberg";
		a2 = "Bill Gates";
		a3 = "Anders Frandsen";
		a4 = "Nedim Cokljat";
		
		answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		allQuestions.add(new Questions(q,answers));


		
		
	}
	
	public void correct(){
		score++;
	}
	
	public void reset(){
		//reset
	}


}
