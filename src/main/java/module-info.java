module com.github.Sangarru11.CunetaParty {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens com.github.Sangarru11.CunetaParty to javafx.fxml;
    opens com.github.Sangarru11.CunetaParty.View to javafx.fxml,java.xml.bind;
    opens com.github.Sangarru11.CunetaParty.model.Connection to java.xml.bind;

    exports com.github.Sangarru11.CunetaParty;
    exports com.github.Sangarru11.CunetaParty.View;

}
