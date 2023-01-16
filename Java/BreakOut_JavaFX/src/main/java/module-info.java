module de.oose.breakout {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.apache.logging.log4j;
    requires javafx.media;
    requires java.logging;
    requires java.persistence;
    requires java.sql;
    requires org.hsqldb;
    requires org.hibernate.orm.core;
    requires javafx.graphics;

    opens de.oose.breakout.highscore;
    exports de.oose.breakout;
}