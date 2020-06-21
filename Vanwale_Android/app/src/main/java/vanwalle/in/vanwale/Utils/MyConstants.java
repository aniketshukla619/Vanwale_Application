package vanwalle.in.vanwale.Utils;

import java.util.ArrayList;

/**
 * Created by Rohit K. Pawar on 13/03/2018.
 */

public class MyConstants {
    public static final String yes="yes";
    public static final String no="no";
    public static final String quantity="1";
    public static final String RESP_SUCCESS = "1";
    public static final String CITY ="city" ;
    public static final String CITYID = "cityid";
    public static final String USERIMAGE ="userimage" ;
    public static final String USERNAME ="username" ;
    public static final String USEREMAIL ="useremail" ;
    public static final String TOTALRENT = "rent";
    public static final String CITYNAME = "cityname";
    public static final String STATENAME ="statename" ;
    public static final String DESCRIPTION = "description";
    public static final String USERID = "user_id";
    public static final String LOCATION = "location";
    public static final String MINPRICE = "min price";
    public static final String MAXPRICE = "max price";
    public static final String RATING = "rating";
    public static final String LENDERID = "lender_id";
    public static final String LOCSTATE ="locstate" ;
    public static final String LOCADDRESS ="loc_address" ;
    public static final String FRAGMENTTAG = "fragment_tag";
    public static final String USERPHONE ="userphone" ;
    public static final String SUBCATID = "subcatid";
    public static final String CATID ="catid" ;
    public static final String CATNAME ="cat_name" ;
    public static final String PRODUCTID = "product id";
    public static final String USERLOGTYPE ="userlogtype" ;
    public static final String STATUS = "status";
    public static final String DELIVERY_AVAILABLE = "delivery available";
    public static String DeviceToken = "";
    public static String DeviceType = "a";
    public static ArrayList<String> lang = new ArrayList<>();
    public static String ITEMID= "item_id";
    public static String STARTDATE="startdate";
    public static String ENDDATE="enddate";
    public static String STARTTIME="starttime";
    public static String ENDTIME="endtime";
    public static String newphone="";

    public enum LoginTypes {
        FACEBOOK("facebook"), TWITTER("twitter"), APP("normal"), GOOGLE("google");
        private String s;

        private LoginTypes(String s) {
            this.s = s;
        }

        public String getType() {
            return this.s;
        }
    }
}
