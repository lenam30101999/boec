var bookId=localStorage.getItem('bookID');
var apiLink='http://localhost:8080/api/v1/books/get-book/';
var apiLinkCart='http://localhost:8080/api/v1/order-items';
var apiLinkGetOrder='http://localhost:8080/api/v1/orders?customer_id='
var apiRating='http://localhost:8080/api/v1/ratings'
var idCustomer=1;
var kt=false;
var rate=0;
const ratingStars = [...document.getElementsByClassName("rating__star")];

function executeRating(stars) {
    const starClassActive = "rating__star fas fa-star";
    const starClassInactive = "rating__star far fa-star";
    const starsLength = stars.length;
    let i;
    stars.map((star) => {
        star.onclick = () => {
            i = stars.indexOf(star);
            rate=i+1;

            if (star.className===starClassInactive) {
                for (i; i >= 0; --i) stars[i].className = starClassActive;
            } else {
                for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
            }
        };
    });
}
executeRating(ratingStars);

function start(){
    console.log(bookId)
    getBook(viewBook)
    // addAction()
    getOrderCount(viewOrderCount)
    addReview()
}
start();
function  getOrderCount(callback){
    fetch(apiLinkGetOrder+idCustomer).then(function (responce){
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
function  getBook(callback){
    fetch(apiLink+bookId).then(function (responce){
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
                                <div class="slider-nav-img"><img src="${book.url_image}" alt="Product Image"></div>
                               
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
                                    <p>${book.price}<span></span>VND</p>
                                </div>
                                <div class="quantity">
                                    <h4>Quantity:</h4>
                                    <div class="qty">
                                        <button class="btn-minus"><i class="fa fa-minus"></i></button>
                                        <input type="text" value="1" name="quantity">
                                        <button class="btn-plus"><i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="p-size">
                                    <h4>So trang: ${book.page_Count}</h4>
                                
                                </div>
                                <div class="p-color">
                                    <h4>Tac gia:${book.author.name}</h4>
                                    
                                </div>
                                <div class="p-size">
                                    <h4>Con lai: ${book.stock}</h4>
                                
                                </div>
                                <div class="action">
                                    <a class="btn" onclick="addAction(${book.id})"><i class="fa fa-shopping-cart"></i>Them vao gio hang</a>
                                    <a class="btn" href="#"><i class="fa fa-shopping-bag"></i>Mua ngay</a>
                                </div>
                            </div>
                        </div>`;
    document.getElementById("bookView").innerHTML = bookView;

    //view review-----------------

    var listReview=document.querySelector('#listReview');
    var htmls=book.ratings.map(function(review){
        return `<div class="reviewer"> ${review.customer.id} - <span>19 June 2021</span></div>
                                    <div class="ratting" id="star-${review.id}">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        
                                    </div>
                                    <p>${review.content}</p>`
    })
    listReview.innerHTML=htmls.join('');


    var reviewSize=`${book.ratings.size()}`;
    document.getElementById("danhGia").innerHTML = reviewSize;

    window.onload=function (){
        var viewStar=`<i class="fa fa-star"></i>`;
        console.log(book.ratings.id)
        for(var i = 0; i < rating.length; i++){
            document.getElementById("star-"+rating.id).innerHTML = viewStar;
        }

    }
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
    var quantity=document.querySelector('input[name="quantity"]').value;
        var formData={
            book: {id : bookID},
            customer_id : idCustomer,
            quantity : quantity
        }
        addToCart(formData);
}

function addReview(){
    var btnAdd=document.querySelector('#btnAddReview');
    if (true){
        btnAdd.onclick=function (){
            var content=document.getElementById('reviewContent').value;
            var formData={
                rate : rate,
                content : content,
                book : {id : bookId},
                customer : {id : idCustomer}
            }

            var option={
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            };
            fetch(apiRating,option).then(function (responce){
                responce.json()
            }).then(function (){
                getBook(viewBook);
                kt=true;
            });
        }
    }


}
function addAction2(){
    // window.onload = function(){
    //     // your code
    // };
    var btnAdd=document.querySelector('#btnAdd');
    btnAdd.onclick=function (){
        var formData={
            book: {id : "1"},
            customer_id : 1,
            quantity : "1"
        }
        addToCart(formData);
    }


}