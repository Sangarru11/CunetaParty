package com.github.Sangarru11.CunetaParty.Test;

import com.github.Sangarru11.CunetaParty.Utils.XMLManager;
import com.github.Sangarru11.CunetaParty.model.Connection.ConnectionProperties;

public class loadConnection {
    public static void main(String[] args) {
        ConnectionProperties c = XMLManager.readXML(new ConnectionProperties(),"connection.xml");
        System.out.println(c);
    }
}
