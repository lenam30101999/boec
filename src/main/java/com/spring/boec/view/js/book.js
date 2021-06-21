var apiLink='http://localhost:8080/api/v1/books?textSearch';
var apiAddFavorite='http://localhost:8080/api/v1/favorites';
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
    var listBook=document.querySelector('#listBook');
    var htmls=books.map(function(book){
        var result1=`<div class="col-lg-3" >
                <div class="product-item">
                    <div class="product-title">
                        <a onclick="viewBook(${book.id})">${book.name}</a>
                        <div class="ratting"> <a>${book.avg_rating}</a>`;

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
                            <img src="${book.url_image}" alt="Product Image" width='400' height='400'>
                        </a>
                        <div class="product-action">
                            <a href="cart.html"><i class="fa fa-cart-plus"></i></a>
                            <a onclick="addFavorite(${book.id})"><i class="fa fa-heart"></i></a>
                            
                        </div>
                    </div>
                    <div class="product-price">
                        <h3>${book.price}<span>VND</span></h3>
                        <a class="btn" onclick="viewBook(${book.id})"><i class="fa fa-shopping-cart"></i>View now</a>
                    </div>
                </div>
            </div>`;
        return result1+result2+result3;
    })
    listBook.innerHTML=htmls.join('');

        // slibar=======
    var listBook2=document.querySelector('#listSlibar');
    var htmls2=books.map(function(book){
        return `<div class="header-slider-item">
                        <img src="${book.url_image}" alt="Slider Image"  width='400' height='400'/>
                        <div class="header-slider-caption">
                            <p>${book.name}</p>
                            <a class="btn" onclick="viewBook(${book.id})"><i class="fa fa-shopping-cart"></i>View Now</a>
                        </div>
                    </div>`;
    })
    // listBook2.innerHTML=htmls2.join('');

}
function addFavorite(id){
    var formData={
       book : {
           id : id
       },
        customer_id : customerID
    }

    var option={
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    };
    fetch(apiAddFavorite,option).then(function (responce){
        responce.json()
    }).then(function (){
        
    });
}
function viewBook(id){
    localStorage.setItem('bookID',id);
    window.location.href = './book_detail.html';
}
