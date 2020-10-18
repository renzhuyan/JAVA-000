package lesson01.question02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义ClassLoader解析
 * 1、获取bytes（可自定义加密规则，获取的时候解密获取，安全性高）
 * 2、根据类名、bytes获取到class对象
 * 3、反射调用执行
 */
public class HelloClassloader extends ClassLoader{
    public static final String[] MODEL = {"r","rw","rws","rwd"};
    public static final String FILE_NAME = "Hello.xlass";
    public static final String CLASS_NAME = "Hello";
    public static final String METHOD_NAME = "hello";

    public static void main(String[] args) throws Exception {
        HelloClassloader loader = new HelloClassloader();
        Class helloClass = loader.findClass(CLASS_NAME);
        Object hello = helloClass.newInstance();
        Method method = helloClass.getDeclaredMethod(METHOD_NAME);
        method.invoke(hello);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = getXlassBytes();
        bytes = converBytes(bytes);
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getXlassBytes() {
        String name = this.getClass().getClassLoader().getResource(FILE_NAME).getPath();
        try {
            RandomAccessFile raf = new RandomAccessFile(name, MODEL[0]);
            byte[] bytes = new byte[(int) raf.length()];
            raf.readFully(bytes);
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] converBytes(byte[] bytes){

        byte[] converBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            converBytes[i] = (byte)(255 - bytes[i]);
        }

        return converBytes;
    }

}