/**
 * 
 */
var marker;
function showSchulen(resp,markerclass) 
{
	
	if (marker != null) 
	{
		deleteMarker();
		marker = new Array();
	}
	else
	{
		marker = new Array();
	}
	
	for (i = 0; i < resp.length; i++) 
	{
		setMarker(resp[i].adress.geodaten.lat,resp[i].adress.geodaten.lon,markerclass,
				'id: ' + resp[i].schulnr 
				+ '</br> Schulname: ' + resp[i].name 
				+ '</br> Adresse: ' + resp[i].adress.strasse + " " + resp[i].adress.hausnr
				+ '</br>' + resp[i].adress.plz + " " + resp[i].adress.ort);
	};
	
	marker[marker.length-1].closePopup();
	centerMap();
}