import org.junit.jupiter.api.Test;

import java.util.Calendar;
public class test {

        public static void main(String[] args) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH )+1;

            System.out.println(year + " 年 " + month + " 月");

        }

        @Test
        public void test(){
            int x = 9 / 3 * (1 + 2);
            System.out.println(x);
        }
    }
