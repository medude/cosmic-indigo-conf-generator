package apis;

import apis.console.ConsoleType;
import apis.console.JavaConsole;
import apis.console.NullConsole;
import apis.errorHandle.ErrorHandleType;
import apis.errorHandle.JavaErrorHandler;
import apis.errorHandle.NullErrorHandler;

public class ApiHandler {
	public static void init() {
		consoles[0] = new JavaConsole();
		consoles[1] = new NullConsole();

		errorHandlers[0] = new JavaErrorHandler();
		errorHandlers[1] = new NullErrorHandler();
    }

    //////////////////////////////////
    // Error Handlers //
    //////////////////////////////////
    private static ErrorHandleType[] errorHandlers = new ErrorHandleType[2];

    public static ErrorHandleType getErrorHandler() {
		return errorHandlers[0];
    }

    //////////////////////////////////
    // Consoles //
    //////////////////////////////////
    private static ConsoleType[] consoles = new ConsoleType[2];

    public static ConsoleType getConsole() {
		return consoles[0];
    }
}
