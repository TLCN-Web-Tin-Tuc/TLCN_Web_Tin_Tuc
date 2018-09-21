-- drop table if exists tl_company;
-- drop table if exists tl_user;
-- drop table if exists tl_account;
-- drop table if exists tl_role;
-- drop table if exists tl_privileges;
-- drop table if exists tl_cat;
-- drop table if exists tl_workflow;
-- drop table if exists tl_status_object;
-- drop table if exists tl_items;
-- drop table if exists tl_item_versions;
-- drop table if exists tl_item_access;
-- drop table if exists tl_attach_file;
-- drop table if exists tl_feedback;
-- drop table if exists tl_contact;
-- drop table if exists tl_parameter_config;
-- drop table if exists tl_comments;
-- drop table if exists tl_response_comment;
-- drop table if exists tl_chat;
-- drop table if exists tl_chat_line;
-- drop table if exists tl_mechanism_type;
-- drop table if exists tl_mechanism;
-- drop table if exists tl_mechanism_report;
-- drop table if exists tl_streaming;
-- drop table if exists tl_recruiting;
-- drop table if exists tl_seeking;

-- Bảng danh mục nhieu cap - Category
create table tl_cat (
	  id int not null auto_increment
	, name nvarchar(50) not null				-- Tên danh mục: Job, Co khi,  Sach, De tai, Article, Company profile  
	, type nvarchar(50) not null                -- Job, Certificate,...
	, parent_id int                      		-- Danh muc cha. Refer to id. Null co nghia la ko co cha - root
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

create table tl_role (
      id int not null  auto_increment
    , name nvarchar(64) unique 
    , created datetime not null
    , createdby_username varchar(50) not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
    , primary key (id)
);

create table tl_account (
	  id int not null auto_increment
	, email varchar(255) not null unique	-- tên đăng nhập
	, password varchar(255) not null
	, confirmation_token varchar(255)		-- chứa token register.
	, enable bit(1)							-- bật sau khi verified từ mail
	, firstname varchar(128)
	, lastname varchar(128)
--	, type int 								-- 1: Hệ thống, 0: Ngoài hệ thống (FB, GMAIL, GITHUB, ...)
--	, user int 								-- Refer to tl_user
	, role int 
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
--	, foreign key (user) references tl_user(id)
	, foreign key (role) references tl_role(id)
);


create table tl_user (
      id int not null auto_increment
    , avatar longblob							  	-- avatar image of user
    , address nvarchar(512)
    , phone varchar(15)
    , gender int 									-- 1: Male, 0: Female
    , birthday datetime
    , description nvarchar(1000)					-- Mô tả cá nhân
	, created datetime not null
    , createdby_username varchar(50) not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50) 
    , primary key (id) 
	, foreign key (id) references tl_account(id)
    , foreign key (company) references tl_company(id)
	-- , foreign key (account_id) references tl_account(id)
	, foreign key (sub_job) references tl_cat(id)
);

/**
 * configuration for workflow
 */
--create table tl_workflow (
--       id int not null auto_increment
--    , cat int					  	  		  -- Danh mục 
--    , account int	
--	, created datetime not null
--  , createdby_username varchar(50) not null
--   , lastmodified datetime
--    , lastmodifiedby_username varchar(50)
--    , primary key (id)
--	, foreign key (account) references tl_account(id)
--	, foreign key (cat) references tl_cat(id)
--);

-- Bảng items
create table tl_items (
	  id int not null auto_increment
	, cat int not null		        				-- Tham khảo đến bảng tl_cat
	, file_name nvarchar(128)						-- File name ex: hinh1 
	, file_extension nvarchar(20)					-- File content ex: .jpg, .png, ...
  	, image longblob               			  		-- main image
  	, title nvarchar(512) 							-- Tiêu đề item
  	, short_desc nvarchar(1024)						-- Mô tả ngắn gọn về item
	, descr text          							-- nội dung đầy đủ item
--	, status int default 0			  	 			-- Refer to status_object.id
	, author int 		 			  				-- account tac gia
	, views int default 0		             		-- lượt xem. Đếm sẵn từ table item_access
	, likes int default 0	             			-- lượt like. 
	, downloads int default 0	         			-- lượt download
	, comments int default 0			         	-- lượt comment
	, status_object tinyint default 0				-- Trạng thái (1: đã duyệt, 0: chờ duyệt, 2: không duyệt)
	, created datetime 
	, createdby_username varchar(50) 
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (author) REFERENCES tl_account(id)
	, FOREIGN KEY (cat) REFERENCES tl_cat(id)
);

