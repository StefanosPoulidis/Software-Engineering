
function topPage() {
	document.getElementById('toppage').scrollIntoView({ block: 'end',  behavior: 'smooth' });
};

function scrollToMap() {
	document.getElementById('map').scrollIntoView({ block: 'end',  behavior: 'smooth' });
	
};

function renewMap() {
	var map;
	
	map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 12,
	  center: new google.maps.LatLng(37.896219,23.742171),
	  mapTypeId: 'roadmap'
	});
	
	var script = document.createElement('script');
	// This example uses a local copy of the GeoJSON stored at
	// http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojsonp
	script.src = 'http://localhost:8000/';
	document.getElementsByTagName('head')[0].appendChild(script);
	
	map.data.addGeoJson('testMarkers2.js');
	
	
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
	}
};