package researchanddevelopment


import groovy.sql.Sql
import java.io.*; // ito giezel need 
import java.net.*;
import java.util.*; // ito giezel need
import java.util.concurrent.ConcurrentHashMap;
import org.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.parser.JSONParser
import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpSession
import java.text.DateFormat; // ito giezel need
import java.text.SimpleDateFormat; // ito giezel need
import java.util.Calendar; // ito giezel need
import java.util.Date; // ito giezel need
import java.util.Formatter.DateTime // ito giezel need
import java.util.Iterator; 
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.codec.binary.Base64;

class ApiController {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateFormat refDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private HttpURLConnection  myURLConnection;
    def dataSource 
    static allowedMethods = [save: "POST", update: ["PUT","POST"], delete: "DELETE", saveCharge: "PUT"]
    String resultValue = null
    
    def index() {
    }
    
    def portalProcessParameters(params){
        println("============= portalProcessParameters ================")
        println("params: "+params)
        
        URL url = null;
        String action = "";
        action = "Echo";
        String jmXSecurityCode = "";
        String jmXConnectionUrl = "";
        String multiSystoken = "9b109bf4b070678a276214d68787a9a1c74996bc";
        String multiSysCode = "TBILLER";
        String callBackUrl = "";
        
        // last 4 digits of shortCode
        String shortCode = "0079";
        String appId = "jEoBseA59nuXki8bq9c5KGuyRE9Ls6xK"
        String appSecret = "a15bdac7f06e624ea9a8ddfa7a9e50eae1ab420bab235d8157bc39c23e1d5f57"
        
        try {
            if("Echo".equals(action)){
                System.out.println("AAA");

                jmXConnectionUrl = "http://developer.globelabs.com.ph/oauth/access_token"
                println("jmXConnectionUrl: "+jmXConnectionUrl)
                url = new URL(jmXConnectionUrl);
            }
            // required item to validate request

            //params.put("callback_url",callBackUrl)
            params.put("customerId","1233445566666")
            params.put("txnId","6666")
            
            StringBuilder postData = new StringBuilder();
            
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("X-MultiPay-Token", "9b109bf4b070678a276214d68787a9a1c74996bc");
            conn.setRequestProperty("X-MultiPay-Code", "TBILLER");
            
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
                     
            Reader insx = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

            //==========================
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = insx.readLine()) != null) {
               response.append(inputLine);
            }
            insx.close();
            //print in String
            return response
        } 
        catch (MalformedURLException e) { 
            //System.out.println(e);
            // new URL() failed
            
            // ...
        }  
    }
    
    def createTransactionReference(){
        
        // generatetimeusing 
        Date date = new Date();
        String refDetails = ""+refDateFormat.format(date).toString();
        refDetails = refDetails.replaceAll(" ", "").toLowerCase()
        refDetails = refDetails.replaceAll("-", "").toLowerCase()
        refDetails = refDetails.replaceAll(":", "").toLowerCase()
        
        return refDetails
    }
    def sendEmail() {
        println("===================== SEND EMAIL ===================")
        def json = request.JSON
        def emailAdd = "sample@gmail.com"

        println("emailAdd: "+emailAdd)
        def emailSubjectContent = "MBP ONLINE BANKING CODE VERIFICATION"
        
        def regCode = passcodeMaker()
        
        def jsrequestAction = json.reqAction
        def emailMsgContent = ""
        if(jsrequestAction == "registration"){
            emailMsgContent = "<h3>Hello There! Your Registration Code is <strong style='color: #00b3b3'>"+regCode+"</strong> use this code to complete your registration process.</h3>"
        }else if(jsrequestAction == "linkAccount"){
            emailMsgContent = "<h3>Hello There! Your Link Account Verification Code is <strong style='color: #00b3b3'>"+regCode+"</strong> use this code to complete your Account Linking process.</h3>"
        }else{
            // posible txn
            emailMsgContent = "<h3>Hello There! Your Fund Transfer Verification Code is <strong style='color: #00b3b3'>"+regCode+"</strong> use this code to complete your Fund Transfer process.</h3>"
        }
        
        
        sendMail {
            to emailAdd
            subject emailSubjectContent
            html emailMsgContent
        }
        println("=========== VERIFIFY ACCOUNT SECTION =============")
        println("Email Sent to : "+emailAdd)
        println("Code : "+regCode)
        println("==================================================")

        
        def reponseWebPortal = []
        reponseWebPortal << [reponseCode:'xxxx', responsetexx:regCode]
        render reponseWebPortal as grails.converters.JSON
        
    }
}
