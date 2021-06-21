
var apiState='http://localhost:8080/api/v1/orders';
var bookID=localStorage.getItem("bookID");
function start(){
    getAllOrder(viewAllOrder);
}
start();

function  getAllOrder(callback){
    fetch(apiState).then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewAllOrder(books){
    var listBook=document.querySelector('#listOrder');
    var htmls=books.map(function(order){
        return `<tr>
                        
                    <td>${order.id}</td>
                    <td>${order.date}</td>
                    <td>${order.total_item}</td>
                    <td>${order.payment.total_money}</td>
                    <td>${order.payment.is_paid}</td>
                    <td>${order.state}</td>
                    <td>
                        <a class="btn btn-danger" onclick="approved(${order.id})">APPROVED</a>
                        <a class="btn btn-secondary" onclick="rejected(${order.id})">REJECT</a>
                    </td>
                </tr>`;
    })

    listBook.innerHTML=htmls.join('');

}
function approved(id){
    var formData={
        id : id,
        state : "APPROVED"
    }

    var option={
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    };
    fetch(apiState,option).then(function (responce){
        responce.json()
    }).then(function (){
        getAllOrder(viewAllOrder);
    });
}
function rejected(id){
    var formData={
        id : id,
        state : "REJECTED"
    }

    var option={
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    };
    fetch(apiState,option).then(function (responce){
        responce.json()
    }).then(function (){
        getAllOrder(viewAllOrder);
    });
}