#!/usr/bin/env sh
# Set up the fa services database

execute () {
  psql -d $POSTGRES_DB -U $POSTGRES_USER -f $1
}

cd $SQL_PATH
execute 01-create-uuid-extension.sql
execute 02-create-schema.sql
execute 03-initialise_with_dummy_data.sql
cd - > /dev/null
