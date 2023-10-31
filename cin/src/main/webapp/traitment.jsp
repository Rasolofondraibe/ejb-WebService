<%@ page import="com.gestionargent.Transaction"%>
<%@ page import="com.gestionterre.Terre"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gestionterre.Coordonnee"%>

<%
String idRecepteur = request.getParameter("idRecepteur");
String valeur = request.getParameter("valeur");
String personneenvoie = request.getParameter("personneenvoie");
if(idRecepteur != null  && valeur != null && personneenvoie != null){
    double valeurDouble = Double.valueOf(valeur);
    Transaction transaction = new Transaction();
    transaction.toDoTransaction(idRecepteur,personneenvoie,valeurDouble); 
    
    //redirection vers la page index.jsp
        response.sendRedirect("index.jsp");
}



String[] coordonneexValues = request.getParameterValues("coordonneex[]");
String[] coordonneeyValues = request.getParameterValues("coordonneey[]");
String numerocin = request.getParameter("numerocin");
String nomterre = request.getParameter("nomterre");

if (coordonneexValues != null && coordonneeyValues != null && numerocin != null && nomterre != null) {
    // Le code à exécuter si toutes les conditions sont vraies
    Terre terre = new Terre();
    terre.achatterre(numerocin, nomterre, coordonneexValues, coordonneeyValues);
    //redirection vers la page index.jsp
    response.sendRedirect("carte.jsp");
}

String idterre = request.getParameter("idterre");

if(idterre != null){
    Terre terre = new Terre();
    String listeCoordonee = terre.listeCoordonneJson(idterre);
    request.setAttribute("coordonnee", listeCoordonee);
    request.setAttribute("idterre", idterre);
    RequestDispatcher dispatcher = request.getRequestDispatcher("cartePersonne.jsp");
    dispatcher.forward(request, response);
}
%>