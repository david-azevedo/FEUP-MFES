package generated.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SuperFitQuote {
  private static int hc = 0;
  private static SuperFitQuote instance = null;

  public SuperFitQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SuperFitQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SuperFitQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SuperFitQuote;
  }

  public String toString() {

    return "<SuperFit>";
  }
}
