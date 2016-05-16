package metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import com.mysql.jdbc.PreparedStatement;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions
{
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();

	private Inscriptions()
	{
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe)
	{
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
		return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
		return personne;
	}
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Equipe createEquipe(String nom) 
	{
		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
		return equipe;
	}
	
	void remove(Competition competition)
	{ 
		competitions.remove(competition);
	}
	
	void remove(Candidat candidat)
	{
		candidats.remove(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	
	public static void main(String[] args)
	{
		System.out.println("*********************implementation BDD*************************\n");

		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Connection con = SingletonConnection.getConnection();
		MetierImpl metier = new MetierImpl();
		
//		for(Candidat equip : metier.GetAllEquipe())
//		{
//			System.out.println(equip.getNom());
//		}
//		Map<String, String> myMap = null;
//		myMap = metier.SerchPersonneInEquipeParMC("a");
//		for (Entry<String, String> currentEntry : myMap.entrySet()) {
//			String id = currentEntry.getKey();
//			String value = currentEntry.getValue();
//			System.out.println( id +"\t"+ value +"\n");
//		   
//		}
//		
//		
//		for (Map<String, String> e : metier.GetAllPersonneInEquipe())
//			{
//				System.out.println(pers.estEnEquipe()+ "\t"+"\n");
//				
//	
//			}
//		metier.DeletePersonneInEquipe("foot","jean");
//		metier.DeletePersonne("lucie");
//		System.out.println(metier.SerchEquipeParMC(""));
//		System.out.println(metier.SerchPersonneParMC("l"));
//		System.out.println(metier.SerchPersonneParMC("l"));
//		metier.addPersonne(inscriptions.createPersonne("Tony", "Dent de plomb", "azerty"));
//		metier.addPersonne(inscriptions.createPersonne("jean","emile","leot@gmail.com"));
//		SortedSet<Competition> AllPers = metier.GetAllCompetInscrit("dol");
//		for(Competition pers : AllPers)
//		{
//			System.out.println(pers.getNom()+ "\n");
////			System.out.println(pers.toString());
//
//		}
//		metier.addEquipe(inscriptions.createEquipe("foot"));
//  	metier.addEquipe(inscriptions.createEquipe("foot"));
//		LocalDate today = LocalDate.now();
//		metier.UpdateCompetition(inscriptions.createCompetition("Mondial de fléchettes",today, false),"foot");
//		metier.addCompetition(inscriptions.createCompetition("Mondial de fléchettes",today, false));
		
//		metier.addInscrit("foot", "Mondial de fléchettes");
//		metier.addPersonneInEquipe("foot", "tony");
//		metier.addPersonneInEquipe("foot", "kk");
//		
//        
//		System.out.println("*********************implementation BDD*************************\n");
//		
//		Competition flechettes = inscriptions.createCompetition("Mondial de fléchettes", null, false);
//		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty"), 
//				boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
//		flechettes.add(tony);
//		Equipe lesManouches = inscriptions.createEquipe("Les Manouches");
//		lesManouches.add(boris);
//		lesManouches.add(tony);
//		System.out.println(inscriptions);
//		lesManouches.delete();
//		System.out.println(inscriptions);
//		try
//		{
//			inscriptions.sauvegarder();
//		} 
//		catch (IOException e)
//		{
//			System.out.println("Sauvegarde impossible." + e);
//		}
//		LocalDate today = LocalDate.of(2020, 03, 05);
//		Competition flechettes = inscriptions.createCompetition("Mondial de fléchettes", today, false);
//		LocalDate today2 = LocalDate.of(2030, 03, 05);
//		flechettes.setDateCloture(today2);
//		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty");
//		try {
//			flechettes.add(tony);
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		
//		System.out.println(flechettes.inscriptionsOuvertes());

	}
}
