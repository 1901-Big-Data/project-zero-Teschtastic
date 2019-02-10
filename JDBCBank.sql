create table users (
    user_id number(10) primary key,
    username varchar2(255) not null unique,
    pass varchar2(255) not null,
    is_admin number(1) not null
);

insert into users (user_id, username, pass, is_admin) values (1, 'root', 'p4ssw0rd', 1);
insert into accounts (account_id, type_account, username, account_bal) values (1, 1, 'root', 0.00);

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

create or replace procedure addUser(username IN varchar2, pass IN varchar2, acc_type in number,
                                    bal in number, is_admin number, user_id out number, account_id out number)
is
begin
    insert into users (username, pass, is_admin) 
    values(username, pass, is_admin);
    user_id := user_seq.currval;

    insert into accounts (type_account, username, account_bal)
    values(acc_type, username, bal);
    account_id := account_seq.currval;
    commit;
end;
/

CREATE OR REPLACE PROCEDURE login(users_username    in varchar2,    upassword   in varchar2, 
                                  passed            out number,     userid      out number,     isAdmin out number, 
                                  accbal            out number,     accid       out number,     acctype out number) 
as
    users_password varchar2(200);
    userId number(10);
    user_is_admin number(10);
    accId number(10);
    acc_type number(2);
    acc_bal number(10,2);
begin
    select  pass, 
            user_id, 
            is_admin,
            account_id,
            type_account,
            account_bal 
            
    into    users_password, 
            userId, 
            user_is_admin, 
            accId, 
            acc_type, 
            acc_bal
            
    from    users full join accounts
    on   username = users_username;
    
    if users_password = upassword then
        passed := 1;
        userid := userId;
        isAdmin := user_is_admin;
        accbal := acc_bal;
        accid := accId;
        acctype := acc_type;
    else
        passed := 0;
        userid := 0;
        isAdmin := 0;
        accbal := 0;
        accid := 0;
        acctype := 0;
    end if;
end;
/

CREATE OR REPLACE PROCEDURE deleteUser(user_name IN varchar2, pass_word IN varchar2)
IS
BEGIN
    delete  from users 
    where username = user_name and pass = pass_word;
    commit;
    delete from accounts
    where username = user_name;
    commit;
END;
/

drop table accounts;
drop table users;

create table accounts (
    account_id number(10) not null primary key,
    type_account number(2) not null,
    username varchar(255) not null,
    account_bal number(10,2),
    constraint acc_username 
        foreign key (username) 
        references users(username)
);

commit;