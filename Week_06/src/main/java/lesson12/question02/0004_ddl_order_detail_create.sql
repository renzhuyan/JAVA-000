create table if not exists order_detail(
    id int(11) not null auto_increment comment '主键id',
    order_id int(11) not null  comment '订单id',
    goods_id int(11) not null comment '商品id',
    primary key(id)
) comment '订单明细表' ENGINE=InnoDB DEFAULT CHARSET=utf8;