var bookId=localStorage.getItem('bookID');
var apiLink='http://localhost:8080/api/v1/books/get-book/';
var apiLinkCart='http://localhost:8080/api/v1/order-items';
var apiLinkGetOrder='http://localhost:8080/api/v1/orders?customer_id='
var apiRating='http://localhost:8080/api/v1/ratings'
var idCustomer=sessionStorage.getItem("userID");
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
                                    <h4>Page: ${book.page_count}</h4>
                                
                                </div>
                                <div class="p-color">
                                    <h4>Author:${book.author.name}</h4>
                                    
                                </div>
                                <div class="p-size">
                                    <h4>Stock: ${book.stock}</h4>
                                
                                </div>
                                <div class="action">
                                    <a class="btn" onclick="addAction(${book.id})"><i class="fa fa-shopping-cart"></i>Add to Cart</a>
<!--                                    <a class="btn" href="#"><i class="fa fa-shopping-bag"></i>Mua ngay</a>-->
                                </div>
                            </div>
                        </div>`;
    document.getElementById("bookView").innerHTML = bookView;


    //book descriptin

    var description=`Publisher: ${book.publisher.name}, adrress: ${book.publisher.address}
    <br>
        Author: ${book.author.name}`;
    document.getElementById("description").innerHTML = description;
    // view reviewer=============

    var listReview=document.querySelector('#listReview');
    var htmls=book.ratings.map(function(review){
        var ok=`<div class="reviewer"> ${review.customer.fullName.firstName} ${review.customer.fullName.middleName} 
                                        ${review.customer.fullName.lastName}- 
                        <span>19 June 2021</span></div>
                                    <div class="ratting" id="star-${review.id}"><i class="fa fa-star-half-empty"></i> `;
        var ok1=``;
        for (var i=0;i<review.rate;i++){
            ok1+=`<i class="fa fa-star"></i>`;
        }
        for (var i=review.rate;i<5;i++){
            ok1+=`<i class="far fa-star"></i>`;
        }

        var ok2=` </div>
                  <p>${review.content}</p>`


        return ok+ok1+ok2;
    })
    listReview.innerHTML=htmls.join('');

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
                start();
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
