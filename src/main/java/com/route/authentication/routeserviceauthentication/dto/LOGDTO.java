package com.route.authentication.routeserviceauthentication.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class LOGDTO {


    private UserCredentialDTO LOG;

    public UserCredentialDTO getLOG() {
        return LOG;
    }

    public void setLOG(UserCredentialDTO LOG) {
        this.LOG = LOG;
    }

    @Override
    public String toString() {
        return "LOGDTO{" +
                "LOG=" + LOG +
                '}';
    }
}
