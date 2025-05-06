package dslabs.clientserver;

import dslabs.framework.Timer;
import lombok.Data;

@Data
final class ClientTimer implements Timer {
  // Your code here...
  static final int CLIENT_RETRY_MILLIS = 100;
  private final int sequenceNum;
}
