package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;




public class MetierImpl implements IMetier {

	@Override
	public void addPersonne(Personne pers) {
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addPersonne(?,?,?)");
			ps.setString(1,pers.getPrenom());
			ps.setString(2,pers.getMail());
			ps.setString(3,pers.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("la personne ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addEquipe(Equipe equip) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addEquipe(?)");
			
			ps.setString(1,equip.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("l'equipe ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public SortedSet<Personne> GetAllPersonne() 
	{
		
		SortedSet<Personne> persAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call GetAllPersonne()");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Personne pers = new Personne(null, " ", " ", " ");
				
				pers.setMail(rs.getString("mail"));
				pers.setPrenom(rs.getString("prenom"));
				String nomCandidat = rs.getString("nomCandidat");
				
				PreparedStatement ps2 = conn.prepareStatement
						("call GetoneCandidat(?)");
				ps2.setString(1, nomCandidat);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				Candidat candit = new Personne(null, " ", " ", " ");
				candit.setNom(rs2.getString("nomCandidat"));
				pers.setNom(candit.getNom());
				persAll.add(pers);
				ps2.close();
			}
			
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return persAll;
		
	}

	@Override
	public void addCandidat(Candidat candid) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addCandidat(?)");
			
			ps.setString(1,candid.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("le candidat ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addCompetition(Competition comp) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addCompetition(?,?,?)");
			
			ps.setString(1,comp.getNom() ); 
			 ps.setDate(2, java.sql.Date.valueOf(comp.getDateCloture()));
			ps.setBoolean(3,comp.estEnEquipe() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("la competion ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addInscrit(String candid ,String comp)
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addInscrit(?,?)");
			
			ps.setString(1,candid); 
			ps.setString(2,comp ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("l'inscription  ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addPersonneInEquipe(String equipe, Personne pers) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addPersonneInEquipe(?,?,?,?)");
			
			ps.setString(1,equipe); 
			ps.setString(2,pers.getNom()); 
			ps.setString(3,pers.getPrenom()); 
			ps.setString(4,pers.getMail()); 
			ps.executeUpdate();
			ps.close();
			System.out.println("personne  ajoutee dans equipe avec succees!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public SortedSet<Personne> SerchPersonneParMC(String mc) {
		SortedSet<Personne> persAll = new TreeSet<>();

		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select prenom,mail,nomCandidat from personne,candidat where personne.id_candid = CANDIDAT.id_candid and nomCandidat like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Personne pers = new Personne(null, " ", " ", " ");
				
				pers.setMail(rs.getString("mail"));
				pers.setPrenom(rs.getString("prenom"));
				String nomCandidat = rs.getString("nomCandidat");
				
				PreparedStatement ps2 = conn.prepareStatement
						("call GetoneCandidat(?)");
				ps2.setString(1, nomCandidat);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				Candidat candit = new Personne(null, " ", " ", " ");
				candit.setNom(rs2.getString("nomCandidat"));
				pers.setNom(candit.getNom());
				persAll.add(pers);
				ps2.close();
			}
			
			ps.close();
			System.out.println("personne récupérée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return persAll;
	}

	@Override
	public void UpdatePersonne(Personne pers, String nom) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps2 = conn.prepareStatement
					("update candidat set nomCandidat = ? where nomCandidat = ? ");
			
			ps2.setString(1,pers.getNom());
			ps2.setString(2,nom);
			
			 ps2.executeUpdate();
			
			PreparedStatement ps = conn.prepareStatement
					("update personne set prenom = ?,mail=? where personne.id_candid in(select id_candid from candidat where nomCandidat = ? )");
			
			ps.setString(1,pers.getPrenom());
			ps.setString(2,pers.getMail());
			ps.setString(3,pers.getNom());
		    ps.executeUpdate();
		    
		    PreparedStatement ps3 = conn.prepareStatement
					("delete from composer where id_candid in(select id_candid FROM candidat where nomCandidat =  ? )");
			
			ps3.setString(1,pers.getNom());
			ps3.executeUpdate();
			 
			ps3.close();
		    ps2.close();
			ps.close();
			
			System.out.println("personne modifiée !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void DeletePersonne(String pers) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("delete from candidat where nomCandidat = ?");
			ps.setString(1,pers);
		    ps.executeUpdate();
			ps.close();
			System.out.println("personne supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Personne GetPersonne(String pers) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select prenom,mail,nomCandidat from personne,candidat where personne.id_candid = CANDIDAT.id_candid and nomCandidat = ?");
			ps.setString(1,pers ); 
			Personne per = new Personne(null, " ", " ", " ");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				per.setMail(rs.getString("mail"));
				per.setPrenom(rs.getString("prenom"));
				per.setNom(rs.getString("nomCandidat"));
				ps.close();
				System.out.println("personne récupée !!");
				return per;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SortedSet<Equipe> SerchEquipeParMC(String mc) {
		SortedSet<Equipe> EqupeAll = new TreeSet<>();

		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nomCandidat from equipe,CANDIDAT  where equipe.id_candid = CANDIDAT.id_candid  and nomCandidat like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Equipe equip = new Equipe(null, " ");
				
				equip.setNom(rs.getString("nomCandidat"));
				EqupeAll.add(equip);
				
			}
			
			ps.close();
			System.out.println("equipe récupérée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EqupeAll;
	}

	@Override
	public void DeleteEquipe(String equip) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("delete from candidat where nomCandidat = ?");
			ps.setString(1,equip);
		    ps.executeUpdate();
			ps.close();
			System.out.println("equipe supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public SortedSet<Equipe> GetAllEquipe() {
		
		SortedSet<Equipe> EquipAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nomCandidat  from equipe,candidat where EQUIPE.id_candid = CANDIDAT.id_candid");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Equipe equip = new Equipe(null, " ");
				
				equip.setNom(rs.getString("nomCandidat"));				
			
				EquipAll.add(equip);
				
			}
			
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EquipAll;
		
	}

	
	@Override
	public void DeletePersonneInEquipe(String equip, String pers) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("DELETE FROM  composer  WHERE   nomCandidat = ? AND nomCandidat_CANDIDAT = ?");
			ps.setString(1,equip);
			ps.setString(2,pers);
		    ps.executeUpdate();
			ps.close();
			System.out.println("personne supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> SerchPersonneInEquipeParMC(String mc)
	{
		
		Map<String, String> persinequip = new HashMap<String, String>();
		Connection conn = SingletonConnection.getConnection();
		String pers = "";
		String equip = "" ;
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("call GetAllPersonneInEquipe()");
			PreparedStatement ps2 = conn.prepareStatement("select nomCandidat from CANDIDAT c,equipe p where nomCandidat like ? and c.id_candid = p.id_candid and c.id_candid NOT IN (select id_candid from composer)and p.id_candid NOT IN (select id_candid_CANDIDAT from composer)");
			PreparedStatement ps3 = conn.prepareStatement("select nomCandidat from CANDIDAT where nomCandidat like ? and id_candid = ?");
			PreparedStatement ps4 = conn.prepareStatement("select nomCandidat from CANDIDAT where  id_candid = ?");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ps3.setString(1,"%"+mc+"%");
				ps3.setInt(2,rs.getInt("id_candid_CANDIDAT"));
				ResultSet rs3_ = ps3.executeQuery();
			
				if(rs3_.next()) 
				{
					ps4.setInt(1,rs.getInt("id_candid"));
					
					ResultSet rs4 = ps4.executeQuery();
					
					
					if(rs4.next())
						 pers = rs4.getString("nomCandidat");
					
					equip = rs3_.getString("nomCandidat");
					
					persinequip.put(pers,equip);
				}
					 
					
				
				
			}
			ps2.setString(1,"%"+mc+"%");
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				
				persinequip.put(rs2.getString("nomCandidat"),"Aucun");
				
			}
			
			ps.close();
			ps2.close();
			ps3.close();
			System.out.println("recherche effectuée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return persinequip;
			
	}

	@Override
	public Map<String, String> GetAllPersonneInEquipe() {
		

		Map<String, String> persinequip = new HashMap<String, String>();
		Connection conn = SingletonConnection.getConnection();
		String pers = "";
		String equip = "" ;
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("call GetAllPersonneInEquipe()");
			PreparedStatement ps2 = conn.prepareStatement("select nomCandidat from CANDIDAT c,equipe p where c.id_candid = p.id_candid and c.id_candid NOT IN (select id_candid from composer)and c.id_candid NOT IN (select id_candid_CANDIDAT from composer)");
			PreparedStatement ps3 = conn.prepareStatement("select nomCandidat from CANDIDAT where id_candid = ?");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{

				ps3.setInt(1,rs.getInt("id_candid"));
				ResultSet rs3 = ps3.executeQuery();
				
				
				if(rs3.next())
					 pers = rs3.getString("nomCandidat");
			
				
				ps3.setInt(1,rs.getInt("id_candid_CANDIDAT"));
				ResultSet rs3_ = ps3.executeQuery();
			
				if(rs3_.next()) 
					 equip = rs3_.getString("nomCandidat");
					
				persinequip.put(pers,equip);
				
			}
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				
				persinequip.put(rs2.getString("nomCandidat"),"Aucun");
				
			}
			
			ps.close();
			ps2.close();
			ps3.close();
			System.out.println("persInEquipe récupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return persinequip;
	}

	@Override
	public SortedSet<Competition> GetCompetition() {
		
		SortedSet<Competition> CompetpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Competition compet;
		LocalDate date ;
		try {
			PreparedStatement ps = conn.prepareStatement
					(" call GetCompetition()");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			
				CompetpAll.add(compet);
				
			}
			
			ps.close();
			
			System.out.println("compétition ajoutée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return CompetpAll;
	}
	public SortedSet<Competition> SerchCompetitionParMC(String mc){
		
		SortedSet<Competition> CompetpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Competition compet;
		LocalDate date ;
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nom_compet,dateclose,enEquipe  from COMPETITION where  nom_compet like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			
				CompetpAll.add(compet);
				
			}
			
			ps.close();
			
			System.out.println("compétition récupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return CompetpAll;
	}
	public Competition GetOneCompetition(String nom ){
		
		Connection conn = SingletonConnection.getConnection();
		Competition compet = null;
		LocalDate date ;
		try {
			PreparedStatement ps = conn.prepareStatement
					(" select nom_compet,dateclose,enEquipe  from COMPETITION where nom_compet = ? ");
			ps.setString(1,nom ); 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			}
			
			ps.close();
			
			System.out.println("compétition recupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return compet;
	}

	@Override
	public void UpdateCompetition(Competition compet,String nomcompet) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("update competition set nom_compet = ?,dateclose= ?, enEquipe = ? where nom_compet = ? ");
			ps.setString(1,compet.getNom());
			ps.setDate(2, java.sql.Date.valueOf(compet.getDateCloture()));
			ps.setBoolean(3,compet.estEnEquipe());
			ps.setString(4,nomcompet);
		    ps.executeUpdate();
			ps.close();
			System.out.println("competition modifiée !!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void UpdateEquipe(Equipe equip,String beforeName) {
		Connection conn = SingletonConnection.getConnection();
		

		try {
			PreparedStatement ps = conn.prepareStatement
					("update candidat set nomCandidat = ? where nomCandidat = ? ");
			ps.setString(1,equip.getNom());
			ps.setString(2,beforeName);
			
		    ps.executeUpdate();
			ps.close();
			System.out.println("equipe modifiée !!!!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Map<String, String> GetAlliEquipInPersonne() {
		
		Map<String, String> persinequip = new HashMap<String, String>();
		Connection conn = SingletonConnection.getConnection();
		String pers = "";
		String equip = "" ;
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("call GetAllPersonneInEquipe()");
			PreparedStatement ps2 = conn.prepareStatement("select nomCandidat from CANDIDAT c,personne p where c.id_candid = p.id_candid and c.id_candid NOT IN (select id_candid from composer)and c.id_candid NOT IN (select id_candid_CANDIDAT from composer)");
			PreparedStatement ps3 = conn.prepareStatement("select nomCandidat from CANDIDAT where id_candid = ?");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{

				ps3.setInt(1,rs.getInt("id_candid"));
				ResultSet rs3 = ps3.executeQuery();
				
				
				if(rs3.next())
					 pers = rs3.getString("nomCandidat");
			
				
				ps3.setInt(1,rs.getInt("id_candid_CANDIDAT"));
				ResultSet rs3_ = ps3.executeQuery();
			
				if(rs3_.next()) 
					 equip = rs3_.getString("nomCandidat");
					
				persinequip.put(pers,equip);
				
			}
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				
				persinequip.put(rs2.getString("nomCandidat"),"Aucune");
				
			}
			
			ps.close();
			ps2.close();
			ps3.close();
			System.out.println("persInEquipe récupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return persinequip;
	}

	@Override
	public void updadePersonneInEquipe(Personne pers, String beforeName, String equip) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps2 = conn.prepareStatement
					("update candidat set nomCandidat = ? where nomCandidat = ? ");
			
			ps2.setString(1,pers.getNom());
			ps2.setString(2,beforeName);
			
			 ps2.executeUpdate();
			
			PreparedStatement ps = conn.prepareStatement
					("update personne set prenom = ?,mail=? where personne.id_candid in(select id_candid from candidat where nomCandidat = ? )");
			
			ps.setString(1,pers.getPrenom());
			ps.setString(2,pers.getMail());
			ps.setString(3,pers.getNom());
		    ps.executeUpdate();
		    
		    PreparedStatement ps3 = conn.prepareStatement("call updatePersonneInEquipe(?,?)");
			
			ps3.setString(1,equip);
			ps3.setString(2,pers.getNom());
			ps3.executeQuery();
			
		    
		    ps2.close();
			ps.close();
			ps3.close();
			
			System.out.println("personne modifiée !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public SortedSet<Candidat> GetAllCandidat() {
		
		
		SortedSet<Candidat> CandidpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Candidat equipe;
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nomCandidat from CANDIDAT");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				equipe = new Equipe(null, rs.getString("nomCandidat"));
				
				CandidpAll.add(equipe);
				
			}
			
			ps.close();
			
			System.out.println("candidat recupéré !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return CandidpAll;
	}

	@Override
	public SortedSet<Competition> GetAllCompetInscrit(String candid) {
		
		SortedSet<Competition> CompetpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Competition compet;
		boolean enEquip = true;
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select id_candid from CANDIDAT where nomCandidat = ? and id_candid in(SELECT id_candid from personne)");
			ps.setString(1,candid ); 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 enEquip = false;				
			}
			
			PreparedStatement ps2 = conn.prepareStatement
					(" select nom_compet from COMPETITION where enEquipe = ?");
			ps2.setBoolean(1,enEquip ); 
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs2.next())
			{
				String gg = rs2.getString("nom_compet");
				
				compet = new Competition(null,gg , null, false);
				
				CompetpAll.add(compet);
				
			}
			
			ps2.close();
			ps.close();
			
			System.out.println("compétition recupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return CompetpAll;
	}


}
