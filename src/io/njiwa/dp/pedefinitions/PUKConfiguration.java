/*
 * Kasuku - Open Source eUICC Remote Subscription Management Server
 * 
 * 
 * Copyright (C) 2019 - , Digital Solutions Ltd. - http://www.dsmagic.com
 *
 * Paul Bagyenda <bagyenda@dsmagic.com>
 * 
 * This program is free software, distributed under the terms of
 * the GNU General Public License.
 */ 

/**
 * This class file was automatically generated by jASN1 v1.6.0 (http://www.openmuc.org)
 */

package io.njiwa.dp.pedefinitions;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerIdentifier;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.types.BerOctetString;

import java.io.IOException;
import java.io.InputStream;


public class PUKConfiguration {

	public static final BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
	protected BerIdentifier id;

	public byte[] code = null;
	private PUKKeyReferenceValue keyReference = null;

	private BerOctetString pukValue = null;

	private UInt8 maxNumOfAttempsRetryNumLeft = null;

	public PUKConfiguration() {
		id = identifier;
	}

	public PUKConfiguration(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public void setKeyReference(PUKKeyReferenceValue keyReference) {
		this.keyReference = keyReference;
	}

	public PUKKeyReferenceValue getKeyReference() {
		return keyReference;
	}

	public void setPukValue(BerOctetString pukValue) {
		this.pukValue = pukValue;
	}

	public BerOctetString getPukValue() {
		return pukValue;
	}

	public void setMaxNumOfAttempsRetryNumLeft(UInt8 maxNumOfAttempsRetryNumLeft) {
		this.maxNumOfAttempsRetryNumLeft = maxNumOfAttempsRetryNumLeft;
	}

	public UInt8 getMaxNumOfAttempsRetryNumLeft() {
		return maxNumOfAttempsRetryNumLeft;
	}

	public int encode(BerByteArrayOutputStream os, boolean explicit) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			if (maxNumOfAttempsRetryNumLeft != null) {
				codeLength += maxNumOfAttempsRetryNumLeft.encode(os, false);
				// write tag: CONTEXT_CLASS, PRIMITIVE, 2
				os.write(0x82);
				codeLength += 1;
			}
			
			codeLength += pukValue.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
			
			codeLength += keyReference.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 0
			os.write(0x80);
			codeLength += 1;
			
			codeLength += BerLength.encodeLength(os, codeLength);
		}

		if (explicit) {
			codeLength += id.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is, boolean explicit) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerIdentifier berIdentifier = new BerIdentifier();

		if (explicit) {
			codeLength += id.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		codeLength += totalLength;

		subCodeLength += berIdentifier.decode(is);
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
			keyReference = new PUKKeyReferenceValue();
			subCodeLength += keyReference.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
			pukValue = new BerOctetString();
			subCodeLength += pukValue.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
			maxNumOfAttempsRetryNumLeft = new UInt8();
			subCodeLength += maxNumOfAttempsRetryNumLeft.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("SEQUENCE{");
		sb.append("keyReference: ").append(keyReference);
		
		sb.append(", ");
		sb.append("pukValue: ").append(pukValue);
		
		if (maxNumOfAttempsRetryNumLeft != null) {
			sb.append(", ");
			sb.append("maxNumOfAttempsRetryNumLeft: ").append(maxNumOfAttempsRetryNumLeft);
		}
		
		sb.append("}");
		return sb.toString();
	}

}

