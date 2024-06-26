import java.sql.*;
import java.util.Scanner;

public class GaussDBDemo {
    static final String JDBC_DRIVER = "com.huawei.opengauss.jdbc.Driver";
    static final String DB_URL = "jdbc:opengauss://110.41.120.191:8000/Wechat?ApplicationName=app1";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "abc888888@";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner sc = new Scanner(System.in); // 创建一个扫描器对象，用于接收用户输入
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化 Statement 对象...");
            stmt = conn.createStatement();
            String sql;
            ResultSet rs;
            int choice; // 用于存储用户的选择
            do {
                System.out.println("\n欢迎使用GaussDBDemo程序，我们提供以下服务：");
                System.out.println("1. 简单增删改");
                System.out.println("2. 查询");
                System.out.println("3. 进阶查询");
                System.out.println("0. 退出程序");
                System.out.println("请输入你想要执行的功能编号：");
                choice = sc.nextInt(); // 接收用户输入的整数
                switch (choice) {
                    case 1:
                        // 功能1：执行简单增删改功能
                        System.out.println("\n1. 用户1点击退出按钮，退出微信，请用SQL语句更新用户的状态。");
                        System.out.println("2. 2021年华为正式发布了基于鸿蒙的手机，鸿蒙操作系统名称是Harmony，请在登录设备中增加一项，设设备的编号是6。");
                        System.out.println("3. 2021年华为正式发布了openEulerc操作系统，请在登录设备中增加一项。");
                        System.out.println("4. 公安局展开新一轮预警，首先需要清除预警表中的所有历史记录，请完成。");
                        System.out.println("5. 预警规则1是单笔转账超过400的用户，将该用户的微信UID以及转账的时间存入Warning表。");
                        System.out.println("6. 预警规则2是累计转出超过1000的用户以及最后一次转账时间存入预警表。");
                        System.out.println("7. 查询");
                        System.out.println("0. 返回上一级菜单。");
                        System.out.println("请输入你想要执行的功能编号：");
                        int query1 = sc.nextInt(); // 接收用户输入的整数
                        System.out.println();
                        switch (query1) {
                            case 1:
                                // 增删改1：更新用户1的状态为2（离线）
                                sql = "UPDATE \"Schema_ljh_zzh\".UserInfo SET Status = 2 WHERE UID = 1";
                                stmt.executeUpdate(sql); // 执行更新语句
                                System.out.println("用户1的状态已更新为离线。");
                                break;
                            case 2:
                                // 增删改2：在登录设备中增加一项，设备编号为6，名称为Harmony
                                sql = "INSERT INTO \"Schema_ljh_zzh\".Device VALUES (6, 'Harmony')";
                                stmt.executeUpdate(sql); // 执行插入语句
                                System.out.println("登录设备中已增加一项，设备编号为6，名称为Harmony。");
                                break;
                            case 3:
                                // 增删改3：在登录设备中增加一项，设备编号为最大编号加1，名称为openEuler
                                sql = "INSERT INTO \"Schema_ljh_zzh\".Device(DID, Name) SELECT MAX(DID) + 1, 'openEuler' FROM \"Schema_ljh_zzh\".Device";
                                stmt.executeUpdate(sql); // 执行插入语句
                                System.out.println("登录设备中已增加一项，设备编号为最大编号加1，名称为openEuler。");
                                break;
                            case 4:
                                // 增删改4：删除预警表中的所有记录
                                sql = "DELETE FROM \"Schema_ljh_zzh\".Warning";
                                stmt.executeUpdate(sql); // 执行删除语句
                                System.out.println("预警表中的所有记录已删除。");
                                break;
                            case 5:
                                // 增删改5：将单笔转账超过400的用户的微信UID以及转账的时间存入Warning表
                                sql = "INSERT INTO \"Schema_ljh_zzh\".Warning SELECT UID1, SendTime FROM \"Schema_ljh_zzh\".Transfer WHERE Amount >= 400";
                                stmt.executeUpdate(sql); // 执行插入语句
                                System.out.println("单笔转账超过400的用户的微信UID以及转账的时间已存入Warning表。");
                                break;
                            case 6:
                                // 增删改6：将累计转出超过1000的用户以及最后一次转账时间存入预警表
                                sql = "INSERT INTO \"Schema_ljh_zzh\".Warning SELECT UID1, MAX(SendTime) FROM \"Schema_ljh_zzh\".Transfer GROUP BY UID1 HAVING SUM(Amount) >= 1000";
                                stmt.executeUpdate(sql); // 执行插入语句
                                System.out.println("累计转出超过1000的用户以及最后一次转账时间已存入预警表。");
                                break;
                            case 0:
                                // 返回上一级菜单
                                System.out.println("返回上一级菜单。");
                                break;
                            default:
                                // 输入无效的编号
                                System.out.println("请输入有效的编号！");
                                break;
                        }
                        break;
                    case 2:
                        // 功能2：执行查询功能
                        System.out.println("\n1. 列出每一个用户在每一个设备类型上登录微信次数。");
                        System.out.println("2. 列出用户1的好友UID。");
                        System.out.println("3. 列出每一个用户的好友数目。");
                        System.out.println("4. 列出用户1和用户3的共同好友。");
                        System.out.println("5. 统计用户1和用户3的共同好友的数量。");
                        System.out.println(
                                "6. 统计每一个用户对之间的好友数量。");
                        System.out.println("7. 显示没有共同好友的用户对。");
                        System.out.println("8. 查询聊天记录中包含‘账号’两个字的用户聊天记录。");
                        System.out.println("9. 查询只有聊天记录，但是没有转账记录的用户微信UID。");
                        System.out.println("10. 统计用户1主动发送至少5条消息的用户及消息总条数。");
                        System.out.println("11. 统计用户在2023年分别向哪些用户发送了多少条消息。");
                        System.out.println("12. 查询使用‘KylinOS’登录微信账号的用户名。");
                        System.out.println("13. 查询只使用KylinOS设备登录微信的用户账号。");
                        System.out.println("14. 查询至少在两种设备上登录过微信账号的用户UID。");
                        System.out.println("15. 查询在所有设备上均实现登录的微信用户名。");
                        System.out.println("16. 查询连续七天之内有两笔向外转账的用户微信ID。");
                        System.out.println("0. 返回上一级菜单。");
                        System.out.println("请输入你想要执行的查询编号：");
                        int query2 = sc.nextInt(); // 接收用户输入的整数
                        System.out.println();
                        switch (query2) {
                            case 1:
                                // 查询1：列出每一个用户在每一个设备类型上登录微信次数,按照微信号从小到大输出，相同的微信号，按照登录次数从高到低输出。
                                sql = "SELECT UID, Name, COUNT(*) AS Total FROM \"Schema_ljh_zzh\".Login, \"Schema_ljh_zzh\".Device WHERE Login.DID = Device.DID GROUP BY UID, Name ORDER BY UID, Total DESC";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户ID\t设备名称\t登录次数");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID");
                                    String name = rs.getString("Name");
                                    int total = rs.getInt("Total");

                                    // 输出数据
                                    System.out.print(uid + "\t" + name + "\t" + total + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 2:
                                // 查询2：列出用户1的好友UID。
                                sql = "SELECT UID2 FROM \"Schema_ljh_zzh\".Friend WHERE UID1 = 1";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户1的好友UID：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid2 = rs.getInt("UID2");

                                    // 输出数据
                                    System.out.print(uid2 + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 3:
                                // 查询3：列出每一个用户的好友数目。
                                sql = "SELECT UID1, COUNT(*) AS Friends FROM \"Schema_ljh_zzh\".Friend GROUP BY UID1 ORDER BY UID1";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户ID\t好友数目");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");
                                    int friends = rs.getInt("Friends");

                                    // 输出数据
                                    System.out.print(uid1 + "\t" + friends + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 4:
                                // 查询4：列出用户1和用户3的共同好友。
                                sql = "SELECT f1.UID2 FROM \"Schema_ljh_zzh\".Friend f1 WHERE f1.UID1 = 1 AND f1.UID2 IN (SELECT f2.UID2 FROM \"Schema_ljh_zzh\".Friend f2 WHERE f2.UID1 = 3)";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户1和用户3的共同好友：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid2 = rs.getInt("UID2");

                                    // 输出数据
                                    System.out.print(uid2 + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 5:
                                // 查询5：统计用户1和用户3的共同好友的数量。
                                sql = "SELECT COUNT(*) AS Common FROM \"Schema_ljh_zzh\".Friend f1, \"Schema_ljh_zzh\".Friend f2 WHERE f1.UID1 = 1 AND f2.UID1 = 3 AND f1.UID2 = f2.UID2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户1和用户3的共同好友的数量：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int common = rs.getInt("Common");

                                    // 输出数据
                                    System.out.print(common + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 6:
                                // 查询6：统计每一个用户对之间的好友数量，如果他们之间没有好友就不显示，每个用户对只显示一次，比如1和2有5个好友，那么显示（1，2，5）或者（2，1，5）。
                                sql = "SELECT f1.UID1 AS UID1_1, f2.UID1 AS UID1_2, COUNT(*) AS Mutual FROM \"Schema_ljh_zzh\".Friend f1, \"Schema_ljh_zzh\".Friend f2 WHERE f1.UID1 < f2.UID1 AND f1.UID2 = f2.UID2 GROUP BY f1.UID1, f2.UID1";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("每一个用户对之间的好友数量：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1_1");
                                    int uid2 = rs.getInt("UID1_2");
                                    int mutual = rs.getInt("Mutual");

                                    // 输出数据
                                    System.out.print("(" + uid1 + "," + uid2 + "," + mutual + ")\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 7:
                                // 查询7：显示没有共同好友的用户对。
                                sql = "SELECT u1.UID AS UID1, u2.UID AS UID2 FROM \"Schema_ljh_zzh\".UserInfo u1, \"Schema_ljh_zzh\".UserInfo u2 WHERE u1.UID < u2.UID AND NOT EXISTS (SELECT * FROM \"Schema_ljh_zzh\".Friend f1, \"Schema_ljh_zzh\".Friend f2 WHERE f1.UID1 = u1.UID AND f2.UID1 = u2.UID AND f1.UID2 = f2.UID2)";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("没有共同好友的用户对：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");
                                    int uid2 = rs.getInt("UID2");

                                    // 输出数据
                                    System.out.print("(" + uid1 + "," + uid2 + ")\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 8:
                                // 查询8：查询聊天记录中包含‘账号’两个字的用户聊天记录。
                                sql = "SELECT * FROM \"Schema_ljh_zzh\".Messages WHERE Content LIKE '%账号%'";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.printf("%-8s\t%-8s\t%-16s\t%-24s\t%-8s\n", "发送者ID", "接收者ID", "内容", "发送时间",
                                        "设备ID");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");
                                    int uid2 = rs.getInt("UID2");
                                    String content = rs.getString("Content");
                                    Timestamp sendTime = rs.getTimestamp("SendTime");
                                    int did = rs.getInt("DID");

                                    // 输出数据
                                    System.out.printf("%-8d\t%-8d\t%-16s\t%-24s\t%-8d\n", uid1, uid2, content, sendTime,
                                            did);
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 9:
                                // 查询9：查询只有聊天记录，但是没有转账记录的用户微信UID。
                                sql = "(SELECT UID1 FROM \"Schema_ljh_zzh\".Messages UNION SELECT UID2 FROM \"Schema_ljh_zzh\".Messages) EXCEPT (SELECT UID1 FROM \"Schema_ljh_zzh\".Transfer UNION SELECT UID2 FROM \"Schema_ljh_zzh\".Transfer)";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("只有聊天记录，但是没有转账记录的用户微信UID：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt(1);

                                    // 输出数据
                                    System.out.print(uid + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 10:
                                // 查询10：统计用户1主动向哪些用户发送至少5条消息，列出聊天对象和消息数。
                                sql = "SELECT UID2, COUNT(*) AS Total FROM \"Schema_ljh_zzh\".Messages WHERE UID1 = 1 GROUP BY UID2 HAVING Total >= 5";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户1主动向以下用户发送至少5条消息：");
                                System.out.printf("%-8s\t%-8s\n", "聊天对象", "消息数");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid2 = rs.getInt("UID2");
                                    int total = rs.getInt("Total");

                                    // 输出数据
                                    System.out.printf("%-8d\t%-8d\n", uid2, total);
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 11:
                                // 查询11：按照用户统计该用户在2023年分别向哪些用户发送了多少条消息，列出用户微信UID1，接受对象的微信UID2，消息总条数。
                                // 报错：year is supported only in B-format database
                                sql = "SELECT UID1, UID2, COUNT(*) AS Total FROM \"Schema_ljh_zzh\".Messages WHERE date_part('year', SendTime) = 2023 GROUP BY UID1, UID2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.printf("%-8s\t%-8s\t%-8s\n", "用户", "接受对象", "消息总条数");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");
                                    int uid2 = rs.getInt("UID2");
                                    int total = rs.getInt("Total");

                                    // 输出数据
                                    System.out.printf("%-8d\t%-8d\t%-8d\n", uid1, uid2, total);
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 12:
                                // 查询12：查询使用‘KylinOS’登录微信账号的用户名
                                sql = "SELECT U.Name FROM (\"Schema_ljh_zzh\".UserInfo U JOIN \"Schema_ljh_zzh\".Login L ON U.UID = L.UID) JOIN \"Schema_ljh_zzh\".Device D ON L.DID = D.DID WHERE D.Name = 'KylinOS'";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("使用‘KylinOS’登录微信账号的用户名：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    String name = rs.getString("Name");

                                    // 输出数据
                                    System.out.print(name + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 13:
                                // 查询13：查询只使用KylinOS设备登录微信的用户账号
                                sql = "SELECT UID FROM \"Schema_ljh_zzh\".Login L JOIN \"Schema_ljh_zzh\".Device D ON L.DID = D.DID GROUP BY UID HAVING COUNT(DISTINCT CASE WHEN D.Name = 'KylinOS' THEN L.DID END) = COUNT(DISTINCT L.DID)";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("只使用KylinOS设备登录微信的用户账号：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID");

                                    // 输出数据
                                    System.out.print(uid + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 14:
                                // 查询14：至少在两种设备上登录过微信账号的用户UID
                                sql = "SELECT UID FROM \"Schema_ljh_zzh\".Login GROUP BY UID HAVING COUNT(DISTINCT DID) >= 2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("至少在两种设备上登录过微信账号的用户UID：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID");

                                    // 输出数据
                                    System.out.print(uid + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 15:
                                // 查询15：查询在所有设备上均实现登录的微信用户名。
                                /*
                                 * sql =
                                 * "SELECT Name FROM \"Schema_ljh_zzh\".UserInfo WHERE NOT EXISTS (SELECT * FROM \"Schema_ljh_zzh\".Device WHERE NOT EXISTS (SELECT * FROM \"Schema_ljh_zzh\".Login WHERE Login.UID = UserInfo.UID AND Login.DID = Device.DID))"
                                 * ;
                                 */
                                sql = "SELECT Name FROM \"Schema_ljh_zzh\".UserInfo U JOIN ( SELECT UID FROM \"Schema_ljh_zzh\".Login GROUP BY UID HAVING COUNT(DISTINCT DID) = ( SELECT COUNT(*) FROM \"Schema_ljh_zzh\".Device ) ) AS AllDevices ON U.UID = AllDevices.UID";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("在所有设备上均实现登录的微信用户名：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    String name = rs.getString("Name");

                                    // 输出数据
                                    System.out.print(name + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 16:
                                // 查询16：查询连续七天之内有两笔向外转账的用户微信ID
                                sql = "SELECT DISTINCT UID1 FROM \"Schema_ljh_zzh\".Transfer t1 WHERE EXISTS ( SELECT * FROM \"Schema_ljh_zzh\".Transfer t2 WHERE t1.UID1 = t2.UID1 AND t1.SendTime < t2.SendTime AND TIMESTAMPDIFF(DAY, t1.SendTime, t2.SendTime) <= 7 )";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("连续七天之内有两笔向外转账的用户微信ID：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");

                                    // 输出数据
                                    System.out.print(uid1 + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 0:
                                // 返回上一级菜单
                                System.out.println("返回上一级菜单。");
                                break;
                            default:
                                // 输入无效的编号
                                System.out.println("请输入有效的编号！");
                                break;
                        }
                        break;
                    case 3:
                        // 功能3：执行进阶查询功能
                        System.out.println("\n1. 重建Friend表。");
                        System.out.println("2. 列出用户1的好友。");
                        System.out.println("3. 统计每一个用户的好友数目。");
                        System.out.println("4. 列出具有4个共同好友以上（包含），但自身不是好友的用户对。");
                        System.out.println("5. 查询2024年3月份，既有转出又有转入的用户，以及各自相应的总金额。");
                        System.out.println("6. 查询2024年3月份，每一个用户转出总额，转入总额。");
                        System.out.println("7. 统计每一对用户之间的发送信息的数目。");
                        System.out.println("0. 返回上一级菜单。");
                        System.out.println("请输入你想要执行的查询编号：");
                        int query3 = sc.nextInt(); // 接收用户输入的整数
                        System.out.println();
                        switch (query3) {
                            case 1:
                                // 进阶查询1：重建Friend表
                                stmt.executeUpdate("DELETE FROM \"Schema_ljh_zzh\".Friend"); // 清空Friend表
                                sql = "INSERT INTO \"Schema_ljh_zzh\".Friend (UID1, UID2, AddTime) VALUES"; // 定义插入语句的前半部分
                                System.out.println("请输入你想要重建的Friend表的元组，元组间以逗号分隔，以分号结束：");
                                sc.nextLine(); // 清除缓冲区
                                String rebuild = ""; // 初始化重建语句为空字符串
                                String line = ""; // 初始化每一行的输入为空字符串
                                do {
                                    line = sc.nextLine(); // 接收用户输入的一行
                                    rebuild += line + "\n"; // 将每一行的输入拼接到重建语句中，并换行
                                } while (!line.endsWith(";")); // 循环直到用户输入的一行以分号结束
                                try {
                                    stmt.executeUpdate(sql + rebuild); // 执行重建语句
                                    System.out.println("Friend表已重建。");
                                } catch (SQLException se) {
                                    // 处理SQL错误
                                    System.out.println("重建Friend表失败，请检查你输入的元组格式是否正确。");
                                    se.printStackTrace();
                                }
                                break;
                            case 2:
                                // 进阶查询2：列出用户1的好友
                                sql = "SELECT UID2 FROM \"Schema_ljh_zzh\".Friend WHERE UID1 = 1 UNION SELECT UID1 FROM \"Schema_ljh_zzh\".Friend WHERE UID2 = 1";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户1的好友：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID2");

                                    // 输出数据
                                    System.out.print(uid + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 3:
                                // 进阶查询3：统计每一个用户的好友数目
                                sql = "SELECT f1.UID1, count(*) + (SELECT COUNT(*) FROM \"Schema_ljh_zzh\".Friend F2 WHERE f2.uid2 = f1.uid1) AS Friends FROM \"Schema_ljh_zzh\".Friend f1 GROUP BY f1.UID1 UNION SELECT f3.UID2 AS UID, count(*) + (SELECT COUNT(*) FROM \"Schema_ljh_zzh\".Friend F4 WHERE f4.uid1 = f3.uid2) AS Friends FROM \"Schema_ljh_zzh\".Friend f3 GROUP BY f3.UID2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("用户ID\t好友数目");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID1");
                                    int friends = rs.getInt("Friends");

                                    // 输出数据
                                    System.out.print(uid + "\t" + friends + "\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 4:
                                // 进阶查询4：列出具有4个共同好友以上（包含），但自身不是好友的用户对，每一个用户对只列一次
                                sql = "SELECT a AS uid1, b AS uid2 FROM (SELECT f1.uid1 AS a, f2.uid1 AS b, f1.uid2 AS c FROM \"Schema_ljh_zzh\".Friend f1, \"Schema_ljh_zzh\".Friend f2 WHERE f1.uid2 = f2.uid2 AND f1.uid1 < f2.uid1 UNION SELECT f3.uid1 AS a, f4.uid2 AS b, f3.uid2 AS c FROM \"Schema_ljh_zzh\".Friend f3, \"Schema_ljh_zzh\".Friend f4 WHERE f3.uid2 = f4.uid1 AND f3.uid1 < f4.uid2 UNION SELECT f5.uid2 AS a, f6.uid2 AS b, f5.uid1 AS c FROM \"Schema_ljh_zzh\".Friend f5, \"Schema_ljh_zzh\".Friend f6 WHERE f5.uid1 = f6.uid1 AND f5.uid2 < f6.uid2 UNION SELECT f7.uid2 AS a, f8.uid1 AS b, f7.uid1 AS c FROM \"Schema_ljh_zzh\".Friend f7, \"Schema_ljh_zzh\".Friend f8 WHERE f7.uid1 = f8.uid2 AND f7.uid2 < f8.uid1) tmp WHERE tmp.a NOT IN (SELECT f9.uid1  FROM \"Schema_ljh_zzh\".Friend f9  WHERE tmp.b = f9.uid2 UNION SELECT f10.uid2 FROM \"Schema_ljh_zzh\".Friend f10  WHERE tmp.b = f10.uid1) GROUP BY a, b HAVING count(*) >= 4";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("具有4个共同好友以上，但自身不是好友的用户对：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("uid1");
                                    int uid2 = rs.getInt("uid2");

                                    // 输出数据
                                    System.out.print("(" + uid1 + "," + uid2 + ")\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 5:
                                // 进阶查询5：查询2024年3月份，既有转出又有转入的用户，以及各自相应的总金额
                                sql = "SELECT UID1, tmp1.AllOut, tmp2.AllIn FROM (SELECT UID1, SUM(amount) AS AllOut FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID1) tmp1 JOIN (SELECT UID2, SUM(amount) AS AllIn FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID2) tmp2 ON tmp1.UID1 = tmp2.UID2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.printf("%-8s\t%-8s\t%-8s\n", "用户ID", "转出金额", "转入金额");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID1");
                                    int allOut = rs.getInt("AllOut");
                                    int allIn = rs.getInt("AllIn");

                                    // 输出数据
                                    System.out.printf("%-8s\t%-8s\t%-8s\n", uid, allOut, allIn);
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 6:
                                // 查询6：查询2024年3月份，每一个用户转出总额，转入总额，如果两项都为零，不需要列出。如果一项有，另外一项没有，没有的显示为0。例如用户3只收到1笔1000转入金额，那么显示（3，null，1000）
                                sql = "SELECT UID1, tmp1.AllOut, tmp2.AllIn FROM (SELECT UID1, SUM(amount) AS AllOut FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID1) tmp1 LEFT JOIN (SELECT UID2, SUM(amount) AS AllIn FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID2) tmp2 ON tmp1.UID1 = tmp2.UID2 UNION SELECT UID2, tmp3.AllOut, tmp4.AllIn FROM (SELECT UID1, SUM(amount) AS AllOut FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID1)tmp3 RIGHT JOIN (SELECT UID2, SUM(amount) AS AllIn FROM \"Schema_ljh_zzh\".Transfer WHERE SENDTIME BETWEEN '2024-03-01 00:00:00' AND '2024-03-31 23:59:59' GROUP BY UID2) tmp4 ON tmp3.UID1 = tmp4.UID2";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("每一个用户转出总额，转入总额：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid = rs.getInt("UID1");
                                    int allOut = rs.getInt("AllOut");
                                    int allIn = rs.getInt("AllIn");

                                    // 输出数据
                                    System.out.print("(" + uid + "," + allOut + "," + allIn + ")\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 7:
                                // 查询7：统计每一对用户之间的发送信息的数目，例如1向2发送了5条信息，同时2向1发送了4条消息，那么他们之间的消息就是9条
                                sql = "SELECT tmp1.UID1,tmp1.UID2,tmp1.total+tmp2.total AS total FROM (SELECT UID1,UID2,Count(*) AS total FROM \"Schema_ljh_zzh\".Messages GROUP BY UID1,UID2) tmp1 JOIN (SELECT UID1,UID2,Count(*) AS total FROM \"Schema_ljh_zzh\".Messages GROUP BY UID1,UID2) tmp2 ON tmp1.UID1=tmp2.uid2 and tmp1.uid2=tmp2.uid1 UNION SELECT tmp5.UID1,tmp5.UID2,tmp5.total1 FROM ((SELECT UID1,UID2,Count(*) as total1 FROM \"Schema_ljh_zzh\".Messages GROUP BY UID1,UID2) tmp3 LEFT  JOIN (select UID1,UID2,Count(*) as total2 FROM \"Schema_ljh_zzh\".Messages GROUP BY UID1,UID2) tmp4 ON tmp3.UID1=tmp4.uid2 and tmp3.uid2=tmp4.uid1) AS tmp5(uid1,uid2,total1,uid3,uid4,total2) WHERE total2 IS null";
                                rs = stmt.executeQuery(sql); // 执行查询语句
                                System.out.println("每一对用户之间的发送信息的数目：");
                                while (rs.next()) {
                                    // 通过字段检索
                                    int uid1 = rs.getInt("UID1");
                                    int uid2 = rs.getInt("UID2");
                                    int total = rs.getInt("total");

                                    // 输出数据
                                    System.out.print("(" + uid1 + "," + uid2 + "," + total + ")\n");
                                }
                                rs.close(); // 关闭结果集对象
                                break;
                            case 0:
                                // 返回上一级菜单
                                System.out.println("返回上一级菜单。");
                                break;
                            default:
                                // 输入无效的编号
                                System.out.println("请输入有效的编号！");
                                break;
                        }
                        break;
                    case 0:
                        // 退出程序
                        System.out.println("\n感谢使用，再见！");
                        break;
                    default:
                        // 输入无效的编号
                        System.out.println("请输入有效的编号！");
                        break;
                }
            } while (choice != 0); // 循环输入直到用户输入0

            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            if (sc != null) // 关闭扫描器对象
                sc.close();
        }
        System.out.println("Goodbye!");
    }
}