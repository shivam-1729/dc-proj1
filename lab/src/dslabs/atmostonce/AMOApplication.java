package dslabs.atmostonce;

import dslabs.framework.Address;
import dslabs.framework.Application;
import dslabs.framework.Command;
import dslabs.framework.Result;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public final class AMOApplication<T extends Application> implements Application {
  @Getter @NonNull private final T application;

  // Your code here...
  private HashMap<Address, AMOResult> lastExecutedResult = new HashMap<>();

  @Override
  public AMOResult execute(Command command) {
    if (!(command instanceof AMOCommand amoCommand)) {
      throw new IllegalArgumentException();
    }

    // Your code here...
    if (alreadyExecuted(amoCommand)){
      return lastExecutedResult.get(amoCommand.address());
    }

    AMOResult result = new AMOResult(application.execute(amoCommand.command()), amoCommand.sequenceNum());
    lastExecutedResult.put(amoCommand.address(), result);
    return result;
  }

  public Result executeReadOnly(Command command) {
    if (!command.readOnly()) {
      throw new IllegalArgumentException();
    }

    if (command instanceof AMOCommand) {
      return execute(command);
    }

    return application.execute(command);
  }

  public boolean alreadyExecuted(AMOCommand amoCommand) {
    // Your code here...
    return (lastExecutedResult.get(amoCommand.address()) != null &&
              lastExecutedResult.get(amoCommand.address()).sequenceNum() >= amoCommand.sequenceNum());
  }
}
