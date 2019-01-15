package app.hapo.car.freight.common.util;

import java.util.Random;

/**
 * freight
 * Class: GenerateKey
 * Created by hapo on 2019-01-15.
 * Description:
 */
public class GenerateKey {

    public static String generateRandomKey(int size){
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do {
            num = ran.nextInt(75)+48;
            if((num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122)) {
                sb.append((char)num);
            }else {
                continue;
            }
        } while (sb.length() < 50);

        return sb.toString();
    }

}
