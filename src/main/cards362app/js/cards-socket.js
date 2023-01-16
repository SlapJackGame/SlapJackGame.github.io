var wsUri = "ws://localhost:8080/cards362/socket";
var output;

function init() {
  wsUri += "?" + window.location.href.split("?")[1];
  output = document.getElementById("output");
  testWebSocket();
}

function testWebSocket() {
  websocket = new WebSocket(wsUri);
  websocket.onopen = function(evt) { onOpen(evt) };
  websocket.onclose = function(evt) { onClose(evt) };
  websocket.onmessage = function(evt) { onMessage(evt) };
  websocket.onerror = function(evt) { onError(evt) };
}

function onOpen(evt) {
  console.log("CONNECTED");
  console.log("href=" + window.location.href);
}

function onClose(evt) {
  console.log("DISCONNECTED");
}

function onMessage(evt) {
  console.log('RECEIVED: ' + evt.data);
  result = eval(evt.data);
  doSend(result);
}

function onError(evt) {
  console.log('ERROR: ' + evt.data);
}

function doSend(message) {
  console.log('SENT: ' + message);
  websocket.send(message);
}

window.addEventListener("load", init, false);

allCards = {}

function cardMouseEvent(ev) {
  var card = $(this).data('card');
  doSend(JSON.stringify({event: 'cardevent', 'id': card.id}));
}

