var id=localStorage.getItem('bookID');
var apiLink='localhost:8080/api/v1/order-items';
function start(){
    console.log(id)
    getBook(viewBook)
}
start();
function  getBook(callback){
    fetch(apiLink+id).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function addToCart(book){
    var option={
        method: 'POST',
        body: JSON.stringify(book)
    };
    fetch(apiLink,option).then(function (responce){
        responce.json()
    });
}
function addAction(){
    var btnAdd=document.querySelector("btnAdd");
    btnAdd.onclick=function (){
        var formData={
            book: {id : "1"},
            customer_id : 1,
            quantity : "1"
        }
    }
    addToCart(formData);
}