-- fill bloodtypes table
INSERT into bloodTypes (bloodType, resusFactor) values ('O','-');
INSERT into bloodTypes (bloodType, resusFactor) values ('O','+');
INSERT into bloodTypes (bloodType, resusFactor) values ('A','+');
INSERT into bloodTypes (bloodType, resusFactor) values ('A','-');
INSERT into bloodTypes (bloodType, resusFactor) values ('B','+');
INSERT into bloodTypes (bloodType, resusFactor) values ('B','-');
INSERT into bloodTypes (bloodType, resusFactor) values ('AB','+');
INSERT into bloodTypes (bloodType, resusFactor) values ('AB','-');

-- fill colonies table
INSERT into colonies (NAME) values ('Adria');
INSERT into colonies (NAME) values ('Mars');
INSERT into colonies (NAME) values ('Remote-1');
INSERT into colonies (NAME) values ('Remote-2');
INSERT into colonies (NAME) values ('Remote-3');
INSERT into colonies (NAME) values ('Remote-4');

-- fill subscriptiontypes table
INSERT into subscriptionTypes(subscriptionName) values ('ResQ');
INSERT into subscriptionTypes(subscriptionName) values ('ResQ +');
INSERT into subscriptionTypes(subscriptionName) values ('ResQ Pro');

-- fill metriccategories table
INSERT into metricCategories(METRICNAME, UNIT) values ('Heart Rate','BPM');
INSERT into metricCategories(METRICNAME, UNIT) values ('Blood Pressure','mmHg');
INSERT into metricCategories(METRICNAME, UNIT) values ('Temperature','°C');
INSERT into metricCategories(METRICNAME, UNIT) values ('SPO2','%');
INSERT into metricCategories(METRICNAME, UNIT) values ('Muscle Mass','%');
INSERT into metricCategories(METRICNAME, UNIT) values ('Body Water','%');
INSERT into metricCategories(METRICNAME, UNIT) values ('Body Fat','%');
INSERT into metricCategories(METRICNAME, UNIT) values ('Visceral Fat','%');

-- fill gender table
INSERT INTO gender(symbol) values ('M');
INSERT INTO gender(symbol) values ('F');

-- fill users table
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (1, 'bob','bobbelino','2004-02-03',1,2, 'M');
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (2, 'alice','anderson','2064-04-07',1,3, 'F');
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (3, 'carol','carolson','2081-11-12',4,1, 'F');
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (4, 'Jane','Doe','1970-09-11',3,4, 'F');
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (5, 'karel', 'degrote','2000-01-01',2,6, 'F');
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (6, 'joske','vermeulen','2020-06-10',2,5, 'M');

-- fill subscriptions table
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (1,1,100);
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (2,1,200);
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (3,2,150);
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (4,3,30);
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (5,1,60);
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (6,2,1000);

-- fill metrics table
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,2,'2084-01-01','17:47:52',1812);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-01','18:48:22',10);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,5,'2084-01-01','08:57:14',15);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (2,6,'2084-01-01','16:27:45',100);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (3,4,'2084-01-01','17:42:32',39);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-01','NULL',70); -- apparently you can't insert NULL NATIVELY (see db-create for more detailed comment)
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-02','NULL',72);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-03','NULL',69);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-04','NULL',70);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-05','NULL',75);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-06','NULL',73);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-07','NULL',80);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-08','NULL',81);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-09','NULL',75);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-10','NULL',76);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-11','NULL',71);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-12','NULL',70);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-13','NULL',68);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-14','NULL',66);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-01','NULL',36); 
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-02','NULL',37); 
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-03','NULL',36.5);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-04','NULL',37.1);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-05','NULL',35.9);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-06','NULL',36.1);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-07','NULL',36.2);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-08','NULL',37.5);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-09','NULL',38); 
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-10','NULL',38.5);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-11','NULL',39); 
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-12','NULL',38); 
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-13','NULL',36.7);
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,3,'2084-01-14','NULL',36); 


-- fill medicalhistories table
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (1, '2084-05-15', '09:30:00', 'Common Cold');
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (1, '2084-05-16', '10:00:00', 'Type 2 Diabetes');
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (3, '2084-05-17', '14:30:00', 'Hypertension');
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (4, '2084-05-18', '16:00:00', 'Asthma');
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (5, '2084-05-20', '11:15:00', 'Seasonal Allergies');

-- fill patientsymptoms table
INSERT into patientSymptoms(medicalhistoryid, symptom) values (4,'cough');
INSERT into patientSymptoms(medicalhistoryid, symptom) values (2,'Dizziness');
INSERT into patientSymptoms(medicalhistoryid, symptom) values (1,'mild fever');

-- fill doctors table
INSERT into doctors(adriaId, fullname) values (999,'Natalie Portman');
INSERT into doctors(adriaId, fullname) values (1000,'Natalie Portman');
INSERT into doctors(adriaId, fullname) values (998,'Emilia Clarke');
INSERT into doctors(adriaId, fullname) values (997,'Robert Downey Jr.');

-- fill diagnosedBy table
INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (1, 999);
INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (1, 1000);
INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (1, 998);
INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (2, 997);