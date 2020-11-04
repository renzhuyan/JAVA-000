package lesson05.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * 过滤器
 */
public class Filter {
    static final FilterSingleton filterSingleton = FilterSingleton.getInstance();

    static private void addRequestFilter(HttpRequestFilter requestFrontFilter) {
        filterSingleton.registerRequestFrontFilter(requestFrontFilter);
    }

    static private void addResponseFilter(HttpResponseFilter responseBackendFilter) {
        filterSingleton.registerResponseBackendFilter(responseBackendFilter);
    }

    /**
     * 在这个方法中添加Request的过滤操作类,在启动函数中进行调用
     */
    static public void initRequestFilter() {
        addRequestFilter(new HttpRequestFilterImpl());
    }

    /**
     * 在这个方法中添加Response的过滤操作类，在启动函数中进行调用
     */
    static public void initResponseFilter() {
        addResponseFilter(new HttpResponseFilterImpl());
    }

    /**
     * 遍历Request过滤操作链，对Request进行处理，在Server inbound接收到Request后进行调用
     * @param fullRequest
     */
    static public void requestProcess(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        for (HttpRequestFilter filter: filterSingleton.getRequestFrontFilterList()) {
            filter.filter(fullRequest , ctx);
        }
    }

    /**
     * 调用Response过滤操作链，对Response进行处理，在Server outbound发送Response前进行调用
     * @param response
     */
    static public void responseProcess(HttpResponse response) {
        for (HttpResponseFilter filter: filterSingleton.getResponseBackendFilters()) {
            filter.filter(response);
        }
    }
}
