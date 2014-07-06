socket = new WebSocket "ws://localhost:9000/socket"

socket.onmessage = (message) ->
  console = document.getElementById("console")
  console.innerHTML += "<div>" + message.data + "</div>"

document
  .getElementById "send"
  .addEventListener("click", (event) ->
    event.preventDefault()
    value = document.getElementById("value").value
    socket.send value)