-- -- Bảng đánh giá item
-- Create table tl_review_items (
--       id int not null auto_increment
-- 	, item_id int not null
-- 	, note text
--     , created datetime not null              
--     , createdby_username varchar(50) not null
--     , lastmodified datetime
--     , lastmodifiedby_username varchar(50)
--     , primary key (id)
--     , foreign key (item_id) references tl_items(id)
-- );

/**
 * quản lý trạng thái của một đối tượng cụ thể
 */
--create table tl_status_object (
 --     id int not null auto_increment    
 --   , Item int not null unique     			 -- Refer to id of the entity. Ex: tl_items.id
--	, current_status varchar(32)             -- Trạng thái sau khi chuyển: Created | Approved | Rejected
--   , comment text                           -- comment when change previous status into current status
--    , created datetime not null              -- Dựa vào thời gian gần nhất để biết trạng thái hiện của đối tượng (current_status)
--    , createdby_username varchar(50) not null
--    , lastmodified datetime
--    , lastmodifiedby_username varchar(50)
--    , primary key (id)
 --   , foreign key (Item) references tl_items(id)
--);

-- Luu cac hanh dong cua nguoi dung
create table tl_item_access (
	  id int not null auto_increment
	, item int not null									-- Refer to tl_items
	, action int not null				   				-- 1: View; 2: Like: 3: Download; 4: Comments; 5: Share
	, finsh_action datetime								-- Thời gian kết thúc action
 	, created datetime not null         				-- Time performs action
	, createdby_username varchar(255) not null 			-- person performs action
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (item) references tl_items(id)
	, foreign key (createdby_username) references tl_account(email) 
);

-- Attached images or any files for item of the library.
create table tl_attach_file (
	  id int not null auto_increment	
	, item int											-- Tham khảo đến bảng items
	, file_name nvarchar(128)							-- File name ex: hinh1 
	, file_extension nvarchar(20)						-- File content ex: .jpg, .png, ...
	, file_content longblob								-- Nội dung của file đính kèm
	, link nvarchar(256)							    -- Link tới file (youtube,...). Link được dùng để thay thế file_name, file_extension, file_content
	, type_id int 										-- Tham khảo tới bảng tl_cat (type = image, video, media, ...)
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (item) references tl_items(id) on delete cascade
	, foreign key (type_id) references tl_cat(id)
);

-- Bảng góp ý và phản hồi góp ý
--create table tl_feedback (
--	  id int not null auto_increment
--	, name nvarchar(512) 
--	, email varchar(128)
--	, phone varchar(15)
--	, content text								-- Nội dung góp ý
--	, subject nvarchar(512)
--	, created datetime not null
--	, createdby_username varchar(50) not null
--	, lastmodified datetime
--	, lastmodifiedby_username varchar(50)
--	, primary key (id)
-- );

-- Bảng phản hồi góp ý
--create table tl_feedback_rep (
--	  id int not null auto_increment
--	, feedback int 										
--	, response_name varchar(255) 	 				
	--, content text 									
--	, created datetime not null
--	, createdby_username varchar(50) not null
--	, lastmodified datetime
--	, lastmodifiedby_username varchar(50)
--	, primary key (id)
--	, foreign key (feedback) references tl_feedback (id) on delete cascade
--	, foreign key (response_name) references tl_account(email)
--);

-- Bảng attached files for feedback
--create table tl_feedback_attach_file (
--	  id int not null auto_increment	
--	, feedback int										-- Tham khảo đến bảng tl_feedback
--	, response int 										-- Tham khảo đến bảng tl_feedback_rep
--	, file_name nvarchar(128)							-- File name ex: hinh1 
--	, file_extension nvarchar(20)						-- File content ex: .jpg, .png, ...
--	, image longblob 								    -- Nội dung của file đính kèm
--	, created datetime not null
--	, createdby_username varchar(50) not null
--	, lastmodified datetime
--	, lastmodifiedby_username varchar(50)
--	, primary key (id)
--	, foreign key (feedback) references tl_feedback(id) on delete cascade
--	, foreign key (response) references tl_feedback_rep(id) on delete cascade
--);


