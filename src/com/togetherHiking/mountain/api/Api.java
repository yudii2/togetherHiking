package com.togetherHiking.mountain.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.togetherHiking.mountain.model.dto.Mountain;
import com.togetherHiking.mountain.model.service.MountainService;

public class Api { 
   
public void api() throws XmlPullParserException, IOException {
       
      Mountain mountain = new Mountain();
      MountainService mountainservice = new MountainService();
      
      String key = "vb7VRvR6GWpXJT1EaIIcYMLIE2wH%2FSLTxeJLj2OZ%2BezJUNWB20DGIYmMKJWFy56abCDff5P21JYKLDslp%2FIKLg%3D%3D";
      int pageNum = 1;
         
        for(pageNum = 1; pageNum < 156; pageNum++) {
        
         String urlCode = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice?"
               + "ServiceKey=" + key + "&pageNo=" + pageNum + "&mntnInfoAraCd=08";	//서울 : 01 , 경기 :08
         
         
           StringBuilder urlBuilder = new StringBuilder(urlCode); /*URL*/
           
           URL url = new URL(urlBuilder.toString());

           
           //xmlParser test
           XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
           factory.setNamespaceAware(true);
           XmlPullParser xpp = factory.newPullParser();
           BufferedInputStream bs = new BufferedInputStream(url.openStream());
           xpp.setInput(bs, "utf-8");

           int event_type = xpp.getEventType();
            
            String tag = null;
            String mName = null;    //이름
            String mountainIdx= null;   //인덱스번호
            String mLoc= null;      //위치
            String mInfo= null;   //정보
            String mHight= null;      //높이
            
            while (event_type != XmlPullParser.END_DOCUMENT) {
                if (event_type == XmlPullParser.START_TAG) {
                    tag = xpp.getName();
                } else if (event_type == XmlPullParser.TEXT) {
                    if(tag.equals("mntnnm")){
                    	mName = xpp.getText();
                    }else if(tag.equals("mntnid")){
                    	mountainIdx = xpp.getText();
                    }else if(tag.equals("mntninfopoflc")){
                    	mLoc = xpp.getText();
                    }else if(tag.equals("mntninfodtlinfocont")){
                    	mInfo = xpp.getText();
                    }else if(tag.equals("mntninfohght")){
                    	mHight = xpp.getText();
                    }
                } else if (event_type == XmlPullParser.END_TAG) {
                    tag = xpp.getName();
                    
                    
                    if (tag.equals("item")) {
                      // System.out.println("pageNum : "+ pageNum + "\n");

                        mountain.setmName(mName);
                        mountain.setMountainIdx(mountainIdx);
                        mountain.setmInfo(mInfo);
                        mountain.setmLoc(mLoc);
                        mountain.setmHight(mHight);

                        String beforetext = mountain.toString();
                        String mountaintext = beforetext.replaceAll("&lt;br /&gt;", "");   
                        mountaintext = mountaintext.replaceAll("&amp;nbsp;", ""); 

                        System.out.println(mountaintext);
                        System.out.println("============================================================================");
                                                   
                      mountainservice.insertMountain(mountain);	
                      
                      //데이터 가공 필요 (공백으로 대체하던가 regex처리)
                      //1. &lt;br /&gt;   &amp;nbsp;   &amp;ucirc;   &amp;acirc;   &amp;atilde;   &amp;otilde;    <BR>   <br>
                      //2. 산번호를 sc_mountain_idx(시퀀스)로 사용 --> 동일한 산이 중복
                      //3.  동일한 산을 데이터에 넣은 후 꺼내 쓸 때 위에것만 꺼내온다고 전제하면 쓸모없는 데이터가 아닌지 확인해야함
                      // * 애초에 값을 DB에 저장할 때 한개만 저장하는 방법을 구현할 수는 없나? 어렵..
                    
                    } 
                }
                event_type = xpp.next();
            } 
            
        }
   }
   
    public static void main(String[] args) throws IOException, XmlPullParserException {
       
       Api api = new Api();
       api.api();
    }
    
			
}
