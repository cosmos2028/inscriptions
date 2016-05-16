<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Equipe</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript">
	function confirmer(url)
	{
		var rep=confirm("Etes vous sûr  de vouloir supprimer?");
		
		if(rep==true)
			{
				document.location=url;
			}
	}
</script>

</head>
<body>
<%@ include file="menu.jsp" %>

	<div id="colEquip">
				
		<h3 style="margin-top: 0;">Ajouter une équipe</h3>
				
		<form action="equipe.david" method="post">
            
        	<label  class="col1_label"> Nom</label>
                   
        	<input type="text" name="nom" value="${modele.inputNom}" required/> <br/>
          			
            <input type="submit" value="Enregistrer" name="action" class="col1_input_equip" >
            <input type="hidden" value ="${modele.mode}" name="mode"/>
    	</form>
    			
		<div> ${modele.msgError}</div>
	
	</div>
				
	<table>
		<tr>
			
			<td>
				<div>
					<h3>Rechercher une équipe</h3>
					<form action="equipe.david" method="post">
						<table>
							<tr>
								<td>Mot clé</td>
								<td><input type="text" name="motcle" value="${modele.motCle }" placeholder="nom equipe"></td>
								<td><input type="submit" value="chercher" name="action" ></td>
							</tr>
						</table>
					</form>
	
					<table class="table1" cellspacing="0" cellpadding="0">
						<tr>
							<th>Nom</th> <th>Membre</th> <th>Modifier</th> <th>Supprimer</th> 
						</tr>
						
						<c:forEach items="${modelInEquip}" var="p">
							<tr>
        					 <c:choose>
  								<c:when test="${p.value == 'Aucun'}">
  								
   									<td>${p.key}</td>
    								<td>${p.value}</td>
    								<td><a href="equipe.david?action=modifier&nom=${p.key}"><img src="img/edit.jpg" alt="edit_Image" /></a></td>
									<td><a href="javascript:confirmer('equipe.david?action=delete&nom=${p.key}')"><img src="img/supp.jpg" alt="supprimer_Image" /></a></td>
									
  								</c:when>
  								<c:otherwise>
  								
    								<td>${p.value}</td>
									<td>${p.key}</td>
									<td><a href="equipe.david?action=modifier&nom=${p.value}"><img src="img/edit.jpg" alt="edit_Image" /></a></td>
									<td><a href="javascript:confirmer('equipe.david?action=delete&nom=${p.value}')"><img src="img/supp.jpg" alt="supprimer_Image" /></a></td>
									
 									</c:otherwise>
							</c:choose>

		
							</tr>
			
						</c:forEach>
					</table>
				</div>
			</td>
		 </tr>
		
	</table>

</body>
</html>