package com.example.wordbook;
import java.io.UnsupportedEncodingException;


public  class Search {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20191201000361972";
    private static final String SECURITY_KEY = "TDyQIhkxwQkq2mzAd1Fm";

    public static String chaci(String str) throws UnsupportedEncodingException {
        final String string = str;

            TransApi api = new TransApi(APP_ID, SECURITY_KEY);

            String query = string;


            String jd = api.getTransResult(query, "auto", "zh");
            System.out.println(jd);
//        String str = jd.split(":")[4];
            System.out.println(jd.split(":")[5]);
            System.out.println(jd.split(":")[5].split("\"")[1]);
            System.out.println(convertUnicode(jd.split(":")[5].split("\"")[1]));
//        String res = str.split("\"")[1];
//        System.out.println(convertUnicode(res));
            return convertUnicode(jd.split(":")[5].split("\"")[1]);




}
    public static String convertUnicode(String ori){
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }



}
