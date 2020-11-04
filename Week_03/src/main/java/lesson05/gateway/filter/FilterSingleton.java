package lesson05.gateway.filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FilterSingleton {
    private enum EnumSingleton {
        /**
         * 懒汉枚举单例
         */
        INSTANCE;
        private FilterSingleton instance = null;

        private EnumSingleton(){
            instance = new FilterSingleton();
        }
        public FilterSingleton getSingleton(){
            return instance;
        }
    }

    public static FilterSingleton getInstance(){
        return EnumSingleton.INSTANCE.getSingleton();
    }

    /**
     * Request过滤操作链
     */
    List<HttpRequestFilter> requestFrontFilterList = new ArrayList<>();
    /**
     * Response过滤操作链
     */
    List<HttpResponseFilter> responseBackendFilters = new ArrayList<>();

    public void registerRequestFrontFilter(HttpRequestFilter requestFrontFilter) {
        this.requestFrontFilterList.add(requestFrontFilter);
    }

    public void registerResponseBackendFilter(HttpResponseFilter responseBackendFilter) {
        this.responseBackendFilters.add(responseBackendFilter);
    }

    public List<HttpRequestFilter> getRequestFrontFilterList() {
        return requestFrontFilterList;
    }

    public List<HttpResponseFilter> getResponseBackendFilters() {
        return responseBackendFilters;
    }
}
