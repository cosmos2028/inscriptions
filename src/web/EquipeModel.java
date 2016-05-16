package web;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import metier.Equipe;
import metier.Inscriptions;
import metier.Personne;

public class EquipeModel 
{
	private  String mode = "Enregistrer";
	private String motCle ;
	private Equipe equip ;
	private String msgError;
	private String inputNom;
	private  Map<String, String> AllPersInEquipe;
	private static String beforeName;
	
	
	public static String getBeforeName() {
		return beforeName;
	}
	public static void setBeforeName(String beforeName) {
		EquipeModel.beforeName = beforeName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Map<String, String> getAllPersInEquipe() {
		return AllPersInEquipe;
	}
	public void setAllPersInEquipe(Map<String, String> allPersInEquipe) {
		this.AllPersInEquipe = allPersInEquipe;
	}
	public String getInputNom() {
		return inputNom;
	}
	public void setInputNom(String inputNom) {
		this.inputNom = inputNom;
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
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	
	public Equipe getEquip() {
		return equip;
	}
	public void setEquip(Equipe equip) {
		this.equip = equip;
	}
	

}
