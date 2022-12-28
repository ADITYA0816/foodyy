const processOrder = async (id) => {
	const response = await fetch('process_order.do?order_id='+id);
	return response.text();
};

function processing(clicked_id)
{
    var id = clicked_id.substring(3);
    processOrder(id).then((data)=>{
		console.log(data);
		document.getElementById('id_card_'+id).classList.add('d-none');
		
	}).catch((error)=>{
		console.log(error);
	});
}


const processedOrder = async (id) => {
	console.log(id);
	const response = await fetch('processed_order.do?order_id='+id);
	return response.text();
};

function processed(clicked_id)
{
    var id = clicked_id.substring(3);
	console.log(id);
    processedOrder(id).then((data)=>{
		console.log(data);
		document.getElementById('id_card_'+id).classList.add('d-none');
	}).catch((error)=>{
		console.log(error);
	});
}



const deliverOrder = async (id) => {
	console.log(id);
	const response = await fetch('deliver_order.do?order_id='+id);
	return response.text();
};

function deliver(clicked_id)
{
    var id = clicked_id.substring(3);
	console.log(id);
	deliverOrder(id).then((data)=>{
		console.log(data);
		document.getElementById('id_card_'+id).classList.add('d-none');
	}).catch((error)=>{
		console.log(error);
	});
}
