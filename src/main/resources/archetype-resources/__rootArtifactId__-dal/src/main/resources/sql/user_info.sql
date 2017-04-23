#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
create database if not exists `test`;
use `test`;
create table if not exists `user_info`(
`id` bigint auto_increment primary key,
`name` varchar(128) comment '名称',
`age` int comment '年龄',
unique key(`name`)
)engine=innodb default charset=utf8 auto_increment=1;

insert into `user_info`(`name`, `age`) values
('a', 11),
('b', 12),
('c', 13),
('d', 14);