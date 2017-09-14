FROM postgres:9.5
MAINTAINER Graham Baitson <grahambaitson@gmail.com.com>

ENV SQL_PATH=/opt/fa/database/sql

COPY *.sql $SQL_PATH/

COPY fa-services.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/fa-services.sh
