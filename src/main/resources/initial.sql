INSERT INTO `user` (`username`, `password`) VALUES
                                                ('admin', '$2a$10$Ptp1ZRU7jLFs3uI8RQnYm.0sgVsSQ55EJiU48rrFQVPpdOtVroqvK');

INSERT INTO product (code, name) VALUES
                                     ('P001', 'Shirt'),
                                     ('P002', 'Pants');

-- 布料
INSERT INTO material (name, product_id) VALUES
                                            ('Cotton', 1),
                                            ('Polyester', 1),
                                            ('Denim', 2),
                                            ('Wool', 2);

-- 颜色
INSERT INTO color (name, stock, material_id) VALUES
                                                 ('Red', 10, 1),
                                                 ('Blue', 5, 1),
                                                 ('White', 20, 2),
                                                 ('Black', 15, 3),
                                                 ('Gray', 8, 4);