### Flyway ClickHouse Fork

This is a Fork of Flyway https://flywaydb.org / https://github.com/flyway/flyway

With the clickhouse PR merged: https://github.com/flyway/flyway/issues/1772

It has been altered for working with ClickHouse clusters with the Distributed DDL turned on. As such, a cluster name
is required as an additional parameter (See the Flyway class and/or createMetaDataTable.sql)

Anything not related to ClickHouse has been trimmed out to improve build/test times.