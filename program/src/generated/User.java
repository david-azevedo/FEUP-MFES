package generated;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public static final Number CHALLENGEVALUE = 1000L;
  public static final Number DISTANCEMULTIPLIER = 100L;
  public static final Number NORMAL = 2000L;
  public static final Number ACTIVE = 5000L;
  public static final Number SUPERFIT = 10000L;
  public static final Number FORRESTGUMP = 20000L;
  public String name;
  private VDMSet friends;
  private VDMMap routes;
  private VDMMap top;
  private Workout currentWorkout;
  private Global global;
  private Statistics statistics;
  private VDMSet completedChallenges;
  private VDMSet owner;
  private VDMSet participation;
  private Object rank;

  public void cg_init_User_1(final String n, final Global g) {

    currentWorkout = null;
    global = g;
    friends = SetUtil.set();
    top = MapUtil.map();
    routes = MapUtil.map();
    name = n;
    statistics = new Statistics();
    completedChallenges = SetUtil.set();
    owner = SetUtil.set();
    participation = SetUtil.set();
    rank = generated.quotes.LazyQuote.getInstance();
    return;
  }

  public User(final String n, final Global g) {

    cg_init_User_1(n, g);
  }

  public void removeFromChallenge(final String cName) {

    Challenge iotaExp_1 = null;
    Long iotaCounter_1 = 0L;
    VDMSet set_6 =
        SetUtil.diff(
            SetUtil.diff(Utils.copy(participation), Utils.copy(owner)),
            Utils.copy(completedChallenges));
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext(); ) {
      Challenge c = ((Challenge) iterator_6.next());
      if (Utils.equals(c.name, cName)) {
        iotaCounter_1++;
        if (iotaCounter_1.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_1 = c;
        }
      }
    }
    if (Utils.equals(iotaCounter_1, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    Challenge cha = iotaExp_1;
    cha.removeSelf(this);
    participation = SetUtil.diff(Utils.copy(participation), SetUtil.set(cha));
  }

  public void startChallenge(final String cName, final Number time) {

    Challenge iotaExp_2 = null;
    Long iotaCounter_2 = 0L;
    VDMSet set_8 = SetUtil.union(Utils.copy(owner), Utils.copy(participation));
    for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext(); ) {
      Challenge challenge = ((Challenge) iterator_8.next());
      if (Utils.equals(challenge.name, cName)) {
        iotaCounter_2++;
        if (iotaCounter_2.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_2 = challenge;
        }
      }
    }
    if (Utils.equals(iotaCounter_2, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    Challenge myChallenge = iotaExp_2;
    Types.Route route = null;
    if (myChallenge instanceof DistanceChallenge) {
      route = new Types.Route(cName, SeqUtil.seq());
      currentWorkout = new WorkoutNew(Math.secondsToTime(time), Utils.copy(route));

    } else {
      if (myChallenge instanceof RouteChallenge) {
        currentWorkout =
            new WorkoutExisting(Math.secondsToTime(time), ((RouteChallenge) myChallenge).route);
      }
    }
  }

  private void updateRank() {

    Number value =
        Math.timeToSeconds(statistics.getTotalRunTime()).longValue()
            + statistics.getTotalDistance().longValue() * User.DISTANCEMULTIPLIER.longValue()
            + completedChallenges.size() * User.CHALLENGEVALUE.longValue();
    if (value.longValue() >= User.FORRESTGUMP.longValue()) {
      rank = generated.quotes.ForrestGumpQuote.getInstance();
    } else {
      if (value.longValue() >= User.SUPERFIT.longValue()) {
        rank = generated.quotes.SuperFitQuote.getInstance();
      } else {
        if (value.longValue() >= User.ACTIVE.longValue()) {
          rank = generated.quotes.ActiveQuote.getInstance();
        } else {
          if (value.longValue() >= User.NORMAL.longValue()) {
            rank = generated.quotes.NormalQuote.getInstance();
          }
        }
      }
    }
  }

  public Statistics getStatistics() {

    return statistics;
  }

  private void finishChallenges(final Workout workout) {

    for (Iterator iterator_25 =
            SetUtil.diff(
                    SetUtil.union(Utils.copy(owner), Utils.copy(participation)),
                    Utils.copy(completedChallenges))
                .iterator();
        iterator_25.hasNext();
        ) {
      Challenge challenge = (Challenge) iterator_25.next();
      if (challenge.completed(workout)) {
        completedChallenges =
            SetUtil.union(Utils.copy(completedChallenges), SetUtil.set(challenge));
      }
    }
  }

  public Object getRank() {

    return rank;
  }

  public void resetStatistics() {

    statistics.reset();
  }

  public void addChallenge(final Challenge chal) {

    participation = SetUtil.union(Utils.copy(participation), SetUtil.set(chal));
  }

  public void removeChallenge(final Challenge chal) {

    participation = SetUtil.diff(Utils.copy(participation), SetUtil.set(chal));
  }

  public void addFriendToChallenge(final String friendName, final String challengeName) {

    for (Iterator iterator_26 = owner.iterator(); iterator_26.hasNext(); ) {
      Challenge c = (Challenge) iterator_26.next();
      if (Utils.equals(c.name, challengeName)) {
        c.addParticipant(this, global.getUser(friendName));
        global.getUser(friendName).addChallenge(c);
      }
    }
  }

  public void removeFriendFromChallenge(final String friendName, final String challengeName) {

    for (Iterator iterator_27 = owner.iterator(); iterator_27.hasNext(); ) {
      Challenge c = (Challenge) iterator_27.next();
      if (Utils.equals(c.name, challengeName)) {
        c.removeParticipant(this, global.getUser(friendName));
        global.getUser(friendName).removeChallenge(c);
      }
    }
  }

  public void createDistanceChallenge(final String n, final Number d) {

    owner = SetUtil.union(Utils.copy(owner), SetUtil.set(new DistanceChallenge(n, this, d)));
  }

  public void createRouteChallenge(final String n, final String routeName) {

    Types.Route iotaExp_4 = null;
    Long iotaCounter_4 = 0L;
    VDMSet set_19 = MapUtil.dom(Utils.copy(routes));
    for (Iterator iterator_19 = set_19.iterator(); iterator_19.hasNext(); ) {
      Types.Route route = ((Types.Route) iterator_19.next());
      if (Utils.equals(route.name, routeName)) {
        iotaCounter_4++;
        if (iotaCounter_4.longValue() > 1L) {
          throw new RuntimeException("Iota selects more than one result");
        } else {
          iotaExp_4 = Utils.copy(route);
        }
      }
    }
    if (Utils.equals(iotaCounter_4, 0L)) {
      throw new RuntimeException("Iota selects more than one result");
    }

    owner =
        SetUtil.union(
            Utils.copy(owner), SetUtil.set(new RouteChallenge(n, this, Utils.copy(iotaExp_4))));
  }

  public void addFriend(final String n) {

    User friend = global.getUser(n);
    if (!(Utils.equals(friend, null))) {
      addFriend(friend);
      friend.addFriend(this);
    }
  }

  public void addFriend(final User friend) {

    friends = SetUtil.union(Utils.copy(friends), SetUtil.set(friend));
  }

  public void removeFriend(final String n) {

    for (Iterator iterator_28 = friends.iterator(); iterator_28.hasNext(); ) {
      User f = (User) iterator_28.next();
      if (Utils.equals(f.name, n)) {
        removeFriend(f);
        f.removeFriend(this);
      }
    }
  }

  public void removeFriend(final User friend) {

    friends = SetUtil.diff(Utils.copy(friends), SetUtil.set(friend));
  }

  public VDMSeq listFriends() {

    VDMSeq result = SeqUtil.seq();
    for (Iterator iterator_29 = friends.iterator(); iterator_29.hasNext(); ) {
      User friend = (User) iterator_29.next();
      result = SeqUtil.conc(Utils.copy(result), SeqUtil.seq(friend.name));
    }
    return Utils.copy(result);
  }

  public void startWorkout(final String routeName, final Number startTime) {

    Types.Timestamp time = Math.secondsToTime(startTime);
    Types.Route route = null;
    for (Iterator iterator_30 = MapUtil.dom(Utils.copy(routes)).iterator();
        iterator_30.hasNext();
        ) {
      Types.Route r = (Types.Route) iterator_30.next();
      if (Utils.equals(r.name, routeName)) {
        route = Utils.copy(r);
      }
    }
    if (!(Utils.equals(route, null))) {
      currentWorkout = new WorkoutExisting(Utils.copy(time), Utils.copy(route));
    } else {
      route = new Types.Route(routeName, SeqUtil.seq());
      currentWorkout = new WorkoutNew(Utils.copy(time), Utils.copy(route));
    }
  }

  public void cancelCurrentWorkout() {

    currentWorkout = null;
  }

  public void updateByGPS(final Types.Point point) {

    currentWorkout.newGPSPosition(Utils.copy(point));
  }

  private void updateTop(final Workout workout) {

    VDMMap pair = MapUtil.domResTo(SetUtil.set(workout.getRoute()), Utils.copy(top));
    if (!(Utils.empty(pair))) {
      Long exists1Counter_4 = 0L;
      VDMSet set_21 = MapUtil.rng(Utils.copy(pair));
      for (Iterator iterator_21 = set_21.iterator();
          iterator_21.hasNext() && (exists1Counter_4.longValue() < 2L);
          ) {
        Workout t = ((Workout) iterator_21.next());
        if (Math.timeToSeconds(t.calculateTime()).longValue()
            > Math.timeToSeconds(workout.calculateTime()).longValue()) {
          exists1Counter_4++;
        }
      }
      if (Utils.equals(exists1Counter_4, 1L)) {
        top =
            MapUtil.override(Utils.copy(top), MapUtil.map(new Maplet(workout.getRoute(), workout)));
      }

    } else {
      top = MapUtil.override(Utils.copy(top), MapUtil.map(new Maplet(workout.getRoute(), workout)));
    }
  }

  private void storeCurrentWorkout() {

    Types.Route r1 = currentWorkout.getRoute();
    VDMMap pair = MapUtil.domResTo(SetUtil.set(currentWorkout.getRoute()), Utils.copy(routes));
    updateTop(currentWorkout);
    if (!(Utils.empty(pair))) {
      for (Iterator iterator_31 = MapUtil.rng(Utils.copy(pair)).iterator();
          iterator_31.hasNext();
          ) {
        VDMSeq e = (VDMSeq) iterator_31.next();
        routes =
            MapUtil.override(
                Utils.copy(routes),
                MapUtil.map(
                    new Maplet(
                        Utils.copy(r1), SeqUtil.conc(Utils.copy(e), SeqUtil.seq(currentWorkout)))));
      }
    } else {
      routes =
          MapUtil.override(
              Utils.copy(routes),
              MapUtil.map(new Maplet(Utils.copy(r1), SeqUtil.seq(currentWorkout))));
    }
  }

  public VDMMap getRoutesPerformed() {

    VDMMap res = MapUtil.map();
    for (Iterator iterator_32 = MapUtil.dom(Utils.copy(routes)).iterator();
        iterator_32.hasNext();
        ) {
      Types.Route route = (Types.Route) iterator_32.next();
      for (Iterator iterator_33 =
              MapUtil.rng(MapUtil.domResTo(SetUtil.set(Utils.copy(route)), Utils.copy(routes)))
                  .iterator();
          iterator_33.hasNext();
          ) {
        VDMSeq workouts = (VDMSeq) iterator_33.next();
        res =
            MapUtil.override(
                Utils.copy(res), MapUtil.map(new Maplet(Utils.copy(route), workouts.size())));
      }
    }
    return Utils.copy(res);
  }

  public VDMMap getTop() {

    VDMMap res = MapUtil.map();
    for (Iterator iterator_34 = MapUtil.dom(Utils.copy(top)).iterator(); iterator_34.hasNext(); ) {
      Types.Route route = (Types.Route) iterator_34.next();
      for (Iterator iterator_35 =
              MapUtil.rng(MapUtil.domResTo(SetUtil.set(Utils.copy(route)), Utils.copy(top)))
                  .iterator();
          iterator_35.hasNext();
          ) {
        Workout workout = (Workout) iterator_35.next();
        res =
            MapUtil.override(
                Utils.copy(res),
                MapUtil.map(
                    new Maplet(Utils.copy(route), Math.timeToSeconds(workout.calculateTime()))));
      }
    }
    return Utils.copy(res);
  }

  public VDMSeq listRoutes() {

    VDMSeq res = SeqUtil.seq();
    for (Iterator iterator_36 = MapUtil.dom(Utils.copy(routes)).iterator();
        iterator_36.hasNext();
        ) {
      Types.Route e = (Types.Route) iterator_36.next();
      res = SeqUtil.conc(Utils.copy(res), SeqUtil.seq(e.name));
    }
    return Utils.copy(res);
  }

  public VDMSeq listChallenges() {

    VDMSeq res = SeqUtil.seq();
    for (Iterator iterator_37 =
            SetUtil.union(Utils.copy(owner), Utils.copy(participation)).iterator();
        iterator_37.hasNext();
        ) {
      Challenge e = (Challenge) iterator_37.next();
      res = SeqUtil.conc(Utils.copy(res), SeqUtil.seq(e.name));
    }
    return Utils.copy(res);
  }

  public VDMSeq listCompletedChallenges() {

    VDMSeq res = SeqUtil.seq();
    for (Iterator iterator_38 = completedChallenges.iterator(); iterator_38.hasNext(); ) {
      Challenge e = (Challenge) iterator_38.next();
      res = SeqUtil.conc(Utils.copy(res), SeqUtil.seq(e.name));
    }
    return Utils.copy(res);
  }

  public void finishCurrentWorkout(final Number time) {

    currentWorkout.finish(Math.secondsToTime(time));
    if (currentWorkout.isFinished()) {
      updateStatistics(currentWorkout);
      finishChallenges(currentWorkout);
      storeCurrentWorkout();
    }

    currentWorkout = null;
  }

  public Types.Route getCurrentRoute() {

    if (!(Utils.equals(currentWorkout, null))) {
      return currentWorkout.getRoute();
    }

    return null;
  }

  private void updateStatistics(final Workout workout) {

    statistics.addTime(workout.calculateTime());
    statistics.addDistance(workout.getDistance());
    updateRank();
  }

  public User() {}

  public String toString() {

    return "User{"
        + "CHALLENGEVALUE = "
        + Utils.toString(CHALLENGEVALUE)
        + ", DISTANCEMULTIPLIER = "
        + Utils.toString(DISTANCEMULTIPLIER)
        + ", NORMAL = "
        + Utils.toString(NORMAL)
        + ", ACTIVE = "
        + Utils.toString(ACTIVE)
        + ", SUPERFIT = "
        + Utils.toString(SUPERFIT)
        + ", FORRESTGUMP = "
        + Utils.toString(FORRESTGUMP)
        + ", name := "
        + Utils.toString(name)
        + ", friends := "
        + Utils.toString(friends)
        + ", routes := "
        + Utils.toString(routes)
        + ", top := "
        + Utils.toString(top)
        + ", currentWorkout := "
        + Utils.toString(currentWorkout)
        + ", global := "
        + Utils.toString(global)
        + ", statistics := "
        + Utils.toString(statistics)
        + ", completedChallenges := "
        + Utils.toString(completedChallenges)
        + ", owner := "
        + Utils.toString(owner)
        + ", participation := "
        + Utils.toString(participation)
        + ", rank := "
        + Utils.toString(rank)
        + "}";
  }
}
