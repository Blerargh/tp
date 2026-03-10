package cpp.logic.parser;

import cpp.logic.commands.ListCommand;
import cpp.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    @Override
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("contacts")) {
            return new ListCommand("contacts");
        } else if (trimmedArgs.equals("assignments")) {
            return new ListCommand("assignments");
        } else if (trimmedArgs.equals("classes")) {
            return new ListCommand("classes");
        } else if (trimmedArgs.isEmpty()) {
            throw new ParseException(ListCommand.MESSAGE_TAB_EMPTY);
        } else {
            throw new ParseException(ListCommand.MESSAGE_TAB_INVALID);
        }
    }
}
