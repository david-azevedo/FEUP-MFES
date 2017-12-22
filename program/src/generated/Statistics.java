package generated;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Statistics {
  private Number totalDistance = 0L;
  private Types.Timestamp totalRunTime = new Types.Timestamp(0L, 0L, 0L);

  public void reset() {

    Number atomicTmp_9 = 0L;
    Types.Timestamp atomicTmp_10 = new Types.Timestamp(0L, 0L, 0L);
    {
        /* Start of atomic statement */
      totalDistance = atomicTmp_9;
      totalRunTime = Utils.copy(atomicTmp_10);
    } /* End of atomic statement */
  }

  public Number getTotalDistance() {

    return totalDistance;
  }

  public Types.Timestamp getTotalRunTime() {

    return Utils.copy(totalRunTime);
  }

  public Number getVelocity() {

    if (Utils.equals(Math.timeToSeconds(Utils.copy(totalRunTime)), 0L)) {
      return 0L;
    }

    return Utils.divide(
        (1.0 * totalDistance.longValue()),
        Math.timeToSeconds(Utils.copy(totalRunTime)).longValue());
  }

  public void addTime(final Types.Timestamp time) {

    totalRunTime =
        Math.secondsToTime(
            Math.timeToSeconds(Utils.copy(time)).longValue()
                + Math.timeToSeconds(Utils.copy(totalRunTime)).longValue());
  }

  public void addDistance(final Number dist) {

    totalDistance = dist.longValue() + totalDistance.longValue();
  }

  public Statistics() {}

  public String toString() {

    return "Statistics{"
        + "totalDistance := "
        + Utils.toString(totalDistance)
        + ", totalRunTime := "
        + Utils.toString(totalRunTime)
        + "}";
  }
}
