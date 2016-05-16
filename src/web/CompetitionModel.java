package web;

import java.util.Set;

import metier.Inscriptions;
import metier.Competition;

public class CompetitionModel {

	private String mode = "Enregistrer";
	private static  String  nomCompt ;
	private String msgError;
	private Set<Competition> competition;
	private String motCle ;
	private Competition compet ;
	
	public String getNomCompt() {
		return nomCompt;
	}
	public void setNomCompt(String nomCompt) {
		this.nomCompt = nomCompt;
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
	public Set<Competition> getCompetition() {
		return competition;
	}
	public void setCompetition(Set<Competition> competition) {
		this.competition = competition;
	}
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public Competition getCompet() {
		return compet;
	}
	public void setCompet(Competition compet) {
		this.compet = compet;
	}

	
}
