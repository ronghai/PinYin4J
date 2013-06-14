 

package com.ecbeta.pinyin4j.format;


final public class HanyuPinyinOutputFormat implements Cloneable
{

        /**
     * The option indicates that the output of 'ü' is "u:"
     */
    public static final int HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON = 1;

    /**
     * The option indicates that the output of 'ü' is "v"
     */
    public static final int HANYAN_PINYIN_V_CHAR_TYPE_WITH_V =2;

    /**
     * The option indicates that the output of 'ü' is "ü" in Unicode form
     */
    public static final int HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_UNICODE = 4;



    /**
     * The option indicates that hanyu pinyin is outputted with tone numbers
     */
    public static final int HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER = 8;

    /**
     * The option indicates that hanyu pinyin is outputted without tone numbers
     * or tone marks
     */
    public static final int HANYU_PINYIN_TONE_TYPE_WITHOUT_TONE = 16;

    /**
     * The option indicates that hanyu pinyin is outputted with tone marks
     */
    public static final int HANYU_PINYIN_TONE_TYPE_WITH_TONE_MARK = 32;


       /**
     * The option indicates that hanyu pinyin is outputted as uppercase letters
     */
    public static final int HANYU_PINYIN_CASE_TYPE_UPPERCASE =64;

    /**
     * The option indicates that hanyu pinyin is outputted as lowercase letters
     */
    public static final int HANYU_PINYIN_CASE_TYPE_LOWERCASE = 128;

     /**
     * The option indicates that hanyu pinyin is outputted as first letter uppercase
     */
    public static final int HANYU_PINYIN_CASE_TYPE_CAPITALIZE = 256;


    private int charType, caseType,toneType;


    public HanyuPinyinOutputFormat()
    {
        restoreDefault();
    }


    @Override
    public Object  clone() throws CloneNotSupportedException{
        return super.clone();
    }
    /**
     * Restore default variable values for this class
     * 
     * Default values are listed below:
     * 
     * <p>
     * HanyuPinyinVCharType := WITH_U_AND_COLON <br>
     * HanyuPinyinCaseType := LOWERCASE <br>
     * HanyuPinyinToneType := WITH_TONE_NUMBER <br>
     */
    public void restoreDefault()
    {
        this.charType=HANYAN_PINYIN_V_CHAR_TYPE_WITH_U_AND_COLON;
        this.caseType=HANYU_PINYIN_CASE_TYPE_LOWERCASE;
        this.toneType=HANYU_PINYIN_TONE_TYPE_WITH_TONE_NUMBER;
    }





      /**
     * Returns the output cases of Hanyu Pinyin characters
     *
     * @see HanyuPinyinCaseType
     */
    public int getCaseType()
    {
        return caseType;
    }

    /**
     * Define the output cases of Hanyu Pinyin characters
     *
     * @param caseType
     *            the output cases of Hanyu Pinyin characters
     *
     * @see HanyuPinyinCaseType
     */
    public void setCaseType(int caseType)
    {
        this.caseType = caseType;
    }

    /**
     * Returns the output format of Chinese tones
     *
     * @see HanyuPinyinToneType
     */
    public int getToneType()
    {
        return toneType;
    }

    /**
     * Define the output format of Chinese tones
     *
     * @param toneType
     *            the output format of Chinese tones
     *
     * @see HanyuPinyinToneType
     */
    public void setToneType(int toneType)
    {
        this.toneType = toneType;
    }

    /**
     * Returns output format of character 'ü'
     *
     * @see HanyuPinyinVCharType
     */
    public int getVCharType()
    {
        return  this.charType;
    }

    /**
     * Define the output format of character 'ü'
     *
     * @param charType
     *            the output format of character 'ü'
     *
     * @see HanyuPinyinVCharType
     */
    public void setVCharType(int charType)
    {
        this.charType = charType;
    }

     public int getCharType()
    {
        return  this.charType;
    }
     public void setCharType(int charType)
    {
        this.charType = charType;
    }
}
