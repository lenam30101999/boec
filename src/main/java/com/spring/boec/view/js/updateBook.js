var apiLink='http://localhost:8080/api/v1/books/get-book/';
var apiUpdateBook='http://localhost:8080//api/v1/books/update-book';
var bookID=localStorage.getItem("bookID");
function start(){
    getBook(view)
    updateBook()
}
start();

function  getBook(callback){
    fetch(apiLink+bookID).then(function (responce){
        return responce.json();

    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function view(book){
    console.log("chay qya day"+book.name)
    document.getElementById('bookName2').value=book.name;
    document.getElementById('stock2').value=book.stock;
    document.getElementById('price2').value=book.price;
    document.getElementById('image2').value=book.url_image;
    document.getElementById('page2').value=book.page_count;
    document.getElementById('author2').value=book.author.id;
    document.getElementById('publisher2').value=book.publisher.id;
}
function updateBook(){


    var btnUpdate=document.querySelector('#btnUpdateBook');
    btnUpdate.onclick=function (){
        var formData={
            id : bookID,
            name: document.getElementById('bookName2').value,
            stock: document.getElementById('stock2').value,
            price: document.getElementById('price2').value,
            url_image: document.getElementById('image2').value,
            page_count: document.getElementById('page2').value,
            author: {
                id: document.getElementById('author2').value
            },
            publisher: {
                id: document.getElementById('publisher2').value
            }
        }

        var option={
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        };
        fetch(apiUpdateBook,option).then(function (responce){
            responce.json()
        }).then(function (){
            window.location.href = './admin.html';
        });

    }
}