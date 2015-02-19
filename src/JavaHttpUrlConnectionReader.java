import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class JavaHttpUrlConnectionReader {
	public static void main(String[] args) {
 
		Movie movie = new Movie();
		movie.setMovieName("fasdfasdfasdf");
		movie.setMovieYear("fasdfasdfasdf");
		movie.setMovieDuration("fasdfasdfasdf");
		movie.setMovieRating("fasdfasdfasdf");
		movie.setMovieVotes("fasdfasdfasdf");
		movie.setMovieGenre("fasdfasdfasdf");
		
		try{
			 
			String homeDirectory = System.getProperty("user.home");
			String fileName = "movie-info.txt";
			File file=new File(homeDirectory + "/"
					+ fileName);
	        FileWriter fw=new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw=new BufferedWriter(fw);
	        bw.write(movie.toString());
	        bw.close();
			System.out.println("Done");
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
 
	}
	
	public ArrayList<String> getTitleId()
	{
		String homeDirectory = System.getProperty("user.home");
		String fileName = "movie-title.txt";
		String movieTitle;
		BufferedReader fileReader;
		ArrayList<String> movieTitleList =  new ArrayList<String>();

		try {
			fileReader = new BufferedReader(new FileReader(homeDirectory + "/"
					+ fileName));
			
			while ((movieTitle = fileReader.readLine()) != null) {
				movieTitleList.add(movieTitle);	
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieTitleList;
	}
	
	public String getHtmlFromUrl(String title)
	{
		String inputLine;
		String outputLine = "";
		try {
			URL url = new URL("http://www.imdb.com/title/" + title);
			URLConnection conn = url.openConnection();
			
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
 
			
 
			while ((inputLine = br.readLine()) != null) {
				//bw.write(inputLine);
				outputLine +=  inputLine;
				outputLine += "\n";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return outputLine;
	}
	
	public Movie createMovie(String html)
	{
		Movie movie = new Movie();
		movie.setMovieName(parseMovieName(html));
		movie.setMovieYear(parseMovieYear(html));
		movie.setMovieDuration(parseMovieDuration(html));
		movie.setMovieRating(parseMovieRating(html));
		movie.setMovieVotes(parseMovieVotes(html));
		movie.setMovieGenre(parseMovieGenre(html));
		
		return movie;
	}
	
	public String parseMovieName(String outputLine)
	{
		Pattern p = Pattern.compile("<title>(.*)</title>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    
	    if (m.find())
	    {
	      //String name =  m.group(1).split("(")[0];
	      //String year =  m.group(1).split("(")[1].split(")")[0];
	      ///return "movie name is :" + name + "year is :" + year;
	    	return m.group(1).split("\\(")[0].toString();
	    }
		
	    else
	    	return null;
	}
	
	public String parseMovieYear(String outputLine)
	{
		Pattern p = Pattern.compile("<title>(.*)</title>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    
	    if (m.find())
	    {
	      //String name =  m.group(1).split("(")[0];
	      //String year =  m.group(1).split("(")[1].split(")")[0];
	      ///return "movie name is :" + name + "year is :" + year;
	    	return m.group(1).split("\\(")[1].toString().split("\\)")[0];
	    }
		
	    else
	    	return null;
	}
	
	public String parseMovieDuration(String outputLine)
	{
		Pattern p = Pattern.compile("<time itemprop=\"duration\".*>(.*)</time>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    
	    if (m.find())
	    {
	    	return m.group(1);
	    }
		
	    else
	    	return null;
	}
	
	public String parseMovieRating(String outputLine)
	{
		Pattern p = Pattern.compile("<span itemprop=\"ratingValue\">(.*?)</span>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    
	    if (m.find())
	    {
	    	return m.group(1);
	    }
		
	    else
	    	return null;
	}
	
	public String parseMovieVotes(String outputLine)
	{
		Pattern p = Pattern.compile("<span itemprop=\"ratingCount\".*>(.*)</span>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    
	    if (m.find())
	    {
	    	return m.group(1);
	    }
		
	    else
	    	return null;
	}
	
	public String parseMovieGenre(String outputLine)
	{
		Pattern p = Pattern.compile("<span class=\"itemprop\" itemprop=\"genre\">(.*)</span>");
	    //Pattern p = Pattern.compile("</.*?>");
	    Matcher m = p.matcher(outputLine);
	    String movieGenre = "";
	    
	    while (m.find())
	    {
	    	movieGenre += m.group(1) + ", ";
	    }
		
	    return movieGenre.substring(0, movieGenre.length()-2);
	}
	
	
	
	
}