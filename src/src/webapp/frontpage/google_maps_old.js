// When refreshing the page scroll to the top of the page
topPage();

// Declaring the map and infobox objects
var map;
var infoview;
var service = new google.maps.places.PlacesService(map);

// Initialize the Map. This function is called directly from the Google API
// from the next script tagged region

function initMap() {

  var init_location = {lat: 37.98, lng: 23.72};
  // var pyrmont = new google.maps.LatLng(-33.8665433,151.1956316)
  // Initialize the map object
  map = new google.maps.Map(document.getElementById('map'), {zoom: 10, center: init_location});
  // Initialize a marker that points to your location
  var init_marker = new google.maps.Marker({position: init_location, map: map});
  // Initialize the infowindow object
  infowindow = new google.maps.InfoWindow();
  // Initialize the Places API that depicts places of a specific "type"
  // around a certain "radius"
  service.nearbySearch({location: init_location, radius: 500, type: ['store']}, callback);
}

// Check that the status pf the service is ok and create markers of the specified
// "type" place
function callback(results, status) {
  if (status === google.maps.places.PlacesServiceStatus.OK) {
	for (var i = 0; i < results.length; i++) {
	  createMarker(results[i]);
	}
  }
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({map: map, position: place.geometry.location});
  google.maps.event.addListener(marker, 'click', function() {
	infowindow.setContent(place.name);
	infowindow.open(map, this);
  });
}

var locationByName;
function returnLocation() {
  // First get the location from the form[id = frm0]
  var elemForm = document.getElementById('frm0');
  locationByName = '' + elemForm.elements[0].value;
  var request = {
	query: locationByName
  };
  service.findPlaceFromQuery(request, callback);
};