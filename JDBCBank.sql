create table users (
    user_id number(10) primary key,
    username varchar2(255) not null unique,
    pass varchar2(255) not null,
    is_admin number(1) not null
);

drop table accounts;
drop table users;

create table accounts (
    account_id number(10) not null primary key,
    type_account varchar2(255) not null,
    acc_user varchar(255) not null,
    account_bal number(10,2),
    constraint acc_username 
        foreign key (acc_user) 
        references users(username)
);


insert into users (user_id, username, pass, is_admin) values (1, 'root', 'p4ssw0rd', 1);
insert into accounts (account_id, type_account, username, account_bal) values (1, 'admin', 'root', 0.00);

CREATE SEQUENCE user_seq
    start with 1
    increment by 1;
    
CREATE OR REPLACE TRIGGER user_trigger_id 
BEFORE INSERT ON users
FOR EACH ROW
BEGIN 
    IF :new.user_id IS NULL THEN
    SELECT user_seq.nextval INTO :new.user_id from dual;
    END IF;
END;    
/

drop sequence account_seq;
drop trigger account_trigger_id;

CREATE SEQUENCE account_seq
    start with 1
    increment by 1;

CREATE OR REPLACE TRIGGER account_trigger_id 
BEFORE INSERT ON accounts
FOR EACH ROW
BEGIN 
    IF :new.account_id IS NULL THEN
    SELECT account_seq.nextval INTO :new.account_id from dual;
    END IF;
END;    
/

create or replace procedure addUser(u_username IN varchar2, u_pass IN varchar2, isadmin in number, user_id out number)
is
begin
    insert into users (username, pass, is_admin) 
    values(u_username, u_pass, isadmin);
    user_id := user_seq.currval;
    commit;
end;
/

create or replace procedure addAccount(u_username in varchar2, bal in number, acc_type in varchar2, account_id out number)
is
begin
    insert into accounts (type_account, acc_user, account_bal)
    values(acc_type, u_username, bal);
    account_id := account_seq.currval;
    commit;
end;
/

create or replace procedure login(uusername    in varchar2,    upassword   in varchar2, 
                                  passed       out number,     userid      out number,     isAdmin out number) 
as
    u_password varchar2(200);
    user_Id number(10);
    user_is_admin number(10);
begin
    select  pass, 
            user_id, 
            is_admin
    into    u_password,
            user_Id,
            user_is_admin
    from    users
    where   username = uusername;

    if  u_password = upassword then
        passed := 1;
        userid := user_Id;
        isAdmin := user_is_admin;
    else
        passed := 0;
        userid := 0;
        isAdmin := 0;
    end if;
end;
/

create or replace procedure deleteUser(user_name in varchar2, pass_word in varchar2)
is
begin
    delete from users 
    where username = user_name and pass = pass_word;
    commit;
end;
/

create or replace procedure deleteAccount(user_name in varchar2, pass_word in varchar2, acc_id in number)
is
begin
    delete  from accounts 
    where acc_user = user_name and account_id = acc_id;
    commit;
end;
/

create or replace procedure viewBalance(user_name in varchar2, acc_type out varchar2, acc_bal out number)
as
    acctype varchar2(255);
    accbal number(10,2);
begin
    select type_account, account_bal
    into acctype, accbal
    from accounts
    where acc_user = user_name;
        
    acc_type := acctype;
    acc_bal := accbal;
end;
/

create or replace procedure changeBalance(user_name in varchar2, bal in number, acc_id in number)
as
begin
    update accounts 
    set account_bal = bal + (select account_bal 
                               from accounts 
                               where acc_user = user_name and account_id = acc_id)
    where acc_user = user_name and account_id = acc_id;
end;
/

create or replace procedure getAccount(user_name in varchar2, acc_id in number, bal out number)
as
begin
    select  account_bal
    into bal
    from accounts
    where acc_user = user_name and account_id = acc_id;
end;
/