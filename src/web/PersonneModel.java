package web;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import metier.Equipe;
import metier.Personne;

public class PersonneModel 
{
	private String mode = "Enregistrer";
	private String msgError;
	private Set<Personne> personnes;
	private String motCle ;
	private Personne pers ;
	private  Map<String, String> AllPersInEquipe;
	private static String beforeName;
	private Set<Equipe>  AllEquip;
	
	public static String getBeforeName() {
		return beforeName;
	}
	
	public static void setBeforeName(String beforeName) {
		PersonneModel.beforeName = beforeName;
	}

	
	public Map<String, String> getAllPersInEquipe() {
		return AllPersInEquipe;
	}
	public void setAllPersInEquipe(Map<String, String> allPersInEquipe) {
		AllPersInEquipe = allPersInEquipe;
	}
	
	public Set<Equipe> getAllEquip() {
		return AllEquip;
	}
	public void setAllEquip(Set<Equipe> allEquip) {
		AllEquip = allEquip;
	}	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	public String getMotCle() {
		return motCle;
	}

	public Personne getPers() {
		return pers;
	}

	public void setPers(Personne pers) {
		this.pers = pers;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

}
