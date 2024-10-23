-- fill bloodtypes table
INSERT into bloodTypes (bloodType, resusFactor) values ('O','-'),
('O','+'),
('A','+'),
('A','-'),
('B','+'),
('B','-'),
('AB','+'),
('AB','-');

-- fill colonies table
INSERT into colonies (NAME) values ('Adria'),
('Mars'),
('Remote-1'),
('Remote-2'),
('Remote-3'),
('Remote-4');

-- fill subscriptiontypes table
INSERT into subscriptionTypes(subscriptionName) values ('ResQ'),
('ResQ +'),
('ResQ Pro');

-- fill metriccategories table
INSERT into metricCategories(METRICNAME, UNIT) values ('Heart Rate','BPM'),
('Blood Pressure','mmHg'),
('Temperature','°C'),
('SPO2',''),
('Muscle Mass','%'),
('Body Water','%'),
('Body Fat','%'),
('Visceral Fat',''),
('Bone Mass','%');

-- fill gender table
INSERT INTO gender(symbol) values ('M'),
('F');

-- fill users table
INSERT into users(id, fname, lname, birthdate, colonyId, bloodtypeId, gender) values (1, 'bob','bobbelino','2004-02-03',1,2, 'M'),
(2, 'alice','anderson','2064-04-07',1,3, 'F'),
(3, 'carol','carolson','2081-11-12',4,1, 'F'),
(4, 'Jane','Doe','1970-09-11',3,4, 'F'),
(5, 'karel', 'degrote','2000-01-01',2,6, 'F'),
(6, 'joske','vermeulen','2020-06-10',2,5, 'M');

-- fill subscriptions table
INSERT into subscriptions(USERID, SUBSCRIPTIONTYPEID, SUBSCRIPTIONDURATIONINDAYS) values (1,1,100),
(2,1,200),
(3,2,150),
(4,3,30),
(5,1,60),
(6,2,1000);

-- fill metrics table
INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,2,'2084-01-01','17:47:52',1812),
(1,3,'2084-01-01','18:48:22',10),
(1,5,'2084-01-01','08:57:14',15),
(2,6,'2084-01-01','16:27:45',100),
(3,4,'2084-01-01','17:42:32',39);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,1,'2084-01-01','NULL',70), -- apparently you can't insert NULL NATIVELY (see db-create for more detailed comment)
(1,1,'2084-01-02','NULL',72),
(1,1,'2084-01-03','NULL',69),
(1,1,'2084-01-04','NULL',70),
(1,1,'2084-01-05','NULL',75),
(1,1,'2084-01-06','NULL',73),
(1,1,'2084-01-07','NULL',80),
(1,1,'2084-01-08','NULL',81),
(1,1,'2084-01-09','NULL',75),
(1,1,'2084-01-10','NULL',76),
(1,1,'2084-01-11','NULL',71),
(1,1,'2084-01-12','NULL',70),
(1,1,'2084-01-13','NULL',68),
(1,1,'2084-01-14','NULL',66);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,4,'2084-01-01','NULL',100),
(1,4,'2084-01-02','NULL',99),
(1,4,'2084-01-03','NULL',99),
(1,4,'2084-01-04','NULL',99),
(1,4,'2084-01-05','NULL',100),
(1,4,'2084-01-06','NULL',98),
(1,4,'2084-01-07','NULL',97),
(1,4,'2084-01-08','NULL',99),
(1,4,'2084-01-09','NULL',100),
(1,4,'2084-01-10','NULL',100),
(1,4,'2084-01-11','NULL',99),
(1,4,'2084-01-12','NULL',100),
(1,4,'2084-01-13','NULL',100),
(1,4,'2084-01-14','NULL',98);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,5,'2084-01-01','NULL',44.2),
(1,5,'2084-01-02','NULL',44.2),
(1,5,'2084-01-03','NULL',44.3),
(1,5,'2084-01-04','NULL',44.2),
(1,5,'2084-01-05','NULL',44.5),
(1,5,'2084-01-06','NULL',44.5),
(1,5,'2084-01-07','NULL',44.4),
(1,5,'2084-01-08','NULL',44.7),
(1,5,'2084-01-09','NULL',44.8),
(1,5,'2084-01-10','NULL',45),
(1,5,'2084-01-11','NULL',45),
(1,5,'2084-01-12','NULL',45.1),
(1,5,'2084-01-13','NULL',45.1),
(1,5,'2084-01-14','NULL',44.9);


INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,6,'2084-01-01','NULL',58.9),
(1,6,'2084-01-02','NULL',59.3),
(1,6,'2084-01-03','NULL',60.3),
(1,6,'2084-01-04','NULL',59.8),
(1,6,'2084-01-05','NULL',61.4),
(1,6,'2084-01-06','NULL',58.2),
(1,6,'2084-01-07','NULL',58.9),
(1,6,'2084-01-08','NULL',58.2),
(1,6,'2084-01-09','NULL',61.9),
(1,6,'2084-01-10','NULL',60),
(1,6,'2084-01-11','NULL',60.4),
(1,6,'2084-01-12','NULL',59.4),
(1,6,'2084-01-13','NULL',61.5),
(1,6,'2084-01-14','NULL',61.5);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,7,'2084-01-01','NULL',19.9),
(1,7,'2084-01-02','NULL',19.9),
(1,7,'2084-01-03','NULL',19.9),
(1,7,'2084-01-04','NULL',19.8),
(1,7,'2084-01-05','NULL',19.7),
(1,7,'2084-01-06','NULL',19.7),
(1,7,'2084-01-07','NULL',19.8),
(1,7,'2084-01-08','NULL',19.6),
(1,7,'2084-01-09','NULL',19.3),
(1,7,'2084-01-10','NULL',19),
(1,7,'2084-01-11','NULL',19.1),
(1,7,'2084-01-12','NULL',18.8),
(1,7,'2084-01-13','NULL',18.6),
(1,7,'2084-01-14','NULL',18.7);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,8,'2084-01-01','NULL',7.51),
(1,8,'2084-01-02','NULL',7.49),
(1,8,'2084-01-03','NULL',7.49),
(1,8,'2084-01-04','NULL',7.45),
(1,8,'2084-01-05','NULL',7.47),
(1,8,'2084-01-06','NULL',7.4),
(1,8,'2084-01-07','NULL',7.42),
(1,8,'2084-01-08','NULL',7.38),
(1,8,'2084-01-09','NULL',7.35),
(1,8,'2084-01-10','NULL',7.36),
(1,8,'2084-01-11','NULL',7.32),
(1,8,'2084-01-12','NULL',7.2),
(1,8,'2084-01-13','NULL',7.21),
(1,8,'2084-01-14','NULL',7.19);

INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (1,9,'2084-01-01','NULL',6.21),
(1,9,'2084-01-02','NULL',6.29),
(1,9,'2084-01-03','NULL',6.29),
(1,9,'2084-01-04','NULL',6.35),
(1,9,'2084-01-05','NULL',6.27),
(1,9,'2084-01-06','NULL',6.4),
(1,9,'2084-01-07','NULL',6.42),
(1,9,'2084-01-08','NULL',6.38),
(1,9,'2084-01-09','NULL',6.35),
(1,9,'2084-01-10','NULL',6.36),
(1,9,'2084-01-11','NULL',6.32),
(1,9,'2084-01-12','NULL',6.2),
(1,9,'2084-01-13','NULL',6.21),
(1,9,'2084-01-14','NULL',6.19);


-- fill medicalhistories table
INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (1, '2084-05-15', '09:30:00', 'Common Cold'),
(1, '2084-05-16', '10:00:00', 'Type 2 Diabetes'),
(3, '2084-05-17', '14:30:00', 'Hypertension'),
(4, '2084-05-18', '16:00:00', 'Asthma'),
(5, '2084-05-20', '11:15:00', 'Seasonal Allergies');

-- fill patientsymptoms table
INSERT into patientSymptoms(medicalhistoryid, symptom) values (4,'cough'),
(2,'Dizziness'),
(1,'mild fever');

-- fill doctors table
INSERT into doctors(adriaId, fullname) values (999,'Natalie Portman'),
(1000,'Natalie Portman'),
(998,'Emilia Clarke'),
(997,'Robert Downey Jr.');

-- fill diagnosedBy table
INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (1, 999),
(1, 1000),
(1, 998),
(2, 997);

