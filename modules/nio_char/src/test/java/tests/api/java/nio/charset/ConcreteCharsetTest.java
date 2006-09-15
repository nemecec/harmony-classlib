package tests.api.java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import junit.framework.TestCase;

/**
 * Super class for concrete charset test suites.
 */
public abstract class ConcreteCharsetTest extends TestCase {

	// the canonical name of this charset
	protected final String canonicalName;

	// the aliases set
	protected final String[] aliases;

	// canEncode
	protected final boolean canEncode;

	// isRegistered
	protected final boolean isRegistered;

	// charset instance
	protected Charset testingCharset;

	/*
	 * Initialize the field "testingCharset" here.
	 * 
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		this.testingCharset = Charset.forName(this.canonicalName);
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for ConcreteCharsetTest.
	 * 
	 */
	public ConcreteCharsetTest(String arg0, String canonicalName,
			String[] aliases, boolean canEncode, boolean isRegistered) {
		super(arg0);
		this.canonicalName = canonicalName;
		this.canEncode = canEncode;
		this.isRegistered = isRegistered;
		this.aliases = aliases;
	}

	/*
	 * Test canEncode.
	 */
	public void testCanEncode() {
		assertEquals(this.canEncode, this.testingCharset.canEncode());
	}

	/*
	 * Test isRegistered.
	 */
	public void testIsRegistered() {
		assertEquals(this.isRegistered, this.testingCharset.isRegistered());
	}

	/*
	 * Test name.
	 */
	public void testName() {
		assertEquals(this.canonicalName, this.testingCharset.name());
		// assertEquals(this.canonicalName, this.testingCharset.displayName());
		// assertEquals(this.canonicalName,
		// this.testingCharset.displayName(null));
	}

	/*
	 * Test aliases.
	 */
	public void testAliases() {
		for (int i = 0; i < this.aliases.length; i++) {
			Charset c = Charset.forName(this.aliases[i]);
			assertEquals(this.canonicalName, c.name());
			// TODO
			// assertTrue(this.testingCharset.aliases().contains(this.aliases[i]));
		}
	}

	/*
	 * Test the method encode(String) with null.
	 */
	public void testEncode_String_Null() {
		try {
			this.testingCharset.encode((String) null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/*
	 * Test the method encode(CharBuffer) with null.
	 */
	public void testEncode_CharBuffer_Null() {
		try {
			this.testingCharset.encode((CharBuffer) null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/*
	 * Test encoding.
	 */
	protected void internalTestEncode(String input, byte[] output) {
		ByteBuffer bb = this.testingCharset.encode(input);
		int i = 0;
		bb.rewind();
		while (bb.hasRemaining() && i < output.length) {
			assertEquals(output[i], bb.get());
			i++;
		}
		assertFalse(bb.hasRemaining());
		assertEquals(output.length, i);
	}

	/*
	 * Test encoding.
	 */
	public abstract void testEncode_Normal();

	/*
	 * Test decoding.
	 */
	protected void internalTestDecode(byte[] input, char[] output) {
		CharBuffer chb = this.testingCharset.decode(ByteBuffer.wrap(input));
		int i = 0;
		chb.rewind();
		while (chb.hasRemaining() && i < output.length) {
			assertEquals(output[i], chb.get());
			i++;
		}
		assertFalse(chb.hasRemaining());
		assertEquals(output.length, i);
	}

	/*
	 * Test decoding.
	 */
	public abstract void testDecode_Normal();
}
