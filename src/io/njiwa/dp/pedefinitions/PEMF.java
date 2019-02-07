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
import org.openmuc.jasn1.ber.types.BerObjectIdentifier;

import java.io.IOException;
import java.io.InputStream;


public class PEMF {

	public static final BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
	protected BerIdentifier id;

	public byte[] code = null;
	private PEHeader mfHeader = null;

	private BerObjectIdentifier templateID = null;

	private File mf = null;

	private File efPl = null;

	private File efIccid = null;

	private File efDir = null;

	private File efArr = null;

	private File efUmpc = null;

	public PEMF() {
		id = identifier;
	}

	public PEMF(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public void setMfHeader(PEHeader mfHeader) {
		this.mfHeader = mfHeader;
	}

	public PEHeader getMfHeader() {
		return mfHeader;
	}

	public void setTemplateID(BerObjectIdentifier templateID) {
		this.templateID = templateID;
	}

	public BerObjectIdentifier getTemplateID() {
		return templateID;
	}

	public void setMf(File mf) {
		this.mf = mf;
	}

	public File getMf() {
		return mf;
	}

	public void setEfPl(File efPl) {
		this.efPl = efPl;
	}

	public File getEfPl() {
		return efPl;
	}

	public void setEfIccid(File efIccid) {
		this.efIccid = efIccid;
	}

	public File getEfIccid() {
		return efIccid;
	}

	public void setEfDir(File efDir) {
		this.efDir = efDir;
	}

	public File getEfDir() {
		return efDir;
	}

	public void setEfArr(File efArr) {
		this.efArr = efArr;
	}

	public File getEfArr() {
		return efArr;
	}

	public void setEfUmpc(File efUmpc) {
		this.efUmpc = efUmpc;
	}

	public File getEfUmpc() {
		return efUmpc;
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
			if (efUmpc != null) {
				codeLength += efUmpc.encode(os, false);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 7
				os.write(0xa7);
				codeLength += 1;
			}
			
			codeLength += efArr.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 6
			os.write(0xa6);
			codeLength += 1;
			
			codeLength += efDir.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 5
			os.write(0xa5);
			codeLength += 1;
			
			codeLength += efIccid.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 4
			os.write(0xa4);
			codeLength += 1;
			
			if (efPl != null) {
				codeLength += efPl.encode(os, false);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 3
				os.write(0xa3);
				codeLength += 1;
			}
			
			codeLength += mf.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
			os.write(0xa2);
			codeLength += 1;
			
			codeLength += templateID.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
			
			codeLength += mfHeader.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			os.write(0xa0);
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
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)) {
			mfHeader = new PEHeader();
			subCodeLength += mfHeader.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
			templateID = new BerObjectIdentifier();
			subCodeLength += templateID.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)) {
			mf = new File();
			subCodeLength += mf.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 3)) {
			efPl = new File();
			subCodeLength += efPl.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)) {
			efIccid = new File();
			subCodeLength += efIccid.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 5)) {
			efDir = new File();
			subCodeLength += efDir.decode(is, false);
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 6)) {
			efArr = new File();
			subCodeLength += efArr.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berIdentifier.decode(is);
		}
		else {
			throw new IOException("Identifier does not match the mandatory sequence element identifer.");
		}
		
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 7)) {
			efUmpc = new File();
			subCodeLength += efUmpc.decode(is, false);
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
		sb.append("mfHeader: ").append(mfHeader);
		
		sb.append(", ");
		sb.append("templateID: ").append(templateID);
		
		sb.append(", ");
		sb.append("mf: ").append(mf);
		
		if (efPl != null) {
			sb.append(", ");
			sb.append("efPl: ").append(efPl);
		}
		
		sb.append(", ");
		sb.append("efIccid: ").append(efIccid);
		
		sb.append(", ");
		sb.append("efDir: ").append(efDir);
		
		sb.append(", ");
		sb.append("efArr: ").append(efArr);
		
		if (efUmpc != null) {
			sb.append(", ");
			sb.append("efUmpc: ").append(efUmpc);
		}
		
		sb.append("}");
		return sb.toString();
	}

}

