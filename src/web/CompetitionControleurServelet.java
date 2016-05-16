package web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Competition;
import metier.IMetier;
import metier.Inscriptions;
import metier.MetierImpl;
import metier.Personne;

public class CompetitionControleurServelet extends HttpServlet{
 
IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CompetitionModel modelComp = new CompetitionModel();
		modelComp.setCompetition(metier.GetCompetition());
		String action = request.getParameter("action");
		request.setAttribute("modele", modelComp);
		
		if (action !=null) 
		{
			Inscriptions inscriptions = Inscriptions.getInscriptions();
			
			if (action.equals("Enregistrer")) 
			{
				String estEquipe;
				Competition compet;
				LocalDate date = LocalDate.parse(request.getParameter("date"));
				estEquipe = request.getParameter("simple");
				modelComp.setMode(request.getParameter("mode"));
				boolean varEstEquip;
				
				if(estEquipe.equals("equipe"))
					varEstEquip = true ;
				
				else
					varEstEquip =  false;
				
				if (modelComp.getMode().equals("Enregistrer")) 
				 {
					
					compet = inscriptions.createCompetition(request.getParameter("nom"), date, varEstEquip);
					modelComp.setCompet(compet);
					metier.addCompetition(modelComp.getCompet());
					modelComp.setCompetition(metier.GetCompetition());
					 
				 }else if(modelComp.getMode().equals("modifier"))
				 {
					 compet = inscriptions.createCompetition(request.getParameter("nom"), date, varEstEquip);
					 modelComp.setCompet(compet);
					 metier.UpdateCompetition(modelComp.getCompet(),modelComp.getNomCompt());
					 modelComp.setCompetition(metier.GetCompetition());					
				 }
		    }
			else if(action.equals("modifier"))
			{
				
				modelComp.setNomCompt(request.getParameter("nom"));
				modelComp.setCompet(metier.GetOneCompetition(modelComp.getNomCompt()));
				modelComp.setMode("modifier");				
			}
			else if(action.equals("chercher"))
			{
				modelComp.setMotCle(request.getParameter("motcle"));
				modelComp.setCompetition(metier.SerchCompetitionParMC(modelComp.getMotCle()));
				
			}
		}
		
		request.getRequestDispatcher("Competition.jsp").forward(request, response);
		
	}
	
}

