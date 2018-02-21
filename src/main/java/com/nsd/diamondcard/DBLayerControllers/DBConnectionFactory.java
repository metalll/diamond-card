package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

class DBConnectionFactory {

    private static volatile JdbcConnectionSource source = null;

    static JdbcConnectionSource getSource() {

        if (source == null) {
            try {
                Class.forName("org.postgresql.Driver");
                source = new JdbcConnectionSource("jdbc:postgresql://ec2-54-235-146-184.compute-1.amazonaws.com:5432/dc4id95g8fkddn?sslmode=require&user=kpxtqcjnjmomrk&password=9f501dad1443c91188f52dfeb2eeff7dd405f34eaa8ef28bbed757fef0ec3f21");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return source;
    }
}
