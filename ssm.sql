--查看表
select * from all_tables where TABLE_NAME = 'SYS_USER' and OWNER='SSMDEMO'
--创建表
CREATE TABLE SYS_USER (  
  id NUMBER(11) NOT NULL,  
  user_name varchar(40) NOT NULL,  
  password varchar(255) NOT NULL,  
  age NUMBER(4) NOT NULL,  
  PRIMARY KEY (id)  
)
CREATE TABLE SYS_ROLE (  
  id NUMBER(11) NOT NULL,  
  role_name varchar(40) NOT NULL,
  IS_VALID NUMBER(1) NOT NULL,  
  PRIMARY KEY (id)  
)
CREATE TABLE SYS_USER_ROLE (  
  id NUMBER(11) NOT NULL,  
  role_ID NUMBER(11) NOT NULL,
  USER_ID NUMBER(11) NOT NULL,  
  PRIMARY KEY (id)  
)
--添加外键
alter table sys_user_role 
add constraint fk_role_id
foreign key(role_id ) references sys_role(id); 
alter table sys_user_role 
add constraint fk_user_id
foreign key(user_id ) references sys_user(id); 
--创建序列
CREATE SEQUENCE user_Sequence
 INCREMENT BY 1   -- 每次加几个  
     START WITH 1     -- 从1开始计数  （Next number）
     NOMAXVALUE       -- 不设置最大值  
     NOCYCLE          -- 一直累加，不循环
CREATE SEQUENCE role_Sequence
 INCREMENT BY 1   -- 每次加几个  
     START WITH 1     -- 从1开始计数  （Next number）
     NOMAXVALUE       -- 不设置最大值  
     NOCYCLE          -- 一直累加，不循环
CREATE SEQUENCE user_role_Sequence
 INCREMENT BY 1   -- 每次加几个  
     START WITH 1     -- 从1开始计数  （Next number）
     NOMAXVALUE       -- 不设置最大值  
     NOCYCLE          -- 一直累加，不循环
--建立一个自增触发器:
CREATE TRIGGER user_increase
  BEFORE insert ON SYS_USER
  FOR EACH ROW
begin
  select user_Sequence.nextval into :New.id from dual;
end;

CREATE TRIGGER role_increase
  BEFORE insert ON SYS_ROLE
  FOR EACH ROW
begin
  select role_Sequence.nextval into :New.id from dual;
end;

CREATE TRIGGER user_role_increase
  BEFORE insert ON SYS_USER_ROLE
  FOR EACH ROW
begin
  select user_role_Sequence.nextval into :New.id from dual;
end;
--插入数据
insert  into SYS_USER(user_name,password,age) values ('test','123456',24);
insert into SYS_ROLE(role_name,IS_VALID) values ('管理员',1);