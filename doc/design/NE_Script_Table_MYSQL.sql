create table ne_cat (
	  id int not null auto_increment
	, name nvarchar(50) not null			
	, type nvarchar(50) not null               
	, parent_id int                 
	, check_cat boolean default 1
	, file_name nvarchar(128)
	, file_extension varchar(20)
	, image longblob
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key(id)
);

create table ne_role (
      id int not null  auto_increment
    , name nvarchar(64) unique 
    , created datetime not null
    , createdby_username varchar(50) not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
    , primary key (id)
);

create table ne_account (
	  id int not null auto_increment
	, email varchar(255) not null unique	
	, password varchar(255) not null
	, confirmation_token varchar(255)		
	, enable bit(1)							
	, firstname varchar(128)
	, lastname varchar(128)
--	, type int 								
--	, user int 								
	, role int 
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
--	, foreign key (user) references ne_user(id)
	, foreign key (role) references ne_role(id)
);


create table ne_user (
      id int not null auto_increment
    , avatar longblob							 
    , address nvarchar(512)
    , phone varchar(15)
    , gender int 									
    , birthday datetime
    , description nvarchar(1000)					
	, created datetime not null
    , createdby_username varchar(50) not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50) 
    , primary key (id) 
	, foreign key (id) references ne_account(id)
	, foreign key (account_id) references ne_account(id)
	, foreign key (sub_job) references ne_cat(id)
);

-- Bảng items
create table ne_items (
	  id int not null auto_increment
	, cat int not null		        				
	, file_name nvarchar(128)						
	, file_extension nvarchar(20)					
  	, image longblob               			  		
  	, title nvarchar(512) 							
  	, short_desc nvarchar(1024)						
	, descr text          							
--	, status int default 0			  	 			
	, author int 		 			  				
	, views int default 0		             		
	, likes int default 0	             			
	, downloads int default 0	         			
	, comments int default 0			         	
	, status_object tinyint default 0
	, created datetime 
	, createdby_username varchar(50) 
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (author) REFERENCES ne_account(id)
	, FOREIGN KEY (cat) REFERENCES ne_cat(id)
);

create table ne_item_access (
	  id int not null auto_increment
	, item int not null									
	, action int not null				   				
	, finsh_action datetime								
 	, created datetime not null         				
	, createdby_username varchar(255) not null 
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (item) references ne_items(id)
	, foreign key (createdby_username) references ne_account(email) 
);

create table ne_attach_file (
	  id int not null auto_increment	
	, item int											
	, file_name nvarchar(128)							
	, file_extension nvarchar(20)						
	, file_content longblob								
	, link nvarchar(256)  
	, type_id int
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (item) references ne_items(id) on delete cascade
	, foreign key (type_id) references ne_cat(id)
);

create table ne_comments (
	  id int not null auto_increment
	, user int
	, action int not null						
	, content text not null						
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (action) REFERENCES ne_item_access(id)
	, FOREIGN KEY (user) REFERENCES ne_user(id)
);

create table ne_response_comment(
	  id int not null auto_increment
	, user int
	, comment int not null 				
	, content text not null			
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (comment) REFERENCES ne_comments(id)
	, FOREIGN KEY (user) REFERENCES ne_user(id)
);

create table ne_report (
	  id int not null auto_increment
	, type nvarchar(128)
	, content nvarchar(1024)
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id) 
);

create table permission (					
      id int not null auto_increment  
    , permission_name varchar(128) not null         
	, cd varchar(64) not null	 	
    , cat int not null    
    , create_per boolean
    , update_per boolean
    , delete_per boolean
	, approve_per boolean
	, createdby_username varchar(50) not null
    , created datetime not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
    , foreign key (permission_object_cd) references permission_object(cd)
    , primary key (id)
);

create table ne_assign_permission (
      id int not null auto_increment
    , type int not null					         
    , identifier varchar(64)  					 						 
    , permission int not null    				 
    , createdby_username varchar(50) not null
    , created datetime not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (cat) references ne_cat(id)
	, foreign key (permission) references permission(id)
);
