drop table if exists metrics;
drop table if exists metricCategories;
drop table if exists subscriptions;
drop table if exists subscriptionTypes;
drop table if exists patientSymptoms;
drop table if exists diagnosedBy;
drop table if exists medicalHistories;
drop table if exists users;
drop table if exists bloodTypes;
drop table if exists colonies;
drop table if exists gender;
drop table if exists doctors;

/*---------------- reminder: (on webpage) ----------------
- JDBC URL should be set to jdbc:h2:./db-05 (not default)
- you should remove sa
--------------------------------------------------------*/

-- "helper tables"
create table bloodTypes (
	id identity not null primary key,
	bloodType varchar(2),
	resusFactor varchar(1)
);

create table colonies (
	id identity not null primary key,
	name varchar(100) not null unique
);

create table subscriptionTypes (
	id identity not null primary key,
	subscriptionName varchar(150) not null unique
);

create table metricCategories (
	id identity not null primary key,
	metricName varchar(250) not null unique,
	unit varchar(25) not null
);

create table gender (
    symbol varchar(1) not null primary key
);

-- user data
CREATE TABLE users (
	id bigint not null primary key, -- TODO: ask team members about PK
	
	-- basic personal info
	fname varchar(100) not null,
	lname varchar(100) not null,
	birthdate date not null,
	colonyId int,
	gender varchar(1),
	
	-- medical info
	bloodTypeId int,
	
	-- foreign keys
	constraint fk_bloodTypeId foreign key (bloodTypeId) references bloodTypes(id),
	constraint fk_colonyId foreign key (colonyId) references colonies(id),
    constraint fk_gender foreign key (gender) references gender(symbol)
);

create table subscriptions (
	userId int not null,
	subscriptionTypeId int not null,
	subscriptionDurationInDays int not null,
	primary key (userId, subscriptionTypeId),
	constraint fk_userId1 foreign key (userId) references users(id),
	constraint fk_subscriptionTypeId foreign key (subscriptionTypeId) references subscriptionTypes(id)
);

-- user metrics
create table metrics (
	userId int not null,
	metricCategoryId int not null,
	dateOfRecording date not null,
	/*
	date would be better here but we need it to be able to be marked as NULL (time is optional)
		- a string based value (like time) in H2 can't be null apparently
		- a time field gives parsing errors when left empty => no way to mark it as NULL
	=> resolved by using a varchar as date that we put "NULL" in to mark it as empty
	*/
	timeOfRecording varchar(8),
	metricValue double not null,
	primary key (userid, metricCategoryId, dateOfRecording, timeOfRecording),
	constraint fk_userId2 foreign key (userId) references users(id),
	constraint fk_metricCategoryId foreign key (metricCategoryId) references metricCategories(id)
);

create table medicalHistories (
    entryId identity not null primary key,
    userId int not null,
    dateOfRecording date not null,
    timeOfRecording time not null,
    condition varchar(100) not null,

    constraint fk_userId3 foreign key (userId) references users(id)
);

create table patientSymptoms (
    medicalHistoryId int not null,
    symptom varchar(255) not null,

    primary key (medicalHistoryId, symptom),
    constraint fk_medicalHistoryId foreign key (medicalHistoryId) references medicalhistories(entryId)
);

create table doctors (
    adriaId int not null,
    fullname varchar(100) not null,

    primary key (adriaId, fullname)
);

create table diagnosedBy (
    medicalHistoryId int not null,
    doctorId int not null,

    primary key (medicalHistoryId, doctorId),
    constraint fk_medicalHistoryId2 foreign key (medicalHistoryId) references medicalhistories(entryId),
    constraint fk_userId4 foreign key (doctorId) references doctors(adriaId)
);