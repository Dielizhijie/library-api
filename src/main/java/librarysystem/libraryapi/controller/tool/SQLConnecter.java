package librarysystem.libraryapi.controller.tool;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

public class SQLConnecter {
    String[] NEED_RESULT = {"select"};
    String sql;
    HttpServletResponse response;
    String alertString = "数据库访问出错";
    CallBack callBack = null;

    public SQLConnecter(String sql, HttpServletResponse response, String alertString) {
        this.sql = sql;
        this.response = response;
        getResult();
    }

    public SQLConnecter(String sql, HttpServletResponse response) {
        this.sql = sql;
        this.response = response;
        getResult();
    }

    public void getConnection(CallBack callBack) {
        this.callBack = callBack;
    }

    private void getResult() {
        if (judgeIsNeedResult(sql)) {
            try {
                DBManager dbManager = new DBManager(sql);
                ResultSet result = null;
                result = dbManager.preparedStatement.executeQuery();
                onResult(result);
            } catch (Exception e) {
                Alert.popErrorAlert(response, alertString);
            }
        } else {
            try {
                DBManager dbManager = new DBManager(sql);
                dbManager.preparedStatement.executeUpdate();
                dbManager.close();
                onResult(null);
            } catch (Exception e) {
                Alert.popErrorAlert(response, alertString);
            }
        }
    }

    private boolean judgeIsNeedResult(String sql) {
        String[] tempString = sql.split(" ");
        boolean isNeedResult = false;
        for (int i = 0; i < NEED_RESULT.length; i++) {
            if (tempString[0].equals(NEED_RESULT[i])) {
                isNeedResult = true;
            }
        }
        return isNeedResult;
    }

    private void onResult(ResultSet result) {
        callBack.onConnected(result);
    }

    public abstract static class CallBack {
        public abstract void onConnected(ResultSet resultSet);
    }
}
