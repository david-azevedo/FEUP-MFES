
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Types {
  public Types() {}

  public String toString() {

    return "Types{}";
  }

  public static class Timestamp implements Record {
    public Number hour;
    public Number minutes;
    public Number seconds;

    public Timestamp(final Number _hour, final Number _minutes, final Number _seconds) {

      hour = _hour;
      minutes = _minutes;
      seconds = _seconds;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Timestamp)) {
        return false;
      }

      Timestamp other = ((Timestamp) obj);

      return (Utils.equals(hour, other.hour))
          && (Utils.equals(minutes, other.minutes))
          && (Utils.equals(seconds, other.seconds));
    }

    public int hashCode() {

      return Utils.hashCode(hour, minutes, seconds);
    }

    public Timestamp copy() {

      return new Timestamp(hour, minutes, seconds);
    }

    public String toString() {

      return "mk_Types`Timestamp" + Utils.formatFields(hour, minutes, seconds);
    }
  }

  public static Boolean inv_Timestamp(final Timestamp t) {

    Boolean andResult_9 = false;

    if (t.minutes.longValue() <= 59L) {
      if (t.seconds.longValue() <= 59L) {
        andResult_9 = true;
      }
    }

    return andResult_9;
  }

  public static class Point implements Record {
    public Number lat;
    public Number lon;

    public Point(final Number _lat, final Number _lon) {

      lat = _lat;
      lon = _lon;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Point)) {
        return false;
      }

      Point other = ((Point) obj);

      return (Utils.equals(lat, other.lat)) && (Utils.equals(lon, other.lon));
    }

    public int hashCode() {

      return Utils.hashCode(lat, lon);
    }

    public Point copy() {

      return new Point(lat, lon);
    }

    public String toString() {

      return "mk_Types`Point" + Utils.formatFields(lat, lon);
    }
  }

  public static class Route implements Record {
    public String name;
    public VDMSeq route;

    public Route(final String _name, final VDMSeq _route) {

      name = _name != null ? _name : null;
      route = _route != null ? Utils.copy(_route) : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Route)) {
        return false;
      }

      Route other = ((Route) obj);

      return (Utils.equals(name, other.name)) && (Utils.equals(route, other.route));
    }

    public int hashCode() {

      return Utils.hashCode(name, route);
    }

    public Route copy() {

      return new Route(name, route);
    }

    public String toString() {

      return "mk_Types`Route" + Utils.formatFields(name, route);
    }
  }
}
