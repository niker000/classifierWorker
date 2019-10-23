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

$(document).ready(function() {

  $("ul").click(function() {
    var li = document.createElement("li")
    li.innerText = items[0].purchaseCode
    var ul = document.createElement("ul")
    var list = ul.appendChild(li)
     event.target.appendChild(ul)
    console.log("sss")
  });
});