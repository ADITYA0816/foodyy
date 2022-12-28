const inputs = document.querySelectorAll('input');

const form = document.forms[0];

const result = {
	email: false,
	password: false
};

//-------------------------------------Validation-------------------------------------
//matching field with agex patterns
const patterns = {
	email: /^([a-zA-Z][a-zA-Z\d-_\.]*)@([a-zA-Z\d-_]{2,})\.([a-zA-Z]{2,5})(\.[a-zA-Z]{2,5})?$/,
	password: /^[a-zA-Z\d_-]{6,16}$/
};

form.addEventListener('submit', function(event){
	if(result.email && result.password){
		return true;
	}else{
		event.preventDefault();
		return false;
	}
});

const validate = (field, pattern) => {

	let help = document.querySelector('#'+field.attributes.name.value+'_help');
	let error = document.querySelector('#'+field.attributes.name.value+'_error');
	
	if(field.attributes.name.value==='email'){
		error.innerHTML = 'Invalid Email. Please enter valid Email.';
	}
	
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

//-------------------------------------Show Password-------------------------------------
//clicking on eye button makes password visible
const passwordField = document.querySelector('#id_password');
const viewPassword = document.querySelector('#view_password');

let passVisible = false;

viewPassword.addEventListener('click', function(event){
	if(passVisible){
		passwordField.attributes.type.value = 'password';
		passVisible = false;
	}else{	
		passwordField.attributes.type.value = 'text';
		passVisible = true;
	}
});

