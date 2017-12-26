package generated;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
abstract public class Challenge {
  public String name = "";
  protected User owner = null;
  protected VDMSet participants = SetUtil.set();

  public User getOwner() {

    return owner;
  }

  public VDMSet getParticipants() {

    return Utils.copy(participants);
  }

  public void addParticipant(final User o, final User participant) {

    participants = SetUtil.union(Utils.copy(participants), SetUtil.set(participant));
  }

  public void removeParticipant(final User o, final User p) {

    participants = SetUtil.diff(Utils.copy(participants), SetUtil.set(p));
  }

  public void removeSelf(final User u) {

    participants = SetUtil.diff(Utils.copy(participants), SetUtil.set(u));
  }

  public abstract Boolean completed(final Workout Workout);

  public Challenge() {}

  public String toString() {

    return "Challenge{"
        + "name := "
        + Utils.toString(name)
        + ", owner := "
        + Utils.toString(owner)
        + ", participants := "
        + Utils.toString(participants)
        + "}";
  }
}
