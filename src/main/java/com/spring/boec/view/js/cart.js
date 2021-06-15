var apiLinkGetOrder='http://localhost:8080/api/v1/orders?customer_id='
var apiDelete='http://localhost:8080/api/v1/order-items/'
var apiState='http://localhost:8080/api/v1/orders'
var apiPayment='http://localhost:8080/api/v1/payments/'
var idOrder
var state
function start(){
    getOrder(viewOrder)
    // addAction()
    addState()
}
start();


function  getOrder(callback){
    fetch(apiLinkGetOrder+"1").then(function (responce){
        return responce.json();
    }).then(callback).catch(function (err){
        console.log(err)
    });
}
function viewOrder(order){
    // var bookView=document.querySelector('bookView');
    idOrder=order.id;
    state=order.state;

    var listOrder=document.querySelector('#listOrder');

    var htmls=order.order_items.map(function(orderItem){
        return `<tr>
                                            <td>
                                                <div class="img">
                                                    <a href="#"><img src="${orderItem.book.url_image}" alt="Image"></a>
                                                    <p>${orderItem.book.name}</p>
                                                </div>
                                            </td>
                                            <td>${orderItem.book.price} VND</td>
                                            <td>
                                                <div class="qty">
                                                    <button class="btn-minus"><i class="fa fa-minus"></i></button>
                                                    <input type="text" value="${orderItem.quantity}">
                                                    <button class="btn-plus"><i class="fa fa-plus"></i></button>
                                                </div>
                                            </td>
                                          
                                            <td><button><i class="fa fa-trash" onclick="deleteOrderItem(${orderItem.id})"></i></button></td>
                                        </tr>`
    })
    listOrder.innerHTML=htmls.join('');

    var bookView=`${order.payment.total_money}`;
    document.getElementById("totalPrice").innerHTML = bookView;
    document.getElementById("totalPrice2").innerHTML = bookView;
    if (order.state!=null && order.state!=""){
        var stateView=`Trạng thái: ${order.state}`;
        document.getElementById("stateView").innerHTML = stateView;
    }


}

function deleteOrderItem(id){
    var option={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },

    };
    fetch(apiDelete+id,option).then(function (responce){
        responce.json()
    }).then(function (){
        getOrder(viewOrder);
    });
}
function addState(){
    var btnAdd=document.querySelector('#btnState');
    btnAdd.onclick=function (){
        if (state!="APPROVED"){
            var formData={
                id : idOrder,
                state : "PENDING"
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
                getOrder(viewOrder);
            });
        }else{
            if(confirm('Xác nhận thanh toán?')){
                var option={
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                };
                fetch(apiPayment+idOrder,option).then(function (responce){
                    responce.json()
                }).then(function (){
                    alert('Thanh toán thành công!');
                    window.location.href = './customer_view.html';
                });
            }
        }

    }


}