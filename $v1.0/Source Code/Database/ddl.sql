create table
word_object(
w_id number(4,0) constraint pk_word_object primary key,
word varchar2(50) constraint nn_word not null,
syllable varchar2(75) constraint nn_syllable not null,
i_flag char(1) constraint nn_i_flag not null,
pv_flag char(1) constraint nn_pv_flag not null
);

alter table word_object
add constraint AK_word
unique(word);

alter table word_object
add constraint AK_syllable
unique(syllable);

alter table word_object 
add constraint ck_i_flag
check(i_flag in ('y','n'));

alter table word_object 
add constraint ck_pv_flag
check(pv_flag in ('y','n'));

create table part_of_speech_code
(
p_code numeric(2,0) constraint pk_part_of_speech_code primary key
,abbr varchar2(10) constraint nn_abbr not null
,full_form varchar2(20) constraint nn_full_form not null
);


create table part_of_speech
(
w_id number(4,0) constraint fk_part_of_speech_word_object references word_object on delete cascade
,p_code numeric(2,0) constraint fk_part_of_speech_code references part_of_speech_code on delete cascade
,c_seq number(2,0) constraint nn_c_seq not null
,context varchar2(50)
,constraint pk_part_of_speech primary key(w_id,p_code,c_seq)
);



create table context_meaning
(
w_id number(4,0)
,p_code number(2,0)
,c_seq number(2,0)
,m_seq number(2,0)
,meaning varchar2(200) not null
,example varchar2(200) not null
,constraint fk_part_of_speech foreign key(w_id,p_code,c_seq) references part_of_speech
,constraint pk_context_meaning primary key(w_id,p_code,c_seq,m_seq)
);

alter table context_meaning 
add constraint fk_part_of_speech 
foreign key(w_id,p_code,c_seq) references part_of_speech
on delete cascade;

create table accent
(
a_code char(1) constraint pk_accent primary key
,a_name varchar2(5) constraint nn_a_name not null
);

create table phonology
(
w_id number(4,0) constraint fk_phonology_word_object references word_object
,a_code char(1) constraint fk_accent references accent
,phonetics nvarchar2(75)
,pronunciation blob
,constraint pk_phonology primary key(w_id, a_code)
);

alter table phonology
drop constraint fk_phonology_word_object;

alter table phonology
drop constraint fk_accent;

alter table phonology
add constraint fk_accent
foreign key(a_code) references accent
on delete cascade;

alter table phonology
add constraint fk_phonology_word_object
foreign key(w_id) references word_object 
on delete cascade;

create table idiom
(
w_id number(4,0) constraint fk01 references word_object
,p_code number(2,0) constraint fk02 references part_of_speech_code
,i_seq number(2,0)
,form varchar2(50) constraint nn_form  not null
,meaning varchar2(200) constraint nn_meaning not null
,example varchar2(200) constraint nn_example not null
,constraint pk_idiom primary key(w_id,p_code,i_seq)
);

alter table idiom
drop constraint fk02;

alter table idiom
add constraint fk01
foreign key(w_id) references word_object
on delete cascade;

alter table idiom
add constraint fk02
foreign key(p_code) references part_of_speech_code
on delete cascade;

create table phrasal_verb
(
w_id number(4,0) constraint fk_phrasal_verb_word_object references word_object
,pv_seq number(2,0)
,form varchar2(50) constraint nn_pv_form not null
,meaning varchar2(200) constraint nn_pv_meaning not null
,example varchar2(200) constraint nn_pv_example not null
,constraint pk_phrasal_verb primary key(w_id, pv_seq)
);

alter table phrasal_verb
drop constraint fk_phrasal_verb_word_object;

alter table phrasal_verb
add constraint fk_phrasal_verb_word_object
foreign key(w_id) references word_object
on delete cascade;

drop table phrasal_verb;
drop table idiom;
drop table phonology;
drop table accent;
drop table context_meaning;
drop table part_of_speech;
drop table part_of_speech_code;
drop table word_object;

commit work;
delete table part_of_speech_code;