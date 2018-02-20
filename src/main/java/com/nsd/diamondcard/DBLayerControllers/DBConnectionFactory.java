package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

class DBConnectionFactory {

    private static volatile JdbcConnectionSource source = null;

    static JdbcConnectionSource getSource() {

        if (source == null) {
            try {
                Class.forName("org.postgresql.Driver");
                source = new JdbcConnectionSource("jdbc:postgresql://ec2-107-20-214-99.compute-1.amazonaws.com:5432/despop7cfvtigv?sslmode=require&user=ikbdtnvxgyxbed&password=92ed31ff0cc4ec2cf4037b3d1f83ae7f57542d8e32ab4075a669210f5cbc4c9d");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return source;
    }
}
