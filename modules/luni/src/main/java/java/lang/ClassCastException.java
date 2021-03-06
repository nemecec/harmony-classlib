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

package java.lang;

import org.apache.harmony.luni.util.Msg;

/**
 * Thrown when a program attempts to cast a an object to a type with which it is
 * not compatible.
 */
public class ClassCastException extends RuntimeException {
    private static final long serialVersionUID = -9223365651070458532L;

    /**
     * Constructs a new {@code ClassCastException} that includes the current
     * stack trace.
     */
    public ClassCastException() {
        super();
    }

    /**
     * Constructs a new {@code ClassCastException} with the current stack trace
     * and the specified detail message.
     * 
     * @param detailMessage
     *            the detail message for this exception.
     */
    public ClassCastException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code ClassCastException} with the current stack trace
     * and a detail message based on the source and target class.
     * 
     * @param instanceClass
     *            the class being cast from.
     * @param castClass
     *            the class being cast to.
     */
    ClassCastException(Class<?> instanceClass, Class<?> castClass) {
        super(Msg.getString("K0340", instanceClass.getName(), castClass //$NON-NLS-1$
                .getName()));
    }
}
