package helpClasses;

import model.Admin;
import model.User;

public class LoggedInUtil {

     private static User loggedInUser;

     public static User getLoggedInUser() {
         return loggedInUser;
     }

     public static void setLoggedInUser(User loggedInUser) {
         LoggedInUtil.loggedInUser = loggedInUser;
     }

     public static void deleteLoggedInStatus(){
         loggedInUser = null;
     }

     public static String getUserToString(){
         if(loggedInUser instanceof Admin)
             return "Přihlášen jako: admin";
         else
             return "Přihlášen jako: " +  loggedInUser.getName() + " '" + loggedInUser.getNick() + "' "+loggedInUser.getSurname();

     }
 }
