package generated;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DistanceChallenge extends Challenge {
  public Number distance;

  public void cg_init_DistanceChallenge_1(final String n, final User u, final Number d) {

    String atomicTmp_1 = n;
    User atomicTmp_2 = u;
    VDMSet atomicTmp_3 = SetUtil.set(u);
    Number atomicTmp_4 = d;
    {
        /* Start of atomic statement */
      name = atomicTmp_1;
      owner = atomicTmp_2;
      participants = Utils.copy(atomicTmp_3);
      distance = atomicTmp_4;
    } /* End of atomic statement */

    return;
  }

  public DistanceChallenge(final String n, final User u, final Number d) {

    cg_init_DistanceChallenge_1(n, u, d);
  }

  public Boolean completed(final Workout workout) {

    if (workout.getDistance().longValue() >= distance.longValue()) {
      return true;
    }

    return false;
  }

  public DistanceChallenge() {}

  public String toString() {

    return "DistanceChallenge{" + "distance := " + Utils.toString(distance) + "}";
  }
}
