create table if not exists user_info(
    user_id int(11) not null auto_increment comment '用户id',
    user_name varchar(20) not null comment '用户名称',
    age int(11) comment '年龄',
    sex varchar(10) comment '性别',
    edu varchar(10) comment '学历',
    primary key(user_id)
) comment '用户信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;