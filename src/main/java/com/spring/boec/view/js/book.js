var apiLink='http://localhost:8080/api/v1/books?textSearch';
// var id=urlParams.get('id');
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
    var listBook=document.querySelector('#listBook');
    var htmls=books.map(function(book){
        return `<div class="col-lg-3" >
                <div class="product-item">
                    <div class="product-title">
                        <a onclick="viewBook(${book.id})">${book.name}</a>
                        <div class="ratting">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                    </div>
                    <div class="product-image">
                        <a href="product-detail.html">
                            <img src="img/product-1.jpg" alt="Product Image">
                        </a>
                        <div class="product-action">
                            <a href="#"><i class="fa fa-cart-plus"></i></a>
                            <a href="#"><i class="fa fa-heart"></i></a>
                            <a href="#"><i class="fa fa-search"></i></a>
                        </div>
                    </div>
                    <div class="product-price">
                        <h3><span>$</span>99</h3>
                        <a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy Now</a>
                    </div>
                </div>
            </div>`
    })
    listBook.innerHTML=htmls.join('');

}
function viewBook(id){
    localStorage.setItem('bookID',id);
    window.location.href = './book_detail.html';
}