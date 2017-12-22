package generated;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WorkoutExisting extends Workout {
  private Types.Route remainingRoute;
  private Types.Point lastPoint;
  private Number traveledDistance;

  public void cg_init_WorkoutExisting_1(final Types.Timestamp t, final Types.Route r) {

    traveledDistance = 0L;
    threshold = 5L;
    route = Utils.copy(r);
    remainingRoute = Utils.copy(r);
    lastPoint = null;
    Types.Timestamp atomicTmp_11 = Utils.copy(t);
    Types.Timestamp atomicTmp_12 = Utils.copy(t);
    {
        /* Start of atomic statement */
      startTime = Utils.copy(atomicTmp_11);
      endTime = Utils.copy(atomicTmp_12);
    } /* End of atomic statement */

    return;
  }

  public WorkoutExisting(final Types.Timestamp t, final Types.Route r) {

    cg_init_WorkoutExisting_1(Utils.copy(t), Utils.copy(r));
  }

  public Boolean isFinished() {

    return Utils.equals(remainingRoute.route.size(), 0L);
  }

  public Types.Point getLastPoint() {

    if (remainingRoute.route.size() > 0L) {
      return Utils.copy(((Types.Point) remainingRoute.route.get(0)));

    } else {
      return null;
    }
  }

  private void updateDistance(final Types.Point point) {

    if (!(Utils.equals(lastPoint, null))) {
      traveledDistance =
          traveledDistance.doubleValue()
              + Math.distance(Utils.copy(lastPoint), Utils.copy(point)).doubleValue();
    }

    lastPoint = Utils.copy(point);
  }

  public void newGPSPosition(final Types.Point point) {

    if (insideOfThreshold(Utils.copy(point))) {
      updateDistance(Utils.copy(point));
      remainingRoute.route = SeqUtil.tail(Utils.copy(remainingRoute.route));
    }
  }

  public Number getDistance() {

    return traveledDistance;
  }

  public WorkoutExisting() {}

  public String toString() {

    return "WorkoutExisting{"
        + "remainingRoute := "
        + Utils.toString(remainingRoute)
        + ", lastPoint := "
        + Utils.toString(lastPoint)
        + ", traveledDistance := "
        + Utils.toString(traveledDistance)
        + "}";
  }
}