-- add symptoms
INSERT INTO symptoms (id, name) VALUES
(1,'sneezing'),
(2,'runny or stuffy nose'),
(3,'cough'),
(4,'sore throat'),
(5,'fatigue'),
(6,'fever'),
(7,'chills'),
(8,'body aches'),
(9,'headache'),
(10,'shortness of breath'),
(11,'loss of taste or smell'),
(12,'wheezing'),
(13,'coughing'),
(14,'chest tightness'),
(15,'increased thirst'),
(16,'frequent urination'),
(17,'unexplained weight loss'),
(18,'chest pain'),
(19,'vision problems'),
(20,'severe headache'),
(21,'nausea'),
(22,'sensitivity to light and sound'),
(23,'bull''s-eye rash'),
(24,'flu-like symptoms'),
(25,'joint pain'),
(26,'high fever'),
(27,'cough with phlegm'),
(28,'cough with mucus'),
(29,'chest discomfort'),
(30,'diarrhea'),
(31,'vomiting'),
(32,'abdominal pain'),
(33,'severe pain in the back or side'),
(34,'blood in urine'),
(35,'pain or burning during urination'),
(36,'frequent urge to urinate'),
(37,'cloudy urine'),
(38,'loss of appetite'),
(39,'swelling'),
(40,'stiffness'),
(41,'bloating'),
(42,'weight loss'),
(43,'bloody stools'),
(44,'difficulty walking'),
(45,'numbness or tingling'),
(46,'muscle weakness'),
(47,'rash'),
(48,'tremors'),
(49,'slowed movement'),
(50,'balance problems'),
(51,'memory loss'),
(52,'confusion'),
(53,'difficulty with familiar tasks'),
(54,'abdominal bloating'),
(55,'pelvic pain'),
(56,'difficulty eating'),
(57,'lump in the breast'),
(58,'changes in breast size or shape'),
(59,'nipple discharge'),
(60,'difficulty starting or stopping urination'),
(61,'weak or interrupted flow'),
(62,'jaundice'),
(63,'night sweats'),
(64,'weakness'),
(65,'pale or sallow skin'),
(66,'chronic cough'),
(67,'seizures'),
(68,'temporary confusion'),
(69,'staring spells'),
(70,'hallucinations'),
(71,'delusions'),
(72,'disorganized thinking'),
(73,'extreme mood swings'),
(74,'difficulty sleeping'),
(75,'energy changes'),
(76,'persistent sadness'),
(77,'loss of interest or pleasure'),
(78,'excessive worry'),
(79,'restlessness'),
(80,'difficulty concentrating'),
(81,'sudden and repeated attacks of fear'),
(82,'intense fear of social situations'),
(83,'fear of judgment or embarrassment'),
(84,'persistent, unwanted thoughts'),
(85,'ritualistic behaviors'),
(86,'flashbacks'),
(87,'nightmares'),
(88,'hypervigilance'),
(89,'extreme preoccupation with food, weight, and body image'),
(90,'restrictive eating'),
(91,'binge eating'),
(92,'weight gain'),
(93,'sensitivity to cold'),
(94,'dry skin'),
(95,'increased appetite'),
(96,'anxiety'),
(97,'irritability'),
(98,'painful periods'),
(99,'pain during or after sex'),
(100,'irregular periods'),
(101,'excess hair growth'),
(102,'acne'),
(103,'widespread pain'),
(104,'sleep disturbances'),
(105,'profound fatigue'),
(106,'memory and concentration problems'),
(107,'uncontrolled movements'),
(108,'loss of cognitive abilities'),
(109,'mood changes'),
(110,'difficulty speaking or swallowing'),
(111,'muscle cramps'),
(112,'anal itching'),
(113,'pain or discomfort'),
(114,'bleeding during bowel movements'),
(115,'sudden, severe pain'),
(116,'redness in joints'),
(117,'changes in bowel habits'),
(118,'brittle bones'),
(119,'back pain'),
(120,'loss of height over time'),
(121,'swelling in the abdomen'),
(122,'yellowing of the skin and eyes'),
(123,'irregular heartbeat'),
(124,'palpitations'),
(125,'leg pain'),
(126,'numbness or weakness'),
(127,'coldness in lower leg or foot'),
(128,'purple stretch marks'),
(129,'darkening of the skin'),
(130,'pain episodes'),
(131,'persistent cough with thick mucus'),
(132,'frequent lung infections'),
(133,'difficulty breathing'),
(134,'easy bruising'),
(135,'prolonged bleeding'),
(136,'shakiness'),
(137,'sweating'),
(138,'painful urination'),
(139,'unusual discharge'),
(140,'abnormal discharge'),
(141,'painless sores'),
(142,'skin rash'),
(143,'itching or pain in genital area'),
(144,'small red bumps or tiny white blisters'),
(145,'genital warts'),
(146,'abnormal Pap smears'),
(147,'cervical dysplasia'),
(148,'changes in urination'),
(149,'gradual loss of peripheral vision'),
(150,'eye pain'),
(151,'blurred vision'),
(152,'cloudy or blurred vision'),
(153,'difficulty seeing at night'),
(154,'sensitivity to light'),
(155,'blurred or distorted vision'),
(156,'difficulty recognizing faces'),
(157,'dark or empty spots in vision'),
(158,'loud snoring'),
(159,'choking or gasping during sleep'),
(160,'excessive daytime sleepiness'),
(161,'urge to move legs'),
(162,'uncomfortable sensations in legs'),
(163,'difficulty falling asleep'),
(164,'difficulty staying asleep'),
(165,'waking up too early'),
(166,'pauses in breathing during sleep'),
(167,'daytime fatigue'),
(168,'dry eyes'),
(169,'dry mouth'),
(170,'paralysis'),
(171,'cough that lasts more than three weeks'),
(172,'conjunctivitis'),
(173,'muscle pain'),
(174,'muscle aches'),
(175,'sudden high fever'),
(176,'severe headaches'),
(177,'joint and muscle pain'),
(178,'prolonged fever'),
(179,'severe diarrhea'),
(180,'dehydration'),
(181,'skin sores'),
(182,'nerve damage'),
(183,'swollen lymph nodes'),
(184,'difficulty swallowing'),
(185,'stiffness of jaw muscles'),
(186,'muscle spasms'),
(187,'excessive salivation'),
(188,'rash with pustules'),
(189,'runny nose'),
(190,'red eyes'),
(191,'mild fever'),
(192,'swollen salivary glands'),
(193,'itchy rash'),
(194,'painful rash'),
(195,'burning or tingling sensation'),
(196,'extreme fatigue'),
(197,'persistent cough'),
(198,'change in bowel habits'),
(199,'blood in stool'),
(200,'abdominal discomfort'),
(201,'indigestion'),
(202,'lump or swelling in the testicle'),
(203,'changes in size or shape of the testicle'),
(204,'abnormal vaginal bleeding'),
(205,'pain during intercourse'),
(206,'lump in the neck'),
(207,'hoarseness'),
(208,'heavy menstrual bleeding'),
(209,'pelvic pain or pressure'),
(210,'softening and weakening of bones'),
(211,'delayed growth'),
(212,'yellowing of the skin or eyes'),
(213,'changes in moles or skin growths'),
(214,'itchiness'),
(215,'bleeding'),
(216,'frequent infections'),
(217,'chest pain or discomfort'),
(218,'pain or tenderness in the leg'),
(219,'warmth or redness'),
(220,'severe abdominal or back pain'),
(221,'dizziness'),
(222,'heartburn'),
(223,'regurgitation'),
(224,'severe coughing fits'),
(225,'whooping sound'),
(226,'motor abnormalities'),
(227,'dark urine'),
(228,'bone pain'),
(229,'fractures');

