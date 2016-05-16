package metier;

import java.util.Map;
import java.util.SortedSet;

public interface IMetier {

	public void addPersonne(Personne pers);
	public void addCandidat(Candidat candid);
	public void addEquipe(Equipe equip);
	public void addCompetition(Competition comp);
	public void addInscrit(String candid ,String comp);
	public SortedSet<Personne> GetAllPersonne();
	public void addPersonneInEquipe(String equipe ,Personne pers);
	public SortedSet<Personne> SerchPersonneParMC(String mc);
	public void UpdatePersonne(Personne pers,String nom);
	public void DeletePersonne(String pers);
	public Personne GetPersonne(String pers);
	
	public SortedSet<Equipe> SerchEquipeParMC(String mc);
	public void DeleteEquipe(String equip);
	public SortedSet<Equipe> GetAllEquipe();
	
	public Map<String, String>SerchPersonneInEquipeParMC(String mc);
	public Map<String, String> GetAllPersonneInEquipe();
	public void DeletePersonneInEquipe(String equip ,String pers);
	public SortedSet<Competition> GetCompetition();
	public SortedSet<Competition> SerchCompetitionParMC(String mc);
	public Competition GetOneCompetition(String nom);
	public void UpdateCompetition(Competition compet,String nomcompet);
	public void UpdateEquipe(Equipe equip,String beforeName);
	public Map<String, String> GetAlliEquipInPersonne();
	public void updadePersonneInEquipe(Personne pers,String beforeName,String equip);
	public SortedSet<Candidat> GetAllCandidat();
	public SortedSet<Competition> GetAllCompetInscrit(String candid);

	
	
	
}
