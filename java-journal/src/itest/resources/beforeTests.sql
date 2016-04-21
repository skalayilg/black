
insert into topic (id,name) values 
	(1,'Medical'),(2,'Biology'),(3,'Cardiology'),(4,'Endocrinology'),(5,'Infectious Diseases'),
	(6,'Genetics'),(7,'Microbiology'),(8,'Neurology'),(9,'Oncology'),(10,'Physiology');
	
insert into user (id,fullname,password,role,username) values 
    (1,'Alex Job','123abc','subscriber','alex'),
    (2,'Felex Job','123abc','subscriber','felex'),
    (3,'Alex Peter','123abc','subscriber','alexp'),
    (4,'Job Peter','123abc','subscriber','jobp'),
    (5,'Peter Matt','123abc','publisher','peter'),
    (6,'Philip Peter','123abc','publisher','phil');
    
    
insert into journal (id,file,title,topic_id,user_id) values
    (1,'abc1.pdf','Cold Spring Harbor Perspectives in Medicine',1,5),
    (2,'abc2.pdf','BMC Medicine',1,5),
    (3,'abc3.pdf','Briefings in Bioinformatics',2,5),
    (4,'abc4.pdf','JACC - Cardiovascular Interventions',3,6),
    (5,'abc5.pdf','International Journal of Obesity and Related Metabolic Disorders',4,6),
    (6,'abc6.pdf','Emerging Infectious Diseases',5,6);
    
insert into user_topic (subscribers_id, topicSubcriptions_id) values
    (1,1),(1,2),(1,3),(2,3),(2,5),(3,1),(4,3);