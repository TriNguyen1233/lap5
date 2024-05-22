package builder.pattern1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

// Document.java (Abstract Class)
public abstract class Document {
    protected String name;
    protected String content;
    protected String fileType;
    protected boolean isEncrypted;

    public abstract void save();

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getFileType() {
        return fileType;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }
}