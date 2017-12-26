package generated;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Math {
  public static Number sqrt(final Number v) {

    Number eps = 1.0E-15;
    Number res = v;
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 =
          Utils.abs(res.doubleValue() - Utils.divide(v.doubleValue(), res.doubleValue()))
              > eps.doubleValue() * res.doubleValue();
      if (!(whileCond_1)) {
        break;
      }

      res = Utils.divide(Utils.divide(v.doubleValue(), res.doubleValue()) + res.doubleValue(), 2.0);
    }

    return res;
  }

  public static Number distance(final Types.Point p1, final Types.Point p2) {

    Boolean andResult_8 = false;

    if (Utils.equals(p1.lat, p2.lat)) {
      if (Utils.equals(p1.lon, p2.lon)) {
        andResult_8 = true;
      }
    }

    if (andResult_8) {
      return 0.0;
    }

    return sqrt(
        (p1.lat.doubleValue() - p2.lat.doubleValue())
                * (p1.lat.doubleValue() - p2.lat.doubleValue())
            + (p1.lon.doubleValue() - p2.lon.doubleValue())
                * (p1.lon.doubleValue() - p2.lon.doubleValue()));
  }

  public Math() {}

  public static Number timeToSeconds(final Types.Timestamp time) {

    return time.hour.longValue() * 60L * 60L
        + time.minutes.longValue() * 60L
        + time.seconds.longValue();
  }

  public static Types.Timestamp secondsToTime(final Number seconds) {

    return new Types.Timestamp(
        Utils.mod(Utils.div(Utils.div(seconds.longValue(), 60L), 60L), 24L),
        Utils.mod(Utils.div(seconds.longValue(), 60L), 60L),
        Utils.mod(seconds.longValue(), 60L));
  }

  public String toString() {

    return "Math{}";
  }
}
