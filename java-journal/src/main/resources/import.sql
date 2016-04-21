
insert into topic (name) values 
	("Medical"),("Biology"),("Cardiology"),("Endocrinology"),("Infectious Diseases"),
	("Genetics"),("Microbiology"),("Neurology"),("Oncology"),("Physiology");
	
insert into user (fullname,password,role,username) values 
    ("Alex Job","123abc","subscriber","alex"),
    ("Felex Job","123abc","subscriber","felex"),
    ("Alex Peter","123abc","subscriber","alexp"),
    ("Job Peter","123abc","subscriber","jobp"),
    ("Peter Matt","123abc","publisher","peter"),
    ("Philip Peter","123abc","publisher","phil");
    
    
insert into journal (file,title,topic_id,user_id) values
    ("abc1.pdf","Cold Spring Harbor Perspectives in Medicine",1,5),
    ("abc2.pdf","BMC Medicine",1,5),
    ("abc3.pdf","Briefings in Bioinformatics",2,5),
    ("abc4.pdf","JACC - Cardiovascular Interventions",3,6),
    ("abc5.pdf","International Journal of Obesity and Related Metabolic Disorders",4,6),
    ("abc6.pdf","Emerging Infectious Diseases",5,6);
    
insert into user_topic (subscribers_id, topicSubcriptions_id) values
    (1,1),(1,2),(1,3),(2,3),(2,5),(3,1),(4,3);
        
	