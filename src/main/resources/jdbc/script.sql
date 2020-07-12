DROP TABLE IF EXISTS phone_numbers;

CREATE TABLE phone_numbers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NULL,
    phone_number VARCHAR(25) NOT NULL
);

INSERT INTO phone_numbers (name, phone_number) VALUES
('Cully Flanigan', '+60 (430) 611-6074'),
('Corilla Barca', '+86 (588) 264-0167'),
('Betteann Benterman', '+1 (893) 194-9323'),
('Mic Hanhard', '+63 (422) 370-0875'),
('Leandra Palphramand', '+48 (339) 363-7435'),
('Osbourne Lourens', '+63 (105) 766-2314'),
('Benton MacGovern', '+46 (421) 179-1462'),
('Merralee Grissett', '+972 (549) 749-7353'),
('Stephannie Lethardy', '+62 (677) 412-4859'),
('Trumann Minci', '+63 (404) 630-2467');