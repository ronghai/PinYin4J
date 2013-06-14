
package com.ecbeta.pinyin4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ChineseToPinyinResource
{
    /**
     * A hash table contains <Unicode, HanyuPinyin> pairs
     */
    private Properties unicodeToHanyuPinyinTable = null;

    /**
     * @param unicodeToHanyuPinyinTable
     *            The unicodeToHanyuPinyinTable to set.
     */
    private void setUnicodeToHanyuPinyinTable(
            Properties unicodeToHanyuPinyinTable)
    {
        this.unicodeToHanyuPinyinTable = unicodeToHanyuPinyinTable;
    }

    /**
     * @return Returns the unicodeToHanyuPinyinTable.
     */
    private Properties getUnicodeToHanyuPinyinTable()
    {
        return unicodeToHanyuPinyinTable;
    }

    /**
     * Private constructor as part of the singleton pattern.
     */
    private ChineseToPinyinResource()
    {
        initializeResource();
    }

    /**
     * Initialize a hash-table contains <Unicode, HanyuPinyin> pairs
     */
    private void initializeResource()
    {
        try
        {
            final String resourceName = "/com/ecbeta/dict/unicode2hanyupinyin.dict";

            setUnicodeToHanyuPinyinTable(new Properties());
            getUnicodeToHanyuPinyinTable().load(ResourceHelper.getResourceInputStream(resourceName));

        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
 


    /**
     * Get the unformatted Hanyu Pinyin representations of the given Chinese
     * character in array format.
     * 
     * @param ch
     *            given Chinese character in Unicode
     * @return The Hanyu Pinyin strings of the given Chinese character in array
     *         format; return null if there is no corresponding Pinyin string.
     */
    String[] getHanyuPinyinStringArray(char ch)
    {
        String pinyinRecord = getHanyuPinyinRecordFromChar(ch);

        if (null != pinyinRecord)
        {
            int indexOfLeftBracket = pinyinRecord.indexOf(Field.LEFT_BRACKET);
            int indexOfRightBracket = pinyinRecord.lastIndexOf(Field.RIGHT_BRACKET);

            String stripedString = pinyinRecord.substring(indexOfLeftBracket
                    + Field.LEFT_BRACKET.length(), indexOfRightBracket);

            return stripedString.split(Field.COMMA);

        } else
            return null; // no record found or mal-formatted record
    }

    private boolean isValidRecord(String record)
    {
        final String noneStr = "(none0)";

        if ((null != record) && !record.equals(noneStr)
                && record.startsWith(Field.LEFT_BRACKET)
                && record.endsWith(Field.RIGHT_BRACKET))
        {
            return true;
        } else
            return false;
    }

    private String getHanyuPinyinRecordFromChar(char ch)
    {

        int codePointOfChar = ch;

        String codepointHexStr = Integer.toHexString(codePointOfChar).toUpperCase();

        // fetch from hashtable
        String foundRecord = getUnicodeToHanyuPinyinTable().getProperty(codepointHexStr);

        return isValidRecord(foundRecord) ? foundRecord : null;
    }


    static ChineseToPinyinResource getInstance()
    {
        return ChineseToPinyinResourceHolder.theInstance;
    }

    private static class ChineseToPinyinResourceHolder
    {
        static final ChineseToPinyinResource theInstance = new ChineseToPinyinResource();
    }


    class Field
    {
        static final String LEFT_BRACKET = "(";

        static final String RIGHT_BRACKET = ")";

        static final String COMMA = ",";
    }
}
