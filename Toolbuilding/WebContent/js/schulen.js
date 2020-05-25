/**
 * 
 */
function initMap() 
{ 
	mapid = L.map('mapid').setView([53.0792962,8.8016937],13); 
	myRenderer = L.canvas({ padding: 0.5 }); 
	osm = L.tileLayer(
	'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiY2FzeTA0MDciLCJhIjoiY2p2eXp5NXpwMGxjMTQ4bHM0cndoMnluNCJ9.MnEjD0zE9MmxONh9myv76g',
		{ 
			maxZoom: 18, 
			name: 'osm', 
			attribution: 
				'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a>, '+ 
				'Imagery &copy; <a href="http://mapbox.com">Mapbox</a>', 
				id: 'mapbox.streets'
		}).addTo(mapid); 
	baseMaps = 
	{ 
		"OSM": osm, 
	}; 
	popup = L.popup();
	mapid.on('click', onMapClick); 
}


function onMapClick(e) 
{
	popup
		.setLatLng(e.latlng)
		.setContent("Position: " + e.latlng.toString())
		.openOn(mapid);
}

function setMarker(lat, lon, markerClass, text) 
{
	var lamMarker = null;
	var radius = 3;
	
	switch (markerClass) 
	{
		case 'marker-red':
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#770000', fill: '#d63e2a', weight: 1
			})
			break;
		case "marker-green":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#008000', fill: '#72b026', weight: 1
			})
			break;
		case "marker-darkred":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#B40431', fill: '#FA5882', weight: 1
			})
			break;
		case "marker-purple":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#8904B1', fill: '#CC2EFA', weight: 1
			})
			break;		
		case "marker-darkgreen":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#0B610B', fill: '#298A08', weight: 1
			})
			break;	
		case "marker-darkblue":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#086A87', fill: '#0489B1', weight: 1
			})
			break;	
		case "marker-black":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#000000', fill: '#2E2E2E', weight: 1
			})
			break;	
		case "marker-orange":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#FF8000', fill: '#FE9A2E', weight: 1
			})
			break;	
		case "marker-darkorange":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#B45F04', fill: '#DBA901', weight: 1
			})
			break;	
		case "marker-brown":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#61380B', fill: '#5F4C0B', weight: 1
			})
			break;	
		case "marker-darkbrown":
			lamMarker = new L.circleMarker([lat, lon],
			{
				renderer: myRenderer, radius: radius, color: '#3B240B', fill: '#3A2F0B', weight: 1
			})
			break;	
	}
	
	marker.push(lamMarker);
	lamMarker.addTo(mapid).bindPopup(text + "<br>" + lat + ", " + lon).openPopup();
}

function deleteMarker() 
{
	
	for(i=0; i < marker.length; i++) 
	{
		 mapid.removeLayer(marker[i]);
	
	}
	marker = null;
}

var minY = 53.0;
var maxY = 54.0;
var minX = 8.0;
var maxX = 9.0;
function centerMap() 
{
	mapid.fitBounds([[minY, minX],[maxY, maxX]]);
}