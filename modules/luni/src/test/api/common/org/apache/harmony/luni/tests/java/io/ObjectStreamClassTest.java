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

package org.apache.harmony.luni.tests.java.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Proxy;

import junit.framework.TestCase;

public class ObjectStreamClassTest extends TestCase {

    static class DummyClass implements Serializable {
        private static final long serialVersionUID = 999999999999999L;

        long bam = 999L;

        int ham = 9999;

		public static long getUID() {
			return serialVersionUID;
		}
	}
    
    /**
     * @tests java.io.ObjectStreamClass#forClass()
     */
    public void test_forClass() {
        // Need to test during serialization to be sure an instance is
        // returned
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        assertTrue("forClass returned an object: " + osc.forClass(), osc
                .forClass().equals(DummyClass.class));
    }

    /**
     * @tests java.io.ObjectStreamClass#getField(java.lang.String)
     */
    public void test_getFieldLjava_lang_String() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        assertEquals("getField did not return correct field", 'J', osc
                .getField("bam").getTypeCode());
        assertNull("getField did not null for non-existent field", osc
                .getField("wham"));
    }

    /**
     * @tests java.io.ObjectStreamClass#getFields()
     */
    public void test_getFields() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        ObjectStreamField[] osfArray = osc.getFields();
        assertTrue(
                "Array of fields should be of length 2 but is instead of length: "
                        + osfArray.length, osfArray.length == 2);
    }

    /**
     * @tests java.io.ObjectStreamClass#getName()
     */
    public void test_getName() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        assertTrue(
                "getName returned incorrect name: " + osc.getName(),
                osc
                        .getName()
                        .equals(
                                "org.apache.harmony.luni.tests.java.io.ObjectStreamClassTest$DummyClass"));
    }

    /**
     * @tests java.io.ObjectStreamClass#getSerialVersionUID()
     */
    public void test_getSerialVersionUID() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        assertTrue("getSerialversionUID returned incorrect uid: "
                + osc.getSerialVersionUID() + " instead of "
                + DummyClass.getUID(), osc.getSerialVersionUID() == DummyClass
                .getUID());
    }

    /**
     * @tests java.io.ObjectStreamClass#toString()
     */
    public void test_toString() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        String oscString = osc.toString();

        // The previous test was more specific than the spec so it was replaced
        // with the test below
        assertTrue("toString returned incorrect string: " + osc.toString(),
                oscString.indexOf("serialVersionUID") >= 0
                        && oscString.indexOf("999999999999999L") >= 0);
        ;
    }

    /**
     * @tests java.io.ObjectStreamClass#lookup(java.lang.Class)
     */
    public void test_lookupLjava_lang_Class() {
        ObjectStreamClass osc = ObjectStreamClass.lookup(DummyClass.class);
        assertTrue(
                "lookup returned wrong class: " + osc.getName(),
                osc
                        .getName()
                        .equals(
                                "org.apache.harmony.luni.tests.java.io.ObjectStreamClassTest$DummyClass"));
    }

    public void testSerialization() {
        ObjectStreamClass osc = ObjectStreamClass
                .lookup(ObjectStreamClass.class);
        assertEquals(0, osc.getFields().length);
    }

    public void test_specialTypes() {
        Class<?> proxyClass = Proxy.getProxyClass(this.getClass()
                .getClassLoader(), new Class[] { Runnable.class });

        ObjectStreamClass proxyStreamClass = ObjectStreamClass
                .lookup(proxyClass);

        assertEquals("Proxy classes should have zero serialVersionUID", 0,
                proxyStreamClass.getSerialVersionUID());
        ObjectStreamField[] proxyFields = proxyStreamClass.getFields();
        assertEquals("Proxy classes should have no serialized fields", 0,
                proxyFields.length);

        ObjectStreamClass enumStreamClass = ObjectStreamClass
                .lookup(Thread.State.class);

        assertEquals("Enum classes should have zero serialVersionUID", 0,
                enumStreamClass.getSerialVersionUID());
        ObjectStreamField[] enumFields = enumStreamClass.getFields();
        assertEquals("Enum classes should have no serialized fields", 0,
                enumFields.length);
    }
    
        /**
     * @since 1.6 
     */
    static class NonSerialzableClass {
        private static final long serialVersionUID = 1l;
        public static long getUID() {
            return serialVersionUID;
        }
    }
    
    /**
     * @since 1.6
     */
    static class ExternalizableClass implements Externalizable {

        private static final long serialVersionUID = -4285635779249689129L;

        public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
            throw new ClassNotFoundException();
        }

        public void writeExternal(ObjectOutput output) throws IOException {
            throw new IOException();
        }
        
	}
	
    /**
     * @tests java.io.ObjectStreamClass#lookupAny(java.lang.Class)
     * @since 1.6
     */
    public void test_lookupAnyLjava_lang_Class() {
        // Test for method java.io.ObjectStreamClass
        // java.io.ObjectStreamClass.lookupAny(java.lang.Class)
        ObjectStreamClass osc = ObjectStreamClass.lookupAny(DummyClass.class);
        assertEquals("lookup returned wrong class: " + osc.getName(),
                "org.apache.harmony.luni.tests.java.io.ObjectStreamClassTest$DummyClass", osc
                        .getName());
        
        osc = ObjectStreamClass.lookupAny(NonSerialzableClass.class);
        assertEquals("lookup returned wrong class: " + osc.getName(),
                "org.apache.harmony.luni.tests.java.io.ObjectStreamClassTest$NonSerialzableClass",
                osc.getName());
        
        osc = ObjectStreamClass.lookupAny(ExternalizableClass.class);        
        assertEquals("lookup returned wrong class: " + osc.getName(),
                "org.apache.harmony.luni.tests.java.io.ObjectStreamClassTest$ExternalizableClass",
                osc.getName());

        osc = ObjectStreamClass.lookup(NonSerialzableClass.class);
        assertNull(osc);
        
    }
    
    
}
