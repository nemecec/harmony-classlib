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
package org.apache.harmony.pack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CPRef extends ConstantPoolEntry {

	CPClass className;
	transient int classNameIndex;

	protected CPNameAndType nameAndType;
	transient int nameAndTypeIndex;

	public CPRef(byte type, CPClass className, CPNameAndType descriptor) {
		super(type);
		this.className = className;
		this.nameAndType = descriptor;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CPRef other = (CPRef) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (nameAndType == null) {
			if (other.nameAndType != null)
				return false;
		} else if (!nameAndType.equals(other.nameAndType))
			return false;
		return true;
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		ClassFileEntry[] entries = new ClassFileEntry[2];
		entries[0] = className;
		entries[1] = nameAndType;
		return entries;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((className == null) ? 0 : className.hashCode());
		result = PRIME * result
				+ ((nameAndType == null) ? 0 : nameAndType.hashCode());
		return result;
	}

	protected void resolve(ClassConstantPool pool) {
		super.resolve(pool);
		nameAndTypeIndex = pool.indexOf(nameAndType);
		classNameIndex = pool.indexOf(className);
	}

   public String comparisonString() {
        // This one is tricky. The sorting appears to be
        // done based on the indices of the method descriptor
        // and class name in the classpool *after* sorting them.

       // If we haven't yet been resolved, just do a normal
       // compare (so things like .contains() work).
        if(!isResolved()) {
            return super.comparisonString();
        }

        // If we get here, the receiver has been resolved; there
        // is a different sort order.
        StringBuffer result = new StringBuffer();
        // Pad all numbers to 6 digits so they sort correctly.
        int padLength = 6;
        int classIndexLength = ("" + classNameIndex).length();
        int nameAndTypeIndexLength = ("" + nameAndTypeIndex).length();

        for(int index=0; index < (padLength - classIndexLength); index++) {
            result.append('0');
        }
        result.append("" + classNameIndex);
        result.append(":");
        for(int index=0; index < (padLength - nameAndTypeIndexLength); index++) {
            result.append('0');
        }
        result.append("" + nameAndTypeIndex);
        return result.toString();
    }

	public String toString() {
		String type;
		if (getTag() == ConstantPoolEntry.CP_Fieldref) {
			type = "FieldRef"; //$NON-NLS-1$
		} else if (getTag() == ConstantPoolEntry.CP_Methodref) {
			type = "MethoddRef"; //$NON-NLS-1$
		} else if (getTag() == ConstantPoolEntry.CP_InterfaceMethodref) {
			type = "InterfaceMethodRef"; //$NON-NLS-1$
		} else {
			type = "unknown"; //$NON-NLS-1$
		}
		return type + ": " + className + "#" + nameAndType; //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected void writeBody(DataOutputStream dos) throws IOException {
		dos.writeShort(classNameIndex);
		dos.writeShort(nameAndTypeIndex);
	}

}