package ece.ntua.softeng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: rgba(20, 20, 20, 0.8);\n" +
                "            font-family: sans-serif;\n" +
                "            scroll-behavior: smooth;\n" +
                "        }\n" +
                "\n" +
                "        #toppage {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        ul.navbar {\n" +
                "            list-style-type: none;\n" +
                "            margin: 0px;\n" +
                "            padding: 0px;\n" +
                "            overflow: hidden;\n" +
                "            background-color: rgba(163, 163, 163, 0.8);\n" +
                "            max-width: 100%;\n" +
                "            height: 40px;\n" +
                "\n" +
                "            border-style: solid;\n" +
                "            border-color: grey;\n" +
                "            border-width: 2px;\n" +
                "            border-radius: 7px;\n" +
                "        }\n" +
                "\n" +
                "        li.navbar {\n" +
                "            float: left;\n" +
                "            display: inline;\n" +
                "        }\n" +
                "\n" +
                "        li a {\n" +
                "            display: block;\n" +
                "\n" +
                "            height: 40px;\n" +
                "            max-width: 300px;\n" +
                "\n" +
                "            margin-right: 8px;\n" +
                "            padding: 5px 5px;\n" +
                "\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            font-size: 1.2em;\n" +
                "            font-family: sans-serif;\n" +
                "            background-color: rgba(163, 163, 163, 0);\n" +
                "        }\n" +
                "\n" +
                "        li a:hover:not(.active) {\n" +
                "            border-style: none;\n" +
                "            border-width: 2px;\n" +
                "            border-radius: 5px;\n" +
                "\n" +
                "            transition: background-color 1s;\n" +
                "            background-color: rgba(87, 87, 87, 0.8);\n" +
                "            color: black;\n" +
                "        }\n" +
                "\n" +
                "        li a.active {\n" +
                "            background-color: rgb(255, 63, 71);\n" +
                "        }\n" +
                "\n" +
                "        #fieldList {\n" +
                "            color: white;\n" +
                "            font-size: 1vw;\n" +
                "            font-family: sans-serif;\n" +
                "\n" +
                "            margin-top: 40px;\n" +
                "            margin-right: 300px;\n" +
                "            margin-left: 300px;\n" +
                "            padding-top: 15px;\n" +
                "\n" +
                "            overflow: hidden;\n" +
                "\n" +
                "            background-color: rgba(255, 63, 71, 0.8);\n" +
                "\n" +
                "            border-style: solid;\n" +
                "            border-color: rgba(255, 63, 71, 0.8);\n" +
                "            border-width: 2px;\n" +
                "            border-radius: 7px;\n" +
                "\n" +
                "            position: fixed;\n" +
                "        }\n" +
                "\n" +
                "        checkBox {\n" +
                "            resize: both;\n" +
                "        }\n" +
                "\n" +
                "        locationBar {\n" +
                "            margin: auto;\n" +
                "            padding: 0px 10px;\n" +
                "        }\n" +
                "\n" +
                "        locationBar input[type=\"text\"] {\n" +
                "            border-style: none;\n" +
                "            border-width: 2px;\n" +
                "            border-radius: 5px;\n" +
                "\n" +
                "            padding: 10px 10px;\n" +
                "\n" +
                "            width: 97%;\n" +
                "        }\n" +
                "\n" +
                "        #map {\n" +
                "            display: block;\n" +
                "            margin: 0;\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            background-color: lightgrey;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <script>\n" +
                "        function topPage() {\n" +
                "            document.getElementById('toppage').scrollIntoView();\n" +
                "        };\n" +
                "\n" +
                "        function scrollToMap() {\n" +
                "            document.getElementById('map').scrollIntoView();\n" +
                "        };\n" +
                "        function returnLocation() {};\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body id=\"toppage\">\n" +
                "<ul class=\"navbar\">\n" +
                "    <li class=\"navbar\"><a class=\"active\" href=\"#home\">Home</a></li>\n" +
                "    <li class=\"navbar\"><a href=\"#contact\">Contact</a></li>\n" +
                "    <li class=\"navbar\"><a href=\"#about\">About</a></li>\n" +
                "    <li class=\"navbar\" style=\"float: right;\"><a style=\"background-color: rgba(0, 0, 0, 0.6); border: 2px none; border-radius: 7px;\" href=\"#about\">Sign up</a></li>\n" +
                "    <li class=\"navbar\" style=\"float: right;\"><a href=\"#about\">Sign in</a></li>\n" +
                "</ul>\n" +
                "<div id=\"fieldList\" style=\"text-align: justify;\">\n" +
                "    <form id=\"frm0\" onsubmit=\"scrollToMap(); return false;\">\n" +
                "        <locationBar><input type=\"text\" placeholder=\"Please type your location..\"></locationBar><br><br>\n" +
                "        <input type=\"checkbox\" name=\"field\" value=\"Ανθρωπιστικής\"> Πεδίο Ανθρωπιστικής\n" +
                "        <input type=\"checkbox\" name=\"field\" value=\"Υγείας\"> Πεδίο Υγείας\n" +
                "        <input type=\"checkbox\" name=\"field\" value=\"Θετικής\"> Πεδίο Θετικής\n" +
                "        <input type=\"checkbox\" name=\"field\" value=\"Οικονομίας και Πληροφορικής\"> Πεδίο Οικονομίας και Πληροφορικής" +
                "    </form>\n" +
                "</div>\n" +
                "<div style=\"position: fixed; margin: 420px 200px;\">\n" +
                "    <p style=\"color: white; font-size: 3em; font-style: italic; font-weight: bold; text-shadow: -2px 2px 3px black;\"> Ο καλύτερος οδηγός φροντιστηρίων </p>\n" +
                "</div>\n" +
                "<div style=\"position: fixed; margin: 520px 200px;\">\n" +
                "    <p style=\"color: white; font-size: 1em; font-style: italic; font-weight: normal; text-shadow: -2px 2px 3px black;\"> Εδώ θα βρέις την καλύτερη προσφορά για φροντιστήρια που να προσαρμόζεται αποκλειστικά στις δικές σου ανάγκες </p>\n" +
                "</div>\n" +
                "<div style=\"background-color: black; height: 100%; width: 100%;\">\n" +
                "    <img src=\"alt_background.jpg\" alt=\"toppagebg\" width=\"100%\" height=\"100%\">\n" +
                "</div>\n" +
                "<div id=\"map\"></div>\n" +
                "<script async defer>\n" +
                "    // When refreshing the page scroll to the top of the page\n" +
                "    topPage();\n" +
                "\n" +
                "    // Declaring the map and infobox objects\n" +
                "    var map;\n" +
                "    var infoview;\n" +
                "    var service = new google.maps.places.PlacesService(map);\n" +
                "\n" +
                "    // Initialize the Map. This function is called directly from the Google API\n" +
                "    // from the next script tagged region\n" +
                "\n" +
                "    function initMap() {\n" +
                "\n" +
                "        var init_location = {lat: 37.98, lng: 23.72};\n" +
                "        // var pyrmont = new google.maps.LatLng(-33.8665433,151.1956316)\n" +
                "        // Initialize the map object\n" +
                "        map = new google.maps.Map(document.getElementById('map'), {zoom: 10, center: init_location});\n" +
                "        // Initialize a marker that points to your location\n" +
                "        var init_marker = new google.maps.Marker({position: init_location, map: map});\n" +
                "        // Initialize the infowindow object\n" +
                "        infowindow = new google.maps.InfoWindow();\n" +
                "        // Initialize the Places API that depicts places of a specific \"type\"\n" +
                "        // around a certain \"radius\"\n" +
                "        service.nearbySearch({location: init_location, radius: 500, type: ['store']}, callback);\n" +
                "    }\n" +
                "\n" +
                "    // Check that the status pf the service is ok and create markers of the specified\n" +
                "    // \"type\" place\n" +
                "    function callback(results, status) {\n" +
                "        if (status === google.maps.places.PlacesServiceStatus.OK) {\n" +
                "            for (var i = 0; i < results.length; i++) {\n" +
                "                createMarker(results[i]);\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    function createMarker(place) {\n" +
                "        var placeLoc = place.geometry.location;\n" +
                "        var marker = new google.maps.Marker({map: map, position: place.geometry.location});\n" +
                "        google.maps.event.addListener(marker, 'click', function() {\n" +
                "            infowindow.setContent(place.name);\n" +
                "            infowindow.open(map, this);\n" +
                "        });\n" +
                "    }\n" +
                "\n" +
                "    var locationByName;\n" +
                "    function returnLocation() {\n" +
                "        // First get the location from the form[id = frm0]\n" +
                "        var elemForm = document.getElementById('frm0');\n" +
                "        locationByName = '' + elemForm.elements[0].value;\n" +
                "        var request = {\n" +
                "            query: locationByName\n" +
                "        };\n" +
                "        service.findPlaceFromQuery(request, callback);\n" +
                "    };\n" +
                "</script>\n" +
                "<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyAlqmOZ4mLjS5sV22U_zFG9MITQ6LpIGLM&libraries=places&callback=initMap&language=el&region=GR\"></script>\n" +
                "</body>\n" +
                "</html>\n");

    }
}
