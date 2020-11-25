create table if not exists goods_info(
    goods_id int(11) not null auto_increment comment '商品id',
    goods_name varchar(20) not null comment '商品名称',
    price decimal(18,2) comment '价格',
    primary key(goods_id)
) comment '商品信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;