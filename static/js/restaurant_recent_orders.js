const acceptOrder = async (id) => {
	const response = await fetch('accept_order.do?order_id='+id);
	return response.text();
};

function accept(clicked_id)
{
    var id = clicked_id.substring(3);
    acceptOrder(id).then((data)=>{
		console.log(data);
		var accept_order_food = document.getElementById('id_card_'+id)
		accept_order_food.classList.add('d-none');
	}).catch((error)=>{
		console.log(error);
	});
}


const rejectOrder = async (id) => {
	const response = await fetch('reject_order.do?order_id='+id);
	return response.text();
};

function reject(clicked_id)
{
    var id = clicked_id.substring(3);
    rejectOrder(id).then((data)=>{
		console.log(data);
		var rejectOrder = document.getElementById('id_card_'+id)
		rejectOrder.classList.add('d-none');
	}).catch((error)=>{
		console.log(error);
	});
}