# CREATE TABLE IF NOT EXISTS category
# (
#     id          LONG NOT NULL PRIMARY KEY,
#     name        VARCHAR(255),
#     description VARCHAR(255)
# );
#
# CREATE TABLE IF NOT EXISTS product
# (
#     id                 LONG NOT NULL PRIMARY KEY,
#     name               VARCHAR(255),
#     description        VARCHAR(255),
#     available_quantities DOUBLE NOT NULL,
#     price              DECIMAL(10, 2),
#     category_id        LONG,
#     CONSTRAINT fk_category_id
#         FOREIGN KEY (category_id)
#             REFERENCES category (id)
# );
