<%@ page import="com.gestionsante.Personne"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gestionterre.Terre"%>

<%
    Personne personne = new Personne();
    List<Personne> listePersonne = personne.getAllPersonne();
%>

<script src="assets/js/sript.js"></script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carte</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
    integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
    crossorigin=""/>
    <link rel="stylesheet" href="assets/css/Style.css">
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
    integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
    crossorigin=""></script>
</head>
<body>

    <form action="carte.jsp">
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
    
    String numerocin = request.getParameter("idPersonne");
    if(numerocin != null){ %>

            <form action="traitment.jsp" >
                <p>Nom terre : <input type="text" name="nomterre"></p>
                <div class="all_input">
                    <p>coordonnee : </p>
                    <div class="input-ajout">
                        <input type="text" name="coordonneex[]">
                        <input id="coordonnee" type="text" name="coordonneey[]">
                    </div>
                </div>
                <input type="hidden" value="<%= numerocin %>" name="numerocin">
                <button class="btn">Ajouter</button>
                <input type="submit" value="acheter">
            </form>

            <div id="map"></div>

            <h3>Liste tany</h3>
            <ul>
                <% 
                    Terre terre = new Terre();
                    List<Terre> listeTerre = terre.getAllTerre(numerocin);
                    for(Terre t : listeTerre){ %>
                        <li><%= t.getNomterre() %> <a href="traitment.jsp?idterre=<%=t.getIdterre()%>">voir carte</a></li>
                <%   }
                %>
            </ul>
            
    <% } %>




    <script>
        
        var map = L.map('map').setView([-18.971988198135847, 47.543453353323436], 13);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);
        
        
        
        //enregistrement du coordonnee quand on clique sur la carte 

        const inputX = document.querySelector('[name="coordonneex[]"]');
        const inputY = document.querySelector('[name="coordonneey[]"]');


        
        var polygone = [];
        var polygonLayer = null;  //le dessin polygone sur la carte
        map.on('click', function(e) {
            
            //dupliquer le dv input-ajout
            const inputAjout = document.querySelector(".input-ajout");
            var latlng = e.latlng;
            var latitude = latlng.lat;
            var longitude = latlng.lng;
            
            var marker = L.marker([latitude, longitude]).addTo(map);

            if(inputX.value=="" && inputY.value==""){
                inputX.value = latitude;
                inputY.value = longitude;
            }else{
                var cloneInput = inputAjout.cloneNode(true);
                var clonedInputs = cloneInput.querySelectorAll("input");
                clonedInputs[0].value = latitude;
                clonedInputs[1].value = longitude;
                document.querySelector(".all_input").appendChild(cloneInput);
            }

            //mettre a jour le polygone
            if (polygonLayer) {
                map.removeLayer(polygonLayer);
            }

            polygone.push([latitude,longitude]);
            polygonLayer = L.polygon(polygone, { color: 'blue' }).addTo(map);
        });
    </script>




    <script>
        let destination = document.querySelector('.input-ajout');
        let btn = document.querySelector('.btn');
        btn.addEventListener('click',addChamps);
        let inputAjout=document.getElementById('coordonnee');

        function addChamps(e){
            e.preventDefault();
            const inputAjout = document.querySelector(".input-ajout");
            const cloneInput = inputAjout.cloneNode(true);


            //rendre vide les inputs
            const clonedInputs = cloneInput.querySelectorAll("input");
            clonedInputs.forEach(input => {
                input.value = "";
            });

            document.querySelector(".all_input").appendChild(cloneInput);
        }
    </script>
</body>
</html>