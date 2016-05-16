<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script type="text/javascript">

	function confirmer(url)
	{
		var rep=confirm("Etes vous sûr  de vouloir désinscrire ?");
		
		if(rep==true)
			{
				document.location=url;
			}
	}
	
	$(function () {
	     
	      $('#candidat').bind('change keyup',function () {
	   
	        //get value of selected option
	        var value = $(this).children("option:selected").attr('value');
	        
	        var nameImput = $( '#nameImput' ).val();
	        var nameImput2 = 'Envoyer';
	        var nameImput3 = 'Inscrire';
	        var nameImput4 = 'selected';
	        
	        if(nameImput == nameImput2 )
	        	{
	        	 $("#selectcompet").hide();
	        	
	        	}
	        else if(nameImput == nameImput3 &&  value!= nameImput4)
	        	{
	        	$("#selectcompet").hide();
	        	}

	      }).change();
	   
	    });
	
	
	
</script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<div id="col1">
		<form action="inscription.david" method="post"> 
            
            <p>
      			<label for="candidat">Candidat</label>
      			<select name="candidSelect" id="candidat" style="width: 126px;height: 24px;margin-left: 25px;" required>
         			<c:forEach items="${modelCandid}" var="p">
         			<option selected disabled hidden style='display: none' value='selected'>${model.valSelect}</option>
					<option value="<c:out value='${p.nom}'/>">${p.nom}</option>
				
					</c:forEach>
					  
      			</select>
      			
   			</p>
   			
   			<p id="selectcompet">
   			
      			<label for="competition">Compétition</label>
      			<select name="compselect" id="competition" style="width: 126px;height: 24px;">
         			<c:forEach items="${modelCompet}" var="p">
						<option value="<c:out value='${p.nom}'/>">${p.nom}</option>
				
					</c:forEach>  
      			</select>
   			</p>
   		<input type="submit" value="Enregistrer" name="action"  class="col1_input" style="margin-left: 115px;" >
   		<input type="hidden" value ="${model.nameImput}" name="mode" id="nameImput"/>
   	
		</form>
	
	</div>
	
	
	<div>
		<form action="competition.david" method="post">
		<table>
			<tr>
				<td>Mot clé</td>
				<td><input type="text" name="motcle" value="${modele.motCle }" placeholder="compétition ou candidat"></td>
				<td><input type="submit" value="chercher" name="action" ></td>
			</tr>
		</table>
	</form>
	
		<table class="table1" cellspacing="0" cellpadding="0">
			<tr>
				<th>Compétition</th> <th>Candidat</th> <th>Date</th> <th>Type</th> <th>Désinscrire</th> 
			</tr>
			<c:forEach items="${modele.personnes}" var="p">
			<tr>
				<td>${p.nom}</td>
				<td>${p.prenom}</td>
				<td>${p.mail}</td>
				<td>${p.mail}</td>
				<td><a href="javascript:confirmer('personne.david?action=delete&nom=${p.nom}')"> <img src="img/déinscrit.jpg" alt="déinscrit_Image" /> </a></td>
			</tr>
			
			</c:forEach>
		</table>
	</div>
	<span id="error" > ${modele.msgError}</span>
</body>
</html>