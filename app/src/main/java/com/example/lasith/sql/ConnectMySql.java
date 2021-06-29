package com.example.lasith.sql;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.app.ProgressDialog.show;

public  class ConnectMySql extends AsyncTask<String, Void, String> {
    private TextView texdata;
        ConnectMySql(TextView txtdata){
            this.texdata = txtdata;
        }
        String res = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    private static final String url = "jdbc:mysql://192.168.43.6/myDB";
    private static final String user = "lasith";
    private static final String pass = "password";
        @Override
        protected  String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                st.execute("INSERT INTO tblCountries VALUES(20,'Japan')");
                ResultSet rs = st.executeQuery("select distinct Country from tblCountries");

                while (rs.next()) {
                    result += rs.getString(1).toString() + "\n";
                }
                res = result;
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            texdata.setText(result);
        }
    }
