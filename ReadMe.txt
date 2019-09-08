-Database MySql,AWS Amazon RDS
	Schema-> customer
		username       varchar(15) pk
		password       varchar(20)
		address        varchar(200)
		phone          varchar(10)
		reference_code varchar(12)
		Member_type    varchar(20)
		salary         int(11)
-Server AWS Amazon EC2 (Amazon Linux AMI 2018.03.0 (HVM), SSD Volume Type)
-Api authentication with JWT
	authentication-> http://ec2-13-58-136-208.us-east-2.compute.amazonaws.com:8080/AYCAP/authenticate
		method: post
		username="aycapapi"
		password="password"
		return token
	
	add new customer-> http://ec2-13-58-136-208.us-east-2.compute.amazonaws.com:8080/AYCAP/rest/add
		method: post
		required token
		example body 
			{
				"username": "famtest05",
				"password": "123456",
				"address": "bangkok",
				"phone": "0875689940",
				"salary": "15000"
			}
		return 
			{
				"username": "famtest05",
				"password": "123456",
				"address": "bangkok",
				"phone": "0875689940",
				"referenceCode": "201909089940",
				"memberType": "Silver",
				"salary": 15000
			}
		
	get all customers -> http://ec2-13-58-136-208.us-east-2.compute.amazonaws.com:8080/AYCAP/rest/all
		method: get
		required token
		return all customers
		
	get customer -> http://ec2-13-58-136-208.us-east-2.compute.amazonaws.com:8080/AYCAP/rest/{username}
		method: get
		required token
		return customer
		
-Technology
	spring boot
	hibernate
	JPA
	MySql
	JWT
	Mockito
	AWS
    
If have any question please contact me.

Theepop Chaitep
Theepop.c@gmail.com
0639686884
