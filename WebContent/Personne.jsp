<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personne</title>
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
	<div id="col1">
	<form action="personne.david" method="post">
        
     
                <label for="nom" class="col1_label"> Nom</label>
                    <input type="text" name="nom" value="${modele.pers.nom }" required/> <br/>
            
                <label for="prenom"> Prenom</label>
                    <input type="text" name="prenom" value="${modele.pers.prenom }" required/> <br/>
            
                <label for="mail" class="col1_label"> Mail</label>
                    <input type="text" name="mail" value="${modele.pers.mail }" required/> <br/>
               <p>
      				<label for="Equipe">Equipe</label>
      				<select name="equipSelect" id="Equipe" style=" width:178px;height:24px;"/>
         				<c:forEach items="${modelAllEquipe}" var="p">
         					<option selected disabled hidden style='display: none' value=''></option>
							<option value="<c:out value='${p.nom}'/>" >${p.nom}</option>
						</c:forEach>  
      				</select>
   			 </p>
           
                <input type="submit" value="Enregistrer" name="action" class="col1_input" >
                <input type="hidden" value ="${modele.mode }" name="mode"/>
    </form>
	<div> ${modele.msgError}</div>
	
	</div>
	
	
	<div>
		<form action="personne.david" method="post">
		<table>
			<tr>
				<td>Mot clé</td>
				<td><input type="text" name="motcle" value="${modele.motCle }" placeholder="Nom personne"></td>
				<td><input type="submit" value="chercher" name="action" ></td>
			</tr>
		</table>
		</form>
	
		<table class="table1" cellspacing="0" cellpadding="0">
			<tr>
				<th>Nom</th> <th>Prenom</th> <th>Mail</th> <th>Equipe</th> <th>Modifier</th> <th>Supprimer</th>
			</tr>
			<c:forEach items="${modele.personnes}" var="p">
			<tr>
				<td>${p.nom}</td>
				<td>${p.prenom}</td>
				<td>${p.mail}</td>
				
				<c:forEach items="${modelAllEquipe2}" var="p2">
				
					<c:if test="${p.nom == p2.key}">
					
        				<td>${p2.value}</td>
    					
        			</c:if>
			
				</c:forEach>
			
				
				<td><a href="personne.david?action=modifier&nom=${p.nom}"><img src="img/edit.jpg" alt="edit_Image" /></a></td>
				<td><a href="javascript:confirmer('personne.david?action=delete&nom=${p.nom}')"> <img src="img/supp.jpg" alt="supprimer_Image" /> </a></td>
				
			</tr>
			
			</c:forEach>
		</table>
	</div>
</body>
</html>

