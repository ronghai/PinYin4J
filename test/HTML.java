
import com.ecbeta.pinyin4j.PinyinHelper;
import com.ecbeta.pinyin4j.format.HanyuPinyinOutputFormat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author L5M
 */
public class HTML {
    public static void main(String ar[]){
        HanyuPinyinOutputFormat upperCaseFormat = new HanyuPinyinOutputFormat();
        upperCaseFormat.setCaseType(HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_UPPERCASE);

        String test = "ab你好";
        StringBuffer sb = new StringBuffer();

        for(int i=0;i<test.length();i++){
              String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(test.charAt(i), upperCaseFormat);
              if(pinyinArray!=null &&pinyinArray.length>0){
                  sb.append(pinyinArray[0]);
              }else{
                  sb.append(test.substring(i,i+1));
              }
        }

        System.out.println(sb);
        return ;


    }
}
