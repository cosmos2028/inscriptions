package web;

import java.io.IOException;
import java.util.Map;
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

public class EquipeControleurServlet extends HttpServlet 
{

IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responses) throws ServletException, IOException {
		doPost(request,responses);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EquipeModel modelEquipe = new EquipeModel();
		String action = request.getParameter("action");
		modelEquipe.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
		request.setAttribute("modele", modelEquipe);
		
		if (action !=null) 
		{
			
			Inscriptions inscriptions = Inscriptions.getInscriptions();
			Equipe equip ;
			
			
			 if(action.equals("Enregistrer"))
			{
				 equip = inscriptions.createEquipe(request.getParameter("nom"));
				 modelEquipe.setEquip(equip);
				 modelEquipe.setMode(request.getParameter("mode"));
				 
					
				if (modelEquipe.getMode().equals("Enregistrer")) 
				 {
					metier.addEquipe(modelEquipe.getEquip());
					modelEquipe.setInputNom(modelEquipe.getEquip().getNom());
					 
				 }else if(modelEquipe.getMode().equals("modifier"))
				 
					 metier.UpdateEquipe(modelEquipe.getEquip(), EquipeModel.getBeforeName());
					 
				modelEquipe.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
			}
			 else if(action.equals("modifier"))
			 {
				 EquipeModel.setBeforeName(request.getParameter("nom")); 
				 modelEquipe.setInputNom(request.getParameter("nom"));
				 modelEquipe.setMode("modifier");				 
				 
			 }
			 else if(action.equals("delete"))
			 {
				 metier.DeleteEquipe(request.getParameter("nom"));
				 modelEquipe.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
			 }
			 
			 else if(action.equals("chercher"))
			 {
				 modelEquipe.setMotCle(request.getParameter("motcle"));
				 modelEquipe.setAllPersInEquipe(metier.SerchPersonneInEquipeParMC(modelEquipe.getMotCle()));
				 
			 }
			 
		}
		
		request.setAttribute("modelInEquip",  modelEquipe.getAllPersInEquipe());
		
		request.getRequestDispatcher("Equipe.jsp").forward(request, response);

	}
}

