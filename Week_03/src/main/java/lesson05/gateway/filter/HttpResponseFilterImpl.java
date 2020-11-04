package lesson05.gateway.filter;

import io.netty.handler.codec.http.HttpResponse;

public class HttpResponseFilterImpl implements HttpResponseFilter{
    static final FilterSingleton filterSingleton = FilterSingleton.getInstance();
    @Override
    public void filter(HttpResponse response) {

    }
}
