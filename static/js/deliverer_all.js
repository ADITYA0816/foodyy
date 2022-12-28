const acceptOrder = async (id) => {
	const response = await fetch('accept_order_delivery.do?order_id='+id);
	return response.text();
};

function acceptOrderDelivery(clicked_id)
{
    var id = clicked_id.substring(3);
    acceptOrder(id).then((data)=>{
		console.log(data);
		document.getElementById('card_'+id).classList.add('d-none');
		
	}).catch((error)=>{
		console.log(error);
	});
}




const orderDelivered = async (id) => {
	const response = await fetch('order_delivered.do?order_id='+id);
	return response.text();
};

function delivered(clicked_id)
{
    var id = clicked_id.substring(3);
    orderDelivered(id).then((data)=>{
		console.log(data);
		document.getElementById('card_'+id).classList.add('d-none');
		
	}).catch((error)=>{
		console.log(error);
	});
}
