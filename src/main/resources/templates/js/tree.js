var items = [{
    "Id": "1",
    "title": "abc",
    "purchaseCode": "03100000-2"
},
  {
    "Id": "2",
    "title": "def",
    "purchaseCode": "03200000-3"
},
  {
    "Id": "3",
    "title": "ghi",
    "purchaseCode": "03210000-3"
}];
alert("ты пидор")
$('#searchNodes').on('click', function() {
  var searchInput = $('#code').val();
  var url = "http://localhost:8080/app/nodes?code=" + searchInput;
});

$('#searchNodes').on('click', (function() {
  var oldDiv = document.getElementById("old")
  var div;
  var li;
  var resultDiv = document.createElement("div")
  items.forEach(function(element) {
    div = document.createElement("div")
    li = document.createElement("li")
    li.innerText = element.purchaseCode + "\n" + element.title
    div.append(li)
    console.log("end")
    resultDiv.append(div)
  });

  var elem = document.getElementById("subNodes")
  if (oldDiv) {
    elem.replaceChild(resultDiv, oldDiv)
  } else {
      $('.subNodes').append(resultDiv)
  }
  resultDiv.setAttribute("id", "old")
}));

$('#searchNode').on('click', function() {
  var searchInput = $('#code').val();
  var url = "http://localhost:8080/app/node?code=" + searchInput;
});
