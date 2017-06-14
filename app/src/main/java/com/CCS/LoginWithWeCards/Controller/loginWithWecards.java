package com.CCS.LoginWithWeCards.Controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Keep;
import android.util.Log;
import android.widget.Toast;

import com.CCS.LoginWithWeCards.Controller.Handler.Communicator;
import com.CCS.LoginWithWeCards.Controller.Handler.CommunicatorHandler;
import com.CCS.LoginWithWeCards.Controller.Handler.LoginHandler;
import com.CCS.LoginWithWeCards.Controller.Handler.loginWithWecardsHandler;
import com.CCS.LoginWithWeCards.UI.LoginScreen;
import com.CCS.LoginWithWeCards.Utils.appInstalledOrNot;

import static com.CCS.LoginWithWeCards.API.jsonKeys.API_KEY;
import static com.CCS.LoginWithWeCards.API.jsonKeys.APP_NAME;
import static com.CCS.LoginWithWeCards.API.jsonKeys.IC_LAUNCHER;
import static com.CCS.LoginWithWeCards.API.jsonKeys.PACKAGENAME;
import static com.CCS.LoginWithWeCards.API.jsonKeys.WECARDS_APP_ID;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.Tag;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginActvity;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppIcon;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithAppPackageName;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.loginWithWecardKey;
import static com.CCS.LoginWithWeCards.Utils.AppContacts.wecardsAppPackage;
import static com.CCS.LoginWithWeCards.Utils.getResources.getDrawable;
import static com.CCS.LoginWithWeCards.Utils.getResources.getString;

/**
 * Created by mauliksantoki on 24/4/17.
 */

@Keep
public class loginWithWecards implements loginWithWecardsHandler {

    private Activity context;
    private LoginHandler loginHandler;
    private Communicator communicator;

    public loginWithWecards(Activity context, LoginHandler loginListener) {
        this.context = context;
        communicator = new CommunicatorHandler();
        setAppIcon();
        setAppLoginID();
        setAppName();
        setAppPackageName();
        this.loginHandler = loginListener;
        communicator.connect(context, loginHandler);

    }

    @Override
    public void setAppPackageName() {
        loginWithAppPackageName = context.getPackageName();
    }

    @Override
    public void setAppIcon() {
        loginWithAppIcon = getDrawable(context, IC_LAUNCHER);
    }

    @Override
    public void setAppName() {
        loginWithAppName = getString(context, APP_NAME);
    }


    @Override
    public void setAppLoginID() {
        loginWithWecardKey = getString(context, WECARDS_APP_ID);
    }


    @Override
    public void login() {
        if (!validation()) {
            return;
        }

        if (appInstalledOrNot.appInstalledOrNot(wecardsAppPackage, context)) {
            /*
            * if application  install that time this message call
            */
            Intent loginWithWecards = new Intent(loginActvity);
            loginWithWecards.putExtra(API_KEY, loginWithWecardKey);
            loginWithWecards.putExtra(PACKAGENAME, loginWithAppPackageName);
            context.startActivity(loginWithWecards);
        } else {
           /*
            * if application  not install that time this message call
            */
            new LoginScreen(context);
        }
    }

    @Override
    public void logout() {
        communicator.logout(context);

    }

    @Override
    public void unRegisterCommunicator() {
        communicator.disConnect(context);
    }

    @Override
    public boolean validation() {
        if (loginWithWecardKey != null && loginWithWecardKey.trim().length() <= 0) {
            error("Add Wecards key in Your Project String.xml \"<string name=\"wecards_app_id\">XXXXXXXXXXXX</string>\" ");
            return false;
        }
        return true;
    }

    @Override
    public void error(String message) {
        Log.e(Tag, message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }



    /*
     * this method call when register activity will destory
     */


}
