package com.mvc.rest.logging;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ObjectMessage;

public class CCLogEventFactory implements LogEventFactory {
	
	public static final Charset UTF8 = Charset.forName("UTF-8");
    // 12 to 19 digits words.
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\b[0-9]{12,19}\\b)");
    private static final String NUMBER_MASK = "+++++++++++++++";

    // locates CVV2>123</ or <cvvCode>123</cvvCode> or CVV2>1234</ or <cvvCode>1234</cvvCode> occurrences.
    private static final Pattern CVV2_PATTERN = Pattern.compile("(\\<cvvCode\\>[0-9]{3,4}\\<\\/cvvCode\\>)|(CVV2\\>[0-9]{3,4}\\<\\/)");
    // (CVV2\\>[0-9]{3,4}\\<\\/)
    private static final String CVV2_MASK = "+++";

	@Override
	public LogEvent createEvent(String loggerName, Marker marker, String fqcn,	Level level, Message message,

			List<Property> properties, Throwable t) {

		{ // In my case i wanted "log.error(new Exception(msg))"

			// to pass the exception properly to the event,

			// as if "log.error(msg, new Exception(msg))" was called.

			if (t == null && message instanceof ObjectMessage) {

				ObjectMessage msg = (ObjectMessage) message;	

				t = msg.getThrowable();
				
				
				String logmessage = message.getFormat();
				StringBuffer maskedMessage = new StringBuffer(logmessage.length());
		        maskedMessage.append(message);

		        boolean matched = false;

		        matched = maskCreditCardNumber(logmessage, maskedMessage, matched);

		        matched = maskCvv2(logmessage, maskedMessage, matched);
				
				
		        message = new ObjectMessage(maskedMessage.toString());
			}

		}
		

		
		//-DLog4jLogEventFactory=com.mvc.rest.logging.CCLogEventFactory

		return new Log4jLogEvent(loggerName, marker, fqcn, level, message,	properties, t);

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
