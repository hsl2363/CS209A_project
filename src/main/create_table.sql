drop table Questions;
drop table Answers;
drop table Comments;
drop table Users;
drop table Tags;
drop table tag;

create table Questions (
    accepted_answer_id int,
	answer_count int,
	-- public List<Answer> answers;
	comment_count int,
    -- public List<Comment> comments;
	creation_date int,
	down_vote_count int,
	favorite_count int,
	is_answered boolean,
	owner_id int,
	question_id int primary key,
	-- public List<String> tags;
	title varchar,
	up_vote_count int,
	view_count int
);

create table Answers(
	answer_id int primary key,
	comment_count int,
	creation_date int,
	down_vote_count int,
	is_accepted boolean,
	owner_id int,
	question_id int,
	up_vote_count int
);

create table Comments(
	comment_id int primary key,
	creation_date int,
	owner_id int,
	post_id int,
	reply_to_user boolean
);

create table Users(
	accepted_rate int,
	account_id int primary key,
	display_name varchar,
	reputation int,
	user_id int
);

create table Tags(
	id serial primary key,
	content varchar
);

create table tag(
	question_id int,
	tagid int,
	primary key(question_id,tagid)
);