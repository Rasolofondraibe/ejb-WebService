<%@ page import="com.gestionsante.Personne"%>
<%@ page import="java.util.List"%>

<%
    Personne personne = new Personne();
    List<Personne> listePersonne = personne.getAllPersonne();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN</title>
</head>
<body>
    <form action="">
        <p>
            Personne : <select name="idPersonne" id="">
                            <% for(Personne p : listePersonne){ %>
                                    <option value="<%= p.getNumerocin() %>"><%= p.getNom()+" "+p.getPrenom() %></option>
                            <% } %>
                        </select>
        </p>
        <input type="submit" value="valider">
    </form>



    <%
        String idPersonne = request.getParameter("idPersonne");
        if(idPersonne != null){ %>
                <h1>Envoyer argent</h1>

                <form action="traitment.jsp">
                    <p>
                        Recepeteur : <select name="idRecepteur" id="">
                                        <% for(Personne p : listePersonne){ %>
                                                <option value="<%= p.getNumerocin() %>"><%= p.getNom()+" "+p.getPrenom() %></option>
                                        <% } %>
                                    </select>
                    </p>
                    <p>Argent :  <input type="text" name="valeur"></p>
                    <input type="hidden" value="<%=idPersonne%>" name="personneenvoie">
                    <input type="submit" value="valider">
                </form>
    <% } %>

</body>
</html>