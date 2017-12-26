
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
abstract public class Workout {
  protected Types.Route route;
  protected Types.Timestamp startTime;
  protected Types.Timestamp endTime;
  protected Number threshold;

  public abstract Boolean isFinished();

  public abstract void newGPSPosition(final Types.Point point);

  public abstract Number getDistance();

  public abstract Types.Point getLastPoint();

  public Types.Route getRoute() {

    return Utils.copy(route);
  }

  public void finish(final Types.Timestamp time) {

    endTime = Utils.copy(time);
  }

  public Number calculateVelocity() {

    return Utils.divide(
        (1.0 * getDistance().longValue()), Math.timeToSeconds(calculateTime()).longValue());
  }

  public Types.Timestamp calculateTime() {

    return Math.secondsToTime(
        Utils.abs(
            Math.timeToSeconds(Utils.copy(endTime)).longValue()
                - Math.timeToSeconds(Utils.copy(startTime)).longValue()));
  }

  protected Boolean outsideOfThreshold(final Types.Point point) {

    if (Utils.equals(getLastPoint(), null)) {
      return false;
    }

    return Math.distance(Utils.copy(point), getLastPoint()).doubleValue() > threshold.longValue();
  }

  protected Boolean insideOfThreshold(final Types.Point point) {

    if (Utils.equals(getLastPoint(), null)) {
      return false;
    }

    return Math.distance(Utils.copy(point), getLastPoint()).doubleValue() <= threshold.longValue();
  }

  public Workout() {}

  public String toString() {

    return "Workout{"
        + "route := "
        + Utils.toString(route)
        + ", startTime := "
        + Utils.toString(startTime)
        + ", endTime := "
        + Utils.toString(endTime)
        + ", threshold := "
        + Utils.toString(threshold)
        + "}";
  }
}
