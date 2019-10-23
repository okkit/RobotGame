package utils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class ObjectSizeInterpreter {
	/**
	 * @param line   is a non-null, package-qualified name of a class.
	 * @param result is a non-null, empty List which acts as an "out" parameter;
	 *               when returned, result must contain a non-null, non-empty List
	 *               containing a description of the size of the object.
	 *
	 * @return true only if the user has requested to quit the Interpreter.
	 * @exception IllegalArgumentException if a param does not comply.
	 */
	public boolean parseInput(String line, final List<Object> result) {
		Objects.requireNonNull(line);
		Objects.requireNonNull(result);
		if (!result.isEmpty()) {
			throw new IllegalArgumentException("Result param must be empty.");
		}

		boolean hasRequestedQuit = line.trim().equalsIgnoreCase(QUIT) || line.trim().equalsIgnoreCase(EXIT);

		if (hasRequestedQuit) {
			// display only a blank line
			result.add(NEW_LINE);
		} else {
			try {
				Class<?> theClass = Class.forName(line);
				ObjectSizer sizer = new ObjectSizer();
				long size = sizer.getObjectSize(theClass);
				if (size > 0) {
					Object[] insertedData = { theClass, Long.valueOf(size) };
					MessageFormat sizeMessage = new MessageFormat(PATTERN);
					String message = sizeMessage.format(insertedData);
					result.add(message);
					result.add(NEW_LINE);
				}
				result.add(DEFAULT_PROMPT);
			} catch (ClassNotFoundException ex) {
				// recover by asking the user for corrected input
				result.clear();
				result.add(ERROR_PROMPT);
			}
		}

		if (result.isEmpty()) {
			throw new IllegalStateException("Result must be non-empty.");
		}
		return hasRequestedQuit;
	}

	/**
	 * Return the text to be displayed upon start-up of the Interpreter.
	 */
	public String getHelloPrompt() {
		return HELLO_PROMPT;
	}

	// PRIVATE
	private static final String HELLO_PROMPT = "Please enter a class name>";
	private static final String DEFAULT_PROMPT = "Please enter a class name>";
	private static final String ERROR_PROMPT = "Invalid.  Example:\"java.lang.String\">";
	private static final String PATTERN = "Approximate size of {0} objects in bytes: {1}";
	private static final String QUIT = "quit";
	private static final String EXIT = "exit";
	private static final String NEW_LINE = System.getProperty("line.separator");
}
