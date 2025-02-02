package builder.pattern1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class ConfidentialDocument extends Document {
	public ConfidentialDocument(String name, String content) {
		this.name = name;
		if (content != null) {
			this.content = content;
		} else {
			throw new IllegalArgumentException("Content cannot be null");
		}
		this.fileType = ".zip";
		this.isEncrypted = true;
	}

	@Override
	public void save() {
		try {
			// Encrypt and save as .zip file
			String fileName = name + fileType;
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry entry = new ZipEntry(name + ".txt");
			zos.putNextEntry(entry);
			byte[] contentBytes = content.getBytes();
			// Encrypt the content using AES encryption
			byte[] encryptedBytes = encrypt(contentBytes);
			zos.write(encryptedBytes);
			zos.closeEntry();
			zos.close();
			System.out.println("Encrypted and saved as .zip file: " + fileName);
		} catch (IOException e) {
			System.out.println("Error saving file: " + e.getMessage());
		}
	}

	private byte[] encrypt(byte[] contentBytes) {
		try {
			// Generate a secret key for AES encryption
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128); // 128-bit key
			SecretKey secretKey = keyGen.generateKey();

			// Create a Cipher instance for AES encryption
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			// Encrypt the content bytes
			byte[] encryptedBytes = cipher.doFinal(contentBytes);

			return encryptedBytes;
		} catch (Exception e) {
			System.out.println("Error encrypting content: " + e.getMessage());
			return null;
		}
		// return contentBytes;
	}
}