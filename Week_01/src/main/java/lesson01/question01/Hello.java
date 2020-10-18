package lesson01.question01;

public class Hello {
    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.sum());
        System.out.println(hello.loop(10));
    }

    public int sum(){
        int num1 = 1;
        int num2 = 2;
        return (num1+num2)*4;
    }

    /**
     * 计算1+2+3+..+10
     * @return
     */
    public int loop(int par1){
        int total = 0;

        if(par1 < 10){
            return 0;
        }

        for (int i = 0 ; i<=par1 ; i++) {
            total = total + i;
        }

        return total;
    }

}
