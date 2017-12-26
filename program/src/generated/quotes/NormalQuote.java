package generated.quotes;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class NormalQuote {
  private static int hc = 0;
  private static NormalQuote instance = null;

  public NormalQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static NormalQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new NormalQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof NormalQuote;
  }

  public String toString() {

    return "<Normal>";
  }
}
