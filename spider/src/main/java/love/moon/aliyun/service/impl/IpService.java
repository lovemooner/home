package love.moon.aliyun.service.impl;

import love.moon.aliyun.service.IPTools;
import love.moon.aliyun.pojo.HomeIP;
import love.moon.aliyun.service.IIpService;
import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;
import love.moon.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class IpService implements IIpService {
    public static final Logger LOG = LoggerFactory.getLogger(IpService.class);

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private Connection conn = null;

    {
        try {
            LOG.info("create DB Connection...");
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = PropertiesUtil.load("config.properties");
            URL = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.user");
            PASSWORD = properties.getProperty("jdbc.password");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }


    public boolean updateIPTime(Long id) {
        PreparedStatement pStmt = null;
        try {
            conn.setAutoCommit(false);
            String updateSQL = "update home_ip set last_update= ? where ID= ?";
            pStmt = conn.prepareStatement(updateSQL);
            pStmt.setLong(1, System.currentTimeMillis());
            pStmt.setLong(2, id);
            int result = pStmt.executeUpdate();
            LOG.info("update home_ip last_update success:{}", result == 1);
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    public boolean saveLatestIP(String latestIP) {
        PreparedStatement pStmt = null;
        try {
            conn.setAutoCommit(false);
            String SQL = "insert into home_ip(created,last_update,ip) values(?,?,?)";
            pStmt = conn.prepareStatement(SQL);
            pStmt.setLong(1, System.currentTimeMillis());
            pStmt.setLong(2, System.currentTimeMillis());
            pStmt.setString(3, latestIP);
            int result = pStmt.executeUpdate();
            LOG.info("save home_ip success:{}", result == 1);
            conn.commit();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        return false;
    }


    public HomeIP getCurrentIp() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,ip from home_ip order by created desc limit 1");
            String ip;
            while (rs.next()) {
                HomeIP homeIP = new HomeIP();
                homeIP.setId(rs.getLong(1));
                homeIP.setIp(rs.getString(2));
                return homeIP;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se2) {
                LOG.error(se2.getMessage(), se2);
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                LOG.error(se2.getMessage(), se2);
            }

        }
        return null;
    }

    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public String getLatestIp1() {
        try {
            HttpResponse response = HttpUtil.sendGet("http://ifconfig.me");
            if (response.getCode() == 200) {
                return response.getContent();
            } else {
                LOG.error("getLatestIp failed");
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;

    }

    public String getLatestIp() {
        String url1 = "http://www.cip.cc/";
        String url2 = "http://ifconfig.me/";
        String url3 = "http://icanhazip.com/";
        String[] ips = {url1, url2,url3};
        for (int i = 0; i < ips.length; i++) {
            String ip = IPTools.getMyIP(ips[i]);
            if (ip != null) {
                LOG.info("get ip success,from server:{}",ips[i]);
                return ip;
            }
        }
        return null;
    }


}




