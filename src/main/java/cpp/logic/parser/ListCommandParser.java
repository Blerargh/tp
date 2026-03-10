package cpp.logic.parser;

import java.util.Arrays;

import cpp.logic.Messages;
import cpp.logic.commands.ListCommand;
import cpp.logic.parser.exceptions.ParseException;
import cpp.model.contact.ContactNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    @Override
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || trimmedArgs.equals("contacts")) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListCommand(new ContactNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
