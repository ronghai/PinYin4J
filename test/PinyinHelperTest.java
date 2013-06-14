

import junit.framework.TestCase;
import com.ecbeta.pinyin4j.PinyinHelper; 
import com.ecbeta.pinyin4j.format.HanyuPinyinOutputFormat;
  

public class PinyinHelperTest extends TestCase
{

    public void testToHanyuPinyinStringArray()
    {

        // any input of non-Chinese characters will return null
        {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                assertNull(PinyinHelper.toHanyuPinyinStringArray('A', defaultFormat));
                assertNull(PinyinHelper.toHanyuPinyinStringArray('z', defaultFormat));
                assertNull(PinyinHelper.toHanyuPinyinStringArray(',', defaultFormat));
                assertNull(PinyinHelper.toHanyuPinyinStringArray('。', defaultFormat));
             
        }

        // Chinese characters
        // single pronounciation
        {
               HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] { "li3" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('李', defaultFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }

        }
        {
             HanyuPinyinOutputFormat upperCaseFormat = new HanyuPinyinOutputFormat();
                upperCaseFormat.setCaseType(HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_UPPERCASE);

                String[] expectedPinyinArray = new String[] { "LI3" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('李', upperCaseFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }

        }
        {
            try
            {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] { "lu:3" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('吕', defaultFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        {
            try
            {
                HanyuPinyinOutputFormat vCharFormat = new HanyuPinyinOutputFormat();
                vCharFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V);

                String[] expectedPinyinArray = new String[] { "lv3" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('吕', vCharFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        // multiple pronounciations
        {
            try
            {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] { "jian1", "jian4" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('间', defaultFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }
            } catch( Exception e)
            {
                e.printStackTrace();
            }
        }

        {
            try
            {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] { "hao3", "hao4" };
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('好', defaultFormat);

                assertEquals(expectedPinyinArray.length, pinyinArray.length);

                for (int i = 0; i < expectedPinyinArray.length; i++)
                {
                    assertEquals(expectedPinyinArray[i], pinyinArray[i]);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * test for combination of output formats
     */
    public void testOutputCombination()
    {
        try
        {
            HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

            // fix case type to lowercase firstly, change VChar and Tone
            // combination
            outputFormat.setCaseType(HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_LOWERCASE);

            // WITH_U_AND_COLON and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("lu:3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("lv3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("lü3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // // WITH_U_AND_COLON and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);

            assertEquals("lu:", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);

            assertEquals("lv", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);


            System.out.println(PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            assertEquals("lü", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_AND_COLON and WITH_TONE_MARK is forbidden

            // WITH_V and WITH_TONE_MARK is forbidden

            // WITH_U_UNICODE and WITH_TONE_MARK
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_MARK);

            assertEquals("lǚ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // fix case type to UPPERCASE, change VChar and Tone
            // combination
            outputFormat.setCaseType(HanyuPinyinOutputFormat.HANYU_PINYIN_CASE_TYPE_UPPERCASE);

            // WITH_U_AND_COLON and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("LU:3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("LV3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER);

            assertEquals("LÜ3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // // WITH_U_AND_COLON and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);

            assertEquals("LU:", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_V);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);

            assertEquals("LV", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE);

            assertEquals("LÜ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_AND_COLON and WITH_TONE_MARK is forbidden

            // WITH_V and WITH_TONE_MARK is forbidden

            // WITH_U_UNICODE and WITH_TONE_MARK
            outputFormat.setVCharType(HanyuPinyinOutputFormat.HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinOutputFormat.HANYU_PINYIN_TONE_TYPE_WITH_TONE_MARK);

            assertEquals("LǙ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
