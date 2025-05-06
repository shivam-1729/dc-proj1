package dslabs.clientserver;

import dslabs.framework.Message;
import dslabs.atmostonce.AMOCommand;
import dslabs.atmostonce.AMOResult;
import lombok.Data;

@Data
class Request implements Message {
  // Your code here...
  private final AMOCommand amoCommand;
}

@Data
class Reply implements Message {
  // Your code here...
  private final AMOResult amoResult;
}
