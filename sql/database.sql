drop schema if exists aa_project;
create schema aa_project;
use aa_project;

create table trader_info(
	username varchar(100) CHARACTER SET utf8 not null, # unique identifier
	name varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
	password varchar(100) not null,
	constraint trader_info_pk primary key (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO trader_info VALUES ('mary123', 'Mary Tan','123');
INSERT INTO trader_info VALUES ('john123', 'John Tan','123');
INSERT INTO trader_info VALUES ('andy123', 'Andy Tan','123');
INSERT INTO trader_info VALUES ('alice123', 'Alice Tan','123');
INSERT INTO trader_info VALUES ('ariel123', 'Ariel Tan','123');

create table stock_market(
	stockid int not null,
	stockname varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
	shortname varchar (200) CHARACTER SET utf8 not null, # only lower, upper and spaces allowed
    buyprice double not null,
	sellprice double not null,
	total_change double not null,
	percent_change double not null,
    tradetime timestamp not null, 
	constraint stock_market_pk primary key (stockid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO stock_market VALUES (1, 'DBS Group Holdings', 'DBS', 100.00, 105.00, 5.00, 0.05, '2018-02-02 18:00:00');
INSERT INTO stock_market VALUES (2, 'United Overseas Bank', 'UOB', 100.00, 105.00, 5.00, 0.05, '2018-02-02 18:00:00');
INSERT INTO stock_market VALUES (3, 'Overseas Chinese Banking Corp', 'OCBC', 100.00, 90.00, 10.00, 0.10, '2018-02-02 18:00:00');
INSERT INTO stock_market VALUES (4, 'Apple Inc', 'Apple', 100.00, 105.00, 5.00, 0.05, '2018-02-02 18:00:00');
INSERT INTO stock_market VALUES (5, 'Tesla Motors', 'Tesla', 100.00, 90.00, 5.00, 0.10, '2018-02-02 18:00:00');

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

INSERT INTO stock_trade VALUES ('mary123', 1, '2018-01-02 10:12:00', 'buy', 89.00, 20);
INSERT INTO stock_trade VALUES ('mary123', 1, '2018-01-03 10:12:00', 'sell', 100.00, 12);
INSERT INTO stock_trade VALUES ('mary123', 1, '2018-01-10 08:12:00', 'buy', 83.00, 10);
INSERT INTO stock_trade VALUES ('alice123', 2, '2018-01-04 10:12:00', 'buy', 89.00, 20);
INSERT INTO stock_trade VALUES ('alice123', 3, '2018-01-05 12:12:00', 'buy', 89.00, 20);
INSERT INTO stock_trade VALUES ('andy123', 5, '2018-01-08 09:12:00', 'buy', 15.00, 50);
INSERT INTO stock_trade VALUES ('andy123', 5, '2018-01-19 18:12:00', 'sell', 39.00, 20);
INSERT INTO stock_trade VALUES ('andy123', 5, '2018-01-20 10:12:00', 'sell', 38.00, 20);
INSERT INTO stock_trade VALUES ('ariel123', 4, '2018-01-30 21:12:00', 'buy', 89.00, 12);
INSERT INTO stock_trade VALUES ('john123', 3, '2018-01-28 10:12:00', 'buy', 55.00, 26);
