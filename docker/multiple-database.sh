#!/bin/bash

set -e

psql -v ON_ERROR_STOP=1 --username digital_coffee <<-EOSQL
CREATE DATABASE shop_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO shop_db;

CREATE DATABASE user_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO user_db;

CREATE DATABASE customer_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO customer_db;

CREATE DATABASE menu_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO menu_db;

CREATE DATABASE notification_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO notification_db;

CREATE DATABASE order_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO order_db;

CREATE DATABASE camunda_db;
GRANT ALL PRIVILEGES ON DATABASE postgres TO camunda_db;
EOSQL
