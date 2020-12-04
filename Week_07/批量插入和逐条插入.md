```
-- # 插入数据到订单表中，关闭自动提交，批量插入
set autocommit=0;
DROP PROCEDURE IF EXISTS insert_order;
DELIMITER $
CREATE PROCEDURE insert_order()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i<=1000000 DO
            insert into `order` (sellerid,buyerid, snapshotid, state, createtime, updatetime)
            VALUES (CEILING(rand()*1000), CEILING(rand()*1000), CEILING(rand()*1000),  1, unix_timestamp(now()) , unix_timestamp(now()));
        SET i = i+1;
    END WHILE;
    commit;
END $
CALL insert_order();

-- # 插入数据到订单表中，自动提交，一条一条的插入
set autocommit=1;
DROP PROCEDURE IF EXISTS insert_order;
DELIMITER $
CREATE PROCEDURE insert_order()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=1000000 DO
            insert into `order` (sellerid,buyerid, snapshotid, state, createtime, updatetime)
            VALUES (CEILING(rand()*1000), CEILING(rand()*1000), CEILING(rand()*1000),  1, unix_timestamp(now()) , unix_timestamp(now()));
        SET i = i+1;
    END WHILE;
END $
CALL insert_order();
```

执行的机器是我本地电脑，2013年的macbookpro,16g内存，512g固态，i7 2.4GHZ。


|            | 10w    | 100w    |
| ---------- | ------ | ------- |
| 自动提交   | 17.66s | 188.38s |
| 非自动提交 | 8.61s  | 81.68s  |

