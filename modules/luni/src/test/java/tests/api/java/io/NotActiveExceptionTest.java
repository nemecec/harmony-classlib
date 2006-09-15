/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.api.java.io;

import java.io.ByteArrayOutputStream;
import java.io.NotActiveException;
import java.io.ObjectOutputStream;

public class NotActiveExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.io.NotActiveException#NotActiveException()
	 */
	public void test_Constructor() {
		// Test for method java.io.NotActiveException()
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new ByteArrayOutputStream());
			os.defaultWriteObject();
		} catch (NotActiveException e) {
			// Correct
			return;
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
		fail("Failed to throw exception");
	}

	/**
	 * @tests java.io.NotActiveException#NotActiveException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.io.NotActiveException(java.lang.String)
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new ByteArrayOutputStream());
			os.defaultWriteObject();
		} catch (NotActiveException e) {
			// Correct
			return;
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
		fail("Failed to throw expected exception");
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
