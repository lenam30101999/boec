var apiLink='http://localhost:8080/api/v1/books?textSearch';
var apiLinkGetOrder='http://localhost:8080/api/v1/orders?customer_id='
var customerName=sessionStorage.getItem("userName");
var customerID=sessionStorage.getItem("userID");
function start(){
    if (customerID=="" || customerID==null) {
        window.location.href = './login1.html';

    }
    getCustomer();
    getOrderCount(viewOrderCount)
    search();
    console.log(customerName)
    console.log(customerID)

}
start();
function getCustomer(){
    var name=`${customerName}`;
    document.getElementById("username").innerHTML = name;
}
function  getOrderCount(callback){
    fetch(apiLinkGetOrder+customerID).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewOrderCount(order){

    var totalOrder=`(${order.total_item})`;
    console.log(order.total_item);

    document.getElementById("orderCount").innerHTML = totalOrder;
}
function search(){



    var btnAdd=document.querySelector('#btnSearch');
    btnAdd.onclick=function (){
        var content=document.getElementById('inputSearch').value;
        console.log("chay den day"+content)
        localStorage.setItem('search',content);
        window.location.href = './search.html';
    }
}
