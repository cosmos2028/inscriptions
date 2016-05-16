package web;

import java.util.Set;
import java.util.SortedSet;

import metier.Candidat;
import metier.Equipe;

public class InscriptionModel {

	private Set<Candidat>  AllEquip;
	private String nameImput = "Envoyer";
	private String valSelect;
	

	public String getValSelect() {
		return valSelect;
	}

	public void setValSelect(String valSelect) {
		this.valSelect = valSelect;
	}

	public String getNameImput() {
		return nameImput;
	}

	public void setNameImput(String nameImput) {
		this.nameImput = nameImput;
	}

	public Set<Candidat> getAllEquip() {
		return AllEquip;
	}

	public void setAllEquip(SortedSet<Candidat> sortedSet) {
		AllEquip = sortedSet;
	} 
}
