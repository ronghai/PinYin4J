 
package com.ecbeta.pinyin4j;

import com.ecbeta.pinyin4j.format.HanyuPinyinOutputFormat;


/**
 * Contains logic to format given Pinyin string

 * 
 */
public class PinyinFormatter
{
    /**
     * @param pinyinStr
     *            unformatted Hanyu Pinyin string
     * @param outputFormat
     *            given format of Hanyu Pinyin
     * @return formatted Hanyu Pinyin string
     * @throws BadHanyuPinyinOutputFormatCombination
     */
  public  static String formatHanyuPinyin(String pinyinStr,    HanyuPinyinOutputFormat outputFormat)
         
    {

         int vcharType=outputFormat.getVCharType();

        if((HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_MARK==outputFormat.getToneType() ) ){
            vcharType = HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE;
         }
        if ((HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE==outputFormat.getToneType()  )  )
        {
            pinyinStr = pinyinStr.replaceAll("[1-5]", "");
        } else if (HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_MARK==outputFormat.getToneType() )
        {
            pinyinStr = pinyinStr.replaceAll("u:", "v");
            pinyinStr = convertToneNumber2ToneMark(pinyinStr);
        } 
        if ((HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V==vcharType ) )
        {
            pinyinStr = pinyinStr.replaceAll("u:", "v");
        } else if ((HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE==vcharType ))
        {
            pinyinStr = pinyinStr.replaceAll("u:", "ü");
        }

        if (HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_UPPERCASE ==outputFormat.getCaseType() )
        {
            pinyinStr = pinyinStr.toUpperCase();
            
        }else if ((HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_CAPITALIZE==outputFormat.getCaseType()  ) )
        {
            pinyinStr = capitalize(pinyinStr);
        }
        return pinyinStr;
    }

    /**
     * Convert tone numbers to tone marks using Unicode <br/><br/>
     * 
     * <b>Algorithm for determining location of tone mark</b><br/>
     * 
     * A simple algorithm for determining the vowel on which the tone mark
     * appears is as follows:<br/>
     * 
     * <ol>
     * <li>First, look for an "a" or an "e". If either vowel appears, it takes
     * the tone mark. There are no possible pinyin syllables that contain both
     * an "a" and an "e".
     * 
     * <li>If there is no "a" or "e", look for an "ou". If "ou" appears, then
     * the "o" takes the tone mark.
     * 
     * <li>If none of the above cases hold, then the last vowel in the syllable
     * takes the tone mark.
     * 
     * </ol>
     * 
     * @param pinyinStr
     *            the ascii represention with tone numbers
     * @return the unicode represention with tone marks
     */
    private static String convertToneNumber2ToneMark(final String pinyinStr)
    {
        String lowerCasePinyinStr = pinyinStr.toLowerCase();

        if (lowerCasePinyinStr.matches("[a-z]*[1-5]?"))
        {
            final char defautlCharValue = '$';
            final int defautlIndexValue = -1;

            char unmarkedVowel = defautlCharValue;
            int indexOfUnmarkedVowel = defautlIndexValue;

            final char charA = 'a';
            final char charE = 'e';
            final String ouStr = "ou";
            final String allUnmarkedVowelStr = "aeiouv";
            final String allMarkedVowelStr = "āáăàaēéĕèeīíĭìiōóŏòoūúŭùuǖǘǚǜü";

            if (lowerCasePinyinStr.matches("[a-z]*[1-5]"))
            {

                int tuneNumber = Character.getNumericValue(lowerCasePinyinStr.charAt(lowerCasePinyinStr.length() - 1));

                int indexOfA = lowerCasePinyinStr.indexOf(charA);
                int indexOfE = lowerCasePinyinStr.indexOf(charE);
                int ouIndex = lowerCasePinyinStr.indexOf(ouStr);

                if (-1 != indexOfA)
                {
                    indexOfUnmarkedVowel = indexOfA;
                    unmarkedVowel = charA;
                } else if (-1 != indexOfE)
                {
                    indexOfUnmarkedVowel = indexOfE;
                    unmarkedVowel = charE;
                } else if (-1 != ouIndex)
                {
                    indexOfUnmarkedVowel = ouIndex;
                    unmarkedVowel = ouStr.charAt(0);
                } else
                {
                    for (int i = lowerCasePinyinStr.length() - 1; i >= 0; i--)
                    {
                        if (String.valueOf(lowerCasePinyinStr.charAt(i)).matches("["
                                + allUnmarkedVowelStr + "]"))
                        {
                            indexOfUnmarkedVowel = i;
                            unmarkedVowel = lowerCasePinyinStr.charAt(i);
                            break;
                        }
                    }
                }

                if ((defautlCharValue != unmarkedVowel)
                        && (defautlIndexValue != indexOfUnmarkedVowel))
                {
                    int rowIndex = allUnmarkedVowelStr.indexOf(unmarkedVowel);
                    int columnIndex = tuneNumber - 1;

                    int vowelLocation = rowIndex * 5 + columnIndex;

                    char markedVowel = allMarkedVowelStr.charAt(vowelLocation);

                    StringBuffer resultBuffer = new StringBuffer();

                    resultBuffer.append(lowerCasePinyinStr.substring(0, indexOfUnmarkedVowel).replaceAll("v", "ü"));
                    resultBuffer.append(markedVowel);
                    resultBuffer.append(lowerCasePinyinStr.substring(indexOfUnmarkedVowel + 1, lowerCasePinyinStr.length() - 1).replaceAll("v", "ü"));

                    return resultBuffer.toString();

                } else
                // error happens in the procedure of locating vowel
                {
                    return lowerCasePinyinStr;
                }
            } else
            // input string has no any tune number
            {
                // only replace v with ü (umlat) character
                return lowerCasePinyinStr.replaceAll("v", "ü");
            }
        } else
        // bad format
        {
            return lowerCasePinyinStr;
        }


    }

     public static String capitalize(String s) {
        char ch[];
      ch = s.toCharArray();
       if (ch[0] >= 'a' && ch[0] <= 'z') {
           ch[0] = (char) (ch[0] - 32);
      }
        String newString = new String(ch);
          return newString;
    }
}
