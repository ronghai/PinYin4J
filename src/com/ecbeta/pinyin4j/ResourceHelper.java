 

package com.ecbeta.pinyin4j;

import java.io.BufferedInputStream;

/**
 * Helper class for file resources 
 * 
 */
class ResourceHelper
{
    /**
     * @param resourceName
     * @return resource (mainly file in file system or file in compressed
     *         package) as BufferedInputStream
     */
    static BufferedInputStream getResourceInputStream(String resourceName)
    {
        return new BufferedInputStream(ResourceHelper.class.getResourceAsStream(resourceName));
    }
}
