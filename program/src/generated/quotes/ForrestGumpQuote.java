package quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ForrestGumpQuote {
  private static int hc = 0;
  private static ForrestGumpQuote instance = null;

  public ForrestGumpQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ForrestGumpQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ForrestGumpQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ForrestGumpQuote;
  }

  public String toString() {

    return "<ForrestGump>";
  }
}
