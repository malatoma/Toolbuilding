/**
 * 
 */

var featurevar; 
function showGebiet(data) 
{ 
	if (featurevar != null) featurevar.clearLayers() 
	featurevar = L.geoJson(data, {className: '#819FF7' }); 
	featurevar.addTo(mapid); 
	mapid.fitBounds(featurevar.getBounds());
}


/*function sucheGebiet() 
{
	var xmlhttp = new XMLHttpRequest();
	var art;
	
    for (var i = 0; i < document.bezirke.orte.length; i++)
	{
    	if (document.bezirke.orte[i].selected) 
    	{
            art = document.bezirke.orte[i].id;
        }
	}
	
	var url = "faces/schulen/geom/" + art + "/";
	xmlhttp.onreadystatechange = function () 
	{
		if (xmlhttp.readyState == 4) 
		{
			var resp = JSON.parse(xmlhttp.responseText);
			showGebiet(resp);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}*/

/*function sucheSchulenGebiet() 
{
	var xmlhttp = new XMLHttpRequest();
	var artOrt;
	var artSchule;
    for (var i = 0; i < document.bezirk_schulen.orte2.length; i++)
	{
    	if (document.bezirk_schulen.orte2[i].selected) 
    	{
            artOrt = document.bezirk_schulen.orte2[i].id;
        }
	}
    
    for (var i = 0; i < document.bezirk_schulen.schulenform2.length; i++)
	{
    	if (document.bezirk_schulen.schulenform2[i].selected) 
    	{
            artSchule = document.bezirk_schulen.schulenform2[i].value;
        }
	}
	
	var url = "faces/schulen/gebiet/" + artOrt + "/" + artSchule + "/";
	xmlhttp.onreadystatechange = function () 
	{
		if (xmlhttp.readyState == 4) 
		{
			var resp = JSON.parse(xmlhttp.responseText);
			//showGebiet(resp);
			showSchulen(resp,artSchule);
		}
	};
	
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}*/




