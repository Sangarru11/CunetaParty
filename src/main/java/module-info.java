module com.github.Sangarru11.CunetaParty {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.Sangarru11.CunetaParty to javafx.fxml;
    exports com.github.Sangarru11.CunetaParty;
}
