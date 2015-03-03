package org.archive.modules.writer;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.archive.htmlanalysis.MiLanProductAnalysis;
import org.archive.htmlanalysis.MrProductAnalysis;
import org.archive.htmlanalysis.NapProductAnalysis;
import org.archive.htmlanalysis.SuningProductAnalysis;
import org.archive.io.ReplayCharSequence;
import org.archive.modules.CrawlURI;
import org.archive.modules.Processor;
import org.json.JSONException;
/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月2日 下午3:56:07
 * 
 */
public class PuductWriterProcessor extends Processor {
    
	private static final Logger logger =
	        Logger.getLogger(PuductWriterProcessor.class.getName());
	
    @Override
	protected boolean shouldProcess(CrawlURI curi) {
		// TODO Auto-generated method stub
		return isSuccess(curi);
	}

	@Override
	protected void innerProcess(CrawlURI curi) throws InterruptedException {
		// TODO Auto-generated method stub
		String url = curi.toString();
		try {
			ReplayCharSequence cs = curi.getRecorder().getContentReplayCharSequence();
			String html = cs.toString();
			if(url.contains("http://www.suning.com/emall/cprd_")||Pattern.compile("http\\://product\\.suning\\.com/\\d{10}/\\d{9}.html.*|http\\://product\\.suning\\.com/\\d{9}.html.*").matcher(url).find()){
		    	 SuningProductAnalysis.getSuningProduct(html, url);
		    	 logger.info(curi.toString());
		    	 
		     }else if(url.contains("http://www.net-a-porter.com/product/")){
		    	 NapProductAnalysis.getNapProduct(html, url);
		    	 
		     }else if(Pattern.compile("http://www.mrporter.com/en-cn/mens/.*(/\\d{6}).*").matcher(url).find()){
		    	 MrProductAnalysis.getMrProduct(html, url);
		    	 
		     }else if(Pattern.compile("http://www.milanstation.cc/product-\\d*.html").matcher(url).find()){
		    	 MiLanProductAnalysis.getMiLanProduct(html, url);
		     }
			 if(url.contains("http://www.net-a-porter.com/")||url.contains("http://www.mrporter.com/")||url.contains("http://www.milanstation.cc/")){
				 logger.info(curi.toString());
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
