package study;

import study.common.MDStringUtil;

public class test {
    public static void main(String[] args) {
        String md5 = null;
        try {
            md5 = new MDStringUtil().getBit32("123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(md5);
    }
}
