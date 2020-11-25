create table if not exists order_info(
    order_id int(11) not null auto_increment comment '订单id',
    user_id int(11) not null comment '订单用户id',
    primary key(order_id)
) comment '订单信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;