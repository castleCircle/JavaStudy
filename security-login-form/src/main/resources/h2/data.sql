INSERT  INTO `user`(`id`,`username`,`password`,`algorithm`)
VALUES(1,'john','$2a$10$9VjgRGf4JXlYvgL5p6O0ku2uj7WsaWDFpudgOALNhgyCJFXSmxWve','BCRYPT')
;

INSERT  INTO `authority`(`id`,`name`,`user`)
VALUES(1,'READ','1')
;

INSERT  INTO `authority`(`id`,`name`,`user`)
VALUES(2,'WRITE','1')
;

INSERT  INTO `product`(`id`,`name`,`price`,`currency`)
VALUES(1,'Chocolate','10','USD')
;