-- Bảng comments cho items
create table tl_comments (
	  id int not null auto_increment
	, user int
	, action int not null						-- Refer to tl_item_access
	, content text not null						-- nội dung comments
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (action) REFERENCES tl_item_access(id)
	, FOREIGN KEY (user) REFERENCES tl_user(id)
);

-- Bảng trả lời comment
create table tl_response_comment(
	  id int not null auto_increment
	, user int
	, comment int not null 				-- Refer to tl_comments
	, content text not null				-- Nội dụng trả lời
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id)
	, FOREIGN KEY (comment) REFERENCES tl_comments(id)
	, FOREIGN KEY (user) REFERENCES tl_user(id)
);

-- -- Bảng chat
-- create table tl_chat(
-- 	  id int not null auto_increment
-- 	, first_account varchar(255) not null			-- Người chat thứ nhất
-- 	, second_account varchar(255) not null			-- Người chat thứ hai
-- 	, created datetime not null
-- 	, createdby_username varchar(50) not null
-- 	, lastmodified datetime
-- 	, lastmodifiedby_username varchar(50)
-- 	, primary key (id)
-- 	, FOREIGN KEY (first_account) REFERENCES tl_account(email)
-- 	, FOREIGN KEY (second_account) REFERENCES tl_account(email)
-- );

-- -- Bảng chi tiết nội dung chat
-- create table tl_chat_line(
-- 	  id int not null auto_increment
-- 	, chat int not null						-- Tham khảo đến bảng chat
-- 	, chat_id int not null					-- Id của người chat
-- 	, line_content text not null			-- Nội dung dòng chat do người thực hiện
-- 	, created datetime not null
-- 	, createdby_username varchar(255) not null
-- 	, lastmodified datetime
-- 	, lastmodifiedby_username varchar(50)
-- 	, primary key (id)
-- 	, FOREIGN KEY (createdby_username) REFERENCES tl_account(email)
-- 	, FOREIGN KEY (chat) REFERENCES tl_chat(id)
-- );

-- Bảng báo cáo vi phạm
create table tl_report (
	  id int not null auto_increment
	, type nvarchar(128)
	, content nvarchar(1024)
	, created datetime not null
	, createdby_username varchar(50) not null
	, lastmodified datetime
	, lastmodifiedby_username varchar(50)
	, primary key (id) 
);


/**
 * xac dinh doi tuong phan quyen
 */
--create table permission_object (
--    id int not null auto_increment 
--	, cd varchar(64) not null unique     		-- object code. ex: 1_o | 2_c    
--    , name nvarchar(128) not null                 -- order | customer | ...
--    , create_link varchar(64)
--   , update_link varchar(64)
--    , delete_link varchar(64)
--    , approve_link varchar(64)
--    , createdby_username varchar(50) not null
--    , created datetime not null
--   , lastmodified datetime
--    , lastmodifiedby_username varchar(50)	
--   , primary key (id)
--);

/**
 * quan ly nhom quyen
 */
create table permission (					
      id int not null auto_increment  
    , permission_name varchar(128) not null         -- alias name: nhóm quyền a, nhóm quyền b
	, cd varchar(64) not null	 	
    , cat int not null    
    , create1 boolean                 -- create    
    , update1 boolean                 -- update
    , delete1 boolean                 -- delete   
	, approve boolean                -- approve
	, createdby_username varchar(50) not null
    , created datetime not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
    , foreign key (permission_object_cd) references permission_object(cd)
    , primary key (id)
);

/**
 * quan ly phan quyen cho chuc danh | phong ban | nguoi dung
 */
create table tl_assign_permission (
      id int not null auto_increment
    , type int not null					         -- 0: role; 1: account
    , identifier varchar(64)  					 -- refer to id of department | role | account 							 
    , permission int not null    				 -- refer to permission
    , createdby_username varchar(50) not null
    , created datetime not null
    , lastmodified datetime
    , lastmodifiedby_username varchar(50)
	, primary key (id)
	, foreign key (cat) references tl_cat(id)
	, foreign key (permission) references permission(id)
);
