package generated;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RouteChallenge extends Challenge {
  public Types.Route route;

  public void cg_init_RouteChallenge_1(final String n, final User u, final Types.Route r) {

    String atomicTmp_5 = n;
    User atomicTmp_6 = u;
    VDMSet atomicTmp_7 = SetUtil.set(u);
    Types.Route atomicTmp_8 = Utils.copy(r);
    {
        /* Start of atomic statement */
      name = atomicTmp_5;
      owner = atomicTmp_6;
      participants = Utils.copy(atomicTmp_7);
      route = Utils.copy(atomicTmp_8);
    } /* End of atomic statement */

    return;
  }

  public RouteChallenge(final String n, final User u, final Types.Route r) {

    cg_init_RouteChallenge_1(n, u, Utils.copy(r));
  }

  public Boolean completed(final Workout workout) {

    if (SetUtil.subset(
        SeqUtil.elems(Utils.copy(route.route)),
        SeqUtil.elems(Utils.copy(workout.getRoute().route)))) {
      return true;
    }

    return false;
  }

  public RouteChallenge() {}

  public String toString() {

    return "RouteChallenge{" + "route := " + Utils.toString(route) + "}";
  }
}
