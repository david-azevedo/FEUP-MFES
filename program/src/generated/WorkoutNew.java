package generated;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WorkoutNew extends Workout {
  public void cg_init_WorkoutNew_1(final Types.Timestamp t, final Types.Route r) {

    threshold = 10L;
    route = Utils.copy(r);
    Types.Timestamp atomicTmp_13 = Utils.copy(t);
    Types.Timestamp atomicTmp_14 = Utils.copy(t);
    {
        /* Start of atomic statement */
      startTime = Utils.copy(atomicTmp_13);
      endTime = Utils.copy(atomicTmp_14);
    } /* End of atomic statement */

    return;
  }

  public WorkoutNew(final Types.Timestamp t, final Types.Route r) {

    cg_init_WorkoutNew_1(Utils.copy(t), Utils.copy(r));
  }

  public Boolean isFinished() {

    return route.route.size() > 1L;
  }

  public Types.Point getLastPoint() {

    if (Utils.equals(route.route.size(), 0L)) {
      return null;

    } else {
      return Utils.copy(((Types.Point) Utils.get(route.route, route.route.size())));
    }
  }

  public void newGPSPosition(final Types.Point point) {

    if (outsideOfThreshold(Utils.copy(point))) {
      route.route = SeqUtil.conc(Utils.copy(route.route), SeqUtil.seq(Utils.copy(point)));
    } else if (Utils.equals(route.route.size(), 0L)) {
      route.route = SeqUtil.conc(Utils.copy(route.route), SeqUtil.seq(Utils.copy(point)));
      return;
    }
  }

  public Number getDistance() {

    Types.Point tmp = null;
    Number distance = 0L;
    if (route.route.size() <= 1L) {
      return distance;
    }

    tmp = Utils.copy(((Types.Point) route.route.get(0)));
    for (Iterator iterator_39 = SeqUtil.tail(Utils.copy(route.route)).iterator();
        iterator_39.hasNext();
        ) {
      Types.Point point = (Types.Point) iterator_39.next();
      {
        distance =
            distance.doubleValue()
                + Math.distance(Utils.copy(tmp), Utils.copy(point)).doubleValue();
        tmp = Utils.copy(point);
      }
    }
    return distance;
  }

  public WorkoutNew() {}

  public String toString() {

    return "WorkoutNew{}";
  }
}