-- add deseases and in between table
INSERT INTO diseases (id, name) VALUES (1,'Common Cold');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5);
INSERT INTO diseases (id, name) VALUES (2,'Influenza (Flu)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(2,6),
(2,7),
(2,8),
(2,9),
(2,5);
INSERT INTO diseases (id, name) VALUES (3,'COVID-19');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(3,6),
(3,3),
(3,10),
(3,11),
(3,5);
INSERT INTO diseases (id, name) VALUES (4,'Asthma');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(4,10),
(4,12),
(4,13),
(4,14);
INSERT INTO diseases (id, name) VALUES (5,'Diabetes');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(5,15),
(5,16),
(5,17),
(5,5);
INSERT INTO diseases (id, name) VALUES (6,'Hypertension (High Blood Pressure)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(6,9),
(6,5),
(6,18),
(6,19);
INSERT INTO diseases (id, name) VALUES (7,'Migraine');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(7,20),
(7,21),
(7,22);
INSERT INTO diseases (id, name) VALUES (8,'Lyme Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(8,23),
(8,24),
(8,25),
(8,5);
INSERT INTO diseases (id, name) VALUES (9,'Pneumonia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(9,26),
(9,10),
(9,18),
(9,27);
INSERT INTO diseases (id, name) VALUES (10,'Bronchitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(10,28),
(10,10),
(10,29),
(10,5);
INSERT INTO diseases (id, name) VALUES (11,'Gastroenteritis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(11,30),
(11,21),
(11,31),
(11,32),
(11,6);
INSERT INTO diseases (id, name) VALUES (12,'Kidney Stones');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(12,33),
(12,34),
(12,16);
INSERT INTO diseases (id, name) VALUES (13,'Urinary Tract Infection (UTI)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(13,35),
(13,36),
(13,37);
INSERT INTO diseases (id, name) VALUES (14,'Appendicitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(14,32),
(14,38),
(14,21),
(14,31),
(14,6);
INSERT INTO diseases (id, name) VALUES (15,'Rheumatoid Arthritis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(15,25),
(15,39),
(15,40),
(15,5);
INSERT INTO diseases (id, name) VALUES (16,'Osteoarthritis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(16,25),
(16,40),
(16,39);
INSERT INTO diseases (id, name) VALUES (17,'Celiac Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(17,32),
(17,30),
(17,41),
(17,5);
INSERT INTO diseases (id, name) VALUES (18,'Crohn''s Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(18,32),
(18,30),
(18,42),
(18,5);
INSERT INTO diseases (id, name) VALUES (19,'Ulcerative Colitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(19,32),
(19,43),
(19,30),
(19,5);
INSERT INTO diseases (id, name) VALUES (20,'Multiple Sclerosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(20,5),
(20,44),
(20,45),
(20,46);
INSERT INTO diseases (id, name) VALUES (21,'Lupus');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(21,25),
(21,5),
(21,47),
(21,6);
INSERT INTO diseases (id, name) VALUES (22,'Parkinson''s Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(22,48),
(22,40),
(22,49),
(22,50);
INSERT INTO diseases (id, name) VALUES (23,'Alzheimer''s Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(23,51),
(23,52),
(23,53);
INSERT INTO diseases (id, name) VALUES (24,'Ovarian Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(24,54),
(24,55),
(24,56),
(24,16);
INSERT INTO diseases (id, name) VALUES (25,'Breast Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(25,57),
(25,58),
(25,59);
INSERT INTO diseases (id, name) VALUES (26,'Prostate Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(26,16),
(26,60),
(26,61);
INSERT INTO diseases (id, name) VALUES (27,'Hepatitis B');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(27,5),
(27,62),
(27,32),
(27,21);
INSERT INTO diseases (id, name) VALUES (28,'HIV/AIDS');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(28,6),
(28,5),
(28,17),
(28,63);
INSERT INTO diseases (id, name) VALUES (29,'Anemia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(29,5),
(29,64),
(29,65),
(29,10);
INSERT INTO diseases (id, name) VALUES (30,'COPD (Chronic Obstructive Pulmonary Disease)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(30,10),
(30,66),
(30,12);
INSERT INTO diseases (id, name) VALUES (31,'Epilepsy');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(31,67),
(31,68),
(31,69);
INSERT INTO diseases (id, name) VALUES (32,'Schizophrenia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(32,70),
(32,71),
(32,72);
INSERT INTO diseases (id, name) VALUES (33,'Bipolar Disorder');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(33,73),
(33,74),
(33,75);
INSERT INTO diseases (id, name) VALUES (34,'Depression');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(34,76),
(34,77),
(34,5);
INSERT INTO diseases (id, name) VALUES (35,'Generalized Anxiety Disorder (GAD)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(35,78),
(35,79),
(35,80);
INSERT INTO diseases (id, name) VALUES (36,'Panic Disorder');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(36,81),
(36,18),
(36,10);
INSERT INTO diseases (id, name) VALUES (37,'Social Anxiety Disorder');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(37,82),
(37,83);
INSERT INTO diseases (id, name) VALUES (38,'Obsessive-Compulsive Disorder (OCD)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(38,84),
(38,85);
INSERT INTO diseases (id, name) VALUES (39,'Post-Traumatic Stress Disorder (PTSD)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(39,86),
(39,87),
(39,88);
INSERT INTO diseases (id, name) VALUES (40,'Eating Disorders');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(40,89),
(40,90),
(40,91);
INSERT INTO diseases (id, name) VALUES (41,'Type 1 Diabetes');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(41,15),
(41,16),
(41,17),
(41,5);
INSERT INTO diseases (id, name) VALUES (42,'Type 2 Diabetes');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(42,15),
(42,16),
(42,17),
(42,5);
INSERT INTO diseases (id, name) VALUES (43,'Hypothyroidism');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(43,5),
(43,92),
(43,93),
(43,94);
INSERT INTO diseases (id, name) VALUES (44,'Hyperthyroidism');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(44,42),
(44,95),
(44,96),
(44,97);
INSERT INTO diseases (id, name) VALUES (45,'Endometriosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(45,55),
(45,98),
(45,99);
INSERT INTO diseases (id, name) VALUES (46,'Polycystic Ovary Syndrome (PCOS)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(46,100),
(46,101),
(46,102);
INSERT INTO diseases (id, name) VALUES (47,'Fibromyalgia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(47,103),
(47,5),
(47,104);
INSERT INTO diseases (id, name) VALUES (48,'Chronic Fatigue Syndrome (CFS)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(48,105),
(48,25),
(48,106);
INSERT INTO diseases (id, name) VALUES (49,'Huntington''s Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(49,107),
(49,108),
(49,109);
INSERT INTO diseases (id, name) VALUES (50,'Amyotrophic Lateral Sclerosis (ALS)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(50,46),
(50,110),
(50,111);
INSERT INTO diseases (id, name) VALUES (51,'Hemorrhoids');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(51,112),
(51,113),
(51,114);
INSERT INTO diseases (id, name) VALUES (52,'Gout');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(52,115),
(52,39),
(52,116);
INSERT INTO diseases (id, name) VALUES (53,'Diverticulitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(53,32),
(53,6),
(53,21),
(53,117);
INSERT INTO diseases (id, name) VALUES (54,'Osteoporosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(54,118),
(54,119),
(54,120);
INSERT INTO diseases (id, name) VALUES (55,'Pancreatitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(55,32),
(55,21),
(55,31),
(55,6);
INSERT INTO diseases (id, name) VALUES (56,'Cirrhosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(56,5),
(56,121),
(56,122);
INSERT INTO diseases (id, name) VALUES (57,'Atrial Fibrillation');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(57,123),
(57,124),
(57,5),
(57,10);
INSERT INTO diseases (id, name) VALUES (58,'Peripheral Artery Disease (PAD)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(58,125),
(58,126),
(58,127);
INSERT INTO diseases (id, name) VALUES (59,'Cushing''s Syndrome');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(59,92),
(59,128),
(59,5);
INSERT INTO diseases (id, name) VALUES (60,'Addison''s Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(60,5),
(60,42),
(60,129);
INSERT INTO diseases (id, name) VALUES (61,'Sickle Cell Anemia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(61,130),
(61,5),
(61,62);
INSERT INTO diseases (id, name) VALUES (62,'Cystic Fibrosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(62,131),
(62,132),
(62,133);
INSERT INTO diseases (id, name) VALUES (63,'Hemophilia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(63,134),
(63,135),
(63,25);
INSERT INTO diseases (id, name) VALUES (64,'Vitamin B12 Deficiency');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(64,5),
(64,64),
(64,45),
(64,44);
INSERT INTO diseases (id, name) VALUES (65,'Hypoglycemia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(65,136),
(65,137),
(65,97),
(65,52);
INSERT INTO diseases (id, name) VALUES (66,'Chlamydia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(66,138),
(66,32),
(66,139);
INSERT INTO diseases (id, name) VALUES (67,'Gonorrhea');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(67,138),
(67,140),
(67,55);
INSERT INTO diseases (id, name) VALUES (68,'Syphilis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(68,141),
(68,142),
(68,6);
INSERT INTO diseases (id, name) VALUES (69,'Genital Herpes');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(69,143),
(69,144),
(69,24);
INSERT INTO diseases (id, name) VALUES (70,'Human Papillomavirus (HPV)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(70,145),
(70,146),
(70,147);
INSERT INTO diseases (id, name) VALUES (71,'Chronic Kidney Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(71,5),
(71,39),
(71,148);
INSERT INTO diseases (id, name) VALUES (72,'Glaucoma');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(72,149),
(72,150),
(72,151);
INSERT INTO diseases (id, name) VALUES (73,'Cataracts');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(73,152),
(73,153),
(73,154);
INSERT INTO diseases (id, name) VALUES (74,'Macular Degeneration');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(74,155),
(74,156),
(74,157);
INSERT INTO diseases (id, name) VALUES (75,'Obstructive Sleep Apnea');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(75,158),
(75,159),
(75,160);
INSERT INTO diseases (id, name) VALUES (76,'Restless Legs Syndrome (RLS)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(76,161),
(76,162),
(76,74);
INSERT INTO diseases (id, name) VALUES (77,'Insomnia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(77,163),
(77,164),
(77,165);
INSERT INTO diseases (id, name) VALUES (78,'Sleep Apnea');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(78,158),
(78,166),
(78,167);
INSERT INTO diseases (id, name) VALUES (79,'Rheumatic Fever');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(79,25),
(79,6),
(79,142),
(79,10);
INSERT INTO diseases (id, name) VALUES (80,'Sjögren''s Syndrome');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(80,168),
(80,169),
(80,5);
INSERT INTO diseases (id, name) VALUES (81,'Polio');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(81,46),
(81,170),
(81,133);
INSERT INTO diseases (id, name) VALUES (82,'Tuberculosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(82,171),
(82,18),
(82,17);
INSERT INTO diseases (id, name) VALUES (83,'Malaria');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(83,6),
(83,7),
(83,137),
(83,9),
(83,21);
INSERT INTO diseases (id, name) VALUES (84,'Yellow Fever');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(84,6),
(84,122),
(84,9),
(84,21);
INSERT INTO diseases (id, name) VALUES (85,'Zika Virus');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(85,6),
(85,47),
(85,25),
(85,172);
INSERT INTO diseases (id, name) VALUES (86,'West Nile Virus');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(86,6),
(86,9),
(86,8),
(86,142);
INSERT INTO diseases (id, name) VALUES (87,'Ebola Virus Disease');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(87,6),
(87,20),
(87,173),
(87,31);
INSERT INTO diseases (id, name) VALUES (88,'Lassa Fever');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(88,6),
(88,9),
(88,174),
(88,31);
INSERT INTO diseases (id, name) VALUES (89,'Hantavirus Pulmonary Syndrome');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(89,6),
(89,174),
(89,10);
INSERT INTO diseases (id, name) VALUES (90,'Chikungunya');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(90,6),
(90,25),
(90,173),
(90,9);
INSERT INTO diseases (id, name) VALUES (91,'Dengue Fever');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(91,175),
(91,176),
(91,177),
(91,142);
INSERT INTO diseases (id, name) VALUES (92,'Typhoid Fever');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(92,178),
(92,9),
(92,32),
(92,64);
INSERT INTO diseases (id, name) VALUES (93,'Cholera');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(93,179),
(93,31),
(93,180);
INSERT INTO diseases (id, name) VALUES (94,'Leprosy');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(94,181),
(94,182),
(94,46);
INSERT INTO diseases (id, name) VALUES (95,'Anthrax');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(95,6),
(95,183),
(95,181);
INSERT INTO diseases (id, name) VALUES (96,'Botulism');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(96,46),
(96,184),
(96,151);
INSERT INTO diseases (id, name) VALUES (97,'Plague');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(97,6),
(97,7),
(97,64),
(97,183);
INSERT INTO diseases (id, name) VALUES (98,'Tetanus');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(98,185),
(98,184),
(98,186);
INSERT INTO diseases (id, name) VALUES (99,'Rabies');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(99,6),
(99,9),
(99,187),
(99,52);
INSERT INTO diseases (id, name) VALUES (100,'Smallpox');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(100,26),
(100,188),
(100,5);
INSERT INTO diseases (id, name) VALUES (101,'Measles');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(101,26),
(101,3),
(101,189),
(101,190),
(101,47);
INSERT INTO diseases (id, name) VALUES (102,'Rubella (German Measles)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(102,191),
(102,183),
(102,47);
INSERT INTO diseases (id, name) VALUES (103,'Mumps');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(103,192),
(103,6),
(103,9);
INSERT INTO diseases (id, name) VALUES (104,'Chickenpox');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(104,193),
(104,6),
(104,9);
INSERT INTO diseases (id, name) VALUES (105,'Shingles');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(105,194),
(105,195),
(105,9);
INSERT INTO diseases (id, name) VALUES (106,'Mononucleosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(106,196),
(106,4),
(106,183);
INSERT INTO diseases (id, name) VALUES (107,'Toxoplasmosis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(107,24),
(107,174),
(107,9);
INSERT INTO diseases (id, name) VALUES (108,'Lung Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(108,197),
(108,18),
(108,10);
INSERT INTO diseases (id, name) VALUES (109,'Colon Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(109,198),
(109,199),
(109,200);
INSERT INTO diseases (id, name) VALUES (110,'Liver Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(110,17),
(110,32),
(110,62);
INSERT INTO diseases (id, name) VALUES (111,'Pancreatic Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(111,32),
(111,17),
(111,62);
INSERT INTO diseases (id, name) VALUES (112,'Stomach Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(112,201),
(112,32),
(112,17);
INSERT INTO diseases (id, name) VALUES (113,'Esophageal Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(113,184),
(113,17),
(113,18);
INSERT INTO diseases (id, name) VALUES (114,'Bladder Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(114,34),
(114,16),
(114,55);
INSERT INTO diseases (id, name) VALUES (115,'Testicular Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(115,202),
(115,113),
(115,203);
INSERT INTO diseases (id, name) VALUES (116,'Cervical Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(116,204),
(116,55),
(116,205);
INSERT INTO diseases (id, name) VALUES (117,'Thyroid Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(117,206),
(117,207),
(117,184);
INSERT INTO diseases (id, name) VALUES (118,'Ovarian Cyst');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(118,55),
(118,41),
(118,117),
(118,16);
INSERT INTO diseases (id, name) VALUES (119,'Uterine Fibroids');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(119,208),
(119,209),
(119,16);
INSERT INTO diseases (id, name) VALUES (120,'Endometrial Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(120,17),
(120,55),
(120,204);
INSERT INTO diseases (id, name) VALUES (121,'Prostatitis');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(121,55),
(121,138),
(121,16);
INSERT INTO diseases (id, name) VALUES (122,'Rickets');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(122,210),
(122,211),
(122,173);
INSERT INTO diseases (id, name) VALUES (123,'Gallstones');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(123,32),
(123,21),
(123,31),
(123,212);
INSERT INTO diseases (id, name) VALUES (124,'Melanoma');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(124,213),
(124,214),
(124,215);
INSERT INTO diseases (id, name) VALUES (125,'Leukemia');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(125,5),
(125,216),
(125,17);
INSERT INTO diseases (id, name) VALUES (126,'Myocardial Infarction (Heart Attack)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(126,217),
(126,10),
(126,137);
INSERT INTO diseases (id, name) VALUES (127,'Deep Vein Thrombosis (DVT)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(127,39),
(127,218),
(127,219);
INSERT INTO diseases (id, name) VALUES (128,'Aortic Aneurysm');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(128,220),
(128,221),
(128,10);
INSERT INTO diseases (id, name) VALUES (129,'Gastroesophageal Reflux Disease (GERD)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(129,222),
(129,223),
(129,18);
INSERT INTO diseases (id, name) VALUES (130,'Irritable Bowel Syndrome (IBS)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(130,32),
(130,41),
(130,117);
INSERT INTO diseases (id, name) VALUES (131,'Hepatitis C');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(131,5),
(131,62),
(131,32);
INSERT INTO diseases (id, name) VALUES (132,'Rubella');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(132,47),
(132,191),
(132,183);
INSERT INTO diseases (id, name) VALUES (133,'Whooping Cough (Pertussis)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(133,224),
(133,225),
(133,31);
INSERT INTO diseases (id, name) VALUES (134,'Sleeping Sickness');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(134,6),
(134,9),
(134,52);
INSERT INTO diseases (id, name) VALUES (135,'HIV Encephalopathy');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(135,51),
(135,52),
(135,226);
INSERT INTO diseases (id, name) VALUES (136,'Hepatitis A');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(136,5),
(136,21),
(136,32),
(136,62),
(136,227);
INSERT INTO diseases (id, name) VALUES (137,'Hepatitis D');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(137,5),
(137,62),
(137,32),
(137,21),
(137,31);
INSERT INTO diseases (id, name) VALUES (138,'Hepatitis E');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(138,5),
(138,21),
(138,32),
(138,62),
(138,227);
INSERT INTO diseases (id, name) VALUES (139,'Colorectal Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(139,117),
(139,199),
(139,32);
INSERT INTO diseases (id, name) VALUES (140,'Kidney Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(140,34),
(140,32),
(140,17);
INSERT INTO diseases (id, name) VALUES (141,'Skin Cancer (Melanoma)');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(141,213),
(141,214),
(141,215);
INSERT INTO diseases (id, name) VALUES (142,'Bone Cancer');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(142,228),
(142,39),
(142,229);
INSERT INTO diseases (id, name) VALUES (143,'Lymphoma');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(143,183),
(143,6),
(143,63);
INSERT INTO diseases (id, name) VALUES (144,'Multiple Myeloma');
INSERT INTO disease_symptoms (disease_id, symptom_id) VALUES
(144,228),
(144,5),
(144,64);
