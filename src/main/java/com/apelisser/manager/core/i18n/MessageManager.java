package com.apelisser.manager.core.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Utility class for retrieving localized messages.
 * <p>
 * This class is used for retrieving localized messages from a {@link MessageSource} using the {@link Locale} defined in the thread.
 * It is responsible for retrieving the message associated with a given code, and
 * for retrieving the message associated with a given {@link MessageSourceResolvable}.
 * <p>
 * The class is thread safe and is suitable for use in a multithreaded environment.
 */
@Component
public class MessageManager {

    private final MessageSource messageSource;

    public MessageManager(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Retrieves the message associated with the given code.
     *
     * @param code the code of the message to retrieve
     * @return the message associated with the given code
     */
    public String getMessage(String code) {
        return this.getMessage(code, (Object) null);
    }

    /**
     * Retrieves the message associated with the given code.
     *
     * @param code the code of the message to retrieve
     * @param args optional arguments to be interpolated into the message
     * @return the message associated with the given code
     */
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    /**
     * Retrieves the message corresponding to the given MessageSourceResolvable in
     * the current context locale.
     *
     * @param resolvable the MessageSourceResolvable object representing the message
     *                   to retrieve
     * @return the message string corresponding to the given MessageSourceResolvable
     * in the current context locale
     */
    public String getMessage(MessageSourceResolvable resolvable) {
        return messageSource.getMessage(resolvable, getLocale());
    }

    /**
     * Retrieves the locale from the current context.
     *
     * @return the locale from the current context
     */
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

}
