package com.github.Sangarru11.CunetaParty.Test;

import com.github.Sangarru11.CunetaParty.Utils.XMLManager;
import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionProperties;

public class testConnection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","taller","root","root");
        XMLManager.writeXML(c,"connection.xml");
    }
}
