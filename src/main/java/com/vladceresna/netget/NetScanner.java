package com.vladceresna.netget;

import com.vladceresna.netget.pojos.SitesRecordInBase;
import com.vladceresna.netget.support.SL;
import org.jsoup.Jsoup;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

public class NetScanner {
    public static void scan(){
        String[] alphabeth = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        JedisPooled j = new JedisPooled();

        String rezurl = "";
        for (String letter0:alphabeth) {
            for (String letter1:alphabeth) {
                for (String letter2:alphabeth) {
                    for (String letter3:alphabeth) {
                        for (String letter4:alphabeth) {
                            for (String letter5:alphabeth) {
                                for (String letter6:alphabeth) {
                                    for (String letter7:alphabeth) {
                                        for (String letter8:alphabeth) {
                                            for (String letter9:alphabeth) {
                                                try {
                                                    rezurl = "https://" +
                                                            (letter0+letter1+
                                                                    letter2+
                                                                    letter3+
                                                                    letter4+
                                                                    letter5+
                                                                    letter6+
                                                                    letter7+
                                                                    letter8+
                                                                    letter9).trim()+".com/";
                                                    j.jsonSet("page:" + j.keys("page:*").size(), SL.OtJ(new SitesRecordInBase(Jsoup.connect(rezurl).get().title(),rezurl)));
                                                    System.out.println(((SitesRecordInBase)SL.JtO(j.jsonGet("page:"+j.keys("page:*").size()),SitesRecordInBase.class)).getTitle());
                                                } catch (JedisException ex) {
                                                    try {
                                                        new Jedis() {{
                                                            bgsave();
                                                            save();
                                                            bgsave();
                                                            save();
                                                        }};
                                                    }catch(Exception e){}
                                                }catch (Exception exc) {
                                                    System.out.println(exc.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    public static String setAt(String s, int index, String symbol){

        StringBuilder rezult = new StringBuilder();
        for (int i = 0;i<s.length();i++){
            if (i==index){
                rezult.append(symbol);
            }else {
                rezult.append(s.charAt(i));
            }
        }
        return rezult.toString();
    }
    */
}
