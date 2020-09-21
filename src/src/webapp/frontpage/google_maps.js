var map;
 function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 12,
	  center: new google.maps.LatLng(37.896219,23.742171),
	  mapTypeId: 'roadmap'
	});
	
	var input = document.getElementsByName('location');
	
	/*
	// Create a <script> tag and set the USGS URL as the source.
	var searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
	  searchBox.setBounds(map.getBounds());
	});

	*/
	
	// Create a <script> tag and set the USGS URL as the source.

	var script = document.createElement('script');

	// This example uses a local copy of the GeoJSON stored at
	// http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojsonp
	
	script.src = 'http://localhost:8000/';
	document.getElementsByTagName('head')[0].appendChild(script);
}
  // Loop through the results array and place a marker for each
  // set of coordinates.
window.eqfeed_callback = function(results) {
        for (var i = 0; i < results.features.length; i++) {
          var coords = results.features[i].geometry.coordinates;
          var latLng = new google.maps.LatLng(coords[1],coords[0]);
          var marker = new google.maps.Marker({
            position: latLng,
            map: map
          });
        }
      };