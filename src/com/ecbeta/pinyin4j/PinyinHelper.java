 

package com.ecbeta.pinyin4j;

import com.ecbeta.pinyin4j.format.HanyuPinyinOutputFormat;

 
public class PinyinHelper
{
    
     public static String[] toHanyuPinyinStringArray(char ch)
    {
        return getUnformattedHanyuPinyinStringArray(ch);
    }


     public static String[] toHanyuPinyinStringArray(char ch, HanyuPinyinOutputFormat outputFormat)
    {
        return getFormattedHanyuPinyinStringArray(ch, outputFormat);
    }


    public static String  toHanyuPinyinString(String hanzi, HanyuPinyinOutputFormat outputFormat)
    {
       StringBuffer sb = new StringBuffer();
       for(int i=0;i<hanzi.length();i++){
              String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(hanzi.charAt(i), outputFormat);
              if(pinyinArray!=null &&pinyinArray.length>0){
                  sb.append(pinyinArray[0]);
              }else{
                  sb.append(hanzi.substring(i,i+1));
              }
        }

        return sb.toString();
    }


     private static String[] getFormattedHanyuPinyinStringArray(char ch,
            HanyuPinyinOutputFormat outputFormat)
    {
        String[] pinyinStrArray = getUnformattedHanyuPinyinStringArray(ch);

        if (null != pinyinStrArray)
        {

            for (int i = 0; i < pinyinStrArray.length; i++)
            {
                pinyinStrArray[i] = PinyinFormatter.formatHanyuPinyin(pinyinStrArray[i], outputFormat);
            }

            return pinyinStrArray;

        } else
            return null;
    }

  

    private static String[] getUnformattedHanyuPinyinStringArray(char ch)
    {
        return ChineseToPinyinResource.getInstance().getHanyuPinyinStringArray(ch);
    }
 

    // ! Hidden constructor
    private PinyinHelper()
    {
    }
}
