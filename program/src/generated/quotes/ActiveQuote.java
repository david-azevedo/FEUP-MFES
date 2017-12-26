package quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ActiveQuote {
  private static int hc = 0;
  private static ActiveQuote instance = null;

  public ActiveQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ActiveQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ActiveQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ActiveQuote;
  }

  public String toString() {

    return "<Active>";
  }
}
