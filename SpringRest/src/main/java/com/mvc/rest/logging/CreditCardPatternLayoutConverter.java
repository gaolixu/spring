package com.mvc.rest.logging;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.message.Message;


@Plugin(name = "CreditCardPatternLayoutConverter", category ="Converter")
@ConverterKeys({"creditcard"})
public class CreditCardPatternLayoutConverter extends LogEventPatternConverter {
	public static final Charset UTF8 = Charset.forName("UTF-8");
    // 12 to 19 digits words.
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\b[0-9]{12,19}\\b)");
    private static final String NUMBER_MASK = "+++++++++++++++";

    // locates CVV2>123</ or <cvvCode>123</cvvCode> or CVV2>1234</ or <cvvCode>1234</cvvCode> occurrences.
    private static final Pattern CVV2_PATTERN = Pattern.compile("(\\<cvvCode\\>[0-9]{3,4}\\<\\/cvvCode\\>)|(CVV2\\>[0-9]{3,4}\\<\\/)");
    // (CVV2\\>[0-9]{3,4}\\<\\/)
    private static final String CVV2_MASK = "+++";

    public CreditCardPatternLayoutConverter(String[] options) {
        super("creditcard","creditcard");
    }

    
    public static CreditCardPatternLayoutConverter newInstance(final String[] options) {

        return new CreditCardPatternLayoutConverter (options);

}
    
    @Override
	public void format(LogEvent event, StringBuilder toAppendTo) {
	
    	String message = event.getMessage().getFormat();
           
            StringBuffer maskedMessage = new StringBuffer(message.length());
            maskedMessage.append(message);

            boolean matched = false;

            matched = maskCreditCardNumber(message, maskedMessage, matched);

            matched = maskCvv2(message, maskedMessage, matched);

            // Create a new LoggingEvent with the masked message if we matched a credit card number in it.
           /* if (matched == true) {
                @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
                Throwable throwable = event.getThrowableInformation() != null ? event.getThrowableInformation().getThrowable() : null;

                LoggingEvent maskedEvent = new LoggingEvent(
                        event.fqnOfCategoryClass,
                        Logger.getLogger(event.getLoggerName()),
                        event.timeStamp,
                        event.getLevel(),
                        maskedMessage,
                        throwable);

                return super.format(maskedEvent);
            }*/
            toAppendTo.append(maskedMessage.toString());

           // String REGEX_CREDIT_CARD = "([0-9]{6})[0-9]{0,9}([0-9]{4})";

           // toAppendTo.append(message.replaceAll(REGEX_CREDIT_CARD, "XXXX XXXX XXXX XXXX"));
    }

    private boolean maskCreditCardNumber(String message, StringBuffer maskedMessage, boolean matched) {
        Matcher numberMatcher = NUMBER_PATTERN.matcher(message);
        while (numberMatcher.find()) {
            matched = true;

            // Get matched string metadata
            int startPos = numberMatcher.start();
            int endPos = numberMatcher.end();
            int creditCardLength = endPos - startPos;
            String creditCardNumber = numberMatcher.group();

            // Build the masked credit card value
            // Trim the MASK to the credit card length - last 4 digits to which we'll append the last 4 digits.
            String maskedCreditCard = NUMBER_MASK.substring(0, creditCardLength - 4) + creditCardNumber.substring(creditCardLength - 4);

            // Replace the credit card information
            maskedMessage.replace(startPos, endPos, maskedCreditCard.toString());
        }
        return matched;
    }

    private boolean maskCvv2(String message, StringBuffer maskedMessage, boolean matched) {
        Matcher cvv2Matcher = CVV2_PATTERN.matcher(message);
        while (cvv2Matcher.find()) {
            matched = true;

            // Get matched string metadata
            int startPos = cvv2Matcher.start();
            int endPos = cvv2Matcher.end();
            int cvv2FieldLength = endPos - startPos;
            String cvv2 = cvv2Matcher.group();

            // Build the masked cvv2 value
            String prefix = cvv2.substring(0, cvv2.indexOf('>') + 1);
            String suffix = cvv2.substring(cvv2.indexOf("</") - 1);
            String maskedCvv2 = prefix + CVV2_MASK.substring(0, (cvv2FieldLength - prefix.length() - suffix.length())) + suffix;

            // Replace the credit card information
            maskedMessage.replace(startPos, endPos, maskedCvv2.toString());
        }
        return matched;
    }

	

}