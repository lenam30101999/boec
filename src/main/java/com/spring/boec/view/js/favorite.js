var apiLink='http://localhost:8080/api/v1/favorites?customer_id=';
var apiDelete='http://localhost:8080/api/v1/favorites/';
var apiLinkCart='http://localhost:8080/api/v1/order-items';
// var id=urlParams.get('id');
var customerName=sessionStorage.getItem("userName");
var customerID=sessionStorage.getItem("userID");
function start(){

    getAllBook(viewAllBook)
}
start();
function  getAllBook(callback){
    fetch(apiLink+customerID).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllBook(books){
    var listBook=document.querySelector('#listFavorite');
    var htmls=books.map(function(favorite){
        return `<tr>
                                <td>
                                    <div class="img" >
                                        <a onclick="viewBook(${favorite.book.id})"><img src="${favorite.book.url_image}" alt="Image"></a>
                                        <p onclick="viewBook(${favorite.book.id})">${favorite.book.name}</p>
                                    </div>
                                </td>
                                <td>${favorite.book.price} VND</td>
<!--                                <td>-->
<!--                                    <div class="qty">-->
<!--                                        <button class="btn-minus"><i class="fa fa-minus"></i></button>-->
<!--                                        <input type="text" value="1">-->
<!--                                        <button class="btn-plus"><i class="fa fa-plus"></i></button>-->
<!--                                    </div>-->
<!--                                </td>-->
                                <td><button onclick="addAction(${favorite.book.id})" class="btn-cart">Add to Cart</button></td>
                                <td><button><i  onclick="deleteFavorite(${favorite.id})" class="fa fa-trash"></i></button></td>
                            </tr>`;
    })
    listBook.innerHTML=htmls.join('');

}

function addToCart(book){
    var option={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)
    };
    fetch(apiLinkCart,option).then(function (responce){
        responce.json()
    }).then(function (){
        getOrderCount(viewOrderCount)
    });
}
function addAction(bookID){
    var quantity=1;
    var formData={
        book: {id : bookID},
        customer_id : customerID,
        quantity : quantity
    }
    addToCart(formData);
}
function deleteFavorite(id){
    var option={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },

    };
    fetch(apiDelete+id,option).then(function (responce){
        responce.json()
    }).then(function (){
        getAllBook(viewAllBook);
        getFavoriteCount(viewFavoriteCount);
    });
}
function viewBook(id){
    localStorage.setItem('bookID',id);
    window.location.href = './book_detail.html';
}