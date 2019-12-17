package cn.king.NativeTools;

import java.util.Random;

public class GenerateDateTools {



    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static int Max = 90;
    private static int Min = 65;


    /**
     * 生成android ID
     *
     * @return
     */
    public static String CreateRandomAndroidID() {
        StringBuilder builder = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < 16; i++) {
            builder.append(hex[ran.nextInt(hex.length - 1)]);
        }

        return builder.toString();

    }

    /**
     * 随机生成邮箱地址
     *
     * @param Suffix 邮箱地址后缀
     * @return
     */
    public static String CreateRandomEmailAddress(String Suffix) {
        Random ran = new Random();
        int Len = ran.nextInt(4) + 6;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < Len; i++) {
            int index;
            switch (ran.nextInt(2)) {
                case 0:
                    index = ran.nextInt(Max - Min) + 65;
                    break;
                case 1:
                    index = ran.nextInt(57 - 49) + 49;
                    break;
                case 2:
                    index = ran.nextInt(122 - 97) + 97;
                    break;
                default:
                    index = ran.nextInt(Max - Min) + 49;
                    break;
            }
            builder.append((char) index);
        }
        builder.append("@");
        builder.append(Suffix);
        return builder.toString();
    }

    /**
     * 随机生成用户名
     * @return
     */
    public static String CreateRandomUserName()
    {
        Random ran = new Random();
        int Len = ran.nextInt(2) + 6;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Len; i++) {
            int index;
            switch (ran.nextInt(3)) {
                case 0:
                    index = ran.nextInt(Max - Min) + 65;
                    break;
                case 1:
                    index = ran.nextInt(57 - 49) + 49;
                    break;
                case 2:
                    index = ran.nextInt(122 - 97) + 97;
                    break;
                default:
                    index = ran.nextInt(Max - Min) + 49;
                    break;
            }
            builder.append((char) index);
        }
        return builder.toString();


    }

    /**
     * 随机生成指定长度数字
     * @param Len 需要指定生成的长度
     * @return
     */
    public static String CreateRandomDigital(int Len)
    {
        StringBuilder builder = new StringBuilder();
        Random ran = new Random();
        for (int i = 0 ; i<Len ;i++)
        {
            builder.append(ran.nextInt(10));

        }
        return builder.toString();
    }

    /**
     * 生成指定长度十六进制传
     * @param Len 需要生成的长度
     * @return
     */
    public static String CreateRandomHexStr(int Len) {
        StringBuilder builder = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < Len; i++) {
            builder.append(hex[ran.nextInt(hex.length - 1)]);
        }
        return builder.toString();

    }



}
