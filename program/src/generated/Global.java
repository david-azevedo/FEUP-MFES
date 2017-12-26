package generated;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Global {
  private VDMSet users;

  public void cg_init_Global_1() {

    users = SetUtil.set();
    return;
  }

  public Global() {

    cg_init_Global_1();
  }

  public void addUser(final String name) {

    users = SetUtil.union(Utils.copy(users), SetUtil.set(new User(name, this)));
  }

  public void removeUser(final String name) {

    for (Iterator iterator_22 = users.iterator(); iterator_22.hasNext(); ) {
      User user = (User) iterator_22.next();
      if (Utils.equals(user.name, name)) {
        users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
      }
    }
  }

  public VDMSeq listUsers() {

    VDMSeq result = SeqUtil.seq();
    for (Iterator iterator_23 = users.iterator(); iterator_23.hasNext(); ) {
      User user = (User) iterator_23.next();
      result = SeqUtil.conc(Utils.copy(result), SeqUtil.seq(user.name));
    }
    return Utils.copy(result);
  }

  public User getUser(final String name) {

    for (Iterator iterator_24 = users.iterator(); iterator_24.hasNext(); ) {
      User user = (User) iterator_24.next();
      if (Utils.equals(user.name, name)) {
        return user;
      }
    }
    return null;
  }

  public void clear() {

    users = SetUtil.set();
  }

  public String toString() {

    return "Global{" + "users := " + Utils.toString(users) + "}";
  }
}
