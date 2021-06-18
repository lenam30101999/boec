var apiLink='http://localhost:8080/api/v1/books?textSearch';
var apiElectro='http://localhost:8080/api/v1/electronics?textSearch';
var apiClothes='http://localhost:8080/api/v1/clothes?textSearch';
var apiAddBook='http://localhost:8080/api/v1/books/add-book';
var apiDeleteBook='http://localhost:8080//api/v1/books/delete-book/';
var apiUpdateBook='localhost:8080//api/v1/books/update-book';
var adminName=sessionStorage.getItem("userName");
var adminID=sessionStorage.getItem("userID");
function start(){
    if (adminID=="" || adminID==null) {
        window.location.href = './login1.html';

    }
    getAdmin();

    getAllBook(viewAllBook)
    getAllElectro(viewAllElectro)
    getAllClothes(viewAllClothes)
    addBook()

}
start();
function getAdmin(){
    var name=`${adminName}`;
    document.getElementById("username").innerHTML = name;
}
function  getAllBook(callback){
    fetch(apiLink).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllBook(books){
    var listBook=document.querySelector('#listBook');
    var htmls=books.map(function(book){
        return `<tr>
                    <td><img src=${book.url_image} alt=“image” width='150' height='150'></td>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.page_count}</td>
                    <td>${book.price}</td>
                    <td>${book.stock}</td>
                    <td>${book.author.name}</td>
                    <td>${book.publisher.name}</td>
                    <td>${book.avg_rating}</td>
                    <td>
                        <a class="btn btn-danger" onclick="deleteBook(${book.id})">Delete</a>
                        <a class="btn btn-secondary" onclick="updateBook(${book.id})">Update</a>
                    </td>
                </tr>`;
    })
    listBook.innerHTML=htmls.join('');

}
function addBook(){
    var btnAdd=document.querySelector('#btnAddBook');
    if (true){
        btnAdd.onclick=function (){
            var formData={
                name: document.getElementById('bookName').value,
                stock: document.getElementById('stock').value,
                price: document.getElementById('price').value,
                url_image: document.getElementById('image').value,
                page_count: document.getElementById('page').value,
                author: {
                    id: document.getElementById('author').value
                },
                publisher: {
                    id: document.getElementById('publisher').value
                }
            }

            var option={
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            };
            fetch(apiAddBook,option).then(function (responce){
                responce.json()
            }).then(function (){

                window.location.href = './admin.html';
            });
        }
    }
}
function deleteBook(id){
    var option={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },

    };
    fetch(apiDeleteBook+id,option).then(function (responce){
        responce.json()
    }).then(function (){
        getAllBook(viewAllBook);
    });
}
function  getBook(callback){
    fetch(apiLink+bookId).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function updateBook(id){
    localStorage.setItem('bookID',id);
    window.location.href = './updateBook.html';

}

function  getAllElectro(callback){
    fetch(apiElectro).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllElectro(books){
    var listBook=document.querySelector('#listElectro');
    var htmls=books.map(function(book){
        return `<tr>
                    <td><img src=${book.url_image} alt=“image” width='150' height='150'></td>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.power}</td>
                    <td>${book.price}</td>
                    <td>${book.stock}</td>
                    <td>${book.manufacturer.name}</td>
                    <td>${book.publisher.name}</td>
                    <td>${book.avg_rating}</td>
                    <td>
                        <a class="btn btn-danger" onclick="deleteBook(${book.id})">Delete</a>
                        <a class="btn btn-secondary" onclick="updateBook(${book.id})">Update</a>
                    </td>
                </tr>`;
    })
    listBook.innerHTML=htmls.join('');

}
function  getAllClothes(callback){
    fetch(apiClothes).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllClothes(books){
    var listBook=document.querySelector('#listClothes');
    var htmls=books.map(function(book){
        return `<tr>
                    <td><img src=${book.url_image} alt=“image” width='150' height='150'></td>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.size}</td>
                    <td>${book.price}</td>
                    <td>${book.stock}</td>
                    <td>${book.gender}</td>
                    <td>${book.publisher.name}</td>
                    <td>${book.avg_rating}</td>
                    <td>
                        <a class="btn btn-danger" onclick="deleteBook(${book.id})">Delete</a>
                        <a class="btn btn-secondary" onclick="updateBook(${book.id})">Update</a>
                    </td>
                </tr>`;
    })
    listBook.innerHTML=htmls.join('');

}

// function getCustomer(){
//     var name=`${customerName}`;
//     document.getElementById("username").innerHTML = name;
// }
// function  getOrderCount(callback){
//     fetch(apiLinkGetOrder+idCustomer).then(function (responce){
//         return responce.json();
//     }).then(callback).catch(function (err){
//         console.log(err)
//     });
// }
// function viewOrderCount(order){
//
//     var totalOrder=`(${order.total_item})`;
//     console.log(order.total_item);
//
//     document.getElementById("orderCount").innerHTML = totalOrder;
// }