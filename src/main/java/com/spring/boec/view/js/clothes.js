var apiLink='http://localhost:8080/api/v1/clothes?textSearch';
// var id=urlParams.get('id');
var customerName=sessionStorage.getItem("userName");
var customerID=sessionStorage.getItem("userID");
function start(){

    getAllBook(viewAllBook)
}
start();
function  getAllBook(callback){
    fetch(apiLink).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllBook(books){
    var listBook=document.querySelector('#listClothes');
    var htmls=books.map(function(book){
        var result1=`<div class="col-lg-3" >
                <div class="product-item">
                    <div class="product-title">
                        <a onclick="viewBook(${book.id})">${book.name}</a>
                        <div class="ratting"> ${book.avg_rating}`;

        var result2=``;
        var avg=parseInt(book.avg_rating);

        for (var i=0;i<avg;i++){
            result2+=`<i class="fa fa-star"></i>`;
        }
        for (var i=avg;i<5;i++){
            result2+=`<i class="far fa-star"></i>`;
        }

        var result3=`</div></div>
                    <div class="product-image">
                        <a href="product-detail.html">
                            <img src="${book.url_image}" alt="Product Image">
                        </a>
                        <div class="product-action">
                            <a href="#"><i class="fa fa-cart-plus"></i></a>
                            <a href="#"><i class="fa fa-heart"></i></a>
                            <a href="#"><i class="fa fa-search"></i></a>
                        </div>
                    </div>
                    <div class="product-price">
                        <h3>${book.price}<span>VND</span></h3>
                        <a class="btn" onclick="viewClothes(${book.id})"><i class="fa fa-shopping-cart"></i>Mua ngay</a>
                    </div>
                </div>
            </div>`;
        return result1+result2+result3;
    })
    listBook.innerHTML=htmls.join('');

}
function viewClothes(id){
    localStorage.setItem('electroID',id);
    window.location.href = './book_detail.html';
}
