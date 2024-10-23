// I am very aware that this would be a major security vulernability in a real application, but this is just a demo and I will revoke the API key after the presentation.
// In a real application this would ofcourse be unacceptable and the API key would be stored on the server and the server would make the requests to the API.
const API_KEY = "5b3ce3597851110001cf6248154edb3b911847e7b50a4bfbfe5eaa8d";
const OPENROUTEBASEURL = `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${API_KEY}`;

document.addEventListener("DOMContentLoaded", init);

function init() {
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition(createMap, () => console.log("User has denied!"));
  } else {
    console.log("Browser does not support geolocation");
  }
}

function createMap(userLocation) {
  const userCoords = [userLocation.coords.longitude, userLocation.coords.latitude];
  const currentLongLat = [userCoords[0], userCoords[1]];
  const randomCloseCoord = ol.proj.fromLonLat(currentLongLat);

  const { emsCoord, emsRandomCloseCoord } = createRandomLocation(userCoords);

  const map = buildMap(randomCloseCoord, emsRandomCloseCoord, currentLongLat);
  const emsMarkerLayer = createEMSMarker(emsRandomCloseCoord);

  applyRoute(userCoords, map, emsCoord, emsMarkerLayer);
}

function buildMap(randomCloseCoord, emsRandomCloseCoord, currentLongLat) {
  return new ol.Map({
    target: 'direction-map',
    layers: [
      new ol.layer.Tile({
        source: new ol.source.OSM()
      }),
      createUserMarker(randomCloseCoord),
    ],
    view: new ol.View({
      center: ol.proj.fromLonLat(currentLongLat),
      zoom: 12.3
    })
  });
}

function createRandomLocation(userCoords) {
  const randomLong = (Math.random() * 2 < 1 ? -1 : 1) * Math.random() * 0.1;
  const randomLat = (Math.random() * 2 < 1 ? -1 : 1) * Math.random() * 0.1;
  const emsCoord = [userCoords[0] + randomLong, userCoords[1] + randomLat];
  const emsRandomCloseCoord = ol.proj.fromLonLat(emsCoord);

  return { emsCoord, emsRandomCloseCoord };
}

function createUserMarker(coord) {
  const marker = [new ol.Feature({
    type: 'marker',
    geometry: new ol.geom.Point(coord)
  })];

  const markerVectors = new ol.source.Vector({
    features: marker
  });

  return new ol.layer.Vector({
    source: markerVectors,
    style: new ol.style.Style({
      image: new ol.style.Icon({
        src: "./images/map-marker.png",
        anchor: [0.5, 1]
      })
    })
  });
}

function createEMSMarker(coord) {
  const emsMarker = [new ol.Feature({
    type: 'marker',
    geometry: new ol.geom.Point(coord)
  })];

  const emsMarkerVectors = new ol.source.Vector({
    features: emsMarker
  });

  return new ol.layer.Vector({
    source: emsMarkerVectors,
    style: new ol.style.Style({
      image: new ol.style.Icon({
        src: "./images/alarm.png",
        anchor: [0.5, 1],
        scale: 0.1,
      })
    })
  });
}

function applyRoute(userCoords, map, emsCoord, emsMarkerLayer) {
  getClosestRoute(userCoords[0], userCoords[1], emsCoord[0], emsCoord[1])
    .then(response => {
      if (!response) {
        console.error('Failed to get route');
        return;
      }

      const { location, route } = response;
      addLayers(route, map, location, emsMarkerLayer);
      startRouteSimulation(route, map, location, emsMarkerLayer);
    });
}

function drawRoute(route) {
  const polyline = route.geometry.coordinates.map(el => ol.proj.fromLonLat(el));

  return new ol.layer.Vector({
    source: new ol.source.Vector({
      features: [new ol.Feature({
        type: 'route',
        geometry: new ol.geom.LineString(polyline)
      })]
    }),
    style: new ol.style.Style({
      stroke: new ol.style.Stroke({ color: "rgba(255, 87, 34, 0.8)", width: 6 }),
    })
  });
}

async function getClosestRoute(startlong, startlat, tolong, tolat) {
  const toCoord = [tolong, tolat];
  const url = `${OPENROUTEBASEURL}&start=${startlong},${startlat}&end=${toCoord[0]},${toCoord[1]}`;
  const response = await fetch(url);

  if (!response.ok) {
    console.error(`API request failed with status ${response.status}`);
    return {};
  }

  const result = await response.json();
  return { location: toCoord, route: result.features[0] };
}

function removeLayers(map, routeLayer, markerLayer, emsMarkerLayer) {
  map.removeLayer(routeLayer);
  map.removeLayer(markerLayer);
  map.removeLayer(emsMarkerLayer);
}

function addLayers(route, map, location, emsMarkerLayer) {
  const routeLayer = drawRoute(route);
  map.addLayer(routeLayer);

  const markerLayer = createUserMarker([location[0], location[1]]);
  map.addLayer(markerLayer);

  map.addLayer(emsMarkerLayer);

  return { routeLayer, markerLayer };
}

function startRouteSimulation(route, map, location, emsMarkerLayer) {
  let index = route.geometry.coordinates.length - 1;

  const inteval = setInterval(() => {
    const nextPointOnRoute = route.geometry.coordinates[index];
    if (nextPointOnRoute === undefined) {
      clearInterval(inteval);
      document.querySelector("#ems-status").textContent = "EMS have arrived!";
      return;
    }
    const nextPointOnRouteCoord = ol.proj.fromLonLat(nextPointOnRoute);

    emsMarkerLayer.getSource().getFeatures()[0].getGeometry().setCoordinates(nextPointOnRouteCoord);

    index = (index - 1);
  }, 500); // Adjust the interval as needed
}
