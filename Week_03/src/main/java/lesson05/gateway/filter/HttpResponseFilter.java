package lesson05.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import javax.xml.ws.Response;

public interface HttpResponseFilter {
    
     void filter(HttpResponse response);
    
}
