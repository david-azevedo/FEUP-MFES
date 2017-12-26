package quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LazyQuote {
  private static int hc = 0;
  private static LazyQuote instance = null;

  public LazyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LazyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LazyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LazyQuote;
  }

  public String toString() {

    return "<Lazy>";
  }
}
