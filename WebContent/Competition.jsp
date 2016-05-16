<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compétition</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
	function confirmer(url)
	{
		var rep=confirm("Etes vous sûr  de vouloir modifier?");
		
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
	<form action="competition.david" method="post">
            	<c:set var="today" value="<%=new java.util.Date()%>" />
                <label for="nom" class="col1_label"> Nom</label>
                <input type="text" name="nom" value="${modele.compet.nom }"/> <br/>
                
                <c:set var="max" value="${modele.compet.dateCloture}"/>
                <c:choose>
    				<c:when test="${not empty max}">
        				<c:set var="max" value="${modele.compet.dateCloture}"/>
    				</c:when>
    				
    				<c:otherwise>  
        				
        				<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" var="min"/>
    				</c:otherwise>
				</c:choose>
				
                <label for="date" class="col1_label"> Date</label>
                <input type="date" name="date" min="<c:out value="${min}" />" max="<c:out value="${max}" />" value="${modele.compet.dateCloture}" style ="width: 175px;"/> <br/>
                <c:choose>
				
   					 <c:when test="${modele.compet.enEquipe =='true'}">
   					 	<c:set var="checked" value="checked"/>
    				</c:when> 
    				   
   					<c:otherwise>
        				<c:set var="checked2" value="checked"/>
    				</c:otherwise>
    				
				</c:choose>
				
				<c:if test="${modele.mode=='Enregistrer' }">
            
                	<label for="simple">Simple</label><input type="radio" name="simple" value="simple" id="simple" <c:out value="${checked2}" />/> 
    		    	<label for="equipe">Par equipe</label><input type="radio" name="simple" value="equipe" id="equipe" <c:out value="${checked}" />/><br /><br />
          
        		</c:if>
       			<c:if test="${modele.mode=='modifier' }">
        
               		<input type="hidden" name="simple" value="simple" id="simple" <c:out value="${checked2}" />/> 
    		    	<input type="hidden" name="simple" value="equipe" id="equipe" <c:out value="${checked}" />/><br /><br />
                
        		</c:if>
        
                <input type="submit" value="Enregistrer" name="action" class="col1_input" >
                <input type="hidden" value ="${modele.mode }" name="mode"/>
              
    </form>
	<div> ${modele.msgError}</div>
	
	</div>
	
	
	<div>
		<form action="competition.david" method="post">
		<table>
			<tr>
				<td>Mot clé</td>
				<td><input type="text" name="motcle" value="${modele.motCle }" placeholder="nom compétition"></td>
				<td><input type="submit" value="chercher" name="action" ></td>
			</tr>
		</table>
	</form>
	
		<table class="table1" cellspacing="0" cellpadding="0">
			<tr>
				<th>Nom</th> <th>Date</th> <th>Type</th> <th>Modifier</th> 
			</tr>
			<c:forEach items="${modele.competition}" var="p">
			<tr>
				<td>${p.nom}</td>
				<td>${p.dateCloture}</td>
				
				<c:choose>
				
   					 <c:when test="${p.enEquipe=='true'}">
   					 	<td>equipe</td>
    				</c:when> 
    				   
   					<c:otherwise>
        				<td>simple</td> 
    				</c:otherwise>
    				
				</c:choose>
				
				<td><a href="javascript:confirmer('competition.david?action=modifier&nom=${p.nom}')"> <img src="img/edit.jpg" alt="edit_Image" /> </a></td>
			</tr>
			
			</c:forEach>
		</table>
	</div>
</body>
</html>