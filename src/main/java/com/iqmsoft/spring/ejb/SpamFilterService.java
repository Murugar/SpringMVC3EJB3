package com.iqmsoft.spring.ejb;

import javax.ejb.Local;


@Local
public interface SpamFilterService {

    /**
     * Checks a text for spam words.
     *
     * @param content the content to check.
     * @return true if the text contains spam or inadequate words, otherwise false.
     */
    boolean checkSpam(String content);
}
