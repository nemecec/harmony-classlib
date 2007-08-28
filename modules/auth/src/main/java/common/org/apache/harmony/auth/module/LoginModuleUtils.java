/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.auth.module;

import java.io.IOException;
import java.io.InputStream;

public class LoginModuleUtils {

    /**
     * Reads the password stored in an inputstream to a char array.
     * 
     * @param in
     *            an inputstream which stores the password.
     * @return a char array which contains the password.
     * @throws IOException
     */
    public static char[] getPassword(InputStream in) throws IOException {
        char[] buffer = new char[512];

        // 1.Just ASCII encoding is supported. bytes read from inputstream is
        // cast and put into char array.
        // 2.just read one line.
        int length = 0;
        int nextChar = -1;
        boolean hasCarriage = false;

        do {
            nextChar = in.read();
            if (nextChar == -1 || nextChar == '\n') {
                break;
            }

            if (hasCarriage) {
                buffer = appendChars(buffer, '\r', length++);
                hasCarriage = false;
            }

            if (nextChar == '\r') {
                hasCarriage = true;
            } else {
                buffer = appendChars(buffer, (char) nextChar, length++);
            }

        } while (true);

        if (length == 0) {
            return null;
        }

        char[] password = new char[length];
        System.arraycopy(buffer, 0, password, 0, length);
        return password;
    }

    private static char[] appendChars(char[] src, char c, int position) {
        char[] dest = src;
        if (position == src.length) {
            dest = new char[src.length * 2];
            System.arraycopy(src, 0, dest, 0, src.length);
        }
        dest[position] = c;
        return dest;
    }
}