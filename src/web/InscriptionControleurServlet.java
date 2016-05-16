package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.SortedSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Competition;
import metier.IMetier;
import metier.Inscriptions;
import metier.MetierImpl;
import metier.Personne;

public class InscriptionControleurServlet extends HttpServlet{
 
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

		InscriptionModel model = new InscriptionModel();
		String action = request.getParameter("action");
		
	        String candSelect;
	        
	        request.setAttribute("model", model);
	        
	        if (action !=null) 
			{
				Inscriptions inscriptions = Inscriptions.getInscriptions();
				
				if (action.equals("Enregistrer")) 
				{
					  candSelect = request.getParameter("candidSelect");
					  request.setAttribute("modelCompet",  metier.GetAllCompetInscrit(candSelect));
					  model.setNameImput("Inscrire");
					  model.setValSelect(candSelect);
					  
					  System.out.println(model.getNameImput());
					
				}
			}
	     
	      request.setAttribute("model", model);
		request.setAttribute("modelCandid", metier.GetAllCandidat());
		
		request.getRequestDispatcher("Inscription.jsp").forward(request, response);
		
	}
	
}


