package lesson14.question02.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lesson14.question02.annotation.ReadDataSource;
import lesson14.question02.annotation.WriteDataSource;
import lesson14.question02.entity.OrderInfo;
import lesson14.question02.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoService extends ServiceImpl<OrderInfoMapper,OrderInfo> implements IService<OrderInfo> {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @WriteDataSource
    public void insertOrderInfo(){
        System.out.println("开始新增");
        orderInfoMapper.insert(OrderInfo.builder().OrderId(1).userId(1).build());
        System.out.println("新增成功");
    }

    @ReadDataSource
    public void query(){
        System.out.println("开始查询");
        orderInfoMapper.selectOne(OrderInfo.builder().OrderId(1).build());
        System.out.println("查询结束");
    }

}
