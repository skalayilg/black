create table Journal (id integer not null, file varchar(255), title varchar(255), topic_id integer, primary key (id))
create table Topic (id integer not null, name varchar(255), primary key (id))
create table Topic_User (topicSubcriptions_id integer not null, subscribers_id integer not null)
create table User (id integer not null, fullName varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id))
alter table Journal add index FK_ey03obrljssaybwjqxq1g9m7g (topic_id), add constraint FK_ey03obrljssaybwjqxq1g9m7g foreign key (topic_id) references Topic (id)
alter table Topic_User add index FK_qw225nxbsvnnakgy2y1cl6wy2 (subscribers_id), add constraint FK_qw225nxbsvnnakgy2y1cl6wy2 foreign key (subscribers_id) references User (id)
alter table Topic_User add index FK_dh24iofesyheni941ts21nrr6 (topicSubcriptions_id), add constraint FK_dh24iofesyheni941ts21nrr6 foreign key (topicSubcriptions_id) references Topic (id)



create table Journal (id integer not null auto_increment, file varchar(255), title varchar(255), topic_id integer, user_id integer, primary key (id))
create table Topic (id integer not null auto_increment, name varchar(255), primary key (id))
create table Topic_User (Topic_id integer not null, subscribers_id integer not null)
create table USER_SUBSCRIPTIONS (USERID integer not null, TOPICID integer not null)
create table User (id integer not null auto_increment, fullName varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id))
alter table Topic drop constraint UK_81868hw8bd2f5voe44ghl96kf
alter table Topic add constraint UK_81868hw8bd2f5voe44ghl96kf unique (name)
alter table User drop constraint UK_jreodf78a7pl5qidfh43axdfb
alter table User add constraint UK_jreodf78a7pl5qidfh43axdfb unique (username)
alter table Journal add index FK_ey03obrljssaybwjqxq1g9m7g (topic_id), add constraint FK_ey03obrljssaybwjqxq1g9m7g foreign key (topic_id) references Topic (id)
alter table Journal add index FK_6alfh5p41iuixb7n9nthhu8cf (user_id), add constraint FK_6alfh5p41iuixb7n9nthhu8cf foreign key (user_id) references User (id)
alter table Topic_User add index FK_qw225nxbsvnnakgy2y1cl6wy2 (subscribers_id), add constraint FK_qw225nxbsvnnakgy2y1cl6wy2 foreign key (subscribers_id) references User (id)
alter table Topic_User add index FK_31ucfgj2hvk12261dobo9t4sk (Topic_id), add constraint FK_31ucfgj2hvk12261dobo9t4sk foreign key (Topic_id) references Topic (id)
alter table USER_SUBSCRIPTIONS add index FK_krhg0df6usxhnqmkfod7p1ftf (TOPICID), add constraint FK_krhg0df6usxhnqmkfod7p1ftf foreign key (TOPICID) references Topic (id)
alter table USER_SUBSCRIPTIONS add index FK_rbysoceo1odidxxt5752hbcwf (USERID), add constraint FK_rbysoceo1odidxxt5752hbcwf foreign key (USERID) references User (id)


--Topics
insert into topic (name) values ("Medical"),("Biology"),("Cardiology"),("Endocrinology"),("Infectious Diseases"),("Genetics"),("Microbiology"),("Neurology"),("Oncology"),("Physiology");

----+---------------------+
 id | name                |
----+---------------------+
  1 | Medical             |
  2 | Biology             |
  3 | Cardiology          |
  4 | Endocrinology       |
  5 | Infectious Diseases |
  6 | Genetics            |
  7 | Microbiology        |
  8 | Neurology           |
  9 | Oncology            |
 10 | Physiology          |
----+---------------------+
    
--Users
insert into user (fullname,password,role,username) values 
    ("Alex Job","123abc","subscriber","alex"),
    ("Felex Job","123abc","subscriber","felex"),
    ("Alex Peter","123abc","subscriber","alexp"),
    ("Job Peter","123abc","subscriber","jobp"),
    ("Peter Matt","123abc","publisher","peter"),
    ("Philip Peter","123abc","publisher","phil");
    
 +----+--------------+----------+------------+----------+
| id | fullName     | password | role       | username |
+----+--------------+----------+------------+----------+
|  1 | Alex Job     | 123abc   | subscriber | alex     |
|  2 | Felex Job    | 123abc   | subscriber | felex    |
|  3 | Alex Peter   | 123abc   | subscriber | alexp    |
|  4 | Job Peter    | 123abc   | subscriber | jobp     |
|  5 | Peter Matt   | 123abc   | publisher  | peter    |
|  6 | Philip Peter | 123abc   | publisher  | phil     |
+----+--------------+----------+------------+----------+

--Journals
insert into journal (file,title,topic_id,user_id) values
    ("abc1.pdf","Cold Spring Harbor Perspectives in Medicine",1,5),
    ("abc2.pdf","BMC Medicine",1,5),
    ("abc3.pdf","Briefings in Bioinformatics",2,5),
    ("abc4.pdf","JACC - Cardiovascular Interventions",3,6),
    ("abc5.pdf","International Journal of Obesity and Related Metabolic Disorders",4,6),
    ("abc6.pdf","Emerging Infectious Diseases",5,6);
    

+----+----------+------------------------------------------------------------------+----------+---------+
| id | file     | title   | topic_id | user_id |
+----+----------+------------------------------------------------------------------+----------+---------+
|  1 | abc1.pdf | Cold Spring Harbor Perspectives in Medicine                       |        1 |       5 |
|  2 | abc2.pdf | BMC Medicine                                                      |        1 |       5 |
|  3 | abc3.pdf | Briefings in Bioinformatics                                       |        2 |       5 |
|  4 | abc4.pdf | JACC - Cardiovascular Interventions                               |        3 |       6 |
|  5 | abc5.pdf | International Journal of Obesity and Related Metabolic Disorders  |        4 |       6 |
|  6 | abc6.pdf | Emerging Infectious Diseases                                      |        5 |       6 |
+----+----------+------------------------------------------------------------------+----------+---------+
    


--USER_SUBSCRIPTIONS
insert into USER_SUBSCRIPTIONS (USERID,TOPICID) values
    (1,1),(1,2),(1,3),(2,3),(2,5),(3,1),(4,3);

Delete from  user_topic;
Delete from  journal;
Delete from  topic;
Delete from  user;


 
 
