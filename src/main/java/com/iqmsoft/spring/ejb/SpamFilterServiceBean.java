package com.iqmsoft.spring.ejb;

import javax.ejb.Stateless;


@Stateless
public class SpamFilterServiceBean implements SpamFilterService {

    /** Spam word list. */
    private static final String[] SPAM_WORDS = {"http", "sex", "porn", "buy"};

    @Override
    public boolean checkSpam(String content) {
        for (String spamWord : SPAM_WORDS) {
            if (content.contains(spamWord)) {
                return true;
            }
        }
        return false;
    }
}
