const order_food = document.getElementById('order_food');
const success_box = document.getElementById('success_box');
const order_card = document.getElementById('order_card');


const orderFood = async () => {
	const response = await fetch('order_food.do');
	return response.text();
};

order_food.addEventListener('click', ()=>{
	orderFood().then((data)=>{
		console.log(data);
		success_box.classList.remove('d-none');
		order_card.classList.add('d-none');
	}).catch((error)=>{
		console.log(error);
	});
});