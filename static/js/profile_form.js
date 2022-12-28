const id_contact = document.querySelector('#id_contact');
const id_savedetails = document.querySelector('#id_savedetails');
const id_sendotp = document.querySelector('#id_sendotp');
const otp_box = document.querySelector('#otp_box');
const id_otp = document.querySelector('#id_otp');
const check_otp = document.querySelector('#check_otp');
const profile_form = document.querySelector('#profile_form');

const inputs = document.querySelectorAll('input');

const form = document.forms[0];

const result = {
	name: false,
	address: false,
	contact: false
};


profile_form.addEventListener('submit', (event) => {
	id_contact.disabled = false;
	
	return true;
});

id_savedetails.style.display = 'none'; 
otp_box.style.display = 'none'; 



//-------------------------------------Validation-------------------------------------
//matching field with agex patterns
const patterns = {
	name: /^[a-zA-Z ]{5,20}$/,
	contact: /^[5-9][0-9]{9}$/	
};

form.addEventListener('submit', function(event){
	if(result.name && result.contact){
		return true;
	}else{
		event.preventDefault();
		return false;
	}
});

const validate = (field, pattern) => {

	let help = document.querySelector('#'+field.attributes.name.value+'_help');
	let error = document.querySelector('#'+field.attributes.name.value+'_error');
	
	if(pattern.test(field.value)){
		field.classList.replace('fail', 'success');
		result[field.attributes.name.value] = true;
		help.classList.replace('hide', 'show');
		error.classList.replace('show', 'hide');		
	}else{
		if(field.classList.contains('success')){
			field.classList.replace('success', 'fail');
			help.classList.replace('show', 'hide');
			error.classList.replace('hide', 'show');
		}else{
			field.classList.add('fail');
			help.classList.add('hide');
			error.classList.replace('hide', 'show');
		}
		result[field.attributes.name.value] = false;
	}
}

inputs.forEach((input) => {
	input.addEventListener('keyup', (event) => {		
		let pattern = patterns[event.target.attributes.name.value];
		let field = event.target;
		
		validate(field, pattern);
	});
	
	input.addEventListener('blur', (event) => {		
		let pattern = patterns[event.target.attributes.name.value];
		let field = event.target;
		
		validate(field, pattern);
	});
});


//##############################################
const checkOTP = async () => {
	const response = await fetch('check_otp.do?otp='+id_otp.value);
	return response.text();
};

check_otp.addEventListener('click', ()=>{
	checkOTP().then((data)=>{
		console.log(data);
		if(data=='signin'){
			window.location = 'restaurant_signin.do';
		}else if(data=='true'){
			id_savedetails.style.display = 'inline';
			otp_box.style.display = 'none'; 			
		}else{
			
		}
	}).catch((error)=>{
		console.log(error);
	});
});


//##############################################
const sendOTP = async () => {
	response = await fetch('send_otp.do?contact='+id_contact.value);
	return response.text();
};

id_sendotp.addEventListener('click', ()=>{
	otp_box.style.display = 'block'; 
	id_sendotp.style.display = 'none';
	id_contact.disabled = true;
	
	sendOTP().then((data)=>{
		console.log(data);
	}).catch((err)=>{
		console.log(err);
	});
});