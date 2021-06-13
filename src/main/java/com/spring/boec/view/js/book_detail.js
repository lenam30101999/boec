var id=localStorage.getItem('bookID');
var apiLink='http://localhost:8080/api/v1/books/get-book/';
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
function viewBook(book){
    // var bookView=document.querySelector('bookView');

    var bookView=`<div class="col-md-5">
<!--                            <div class="product-slider-single normal-slider">-->
<!--                                <img src="img/product-1.jpg" alt="Product Image">-->
<!--                               -->
<!--                            </div>-->
                            <div class="product-slider-single-nav normal-slider">
                                <div class="slider-nav-img"><img src="img/product-1.jpg" alt="Product Image"></div>
                               
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="product-content">
                                <div class="title"><h2>${book.name}</h2></div>
                                <div class="ratting">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </div>
                                <div class="price">
                                    <h4>Gia:</h4>
                                    <p>$99 <span>$149</span></p>
                                </div>
                                <div class="quantity">
                                    <h4>Quantity:</h4>
                                    <div class="qty">
                                        <button class="btn-minus"><i class="fa fa-minus"></i></button>
                                        <input type="text" value="1">
                                        <button class="btn-plus"><i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="p-size">
                                    <h4>So trang:</h4>
                                    <h3>${book.pageCount}</h3>
                                </div>
                                <div class="p-color">
                                    <h4>Tac gia:</h4> <h3>${book.author.name}</h3>
                                    
                                </div>
                                <div class="action">
                                    <a class="btn" href="#"><i class="fa fa-shopping-cart"></i>Them vao gio hang</a>
                                    <a class="btn" href="#"><i class="fa fa-shopping-bag"></i>Mua ngay</a>
                                </div>
                            </div>
                        </div>`;
    document.getElementById("bookView").innerHTML = bookView;


}