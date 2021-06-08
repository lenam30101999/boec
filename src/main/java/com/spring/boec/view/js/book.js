var apiLink='http://localhost:8080/api/v1/books?textSearch';
function start(){
    getAllBook(viewAllBook)
}
start();
function  getAllBook(callback){
    fetch(apiLink).then(function (responce){
        return responce.json();
    }).then(callback);
}
function viewAllBook(books){
    var listBook=document.querySelector('#listBook');
    var htmls=books.map(function (book) {
        return '                <div class="product-item">\n' +
            '                    <div class="product-title">\n' +
            '                        <a href="#">${book.name}</a>\n' +
            '                        <div class="ratting">\n' +
            '                            <i class="fa fa-star"></i>\n' +
            '                            <i class="fa fa-star"></i>\n' +
            '                            <i class="fa fa-star"></i>\n' +
            '                            <i class="fa fa-star"></i>\n' +
            '                            <i class="fa fa-star"></i>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="product-image">\n' +
            '                        <a href="product-detail.html">\n' +
            '                            <img src="img/product-1.jpg" alt="Product Image">\n' +
            '                        </a>\n' +
            '                        <div class="product-action">\n' +
            '                            <a href="#"><i class="fa fa-cart-plus"></i></a>\n' +
            '                            <a href="#"><i class="fa fa-heart"></i></a>\n' +
            '                            <a href="#"><i class="fa fa-search"></i></a>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="product-price">\n' +
            '                        <h3><span>$</span>99</h3>\n' +
            '                        <a class="btn" href=""><i class="fa fa-shopping-cart"></i>Buy Now</a>\n' +
            '                    </div>\n' +
            '                </div>';
    });
    listBook.innerHTML=htmls.join('');

}