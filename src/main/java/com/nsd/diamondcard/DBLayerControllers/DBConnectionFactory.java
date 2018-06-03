package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

class DBConnectionFactory {

    private static volatile JdbcConnectionSource source = null;

    static JdbcConnectionSource getSource() {

        if (source == null) {
            try {
                Class.forName("org.postgresql.Driver");
                source = new JdbcConnectionSource("jdbc:postgresql://206.189.185.102:5432/postgres?sslmode=require&user=postgres&password=QazWsx321");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return source;
    }
}
