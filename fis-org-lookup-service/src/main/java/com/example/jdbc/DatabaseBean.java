/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean that creates the database table
 */
public class DatabaseBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseBean.class);
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        String sql = "create table ORGANIZATION (\n"
              + "  org_id integer primary key,\n"
              + "  org_name varchar(60)\n"
              + ")";

        LOG.info("Creating table ORGANIZATION ...");

        try {
            jdbc.execute("drop table ORGANIZATION");
        } catch (Throwable e) {
            // ignore
        }

        jdbc.execute(sql);

        LOG.info("... created table ORGANIZATION");
    }

    public void destroy() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        try {
            jdbc.execute("drop table ORGANIZATION");
        } catch (Throwable e) {
            // ignore
        }
    }
}