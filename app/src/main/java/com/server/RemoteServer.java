package com.server;

/**
 * Created by Charan-celigo on 3/19/2017.
 */
public class RemoteServer {

    private String serverUrl;// = "http://a15cf38e.ngrok.io";
    private String addExpenseEndPoint = "/addexpense";
    private String retrieveExpenseEndPoint = "/getexpense";
    private RemoteServer(){}
    private RemoteServer(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAddExpenseEndPoint() {
        return addExpenseEndPoint;
    }

    public void setAddExpenseEndPoint(String addExpenseEndPoint) {
        this.addExpenseEndPoint = addExpenseEndPoint;
    }

    public String getRetrieveExpenseEndPoint() {
        return retrieveExpenseEndPoint;
    }

    public void setRetrieveExpenseEndPoint(String retrieveExpenseEndPoint) {
        this.retrieveExpenseEndPoint = retrieveExpenseEndPoint;
    }

    private static RemoteServer remoteServer;
    public static synchronized RemoteServer getRemoteServer(String serverUrl){
        if (remoteServer == null) {
            remoteServer = new RemoteServer(serverUrl);
        }
        return remoteServer;
    }

    public static synchronized RemoteServer getRemoteServer(){
        if (remoteServer == null) {
            remoteServer = new RemoteServer();
        }
        return remoteServer;
    }
    public String getServerUrl () {
        return serverUrl;
    }
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
