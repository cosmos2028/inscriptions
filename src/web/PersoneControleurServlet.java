package web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Equipe;
import metier.IMetier;
import metier.Inscriptions;
import metier.MetierImpl;
import metier.Personne;

public class PersoneControleurServlet extends HttpServlet{

IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
     
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException 
	{
		doPost(request, response);		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PersonneModel model = new PersonneModel();
		String action = request.getParameter("action");
		
		model.setAllEquip(metier.GetAllEquipe());
		model.setPersonnes(metier.GetAllPersonne());
		model.setAllPersInEquipe(metier.GetAlliEquipInPersonne());
		
		request.setAttribute("modele", model);
		request.setAttribute("modelAllEquipe", model.getAllEquip());
		request.setAttribute("modelAllEquipe2", model.getAllPersInEquipe());
	
		model.setMsgError("erreur à afficher");
		if (action !=null) 
		{
			Inscriptions inscriptions = Inscriptions.getInscriptions();
			Personne  personne ;
			if (action.equals("chercher")) 
			{
				
				model.setMotCle(request.getParameter("motcle"));
				Set<Personne> personnes = metier.SerchPersonneParMC(model.getMotCle());
				model.setPersonnes(personnes);
			}
			else if(action.equals("delete"))
			{
				String pers = request.getParameter("nom");
				metier.DeletePersonne(pers);
				model.setPersonnes(metier.GetAllPersonne());
			}
			else if(action.equals("Enregistrer"))
			{
				
				 personne = inscriptions.createPersonne(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("mail"));
				 model.setPers(personne);
				 String equipe = request.getParameter("equipSelect");
				 model.setMode(request.getParameter("mode"));
				 
				 if (model.getMode().equals("Enregistrer")) 
				 {
					 if(equipe == null)
					 metier.addPersonne(model.getPers());
					 else
						 metier.addPersonneInEquipe(equipe, model.getPers());
				 }else if(model.getMode().equals("modifier"))
				 {
					 if(equipe == null)
						 metier.UpdatePersonne(model.getPers(),PersonneModel.getBeforeName());
					else
						 metier.updadePersonneInEquipe( model.getPers(),PersonneModel.getBeforeName(),equipe);
				 }
				 
				
			}else if(action.equals("modifier"))
			{
				String nom = request.getParameter("nom");
				Personne pers = metier.GetPersonne(nom);
				PersonneModel.setBeforeName(nom); 
				model.setPers(pers);
				model.setMode("modifier");
				model.setPersonnes(metier.GetAllPersonne());
			}
			
		}
		
		if(action != null) //pour favoriser l'affichage de la recherche
		{
			if(!(action.equals("chercher")))
			{
				model.setAllEquip(metier.GetAllEquipe());
				model.setPersonnes(metier.GetAllPersonne());
				model.setAllPersInEquipe(metier.GetAlliEquipInPersonne());
				
				request.setAttribute("modele", model);
				request.setAttribute("modelAllEquipe", model.getAllEquip());
				request.setAttribute("modelAllEquipe2", model.getAllPersInEquipe());
			}
		
		}
		
		request.getRequestDispatcher("Personne.jsp").forward(request, response);
		
		
		
	}
	
}

