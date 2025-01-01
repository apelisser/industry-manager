package com.apelisser.manager.core.i18n;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.nio.charset.Charset;
import java.util.Locale;

@Getter
@ToString
@EqualsAndHashCode
@ConfigurationProperties(prefix = "application.i18n")
public class I18NProperties {

    /**
     * Set an array of basenames, each following the basic ResourceBundle convention of not specifying file extension or language codes.
     * The resource location format is up to the specific MessageSource implementation.
     */
    private final String[] baseNames;

    /**
     * Specify a default Locale to fall back to, as an alternative to falling back to the system Locale.
     */
    private final Locale defaultLocale;

    /**
     * Set the default charset to use for parsing properties files.
     */
    private final Charset defaultEncoding;

    /**
     * Set whether to fall back to the system Locale if no files for a specific Locale were found.
     */
    private final boolean fallbackToSystemLocale;

    /**
     * Set whether to use the message code as default message instead of throwing a {@code NoSuchMessageException}.
     */
    private final boolean useCodeAsDefaultMessage;

    @ConstructorBinding
    public I18NProperties(String[] baseNames, Locale defaultLocale, Charset defaultEncoding,
            boolean fallbackToSystemLocale, boolean useCodeAsDefaultMessage) {
        this.baseNames = baseNames;
        this.defaultLocale = defaultLocale;
        this.defaultEncoding = defaultEncoding;
        this.fallbackToSystemLocale = fallbackToSystemLocale;
        this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
    }

}
