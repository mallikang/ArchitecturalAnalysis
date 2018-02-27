drop schema if exists aa_project;
create schema aa_project;
use aa_project;

create table trader_info(
	username varchar(100) CHARACTER SET utf8 not null, # unique identifier
	name varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
	password varchar(100) not null,
	constraint trader_info_pk primary key (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table stock_market(
	stockid int not null,
	stockname varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
	shortname varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
	price double not null,
	total_change double not null,
	percent_change double not null,
	constraint stock_market_pk primary key (stockid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table stock_trade(
	trader varchar(100) CHARACTER SET utf8 not null, # unique identifier
	stockid int not null,
	tradetime timestamp not null, #time of transaction
	tradetype varchar(100) not null, #buy or sell
	price double not null, #price of transaction
	quantity int not null, #quantity of stocks in transaction
	constraint stock_trade_pk primary key (trader, stockid, tradetime),
	constraint stock_trade_fk1 foreign key (trader) references trader_info(username),
	constraint stock_trade_fk2 foreign key (stockid) references stock_market(stockid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;