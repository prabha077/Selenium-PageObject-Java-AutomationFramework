package reportManager;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class statusReport {

    public static String reportPath=getClassPathNameBasedOnOs();
    public static String getClassPathNameBasedOnOs(){
        String tempValue=null;

        try{
            if(System.getProperty("os.name").contains("windows"))
            {
                String strclasspath=System.getProperty("user.dir");
                tempValue=strclasspath+"\\AutomationReports\\rainAutomationReport.html";
            }
            else
            {
                String strclasspath=System.getProperty("user.dir");
                tempValue=strclasspath+"/AutomationReports/rainAutomationReport.html";
                //System.out.println("THE OS NAME:" + System.getProperty("os.name") );
                //System.out.println("The dir is "+tempValue);
            }
        }
        catch (Exception e){
            System.out.println("error Getting the classpathNameBasesOnOS Exception -");
            ExceptionUtils.getStackTrace(e);
        }

        return tempValue;
    }

}
