package chat.server;

import chat.server.command.ChangeCommand;
import chat.server.command.Command;
import chat.server.command.ExitCommand;
import chat.server.command.JoinCommand;
import chat.server.command.MessageCommand;
import chat.server.command.UsersCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV3 implements CommandManager {

  private static final String DELIMITER = "\\|";
  private final SessionManager sessionManager;
  private final Map<String, Command> commands = new HashMap<>();

  public CommandManagerV3(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
    commands.put("/join", new JoinCommand(sessionManager));
    commands.put("/message", new MessageCommand(sessionManager));
    commands.put("/change", new ChangeCommand(sessionManager));
    commands.put("/users", new UsersCommand(sessionManager));
    commands.put("/exit", new ExitCommand());
  }

  @Override
  public void execute(String totalMessage, Session session) throws IOException {
    // /join/han
    String[] args = totalMessage.split(DELIMITER);
    String key = args[0];

    Command command = commands.get(key);
    if (command == null) {
      session.send("처리할 수 없는 명령어 입니다." + totalMessage);
    }

    command.execute(args, session);
  }
}
