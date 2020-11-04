package lesson05.server;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,9999);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
