DROP TABLE IF EXISTS USERPHONES;
DROP TABLE IF EXISTS USERSNISUM;
CREATE TABLE USERSNISUM(
    id UUID default random_uuid() PRIMARY KEY ,
name VARCHAR(300) NOT NULL,
email VARCHAR(300) NOT NULL,
password VARCHAR(300) NOT NULL,
    created_date DATE NOT NULL,
    modified_date DATE NOT NULL,
last_login DATE NOT NULL,
token VARCHAR(300) NOT NULL,
isactive BOOLEAN NOT NULL
);


CREATE TABLE USERPHONES(
    id UUID default random_uuid() PRIMARY KEY ,
number BIGINT NOT NULL,
countrycode SMALLINT NOT NULL,
citycode SMALLINT NOT NULL,
USER_ID UUID NOT NULL,
foreign key (USER_ID ) references USERSNISUM(id)
);
