
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * A complete Java program that demonstrates how to
 * extract a tag from a line of HTML using the Pattern
 * and Matcher classes.
 */
public class PatternMatcherGroupHTML
{
  public static void main(String[] args)
  {
    String stringToSearch = "<h1>Yada yada yada <code>StringBuffer</code> " +
    		"<code>blablabla</code> yada yada ...</h1>";
 
    // the pattern we want to search for
    String tag = "code";
    Pattern p = Pattern.compile("<"+tag+">(\\S+)</"+tag+">");
    //Pattern p = Pattern.compile("</.*?>");
    Matcher m = p.matcher(stringToSearch);
    System.out.println(stringToSearch);
    // if we find a match, get the group
    while (m.find())
    {
      // get the matching group
      //String codeGroup = m.group(1);
       
      // print the group
      //System.out.format("'%s'\n", m.group());
    	
      //System.out.println(m.start());
      //System.out.println(m.end());
      //
    	//m.group().split(">")
    	///stringToSearch.replace(m.group(), "<"+tag+">All are now changed</"+tag+">");
    }
    System.out.println(stringToSearch);
 
  }
}