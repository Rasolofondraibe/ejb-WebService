<%@ page import="com.gestionterre.Terre"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/Style.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
    integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
    crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
    integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
    crossorigin=""></script>
</head>
<body>

 

    <div id="map"></div>
    <script>
        
        var map = L.map('map').setView([-18.971988198135847, 47.543453353323436], 13);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

        var coordonnee = '<%= request.getAttribute("coordonnee") %>';

        var jsonCoordonnee = JSON.parse(coordonnee);

        var polygone = [];
        for (var i = 0; i < jsonCoordonnee.length; i++) {
            var coordonneex = jsonCoordonnee[i].coordonneex;
            var coordonneey = jsonCoordonnee[i].coordonneey;
            polygone.push([coordonneex,coordonneey]);
        }
        console.log(coordonnee);
        console.log(polygone);
        L.polygon(polygone, { color: 'blue' }).addTo(map);

    </script>

    <%
        String idterre = String.valueOf(request.getAttribute("idterre"));
        Terre terre = new Terre();
        double a= terre.perimetre(idterre);

    %>

    <h1>Perimetre : <%= a %> m</h1>
    <h1>En km <%= a/1000 %></h1>
</body>
</html>