/*
 * Copyright 2010-2017 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.dbsupport;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for SqlStatementBuilder.
 */
public class SqlStatementBuilderSmallTest {

    @Test
    public void stripDelimiter() {
        StringBuilder sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß';");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter(";", false));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'", sql.toString());
    }

    @Test
    public void stripDelimiterGo() {
        StringBuilder sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß'\nGO");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter("GO", true));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'\n", sql.toString());

        sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß'\ngo");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter("GO", true));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'\n", sql.toString());

        sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß'\nGo");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter("GO", true));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'\n", sql.toString());

        sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß'\ngO");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter("GO", true));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'\n", sql.toString());
    }

    @Test
    public void stripDelimiterCustom() {
        StringBuilder sql = new StringBuilder("SELECT * FROM t WHERE a = 'Straßenpaß'$ßß$");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter("$ßß$", false));
        assertEquals("SELECT * FROM t WHERE a = 'Straßenpaß'", sql.toString());
    }


    @Test
    public void stripDelimiterWithAnotherSpecialCharacters() {
        StringBuilder sql = new StringBuilder("SELECT * FROM t WHERE a = 'BİRİNİ';");
        SqlStatementBuilder.stripDelimiter(sql, new Delimiter(";", false));
        assertEquals("SELECT * FROM t WHERE a = 'BİRİNİ'", sql.toString());
    }
}