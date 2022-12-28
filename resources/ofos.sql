create DATABASE ofos;

use ofos;

create TABLE status
(
    status_id INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(20) NOT NULL   
);

INSERT INTO status (status) VALUES ('Inactive') , ('Profile Incomplete') , ('Active'), ('Blocked'), ('Ended');

create TABLE cities
(
	city_id INT AUTO_INCREMENT PRIMARY KEY,
	city VARCHAR(35) NOT NULL
);

INSERT INTO cities (city) VALUES ('Jabalpur') , ('Bhopal') , ('Indore'), ('Not Selected');


create TABLE order_status
(
	order_status_id	  INT AUTO_INCREMENT PRIMARY KEY,
	status VARCHAR(20) NOT NULL
);

create TABLE deliverers
(
	deliverer_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,	
	email VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	status_id int NOT NULL DEFAULT 1,
	constraint FK_deliverers_status FOREIGN KEY (status_id) REFERENCES status (status_id) 
);

create TABLE food_types
(
	food_type_id INT AUTO_INCREMENT PRIMARY KEY,
	food_type VARCHAR(20) NOT NULL,
	veg BOOLEAN NOT NULL DEFAULT 1,
	desciption VARCHAR(100) NOT NULL
);
insert into food_types(food_type,veg,desciption) value ('Not Selected',1,'null');
insert into food_types(food_type,veg,desciption) value ('Punjabi',1,'Good Food');
insert into food_types(food_type,veg,desciption) value ('Italian',1,'Great Spicy Food');
insert into food_types(food_type,veg,desciption) value ('Chinese',1,'Great Taste');

create TABLE restaurants
(
	restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL 	UNIQUE,
	password VARCHAR(100) NOT NULL,
	pic_path VARCHAR(150),
	address VARCHAR(100),
	city_id INT DEFAULT 4,
	contact VARCHAR(10) NOT NULL UNIQUE,
	status_id int NOT NULL DEFAULT 1,
	activation_code VARCHAR(100),
	constraint FK_restaurants_cities FOREIGN KEY (city_id) REFERENCES cities (city_id),
	constraint FK_restaurants_status FOREIGN KEY (status_id) REFERENCES status (status_id) 
);


create TABLE users
(
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	user_type BOOLEAN NOT NULL DEFAULT 0,
	name VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	pic_path VARCHAR(150),
	address VARCHAR(100),
	city_id INT DEFAULT 4,
	contact VARCHAR(10) NOT NULL UNIQUE,
	status_id int NOT NULL DEFAULT 1,
	activation_code VARCHAR(100),
	constraint FK_users_cities FOREIGN KEY (city_id) REFERENCES cities (city_id),
	constraint FK_users_status FOREIGN KEY (status_id) REFERENCES status (status_id) 	
);


create TABLE foods
(
	food_id INT AUTO_INCREMENT PRIMARY KEY,
	restaurant_id INT,
	name VARCHAR(15) NOT NULL,
	price_per_unit INT NOT NULL,
	pic_path VARCHAR(150),
	food_type_id INT NOT NULL DEFAULT 1,
	status BOOLEAN NOT NULL DEFAULT 0,
	ingredients VARCHAR(100),
	star_rank INT NOT NULL DEFAULT 0,
	star_rank_count INT NOT NULL DEFAULT 0,
	constraint FK_foods_restaurants FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id),
	constraint FK_foods_food_types FOREIGN KEY (food_type_id) REFERENCES food_types (food_type_id) 	
);

 





 